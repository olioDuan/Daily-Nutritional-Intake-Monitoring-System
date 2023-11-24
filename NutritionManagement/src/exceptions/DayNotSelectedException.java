package exceptions;

public class DayNotSelectedException extends Exception {
    public DayNotSelectedException() {
        super("No day selected!");
    }
}
