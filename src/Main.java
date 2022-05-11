public class Main {
    public static void main(String[] args) {
        int cols = 3;
        int rows = 20;

        var possibleStates = PentominoEncoder.generateBoard(cols, rows);

        DLX dlx = new DLX(cols*rows+12, possibleStates);
        dlx.run();
        System.out.println(dlx.getNumberOfSolutions());
    }
}
