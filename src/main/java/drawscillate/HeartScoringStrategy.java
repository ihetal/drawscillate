package drawscillate;
/**
 * Heart Scoring strategy
 */
public class HeartScoringStrategy implements IScoringStrategy {

    /**
    * Function name - calculateScore
    * Description   - calculate scores
    * @param  hits
    * @param difficulty 
    * @return      the score  -
     */
    @Override
    public int calculateScore(int hits, String difficulty) {
        switch (difficulty){
            case "Easy":
                return hits/700;
            case "Normal":
                return hits/1000;
            case "Hard":
                return hits/1200;
        }
        return 0;
    }
}
