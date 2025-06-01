package dk.sdu.cbse.common.score;

/**
 * {@code ScoreService} is used to keep track of the score in the game. <br></br>
 */
public interface ScoreService {
    /** {@code updateScore} is used to update the score whether by increasing or decreasing the score. <br></br>
     * <b> Pre-conditions </b>
     * <ul>
     *  <li> Score has not been updated yet </li>
     * </ul> <br>
     * <b> Post-conditions </b>
     * <ul>
     *  <li> Score is updated</li>
     * </ul>
     *
     * @param scoreIncrement indicates which value the score will change with
     *
     * @return A boolean indicating the success of updating the score
     */
    boolean updateScore(int scoreIncrement);

    /** {@code getScore} is used to retrieve the current score.
     * <b> Pre-conditions </b>
     * <ul>
     *  <li> Score has been initialized </li>
     * </ul> <br>
     * <b> Post-conditions </b>
     * <ul>
     *  <li> Score is retrieved </li>
     * </ul>
     *
     * @return The current score
     */
    int getScore();
}
