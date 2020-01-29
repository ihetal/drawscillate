package drawscillate;

public class StarScoringStrategy implements IScoringStrategy {
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
                return hits/850;
            case "Normal":
                return hits/1100;
            case "Hard":
                return hits/1200;
        }
        return 0;
    }
}
