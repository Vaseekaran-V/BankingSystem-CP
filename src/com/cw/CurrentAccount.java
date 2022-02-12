package com.cw;

/** *********************************************************************
 * File:	CurrentAccount.java
 * Author:	V. Vaseekaran
 * Contents:	6SENG002W CW:  CurrentAccount class
 *		Code for the current account where transactions (deposits and withdrawals) are implemented from the BankAccount interface. Acts as the Java Monitor to monitor threads
 * Date:	10/1/22
 ************************************************************************ */

public class CurrentAccount implements BankAccount{

    private String accountHolderName; //name of the account holder
    private int accountID; //the account number
    private Statement statement; //statement for the transactions performed
    private int bankBalance; //remaining balance in the account

    //Constructor method for the CurrentAccount class
    public CurrentAccount(String studentID, int studentAccountNum, int bankBalance) {
        this.accountHolderName = studentID;
        this.accountID = studentAccountNum;
        this.statement = new Statement(studentID, studentAccountNum);
        this.bankBalance = bankBalance;
    }

    //Returns the bank balance of the account
    @Override
    public int getBalance() {
        return this.bankBalance;
    }

    //Returns the account number of the bank account
    @Override
    public int getAccountNumber() {
        return this.accountID;
    }

    //Returns the name of account holder
    @Override
    public String getAccountHolder() {
        return this.accountHolderName;
    }

    //method to deposit money to the account
    @Override
    public synchronized void deposit(Transaction t) {
        int amountToDeposit = t.getAmount();
        this.bankBalance = bankBalance + amountToDeposit;
        this.statement.addTransaction(t.getCID(), amountToDeposit, this.bankBalance);

        notifyAll(); //Notify the waiting threads that money has been deposited

    }

    //method to withdraw money from the account
    @Override
    public synchronized void withdrawal(Transaction t) {
        int amountToWithdraw = t.getAmount();

        //condition to prevent withdrawal happening when bank balance is lower than the withdrawal amount
        while(amountToWithdraw > this.bankBalance){
            try{
                System.out.println("Withdrawal of " + amountToWithdraw + " by " + t.getCID() + " is not completed. Wait until there are enough funds...");
                wait(); //wait until there is sufficient bank balance to withdraw
            }
            catch(InterruptedException e){}
        }

        this.bankBalance = this.bankBalance - amountToWithdraw;
        this.statement.addTransaction(t.getCID(), amountToWithdraw, this.bankBalance);

    }

    //Check if the bank balance has gone less than zero
    @Override
    public boolean isOverdrawn() {
        return this.bankBalance < 0;
    }

    //Printing the bank statement of the account
    @Override
    public void printStatement() {
        this.statement.print();
    }
}
