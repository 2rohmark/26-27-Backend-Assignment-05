package com.example.demo.domain.artist.dto;

public record ArtistSaveRequestDto(
        String stageName,
        String nationality,
        Integer debutYear
) {
}
