package com.vsrdev.terminux_review_api.repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vsrdev.terminux_review_api.dto.ReviewDTO;
import com.vsrdev.terminux_review_api.mapper.ReviewMapper;
import com.vsrdev.terminux_review_api.model.domain.Review;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class FileReviewRepository implements ReviewRepository {

    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File("data/reviews.json");

    public void ensureFileExists() {

        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();

                mapper.writeValue(file, new ArrayList<>());
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar arquivo Json", e);
        }

    }

    private List<Review> readFromFile() {

        ensureFileExists();

        try {
            System.out.println("📂 [REPOSITORY] Lendo arquivo JSON...");

            List<ReviewDTO> dtos = mapper.readValue(
                    file, new TypeReference<List<ReviewDTO>>() {
                    });

            System.out.println("📄 Total de DTOs lidos: " + dtos.size());

            return dtos.stream()
                    .map(dto -> {
                        try {
                            return ReviewMapper.toDomain(dto);
                        } catch (Exception e) {
                            System.out.println("⚠️ Review inválida ignorada: " + dto);
                            e.printStackTrace(); // 🔥 ADICIONA ISSO
                            return null;
                        }
                    })
                    .filter(review -> review != null)
                    .collect(java.util.stream.Collectors.toList());

        } catch (Exception e) {
            System.out.println("💥 ERRO AO LER JSON:");
            e.printStackTrace(); // 🔥 ESSENCIAL
            throw new RuntimeException("Erro ao ler JSON", e);
        }
    }

    private void writeToFile(List<Review> reviews) {

        try {
            System.out.println("🧠 [REPOSITORY] Iniciando writeToFile");

            List<ReviewDTO> dtos = new ArrayList<>();

            for (Review review : reviews) {
                try {
                    System.out.println("🔄 Convertendo review ID: " + review.getId());
                    dtos.add(ReviewMapper.toDTO(review));
                } catch (Exception e) {
                    System.out.println("💥 Erro ao converter review ID: " + review.getId());
                    e.printStackTrace();
                }
            }

            System.out.println("💾 Escrevendo no arquivo...");

            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(file, dtos);

            System.out.println("✅ Escrita concluída");

        } catch (Exception e) {
            System.out.println("💥 ERRO GERAL NO writeToFile");
            e.printStackTrace();
            throw new RuntimeException("Erro ao escrever JSON", e);
        }
    }

    private List<ReviewDTO> readRawDTOs() {
        ensureFileExists();

        try {
            return mapper.readValue(file, new TypeReference<List<ReviewDTO>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler JSON", e);
        }
    }

    @Override
    public Review save(Review review) {

        List<Review> reviews = readFromFile();

        boolean exists = reviews.stream()
                .anyMatch(r -> r.getId().equals(review.getId()));

        if (exists) {
            reviews.removeIf(r -> r.getId().equals(review.getId()));
        }

        System.out.println("[REPOSITORY] Salvando review ID: " + review.getId());

        reviews.add(review);

        System.out.println("➡️ Chamando writeToFile...");
        writeToFile(reviews);
        System.out.println("⬅️ Voltou do writeToFile");

        return review;

    }

    @Override
    public List<Review> findByBookTitle(String bookTitle) {
        return readFromFile().stream()
                .filter(r -> r.getBookTitle().contains(bookTitle))
                .toList();
    }

    @Override
    public List<Review> findAll() {

        return readFromFile().stream()
                .toList();
    }

    @Override
    public Optional<Review> findById(Long id) {

        return readFromFile().stream()
                .filter(review -> review.getId().equals(id))
                .findFirst();
    }

    @Override
    public void deleteById(Long id) {

        List<Review> reviews = readFromFile();

        boolean removed = reviews.removeIf(r -> r.getId().equals(id));

        if (!removed) {
            throw new RuntimeException("Review não encontrada para deleção");
        }

        writeToFile(reviews);
    }

    @Override
    public boolean existsByUserNameAndBookTitle(String userName, String bookTitle) {

        return readRawDTOs().stream()
                .anyMatch(dto -> dto.getUserName().equalsIgnoreCase(userName) &&
                        dto.getBookTitle().equalsIgnoreCase(bookTitle));
    }

}
