package test_operations;
import static org.junit.jupiter.api.Assertions.*;

import org.eclipse.core.runtime.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.MatrixDimensionException;

import org.junit.Assert.*;

import matrix.Matrix;
import operations.BinaryOperationsTwoMatrices;

/**
 * 
 */

/**
 * @author vmin
 *
 */
class BinaryOperationsTwoMatricesTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link operations.BinaryOperationsTwoMatrices#isEqual()}.
	 */
	@Test
	void testIsEqual() {
		System.out.println("Provjera isEqual");
		double[][] elem1 = {{1,2},{3,4}}; Matrix A = new Matrix(2,2,elem1);
		double[][] elem2 = {{0,0},{0,0}}; Matrix B = new Matrix(2,2,elem2);
		boolean expResult = true;
		BinaryOperationsTwoMatrices result = new BinaryOperationsTwoMatrices(A,B);
		// nama result.equals treba vratiti false a ako vrati true ne radi dobro
		assertNotEquals(expResult, result.equals(B));
		System.out.println("Equal funkcionira");
	}

	/**
	 * Test method for {@link operations.BinaryOperationsTwoMatrices#add()}.
	 * @throws MatrixDimensionException 
	 */
	@Test
	void testAdd() throws MatrixDimensionException {
		System.out.println("Provjera binarne operacije add");
		double[][] elem1 = {{1,2},{3,4}}; Matrix A = new Matrix(2,2,elem1);
		double[][] elem2 = {{0,0},{0,1}}; Matrix B = new Matrix(2,2,elem2);
		double[][] eres = {{1,2},{3,5}}; Matrix expResult = new Matrix(2,2, eres);
		BinaryOperationsTwoMatrices result = new BinaryOperationsTwoMatrices(A,B);
		Matrix res = result.add();		
		for( int i = 0; i < 2; i++)
			for( int j = 0; j < 2; j++)
			{
				System.out.println(expResult.elements[i][j]+" = "+res.elements[i][j]+" - "+B.elements[i][j]);
				Assert.isTrue( expResult.elements[i][j] == res.elements[i][j] );
			}
		System.out.println("Add funkcionira");
		
	}

	/**
	 * Test method for {@link operations.BinaryOperationsTwoMatrices#subtract()}.
	 * @throws MatrixDimensionException 
	 */
	@Test
	void testSubtract() throws MatrixDimensionException {
		System.out.println("Provjera binarne operacije subtract");
		double[][] elem1 = {{1,2},{3,4}}; Matrix A = new Matrix(2,2,elem1);
		double[][] elem2 = {{0,0},{0,1}}; Matrix B = new Matrix(2,2,elem2);
		double[][] eres = {{1,2},{3,3}}; Matrix expResult = new Matrix(2,2, eres);
		BinaryOperationsTwoMatrices result = new BinaryOperationsTwoMatrices(A,B);
		Matrix res = result.subtract();		
		for( int i = 0; i < 2; i++)
			for( int j = 0; j < 2; j++)
			{
				System.out.println(expResult.elements[i][j]+" = "+res.elements[i][j]+" - "+B.elements[i][j]);
				Assert.isTrue( expResult.elements[i][j] == res.elements[i][j] );
			}
		System.out.println("Subtract funkcionira");
	}

	/**
	 * Test method for {@link operations.BinaryOperationsTwoMatrices#multiply()}.
	 * @throws MatrixDimensionException 
	 */
	@Test
	void testMultiply() throws MatrixDimensionException {
		System.out.println("Provjera binarne operacije multiply");
		double[][] elem1 = {{1,2},{3,4}}; Matrix A = new Matrix(2,2,elem1);
		double[][] elem2 = {{0,0},{0,1}}; Matrix B = new Matrix(2,2,elem2);
		double[][] exre= {{0,2},{0,4}};
		Matrix expResult = new Matrix(2,2, exre);
		BinaryOperationsTwoMatrices result = new BinaryOperationsTwoMatrices(A,B);
		Matrix res = result.multiply();		
		for( int i = 0; i < 2; i++)
			for( int j = 0; j < 2; j++)
			{
				System.out.println(expResult.elements[i][j]+" = "+res.elements[i][j]+" - "+B.elements[i][j]);
				Assert.isTrue( expResult.elements[i][j] == res.elements[i][j] );
			}
		System.out.println("Multiply funkcionira");
	}

}
