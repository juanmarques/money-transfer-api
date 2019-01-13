package money.transfer.api.service;

import java.util.Collection;

import money.transfer.api.model.Transaction;

/**
 * @author Juan Marques
 *
 *         12/01/2019
 */
public interface TransferService {

	/**
	 * @param trans Transfers to account
	 */
	void transfer(Transaction trans);

	/**
	 * @return all recorded transactions
	 */
	Collection<Transaction> getHistorical();

	/**
	 * @param transaction Record the transaction
	 */
	void save(Transaction transaction);
}