package money.transfer.api.service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import io.micronaut.spring.tx.annotation.Transactional;
import money.transfer.api.exception.APIException;
import money.transfer.api.model.Account;

/**
 * @author Juan Marques
 *
 *         12/01/2019
 */
@Singleton
public class AccountServiceImpl implements AccountService {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public void save(Account account) {
		if (account.getOwner().isEmpty() || account.getBalance() == 0.0) {
			throw new APIException("Owner/Balance parameter can't be null neither 0");
		}
		entityManager.persist(account);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Account> getAccountByOwner(int owner) {
		Optional<Account> account = Optional
				.of(query("from Account o where o.id = :owner").setParameter("owner", owner).getResultList().stream()
						.findFirst().orElseThrow(() -> new APIException("Account with id " + owner + " not found")));
		return account;
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Account> getAllAccounts() {
		return query("from Account").getResultStream().collect(Collectors.toList());
	}

	private TypedQuery<Account> query(String queryText) {
		return entityManager.createQuery(queryText, Account.class);
	}
}