/***
 * Create a Polynomial object. Input to the constructor can be
 * a double array or a column matrix of type Matrix.
 */

public class Polynomial {
    private Matrix coefficients;
    private int degree;

    public Polynomial(double[] coefficients) {
        int len = coefficients.length;
        double[][] coeffArray2D = new double[len][1];
        for (int i = 0; i < len; i++) {
            coeffArray2D[i][0] = coefficients[i];
        }
        this.coefficients = new Matrix(coeffArray2D);
        this.degree = len - 1;
    }

    public Polynomial(Matrix coefficients) {
        for (double[] row : coefficients.getMatrixArray()) {
            if (row.length != 1) {
                throw new IllegalArgumentException("The matrix must consist only of a single column.");
            }
        }
        this.coefficients = coefficients;
        this.degree = coefficients.numberOfRows - 1;
    }


    public Matrix getCoefficients() {
        return coefficients;
    }

    public int getDegree() {
        return degree;
    }

//    @Override
//    public String toString() {
//        StringBuilder s = new StringBuilder("( ");
//        for (int i = 0; i < degree; i ++) {
//            s.append(String.format("%.2f", this.coefficients.getElementAt(i+1, 1))).append(", ");
//        }
//        s.append(String.format("%.2f", this.coefficients.getElementAt(degree+1, 1))).append(")");
//        return s.toString();


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        boolean flag = true; // if at least one term other than the constant term, the flag is set to false
        for (int i = coefficients.numberOfRows; i > 2; i--) {
            double element = coefficients.getElementAt(i, 1);
            if (element == 0) {
                continue;
            } else if (element > 0) {
                s.append(" + ");
            } else {
                s.append(" - ");
            }
            s.append(String.format("%.2f", Math.abs(element))).append(" x^").append(i - 1);
            flag = false;
        }

        // printing the x coefficient
        if (degree > 1) {
            double xTerm = this.coefficients.getElementAt(2, 1);
            if (xTerm > 0) {
                s.append(String.format(" + %.2f x", xTerm));
                flag = false;
            } else if (xTerm < 0) {
                s.append(String.format(" - %.2f x", xTerm));
                flag = false;
            }
        }

        // printing the x^0 coefficient
        double constantTerm = this.coefficients.getElementAt(1, 1);
        if (constantTerm > 0)
            s.append(String.format(" + %.2f", constantTerm));
        else if (constantTerm < 0)
            s.append(String.format(" - %.2f", constantTerm));
        else if (flag)
            s.append(String.format("%.2f", 0.0));
        return s.toString();
    }
}