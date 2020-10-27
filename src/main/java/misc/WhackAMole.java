package misc;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class WhackAMole {
    private int score;
    private int molesLeft;
    private int attemptsLeft;
    private char[][] moleGrid;

    private WhackAMole(int numAttempts, int gridDimension) {
        attemptsLeft = numAttempts;
        moleGrid = new char[gridDimension][gridDimension];
        for (char[] c : moleGrid) {
            Arrays.fill(c, '*');
        }
    }

    private boolean place(int x, int y) {
        if (moleGrid[x][y] == '*') {
            moleGrid[x][y] = 'M';
            molesLeft++;
            return true;
        } else {
            return false;
        }
    }

    private void whack(int x, int y) {
        if (x == -1 || y == -1) {
            attemptsLeft = 0;
            return;
        }
        if (moleGrid[x][y] == 'M') {
            moleGrid[x][y] = 'W';
            molesLeft--;
        }
        attemptsLeft--;
    }

    private void printGridToUser() {
        for (char[] aMoleGrid : moleGrid) {
            for (int j = 0; j < moleGrid.length; j++) {
                if (aMoleGrid[j] == 'M') {
                    System.out.print('*');
                } else {
                    System.out.print(aMoleGrid[j]);
                }
            }
            System.out.println();
        }
    }

    private void printGrid() {
        for (char[] aMoleGrid : moleGrid) {
            for (int j = 0; j < moleGrid.length; j++) {
                System.out.print(aMoleGrid[j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        WhackAMole game = new WhackAMole(50, 10);
        System.out.println(1);
        game.molesLeft = 0;
        game.score = 0;
        for (int i = 0; i < 10; i++) {
            int x = ThreadLocalRandom.current().nextInt(0, 10);
            int y = ThreadLocalRandom.current().nextInt(0, 10);
            if (!game.place(x, y)) {
                i--;
            }
        }
        System.out.println(2);
        Scanner input = new Scanner(System.in);
        System.out.println("Let the game begin");
        System.out.println("You have 50 attempts");
        while (game.attemptsLeft > 0) {
            System.out.println("Enter your move. \"-1 -1\" to give up");
            game.whack(input.nextInt(), input.nextInt());
            game.printGridToUser();
            System.out.println(game.attemptsLeft);
        }
        game.printGrid();
        System.out.println(game.score);


    }
}
