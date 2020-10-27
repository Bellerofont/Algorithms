package misc;

import java.util.Scanner;

public class GreatestCommonDivisor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextInt();
        long b = scanner.nextInt();
        System.out.println(findGCD(a,b));
        //System.out.println(findLCM(a,b));
    }

    private static long findGCD(long a, long b) {
        long max = Math.max(a,b);
        long min = Math.min(a,b);
        if(max%min==0) return min;
        return findGCD(max%min,min);
    }

    private static long findLCM(long a,long b){
        return a*b/findGCD(a,b);
    }
}
