package operations;

import matrix.Matrix;

public class MultiThreadMatrixAddition implements Runnable {
	public int id_i, id_j, dim;
	public Matrix A, B, C;
	
	@Override public void run() {
		C.elements[id_i][id_j] = A.elements[id_i][id_j] + B.elements[id_i][id_j];
	}
	
	public MultiThreadMatrixAddition(Matrix A, Matrix B, Matrix C,int i, int j){
		this.A = A;
		this.B = B;
		this.C = C;
		this.id_i = i;
		this.id_j = j;
	}
	
	public static Matrix Add(Matrix A, Matrix B) throws InterruptedException {
		int newRow = A.numOfRows;
		int newCol = A.numOfCols;
		Matrix C = new Matrix(newRow, newCol);
		
		Thread[][] threads = new Thread[newRow][newCol];
		
		for(int i = 0; i < newRow; i++) {
			for(int j = 0; j < newCol; j++) {
				threads[i][j] = new Thread(new MultiThreadMatrixAddition(A,B,C,i,j));
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
