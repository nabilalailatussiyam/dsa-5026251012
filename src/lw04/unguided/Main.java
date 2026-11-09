package lw04.unguided;

import java.util.Scanner;

public class Main {
    static void selectionSort(int[] arr, int passes){
        for (int i = 0; i < passes && i < arr.length - 1; i++){
            int idxMin = i;

            for (int j = i + 1; j < arr.length; j++){
                if (arr[j] < arr[idxMin]){
                    idxMin = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr [idxMin];
            arr[idxMin] = temp;
        }
    }

    static int linearSearch(int[] arr, int target){
        for (int i = 0; i < arr.length; i++){
            if (arr[i] == target){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("cards2.txt"));

        int n = sc.nextInt();
        int[] cards = new int[n];

        for (int i = 0; i < n; i++) {
            cards[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] requests = new int[m];

        for (int i = 0; i < m; i++) {
            requests[i] = sc.nextInt();
        }

        selectionSort(cards, 2);

        for (int i = 0; i < n; i++) {
            System.out.print(cards[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < m; i++) {
            int index = linearSearch(cards, requests[i]);

            if (index != -1) {
                System.out.println(requests[i] + ": FOUND at index " + index);
            } else {
                System.out.println(requests[i] + ": NOT FOUND");
            }
        }

        sc.close();
    }
}
