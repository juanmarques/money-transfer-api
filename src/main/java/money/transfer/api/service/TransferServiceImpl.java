package money.transfer.api.service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import io.micronaut.spring.tx.annotation.Transactional;
import money.transfer.api.exception.APIException;
import money.transfer.api.model.Account;
import money.transfer.api.model.Transaction;

/**
 * @author Juan Marques
 *
 *         12/01/2019
 */
@Singleton
public class TransferServiceImpl implements TransferService {

	@Inject
	private AccountServiceImpl accountService;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void transfer(Transaction transfer) {

		Optional<Account> fromAccount = accountService.getAccountByOwner(transfer.getFromId());

		Optional<Account> toAccount = accountService.getAccountByOwner(transfer.getToId());

		if (fromAccount.get().getId() == toAccount.get().getId()) {
			throw new APIException("Transfer to the same account is not allowed");
		}

		if (transfer.getAmount() <= 0) {
			throw new APIException("Transfer amount may not be negative");
		}

		if (transfer.getAmount() > fromAccount.get().getBalance()) {
			throw new APIException("Account " + transfer.getFromId() + " does not have enough balance to transfer");
		}

		fromAccount.get().setBalance(fromAccount.get().getBalance() - transfer.getAmount());
		toAccount.get().setBalance(toAccount.get().getBalance() + transfer.getAmount());

		accountService.save(fromAccount.get());
		accountService.save(toAccount.get());

		transfer.setHourOfTransfer(System.currentTimeMillis());
		save(transfer);
	}

	@Override
	public void save(Transaction transaction) {
		entityManager.persist(transaction);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Transaction> getHistorical() {
		return query("from Transaction").getResultStream().collect(Collectors.toList());
	}

	private TypedQuery<Transaction> query(String queryText) {
		return entityManager.createQuery(queryText, Transaction.class);
	}
}