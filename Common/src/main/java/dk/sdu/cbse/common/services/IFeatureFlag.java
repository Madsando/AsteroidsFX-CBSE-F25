package dk.sdu.cbse.common.services;

/**
 * {@code IFeatureFlag} is used to retrieve feature flags by consumers of this interface.
 *  The feature flags are defined in a {@code .properties} file for ease of use and the
 *  implementing classes are able to retrieve them.
 */
public interface IFeatureFlag {
    /**
     * {@code isFeatureEnabled} is to retrieve the feature flag for a specific feature based on the given tag.
     * It is used to toggle and control a feature. <br></br>
     *
     * <b>Pre-conditions</b>
     * <ul>
     *  <li> A feature is ready to get activated and has not been activated yet </li>
     *  <li> The feature has been tagged in the {@code .properties} file </li>
     * </ul> <br>
     * <b>Post-conditions</b>
     * <ul>
     *  <li> Returns a boolean describing if a feature is enabled </li>
     *  <li> Default is {@code false} if the tag or feature is not found </li>
     *  <li> A feature should be toggled based on this flag </li>
     * </ul>
     *
     * @param featureTag A tag used to find the feature flag
     * @return {@code true} or {@code false} depending on the feature flag. Default is {@code false} if the feature or tag is not found.
     */
    boolean isFeatureEnabled(String featureTag);
}
