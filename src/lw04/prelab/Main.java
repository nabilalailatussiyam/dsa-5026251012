package lw04.prelab;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(Main.class.getResourceAsStream("branches.txt"));

        int n = sc.nextInt();
        int[] branches = new int[n];

        for (int i = 0; i < n; i++) {
            branches[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] targets = new int[m];

        for (int i = 0; i < m; i++) {
            targets[i] = sc.nextInt();
        }

        insertionSort(branches, 3);

        for (int i = 0; i < n; i++) {
            System.out.print(branches[i]);

            if (i < n - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();

        for (int i = 0; i < m; i++) {
            int result = linearSearch(branches, targets[i]);

            if (result != -1) {
                System.out.println(targets[i] + ": FOUND at index " + result);
            } else {
                System.out.println(targets[i] + ": NOT FOUND");
            }
        }

        sc.close();

    }

    public static void insertionSort(int[] branches, int passes) {
        for (int i = 1; i <= passes; i++) {
            int key = branches[i];
            int j = i - 1;

            while (j >= 0 && branches[j] < key) {
                branches[j + 1] = branches[j];
                j--;
            }

            branches[j + 1] = key;
        }
    }

    public static int linearSearch(int[] branches, int target) {
    for (int i = 0; i < branches.length; i++) {
        if (branches[i] == target) {
            return i;
        }
    }

    return -1;
    }
    
}
