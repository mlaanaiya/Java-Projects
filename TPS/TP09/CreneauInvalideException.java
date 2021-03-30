/**
 * CreneauInvalideException indique qu'une date n'est pas valide.
 */
public class CreneauInvalideException extends IllegalArgumentException {

	public CreneauInvalideException(String message) {
		super(message);
	}

	public CreneauInvalideException() {
		super();
	}

	public CreneauInvalideException(Throwable cause) {
		super(cause);
	}

	public CreneauInvalideException(String message, Throwable cause) {
		super(message, cause);
	}

}
