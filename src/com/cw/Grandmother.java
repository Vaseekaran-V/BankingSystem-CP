package com.cw;

/** *********************************************************************
 * File:	Grandmother.java
 * Author:	V. Vaseekaran
 * Contents:	6SENG002W CW:  Grandmother class
 *		Class for the Grandmother object, where the Grandmother can deposit to her grandchild's account.
 * Date:	10/1/22
 ************************************************************************ */

public class Grandmother extends Thread {
    private CurrentAccount studentBankAccount;
    private String grandmotherName;

    //Constructor for the Grandmother class
    public Grandmother(ThreadGroup group, CurrentAccount currentAccount, String grandmotherName) {
        super(group, grandmotherName);
        this.studentBankAccount = currentAccount;
        this.grandmotherName = grandmotherName;
    }

    @Override
    public void run() {
        System.out.println("Transactions from the grandmother begins...\n");

        //Grandmother depositing money to her grandchild's account
        makeDeposition(4000);
        makeDeposition(7000);

        System.out.println("Transactions from the grandmother are completed");

    }

    //method for the grandmother to deposit money to her grandchild's account
    private void makeDeposition(int amount){
        System.out.println(this.grandmotherName + " is going to deposit " + amount + " to her grandchild's account.");
        Transaction topUpGift = new Transaction(this.grandmotherName, amount);
        System.out.println(this.grandmotherName + " has started depositing " + amount + " to her grandchild's account");
        this.studentBankAccount.deposit(topUpGift);
        System.out.println(this.grandmotherName + " has completed depositing " + amount + " to her grandchild's account");
        System.out.println(topUpGift);
        System.out.println();
        //sleeping for a random time between the completed transaction
        sleepTime();
    }

    //method for random sleep time between transactions
    private void sleepTime(){
        try{
            sleep((int)(Math.random() * 100));
        }catch(InterruptedException e){}
    }
}
