package money.transfer.api.exception;

import javax.inject.Singleton;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;

@Produces
@Singleton
@Requires(classes = { APIException.class })
public class APIExceptionHandler implements ExceptionHandler<APIException, HttpResponse<?>> {

	@Override
	public HttpResponse<?> handle(HttpRequest request, APIException exception) {
		return HttpResponse.badRequest((exception.getMessage()));
	}
}