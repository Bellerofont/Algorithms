package edx;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Scanner;

public class LongestCommonSubsequence3 {

    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        URL resource = BinarySearch.class.getClassLoader().getResource("edx/data/5_5_lcs3.in");
        assert resource != null;
        File input = Paths.get(resource.toURI()).toFile();
        Scanner scanner = new Scanner(input);
        int n = scanner.nextInt();
        int [] sequence1 = new int[n];
        for (int i = 0; i < n; i++) {
            sequence1[i] = scanner.nextInt();
        }
        int m = scanner.nextInt();
        int [] sequence2 = new int[m];
        for (int i = 0; i < m; i++) {
            sequence2[i] = scanner.nextInt();
        }
        int l = scanner.nextInt();
        int [] sequence3 = new int[m];
        for (int i = 0; i < l; i++) {
            sequence3[i] = scanner.nextInt();
        }
        long start = System.nanoTime();
        System.out.println(LCS(n, sequence1, m, sequence2, l, sequence3));
        long end = System.nanoTime();
        System.out.println("run time: " + (end-start)/1000000 + "ms");
    }

    private static int LCS(int n, int[] sequence1, int m, int[] sequence2, int l, int[] sequence3) {
        int[][][] distance = new int[n+1][m+1][l+1];
        for(int k = 0; k<distance[0][0].length; k++) {
            for (int j = 0; j < distance[0].length; j++) {
                for (int i = 0; i < distance.length; i++) {
                    if (i == 0 || j == 0 || k == 0) distance[i][j][k] = 0;
                    else if (sequence1[i - 1] == sequence2[j - 1] && sequence1[i - 1] == sequence3[k - 1]) {
                        distance[i][j][k] = distance[i - 1][j - 1][k - 1] + 1;
                    } else {
                        distance[i][j][k] = Math.max(distance[i - 1][j][k], Math.max(distance[i][j - 1][k], distance[i][j][k - 1]));
                    }
                }
            }
        }
        return distance[n][m][l];
    }
}
