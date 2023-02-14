namespace FreeTubeExtensionService.Dtos;

public class FavoriteDto
{
    public required long Id { get; init; }
    public required string Title { get; init; }
    public required string Thumbnail { get; init; }
    public required string Owner { get; init; }
    public required  string VideoId { get; init; }
}

public class CreateFavoriteDto
{
    public required string Title { get; init; }
    public required string Thumbnail { get; init; }
    public required string Owner { get; init; }
    public required string VideoId { get; init; }
}