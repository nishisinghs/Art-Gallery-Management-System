package com.nishi.artgallerymanagementsystem.repository;

import com.nishi.artgallerymanagementsystem.entity.Artwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArtworkRepository extends JpaRepository<Artwork, Long> {

    List<Artwork> findByTitleContainingIgnoreCaseOrArtistContainingIgnoreCase(
            String title,
            String artist
    );
    List<Artwork> findByCategoryIgnoreCase(String category);
}