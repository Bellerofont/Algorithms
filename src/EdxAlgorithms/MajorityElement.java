package EdxAlgorithms;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class MajorityElement {

    public static void main(String[] args) throws IOException {
        File input = new File("4_2_majority_element.in");
        Scanner scanner = new Scanner(input);
        int n = scanner.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
        list.add(scanner.nextInt());
        }
        long start = System.nanoTime();
        System.out.println(majorityElement( n, list));
        long end = System.nanoTime();
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
}
