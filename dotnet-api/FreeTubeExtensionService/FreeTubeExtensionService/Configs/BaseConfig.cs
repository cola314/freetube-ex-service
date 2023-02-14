namespace FreeTubeExtensionService.Configs
{
    public abstract class BaseConfig
    {
        protected string? GetEnvString(string key)
        {
            return Environment.GetEnvironmentVariable(key);
        }
    }
}
