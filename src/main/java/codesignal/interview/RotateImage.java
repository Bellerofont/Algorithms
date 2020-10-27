package codesignal.interview;

public class RotateImage {

    int[][] rotateImage(int[][] a) {
        int n = a.length-1;
        for (int i = 0; i < (n+1) / 2; i++) {
            for (int j = i; j < n-i; j++) {
                int temp = a[i][j];
                a[i][j] = a[n-j][i];
                a[n-j][i] = a[n-i][n-j];
                a[n-i][n-j] = a[j][n-i];
                a[j][n-i] = temp;
            }
        }
        return a;
    }
}
