namespace FreeTubeExtensionService.Configs
{
    public class ApplicationConfig : BaseConfig
    {
        public ApplicationConfig()
        {
            KEYCLOAK_URL = GetEnvString(nameof(KEYCLOAK_URL))!;
            DB_CONNECTION_STRING = GetEnvString(nameof(DB_CONNECTION_STRING))!;
        }

        public string KEYCLOAK_URL { get; }

        public string DB_CONNECTION_STRING { get; }
    }
}
