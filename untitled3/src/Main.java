import static java.lang.Math.abs;

public class Main {
    final static double EPSILON = 1e-4;
    static public class Vector {
        public double x1;
        public double x2;
        public double x3;
        public double x4;

        public Vector(double x1, double x2, double x3, double x4) {
            this.x1 = x1;
            this.x2 = x2;
            this.x3 = x3;
            this.x4 = x4;
        }

        public static Vector createNextIterationVector(Vector x0) {
            double val1 = 0.143 * (-3 * x0.x2 - 2 * x0.x3 - x0.x4 + 3);
            double val2 = 0.1 * (-3 * x0.x1 - 4 * x0.x3 - 2 * x0.x4 + 6);
            double val3 = 0.1 * (-2 * x0.x1 - 4 * x0.x2 - 3 * x0.x4 + 4);
            double val4 = 0.143 * (-x0.x1 - 2 * x0.x2 - 3 * x0.x3 + 7);
            return new Vector(val1, val2, val3, val4);
        }

        public static Vector subtraction(Vector x1, Vector x0) {
            return new Vector(
                    x1.x1 - x0.x1,
                    x1.x2 - x0.x2,
                    x1.x3 - x0.x3,
                    x1.x4 - x0.x4
            );
        }
        public static double norm(Vector subtracted) {
            return Math.max(Math.max(abs(subtracted.x1), abs(subtracted.x2)),
                    Math.max(abs(subtracted.x3), abs(subtracted.x4)));
        }

    }

    public static void main(String[] args)
    {
        System.out.println("Знайти наближено розв'язок системи методом Якобі, поданої у матричному вигляді:\n" +
                "7 3  2  1 | 3\n" +
                "3 10 4  2 | 6\n" +
                "2 4  10 3 | 4\n" +
                "1 2  3  7 | 7\n" +
                "з точністю eps = " + EPSILON);
        Vector x0 = new Vector(0, 0, 0, 0);
        Vector x1;
        Vector x0dummy;
        Vector subtracted;
        int iterationNumber = 0;
        System.out.println();
        do {
            x1 = Vector.createNextIterationVector(x0);
            x0dummy = x0;
            x0 = x1;
            subtracted = Vector.subtraction(x1, x0dummy);
            iterationNumber++;
            System.out.println("Номер ітерації: " + iterationNumber + " X: x1: "
                    + x1.x1 + " x2: " + x1.x2 + " x3: " + x1.x3 + " x4: " + x1.x4
                    + " |X("+iterationNumber+") - X("+(iterationNumber-1)+")|: " + Vector.norm(subtracted));
        } while (Vector.norm(subtracted) > EPSILON);

        System.out.println();
        System.out.println("Результат обчислення:");
        System.out.println("x1 = " + x1.x1);
        System.out.println("x2 = " + x1.x2);
        System.out.println("x3 = " + x1.x3);
        System.out.println("x4 = " + x1.x4);
    }
}