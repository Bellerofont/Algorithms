package kattis.level1;

import java.util.Scanner;

public class Pot {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            sum+=Math.pow(num/10,num%10);
        }
        System.out.println(sum);
    }
}
