namespace FreeTubeExtensionService.Utils;

using System.Security.Claims;

public static class AuthExtension
{
    public static string GetUsername(this ClaimsPrincipal principal)
    {
        return principal.Claims.First(x => x.Type == "preferred_username").Value;
    }
}