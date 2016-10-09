package service;


import model.ConsoleReader;
import model.FileReader;

import java.util.ArrayList;

import static model.Brick.basedBrickList;
import static model.Brick.generalCountOfBricks;
import static model.Wall.height;
import static model.Wall.matrixOfWall;
import static model.Wall.width;

/**
 Object with the help of that we put getting information to our objects: build matrix for Wall
 and for every Bricks with giving width/height, get the count of bricks etc.
 */

public class InputService {

    private final ArrayList<String> dataInfo =
            ConsoleReader.dataList.size() > 0 ? ConsoleReader.dataList : FileReader.dataList;

    public void initDataTable() {

        if (dataInfo == null || dataInfo.isEmpty() || dataInfo.size() == 0) {
            throw new NullPointerException("There are no data in String[] args or File.");
        }

        else if (dataInfo.get(0).length() != 3){
            throw new IllegalStateException("Wrong parameters in line \"Wall parameters\". Size !=3");
        }

        width = Integer.parseInt(dataInfo.get(0).trim().substring(0, 1));
        height = Integer.parseInt(dataInfo.get(0).trim().substring(2));
        ArrayList<String> wallMatrix = new ArrayList<>(dataInfo);

        wallMatrix.remove(0);
        int numberOfRow = wallMatrix.size() - 1;
        while (numberOfRow != height - 1) {
            wallMatrix.remove(numberOfRow);
            numberOfRow--;
        }
        wallMatrix.trimToSize();

        for (String currentRow : wallMatrix) {
            if (currentRow.length() != width) {
                throw new IllegalStateException("Wrong parameters in line \"Wall parameters\". " +
                        "Height or width more/less than parameters of wall");
            }
        }

        int rowCount = wallMatrix.size();
        int columnCount = wallMatrix.get(0).length();
        matrixOfWall = new int[rowCount][columnCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0, k = columnCount - 1; j < columnCount; j++, k--) {
                String line = wallMatrix.get(i);
                String currentRow = line.substring(j, line.length()-k);
                matrixOfWall[i][j] = Integer.parseInt(currentRow);
            }
        }
        if (dataInfo.get(height+1).trim().length() != 1){
            throw new IllegalStateException("Wrong parameters in line \"Count of block sorts\"." +
                    "Count should have one parameter");
        }

        generalCountOfBricks = Integer.parseInt(dataInfo.get(height + 1).trim().substring(0, 1));

        basedBrickList = new ArrayList<>();
        for (int i = 0; i < generalCountOfBricks; i++) {
            String[] currentBrick = dataInfo.get(height + 2 + i).trim().split(" ");
            if (currentBrick.length != 3){
                throw new IndexOutOfBoundsException("Wrong count of elements in one of parts in bricks");
            }
            basedBrickList.add(currentBrick);
        }

        int countOfStringInFileOrConsole = 1 + height + 1 + generalCountOfBricks;
        if (dataInfo.size() != countOfStringInFileOrConsole) {
            throw new IllegalStateException("Wrong parameters in line.");
        }
    }
}
