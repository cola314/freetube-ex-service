using FreeTubeExtensionService.Configs;
using FreeTubeExtensionService.Models;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Tokens;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
var config = new ApplicationConfig();
builder.Services.AddSingleton(config);

builder.Services.AddDbContext<ApplicationDbContext>(options =>
    options.UseMySQL(config.DB_CONNECTION_STRING));

builder.Services.AddAuthentication(options =>
    {
        options.DefaultScheme = JwtBearerDefaults.AuthenticationScheme;
        options.DefaultChallengeScheme = JwtBearerDefaults.AuthenticationScheme;
    })
    .AddJwtBearer(options =>
    {
        options.TokenValidationParameters = new TokenValidationParameters()
        {
            ValidateIssuerSigningKey = true,
            ValidateIssuer = true,
            ValidIssuer = config.KEYCLOAK_URL,
            ValidateAudience = true,
            ValidAudience = "account",
            ValidateLifetime = true,
            IssuerSigningKeyResolver = (s, token, identifier, param) =>
            {
                using var client = new HttpClient();
                var response = client.GetStringAsync($"{config.KEYCLOAK_URL}/protocol/openid-connect/certs").Result;
                var keySet = JsonWebKeySet.Create(response);
                return keySet.Keys;
            }
        };
    });

var corsPolicy = "_myCorsPolicy";
builder.Services.AddCors(options =>
    {
        options.AddPolicy(corsPolicy, policy =>
        {
            policy.AllowAnyOrigin();
        });
    });

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.UseCors(corsPolicy);

app.UseAuthentication();
app.UseAuthorization();

app.MapControllers();

app.Run();
