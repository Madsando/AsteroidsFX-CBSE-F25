package dk.sdu.cbse.featureFlag;

import dk.sdu.cbse.common.services.IFeatureFlag;

import java.io.*;
import java.util.Objects;
import java.util.Properties;

public class FeatureFlagLoader implements IFeatureFlag {
    private static Properties properties = null;

    public FeatureFlagLoader() {
        if (properties == null) {
            properties = new Properties();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                    Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("featureFlags.properties"))))) {
                properties.load(reader);
            } catch (IOException | NullPointerException e) {
                System.out.println("FeatureFlag properties file not found");
            }
        }
    }

    @Override
    public boolean isFeatureEnabled(String feature) {
        return Boolean.parseBoolean(properties.getProperty(feature, "false"));
    }
}
