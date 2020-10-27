package edx;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class PlacingParentheses {
    private static int[][] minA;
    private static int[][] maxA;
    private static char[] operations;

    private static long getMaximValue(String exp) {
        int n = exp.length()/2+1;
        int[] values = new int[n];
        operations = new char[n-1];
        for (int i = 0; i < exp.length(); i++) {
            if(i%2==0) {
                values[i/2]=Character.getNumericValue(exp.charAt(i));
            } else {
                operations[i/2] = exp.charAt(i);
            }
        }
        minA = new int[n][n];
        maxA = new int[n][n];
        for (int i = 0; i < n; i++) {
            minA[i][i] = values[i];
            maxA[i][i] = values[i];
        }
        for (int s = 1; s < n; s++) {
            for (int i = 0; i < n - s; i++) {
                int j = i + s;
                int[] minMax = minAndMax(i,j);
                minA[i][j] = minMax[0];
                maxA[i][j] = minMax[1];
            }
        }
        System.out.println(Arrays.deepToString(minA));
        System.out.println(Arrays.deepToString(maxA));
        return maxA[0][n-1];
    }

    private static int eval(int a, int b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            assert false;
            return 0;
        }
    }

    private static int[] minAndMax(int i,int j){
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int k = i; k < j; k++) {
            int a = eval(maxA[i][k],maxA[k+1][j],operations[k]);
            int b = eval(maxA[i][k],minA[k+1][j],operations[k]);
            int c = eval(minA[i][k],maxA[k+1][j],operations[k]);
            int d = eval(minA[i][k],minA[k+1][j],operations[k]);
            min = IntStream.of(min,a,b,c,d).min().getAsInt();
            max = IntStream.of(max,a,b,c,d).max().getAsInt();
        }
        return new int[]{min,max};
    }

    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        //String exp = scanner.next();
        String exp = "7+6+3-2-7-4*2+4+2-9*6*8";
        System.out.println(getMaximValue(exp));
    }
}

