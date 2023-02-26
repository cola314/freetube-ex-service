﻿namespace FreeTubeExtensionService.Models.Entities;

using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

public class PlaylistMusic
{
    [Key]
    [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
    public long Id { get; set; }

    [Required]
    public string Title { get; set; }
    
    [Required]
    public string Thumbnail { get; set; }

    [Required]
    public string Owner { get; set; }

    [Required]
    public string VideoId { get; set; }
}
