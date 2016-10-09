package service;

import model.Brick;
import model.Wall;

import java.util.ArrayList;

import static model.Brick.brickMatrix;
import static model.Brick.getBrickHeightFromData;
import static model.Brick.sortedBrickListByHeight;
import static model.Wall.matrixOfWall;

/**
 Object that helps sort blocks (we will put at first the biggest blocks because if there are few free places
 we will can not use their), put blocks into wall and check, if there are places with count '1'.
 */

public class WallService {
    private static int wallSizeInPixel;
    private ArrayList<String[]> sortedBrickList = sortedBrickListByHeight;
    private ArrayList<String[]> neededBricksList = new ArrayList<>();

    private static int[][] brickListToMatrix(String[] brick){

        brickMatrix = new int[getBrickHeightFromData(brick)][Brick.getBrickWidthFromData(brick)];

        for(int i = 0; i< getBrickHeightFromData(brick); i++){
            for (int j=0; j<Brick.getBrickWidthFromData(brick); j++){
                brickMatrix[i][j]=1;
            }
        }
        return brickMatrix;
    }

    public static void sortBricksByHeight(ArrayList<String[]> brickList) {

        String[] brickWithMaxHeight = null;

        for (int i = 0; i < brickList.size(); i++) {
            for (String[] aBrickList : brickList) {
                if (getBrickHeightFromData(aBrickList) >= getBrickHeightFromData(brickList.get(i))) {
                    if (!sortedBrickListByHeight.contains(aBrickList)) {
                        brickWithMaxHeight = aBrickList;
                    }
                } else if (sortedBrickListByHeight.contains(brickList.get(i))
                        && !sortedBrickListByHeight.contains(aBrickList)) {
                    brickWithMaxHeight = aBrickList;
                }
            }
            if (!sortedBrickListByHeight.contains(brickWithMaxHeight)) {
                sortedBrickListByHeight.add(brickWithMaxHeight);
            }
        }
    }

    public void putBrickIntoWall() {
        int brickHeight;
        for (String[] brick : sortedBrickList) {
            brickHeight = getBrickHeightFromData(brick);
            if (brickHeight <= Wall.height) {
                neededBricksList.add(brick);
            }
        }
        neededBricksList.trimToSize();

        for (int[] aMatrixOfWall : matrixOfWall) {
            for (int anAMatrixOfWall : aMatrixOfWall) {
                if (anAMatrixOfWall == 1) {
                    wallSizeInPixel++;
                }
            }
        }

        for (String[] brick : neededBricksList) {

            int brickCount = Brick.getBrickCountFromData(brick);
            int[][] currentBrick = brickListToMatrix(brick);

            while (brickCount > 0) {
                outerRow:
                for (int i = 0; i <= Wall.height - currentBrick.length; i++) {
                    outerCol:
                    for (int j = 0; j <= Wall.width - currentBrick[0].length; j++) {
                        for (int k = 0; k < currentBrick.length; k++) {
                            for (int m = 0; m < currentBrick[k].length; m++) {
                                if (matrixOfWall[i + k][j + m] != currentBrick[k][m]) {
                                    continue outerCol;
                                }
                            }
                        }
                        break outerRow;
                    }
                }
                brickCount--;
            }
        }
    }
    public boolean checkWall(){
        return wallSizeInPixel == 0;
    }
}

