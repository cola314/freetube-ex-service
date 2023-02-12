using FreeTubeExtensionService.Models.Entities;
using Microsoft.EntityFrameworkCore;

namespace FreeTubeExtensionService.Models;

public class ApplicationDbContext : DbContext
{
    public ApplicationDbContext(DbContextOptions options) : base(options)
    {
    }

    public DbSet<Favorite> Favorites { get; set; }
}