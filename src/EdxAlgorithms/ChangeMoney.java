package EdxAlgorithms;

public class ChangeMoney {

    public static void main(String[] args) {
        long start = System.nanoTime();
        System.out.println(changeMoneyDP(950));
        long end = System.nanoTime();
        System.out.println("run time: " + (end-start)/1000000 + "ms");
    }

    private static int changeMoneyDP(int n) {
        int[] coins = {1,3,4};
        int[] minCoins = new int[n+1];
        minCoins[0] = 0;
        for (int i = 1; i <= n; i++) {
            minCoins[i] = Integer.MAX_VALUE;
            for (int c : coins) {
                if (i>=c){
                    int numCoins = minCoins[i-c]+1;
                    if(numCoins<minCoins[i]){
                        minCoins[i]=numCoins;
                    }
                }
            }
        }
        return minCoins[n];
    }
}
