package edx;

import java.io.*;
import java.util.*;

public class QuickSort3 {
    private static Random random = new Random();

    private static int[] partition3(int[] a, int l, int r) {
        int x = a[l];
        int lowEnd = l;
        int highStart = l + 1;
        for (int i = l + 1; i <= r; i++) {
            if (a[i] < x) {
                lowEnd++;
                highStart++;
                int t = a[i];
                a[i] = a[lowEnd];
                a[lowEnd] = t;
            } else if (a[i] == x) {
                int t = a[i];
                a[i] = a[highStart];
                a[highStart] = t;
                highStart++;
            }
        }
        int t = a[l];
        a[l] = a[lowEnd];
        a[lowEnd] = t;
        return new int[]{lowEnd, highStart};
    }
    private static void randomizedQuickSort(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int k = random.nextInt(r - l + 1) + l;
        int t = a[l];
        a[l] = a[k];
        a[k] = t;
        int[] m = partition3(a, l, r);
        randomizedQuickSort(a, l, m[0]-1);
        randomizedQuickSort(a, m[1], r);
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        randomizedQuickSort(a, 0, n - 1);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

