using FreeTubeExtensionService.Dtos;
using FreeTubeExtensionService.Models;
using FreeTubeExtensionService.Models.Entities;
using FreeTubeExtensionService.Utils;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace FreeTubeExtensionService.Controllers;

[Authorize]
[ApiController]
[Route("/api/v1/favorites")]
public class FavoriteController : ControllerBase
{
    private readonly ApplicationDbContext _db;

    public FavoriteController(ApplicationDbContext db)
    {
        _db = db;
    }

    [HttpGet]
    public IEnumerable<FavoriteDto> GetFavorites()
    {
        var username = User.GetUsername();

        return _db.Favorites
            .Where(x => x.Username == username)
            .Select(x => new FavoriteDto
            {
                Id = x.Id,
                Title = x.Title,
                Thumbnail = x.Thumbnail,
                Owner = x.Owner,
                VideoId = x.VideoId,
            });
    }

    [HttpPost]
    public long CreateFavorite(FavoriteDto dto)
    {
        var favorite = new Favorite
        {
            Id = dto.Id,
            Title = dto.Title,
            Thumbnail = dto.Thumbnail,
            Owner = dto.Owner,
            VideoId = dto.VideoId,
            Username = User.GetUsername(),
        };
        _db.Favorites.Add(favorite);
        _db.SaveChanges();

        return favorite.Id;
    }
}