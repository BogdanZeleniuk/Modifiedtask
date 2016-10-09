package model;


import java.util.ArrayList;

/**
 This is an object 'brick' with the help of that we will build our wall
 */
public class Brick {
    public static int generalCountOfBricks;
    public static int[][] brickMatrix;
    public static ArrayList<String[]> basedBrickList;
    public static ArrayList<String[]> sortedBrickListByHeight = new ArrayList<>();

    public Brick() {
    }

    public static int getBrickWidthFromData(String[] brick) {
        return Integer.parseInt(brick[0]);
    }

    public static int getBrickHeightFromData(String[] brick) {
        return Integer.parseInt(brick[1]);
    }

    public static int getBrickCountFromData(String[] brick) {
        return Integer.parseInt(brick[2]);
    }
}



