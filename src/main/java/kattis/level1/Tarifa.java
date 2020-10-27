package kattis.level1;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Tarifa {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int n = scanner.nextInt();
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i]=scanner.nextInt();
        }
        System.out.println(tarifa(x,n,p));
    }

    private static int tarifa(int x, int n, int[] p) {
        return x*(n+1) - IntStream.of(p).sum();
    }
}
