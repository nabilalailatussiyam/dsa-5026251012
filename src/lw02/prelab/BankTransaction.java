package lw02.prelab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BankTransaction {

    public static void main(String[] args) {

        LinkedList<String[]> transactions = new LinkedList<>();  // nyimpen transactionnya di linkedlist

        Scanner scanner = new Scanner(
            BankTransaction.class.getResourceAsStream("transactions.txt")
        );

        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();
            String[] data = line.split(" ");         //mecah data jadi array of string, sendiri sendiir 

            transactions.add(data);
        }

        scanner.close();

        LinkedList<String[]> customers = new LinkedList<>();

        for (String[] transaction : transactions) {

            boolean found = false;

            for (String[] customer : customers) {

                if (customer[0].equals(transaction[0])) {
                    found = true;
                }
            }

            if (!found) {
                customers.add(new String[]{transaction[0], "0"});
            }
        }

        Queue<String[]> queue = new LinkedList<>();

        Stack<String[]> failedTransactions = new Stack<>();

        while (!transactions.isEmpty()) {
            queue.add(transactions.remove());
        }

        while (!queue.isEmpty()) {

            String[] transaction = queue.poll();

            for (String[] customer : customers) {

                if (customer[0].equals(transaction[0])) {

                    int amount = Integer.parseInt(transaction[2]);

                    if (transaction[1].equals("DEPOSIT")) {

                        int balance = Integer.parseInt(customer[1]);

                        balance = balance + amount;

                        customer[1] = String.valueOf(balance);
                    }

                    if (transaction[1].equals("WITHDRAW")) {

                        int balance = Integer.parseInt(customer[1]);

                        if (amount <= balance) {

                            balance = balance - amount;

                            customer[1] = String.valueOf(balance);

                        } else {

                            failedTransactions.push(transaction);
                        }
                    }
                }
            }
        }

        System.out.println("=== Final Balances ===");

        for (String[] customer : customers) {

            System.out.println(customer[0] + " : " + customer[1]);
        }

        System.out.println("=== Failed Transactions ===");

        while (!failedTransactions.isEmpty()) {

            String[] transaction = failedTransactions.pop();

            System.out.println(
                transaction[0] + " " +
                transaction[1] + " " +
                transaction[2]
            );
        }
    }
}
