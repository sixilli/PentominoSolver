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
                            ArrayList<Integer> newEncoding = generateEncoding(maxWidth, maxHeight, row, col, rotation);
                            validPositions.add(newEncoding);
                        }
                    }
                }
            }
        }

        return validPositions;
    }

    static private ArrayList<Integer> generateEncoding(int maxWidth, int maxHeight, int row, int col, int[] rotation) {
        // Creating a 2d ArrayList to represent the shape placed, and fill with 0s
        ArrayList<ArrayList<Integer>> shapeEncoding = new ArrayList<>(maxHeight);
        for (int i = 0; i < maxHeight; i++) {
            ArrayList<Integer> newArr = new ArrayList<>(Collections.nCopies(maxWidth, 0));
            shapeEncoding.add(newArr);
        }

        // Apply shape to 2D array, loc (row, col) is assumed to be valid.
        shapeEncoding.get(row).set(col, 1);
        for (int i = 0; i < rotation.length / 2; i++) {
            int xCoord = col + rotation[i * 2];
            int yCoord = row + rotation[i * 2 + 1];

            shapeEncoding.get(yCoord).set(xCoord, 1);
        }

        // Collect all arrays into a 1D array
        ArrayList<Integer> result = new ArrayList<>(Collections.nCopies(maxHeight * maxWidth, 0));

        // Flatten 2D array
        int index = 0;
        for (int iRow = 0; iRow < shapeEncoding.size(); iRow++) {
            for (int jCol = 0; jCol < shapeEncoding.get(iRow).size(); jCol++) {
                int indexVal = shapeEncoding.get(iRow).get(jCol);
                result.set(index, indexVal);
                index++;
            }
        }

        ArrayList<Integer> result2 = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            int num = result.get(i);
            if (num == 1) {
                result2.add(i);
            }
        }
        //System.out.println(result2);

        return result2;
    }
}