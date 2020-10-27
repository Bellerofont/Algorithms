package codesignal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MedianScores {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new MedianScores().medianScores(new int[]{98, 91, 70, 26, 75, 91, 30, 88, 86})));
    }

    int[] medianScores(int[] scores) {
        List<Integer> sortedList = new ArrayList<>();
        int[] medians = new int[scores.length];
        sortedList.add(scores[0]);
        medians[0] = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if(scores[i]>sortedList.get(sortedList.size()-1)){
                sortedList.add(scores[i]);
            }
            else {
                sortedList.add(binarySearch(0,sortedList.size()-1, sortedList, scores[i]), scores[i]);
            }
            if(i%2==0){
                medians[i] = sortedList.get(i/2);
            }
            else{
                medians[i] = (int) Math.round(((double)sortedList.get(i/2)+sortedList.get(i/2+1))/2);
            }
        }
        return medians;
    }

    private static int binarySearch(int left, int right, List<Integer> sequence, int key) {
        if (left>right){
            return left;
        }
        int mid = (left+right)/2;
        if (sequence.get(mid)==key){
            return mid;
        }
        else if (sequence.get(mid)>key){
            return binarySearch(left, mid-1,sequence, key);
        }
        else {
            return binarySearch(mid+1,right,sequence,key);
        }
    }

}
