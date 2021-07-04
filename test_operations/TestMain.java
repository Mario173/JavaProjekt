package test_operations;

import matrix.Matrix;
import operations.BinaryOperationsTwoMatrices;
import operations.MatrixAndScalarOperations;
import operations.OtherOperations;
import operations.UnaryOperations;
import rowmultithread.AddRowsToRows;
import exceptions.SquareMatrixException;
import exceptions.MatrixDimensionException;
import exceptions.WrongInsertException;

public class TestMain {

	public static void main(String[] args) throws SquareMatrixException, MatrixDimensionException, InterruptedException {
		// TODO Auto-generated method stub
		int i=0, j=0;
		double[][] elem22 = new double[2][2];
		double[][] elem33 = new double[3][3];
		double[][] elem44 = new double[4][4];
		double[][] I2 = new double[2][2];
		double[][] I3 = new double[3][3];
		double[][] I4 = new double[4][4];
		double[][] elem23 = new double[2][3];
		int[] pol = {3,1};
		
		for( i = 0; i < 4; i++ )
		{
			// inic jedinicnih matrica
			if( i < 2 ) I2[i][i] = 1;
			if( i < 3 ) I3[i][i] = 1;
			I4[i][i] = 1;
			// init ostalih matr
			for( j = 0; j < 4; j++ )
			{
				if( i < 2 && j < 2 )
					elem22[i][j] = i+j;
				if( i < 3 && j < 3 )
					elem33[i][j] = i+2*j+3;
				if( i < 2 && j < 3 )
					elem23[i][j] = i*i+j+1;
				elem44[i][j] = (i+1)*(j+1);
			}
			//elem44[i][j-1] += 1*i; elem44[i][j-2] -= 0.5;
		}
		//elem33[2][2] = 11;
		elem44[0][0] = 11; elem44[1][1] = 13; elem44[2][2] = 17; elem44[3][3] = 19;
		// stvaranje matrica s poljima kao elemxy i In
		Matrix J2,J3,J4, A,B,C,D, R;
		J2 = new Matrix(2,2,I2); J3 = new Matrix(3,3,I3); J4 = new Matrix(4,4,I4);
		A = new Matrix(2,2,elem22); B = new Matrix(3,3,elem33); C = new Matrix(4,4,elem44);
		D = new Matrix(2,3,elem23);
		BinaryOperationsTwoMatrices Bin;
		MatrixAndScalarOperations MS;
		// OtherOperations Oo = new OtherOperations(A,pol);
		UnaryOperations Uo = new UnaryOperations(C);
		Bin = new BinaryOperationsTwoMatrices(A,D);
		//System.out.println("Umnozak matrice A i D = "+Bin.multiply());
		System.out.println("J2 = "+J2+"\n"+"J3 = "+J3+"\n"+"J4 = "+J4+"\n");
		System.out.println("A= "+A+"\n"+"B= "+B+"\n"+"C= "+C+"\n"+"D= "+D+"\n");
		// R = Oo.calculatePolynomialValue();
		// System.out.println("Polinomial {3,1} od A = "+R+"\n");
		double det = Uo.determinant();
		System.out.println("det od C: "+det); 
		Uo = new UnaryOperations(B);
		int r = Uo.rank(); System.out.println("Rank od B = "+r+"\n");
		
		// idemo napraviti za rj lin sistema 
		double[][] mat = {{4.0, 1.0, 3.0, 7.0},
				{8.0, 1.0, 1.0,5.0},
				{1.0,2.0,3.0,9.0},
				{1.0,0.0,9.0,-11.0}};
		Matrix linsys = new Matrix(4,4,mat);
		double[] vektor = {10.0 ,13.0, 14.0,20.0};
		OtherOperations Oo = new OtherOperations(linsys, vektor);
		double[] rjesenje = Oo.solveLinearSystem();
		for( double el : rjesenje) {
			System.out.println(el);
		}
		System.out.println("determinanta je "+ (new UnaryOperations(linsys)).determinant2() );
		//System.out.println("Rjesenje linearne jedn Ax=(1,2) je: "+rjesenje.toString());
		
		
		
 	}

}