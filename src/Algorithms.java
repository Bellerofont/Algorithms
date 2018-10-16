import java.util.*;

public class Algorithms {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //TODO: replace those stubs with tests
        /*
        System.out.println(changeMoney(997));
        */

        /*
        int n = scanner.nextInt();
        int w = scanner.nextInt();
        double [][] items = new double[n][2];
        for (int i = 0; i < n; i++) {
            items[i][0] = scanner.nextInt();
            items[i][1] = scanner.nextInt();
        }
        System.out.println(maxLoot(n, w,  items));
        */

        /*
        int n = scanner.nextInt();
        long [] profits = new long[n];
        long [] clicks = new long[n];
        for (int i = 0; i < n; i++) {
            profits[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            clicks[i] = scanner.nextInt();
        }
        System.out.println(maxProfit(n,profits,clicks));
        */
        
        /*
        int n = scanner.nextInt();
        int [][] segments = new int[n][2];
        for (int i = 0; i < n; i++) {
            segments[i][0] = scanner.nextInt();
            segments[i][1] = scanner.nextInt();
        }
        System.out.println(minPoints(n, segments));
        */

        /*
        int n = scanner.nextInt();
        System.out.println(maxPrizes(n));
        */

        /*
        int n = scanner.nextInt();
        List <Integer> pieces = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            pieces.add(scanner.nextInt());
        }
        System.out.println(maxSalary(n,pieces));
        */
    }

    private static String maxSalary(int n, List<Integer> pieces) {
        StringBuilder answer = new StringBuilder();
        while (!pieces.isEmpty()) {
            int maxNumber = 0;
            for (int i: pieces){
                if(IsGreaterOrEqual(i,maxNumber)){
                    maxNumber = i;
                }
            }
            answer.append(maxNumber);
            pieces.remove(Integer.valueOf(maxNumber));
        }
        return answer.toString();
    }

    private static boolean IsGreaterOrEqual(int i, int maxNumber) {
        String one = String.valueOf(i) + String.valueOf(maxNumber);
        String two = String.valueOf(maxNumber) + String.valueOf(i);
        return (Integer.parseInt(one) >= Integer.parseInt(two));
    }

    private static int maxPrizes(int n) {
        int k = 0;
        int remaining = n;
        for (int i = 1; i <= n; i++) {
            k++;
            remaining-=i;
            if(remaining<=i){
                break;
            }
        }
        return k;
    }

    private static int minPoints(int n, int[][] segments) {
        Arrays.sort(segments, Comparator.comparingInt(o -> o[1]));
        List<Integer> points = new ArrayList<>();
        int totalPoints = 0;
        int endpoint = 0;
        for (int i = 0; i < n; i++) {
            if(segments[i][0]>endpoint) {
                totalPoints++;
                points.add(endpoint);
                endpoint = segments[i][1];
            }
        }
        points.remove(0);
        //TODO: return both totalPoints and points list
        return totalPoints;
    }

    private static long maxProfit(int n, long[] profits, long[] clicks) {
        Arrays.sort(profits);
        Arrays.sort(clicks);
        System.out.println(Arrays.toString(profits));
        System.out.println(Arrays.toString(clicks));
        long maxProfit = 0;
        for (int i = 0; i < n; i++) {
            maxProfit+=profits[i]*clicks[i];
        }
        return maxProfit;
    }

    private static double maxLoot(int n, int w, double[][] items) {
        double totalValue = 0;
        Arrays.sort(items, Comparator.comparingDouble(o -> o[0]/o[1]));
        System.out.println(Arrays.deepToString(items));
        for (int i = n-1; i >= 0 ; i--) {
            if (w==0){
                return totalValue;
            }
            if(items[i][1]<w){
                w-=items[i][1];
                totalValue+=items[i][0];
            }
            else{
                totalValue+=items[i][0]/items[i][1]*w;
                w=0;
            }
        }
        return totalValue;
    }


    private static int changeMoney(int i) {
        return i/10+i%10/5+i%5;
    }

}
