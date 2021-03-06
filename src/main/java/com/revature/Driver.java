package com.revature;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.AccountServices;
import com.revature.services.UserService;
import java.util.List;
import java.util.Scanner;


public class Driver {
	
	private static UserService us = new UserService();
	private static AccountServices as = new AccountServices();			
	private static Scanner scan = new Scanner(System.in);
		


		public static void main(String[] args) {
			start();
		}
//options to be checked
				private static void giveOptions() {
					System.out.println();
					System.out.println("Welcome to ABC Bank!");
					System.out.println("____________________________");
					System.out.println();
					System.out.println("Please choose from the following options:");
					System.out.println();
					System.out.println("1. Enter [1] if you are a new user");
					System.out.println("2. Enter [2] if you are an existing user");
					System.out.println("3. Enter [3] Exit");
					System.out.println();		
	}
				
		
				
		private static void start() {
			
			boolean check = true;
			User u = null;
			
			while(check) {
				giveOptions();
				
				int input = ensureIntegerInput();
				
				switch(input) {
				case 1:
					u = createUser();
					us.addUser(u);
					break;
					
				case 2:
					User u1 = getUserFromDB();
					if(checkUserInDB(u1)) {
						
						logIn(u1);
					}
					check = false;
					break;
					
				case 3:
					System.out.println("Have a nice day!");
					System.exit(0);
				default:
					System.out.println("Not a valid input. Please try again");
					break;
					
				}		
			}	
		}

//		create user
		private static User createUser() {
			System.out.println("Enter your first name: ");

			String fname = scan.nextLine();
	
			System.out.println("Enter your last name: ");
			String lname = scan.nextLine();
			
			String password = conformPassword();
			System.out.println("Enter \"y\" if you are employee of the bank: ");
			String employee1 = scan.nextLine();
			
			boolean employee = false;
			if(employee1.equalsIgnoreCase("y")) {
				employee = true;
			}
			
			System.out.println("Enter \"y\" if you are admin of the bank: ");
			String admin1 = scan.nextLine();
		
			boolean admin = false;
			if(admin1.equalsIgnoreCase("y")) {
				admin = true;
			}
			
			System.out.println("Create an Account:");
			Account account = createAccount();
			int pin = account.getPinNumber();
			as.addAccount(account);
			Account acc = as.getAccountBYPinNumber(pin);
			return new User(0, fname, lname,  password, acc.getId(), employee, admin, false);
		}

//		method to get users from DB
		
		private static  User getUserFromDB() {
			String fname;
			String password;
			User u;
			
			do {
				System.out.println("Enter your first name: ");
				 fname = scan.nextLine();
				System.out.println("Enter your password: ");
				 password = scan.nextLine();
				System.out.println("Checking database");
				u = us.getUserByFnameAndPassword(fname, password);
				
			} while(u == null);
			return u;	
		}

		
		private static boolean checkUserInDB(User u) {	
			boolean flag = false;
				if(u != null) {		
					flag = true;	
				} else {
					System.out.println("User not found:"); 
					flag = false;
				}
			return flag;
		}

//
		private static void logIn(User user) {
			System.out.println();
			System.out.println("Welcome, " + user.getFname() + "!");
			System.out.println("Please, choose from the following options: ");
			
			while(true) {
				
				printTransactionOption();
				
				int choice = ensureIntegerInput();
				
				switch(choice) {
				case 0:
					System.out.println("Have a nice day!");
					System.exit(0);
					break;
				case 1:
					deposit(user);
					break;
				case 2:
					withdraw(user);
					break;
				case 3: 
					transfer(user);
					break;
				case 4:
					seeDetailsAboutUsers(user);
				default:
					System.out.println("Invalid input. Please try again.");
					break;
				}
			}
		}

//		Admin/Employee access
		private static void seeDetailsAboutUsers(User u) {
			System.out.println("You must be Admin/Employee to access Users' Accounts.");
			System.out.println();
			System.out.println("Enter \"employee\" for employee or \"admin\" for administrator.");
			
			String status = scan.nextLine();
			
			if(status.equalsIgnoreCase("admin") && u.isAdmin()) {
				
				List<User> userList = us.getAllUsers();
				
				System.out.println("List of Users:");
				System.out.println("_____________________________________________");
				for(User u4 : userList) {
					System.out.println(u4);
				}
				System.out.println("____________________________________________");
				System.out.println();
				System.out.println();
				System.out.println("List of Accounts:");
				System.out.println("____________________________________________");
				
				List<Account> accountList = as.getAllAccounts();
				for(Account a : accountList) {
					System.out.println(a);
				}
				System.out.println("____________________________________________");
				giveOptionsToModifyAccounts(u);
				
			}else if(status.equalsIgnoreCase("employee") && u.isEmployee()) {
				System.out.println("List of Accounts");
				System.out.println("_____________________________________________");
				
				List<Account> accountList = as.getAllAccounts();
				for(Account a : accountList) {
					System.out.println(a);
				}
				
				System.out.println("____________________________________________");
				giveOptionsToModifyAccounts(u);
			} else {
				System.out.println("Have a nice day!");
			}	
		}


//options to modify account
		
		private static void giveOptionsToModifyAccounts(User u) {
			System.out.println("Enter Account Id.");
			
			int choice = ensureIntegerInput();
			Account a = as.getAccountById(choice);
			System.out.println(a);
			boolean flag = true;
			while(flag) {
				
				System.out.println("Choose from the following options:");
				System.out.println();
				System.out.println("Enter [0] to go back.");
				System.out.println("Enter [1] to change Account Number.");
				System.out.println("Enter [2] to change Account Balance.");
				System.out.println("Enter [3] to change Account Pin Number.");
				
				int choice1 = ensureIntegerInput();
				switch(choice1) {
				case 0:
					System.out.println("Thank you for choosing ABC Bank!");
					flag = false;
					break;

				case 1:
					System.out.println("Please Enter the Account number for account");
					int accountNumber = ensureIntegerInput();
					a.setAccountNumber(accountNumber);
					as.updateAccount(a);
					System.out.println("Account Number is changed.");
					break;
					
				case 2:
					System.out.println("Please Enter the Balance for account.");
					double balance = ensureDoubleInput();
					a.setBalance(balance);
					as.updateAccount(a);
					System.out.println("Account Balance is changed.");
					break;

				case 3:
					System.out.println("Please Enter the Pin Number for account");
					int pinNumber = ensureIntegerInput();
					a.setPinNumber(pinNumber);
					as.updateAccount(a);
					System.out.println("Account Pin Number is changed.");
					break;

				default:
					System.out.println("Invalid input. Please try again.");
					break;
				}
			}
			
		}

//modify users 
		
		private static void giveOptionsToModifyUsers(User u) {
			System.out.println("Enter user id to modify the user information.");
			int choice = ensureIntegerInput();
			User user1 = us.getUserById(choice);
			System.out.println(user1);
			boolean flag = true;
			while(flag) {
				
				System.out.println("What do you want to change?");
				System.out.println();
				System.out.println("Enter 0 to go back");
				System.out.println("Enter 1 to change user First Name.");
				System.out.println("Enter 2 to change user Last Name.");
				System.out.println("Enter 3 to change user Password.");
				System.out.println("Enter 4 to change user Employee Status.");
				
				int choice1 = ensureIntegerInput();
				switch(choice1) {
				case 0:
					System.out.println("Thank you for checking out.");
					flag = false;
					break;

				case 1:
					System.out.println("Please Enter the user First Name");
					String fname = scan.nextLine();
					user1.setFname(fname);
					us.updateUser(user1);
					System.out.println("User's First Name is changed.");
					break;
				case 2:
					System.out.println("Please Enter the user Last Name");
					String lname = scan.nextLine();
					user1.setLname(lname);
					us.updateUser(user1);
					System.out.println("User's Last Name is changed.");
					break;
				case 3:
					System.out.println("Please Enter the user Password");
					String pass = scan.nextLine();
					user1.setPassword(pass);
					us.updateUser(user1);
					System.out.println("User's Password is changed.");
					break;
				case 5:
					System.out.println("Please Enter the ID for Account.");
					int id1 = ensureIntegerInput();
					u.setAccountId(id1);
					us.updateUser(u);
					System.out.println("User's Account ID is changed.");
					break;
				case 4:
					System.out.println("Please Enter the user's Employee Statue T for \"True\" or F for \"False\"");
					boolean status = false;
					String estatus = scan.nextLine();
					if(estatus.equalsIgnoreCase("T")) {
						status = true;
					}
					user1.setEmployee(status);
					us.updateUser(user1);
					System.out.println("User's Employee Status is changed.");
					break;	
				default:
					System.out.println("There no option for your input, Please try again.");
					break;
				}
			}
			
		}
	
//
		private static int ensureIntegerInput() {
			int choice = 0;
			String ch = scan.nextLine();
			try {
				
				choice = Integer.parseInt(ch.split(" ")[0]);
			}catch(NumberFormatException e) {
				System.out.println("Invalid input");
				e.printStackTrace();
				return -1;
			}
			return choice;
		}
//

		private static void deposit(User u) { 
			System.out.println("Enter the amount you would like to deposit:");
			double amount = ensureDoubleInput();
			Account a = us.getUserAccount(u);
			as.updateBalanceOfAccount(a, amount);
			System.out.println("$" + amount + " Deposited successfully!: ");
		}

		
		private static void withdraw(User u) { //withdraw
			System.out.println("Please enter the amount you would like to withdraw :");
			double amount = ensureDoubleInput();
			Account a = us.getUserAccount(u);
			
			if(a.getBalance() < amount) {
				System.out.println("Insufficient funds to perform this action. :( Please choose from the following options:");
			}else if(a.getBalance() >= amount){
				System.out.println(amount + " withdrawn successfully!");
			}else {
				as.updateBalanceOfAccount(a, (-1*amount));
			}

//			System.out.println(amount + "withdrawn successfully! ");
		}


		private static void transfer(User u) {  //transfer
			
			System.out.println("Transfer amount:");
			double amount = ensureDoubleInput();
			
			System.out.println("Enter the account id of the account to which you want to transfer:");
			int pinNumber = ensureIntegerInput();
			
			Account anotherAccount = as.getAccountById(pinNumber);
			Account userAccount = us.getUserAccount(u);
			
			if(userAccount.getBalance() < amount) {
				System.out.println("Insufficient funds. Transaction failed.");
			} else if(userAccount.getBalance() >= amount) {
				System.out.println("Transferred successfully $" + amount);
			}else {
				as.updateBalanceOfAccount(userAccount, (-1*amount));
			}
//			System.out.println("Transfered successfully " + amount);
		}



//
		private static double ensureDoubleInput() {
			String amt = scan.nextLine();
			double amount = 0.0;
			try {
				amount = Double.parseDouble(amt);
			}catch(NumberFormatException e) {
				System.out.println("Action unsuccessful.");
				e.printStackTrace();
			}
			return amount;
		}

//

		private static void printTransactionOption() {
			System.out.println();
			System.out.println("Enter 0 to exit");
			System.out.println("Enter 1 to deposit.");
			System.out.println("Enter 2 to widthdraw.");
			System.out.println("Enter 3 to transfer.");
			System.out.println("Enter 4 to see transactions.");
			System.out.println();
		}

	

		
// 
		 private static Account createAccount() {
			System.out.println();
			
			System.out.println("Enter a Pin Number");
			
			int pin = ensureIntegerInput();
			return new Account(0,0,0,pin);

		 }	


		private static String conformPassword() {
			String password;
			
			do {
				System.out.println("Please Enter Password");
				 password = scan.nextLine();
				
				if(us.getAllUsersPassword().contains(password)) {
					System.out.println("Password is taken. Please try again.");
				}
				
			} while(us.getAllUsersPassword().contains(password));
			
			return password;
		}
		 		

}