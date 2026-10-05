package lw03.unguided;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Map<String, Integer> enrollment = new HashMap<>();
        LinkedList<String> courseList = new LinkedList<>();
        LinkedList<String> checks = new LinkedList<>();

        int rejected = 0;

        Scanner sc = new Scanner(
            Main.class.getResourceAsStream("enrollment.txt")
        );

        while (sc.hasNext()) {
            String operation = sc.next();
            String course = sc.next();

            if (operation.equals("REGISTER")) {
                int count = sc.nextInt();

                if (count <= 0) {
                    rejected++;
                } else if (enrollment.containsKey(course)) {
                    enrollment.put(course, enrollment.get(course) + count);
                } else {
                    enrollment.put(course, count);
                    courseList.add(course);
                }

                
            } else if (operation.equals("WITHDRAW")) {
                int count = sc.nextInt();

                if (count <= 0) {
                    rejected++;
                } else if (!enrollment.containsKey(course)) {
                    rejected++;
                } else if (enrollment.get(course) < count) {
                    rejected++;
                } else {
                    enrollment.put(course, enrollment.get(course) - count);
                }

            } else if (operation.equals("CHECK")) {
                if (enrollment.containsKey(course)) {
                    checks.add(course + ": " + enrollment.get(course) + " students");
                } else {
                    checks.add(course + " : Not found");
                }
            }
        }

        sc.close();

        System.out.println("===== Enrollment Checks =====");

        for (String check : checks) {
            System.out.println(check);
        }

        System.out.println();

        System.out.println("===== Final Enrollment =====");

        for (String course : courseList) {
            System.out.println(course + ": " + enrollment.get(course) + " students");
        }

        System.out.println();
        System.out.println("Rejected operations: " + rejected);
    }
}
