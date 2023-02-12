using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace FreeTubeExtensionService.Models.Entities;

public class Favorite
{
    [Key]
    [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
    public long Id { get; set; }

    [Required]
    public string Username { get; set; }

    [Required]
    public string Title { get; set; }

    [Required]
    public string Thumbnail { get; set; }

    [Required]
    public string Owner { get; set; }

    [Required] 
    public string VideoId { get; set; }
}