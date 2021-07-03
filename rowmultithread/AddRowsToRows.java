package rowmultithread;

import matrix.Matrix;

public class AddRowsToRows implements Runnable {
	protected Matrix matrix;
	double k;
	int first, second, begin, id;
	
	public AddRowsToRows() {
		
	}
	
	public AddRowsToRows( Matrix a, int m, int n, double alpha, int p, int _id )
	{
		this.matrix = a;
		k = alpha;
		first = m; second = n; begin = p; id = _id;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//debugg
		// System.out.println("id = "+id+" dretvi sam i dodajem redak m="+first+" u n="+second);
		for( int i = begin; i < matrix.numOfCols; i++ ) {
			matrix.elements[second][i] += k*matrix.elements[first][i];
			
		}
		
	}

}
