package edx;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class EditingDistance {

    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        URL resource = BinarySearch.class.getClassLoader().getResource("edx/data/5_3_edit_distance.in");
        assert resource != null;
        File input = Paths.get(resource.toURI()).toFile();
        Scanner scanner = new Scanner(input);
        String string1 = scanner.nextLine();
        String string2 = scanner.nextLine();
        long start = System.nanoTime();
        System.out.println(editingDistance(string1, string2));
        long end = System.nanoTime();
        System.out.println("run time: " + (end - start) / 1000000 + "ms");
    }

    private static int editingDistance(String string1, String string2) {
        int[][] distance = new int[string1.length() + 1][string2.length() + 1];
        int i;
        int j;
        for (i = 0; i < distance.length; i++) {
            distance[i][0] = i;
        }
        for (i = 0; i < distance[0].length; i++) {
            distance[0][i] = i;
        }

        for (j = 1; j < distance[0].length; j++) {
            for (i = 1; i < distance.length; i++) {
                int insert = distance[i][j - 1] + 1;
                int delete = distance[i - 1][j] + 1;
                int match = distance[i - 1][j - 1];
                int mismatch = distance[i - 1][j - 1] + 1;
                if (string1.charAt(i - 1) == string2.charAt(j - 1)) {
                    distance[i][j] = Math.min(insert, Math.min(delete, match));
                } else {
                    distance[i][j] = Math.min(insert, Math.min(delete, mismatch));
                }
            }
        }
        System.out.println(Arrays.deepToString(distance));
        return distance[string1.length()][string2.length()];
    }
}
