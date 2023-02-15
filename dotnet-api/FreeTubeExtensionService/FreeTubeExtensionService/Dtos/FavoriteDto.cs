namespace FreeTubeExtensionService.Dtos;

public class FavoriteDto
{
    public long Id { get; init; }
    public string Title { get; init; }
    public string Thumbnail { get; init; }
    public string Owner { get; init; }
    public  string VideoId { get; init; }
}

public class CreateFavoriteDto
{
    public string Title { get; init; }
    public string Thumbnail { get; init; }
    public string Owner { get; init; }
    public string VideoId { get; init; }
}