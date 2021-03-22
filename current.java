
import java.util.*;
import java.text.*;
import java.io.*;
import java.lang.*;

///Users/kushalthakar/Desktop/JAVA/demo.txt
interface SavingsAccount
{
	final double rate = 0.004,limit = 10000,limit1 = 200;
	void deposit(double n,Date d);
	void withdraw(double n,Date d);
}

class Customer implements SavingsAccount
{
	String username,password,name,address,phone;
	double balance;
	ArrayList<String> transactions;
	Customer(String username,String password,String name,String address,String phone,double balance,Date date)
	{
		this.username = username;
		this.password = password;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.balance = balance;
		transactions  =  new ArrayList<String>(5);
		addTransaction(String.format("Initial deposit - " +NumberFormat.getCurrencyInstance().format(balance)+" as on " + "%1$tD"+" at "+"%1$tT.",date));
	}
	void update(Date date)
	{
		if(balance>= 10000)
		{
			balance += rate*balance;
		}
		else
		{
			balance -= (int)(balance/100.0);
		}
		addTransaction(String.format("Account updated. Balance - " +NumberFormat.getCurrencyInstance().format(balance)+" as on " + "%1$tD"+" at "+"%1$tT.",date));
	}
	@Override
	public void deposit(double amount,Date date)
	{
		balance += amount;
		addTransaction(String.format(NumberFormat.getCurrencyInstance().format(amount)+" credited to your account. Balance - " +NumberFormat.getCurrencyInstance().format(balance)+" as on " + "%1$tD"+" at "+"%1$tT.",date));
	}
	@Override
	public void withdraw(double amount,Date date)
	{
		if(amount>(balance-200))
		{
			System.out.println("Insufficient balance.");
			return;
		}
		balance -= amount;
		addTransaction(String.format(NumberFormat.getCurrencyInstance().format(amount)+" debited from your account. Balance - " +NumberFormat.getCurrencyInstance().format(balance)+" as on " + "%1$tD"+" at "+"%1$tT.",date));
	}
	public void addTransaction(String message)
	{
			transactions.add(0,message);
			if(transactions.size()>5)
			{
				transactions.remove(5);
				transactions.trimToSize();
			}
	}
}
class current
{
    public static void democurrent() throws IOException
    {
        Scanner sc  =  new Scanner(System.in);
		FileWriter writer = new FileWriter("/Users/kushalthakar/Desktop/JAVA/current.txt", true);    
    	BufferedWriter br = new BufferedWriter(writer);
		
		Customer customer;
		String username,password;
		double amount;
		current bank  =  new current();
		int choice;
	outer:	while(true)
		{
			try
			{

			
				System.out.println("\n-------------------");
				System.out.println("BANK    OF     JAVA");
				System.out.println("-------------------\n");
				System.out.println("1. Register account.");
				System.out.println("2. Login.");
				System.out.println("3. Update accounts.");
				System.out.println("4. Exit.");
				System.out.print("\nEnter your choice : ");
				choice = sc.nextInt();
				sc.nextLine();
				switch(choice)
				{
					case 1:
						System.out.print("Enter name : ");
						String name = sc.nextLine();
						br.write("Name: " + name + "\n");
						System.out.print("Enter address : ");
						String address = sc.nextLine();
						br.write("Address: " + address + "\n");
						System.out.print("Enter contact number : ");
						String phone = sc.nextLine();
						br.write("Contact: " + phone + "\n");
						System.out.println("Set username : ");
						username = sc.next();
						br.write("Username: " + username + "\n");
						while(bank.customerMap.containsKey(username))
						{
							System.out.println("Username already exists. Set again : ");
							username = sc.next();
							//br.write("Username: " + username + "\n");
						}
						System.out.println("Set a password (minimum 8 chars; minimum 1 digit, 1 lowercase, 1 uppercase, 1 special character[!@#$%^&*_]) :");
						password = sc.next();
						br.write("Password: " + password + "\n");
						
						while(!password.matches((("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_]).{8,}"))))
						{
							System.out.println("Invalid password condition. Set again :");
							password=sc.next();
							//br.write("Password: " + password + "\n");
						}
						System.out.print("Enter initial deposit : ");
						sc.nextLine();
						while(!sc.hasNextDouble())
						{
							System.out.println("Invalid amount. Enter again :");
							sc.nextLine();
							
						}
						amount=sc.nextDouble();
						br.write("Balance: " + String.valueOf(amount) + "\n");
						br.write("-----------------------------------------\n");
                        //br.flush();
						sc.nextLine();
						
						customer = new Customer(username,password,name,address,phone,amount,new Date());
						bank.customerMap.put(username,customer);
						
						
						break;
					case 2:
						System.out.println("Enter username : ");
						username = sc.next();
						sc.nextLine();
						System.out.println("Enter password : ");
						password = sc.next();
						sc.nextLine();
						if(bank.customerMap.containsKey(username))
						{
							customer = bank.customerMap.get(username);
							if(customer.password.equals(password))
							{
								while(true)
								{
									System.out.println("\n-------------------");
									System.out.println("W  E  L  C  O  M  E");
									System.out.println("-------------------\n");
									System.out.println("1. Deposit.");
									System.out.println("2. Transfer.");
									System.out.println("3. Last 5 transactions.");
									System.out.println("4. User information.");
									System.out.println("5. Log out.");
									System.out.print("\nEnter your choice : ");
									choice = sc.nextInt();
									sc.nextLine();
									switch(choice)
									{
										case 1:
											System.out.print("Enter amount : ");
											while(!sc.hasNextDouble())
											{
												System.out.println("Invalid amount. Enter again :");
												sc.nextLine();
											}
											amount = sc.nextDouble();
											sc.nextLine();
																			customer.deposit(amount,new Date());
											break;
										case 2:
											System.out.print("Enter payee username : ");
											username = sc.next();
											sc.nextLine();
											System.out.println("Enter amount : ");
											while(!sc.hasNextDouble())
											{
												System.out.println("Invalid amount. Enter again :");
												sc.nextLine();
											}
											amount = sc.nextDouble();
											sc.nextLine();
											if(amount > 300000)
											{
												System.out.println("Transfer limit exceeded. Contact bank manager.");
												break;
											}
											if(bank.customerMap.containsKey(username))
											{
												Customer payee = bank.customerMap.get(username);
												payee.deposit(amount,new Date());
												customer.withdraw(amount,new Date());
											}
											else
											{
												System.out.println("Username doesn't exist.");
											}
											break;
										case 3:
											for(String transactions : customer.transactions)
											{
												System.out.println(transactions);
											}
											break;
										case 4:
											System.out.println("Accountholder name : "+customer.name);
											System.out.println("Accountholder address : "+customer.address);
											System.out.println("Accountholder contact : "+customer.phone);
											System.out.println("Accountholder Balance : "+customer.balance);
											break;
										case 5:
											continue outer;
											default:
												System.out.println("Wrong choice !");
									}
								}
							}
							else
							{
								System.out.println("Wrong username/password.");
							}
						}
						else
						{
							System.out.println("Wrong username/password.");
						}
						break;
					case 3:
						System.out.println("Enter username : ");
						username = sc.next();
                        /*BufferedReader br = new BufferedReader(new FileReader("/Users/kushalthakar/Desktop/JAVA/current.txt"));
                        String s = br.readLine();
                        while (s != null)
                        {
                            if(s == username)
                            {
                                System.out.println("OK DONE");
                            }
                        }*/
						if(bank.customerMap.containsKey(username))
						{
							customer = bank.customerMap.get(username);
                            br.write("--------------Updated Username:" + username + "-------------\n");
                            System.out.print("Update Name : ");
						    customer.name = sc.next();
                            br.write("Name: " + customer.name + "\n");
                            System.out.print("Update address : ");
                            customer.address = sc.next();
                            br.write("Address: " + customer.address + "\n");
                            System.out.print("Update Contact Number : ");
						    customer.phone = sc.next();
                            br.write("Contact: " + customer.phone + "\n");
                            
                            
						}
						else
						{
							System.out.println("Username doesn't exist.");
						}
						break;
					case 4:
                        br.close();
                        /*
                        System.out.println("Enter username : ");
                        username = sc.next();
                        if(bank.customerMap.containsKey(username))
                        {
                            customer = bank.customerMap.get(username);
                            System.out.println("\nThank you for choosing Bank Of Java."); 
                            //br.write("Name: " + customer.name + "\n");
                            //br.write("Address: " + customer.address + "\n");
                            //br.write("Contact: " + customer.phone + "\n");
                            //br.write("Username: " + customer.username + "\n");
                            //br.write("Password: " + customer.password + "\n");
                            //br.write("Balance: " + String.valueOf(customer.amount) + "\n");
                           // br.write("-----------------------------------------\n");
                            //br.close();

                        }*/
                        
						System.exit(1);
						break;
					default:
						System.out.println("Wrong choice !");
				}
				
			}
			catch (IOException e)
			{
				System.out.println(e);
			}
		
		}
	}
	Map<String,Customer> customerMap;
	current()
	{
		customerMap = new HashMap <String,Customer>();
	}
	public static void main(String []args) throws IOException
	{
       
       democurrent();
		
    }
}