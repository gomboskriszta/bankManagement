package bank;

import java.awt.Component;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.function.Function;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class View extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JTable table;
	private JFrame frame;
	private JScrollPane tableScroll, textAreaScroll;
	private Bank bank;
	private JTextArea textArea;
	private JTextField firstName, lastName, CNP, address, money, withdrawSum,
			depositSum, newLimit, accountId;
	private JComboBox<String> type;
	private JLabel textLabel, sum, removeLabel, background;
	private JButton addAccount, withdraw, deposit, changeLimit, removeAccount;

	public View() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(820, 700);
		this.setTitle("Bank");
		getContentPane().setLayout(null);
		
		frame = new JFrame();
		panel = new JPanel();
		panel.setBounds(0, 0, 820, 700);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		String[] colName = { "AccountID", "Type", "Owner", "Details" };
		DefaultTableModel model = new DefaultTableModel(colName, 0);
		table = new JTable(model);
		table.setFillsViewportHeight(true);
		tableScroll = new JScrollPane(table);
		
		textArea = new JTextArea("\n                          Account Details\n\n");
		textAreaScroll = new JScrollPane(textArea);
		withdraw = new JButton("Withdraw");
		withdraw.setBackground(SystemColor.activeCaption);
		deposit = new JButton("Deposit");
		deposit.setBackground(SystemColor.activeCaption);
		changeLimit = new JButton("Change");
		changeLimit.setBackground(SystemColor.activeCaption);
		addAccount = new JButton("Add Account");
		addAccount.setBackground(SystemColor.inactiveCaption);
		removeAccount = new JButton("Remove Account");
		removeAccount.setBackground(SystemColor.activeCaption);
		withdrawSum = new JTextField();
		depositSum = new JTextField();
		firstName = new JTextField();
		lastName = new JTextField();
		address = new JTextField();
		CNP = new JTextField();
		newLimit = new JTextField();
		money = new JTextField();
		accountId = new JTextField();
		type = new JComboBox<String>();
		removeLabel = new JLabel("<html>____________________________________<br>Remove Account:<br><br><br>ID:");
		sum = new JLabel("<html>Sum:<br><br><br><br>Sum:<br><br><br>Change limit:</html>");
		textLabel = new JLabel("<html>First name: <br><br><br>Last name: "
				+ "<br><br><br>Address: <br><br><br>CNP: <br><br><br>Starting deposit:");
		background = new JLabel(new ImageIcon("background.jpg"));
		
		tableScroll.setBounds(40, 40, 450, 270);
		withdraw.setBounds(670, 350, 100, 30);
		deposit.setBounds(670, 410, 100, 30);
		changeLimit.setBounds(670, 470, 100, 30);
		removeAccount.setBounds(640, 595, 130, 30);
		textAreaScroll.setBounds(520, 40, 250, 270);
		withdrawSum.setBounds(580, 352, 65, 25);
		depositSum.setBounds(580, 413, 65, 25);
		newLimit.setBounds(580, 475, 65, 25);
		sum.setBounds(525, 355, 50, 150);
		firstName.setBounds(200, 350, 150, 30);
		lastName.setBounds(200, 400, 150, 30);
		address.setBounds(200, 450, 150, 30);
		CNP.setBounds(200, 500, 150, 30);
		money.setBounds(200, 550, 150, 30);
		textLabel.setBounds(80, 190, 150, 550);
		addAccount.setBounds(60, 600, 120, 30);
		type.setBounds(200, 600, 150, 30);
		accountId.setBounds(565, 600, 55, 25);
		removeLabel.setBounds(520, 515, 250, 130);
		background.setBounds(0, 0, 820, 700);
		
		panel.add(textAreaScroll);
		panel.add(tableScroll);
		panel.add(withdraw);
		panel.add(deposit);
		panel.add(changeLimit);
		panel.add(withdrawSum);
		panel.add(depositSum);
		panel.add(newLimit);
		panel.add(sum);
		panel.add(firstName);
		panel.add(lastName);
		panel.add(address);
		panel.add(CNP);
		panel.add(money);
		panel.add(textLabel);
		panel.add(addAccount);
		panel.add(removeAccount);
		panel.add(accountId);
		panel.add(removeLabel);
		panel.add(type);
		type.addItem("Saving Account");
		type.addItem("Spending Account");
		panel.add(background);

		bank = new Bank();
		bank.readAccounts();
		populateTable();

		addAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addAccount();
			}
		});

		removeAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				removeAccount();
			}
		});
		
		withdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				withdrawMoney();
			}
		});

		deposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				depositMoney();
			}
		});

		changeLimit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				changeLimit();
			}
		});

	}

	public void populateTable() {

		String[] colName = { "AccountID", "Type", "Owner", "Details", " Address", "CNP" };
		DefaultTableModel model = new DefaultTableModel(colName, 0);
		table.setModel(model);
		for (Account account : bank.getAccounts().values()) {
			Object[] row = { account.getAccountId(), account.getType(),
					account.getOwner().getFirstName() + " " + account.getOwner().getLastName(), account,
					account.getOwner().getAddress(), account.getOwner().getCNP()};
			
			model = (DefaultTableModel) table.getModel();
			model.addRow(row);

			table.getColumn("Details").setPreferredWidth(100);
			table.getColumn("Details").setCellEditor(
					new DefaultCellEditor(new JCheckBox()) {
						private static final long serialVersionUID = 1L;

						public Component getTableCellEditorComponent(JTable table, Object value, 
								boolean isSelected, int row, int column) {
									textArea.setText("\n                          Account Details\n\n"
											+ ((Account) value).toStringTextArea());
									return null;
						}
					});
		}
	}

	public Void notify(String s){
		JOptionPane.showMessageDialog(frame, s);
		return null;
	}
	
	public void addAccount() {
		Integer startSum = 0;
		try {
			startSum = Integer.parseInt(money.getText());
		
		Account account = null;
		Person owner = new Person(firstName.getText(), lastName.getText(), address.getText(), CNP.getText(), (Function<String, Void>)this::notify);
		if (owner.isWellFormed()) {
		owner.setNotify((Function<String, Void>)this::notify);
		notify(owner.getFirstName()+ " " + owner.getLastName() + " was notified that his account was added");
		if (type.getSelectedItem().equals("Saving Account"))
			account = new SavingAccount(owner);
		else
			account = new SpendingAccount(owner);
		account.addMoney(startSum);
		bank.addAccount(account);
		bank.writeAccounts();
		clearFields();
		populateTable();
		}
		else
			if(owner.getFirstName() == null || owner.getLastName() == null
			|| owner.getAddress() == null || owner.getCNP() == null)
			notify("All fields have to be filled!");
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Insert a number for starting sum!");
		}
	}

	public void removeAccount() {
		Integer accountID = 0;
		try {
			accountID = Integer.parseInt(accountId.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Insert a valid AccountID!");
		}
		Account account = bank.getAccount(accountID);
		bank.removeAccount(account);
		bank.writeAccounts();
		clearFields();
		populateTable();
	}

	
	public void withdrawMoney() {
		Integer withdrawMoney = 0;
		Integer accountId = 0;
		try {
			withdrawMoney = Integer.parseInt(withdrawSum.getText());
			try { 
				accountId = (int) table.getValueAt(table.getSelectedRow(), 0);
				if(bank.getAccount(accountId).getMoney() == (int)withdrawMoney)
					bank.getAccount(accountId).removeMoney(withdrawMoney);
				else {
				if(bank.getAccount(accountId).getMoney() != withdrawMoney && bank.getAccount(accountId).getType() == "Spending account")
					notify("Cannot withdraw money");
				else {
				bank.getAccount(accountId).removeMoney(withdrawMoney);
				textArea.setText("\n                          Account Details\n\n"
						+ bank.getAccount(accountId).toStringTextArea());
				bank.writeAccounts();
				clearFields();
				populateTable();
				notify("I was notified that " + withdrawMoney.toString() + " money was withdrawn from my account");
				}

			}
			}
			catch (ArrayIndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Please select a row from the table");
			}
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			clearFields();
			JOptionPane.showMessageDialog(null, "Insert a valid number for withdrawal sum");
		}
	}

	public void depositMoney() {
		Integer depositMoney = 0;
		Integer accountId = 0;
		try {
			depositMoney = Integer.parseInt(depositSum.getText());
			try {
				accountId = (int) table.getValueAt(table.getSelectedRow(), 0);
				bank.getAccount(accountId).addMoney(depositMoney);
				textArea.setText("\n                          Account Details\n\n"
						+ bank.getAccount(accountId).toStringTextArea());
				bank.writeAccounts();
				clearFields();
				populateTable();
				notify("I was notified that " + depositMoney.toString() +  " money was added to my account");
			} catch (ArrayIndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Please select a row from the table");
			}
		} catch (NumberFormatException e) {
			clearFields();
			JOptionPane.showMessageDialog(null, "Insert a valid number for deposit sum");
		}
	}

	public void changeLimit() {
		Integer limit = 0;
		Integer accountId = 0;
		try {
			limit = Integer.parseInt(newLimit.getText());
			try {
				accountId = (int) table.getValueAt(table.getSelectedRow(), 0);
				String type = (String) table.getValueAt(table.getSelectedRow(), 1);
				if (type.equals("Spending account")) {
					((SpendingAccount) bank.getAccount(accountId)).setLimit(limit);
					textArea.setText("\n                          Account Details\n\n"
							+ bank.getAccount(accountId).toStringTextArea());
					bank.writeAccounts();
					clearFields();
					populateTable();
				} else
					JOptionPane.showMessageDialog(null, "Please select a Spending Account to change the limit");
			} catch (ArrayIndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Please select a row from the table");
			}
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			clearFields();
			JOptionPane.showMessageDialog(null, "Insert a valid number for deposit sum or select an account");
		}
	}

	public void clearFields() {
		firstName.setText(""); 
		lastName.setText(""); 
		CNP.setText(""); 
		address.setText(""); 
		money.setText(""); 
		withdrawSum.setText("");
		depositSum.setText(""); 
		newLimit.setText(""); 
		accountId.setText("");;
	}
	
}