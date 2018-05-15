package bank;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import java.util.function.Function;

import javax.swing.JOptionPane;

public class Person implements Serializable, Observer {

	private static final long serialVersionUID = 1L;
	private transient Function<String, Void> notify;
	private String firstName;
	private String lastName;
	private String address;
	private String CNP;

	public Person(String firstName, String lastName, String address, String CNP, Function<String, Void> notify) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.CNP = CNP;
		this.notify = notify;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getCNP() {
		return CNP;
	}

	public String getAddress() {
		return address;
	}

	public boolean isWellFormed() {
		if (firstName == null || lastName == null || address == null || CNP == null) { 
			JOptionPane.showMessageDialog(null, "Please insert values in every field");
			return false;
		}
		if (firstName.length() < 3){
			JOptionPane.showMessageDialog(null, "Firstname should have at least 3 characters");
			return false;
		}
		
		if (lastName.length() < 3){
			JOptionPane.showMessageDialog(null, "Lastname should have at least 3 characters");
			return false;
		}
			
		if (address.length() < 5){
			JOptionPane.showMessageDialog(null, "Address should have at least 5 characters");
			return false;
		}

		try {
			if (Long.parseLong(CNP) < 0 | CNP.length() != 13){
				JOptionPane.showMessageDialog(null, "CNP is not valid. It should have 13 numerical characters");
				return false;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "CNP is not valid. It should have 13 numerical characters");
			return false;
		}
		return true;
	}

	public String toString() {
		try {
			assert isWellFormed();
			return "Name: " + firstName + " " + lastName + " \n  Address: " + address
					+ " \n  CNP: " + CNP;
		} catch (AssertionError e) {
			System.out.println("Pre or post conditions fails at 'toString' in class 'Person'");
			return null;
		}
	}

	@Override
	public void update(Observable obs, Object obj) {
		notify.apply("Owner " + this.firstName + " " + this.lastName + "got notified by " + obs.toString() + " " + (((int)obj < 0 ? "withdraw" : "add money") + Math.abs((int)(obj))));
		
	}
	
	public void setNotify(Function<String, Void> notify) {
		this.notify = notify;
	}

}
