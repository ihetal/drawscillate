package drawscillate;

public interface IColorCommand {
    /** Execute the Command */
    void execute() ;

    /** 
     * Configure the Receiver for the Command
     * @param iColorReceiver Receiver
     */
    void setReceiver( IColorReceiver iColorReceiver ) ;
}
