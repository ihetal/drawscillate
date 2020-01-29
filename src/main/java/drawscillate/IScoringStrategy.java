package drawscillate;

public interface IScoringStrategy {
    /**
    * Function name - calculateScore
    * Description   - calculate hits 
    * @param  hits    
    * @param  difficulty
    * @return        - int
     */
    public int calculateScore(int hits, String difficulty);
}
