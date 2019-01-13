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
import money.transfer.api.model.Account;
import money.transfer.api.service.AccountServiceImpl;

@Controller(value = "/api")
public class AccountController {

	@Inject
	private AccountServiceImpl accountServiceImpl;

	@Post("/account")
	@CircuitBreaker(reset = "30s")
	public HttpResponse<?> save(@Body @Valid Account account) {

		if (account.getOwner().isEmpty() || account.getBalance() == 0.0) {
			return HttpResponse.status(HttpStatus.BAD_REQUEST).body("Owner/Balance parameter can't be null neither 0");
		} else {
			accountServiceImpl.save(account);
			return HttpResponse.status(HttpStatus.CREATED).body(account);
		}

	}

	@Get("/account")
	@CircuitBreaker(reset = "30s")
	public HttpResponse<?> getAccounts() {
		Observable<Collection<Account>> account = Observable.just(accountServiceImpl.getAllAccounts());
		return HttpResponse.status(HttpStatus.OK).body(account);
	}
}