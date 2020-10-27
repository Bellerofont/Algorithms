package edx;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Stream;

public class ClosestPoints {
    static double minDistance = Double.MAX_VALUE;

    static class Point{
        int x;
        int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        int getX() {
            return x;
        }

        int getY() {
            return y;
        }
    }

    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        URL resource = BinarySearch.class.getClassLoader().getResource("edx/data/4_6_closest.in");
        assert resource != null;
        File input = Paths.get(resource.toURI()).toFile();
        Scanner scanner = new Scanner(input);
        int n = scanner.nextInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(scanner.nextInt(),scanner.nextInt());
        }
        System.out.println(closestPoints(n, points));
    }

    private static double closestPoints(int n, Point[] points){
        Arrays.sort(points, Comparator.comparingInt(Point::getX));
        return closestPoints(0, n, points);
    }

    private static double closestPoints(int left, int right, Point[] points){
        if(left>right){
            return Double.MAX_VALUE;
        }
        int mid = (right-left)/2;
        double d1 = closestPoints(left, mid, points);
        double d2 = closestPoints(mid+1, right, points);
        double dmin = Math.min(d1,d2);
        return 0.0;
    }
}
