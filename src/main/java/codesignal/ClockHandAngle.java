package codesignal;

import java.util.Arrays;

public class ClockHandAngle {

    double clockHandAngle(String time) {
        int[] i = Arrays.stream(time.split(":")).mapToInt(Integer::parseInt).toArray();
        double h = i[0] * 30.0 + i[1] * 0.5 + i[2] * 0.5 / 60;
        double m = i[1] * 6 + i[2] * 0.1;
        double angle = Math.max(h, m) - Math.min(h, m);
        if (Math.abs(h - m) < 180) {
            return angle % 360;
        } else return 360 - angle;
    }

    public static void main(String[] args) {
        System.out.println(new ClockHandAngle().clockHandAngle("1:42:01"));
    }
}
