package test_operations;

//import static org.junit.Assert.*;

import org.eclipse.core.runtime.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.ImpermissibleExponentException;
import exceptions.MatrixDimensionException;
import exceptions.SquareMatrixException;
import exceptions.MatrixIsSingularException;
import matrix.Matrix;
//import operations.MatrixAndScalarOperations;
import operations.OtherOperations;

public class OtherOperationsTest {

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
	public void testCalculatePolynomialValue() throws SquareMatrixException, MatrixDimensionException, ImpermissibleExponentException {
		System.out.println("Provjera unarne operacije racunanja polinoma matrice");
		double[][] elem1 = {{1,2},{3,4}}; Matrix A = new Matrix(2,2,elem1);
		double[][] eres = {{3,4},{6,9}};
		double[] polinom = {1,2};
		Matrix expResult = new Matrix(2,2, eres);
		OtherOperations result = new OtherOperations(A,polinom);
		Matrix r = result.calculatePolynomialValue();
		for( int i = 0; i < 2; i++ ) {
			for( int j = 0; j < 2; j++ ) {
				Assert.isTrue( expResult.elements[i][j] == r.elements[i][j] );
			}
		}
		System.out.println("Racunanje polinoma matrice funkcionira");
	}

	@Test
	public void testSolveLinearSystem() throws MatrixDimensionException, InterruptedException, SquareMatrixException, MatrixIsSingularException {
		System.out.println("Provjera unarne operacije rjesavanja lin sustava");
		double[][] elem1 = {{1,2},{0,4}}; Matrix A = new Matrix(2,2,elem1);
		double[] polinom = {1,8};
		double[] expResult = {-3,2};
		OtherOperations result = new OtherOperations(A,polinom);
		double[] r = result.solveLinearSystem();
		for( int i = 0; i < 2; i++ )
		{
			Assert.isTrue( expResult[i] == r[i] );
		}
		System.out.println("Rjesavanje lin sustava proslo");
	}

}
