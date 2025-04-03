import dk.sdu.cbse.common.services.IFeatureFlag;

module FeatureFlags {
    requires Common;

    provides IFeatureFlag with dk.sdu.cbse.featureFlag.FeatureFlagLoader;
}


