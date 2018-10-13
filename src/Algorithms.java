import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Algorithms {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /*
        System.out.println(changeMoney(997));
        */
        /*
        int n = scanner.nextInt();
        int w = scanner.nextInt();
        double [][] items = new double[n][2];
        for (int i = 0; i < n; i++) {
            items[i][0] = scanner.nextInt();
            items[i][1] = scanner.nextInt();
        }
        System.out.println(maxLoot(n, w,  items));
        */
        /*
        int n = scanner.nextInt();

        long [] profits = new long[n];
        long [] clicks = new long[n];
        for (int i = 0; i < n; i++) {
            profits[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            clicks[i] = scanner.nextInt();
        }
        System.out.println(maxProfit(n,profits,clicks));
        */
    }

    private static long maxProfit(int n, long[] profits, long[] clicks) {
        Arrays.sort(profits);
        Arrays.sort(clicks);
        System.out.println(Arrays.toString(profits));
        System.out.println(Arrays.toString(clicks));
        long maxProfit = 0;
        for (int i = 0; i < n; i++) {
            maxProfit+=profits[i]*clicks[i];
        }
        return maxProfit;
    }

    private static double maxLoot(int n, int w, double[][] items) {
        double totalValue = 0;
        Arrays.sort(items, Comparator.comparingDouble(o -> o[0]/o[1]));
        System.out.println(Arrays.deepToString(items));
        for (int i = n-1; i >= 0 ; i--) {
            if (w==0){
                return totalValue;
            }
            if(items[i][1]<w){
                w-=items[i][1];
                totalValue+=items[i][0];
            }
            else{
                totalValue+=items[i][0]/items[i][1]*w;
                w=0;
            }
        }
        return totalValue;
    }


    private static int changeMoney(int i) {
        return i/10+i%10/5+i%5;
    }

}
