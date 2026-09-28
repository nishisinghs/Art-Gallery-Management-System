package com.nishi.artgallerymanagementsystem.service;

import com.nishi.artgallerymanagementsystem.entity.Artwork;
import java.util.List;

public interface ArtworkService {

    List<Artwork> getAllArtworks();

    Artwork getArtworkById(Long id);

    List<Artwork> searchArtworks(String keyword);

    List<Artwork> getArtworksByCategory(String category);

    long getCategoryCount();

    // Add Artwork
    Artwork saveArtwork(Artwork artwork);

    // Delete Artwork
    void deleteArtwork(Long id);
}