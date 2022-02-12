package com.cw;

/** *********************************************************************
 * File:	StudentLoanCompany.java
 * Author:	V. Vaseekaran
 * Contents:	6SENG002W CW:  StudentLoanCompany class
 *		Class for the StudentLoanCompany object, where the Student Loan Company can deposit loans to the student's account.
 * Date:	10/1/22
 ************************************************************************ */

public class StudentLoanCompany extends Thread{
    private CurrentAccount studentBankAccount;
    private String studentLoanCompanyName;

    //Constructor for the StudentLoanCompany class
    public StudentLoanCompany(ThreadGroup group, CurrentAccount currentAccount, String studentLoanCompanyName) {
        super(group, studentLoanCompanyName);
        this.studentBankAccount = currentAccount;
        this.studentLoanCompanyName = studentLoanCompanyName;
    }

    @Override
    public void run() {
        System.out.println("Transactions from the Student Loan Company begins...\n");

        makeDeposition(10000);
        makeDeposition(15000);
        makeDeposition(9000);

        System.out.println("Transactions from the Student Loan Company is completed");

    }

    //Method for random sleep time between transactions
    private void sleepTime(){
        try{
            sleep((int)(Math.random() * 100));
        }catch(InterruptedException e){}
    }

    //Method for the loan company to make a deposit to the student's account
    private void makeDeposition(int amount){
        System.out.println(this.studentLoanCompanyName + " is going to deposit the loan of " + amount + " to the student's account.");
        Transaction studentLoan = new Transaction(this.studentLoanCompanyName, amount);
        System.out.println(this.studentLoanCompanyName + " has started depositing " + amount + " to the student's account.");
        this.studentBankAccount.deposit(studentLoan);
        System.out.println(this.studentLoanCompanyName + "  has completed depositing " + amount + " to the student's account.");
        System.out.println(studentLoan);
        System.out.println();
        //sleeping for a random time between the completed transaction
        sleepTime();
    }
}
