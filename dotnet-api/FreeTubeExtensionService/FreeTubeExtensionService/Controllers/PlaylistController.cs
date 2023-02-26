namespace FreeTubeExtensionService.Controllers;

using FreeTubeExtensionService.Dtos;
using FreeTubeExtensionService.Models;
using FreeTubeExtensionService.Utils;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

[ApiController]
[Route("/api/v1/playlist")]
public class PlaylistController : Controller
{
    private readonly ApplicationDbContext _db;

    public PlaylistController(ApplicationDbContext db)
    {
        _db = db;
    }

    [HttpGet]
    public List<GetPlaylistDto> GetPlaylists()
    {
        return _db.Playlists
            .AsNoTracking()
            .Include(x => x.Musics)
            .Where(x => x.IsPublic)
            .Select(x => x.ToDto())
            .ToList();
    }

    [Authorize]
    [HttpPost]
    public long CreatePlaylist(CreatePlaylistDto dto)
    {
        var playlist = dto.ToEntity(username: User.GetUsername());
        _db.Playlists.Add(playlist);

        return playlist.Id;
    }
}
