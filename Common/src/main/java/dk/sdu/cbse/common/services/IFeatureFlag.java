package dk.sdu.cbse.common.services;

public interface IFeatureFlag {
    boolean isFeatureEnabled(String feature);
}
