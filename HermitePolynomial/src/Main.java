public class Main {

    public static void main(String[] args) {
	    // write your code here
        double angleInDegrees = 30;
        double angleInRadians = angleInDegrees * 355.0/113/180;
        double c = Math.cos(angleInRadians);
        double s = Math.sin(angleInRadians);

        double[][] rotationMatrixArray = {{c, -s}, {s, c}};
        Matrix rotationMatrix = new Matrix(rotationMatrixArray);

        double[] vectorArray = {0, 1};
        Polynomial vector = new Polynomial(vectorArray);

        Polynomial rotatedVector = rotationMatrix.multiply(vector);

        System.out.println(rotatedVector);

        Hermite h1 = new Hermite(15);
    }
}