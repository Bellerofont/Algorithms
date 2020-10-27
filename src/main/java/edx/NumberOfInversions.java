package edx;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Scanner;

public class NumberOfInversions {
    private static int numberOfInversions = 0;

    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        URL resource = BinarySearch.class.getClassLoader().getResource("edx/data/4_4_inversions.in");
        assert resource != null;
        File input = Paths.get(resource.toURI()).toFile();
        Scanner scanner = new Scanner(input);
        int n = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        long start = System.nanoTime();
        System.out.println(numberOfInversions(array, 0, n - 1));
        long end = System.nanoTime();
        System.out.println("run time: " + (end - start) / 1000000 + "ms");
    }

    private static int numberOfInversions(int[] array, int left, int right) {
        if (right > left) {
            int middle = (left + right) / 2;
            numberOfInversions(array, left, middle);
            numberOfInversions(array, middle + 1, right);
            numberOfInversions += merge(array, left, middle, right);
        }
        return numberOfInversions;
    }

    private static int merge(int[] array, int left, int middle, int right) {
        int count = 0;
        int n1 = middle - left + 1;
        int n2 = right - middle;
        int[] leftHalf = new int[n1];
        int[] rightHalf = new int[n2];
        System.arraycopy(array, left, leftHalf, 0, n1);
        System.arraycopy(array, middle + 1, rightHalf, 0, n2);
        int i = 0;
        int j = 0;
        int pos = left;
        while (i < n1 && j < n2) {
            if (leftHalf[i] <= rightHalf[j]) {
                array[pos] = leftHalf[i];
                i++;
            } else {
                array[pos] = rightHalf[j];
                j++;
                count += n1 - i;
            }
            pos++;
        }
        while (i < n1) {
            array[pos] = leftHalf[i];
            i++;
            pos++;
        }
        while (j < n2) {
            array[pos] = rightHalf[j];
            j++;
            pos++;
        }
        return count;
    }
}
