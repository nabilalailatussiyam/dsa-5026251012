package lw01.unguided;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("rentals.txt")
        );

        int totalRental = scanner.nextInt();

        Rental[] rentals = new Rental[totalRental];

        for (int i = 0; i < totalRental; i++) {
            String type = scanner.next();
            String id = scanner.next();
            int days = scanner.nextInt();
            int units = scanner.nextInt();

            if (type.equals("LAPTOP")) {
                rentals[i] = new LaptopRental(id, days, units);
            } else if (type.equals("PROJECTOR")) {
                rentals[i] = new ProjectorRental(id, days, units);
            }
        }

        for (Rental rental : rentals) {
            System.out.println(rental.summary());
        }

        scanner.close();
    }
}
