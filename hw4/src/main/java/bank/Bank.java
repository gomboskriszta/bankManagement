package bank;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Random;

import javax.swing.JOptionPane;

public class Bank implements BankProc {

	private Hashtable<Integer, Account> accounts;

	public Bank() {
		accounts = new Hashtable<Integer, Account>();
	}

	public void addAccount(Account account) {

		try {
			assert isWellFormed();
			assert account.isWellFormed();
			int preSize = getSize();

			Random rand = new Random();
			Integer accountKey = 0;
			do
				accountKey = rand.nextInt(10000);
			while (accounts.contains(accountKey));

			accounts.put(accountKey, account);
			account.setAccountId(accountKey);
			assert getSize() == preSize + 1;
			assert isWellFormed();
		} catch (AssertionError e) {}

	}

	public void removeAccount(Account account) {

		try {
			assert isWellFormed();
			assert getSize() > 0;
			assert account != null;
			assert accounts.contains(account);
			int preSize = getSize();
			
			accounts.remove(account.getAccountId());

			assert getSize() == preSize - 1;
			assert isWellFormed();
		} catch (AssertionError e) {
			JOptionPane.showMessageDialog(null, "Pre or post conditions fails at 'removeAccount' in class 'Bank'");
		}

	}

	@SuppressWarnings("unchecked")
	public void readAccounts() {
		try {
			assert isWellFormed();
			assert getSize() == 0;
			InputStream file = new FileInputStream("bank.ser");
			ObjectInputStream object = new ObjectInputStream(file);
			try {
				accounts = (Hashtable<Integer, Account>) object.readObject();
			} catch (ClassNotFoundException e) {
				System.err.println(e.getMessage());
			} finally {
				file.close();
				object.close();
			}
			assert isWellFormed();
		} catch (IOException | AssertionError e) {
			JOptionPane.showMessageDialog(null, "Pre or post conditions fails at 'readAccounts' in class 'Bank'");
		}
	}

	public void writeAccounts() {

		try {
			assert getSize() > 0;
			assert isWellFormed();
			OutputStream file = new FileOutputStream("bank.ser");
			ObjectOutputStream object = new ObjectOutputStream(file);
			try {
				object.writeObject(accounts);
			} finally {
				file.close();
				object.close();
			}
		} catch (IOException | AssertionError e) {
			JOptionPane.showMessageDialog(null, "Pre or post conditions fails at 'writeAccounts' in class 'Bank'");
		}
	}

	public boolean isWellFormed() {
		if (accounts == null)
			return false;
		for (Account account : accounts.values()) {
			if (account == null || !account.isWellFormed())
				return false;
		}
		return true;
	}

	public Account getAccount(int accountId) {
		return accounts.get(accountId);
	}
	
	public Hashtable<Integer, Account> getAccounts(){
		return accounts;
	}

	public int getSize() {
		return accounts.size();
	}

	public String toString() {
		String result = null;
		try {
			assert isWellFormed();
			result = accounts.toString();
			assert result != null;
		} catch (AssertionError e) {
			JOptionPane.showMessageDialog(null, "Pre or post conditions fails at 'report' in class 'Bank'");
		}
		return result;
	}

}
