package kattis.level1;

import java.util.Scanner;

public class TakeTwoStones {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(n%2==0?"Bob":"Alice");
    }
}
