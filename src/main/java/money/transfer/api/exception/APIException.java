package money.transfer.api.exception;

/**
 * @author Juan Marques
 *
 *         12/01/2019
 */
public class APIException extends RuntimeException {

	private static final long serialVersionUID = 3800402876613842531L;

	public APIException(String message) {
		super(message);
	}
}