import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PentominoEncoder {
    static ArrayList<ArrayList<Integer>> generateEncodings(int maxHeight, int maxWidth) {
        ArrayList<ArrayList<Integer>> validPositions = new ArrayList<>();

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
                            ArrayList<Integer> newEncoding = generateEncodingForPosition(shape, maxWidth, row, col, rotation);
                            validPositions.add(newEncoding);
                        }
                    }
                }
            }
        }

        return validPositions;
    }

    static private ArrayList<Integer> generateEncodingForPosition(int shape, int maxWidth, int row, int col, int[] rotation) {
        ArrayList<Integer> encoding = new ArrayList<>(6);

        encoding.add(shape);
        encoding.add(row * maxWidth + col + 12);
        for (int i = 0; i < rotation.length / 2; i++) {
            int xCoord = col + rotation[i * 2];
            int yCoord = row + rotation[i * 2 + 1];
            int numToInsert = yCoord * maxWidth + xCoord + 12;

            encoding.add(numToInsert);
        }

        return encoding;
    }
}