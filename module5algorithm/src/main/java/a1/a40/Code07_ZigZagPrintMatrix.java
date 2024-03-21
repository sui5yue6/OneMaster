package a1.a40;

/**
 * @author Eirk
 * @Description
 * @Date 2024/3/21 22:47
 */
public class Code07_ZigZagPrintMatrix {

    public static void printMatrixZigZag(int[][] matrix) {
        int tR = 0;
        int tC = 0;
        int dR = 0;
        int dC = 0;
        int endR = matrix.length - 1;
        int endC = matrix[0].length - 1;
        boolean fromUp = false;
        while (tR != endR + 1) {
            printLevel(matrix, tR, tC, dR, dC, fromUp);
            tR = tC == endC ? tR + 1 : tR;
            tC = tC == endC ? tC : tC + 1;
            dC = dR == endR ? dC + 1 : dC;
            dR = dR == endR ? dR : dR + 1;
            fromUp = !fromUp;
        }
        System.out.println();
    }

    public static void printLevel(int[][] m, int tR, int tC, int dR, int dC, boolean f) {
        if (f) {
            while (tR != dR + 1) {
                System.out.print(m[tR++][tC--] + " ");
            }
        } else {
            while (dR != tR - 1) {
                System.out.print(m[dR--][dC++] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        printMatrixZigZag(matrix);

    }


    public static void f(int[][] matrix) {
        int a = matrix.length;
        int b = matrix[0].length;
        // 0,0    0,0   // 先上，后下
        // 1,0    0,1
        // 2,0    0,2
        // 加入 x没有到底，y到底了。
        // 3,0    1,2    就同步增加x
        // 加入 x也到底了
        // 3,1    2,2   无脑增加y就对了

        int x1=0;
        int y1=0;
        int x2=0;
        int y2=0;



        // 右上就是++，左下就是--
    }


}
