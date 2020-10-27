package kattis.level3;

import java.util.Scanner;

public class BobbysBet {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int r = in.nextInt();
            int s = in.nextInt();
            int x = in.nextInt();
            int y = in.nextInt();
            int w = in.nextInt();
            double success = ((double)s-r+1)/s;
            double failure = 1 - success;
            double chance = 1;
            for(int j = 0; j < x; j++){
                chance-=binominalCoef(y,j)*Math.pow(success,j)*Math.pow(failure,y-j);
            }
            System.out.println(chance*w>1?"yes":"no");
        }

    }

    static int binominalCoef(int n, int k){
        return factorial(n)/(factorial(k)*factorial(n-k));
    }

    static int factorial(int n) {
        if (n >= 1) {
            return n * factorial(n - 1);
        } else {
            return 1;
        }
    }

}
