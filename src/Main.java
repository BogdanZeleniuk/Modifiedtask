import model.ConsoleReader;
import service.InputService;
import model.FileReader;
import service.WallService;
import model.Brick;

import static service.WallService.sortBricksByHeight;

/**
 Check our test assignment.
 */
public class Main {
    public static void main(String[] args) {

        FileReader fileReader = new FileReader();
        fileReader.readInformation();
        if (FileReader.dataList.isEmpty()){
            ConsoleReader consoleReader = new ConsoleReader();
            consoleReader.readInformation();
        }

        InputService inputService = new InputService();
            inputService.initDataTable();
        sortBricksByHeight(Brick.basedBrickList);

        WallService wallService = new WallService();
        wallService.putBrickIntoWall();
        if (wallService.checkWall()){
            System.out.println("YES");
        }
        else
            System.out.println("NO");
    }

}
