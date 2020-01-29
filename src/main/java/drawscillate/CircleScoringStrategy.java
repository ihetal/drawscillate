package drawscillate;

/**
 * Score points of the circle class
 */
public class CircleScoringStrategy implements IScoringStrategy {
    /**
    * Function name - calculateScore
    * Description   - calculate score based on number of checkpoints passed
    * @param  hits  on the shape
    * @param  difficulty of the game
    * @return        -
     */
    @Override
    public int calculateScore(int hits, String difficulty) {
        switch (difficulty){
            case "Easy":
                return hits/670;
            case "Normal":
                return hits/950;
            case "Hard":
                return hits/1050;
        }
        return 0;
    }
}
