package main;

import matrix.Matrix;
import operations.MultiThreadMatrixProduct;
import database.Sqlitedatabase;

public class testbaze {

	public static void main(String[] args) {
		Sqlitedatabase q = new Sqlitedatabase();
		Matrix m = new Matrix(3,3,new double[][]{ {1.0 ,2.0, 3.0},
        					{2.0, 3.0, 5.0},
        					{1.0, 1.0, 1.0}
        					});
		Matrix n = new Matrix(4,3,new double[][]{ {1.0 ,-4.0, 2.0, 5.0},
			{2.0, 3.0, 5.0, 3.0},
			{1.0, 1.0, 1.0, -7.0}
			});
		q.insert_matrix(m);
		Matrix c = new Matrix();
		try {
			c = MultiThreadMatrixProduct.Multiply(m,n);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < c.numOfRows ; i++) {
			for(int j = 0; j < c.numOfCols; j++) {
				System.out.print(c.elements[i][j]+" ");
			}
			System.out.println();
		}
		
		
	}

}
