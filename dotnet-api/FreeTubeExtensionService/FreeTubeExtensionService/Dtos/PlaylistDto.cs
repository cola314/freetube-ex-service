namespace FreeTubeExtensionService.Dtos;

using FreeTubeExtensionService.Models.Entities;

public class CreatePlaylistDto
{
    public string Name { get; init; }
    public bool IsPublic { get; init; }
    public List<MusicDto> Musics { get; init; } = new List<MusicDto>();

    public Playlist ToEntity(string username) => new Playlist
    {
        Name = Name,
        IsPublic = IsPublic,
        Username = username,
        Musics = Musics.Select(x => x.ToEntity()).ToArray(),
    };
}

public class MusicDto
{
    public string Title { get; init; }
    public string Thumbnail { get; init; }
    public string VideoId { get; init; }
    public string Owner { get; init; }

    public PlaylistMusic ToEntity() => new PlaylistMusic
    {
        Title = Title,
        Thumbnail = Thumbnail,
        VideoId = VideoId,
        Owner = Owner,
    };
}

public class GetPlaylistDto
{
    public string Name { get; init; }
    public bool IsPublic { get; init; }
    public List<MusicDto> Musics { get; init; } = new List<MusicDto>();
}

public static class PlaylistDtoExtensions
{
    public static GetPlaylistDto ToDto(this Playlist playlist)
    {
        return new GetPlaylistDto
        {
            Name = playlist.Name,
            IsPublic = playlist.IsPublic,
            Musics = playlist.Musics.Select(x => x.ToDto()).ToList()
        };
    }

    public static MusicDto ToDto(this PlaylistMusic music)
    {
        return new MusicDto
        {
            Owner = music.Owner,
            Thumbnail = music.Thumbnail,
            VideoId = music.VideoId,
            Title = music.Title,
        };
    }
}