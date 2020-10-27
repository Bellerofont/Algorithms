package edx;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.IntStream;

public class SouvenirPartitioning {
    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        URL resource = BinarySearch.class.getClassLoader().getResource("edx/data/6_2_souvenirs.in");
        assert resource != null;
        File input = Paths.get(resource.toURI()).toFile();
        Scanner scanner = new Scanner(input);
        long start = System.nanoTime();
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int[] souvenirs = new int[n];
            for (int i = 0; i < n; i++) {
                souvenirs[i] = scanner.nextInt();
            }
            System.out.print(partitioning3(n, souvenirs)?1:0);
        }
        long end = System.nanoTime();
        System.out.println("\nrun time: " + (end - start) / 1000000 + "ms");
    }

    private static boolean partitioning3(int n, int[] souvenirs) {
        int sum = IntStream.of(souvenirs).sum();
        if(sum%3!=0||n<3) return false;
        boolean[][] sums = new boolean[sum+1][sum+1];
        sums[0][0] = true;
        for (int souvenir : souvenirs) {
            for (int j = 0; j < sum; j++) {
                for (int k = 0; k < sum; k++) {
                    if (sums[j][k]&&j+souvenir<=sum&&k+souvenir<=sum) {
                        sums[j + souvenir][k] = true;
                        sums[j][k + souvenir] = true;
                    }
                }
            }
        }
        return sums[sum/3][sum/3];
    }
}
