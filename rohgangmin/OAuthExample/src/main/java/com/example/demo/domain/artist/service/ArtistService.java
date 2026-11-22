package com.example.demo.domain.artist.service;

import com.example.demo.domain.artist.dto.ArtistInfoResponseDto;
import com.example.demo.domain.artist.dto.ArtistSaveRequestDto;
import com.example.demo.domain.artist.entity.Artist;
import com.example.demo.domain.artist.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepository artistRepository;

    @Transactional
    public ArtistInfoResponseDto saveArtist(ArtistSaveRequestDto artistSaveRequestDto) {
        Artist artist = Artist.builder()
                .stageName(artistSaveRequestDto.stageName())
                .nationality(artistSaveRequestDto.nationality())
                .debutYear(artistSaveRequestDto.debutYear())
                .build();

        artistRepository.save(artist);

        return ArtistInfoResponseDto.from(artist);
    }

    @Transactional
    public void deleteArtist(Long artistId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 아티스트 정보를 찾을 수 없습니다."));

        artistRepository.deleteById(artistId);
    }

    @Transactional(readOnly = true)
    public List<ArtistInfoResponseDto> getAllArtists() {
        return artistRepository.findAll()
                .stream()
                .map(ArtistInfoResponseDto::from)
                .toList();
    }
}