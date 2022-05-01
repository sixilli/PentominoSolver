import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PentominoEncoder {
    static ArrayList<ArrayList<Integer>> generateBoard(ArrayList<ArrayList<Integer>> newBoard) {
        ArrayList<ArrayList<Integer>> validPositions = new ArrayList<>();

        int maxHeight = newBoard.size();
        int maxWidth = newBoard.get(0).size();

        for (int row = 0; row < maxHeight; row++) {
            for (int col = 0; col < maxWidth; col++) {
                for (int shape = 0; shape < Shapes.shapes.length; shape++) {
                    for (int[] rotation : Shapes.shapes[shape]) {
                        boolean validRotation = true;

                        // Test if all points in a given rotation is valid
                        for (int locPair = 0; locPair < rotation.length / 2; locPair++) {
                            int x = rotation[locPair * 2];
                            int y = rotation[locPair * 2 + 1];
                            int testX = x + col;
                            int testY = y + row;

                            boolean isValidX = (testX < maxWidth && testX >= 0);
                            boolean isValidY = (testY < maxHeight && testY >= 0);

                            // If a point in a rotation is not valid move to the next rotation
                            if (!isValidX || !isValidY) {
                                validRotation = false;
                                break;
                            }
                        }

                        // If rotation is valid create an encoding and add it
                        if (validRotation) {
                            ArrayList<Integer> newEncoding = generateEncoding(shape, maxWidth, maxHeight, row, col, rotation);
                            validPositions.add(newEncoding);
                        }
                    }
                }
            }
        }

        return validPositions;
    }

    static private ArrayList<Integer> generateEncoding(int shape, int maxWidth, int maxHeight, int row, int col, int[] rotation) {
        // Creating a 2d ArrayList to represent the shape placed, and fill with 0s
        ArrayList<ArrayList<Integer>> shapeEncoding = new ArrayList<>(maxHeight);
        for (int i = 0; i < maxHeight; i++) {
            ArrayList<Integer> newArr = new ArrayList<>(maxWidth);
            for (int j = 0; j < maxWidth; j++) {
                newArr.add(0);
            }
            shapeEncoding.add(newArr);
        }

        // Apply shape to 2D array
        for (int i = 0; i < rotation.length / 2; i++) {
            int xCoord = col + rotation[i * 2];
            int yCoord = row + rotation[i * 2 + 1];

            shapeEncoding.get(yCoord).set(xCoord, 1);
        }

        // Collect all arrays into a 1D array
        int listLength = maxHeight * maxWidth + Shapes.shapes.length;
        ArrayList<Integer> result = new ArrayList<>(Collections.nCopies(listLength, 0));

        // Set shape
        result.set(shape, 1);

        int step = Shapes.shapes.length;
        for (int i = 0; i < shapeEncoding.size(); i++) {
            for (int j = 0; j < shapeEncoding.get(i).size(); j++) {
                if (shapeEncoding.get(i).get(j) == 1) {
                    result.set(step, 1);
                }
                step++;
            }
        }

        //for (var r : result) {
            //System.out.print(r + ",");
        //}
        //System.out.println("");

        return result;
    }
}