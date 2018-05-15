package bank;

public interface BankProc {

	/**
	 * @pre account != null
	 * @post getSize() == getSize()@pre + 1
	 * @invariant isWellFormed()
	 */
	public abstract void addAccount(Account account);

	/**
	 * @pre getSize() != 0
	 * @pre account != null
	 * @pre accounts.contains (account)
	 * @post getSize() == preSize - 1;
	 * @invariant isWellFormed()
	 */
	public abstract void removeAccount(Account account);

	/**
	 * @pre getSize == 0
	 * @invariant isWellFormed()
	 */
	public abstract void readAccounts();

	/**
	 * @pre getSize() > 0
	 * @post @nochange
	 * @invariant isWellFormed()
	 */
	public abstract void writeAccounts();

	/**
	 * @post @result != null
	 * @invariant isWellFormed()
	 * @return
	 */
	public abstract String toString();
}
