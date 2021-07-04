package test_operations;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.eclipse.core.runtime.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.MatrixDimensionException;
import exceptions.SquareMatrixException;
import matrix.Matrix;
import operations.MatrixAndScalarOperations;
import operations.UnaryOperations;

public class UnaryOperationsTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//Matrix r = result.inverse();
		for( int i = 0; i < 2; i++ ) {
			for( int j = 0; j < 2; j++ ) {
				System.out.println(" = ");
				//Assert.isTrue( expResult.elements[i][j] == r.elements[i][j] );
			}
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLUDecomposition() throws MatrixDimensionException, InterruptedException {
		System.out.println("Provjera unarne operacije LU Dekompozicije");
		double[][] elem1 = {{1,2},{4,3}}; Matrix A = new Matrix(2,2,elem1);
		double[][] expL = {{1,0},{0.25,1}}, expU = {{4,3},{0,1.25}};
		double[][] exppiv = {{1,0}};
		ArrayList<Matrix> expResult = new ArrayList<Matrix>();
		expResult.add( new Matrix(1,2,exppiv));expResult.add( new Matrix(2,2,expL));
		expResult.add( new Matrix(2,2,expU));
		UnaryOperations result = new UnaryOperations(A);
		ArrayList<Matrix> LU = result.LUDecomposition();	
		for(int k = 1; k < 3; k++)
		{
			Matrix Ex = expResult.get(k); Matrix r = LU.get(k);
			System.out.println("Ex: "+Ex); System.out.println("r: "+r);
			for( int i = 0; i < 2; i++)
			{
				for( int j = 0; j < 2; j++)
				{
					System.out.println(Ex.elements[i][j] + "==" + r.elements[i][j]);
					Assert.isTrue( Ex.elements[i][j] == r.elements[i][j] );
				}
			}
		}
		System.out.println("LUDecomposition funkcionira");
	}

	@Test
	public void testTrace() throws SquareMatrixException {
		System.out.println("Provjera unarne operacije traga");
		double[][] elem1 = {{1,2},{3,4}}; Matrix A = new Matrix(2,2,elem1);
		//double[][] eres = {{2,4},{6,8}};
		//Matrix expResultL = new Matrix(2,2, eres);
		UnaryOperations result = new UnaryOperations(A);
		double expResult = 5.0;
		System.out.println("trag= "+result.trace());
		Assert.isTrue( ( expResult == result.trace() ) );
		System.out.println("Trace funkcionira");
	}

	@Test
	public void testRank() {
		System.out.println("Provjera unarne operacije Rank");
		double[][] elem1 = {{1,2},{3,4}}; Matrix A = new Matrix(2,2,elem1);
		//double[][] eres = {{2,4},{6,8}};
		//Matrix expResult = new Matrix(2,2, eres);
		double expResult = 2;
		UnaryOperations result = new UnaryOperations(A);
		try {
			System.out.println(result.rank());
			Assert.isTrue( ( expResult == result.rank() ) );
		} catch (MatrixDimensionException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Rank funkcionira");
	}

	@Test
	public void testDeterminant() {
		System.out.println("Provjera unarne operacije determinante");
		double[][] elem1 = {{1,2},{3,4}}; Matrix A = new Matrix(2,2,elem1);
		//double[][] eres = {{2,4},{6,8}};
		//Matrix expResultL = new Matrix(2,2, eres);
		UnaryOperations result = new UnaryOperations(A);
		double expResult = -2;
		try {
			System.out.println("det=" + result.determinant() );
			Assert.isTrue( ( expResult == result.determinant() ) );
		} catch (SquareMatrixException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Trace funkcionira");
	}

	@Test
	public void testTranspose() {
		System.out.println("Provjera unarne operacije LU Dekompozicije");
		double[][] elem1 = {{1,2},{3,4}}; Matrix A = new Matrix(2,2,elem1);
		double[][] elem23 = {{1,2,3},{3,4,5}}; Matrix B = new Matrix(2,3,elem23);
		double[][] eres = {{1,3},{2,4}}, eres2 = {{1,3},{2,4},{3,5}};
		Matrix expResult = new Matrix(2,2, eres), expResult2 = new Matrix(3,2,eres2);
		UnaryOperations result = new UnaryOperations(A), result2 = new UnaryOperations(B);
		Matrix r1 = result.transpose();
		Matrix r2 = result2.transpose();
		for( int i = 0; i < 2; i++ ) {
			for( int j = 0; j < 3; j++ ) {
				if(j<2)
					Assert.isTrue( expResult.elements[i][j] == r1.elements[i][j] );
				Assert.isTrue(expResult2.elements[j][i] == r2.elements[j][i]);
			}
		}
		System.out.println("Transponiranje funkcionira");
	}

	@Test
	public void testInverse() {
		System.out.println("Provjera unarne operacije LU Dekompozicije");
		double[][] elem1 = {{1,1},{1,0}}; Matrix A = new Matrix(2,2,elem1);
		double[][] eres = {{0,1},{1,-1}};
		Matrix expResult = new Matrix(2,2, eres);
		UnaryOperations result = new UnaryOperations(A);
		try {
			Matrix r = result.inverse();
			for( int i = 0; i < 2; i++ ) {
				for( int j = 0; j < 2; j++ ) {
					//System.out.println(expResult.elements[i][j]+" = "+r.elements[i][j]);
					Assert.isTrue( expResult.elements[i][j] == r.elements[i][j] );
				}
			}			
		} catch (SquareMatrixException | MatrixDimensionException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Inverz funkcionira");
	}

}
