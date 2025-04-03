package itson.sistemarestaurantenegocio.excepciones;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

public class IngredienteRegistradoException extends Exception{

    public IngredienteRegistradoException() {
    }

    public IngredienteRegistradoException(String message) {
        super(message);
    }

    public IngredienteRegistradoException(String message, Throwable cause) {
        super(message, cause);
    }

    public IngredienteRegistradoException(Throwable cause) {
        super(cause);
    }

    public IngredienteRegistradoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
