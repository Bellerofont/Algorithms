import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DivideAndConquerAlgorithms {

    public static void main(String[] args) throws IOException {
        File input  = new File("4_1_binary_search.in");
        Scanner scanner = new Scanner(input);
        int n = scanner.nextInt();
        int [] sequence = new int[n];
        for (int i = 0; i < n; i++) {
            sequence[i] = scanner.nextInt();
        }
        int k = scanner.nextInt();
        int [] keys = new int[k];
        for (int i = 0; i < k; i++) {
            keys[i] = scanner.nextInt();
        }
        System.out.println(binarySearch(n, sequence, k, keys));
    }

    private static int binarySearch(int n, int[] sequence, int k, int[] keys) {
        int hits = 0;
        int [] indexes = new int[k];
        for (int i = 0; i < k; i++) {
            indexes[i] = binSearch(0, n, sequence, keys[i]);
            if(indexes[i]!=-1){
                hits++;
            }
        }
        return hits;
    }

    private static int binSearch(int i, int n, int[] sequence, int key) {
        if (i>n){
            return -1;
        }
        int mid = (i+n)/2;
        if (sequence[mid]==key){
            return mid;
        }
        else if (sequence[mid]>key){
            return binSearch(i, mid-1,sequence, key);
        }
        else {
            return binSearch(mid+1,n,sequence,key);
        }
    }
}
