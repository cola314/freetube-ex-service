namespace FreeTubeExtensionService.Controllers;

using FreeTubeExtensionService.Utils;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

[Authorize]
[ApiController]
[Route("/api/v1/auth")]
public class AuthController : ControllerBase
{
    [HttpGet("me")]
    public string GetUsername()
    {
        return User.GetUsername();
    }
}
