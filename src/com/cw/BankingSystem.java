package com.cw;

/** *********************************************************************
 * File:	BankingSystem.java
 * Author:	V. Vaseekaran
 * Contents:	6SENG002W CW:  BankingSystem class
 *		Class with the main method to instantiate the threads and run the transactions regarding the student's bank account
 * Date:	10/1/22
 ************************************************************************ */

public class BankingSystem {

    CurrentAccount studentBankAccount; //the current account of the student

    //Thread groups for the humans and organizations involved in the transactions
    ThreadGroup humans;
    ThreadGroup organizations;

    //The objects involved in the transactions
    Student student;
    Grandmother grandmother;
    StudentLoanCompany studentLoanCompany;
    University university;

    //Initializing other variables (such as names of humans and organizations) in the transactions
    String STUDENT_NAME = "Vaseekaran V";
    String GRANDMOTHER_NAME = "Mrs Selvi";
    String STUDENT_LOAN_COMPANY_NAME = "Best Loans Pvt Ltd";
    String UNIVERSITY_NAME = "Unique Uni";
    int ACCOUNT_NUMBER = 1059601012;

    //Constructor for BankingSystem (initializing the objects that are involved in the transaction)
    public BankingSystem() {
        this.humans = new ThreadGroup("Humans");
        this.organizations = new ThreadGroup("Organizations");
        this.studentBankAccount = new CurrentAccount(STUDENT_NAME, ACCOUNT_NUMBER, 6000);

        this.student = new Student(humans, studentBankAccount, STUDENT_NAME);
        this.grandmother = new Grandmother(humans, studentBankAccount, GRANDMOTHER_NAME);
        this.studentLoanCompany = new StudentLoanCompany(organizations, studentBankAccount, STUDENT_LOAN_COMPANY_NAME);
        this.university = new University(organizations, studentBankAccount, UNIVERSITY_NAME);

    }

    public static void main(String[] args) {
        System.out.println("The transactions for the student's bank accounts are going to begin...");
        BankingSystem bankingSystem = new BankingSystem();

        //Starting the threads
        System.out.println("The Student thread has started\n");
        bankingSystem.student.start();
        System.out.println("The Grandmother thread has started\n");
        bankingSystem.grandmother.start();
        System.out.println("The StudentLoanCompany thread has started\n");
        bankingSystem.studentLoanCompany.start();
        System.out.println("The University thread has started\n");
        bankingSystem.university.start();

        //waiting for the started threads to terminate
        try{
            bankingSystem.student.join();
            bankingSystem.grandmother.join();
            bankingSystem.studentLoanCompany.join();
            bankingSystem.university.join();
        }catch (InterruptedException e){}

        //Printing the bank statement of all the transactions that have occurred
        bankingSystem.studentBankAccount.printStatement();

        System.out.println("All the transactions for the student's bank account has ended");
    }
}
