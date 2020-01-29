package drawscillate;

public interface IColorInvoker {
    /**
     * Set Command for Invoker
     * @param c Command Object
     */
    void setCommand( IColorCommand c ) ;

    /** Invoke Menu Function */
    void invoke() ;
}
