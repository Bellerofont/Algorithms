package EdxAlgorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Lottery {

    public static void main(String[] args) throws FileNotFoundException {
        File input5 = new File("4_5_lottery.in");
        Scanner scanner = new Scanner(input5);
        int s = scanner.nextInt();
        int p = scanner.nextInt();
        int[] segmentStarts = new int[s];
        int[] segmentEnds = new int[s];
        for (int i = 0; i < s; i++) {
            segmentStarts[i] = scanner.nextInt();
            segmentEnds[i] = scanner.nextInt();
        }
        int[] points = new int[p];
        for (int i = 0; i < p; i++) {
            points[i] = scanner.nextInt();
        }
        long start = System.nanoTime();
        System.out.println(lottery(s, p, segmentStarts, segmentEnds, points));
        long end = System.nanoTime();
        System.out.println("run time: " + (end-start)/1000000 + "ms");
    }


    private static long lottery(int s, int p, int[] segmentStarts, int[] segmentEnds, int[] points) {
        long sum = 0;
        Arrays.sort(segmentStarts);
        Arrays.sort(segmentEnds);
        for (int i = 0; i < p; i++) {
            if(points[i]>segmentEnds[s-1]||points[i]<segmentStarts[0]) continue;
            int lowerBound = binarySearch(0,s-1, segmentEnds,points[i]-1);
            while(lowerBound<s-1&&segmentEnds[lowerBound]<points[i]){
                lowerBound++;
            }
            int upperBound = binarySearch(0,s-1, segmentStarts, points[i]+1)-1;
            while(upperBound>0&&segmentStarts[upperBound]>points[i]){
                upperBound--;
            }
            sum+=upperBound-lowerBound+1;
        }
        return sum;
    }

    private static int binarySearch(int left, int right, int[] sequence, int key) {
        if (left>right){
            return left;
        }
        int mid = (left+right)/2;
        if (sequence[mid]==key){
            return mid;
        }
        else if (sequence[mid]>key){
            return binarySearch(left, mid-1, sequence, key);
        }
        else {
            return binarySearch(mid+1, right, sequence, key);
        }
    }
}
