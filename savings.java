//package SavingsPackage;
//import CurrentPackage.*;

import java.util.*;
import java.text.*;
import java.io.*;
import java.lang.*;

///Users/kushalthakar/Desktop/JAVA/demo.txt
interface SavingsAccount
{
    final double rate = 0.04,limit = 10000,limit1 = 200;
    void deposit(double n,Date d);
    void withdraw(double n,Date d);
    void debitCard(double n,Date d);
    void creditCard(double n, Date d);
}

class Customer implements SavingsAccount
{
    String username,acc_number,password,name,address,phone;
    double balance;
    double credit_balance = 50000;
    ArrayList<String> transactions;
    ArrayList<String> clients;
    Customer(String username, String acc_number, String password,String name,String address,String phone,double balance,Date date)
    {
        this.username = username;
        this.password = password;
        this.acc_number = acc_number;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.balance = balance;
        clients = new ArrayList<String>(5);
        transactions  =  new ArrayList<String>(5);
        //addClients(String.format("Username: " + username + "\n" + "Account Number: " + acc_number + "\n" + "Name: " + name + "\n" + "Address: " + address + "\n" + "Phone: " + phone + "\n" + " as on " + "%1$tD"+" at "+"%1$tT.",date));
        addTransaction(String.format("Initial deposit - " +NumberFormat.getCurrencyInstance().format(balance)+" as on " + "%1$tD"+" at "+"%1$tT.",date));
    }

    public void add(Date date)
    {
        addClients(String.format("Username: " + username + "\n" + "Account Number: " + acc_number + "\n" + "Name: " + name + "\n" + "Address: " + address + "\n" + "Phone: " + phone + "\n" + "as on " + "%1$tD"+" at "+"%1$tT." + "\n-----------------",date));
    }
    public void addClients(String message)
    {
        clients.add(0,message);
        if(clients.size()>5)
        {
            clients.remove(5);
            clients.trimToSize();
        }

    }


    public void update(Date date)
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

    public void debitCard(double amount,Date date)
    {
        if(amount>(balance-200))
        {
            System.out.println("Insufficient balance.");
            return;
        }
        balance -= amount;
        addTransaction(String.format(NumberFormat.getCurrencyInstance().format(amount)+" debited from your Debit Card so now account. Balance - " +NumberFormat.getCurrencyInstance().format(balance)+" as on " + "%1$tD"+" at "+"%1$tT.",date));
    }

    public void creditCard(double amount,Date date)
    {

        if(amount>(credit_balance-200))
        {
            System.out.println("Insufficient balance.");
            return;
        }
        credit_balance -= amount;
        addTransaction(String.format(NumberFormat.getCurrencyInstance().format(amount)+" debited from your Credit card. Balance - " +NumberFormat.getCurrencyInstance().format(credit_balance)+" as on " + "%1$tD"+" at "+"%1$tT.",date));
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
public class savings
{
    public static void demosavings() throws IOException
    {
        Scanner sc  =  new Scanner(System.in);
        FileWriter writer = new FileWriter("/Users/kushalthakar/Desktop/FinalProject_BankingSystem/TextFiles/savings.txt", true);
        BufferedWriter br = new BufferedWriter(writer);

        Customer customer;
        String username,password;
        double amount;
        savings bank  =  new savings();
        int choice;
        outer:	while(true)
        {
            try
            {


                System.out.println("\n-------------------");
                System.out.println("        BANK");
                System.out.println("-------------------\n");
                System.out.println("1. Register account.");
                System.out.println("2. Login.");
                System.out.println("3. Update accounts.");
                System.out.println("4. Update accounts Info.");
                System.out.println("5. Exit.");
                System.out.print("\nEnter your choice : ");
                choice = sc.nextInt();
                sc.nextLine();
                //String str = String.format("%1.s", acc_number);
                /*
						account_number = Math.random();
						System.out.println(account_number);
						*/
                //br.flush();
                /*BufferedReader br = new BufferedReader(new FileReader("/Users/kushalthakar/Desktop/JAVA/savings.txt"));
                        String s = br.readLine();
                        while (s != null)
                        {
                            if(s == username)
                            {
                                System.out.println("OK DONE");
                            }
                        }*/
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
                switch (choice) {
                    case 1: {
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
                        while (bank.customerMap.containsKey(username)) {
                            System.out.println("Username already exists. Set again : ");
                            username = sc.next();
                            //br.write("Username: " + username + "\n");
                        }
                        System.out.println("Set a password (minimum 8 chars; minimum 1 digit, 1 lowercase, 1 uppercase, 1 special character[!@#$%^&*_]) :");
                        password = sc.next();
                        br.write("Password: " + password + "\n");
                        while (!password.matches((("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_]).{8,}")))) {
                            System.out.println("Invalid password condition. Set again :");
                            password = sc.next();
                            //br.write("Password: " + password + "\n");
                        }
                        String acc_number = String.valueOf(Math.random());
                        acc_number = acc_number.substring(2);
                        System.out.println("Account Number: " + acc_number);
                        br.write("Account Number: " + acc_number + "\n");
                        System.out.print("Enter initial deposit : ");
                        sc.nextLine();
                        while (!sc.hasNextDouble()) {
                            System.out.println("Invalid amount. Enter again :");
                            sc.nextLine();

                        }
                        amount = sc.nextDouble();
                        br.write("Balance: " + String.valueOf(amount) + "\n");
                        br.write("-----------------------------------------\n");
                        sc.nextLine();
                        customer = new Customer(username, acc_number, password, name, address, phone, amount, new Date());
                        bank.customerMap.put(username, customer);
                        customer.add(new Date());
                        continue outer;
                    }
                    case 2: {
                        System.out.println("Enter username : ");
                        username = sc.next();
                        sc.nextLine();
                        System.out.println("Enter password : ");
                        password = sc.next();
                        sc.nextLine();
                        if (bank.customerMap.containsKey(username)) {
                            customer = bank.customerMap.get(username);
                            if (customer.password.equals(password)) {
                                while (true) {
                                    System.out.println("\n-------------------");
                                    System.out.println("W  E  L  C  O  M  E");
                                    System.out.println("-------------------\n");
                                    System.out.println("1. Deposit.");
                                    System.out.println("2. Withdraw.");
                                    System.out.println("3. Transfer.");
                                    System.out.println("4. Bill.");
                                    System.out.println("5. Last 5 transactions.");
                                    System.out.println("6. User information.");
                                    System.out.println("7. Log out.");
                                    System.out.print("\nEnter your choice : ");
                                    choice = sc.nextInt();
                                    sc.nextLine();
                                    switch (choice) {
                                        case 1:
                                            System.out.print("Enter amount : ");
                                            while (!sc.hasNextDouble()) {
                                                System.out.println("Invalid amount. Enter again :");
                                                sc.nextLine();
                                            }
                                            amount = sc.nextDouble();
                                            sc.nextLine();
                                            customer.deposit(amount, new Date());
                                            break;
                                        case 2:
                                            System.out.print("Enter amount : ");
                                            while (!sc.hasNextDouble()) {
                                                System.out.println("Invalid amount. Enter again :");
                                                sc.nextLine();
                                            }
                                            amount = sc.nextDouble();
                                            sc.nextLine();
                                            customer.withdraw(amount, new Date());
                                            break;

                                        case 3:
                                            System.out.print("Enter payee username : ");
                                            username = sc.next();
                                            sc.nextLine();
                                            System.out.println("Enter amount : ");
                                            while (!sc.hasNextDouble()) {
                                                System.out.println("Invalid amount. Enter again :");
                                                sc.nextLine();
                                            }
                                            amount = sc.nextDouble();
                                            sc.nextLine();
                                            if (amount > 300000) {
                                                System.out.println("Transfer limit exceeded. Contact bank manager.");
                                                break;
                                            }
                                            if (bank.customerMap.containsKey(username)) {
                                                Customer payee = bank.customerMap.get(username);
                                                payee.deposit(amount, new Date());
                                                customer.withdraw(amount, new Date());
                                            } else {
                                                System.out.println("Username doesn't exist.");
                                            }
                                            break;
                                        case 4: {
                                            System.out.println("1.Debit card");
                                            System.out.println("2.Credit card");
                                            int c = sc.nextInt();
                                            switch (c) {
                                                case 1:
                                                    System.out.print("Enter amount : ");
                                                    while (!sc.hasNextDouble()) {
                                                        System.out.println("Invalid amount. Enter again :");
                                                        sc.nextLine();
                                                    }
                                                    amount = sc.nextDouble();
                                                    sc.nextLine();
                                                    customer.debitCard(amount, new Date());

                                                    break;
                                                case 2:
                                                    System.out.print("Enter amount : ");
                                                    while (!sc.hasNextDouble()) {
                                                        System.out.println("Invalid amount. Enter again :");
                                                        sc.nextLine();
                                                    }
                                                    amount = sc.nextDouble();
                                                    sc.nextLine();
                                                    customer.creditCard(amount, new Date());
                                                    break;

                                                default:
                                                    break;
                                            }
                                        }

                                        case 5:
                                            for (String transactions : customer.transactions) {
                                                System.out.println(transactions);
                                            }
                                            break;
                                        case 6:

                                            for (String clients : customer.clients) {
                                                System.out.println(clients);
                                            }
                                        /*
                                            System.out.println("Accountholder Name : " + customer.name);
                                            System.out.println("Accountholder Account Number : " + customer.acc_number);
                                            System.out.println("Accountholder Address : " + customer.address);
                                            System.out.println("Accountholder Contact : " + customer.phone);
                                            System.out.println("Accountholder Balance : " + customer.balance);
                                        */
                                            break;
                                        case 7:
                                            continue outer;
                                        default:
                                            System.out.println("Wrong choice !");
                                    }
                                }
                            } else {
                                System.out.println("Wrong username/password.");
                                continue outer;
                            }
                        } else {
                            System.out.println("Wrong username/password.");
                            continue outer;
                        }
                    }

                    case 3: {
                        System.out.println("Enter username : ");
                        username = sc.next();
                        if (bank.customerMap.containsKey(username)) {
                            bank.customerMap.get(username).update(new Date());
                        } else {
                            System.out.println("Username doesn't exist.");
                        }
                        break;
                    }

                    case 4 : {

                        System.out.println("Enter username : ");
                        username = sc.next();
                        sc.nextLine();
                        System.out.println("Enter password : ");
                        password = sc.next();
                        sc.nextLine();
                        if (bank.customerMap.containsKey(username)) {
                            customer = bank.customerMap.get(username);
                            if (customer.password.equals(password)) {

                                br.write("----- Updated " + customer.username + "------");

                                Scanner in = new Scanner(System.in);
                                while (true) {
                                    System.out.println("Press 1 to update Name: ");
                                    System.out.println("Press 2 to update Address: ");
                                    System.out.println("Press 3 to update Contact Number: ");
                                    System.out.println("Press 4 to exit: ");
                                    System.out.println("Enter Choice");
                                    int ch = sc.nextInt();
                                    switch (ch) {
                                        case 1:
                                            System.out.print("Update Name : ");
                                            customer.name = in.nextLine();
                                            br.write("\nName: " + customer.name + "\n");
                                            break;
                                        case 2:
                                            System.out.print("Update address : ");
                                            customer.address = in.nextLine();
                                            br.write("Address: " + customer.address + "\n");
                                            break;
                                        case 3:
                                            System.out.print("Update Contact Number : ");
                                            customer.phone = in.nextLine();
                                            br.write("Contact: " + customer.phone + "\n");
                                            break;
                                        case 4:
                                            br.write("-----------------------------------------\n");
                                            br.close();
                                            continue  outer;
                                        default:
                                            break ;
                                    }
                                    customer.add(new Date());

                                }


                            }
                        }
                        else
                        {
                            System.out.println("Username doesn't exist.");
                        }

                    }




                    case 5: {
                        br.close();
                        System.exit(1);
                    }
                    default: System.out.println("Wrong choice !");
                }

            }
            catch (IOException e)
            {
                System.out.println(e);
            }

        }
    }
    Map<String, Customer> customerMap;
    savings()
    {
        customerMap = new HashMap <String, Customer>();
    }
    public static void main(String []args) throws IOException
    {

        demosavings();

    }

}
