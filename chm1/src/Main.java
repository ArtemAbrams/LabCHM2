public class Main
{
    static double [][] S;
    static double [][] D;
    static double[][] A;
    static double [][] multiplications;
    public static void TransMatrix()
    {
        int n  = A.length;
        double[][] result = new double [n][n];
        for(int i = 0 ; i < n; i++)
        {
            for (int j =0; j < n; j++)
            {
                result[i][j] = S[j][i];
            }
        }
        S = result;
    }
    public static void Multiplications()
    {
        double sum =0;
        int n = A.length;
        multiplications = new double[n][n];
        for ( int i =0 ; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                sum = 0;
                for(int k =0; k<n; k++)
                {
                   sum+=S[i][k]*D[k][j];
                   multiplications[i][j] = sum;
                }
            }
        }
    }
    public static void valueOfDMatrix(int index)
    {
       double d = A[index][index];
       for (int j =0; j < index; j++)
       {
           d -= D[j][j] * Math.pow(S[j][index] ,2);
       }
       if(d > 0)
           d = 1;
       else if (d == 0)
           d =0;
       else
           d=-1;
       D[index][index] = (int) d;
    }
    public static double[] YResults(double[] b)
    {
        int n = b.length;
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            double sum = 0.0;
            for (int j = 0; j < i; j++) {
                sum += S[i][j] * result[j];
            }
            result[i] = (b[i] - sum) / S[i][i];
        }
        return result;
    }
    public static double[] XResults(double[] y)
    {
        int n = y.length;
        double[] result = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += S[j][i] * result[j];
            }
            result[i] = (y[i] - sum) / S[i][i];
        }
        return result;
    }
    public static void TriangleMatrix(double[][] R)
    {
        A = R;
        int n = A.length;
        S = new double[n][n];
        D = new double[n][n];
        double valueS;
        for(int i =0 ; i < n ; i++)
        {
            valueOfDMatrix(i);
           for(int j =i; j < n; j++)
           {
               valueS = A[i][j];
              for (int k =0; k < i; k++)
              {
                  if( i == j)
                  {
                      valueS -= D[k][k] * S[k][i] * S[k][i];
                  }
                  else
                  {
                      valueS -= S[k][i]*S[k][j] * D[k][k];
                  }
              }
              if(i==j)
              {
                  valueS= Math.sqrt(Math.abs(valueS));
              }
              else
              {
                  valueS = valueS / (D[i][i] * S[i][i]);
              }
              S[i][j] = valueS;
           }
        }
    }

    public static double det()
    {
        double result=1;
        for (int i =0; i < A.length; i++)
        {
            result*=D[i][i]*Math.pow(S[i][i], 2);
        }
        return  result;
    }
    public static void display(double[][] F)
    {
        for (int i =0; i < A.length; i++)
        {
            for (int j =0; j < A.length; j++)
            {
                System.out.print(F[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args)
    {
        double[][] A = {{4 , 3, 2,1},
                {3,6,4,2},
                {2,4,6,3},
                {1,2,3,4}
        };
        double[] b = {3, 6, 4,7};
        TriangleMatrix(A);
        System.out.println("Matrix S");
        display(S);
        System.out.println("\nMatrix D");
        display(D);
        TransMatrix();
        System.out.println("\nTransposed matrix S ");
        display(S);
        Multiplications();
        System.out.println("\nTransposed matrix S * matrix D");
        display(multiplications);
        System.out.println("\nу value");
        double [] y = YResults(b);
        for (int i =0; i < y.length; i++)
        {
            System.out.println("y["+(i+1) +"]=" + y[i] + "   ");
        }
        System.out.println("\n=================================================\n");
        System.out.println("\n Result\n");
        double [] x = XResults(y);
        for (int i =0; i < x.length; i++)
        {
            System.out.println("x["+(i+1) +"]=" + x[i] + "   ");
        }
    }
}