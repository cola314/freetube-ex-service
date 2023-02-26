namespace FreeTubeExtensionService.Models.Entities;

using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

public class Playlist
{
    [Key]
    [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
    public long Id { get; set; }

    [Required]
    public string Name { get; set; }

    [Required]
    public bool IsPublic { get; set; }

    [Required]
    public string Username { get; set; }

    public ICollection<PlaylistMusic> Musics { get; set; }
}
