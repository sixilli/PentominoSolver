import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int cols = 20;
        int rows = 3;

        ArrayList<ArrayList<Integer>> testMatrix = new ArrayList<>(rows);

        for (int i = 0; i < rows; i++) {
            ArrayList<Integer> newArr = new ArrayList<>(cols);
            testMatrix.add(newArr);
            for (int j = 0; j < cols; j++) {
                testMatrix.get(i).add(1);
            }
        }

        var possibleStates = PentominoEncoder.generateBoard(testMatrix);

        DLX dlx = new DLX(rows * cols, possibleStates);
        //DLX dlx = new DLX(possibleStates.size(), possibleStates);
        dlx.run();
    }
}
