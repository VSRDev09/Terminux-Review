package com.vsrdev.terminux_review_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vsrdev.terminux_review_api.api.response.ApiResponse;
import com.vsrdev.terminux_review_api.dto.ReviewDTO;
import com.vsrdev.terminux_review_api.dto.ReviewPatchDTO;
import com.vsrdev.terminux_review_api.service.ReviewService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ReviewDTO>>> listReviews() {

        List<ReviewDTO> reviews = service.listReviews();
        ApiResponse<List<ReviewDTO>> response = new ApiResponse<>(true, reviews, "Lista de Reviews");

        return ResponseEntity.ok(response);
    }

    @PostMapping

    public ResponseEntity<ApiResponse<ReviewDTO>> createReview(@RequestBody @Valid ReviewDTO dto) {

        System.out.println("[CONTROLLER] DTO recebido:");
        System.out.println(dto);

        ReviewDTO created = service.createReview(dto);

        ApiResponse<ReviewDTO> response = new ApiResponse<>(true, created, "Review criada com sucesso");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(params = "book")
    public ResponseEntity<ApiResponse<List<ReviewDTO>>> findByBookTitle(@RequestParam String book) {

        List<ReviewDTO> search = service.findByBookTitle(book);

        ApiResponse<List<ReviewDTO>> response = new ApiResponse<>(true, search, "Reviews encontradas");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewDTO>> findById(@PathVariable Long id) {

        ReviewDTO review = service.findById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true, review, "Review encontrada"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.ok(new ApiResponse<>(true, null, "Review deletada com sucesso"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewDTO>> update(
            @PathVariable Long id,
            @RequestBody @Valid ReviewDTO dto) {

        ReviewDTO updated = service.update(id, dto);

        return ResponseEntity.ok(
                new ApiResponse<>(true, updated, "Review atualizada"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewDTO>> patch(
            @PathVariable Long id,
            @RequestBody @Valid ReviewPatchDTO dto) {

        ReviewDTO updated = service.patchReview(id, dto);

        return ResponseEntity.ok(
                new ApiResponse<>(true, updated, "Review atualizada"));
    }

}
