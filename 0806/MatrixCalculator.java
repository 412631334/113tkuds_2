
public class MatrixCalculator {

    // 矩陣加法
    public static int[][] add(int[][] A, int[][] B) {
        int rows = A.length;
        int cols = A[0].length;
        int[][] C = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    // 矩陣乘法
    public static int[][] multiply(int[][] A, int[][] B) {
        int rows = A.length;
        int cols = B[0].length;
        int common = B.length;
        int[][] C = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int sum = 0;
                for (int k = 0; k < common; k++) {
                    sum += A[i][k] * B[k][j];
                }
                C[i][j] = sum;
            }
        }
        return C;
    }

    // 矩陣轉置
    public static int[][] transpose(int[][] A) {
        int rows = A.length;
        int cols = A[0].length;
        int[][] T = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                T[j][i] = A[i][j];
            }
        }
        return T;
    }

    // 尋找最大值和最小值
    public static int[] findMaxMin(int[][] A) {
        int max = A[0][0];
        int min = A[0][0];
        for (int[] row : A) {
            for (int val : row) {
                if (val > max) {
                    max = val;
                }
                if (val < min) {
                    min = val;
                }
            }
        }
        return new int[]{max, min};
    }

    // 測試
    public static void main(String[] args) {
        int[][] A = {{1, 2, 3}, {4, 5, 6}};
        int[][] B = {{7, 8, 9}, {10, 11, 12}};

        int[][] sum = add(A, B);
        int[][] mul = multiply(new int[][]{{1, 2}, {3, 4}}, new int[][]{{5, 6, 7}, {8, 9, 10}});
        int[][] trans = transpose(A);
        int[] maxmin = findMaxMin(A);

        System.out.println("矩陣加法結果：");
        printMatrix(sum);

        System.out.println("矩陣乘法結果：");
        printMatrix(mul);

        System.out.println("矩陣轉置結果：");
        printMatrix(trans);

        System.out.println("最大值：" + maxmin[0] + "，最小值：" + maxmin[1]);
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }
}
