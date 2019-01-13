package money.transfer.api.service;

import java.util.Collection;
import java.util.Optional;

import money.transfer.api.model.Account;

/**
 * @author Juan Marques
 *
 *         12/01/2019
 */
public interface AccountService {

	/**
	 * @param ownerId
	 * @return Account of the owner
	 */
	Optional<Account> getAccountByOwner(int owner);

	/**
	 * @return all accounts from db
	 */
	Collection<Account> getAllAccounts();

	/**
	 * @param account Save the account to the db
	 */
	void save(Account account);
}