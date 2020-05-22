package exceptions;

public class IncorrectCityNameException extends RuntimeException {

    public IncorrectCityNameException(String msg) {
        super(msg);
    }
}
