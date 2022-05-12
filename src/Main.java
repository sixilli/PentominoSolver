import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        var testDimensions = new ArrayList<ArrayList<Integer>>();
        testDimensions.add(new ArrayList<>(Arrays.asList(3, 20)));
        testDimensions.add(new ArrayList<>(Arrays.asList(4, 15)));
        testDimensions.add(new ArrayList<>(Arrays.asList(5, 12)));
        testDimensions.add(new ArrayList<>(Arrays.asList(6, 10)));


        for (var test : testDimensions) {
            int cols = test.get(0);
            int rows = test.get(1);
            int coverDimensions = cols*rows+12;

            var encodings = PentominoEncoder.generateEncodings(cols, rows);

            System.out.println("Running test on board: " + cols + " x " + rows);
            DLX dlx = new DLX(coverDimensions, encodings);
            dlx.run();
            System.out.println(dlx.getNumberOfSolutions() + "\n");
        }
    }
}
