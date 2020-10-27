package kattis.level3;

import java.util.*;

public class PrizeNoOneCanWin {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int x = in.nextInt();
        long sum = 0;
        int count = 0;
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            list.add(in.nextInt());
        }
        Collections.sort(list);
        for(int w : list){
            sum+=w;
            if(sum>x) {
                break;
            }
            count++;
        }
        System.out.println(count);
    }
}
