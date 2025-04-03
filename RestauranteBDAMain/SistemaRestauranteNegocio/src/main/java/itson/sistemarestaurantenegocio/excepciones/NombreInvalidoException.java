package itson.sistemarestaurantenegocio.excepciones;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

public class NombreInvalidoException extends Exception {

    public NombreInvalidoException() {
    }

    public NombreInvalidoException(String message) {
        super(message);
    }

    public NombreInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }

    public NombreInvalidoException(Throwable cause) {
        super(cause);
    }

    public NombreInvalidoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
