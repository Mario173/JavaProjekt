package main;

import matrix.Matrix;
import database.Sqlitedatabase;

public class testbaze {

	public static void main(String[] args) {
		Sqlitedatabase q = new Sqlitedatabase();
		Matrix m = new Matrix(3,3,new double[][]{ {1.0 ,2.0, 3.0},
        					{2.0, 3.0, 5.0},
        					{1.0, 1.0, 1.0}
        					});
		q.insert_matrix(m);
		
		
	}

}
