package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FinancialTracker {

    private static ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private static final String FILE_NAME = "transactions.csv";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm:ss";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);

    public static void main(String[] args) throws IOException {
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
                    addDeposit(scanner);
                    break;
                case "P":
                    addPayment(scanner);
                    break;
                case "L":
                    ledgerMenu(scanner);
                    break;
                case "X":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }

        scanner.close();
    }

    public static void loadTransactions(String fileName) throws IOException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    LocalDate date = LocalDate.parse(parts[0], DATE_FORMATTER);
                    LocalTime time = LocalTime.parse(parts[1], TIME_FORMATTER);
                    String vendor = parts[2];
                    String description = parts[3];
                    double amount = Double.parseDouble(parts[4]);
                    transactions.add(new Transaction());
                } else {
                    System.out.println("Invalid format: " + line);
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. New file created ");
            throw new RuntimeException(e);
        }
    }

    private static void addDeposit(Scanner scanner) {
        System.out.println("Enter the date (yyyy-MM-dd):");
        String userDate = scanner.nextLine();
        LocalDate date = LocalDate.parse(userDate, DATE_FORMATTER);

        System.out.println("Enter the time (HH:mm:ss):");
        String userTime = scanner.nextLine();
        LocalTime time = LocalTime.parse(userTime, TIME_FORMATTER);

        System.out.println("Enter a description of deposit: ");
        String description = scanner.nextLine();

        System.out.println("Enter the vendor:");
        String vendor = scanner.nextLine();

        double amount;
        while (true) {
            System.out.println("Enter the amount:");
            try {
                amount = Double.parseDouble(scanner.nextLine());
                if (amount <= 0) {
                    System.out.println("Must be a positive number.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Please enter a number.");
            }
        }

        Transaction deposit = new Transaction();
        transactions.add(deposit);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true))){
            for (Transaction transaction : transactions) {
                String formattedTransaction = String.format("%s|%s|%s|%s|%.2f\n",transaction.getDate(),transaction.getTime(),
                        transaction.getDescription(),transaction.getVendor(),transaction.getAmount());
                writer.write(formattedTransaction);
                writer.newLine();
            }
            System.out.println("Deposit has been saved to file");
        }catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
        scanner.close();

    }

    private static void addPayment(Scanner scanner) {
        System.out.println("Enter the date (yyyy-MM-dd):");
        String userDate = scanner.nextLine();
        LocalDate date = LocalDate.parse(userDate, DATE_FORMATTER);

        System.out.println("Enter the time (HH:mm:ss):");
        String userTime = scanner.nextLine();
        LocalTime time = LocalTime.parse(userTime, TIME_FORMATTER);

        System.out.println("Enter a description of Payment ");
        String description = scanner.nextLine();

        System.out.println("Enter the vendor:");
        String vendor = scanner.nextLine();

        double amount;
        while (true) {
            System.out.println("Enter the amount:");
            try {
                amount = Double.parseDouble(scanner.nextLine());
                if (amount <= 0) {
                    System.out.println("Must be a positive number.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Please enter a number.");
            }
        }

        Transaction deposit = new Transaction();
        transactions.add(deposit);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true))){
            for (Transaction transaction : transactions) {
                String formattedTransaction = String.format("%s|%s|%s|%s|%.2f\n",transaction.getDate(),transaction.getTime(),
                        transaction.getDescription(),transaction.getVendor(),transaction.getAmount());
                writer.write(formattedTransaction);
                writer.newLine();
            }
            System.out.println("Payment has been saved to file");
        }catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
        scanner.close();

    }

    private static void ledgerMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("Ledger");
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
                    displayDeposits();
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
            }
        }
    }

    private static void displayLedger() {
        System.out.println("Ledger");
        for (Transaction transaction: transactions) {
            System.out.println(transaction);
        }
    }

    private static void displayDeposits() {
        System.out.println("Date\nTime\nVendor\nAmount");
        System.out.println("*****************");

        for (Transaction deposit : transactions) {
            double amount = deposit.getAmount();
            if (amount > 0) {
                System.out.println(deposit);
            }
        }
    }
    private static void displayPayments() {
        System.out.println("Date\nTime\nVendor\nAmount");
        System.out.println("*****************");

        for (Transaction deposit : transactions) {
            double amount = deposit.getAmount();
            if (amount < 0) {
                System.out.println(deposit);
            }
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
                    running = false;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    private static void filterTransactionsByDate(LocalDate startDate, LocalDate endDate) {
    boolean foundTransactions = false;

    System.out.println("Transactions between " + startDate + " and " + endDate);

    for (Transaction transaction : transactions) {
        LocalDate transactionDate = transaction.getDate();
        if (!transactionDate.isBefore(startDate) && !transactionDate.isAfter(endDate)) {
            System.out.println(transaction);
            foundTransactions = true;
        }
    }

    if (!foundTransactions) {
        System.out.println("No transactions found between " + startDate + " and " + endDate );
    }
}



    private static void filterTransactionsByVendor(List<Transaction> transactions, String vendor ) {
        boolean foundTransations = false;

        System.out.println("Transactions for Vendor" + vendor);

        for (Transaction transaction : transactions) {
            if (transaction.getVendor().equalsIgnoreCase(vendor)) {
                System.out.println(transaction);
                foundTransations = true;
            }
        }

        if (!foundTransations) {
            System.out.println("No transactions found for Vendor");
        }
    }
    }




