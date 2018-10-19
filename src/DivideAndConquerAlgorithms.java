import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        long start = System.nanoTime();
        System.out.println(binarySearch(n, sequence, k, keys));
        long end = System.nanoTime();
        System.out.println("run time: " + (end-start)/1000000 + "ms");

        File input2 = new File("4_2_majority_element.in");
        scanner = new Scanner(input2);
        n = scanner.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
        list.add(scanner.nextInt());
        }
        start = System.nanoTime();
        System.out.println(majorityElement( n, list));
        end = System.nanoTime();
        System.out.println("run time: " + (end-start)/1000000 + "ms");
    }


    private static int majorityElement(int n, List<Integer> list) {
        if(list.size()==0){
            return -1;
        }
        if(list.size()==1){
            return list.get(0);
        }
        List<Integer> processedList = new ArrayList<>();
        if(n%2!=0){
            if(!list.get(n-1).equals(list.get(n-2))&&!list.get(n-1).equals(list.get(n-3))){
                n--;
                list.remove(n);
            }
            else{
                list.add(list.get(n-1));
                n++;
            }
        }
        for (int i = 0; i < n; i+=2) {
            if(list.get(i).equals(list.get(i+1))){
                processedList.add(list.get(i));
            }
        }
        return majorityElement(processedList.size(),processedList);
    }

    private static int binarySearch(int n, int[] sequence, int k, int[] keys) {
        int hits = 0;
        int [] indexes = new int[k];
        for (int i = 0; i < k; i++) {
            indexes[i] = binarySearch(0, n, sequence, keys[i]);
            if(indexes[i]!=-1){
                hits++;
            }
        }
        return hits;
    }

    private static int binarySearch(int i, int n, int[] sequence, int key) {
        if (i>n){
            return -1;
        }
        int mid = (i+n)/2;
        if (sequence[mid]==key){
            return mid;
        }
        else if (sequence[mid]>key){
            return binarySearch(i, mid-1,sequence, key);
        }
        else {
            return binarySearch(mid+1,n,sequence,key);
        }
    }
}
