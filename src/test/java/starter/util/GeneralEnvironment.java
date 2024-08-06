package starter.util;

import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

public class GeneralEnvironment {
    public static String environmentVariables(String url) {
        EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
        String environment = EnvironmentSpecificConfiguration.from(variables).getProperty(url);
        return environment;
    }
}
