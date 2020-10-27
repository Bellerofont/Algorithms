package misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fibonacci {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        int m = scanner.nextInt();
        System.out.println(pisanoPeriod(n,m));
    }

    private static long calcFib(int n) {
        if (n<=1){
            return n;
        }
        long[] fibs = new long[n+1];
        fibs[0] = 0;
        fibs[1] = 1;
        for (int i = 2; i <=n ; i++) {
            fibs[i] = fibs[i-1] + fibs[i-2];
        }
        return fibs[n];
    }

    private static int lastDigit(int n){
        if (n<=1) return n;
        int previous;
        int current = 1;
        int next = 1;
        for (int i = 2; i < n; i++) {
            previous = current;
            current = next;
            next = (current + previous)%10;
        }
        return next;
    }

    private static long pisanoPeriod(long n, int m){
        List<Integer> fibs = new ArrayList<>();
        fibs.add(0);
        fibs.add(1);
        int period;
        for (int i = 2; i <= n; i++) {
            fibs.add((fibs.get(i - 1) + fibs.get(i - 2)) % m);
            System.out.println(i + "  " + fibs.get(i));
            if (fibs.get(i) == 1 && fibs.get(i - 1) == 0) {
                period = i - 1;
                return fibs.get(Math.toIntExact(n % period));
            }

        }
        return fibs.get((int)n);
        }
}
