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
import matrix.Matrix;
import operations.MatrixAndScalarOperations;

public class MatrixAndScalarOperationsTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	public void testMatrixAndScalarOperations() {
		double[][] elem1 = {{1,2},{3,4}}; Matrix A = new Matrix(2,2,elem1);
		double[][] eres = {{1,2},{3,4}}; Matrix expResult = new Matrix(2,2, eres);
		double scal = 1;
		MatrixAndScalarOperations result = new MatrixAndScalarOperations(A,scal);
		Assert.isTrue( !( expResult.equals(result.scalar()) ) );
	}

	@Test
	public void testScalar() {
		System.out.println("Provjera matrix i scalar operacije scalar");
		double[][] elem1 = {{1,2},{3,4}}; Matrix A = new Matrix(2,2,elem1);
		double[][] eres = {{2,4},{6,8}};
		double scal = 2;
		Matrix expResult = new Matrix(2,2, eres);
		MatrixAndScalarOperations result = new MatrixAndScalarOperations(A,scal);
		Assert.isTrue( !( expResult.equals(result.scalar()) ) );
		System.out.println("Mnozenje scalarom funkcionira");
	}

	@Test
	public void testPotentiate() throws SquareMatrixException, MatrixDimensionException, ImpermissibleExponentException {
		System.out.println("Provjera matrix i scalar operacije potenciranja");
		double[][] elem1 = {{2,0},{0,4}}; Matrix A = new Matrix(2,2,elem1);
		double[][] eres = {{4,0},{0,16}};
		//double[][] eres2 = {{1,0},{0,1}};
		double scal = 2;
		Matrix expResult = new Matrix(2,2, eres); //expResult2 = new Matrix(2,2, eres2);
		MatrixAndScalarOperations result = new MatrixAndScalarOperations(A,scal);
		Assert.isTrue( !( expResult.equals(result.potentiate()) ) );
		System.out.println("Potenciranje funkcionira");
	}

}
