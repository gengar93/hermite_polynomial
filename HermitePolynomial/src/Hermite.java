public class Hermite {
    private int index;
    private Polynomial hermite;
    private Matrix H1;

//    static {
//        int n = 11;
//        double[][] M2xArray = new double[n][n];
//        for (int i = 1; i < n; i++) {
//            M2xArray[i][i-1] = 2;
//        }
//        Matrix M2x = new Matrix(M2xArray);
//        //System.out.println(M2x);
//
//        // Building the <differentiate with x> matrix
//        double[][] DArray = new double[n][n];
//        for (int i= 1; i < n; i++) {
//            DArray[i-1][i] = i;
//        }
//        Matrix D = new Matrix(DArray);
//        //System.out.println(D);
//
//        // Building the <next hermite polynomial> matrix
//        this.H1 = M2x.add(D.multiplyWithScalar(-1));
//        //System.out.println(H1);
//
//        System.out.println("Next hermite polynomial matrix upto degree 10 has been computed in static block.");
//    }

    public Hermite(int index) {
        int n = 15;

        if (index < 0 || index > n) {
            throw new IllegalArgumentException("Hermite polynomials index range should be between 0 and "+(n-1));
        }
        this.index = index;

        double[][] M2xArray = new double[n][n];
        for (int i = 1; i < n; i++) {
            M2xArray[i][i-1] = 2;
        }
        Matrix M2x = new Matrix(M2xArray);

        // Building the <differentiate with x> matrix
        double[][] DArray = new double[n][n];
        for (int i= 1; i < n; i++) {
            DArray[i-1][i] = i;
        }
        Matrix D = new Matrix(DArray);

        // Building the <next hermite polynomial> matrix
        this.H1 = M2x.add(D.multiplyWithScalar(-1));

        double[] coeff = new double[n];
        coeff[0] = 1;
        Polynomial h = new Polynomial(coeff);
        System.out.println("Hermite 0");
        System.out.println(h);
        System.out.println();

        for (int i = 1; i <= index; i++) {
            h = H1.multiply(h);
            System.out.println("Hermite " + i);
            System.out.println(h);
            System.out.println();
        }

        this.hermite = h;

    }
}
