package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import bank.Account;
import bank.Bank;
import bank.Person;
import bank.SavingAccount;

class JUnitTest {

	Bank bank = new Bank();
	
	@Test
	void test() {
		Person person = new Person("Gombos", "Kriszta", "Baciu", "2199648567132", null);
		Account account = new SavingAccount(person);
		assert(account.getOwner().getFirstName() == "Gombos");
		assert(account.getOwner().getLastName() == "Kriszta");
		assert(account.getOwner().getCNP() == "2199648567132");
		assert(account.getOwner().getAddress() == "Baciu");
		bank.addAccount(account);
		bank.removeAccount(account);
		assert(bank.getAccounts().size() == 0);
	}

}
