package com.cw;

/** *********************************************************************
 * File:	Student.java
 * Author:	V. Vaseekaran
 * Contents:	6SENG002W CW:  Student class
 *		Class for the Student object, where the Student can withdraw/deposit from/to their account.
 * Date:	10/1/22
 ************************************************************************ */

public class Student extends Thread {
    private CurrentAccount studentBankAccount;
    private String studentName;

    //Constructor for the Student class
    public Student(ThreadGroup group, CurrentAccount currentAccount, String studentName) {
        super(group, studentName);
        this.studentBankAccount = currentAccount;
        this.studentName = studentName;
    }

    @Override
    public void run() {
        System.out.println("Transactions from the student begins...\n");

        //Transactions done by the student
        makeDeposition(1000);
        makeWithdrawal(1400);
        makeDeposition(500);
        makeWithdrawal(800);
        makeWithdrawal(1200);
        makeDeposition(2000);

        System.out.println("Transactions from the student are completed");
    }

    //Method for a student to deposit money to their account
    private void makeDeposition(int amount){
        System.out.println(this.studentName + " is going to deposit " + amount + " to their bank account.");
        Transaction deposition = new Transaction(this.studentName, amount);
        System.out.println(this.studentName + " started depositing " + amount + " to their bank account.");
        this.studentBankAccount.deposit(deposition);
        System.out.println(this.studentName + " has completed depositing " + amount + " to their bank account.");
        System.out.println(deposition);
        System.out.println();
        //sleeping for a random time between the completed transaction
        sleepTime();
    }

    //Method for a student to withdraw money from their account
    private void makeWithdrawal(int amount){
        System.out.println(this.studentName + " is going to withdraw " + amount + " from their bank account");
        Transaction withdrawal = new Transaction(this.studentName, amount);
        System.out.println(this.studentName + " started withdrawing " + amount + " from their bank account.");
        this.studentBankAccount.withdrawal(withdrawal);
        System.out.println(this.studentName + " has completed withdrawing " + amount + " from their bank account.");
        System.out.println(withdrawal);
        System.out.println();
        //sleeping for a random time between the completed transaction
        sleepTime();
    }

    //Method for a random sleep time between each transaction
    private void sleepTime(){
        try{
            sleep((int)(Math.random() * 100));
        }catch(InterruptedException e){}
    }
}
