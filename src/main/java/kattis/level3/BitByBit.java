package kattis.level3;

import java.util.Arrays;
import java.util.Scanner;

public class BitByBit {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        while (n!=0){
            int[] register = new int[32];
            for (int i = 0; i < 32; i++) {
                register[i] = -1;
            }
            in.nextLine();
            for (int i = 0; i < n; i++) {
                String line = in.nextLine();
                String[] command = line.split(" ");
                System.out.println(Arrays.toString(command));
                switch (command[0]){
                    case "SET": {
                        register[Integer.parseInt(command[1])] = 1;
                        continue;
                    }
                    case "CLEAR": {
                        register[Integer.parseInt(command[1])] = 0;
                        continue;
                    }
                    case "AND": {
                        if(register[Integer.parseInt(command[1])]==-1||register[Integer.parseInt(command[2])]==-1){
                            register[Integer.parseInt(command[1])]= -1;
                        }
                        else register[Integer.parseInt(command[1])] &= register[Integer.parseInt(command[2])];
                        continue;
                    }
                    case "OR": {
                        if(register[Integer.parseInt(command[1])]==1||register[Integer.parseInt(command[2])]==1){
                            register[Integer.parseInt(command[1])] = 1;
                        } else if(register[Integer.parseInt(command[2])]==-1){
                            continue;
                        }
                        else register[Integer.parseInt(command[1])] = register[Integer.parseInt(command[2])];
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 31; i>=0; i--){
                if(register[i]==-1) sb.append("?");
                else sb.append(register[i]);
            }
            System.out.println(sb.toString());
            n = in.nextInt();
        }
    }
}
