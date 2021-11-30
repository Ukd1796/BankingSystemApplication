package com.company;
import java.util.Scanner;
class BankDetails {
    private String AccNo;
    private String name;
    private long balance;
    Scanner sc = new Scanner(System.in);
    public void openAccount() {
        System.out.print("Enter Account No: ");
        try {
            AccNo = sc.next();
        }
        catch(Exception e)
        {
             System.out.println("Please enter a valid input!!");
        }
        System.out.print("Enter Name: ");
        name = sc.next();
        System.out.print("Enter Balance: ");
        int flag =1;
        while(flag!=0) {
            try {
                balance = sc.nextLong();
                if (balance >= 1000) {
                   flag =0;
                } else {
                    throw new MinimumBalanceException();
                }
            } catch (MinimumBalanceException m) {
                System.out.println("Please enter a bigger amount for initial opening of account!!");
            }
        }

    }
    // display account details
    public void showAccount() {
        System.out.println("Name of account holder: " + name);
        System.out.println("Account no.: " + AccNo);
        System.out.println("Balance: " + balance);
    }
    //deposit money
    public void deposit() {
        long amt =0;
        System.out.println("Enter the amount you want to deposit: ");
        try {
            amt = sc.nextLong();
            balance = balance + amt;
        }
        catch(Exception e)
        {
            System.out.println("Please enter a valid input!!");
        }
    }
    // withdraw money
    public void withdrawal() {
        long amt;
        System.out.println("Enter the amount you want to withdraw: ");
        try {
            amt = sc.nextLong();
            if (balance >= amt) {
                balance = balance - amt;
                System.out.println("Balance after withdrawal: " + balance);
            }
            else
            {
                throw new InsufficientBalanceException();
            }
        }
        catch(InsufficientBalanceException e)
        {
            System.out.println("You have insufficient balance!!!");
        }
    }
    //search an account number
    public boolean search(String ac_no) {
        if (AccNo.equals(ac_no)) {
            showAccount();
            return (true);
        }
        return (false);
    }
}
public class BankingApplication{
    public static void main(String arg[]) {
        Scanner sc = new Scanner(System.in);
        //create initial accounts
        System.out.print("How many pre- added accounts do you want to have ? ");
        int n = sc.nextInt();
        BankDetails C[] = new BankDetails[n];
        for (int i = 0; i < C.length; i++) {
            C[i] = new BankDetails();
            C[i].openAccount();
        }
        // loop runs until number 5 is not pressed to exit
        int ch;
        do {
            System.out.println("\n ***Banking Application***");
            System.out.println("1. Check your account \n 2. Deposit the amount \n 3. Withdraw the amount \n 4.Exit ");
            System.out.println("Enter your choice: ");
            ch = sc.nextInt();
            switch (ch) {
                case 1:
                    System.out.print("Enter account no. you want to search: ");
                    String ac_no = sc.next();
                    boolean found = false;
                    for (int i = 0; i < C.length; i++) {
                        found = C[i].search(ac_no);
                        if (found) {
                            break;
                        }
                    }
                            try{
                            if(!found)
                            {
                                throw new AccountNotFoundException();
                            }
                        }
                        catch(AccountNotFoundException a)
                        {
                            System.out.println("Account doesn't exist..!!");
                            break;
                        }
                    break;
                case 2:
                    System.out.print("Enter Account no. : ");
                    ac_no = sc.next();
                    found = false;
                    for (int i = 0; i < C.length; i++) {
                        found = C[i].search(ac_no);
                        if (found) {
                            C[i].deposit();
                            break;
                        }
                    }
                    try{
                        if(!found)
                        {
                            throw new AccountNotFoundException();
                        }
                         }
                        catch(AccountNotFoundException a)
                        {
                            System.out.println("Account doesn't exist..!!");
                            break;
                        }
                    break;
                case 3:
                    System.out.print("Enter Account No : ");
                    ac_no = sc.next();
                    found = false;
                    for (int i = 0; i < C.length; i++) {
                        found = C[i].search(ac_no);
                            if (found) {
                                C[i].withdrawal();
                                break;
                            }
                        }
                    try{
                        if(!found)
                        {
                            throw new AccountNotFoundException();
                        }
                    }
                    catch(AccountNotFoundException a)
                    {
                        System.out.println("Account doesn't exist..!!");
                        break;
                    }
                    break;
                case 4:
                    System.out.println("Thank You !!");
                    break;
            }
        }
        while (ch != 4);
    }
}
class MinimumBalanceException extends Exception{
    MinimumBalanceException(){
        super("Minimum Balance Insufficient");
    }
}

class InsufficientBalanceException extends Exception{
    InsufficientBalanceException(){
        super("Insufficient Balance Please Try With Different Amount");
    }
}
class AccountNotFoundException extends Exception{
    AccountNotFoundException(){
        super("This account does not exist!!");
    }
}
