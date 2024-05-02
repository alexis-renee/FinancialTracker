package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


    public class FinancialTracker {
        private static ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        private static final String FILE_NAME = "transactions.csv";
        private static final String DATE_FORMAT = "yyyy-MM-dd";
        private static final String TIME_FORMAT = "HH:mm:ss";
        private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
        private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);

        public static void main(String[] args) {
            loadTransactions(FILE_NAME);
            Scanner scanner = new Scanner(System.in);
            boolean running = true;
            while (running) {
                System.out.println("Welcome to TransactionApp");
                System.out.println("Choose an option:");
                System.out.println("D) Add Deposit");
                System.out.println("P) Make Payment (Debit)");
                System.out.println("L) Ledger");
                System.out.println("X) Exit");
                String input = scanner.nextLine().trim();

                switch (input.toUpperCase()) {
                    case "D":
                }
                addDeposit(scanner);
                break;
                case "P":
            }
            addPayment(scanner);
            break;
            case "L":
        }
        ledgerMenu(scanner);

         break;
         case "X": {
            running = false;
            break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }
        scanner.close();
}

public static void loadTransactions(String fileName) {        }
    try {
BufferedReader br = new BufferedReader(new FileReader (FILE_NAME));
String line;

    while ((line = br.readLine()) != null) {
String[] tokens = line.split("\\|");
        if (tokens.length == 5) {
LocalDate date = LocalDate.parse(tokens[0], DATE_FORMATTER);
LocalTime time = LocalTime.parse(tokens[1], TIME_FORMATTER);
String description = tokens[2];
String vendor = tokens[3];
double price = Double.parseDouble(tokens[4]);
            transactions.add (new Transaction(date, time, description, vendor, price));
        }
        br.close();
} catch (Exception e) {
        System.out.println("Error");


private static void addDeposit(Scanner scanner) {

        public DepositManager() {
        transactions = new ArrayList<>();
    }

    public void promptAndAddDeposit () {

        System.out.println("Enter date and time (yyyy-MM-dd HH:mm:ss");
        String dateTimeString = scanner.nextLine();

        LocalDateTime dateTime = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            dateTime = LocalDateTime.parse(dateTimeString, formatter);
            {catch(Exception e){

                System.out.println("Invalid date/time format. Please enter in yyyy-MM-dd HH:mm:ss format.");
            }
                return;
            }

            //Prompt for vendor
            System.out.println("Enter vendor ");
            String vendor = scannner.nextLine();
        }

        //Prompt for amount
        System.out.println("Enter amount: ");
        double amount = 0;
        try {
            amount = Double.parseDouble();
            if (amount <= 0) {
                System.out.println("Amount must be a positive number.");
                return;
            }
                        catch(NumberFormatException e){
                System.out.println("Invalid amount format. Please enter a numeric value.");
                return;

                //Create and add deposit
                Deposit deposit = new Deposit(dateTime, vendor, amount);
                transactions.add(deposit);
                System.out.println("Deposit added successfully.");

                //Deposit class definition
                private class Deposit {
                    private String vendor;
                    private double amount;

                    public Deposit(LocalDateTime dateTime, String vendor, double amount) {
                        this.dateTime = dateTime;
                        this.vendor = vendor;
                        this.amount = amount;
                    }

                    //Main method for testing
                    public static void main(String[] args) {
                        DepositManager manager = new Deposit(Manager());
                        manager.promptAndAddDeposit();
                    }
                }
            }
            private static void ledgerMenu (Scanner scanner)}
    } boolean running = true;
    while (running)
}   System.out.println("Ledger");
        System.out.println("Choose an option:");
        System.out.println("A) A`ll");
        System.out.println("D) Deposits");
        System.out.println("P) Payments");
        System.out.println("R) Reports");
        System.out.println("H) Home");

String input = scanner.nextLine().trim();
        switch (input.toUpperCase()) {
        case "A":
displayLedger();
                break;
                        case "D":
displayDeposit();
                    break;
                            case "P":
displayPayments();
                        break;
                                case "R":
reportsMenu(scanner);
                            break;
                                    case "H":
running = false;
default:

        System.out.println("Invalid option");
                                    break;

private static void displayLedger() {
    System.out.println("displayLedger");
    for(Transaction transaction:transactions) {
        System.out.println(transaction);
    }
    private static void displayDeposits() {
        if (transactions.isEmpty()) {
            System.out.println("No deposits to display.");
            return;
        }
        private static void displayPayments() {
            if (transactions.isEmpty()) {
                System.out.println("No payments to display.");
                return;

            }
            System.out.println("*****************");

            for (Transaction Payment : transactions) {
                double amount = payment.getAmount();
                if (amount<0) {

                }
            }

            private static void reportsMenu(Scanner scanner) {
                boolean running = true;
                while (running) {
                    System.out.println("Reports");
                    System.out.println("Choose an option:");
                    System.out.println("1) Month To Date");
                    System.out.println("2) Previous Month");
                    System.out.println("3) Year To Date");
                    System.out.println("4) Previous Year");
                    System.out.println("5) Search by Vendor");
                    System.out.println("0) Back");

                    String input = scanner.nextLine().trim();
                    switch (input) {
                        case "1":
                            // Generate a report for all transactions within the current month,
                            // including the date, vendor, and amount for each transaction.
                        case "2":
                            // Generate a report for all transactions within the previous month,
                            // including the date, vendor, and amount for each transaction.
                        case "3":
                            // Generate a report for all transactions within the current year,
                            // including the date, vendor, and amount for each transaction.
                        case "4":
                            // Generate a report for all transactions within the previous year,
                            // including the date, vendor, and amount for each transaction.
                        case "5":
                            // Prompt the user to enter a vendor name, then generate a report for all transactions
                            // with that vendor, including the date, vendor, and amount for each transaction.
                        case "0":
                            // running = false;
                            // default:
                            System.out.println("Invalid option");
                            break;
                    }
                }
            }

            private static void filterTransactionsByDate(LocalDate startDate, LocalDate endDate) {
                // This method filters the transactions by date and prints a report to the console.}
                // It takes two parameters: startDate and endDate, which represent the range of dates to filter by.
                // The method loops through the transactions list and checks each transaction's date against the date range.
                // Transactions that fall within the date range are printed to the console.
                // If no transactions fall within the date range, the method prints a message indicating that there are no results.    }

                private static void filterTransactionsByVendor(String vendor) {
                    // This method filters the transactions by vendor and prints a report to the console.
                    // It takes one parameter: vendor, which represents the name of the vendor to filter by.
                    // The method loops through the transactions list and checks each transaction's vendor name against the specified vendor name.
                    // Transactions with a matching vendor name are printed to the console.
                    // If no transactions match the specified vendor name, the method prints a message indicating that there are no results.    }
                }
























            }
