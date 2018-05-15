package bank;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class SpendingAccount extends Account {

	private static final long serialVersionUID = 1L;
	private Integer limit;

	
	public SpendingAccount(Person owner) {
		this.owner = owner;
		this.date = new Date();
		this.money = 0;
		this.limit = -1000;
	}
	
	public SpendingAccount(Person owner, String date) {
		this.owner = owner;
		this.money = 0;
		SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
		this.owner = owner;
		try {
			this.date = format.parse(date);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Date is not valid");
		}
		this.limit = -1000;

	}

	/**
	 * @pre this.money - money < limit
	 * @pre money > 0
	 * @post this.money < this.money@pre
	 */
	public void removeMoney(int money) {
		try {
			assert this.money - money > limit;
			assert money > 0;
			assert isWellFormed();
			int preMoney = this.money;
			this.money -= money;
			assert this.money < preMoney;
			assert isWellFormed();
		} catch (AssertionError e) {
			JOptionPane.showMessageDialog(null, "Pre or post condition fails at 'removeMoney' in class 'SpendingAccount'");
		}

	}

	public Integer getLimit() {
		return limit;
	}

	/**
	 * @pre newLimit != null
	 * @post limit@pre != limit
	 * @param newLimit
	 */
	public void setLimit(Integer newLimit) {
		try {
			assert newLimit != null;
			int preLimit = this.limit;
			this.limit = newLimit;
			assert this.limit != preLimit;
		} catch (AssertionError e) {
			JOptionPane.showMessageDialog(null, "Pre or post condition fails at 'setlimit' in class 'SpendingAccount'");
		}
	}
	
	public String toString() {
		try {
			assert isWellFormed();
			return " Details account "+ getAccountId();
		} catch (AssertionError e) {
			JOptionPane.showMessageDialog(null, "Pre or post conditions fails at 'toString' in class 'SpendingAccount'");
			return null;
		}
	}

	public String toStringTextArea() {
		try {
			assert isWellFormed();
			SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
			return " ------------------------------------------------------------\n"
					+ "  Type: Spending account\n"
					+ "  -----------------------------------------------------------\n"
					+ "  Owner:\n  " + owner.toString()
					+ "  \n ------------------------------------------------------------\n"
					+ "  Available money: " + money + "\n"
					+ "  Minimum for withdrawal: " + limit + "\n"
					+ "  -----------------------------------------------------------\n\n"
					+ "  Date: " + format.format(date);
		} catch (AssertionError e) {
			return null;
		}
	}
	
	public boolean isWellFormed() {
		if (owner == null || !owner.isWellFormed() || money == null || date == null || 
				accountId == null || limit == null)
			return false;
		if (money < limit)
			return false;
		return true;
	}

	public String getType() {
		return "Spending account";
	}

}
