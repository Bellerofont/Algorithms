package EdxAlgorithms;

public class PrimitiveCalculator {

    public static void main(String[] args) {
        long start = System.nanoTime();
        System.out.println(calculate(98733));
        long end = System.nanoTime();
        System.out.println("run time: " + (end-start)/1000000 + "ms");
    }

    private static int calculate(int n) {
        int[] result = new int[n+1];
        result[0] = 0;
        for (int i = 1; i <=n ; i++) {
            if (i%3==0){
                result[i] = 3;
            }
            else if(i%2==0){
                result[i] = 2;
            }
            else {
                result[i] = 1;
            }
        }
        int steps = 0;
        while(n!=1){
            if(result[n]==3) n/=3;
            else if(result[n]==2) n/=2;
            else n--;
            steps++;
            System.out.println(n);
        }
        return steps;
    }
}
