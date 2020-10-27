package edx;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Scanner;

public class BinarySearch {

    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        URL resource = BinarySearch.class.getClassLoader().getResource("edx/data/4_1_binary_search.in");
        assert resource != null;
        File input = Paths.get(resource.toURI()).toFile();
        Scanner scanner = new Scanner(input);
        int n = scanner.nextInt();
        int[] sequence = new int[n];
        for (int i = 0; i < n; i++) {
            sequence[i] = scanner.nextInt();
        }
        int k = scanner.nextInt();
        int[] keys = new int[k];
        for (int i = 0; i < k; i++) {
            keys[i] = scanner.nextInt();
        }
        long start = System.nanoTime();
        System.out.println(binarySearch(n, sequence, k, keys));
        long end = System.nanoTime();
        System.out.println("run time: " + (end - start) / 1000000 + "ms");
    }

    private static int binarySearch(int n, int[] sequence, int k, int[] keys) {
        int hits = 0;
        int[] indexes = new int[k];
        for (int i = 0; i < k; i++) {
            indexes[i] = binarySearch(0, n, sequence, keys[i]);
            if (indexes[i] != -1) {
                hits++;
            }
        }
        return hits;
    }

    private static int binarySearch(int left, int right, int[] sequence, int key) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (sequence[mid] == key) {
            return mid;
        } else if (sequence[mid] > key) {
            return binarySearch(left, mid - 1, sequence, key);
        } else {
            return binarySearch(mid + 1, right, sequence, key);
        }
    }
}
