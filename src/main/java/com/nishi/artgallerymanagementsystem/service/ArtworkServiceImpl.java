package com.nishi.artgallerymanagementsystem.service;

import com.nishi.artgallerymanagementsystem.entity.Artwork;
import com.nishi.artgallerymanagementsystem.repository.ArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtworkServiceImpl implements ArtworkService {

    @Autowired
    private ArtworkRepository artworkRepository;

    @Override
    public List<Artwork> getAllArtworks() {
        return artworkRepository.findAll();
    }

    @Override
    public Artwork getArtworkById(Long id) {
        return artworkRepository.findById(id).orElse(null);
    }

    @Override
    public List<Artwork> searchArtworks(String keyword) {
        return artworkRepository
                .findByTitleContainingIgnoreCaseOrArtistContainingIgnoreCase(
                        keyword,
                        keyword
                );
    }

    @Override
    public List<Artwork> getArtworksByCategory(String category) {
        return artworkRepository.findByCategoryIgnoreCase(category);
    }

    @Override
    public Artwork saveArtwork(Artwork artwork) {
        return artworkRepository.save(artwork);
    }

    @Override
    public void deleteArtwork(Long id) {
        artworkRepository.deleteById(id);
    }

    @Override
    public long getCategoryCount() {

        return artworkRepository.findAll()
                .stream()
                .map(Artwork::getCategory)
                .distinct()
                .count();
    }
}