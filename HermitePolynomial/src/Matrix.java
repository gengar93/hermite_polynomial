public class Matrix {
    private double[][] matrixArray;
    public final int numberOfRows;
    public final int numberOfColumns;

    public Matrix(double[][] inputArray) {
        if (!arrayIsMatrix(inputArray)) {
            throw new IllegalArgumentException("All the columns of the array don't have the same number of elements.");
        }
        else {
            this.matrixArray = inputArray;
            this.numberOfRows = inputArray.length;
            this.numberOfColumns = inputArray[0].length;
        }
    }

    /***
     * Checks if the two dimensional array can be a matrix or not.
     * Since two dimensional arrays are arrays of arrays, it is possible that all arrays in this array
     * are not of identical length. Such 2d arrays cannot be made into matrices. This method checks that
     * the 2d arrays is proper rectangular grid of numbers.
     * @param arr (double[][])
     * @return (boolean) return true if it can be made into a matrix and false otherwise
     */
    private boolean arrayIsMatrix(double[][] arr) {
        int l0 = arr[0].length; // length of the first column
        // check if all column lengths match
        for (double[] column : arr) {
            if (column.length != l0) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        int l = lenOfLongestNumber(matrixArray);
        StringBuilder s = new StringBuilder();
        //s.append("|| ");
        for (double[] aA : matrixArray) {
            s.append("|| ");
            for (int j = 0; j < aA.length - 1; j++) {
                s.append(String.format("%" + l + ".2f", aA[j])).append("   ");
            }
            s.append(String.format("%" + l + ".2f", aA[aA.length - 1])).append(" ");
            s.append("||\n");
        }
        return s.toString();
    }

    /***
     * Takes a matrix B as parameter and returns a new matrix which is the sum of B and A (the orignial matrix).
     * B must have the same dimensionality of A.
     * @param B (Matrix): A matrix (required to have same dimensionality of A)
     * @return (Matrix)A new matrix A + B
     */
    public Matrix add(Matrix B) {
        if (this.numberOfRows != B.numberOfRows || this.numberOfColumns != B.numberOfColumns) {
            throw new IllegalArgumentException("The dimensionality of both matrices must match.");
        }
        double[][] BArray = B.getMatrixArray();
        int m = matrixArray.length;
        int n = matrixArray[0].length;
        double[][] sumArray = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sumArray[i][j] = matrixArray[i][j] + BArray[i][j];
            }
        }
        return new Matrix(sumArray);
    }

    /***
     * A static method which takes two matrices A and B as input and returns their sum.
     * It is required that the dimensionality of A and B are the same.
     * @param A (Matrix)
     * @param B (Matrix)
     * @return (Matrix) returns the sum of A and B
     */
    public static Matrix add(Matrix A, Matrix B) {
        if (A.numberOfRows != B.numberOfRows || A.numberOfColumns != B.numberOfColumns) {
            throw new IllegalArgumentException("The dimensionality of both matrices must match.");
        }
        double[][] AArray = A.getMatrixArray();
        double[][] BArray = B.getMatrixArray();
        int m = AArray.length;
        int n = AArray[0].length;
        double[][] sumArray = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sumArray[i][j] = A.getElementAt(i + 1, j + 1) + B.getElementAt(i + 1, j + 1);
            }
        }
        return new Matrix(sumArray);
    }

    /***
     * A.multiply(B) returns a new matrix which is the product of A and B.
     * The number of columns in A should be equal to the number of rows in B.
     * @param B (Matrix): A matrix (required that number of rows in B = number of columns in A)
     * @return (Matrix) A new matrix which is the product A*B
     */
    public Matrix multiply(Matrix B) {
        if (this.numberOfColumns != B.numberOfRows) {
            throw new IllegalArgumentException("Number of columns of A must be equal to number of rows of B.");
        }
        double[][] BArray = B.getMatrixArray();
        int m = matrixArray.length;
        int n = BArray[0].length;
        double[][] productArray = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                double sum = 0;
                for (int k = 0; k < matrixArray[0].length; k++) {
                    sum += matrixArray[i][k] * BArray[k][j];
                }
                productArray[i][j] = sum;
            }
        }
        return new Matrix(productArray);
    }

    /***
     * An overloded method which is used to multiply a matrix and a polynomial and returns a polynomial
     * @param polynomial
     * @return
     */
    public Polynomial multiply(Polynomial polynomial) {
        return new Polynomial(this.multiply(polynomial.getCoefficients()));
    }

    /***
     * A.multiplyWithScalar(k) returns a new matrix where all the elements of A are scaled by k.
     * @param k (double)
     * @return (Matrix) returns a matrix which is scaled by factor k
     */
    public Matrix multiplyWithScalar(double k) {
        int m = matrixArray.length;
        int n = matrixArray[0].length;
        double[][] scaledArray = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                scaledArray[i][j] = k * matrixArray[i][j];
            }
        }
        return new Matrix(scaledArray);
    }

    /***
     * This private method is used to find the length of longest integer portion among all
     * the elements of the matrix. It is required to determine what space is to be allocated to print
     * the elements of the matrix by the toString() method.
     * @param matrix (Matrix)
     * @return (int)
     */
    private int lenOfLongestNumber(double[][] matrix) {
        double greatest = 0;
        for (double[] aMatrix : matrix) {
            for (double entry : aMatrix) {
                if (entry > greatest) {
                    greatest = entry;
                }
            }
        }

        return String.valueOf((int) greatest).length() + 3;
    }

    /***
     * Getter method that returns the two dimensional array that holds the matrix elements.
     * @return (double[][])
     */
    public double[][] getMatrixArray() {
        return matrixArray;
    }

    /***
     * Given the row number i and the column number j, returns at the element at (i, j).
     * Indexing starts from 1 for both i and j.
     * @param i (int) the row index
     * @param j (int) the column index
     * @return (double) the element at (i, j)
     */
    public double getElementAt(int i, int j) {
        if (i < 1 || i > numberOfRows || j < 1 || j > numberOfColumns) {
            throw new IllegalArgumentException("Number of rows is between 1 and " + numberOfRows +
            " Number of columns is between 1 and " + numberOfColumns);
        }
        return this.matrixArray[i - 1][j - 1];
    }
}


