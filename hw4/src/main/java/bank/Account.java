package bank;

import java.io.Serializable;
import java.util.Date;

import javax.swing.JOptionPane;

public abstract class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	protected Integer accountId = 0;
	protected Person owner;
	protected Integer money;
	protected Date date;

	public Person getOwner() {
		return owner;
	}

	public Integer getMoney() {
		return money;
	}

	public Integer getAccountId() {
		return accountId;
	}

	/**
	 * @pre newAccountId != null && newAccountId > 0
	 * @post accountId != null
	 */
	public void setAccountId(Integer newAccountId) {
		try {
			assert newAccountId != null && newAccountId > 0;
			assert isWellFormed();
			this.accountId = newAccountId;
			assert accountId != null;
			assert isWellFormed();

		} catch (AssertionError e) {
			JOptionPane.showMessageDialog(null, "Pre or post conditions fails at 'setAccountId' in class 'Account'");
		}

	}

	/**
	 * @pre money > 0
	 * @post this.money@pre < this.money
	 */
	public void addMoney(int money) {
		try {
			assert money > 0;
			assert isWellFormed();
			int preMoney = this.money;
			this.money += money;
			assert preMoney < this.money;
			assert isWellFormed();
		} catch (AssertionError e) {
			if (money < 0)
				JOptionPane.showMessageDialog(null, "Specify a positive number");
			else
				JOptionPane.showMessageDialog(null, "Pre or post condition fails at 'addMoney' in class 'Account'");
		}

	}

	public abstract void removeMoney(int money);

	public abstract boolean isWellFormed();

	public abstract String toString();
	
	public abstract String toStringTextArea();

	public abstract String getType();

}
