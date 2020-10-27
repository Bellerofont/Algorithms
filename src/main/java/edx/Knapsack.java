package edx;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Knapsack {
    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        URL resource = BinarySearch.class.getClassLoader().getResource("edx/data/6_1_knapsack.in");
        assert resource != null;
        File input = Paths.get(resource.toURI()).toFile();
        Scanner scanner = new Scanner(input);
        int weight = scanner.nextInt();
        int n = scanner.nextInt();
        int[] bars = new int[n];
        for (int i = 0; i < n; i++) {
            bars[i] = scanner.nextInt();
        }
        long start = System.nanoTime();
        System.out.println(knapsack(weight, n, bars));
        long end = System.nanoTime();
        System.out.println("run time: " + (end-start)/1000000 + "ms");
    }

    private static int knapsack(int weight, int n, int[] bars) {
        int[][] total = new int[weight+1][n+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= weight; j++) {
                if(j==0||i==0) {
                    total[j][i] = 0;
                } else if (bars[i-1]<=j){
                    total[j][i] = Math.max(total[j][i-1],total[j-bars[i-1]][i-1]+bars[i-1]);
                } else{
                    total[j][i] = Math.max(total[j-1][i],total[j][i-1]);
                }
            }
        }
        System.out.println(Arrays.deepToString(total));
        return total[weight][n];
    }
}
