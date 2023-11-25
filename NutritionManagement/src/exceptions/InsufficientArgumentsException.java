package exceptions;

public class InsufficientArgumentsException extends Exception {
    public InsufficientArgumentsException(String m){
        super(m);
    }

    public InsufficientArgumentsException(){
        super("Insufficient Arguments!");
    }
}