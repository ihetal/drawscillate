package drawscillate;
/**
 *  score points
 */
public class RectangleScoringStrategy implements IScoringStrategy {
    /**
     * Function name - calculateScore
     * Description   - calculate hits 
     * @param  hits    
     * @param  difficulty
     * @return        - int
      */
    @Override
    public int calculateScore(int hits, String difficulty) {
        switch (difficulty){
            case "Easy":
                return hits/580;
            case "Normal":
                return hits/870;
            case "Hard":
                return hits/980;
        }
        return 0;
    }
}
