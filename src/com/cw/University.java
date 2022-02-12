package com.cw;

/** *********************************************************************
 * File:	University.java
 * Author:	V. Vaseekaran
 * Contents:	6SENG002W CW:  University class
 *		Class for the University object, where the University can withdraw fees from the student's account.
 * Date:	10/1/22
 ************************************************************************ */

public class University extends Thread{
    private CurrentAccount studentBankAccount;
    private String universityName;

    //Constructor for the University Class
    public University(ThreadGroup group, CurrentAccount currentAccount, String universityName) {
        super(group, universityName);
        this.studentBankAccount = currentAccount;
        this.universityName = universityName;
    }

    @Override
    public void run() {
        System.out.println("Transactions from the university begins...\n");

        //Transactions done by the University
        makeWithdrawal(10000);
        makeWithdrawal(8000);
        makeWithdrawal(15000);

        System.out.println("Transactions from the university are completed");

    }

    //Method for having random sleep time between transactions
    private void sleepTime(){
        try{
            sleep((int)(Math.random() * 100));
        }catch(InterruptedException e){}
    }

    //Method for the University to withdraw money fron the student's account
    private void makeWithdrawal(int amount){
        System.out.println(this.universityName + " is going to withdraw the fees amount of " + amount + " from the student's account");
        Transaction feesPayment = new Transaction(this.universityName, amount);
        System.out.println(this.universityName + " started withdrawing " + amount + " from the student's account");
        this.studentBankAccount.withdrawal(feesPayment);
        System.out.println(this.universityName + " has completed withdrawing " + amount + " from the student's account.");
        System.out.println(feesPayment);
        System.out.println();
        //sleeping for a random time between the completed transaction
        sleepTime();
    }
}
