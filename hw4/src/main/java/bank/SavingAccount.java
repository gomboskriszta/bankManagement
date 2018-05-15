package bank;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.swing.JOptionPane;

public class SavingAccount extends Account {

	private static final long serialVersionUID = 1L;

	public SavingAccount(Person owner) {
		this.owner = owner;
		this.date = new Date();
		this.money = 0;

	}

	public SavingAccount(Person owner, String date) {
		SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
		this.owner = owner;
		try {
			this.date = format.parse(date);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Date is not valid");
		}
		this.money = 0;
	}

	/**
	 * @pre this.money > money
	 * @pre money > 0
	 * @post this.money < this.money@pre
	 */
	public void removeMoney(int money) {
		try {
			assert this.money > money;
			assert money > 0;
			assert isWellFormed();
			int preMoney = this.money;
			this.money -= money;
			assert this.money < preMoney;
			assert isWellFormed();
		} catch (AssertionError e) {
			JOptionPane.showMessageDialog(null, "Specify a valid amount of money to withdraw");
		}

	}

	public boolean isWellFormed() {
		if (owner == null || !owner.isWellFormed() || money == null || date == null || accountId == null)
			return false;
		if (money < 0)
			return false;
		return true;
	}

	public String toString() {
		try {
			assert isWellFormed();
			return " Details account "+ getAccountId();
		} catch (AssertionError e) {
			return null;
		}
	}

	public String toStringTextArea() {
		Random randomGenerator = new Random();
		double interest = money * 0.065 * randomGenerator.nextInt(10);
		float inter = (float)interest;
		try {
			assert isWellFormed();
			SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
			return " ------------------------------------------------------------\n"
					+ "  Type: Saving account\n"
					+ "  -----------------------------------------------------------\n"
					+ "  Owner:\n  " + owner.toString()
					+ "  \n ------------------------------------------------------------\n"
					+ "  Available money: " + money + "\n"
					+ "  Interest expense: " + inter + "\n"
					+ "  -----------------------------------------------------------\n\n"
					+ "  Date: " + format.format(date);
		} catch (AssertionError e) {
			return null;
		}
	}
	
	public String getType() {
		return "Saving account";
	}

}
