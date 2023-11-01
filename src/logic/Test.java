package logic;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[][] in1 = {
                {1, 2},
                {3, 4},
        };
        int[][] out1 = {
                {2, 3},
                {4, 5},
        };
        Solution.incrementEach(in1);
        if (Arrays.deepEquals(in1, out1)) {
            System.out.println("OK");
        }
        else {
            System.out.println("FAIL");
        }
    }
}
