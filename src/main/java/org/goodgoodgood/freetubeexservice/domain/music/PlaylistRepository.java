package org.goodgoodgood.freetubeexservice.domain.music;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

    @Query("select p from Playlist p where p.user.email = :email")
    List<Playlist> findAllByUserEmail(String email);
}
