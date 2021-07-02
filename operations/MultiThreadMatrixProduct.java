package operations;

import matrix.Matrix;

import java.lang.Runnable;

public class MultiThreadMatrixProduct implements Runnable {
	public int id_i, id_j, dim;
	public Matrix A, B, C;
	
	@Override public void run() {
		for(int k = 0; k < dim; k++) {
			C.elements[id_i][id_j] += A.elements[id_i][k] * B.elements[k][id_j];
		}
	}
	
	public MultiThreadMatrixProduct(Matrix A, Matrix B, Matrix C,int i, int j, int d){
		this.A = A;
		this.B = B;
		this.C = C;
		this.id_i = i;
		this.id_j = j;
		this.dim = d;
	}
	
	public static Matrix Multiply(Matrix A, Matrix B) throws InterruptedException {
		int newRow = A.numOfRows;
		int newCol = B.numOfCols;
		Matrix C = new Matrix(newCol, newRow);
		
		Thread[][] threads = new Thread[newRow][newCol];
		
		for(int i = 0; i < newRow; i++) {
			for(int j = 0; j < newCol; j++) {
				threads[i][j] = new Thread(new MultiThreadMatrixProduct(A,B,C,i,j,A.numOfCols));
			}
		}
		
		for(int i = 0; i < newRow; i++) {
			for(int j = 0; j < newCol; j++) {
				threads[i][j].start();
			}
		}
		
		for(int i = 0; i < newRow; i++) {
			for(int j = 0; j < newCol; j++) {
				threads[i][j].join();
			}
		}
		
		return C;
		

	}
	
	
	
}
