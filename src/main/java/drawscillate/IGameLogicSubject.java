package drawscillate;

public interface IGameLogicSubject {

    /**
     * Add a Game Logic Observer
     * @param obj Observer Object
     */
    void registerObserver( IGameLogicObserver obj ) ;

    /**
     * Remove Observer
     */
    void removeObserver( ) ;

    /**
     * Broadcast Event to Observers
     */
    void notifyObserver( ) ;
}
