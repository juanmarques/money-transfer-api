package money.transfer.api.controller;

import java.util.Collection;

import javax.inject.Inject;
import javax.validation.Valid;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.retry.annotation.CircuitBreaker;
import io.reactivex.Observable;
import money.transfer.api.model.Transaction;
import money.transfer.api.service.TransferServiceImpl;

/**
 * @author Juan Marques
 *
 *         12/01/2019
 */
@Controller(value = "/api")
public class TransferController {

	@Inject
	private TransferServiceImpl transferService;

	@Post("/transfer")
	@CircuitBreaker(reset = "30s")
	public HttpResponse<?> save(@Body @Valid Transaction trans) {

		if (trans.getFromId() == 0 || trans.getToId() == 0 || trans.getAmount() == 0.0) {
			return HttpResponse.status(HttpStatus.BAD_REQUEST).body("From/ To / Amount parameter can't be null neither 0");
		} else {
			transferService.transfer(trans);
			return HttpResponse.status(HttpStatus.OK).body(trans);
		}
	}

	@Get("/historical")
	@CircuitBreaker(reset = "30s")
	public HttpResponse<?> getTransferHistorical() {
		Observable<Collection<Transaction>> trans = Observable.just(transferService.getHistorical());
		return HttpResponse.status(HttpStatus.OK).body(trans);
	}
}