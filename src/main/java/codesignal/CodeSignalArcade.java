package codesignal;

import java.util.*;

public class CodeSignalArcade {

    static boolean almostIncreasingSequence(int[] sequence) {
        int count = 0;
        int l = 0;
        for (int i = 0; i < sequence.length - 1; i++) {
            int first = sequence[i];
            int second = sequence[i + 1];
            if (first >= second) {
                count++;
                if (count > 1) return false;
                l = i;
            }
        }
        System.out.println("count " + count + " l " + l + " sequence length " + sequence.length);
        if (count == 0 || l == 0) {
            return true;
        } else if(l==sequence.length-2){
            return true;
        }
        return sequence[l - 1] < sequence[l+1] || sequence[l] < sequence[l + 2];
    }

    private static int matrixElementsSum(int[][] matrix) {
        int sum = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            for (int[] aMatrix : matrix) {
                if (aMatrix[i] == 0) break;
                sum += aMatrix[i];
            }
        }
        return sum;
    }

    int commonCharacterCount(String s1, String s2) {
        int count = 0;
        Map<Character,Integer> map1 = new HashMap<>();
        Map<Character,Integer> map2 = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            map1.merge(s1.charAt(i),1,Integer::sum);
        }
        for (int i = 0; i < s2.length(); i++) {
            map2.merge(s2.charAt(i),1,Integer::sum);
        }
        Set<Character> commonKeys = new HashSet<>(map1.keySet());
        commonKeys.retainAll(map2.keySet());
        for(Character character: commonKeys){
            count+=Math.min(map1.get(character),map2.get(character));
        }
        return count;
    }

    boolean isLucky(int n) {
        int numberLength = String.valueOf(n).length();
        String half1 = Integer.toString(n).substring(0,numberLength/2);
        String half2 = Integer.toString(n).substring(numberLength/2);
        return sumOfDigits(half1)==sumOfDigits(half2);
    }

    private int sumOfDigits(String string) {
        int sum = 0;
        for (Character digit: string.toCharArray()) {
            sum+=digit;
        }
        return sum;
    }

    static int[] sortByHeight(int[] a) {
        List<Integer> people = new ArrayList<>();
        for (int n : a) {
            if (n != -1){
                people.add(n);
            }
        }
        Collections.sort(people);
        int index = 0;
        for (int i = 0; i < a.length; i++) {
            if(a[i]!=-1){
                a[i] = people.get(index++);
            }
        }
        return a;
    }

    static String reverseParentheses(String s) {
        while(s.contains("(")){
            int start = s.lastIndexOf("(");
            int end = s.substring(start).indexOf(")");
            String reverse = s.substring(start,start+end+1);
            reverse = new StringBuilder(reverse).reverse().toString();
            reverse = reverse.replaceAll("[()]","");
            s = s.substring(0,start).concat(reverse).concat(s.substring(start+end+1));
        }
        return s;
    }

    private static int arrayChange(int[] inputArray) {
        int count = 0;
        int mismatch;
        for(int i = 1; i<inputArray.length; i++){
            if (inputArray[i]<=inputArray[i-1]){
                mismatch = inputArray[i-1]-inputArray[i]+1;
                count+= mismatch;
                inputArray[i] += mismatch;
            }
        }
        return count;
    }

    boolean palindromeRearranging(String inputString) {
        if(inputString.length()==1) return true;
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < inputString.length(); i++) {
            map.merge(inputString.charAt(i),1,Integer::sum);
        }
        int odds = 0;
        for(Integer i: map.values()){
            if(i%2==1){
                odds++;
            }
        }
        return odds<=1;
    }

    int[][] boxBlur(int[][] image) {
        int [][] result = new int[image.length-2][image[0].length-2];
        for (int i = 1; i < image.length-1; i++) {
            for (int j = 1; j < image[0].length-1; j++) {
                result[i-1][j-1] = (image[i-1][j-1]+image[i-1][j]+image[i-1][j+1]+
                        image[i][j-1]+image[i][j]+image[i][j+1]+
                        image[i+1][j-1]+image[i+1][j]+image[i+1][j+1])/9;
            }
        }
        return result;
    }

    int[][] minesweeper(boolean[][] matrix) {
        int[][] temp = new int[matrix.length+2][matrix.length+2];
        int[][] result = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                temp[i+1][j+1] = matrix[i][j]?1:0;
                System.out.println(temp[i+1][j+1]);
            }
        }
        for (int i = 1; i < temp.length-1; i++) {
            for (int j = 1; j < temp[0].length-1; j++) {
                result[i-1][j-1] = temp[i-1][j-1]+temp[i-1][j]+temp[i-1][j+1]+
                        temp[i][j-1]+temp[i][j]+temp[i][j+1]+
                        temp[i+1][j-1]+temp[i+1][j]+temp[i+1][j+1];
            }
        }
        return result;
    }

    String[] addBorder(String[] picture) {
        int depth = picture.length;
        int width = picture[0].length();
        String[] r = new String[depth + 2];
        for (int i = 1; i < depth + 1; i++) {
            r[i] = "*" + picture[i - 1] + "*";
        }
        r[0] = "";
        r[depth + 1] = "";
        for (int i = 0; i < width + 2; i++) {
            r[0] += "*";
            r[depth + 1] += "*";
        }
        return r;
    }

    int[] arrayReplace(int[] inputArray, int elemToReplace, int substitutionElem) {
        return Arrays.stream(inputArray).map(e -> e==elemToReplace ? substitutionElem : e).toArray();
    }

    boolean evenDigitsOnly(int n) {
        String[] s =Arrays.stream(String.valueOf(n).split("")).filter(e -> Integer.parseInt(e)%2==0).toArray(String[]::new);
        System.out.println(Arrays.toString(s));
        return s.length==String.valueOf(n).length();
    }

    static int depositProfit(int deposit, int rate, int threshold) {
        int years = 0;
        double value = deposit;
        while (value<threshold){
            years++;
            value*=1+rate/100.0;
        }
        return years;
    }

    static boolean stringsRearrangement(String[] inputArray) {
        Arrays.sort(inputArray);
        System.out.println(Arrays.toString(inputArray));
        return false;
    }

    public static void main(String[] args) {

    }
}
