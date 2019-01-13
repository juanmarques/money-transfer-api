package money.transfer.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Juan Marques
 *
 *         12/01/2019
 */

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private int id;
	private int fromId;
	private int toId;
	private double amount;
	private Long hourOfTransfer;

	public Transaction(int from, int to, double amount, long hourOfTransfer) {
		this.fromId = from;
		this.toId = to;
		this.amount = amount;
		this.hourOfTransfer = hourOfTransfer;
	}

	public Transaction() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Long getHourOfTransfer() {
		return hourOfTransfer;
	}

	public void setHourOfTransfer(Long hourOfTransfer) {
		this.hourOfTransfer = hourOfTransfer;
	}

	public int getFromId() {
		return fromId;
	}

	public void setFromId(int fromId) {
		this.fromId = fromId;
	}

	public int getToId() {
		return toId;
	}

	public void setToId(int toId) {
		this.toId = toId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Transaction other = (Transaction) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Transaction [From=" + fromId + ", To=" + toId + ", amount=" + amount + ", transferred in="
				+ hourOfTransfer + "]";
	}
}