package org.goodgoodgood.freetubeexservice.controller;

import lombok.RequiredArgsConstructor;
import org.goodgoodgood.freetubeexservice.config.auth.SessionUser;
import org.goodgoodgood.freetubeexservice.domain.music.MusicService;
import org.goodgoodgood.freetubeexservice.dto.music.AddMusicDto;
import org.goodgoodgood.freetubeexservice.dto.music.CreatePlaylistDto;
import org.goodgoodgood.freetubeexservice.dto.music.PlaylistDto;
import org.goodgoodgood.freetubeexservice.dto.music.PlaylistsShortDto;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MusicController {

    private final HttpSession httpSession;
    private final MusicService musicService;

    @GetMapping("api/playlists")
    public List<PlaylistsShortDto> getPlaylists() {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        return musicService.getPlaylistsByEmail(user.getEmail());
    }

    @GetMapping("api/playlists/{playlistId}")
    public PlaylistDto getPlaylistDetail(@PathVariable Long playlistId) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        return musicService.getPlaylist(user.getEmail(), playlistId);
    }

    @PostMapping("api/playlists")
    public Long createPlaylist(@RequestBody CreatePlaylistDto dto) throws Exception {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        return musicService.createPlaylist(user.getEmail(), dto);
    }

    @PostMapping("api/playlists/{playlistId}/musics")
    public Long addMusic(
            @PathVariable Long playlistId,
            @RequestBody AddMusicDto dto
    ) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        return musicService.addMusic(playlistId, user.getEmail(), dto);
    }

}
