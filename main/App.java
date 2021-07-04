package main;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

import database.Sqlitedatabase;
import exceptions.MatrixDimensionException;
import exceptions.SquareMatrixException;
import exceptions.WrongInsertException;
import exceptions.ImpermissibleExponentException;
import exceptions.MatrixIsSingularException;
import matrix.Matrix;
import operations.BinaryOperationsTwoMatrices;
import operations.MatrixAndScalarOperations;
import operations.OtherOperations;
import operations.UnaryOperations;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;

public class App {

	protected Shell shlMatrixCalculator;
	private Text textInsert;
	private Text textBoxForFileNames;
	
	// variables needed to compute all operations
	Matrix first, second, res;
	double scalar;
	int exponent;
	double[] polynomialOrLinSysb;
	ArrayList<Matrix> LU;
	
	// for the result
	Label lblText;
	
	Sqlitedatabase db;
	
	/**
	 * Default constructor
	 */
	public App() {
		this.first = new Matrix(0,0);
		this.second = new Matrix(0,0);
		this.scalar = 0;
		this.exponent = 0;
		this.polynomialOrLinSysb = new double[15];
		this.res = new Matrix(0,0);
		this.db = new Sqlitedatabase();
		this.LU = new ArrayList<>();
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			App window = new App();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlMatrixCalculator.open();
		shlMatrixCalculator.layout();
		while (!shlMatrixCalculator.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlMatrixCalculator = new Shell(SWT.SHELL_TRIM & (~SWT.RESIZE));
		shlMatrixCalculator.setSize(1024, 512);
		shlMatrixCalculator.setBounds(new Rectangle(100, 100, 1024, 512));
		shlMatrixCalculator.setText("Matrix Calculator");
		
		lblText = new Label(shlMatrixCalculator, SWT.NONE);
		
		Button btnIsEqual = new Button(shlMatrixCalculator, SWT.NONE);
		btnIsEqual.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openNewWindow(2, "Are matrices Equal");
				if(first.numOfRows == 0 || second.numOfRows == 0) {
					res = new Matrix(0,0);
				} else {
					BinaryOperationsTwoMatrices b = new BinaryOperationsTwoMatrices(first, second);
					first = second = res = new Matrix(0,0);
					lblText.setBounds(300, 70, 200, 150);
					if(b.isEqual()) {
						lblText.setText("Given matrices are equal");
					} else {
						lblText.setText("Given matrices are not equal");
					}
				}
			}
		});
		btnIsEqual.setBounds(0, 0, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnIsEqual.setText("isEqual");
		btnIsEqual.setToolTipText("Check if two matrices are equal"); // for displaying text when hovering over the button
		
		
		Button btnAdd = new Button(shlMatrixCalculator, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openNewWindow(2, "Add matrices");
				if(first.numOfRows == 0 || second.numOfRows == 0) {
					res = new Matrix(0,0);
				} else {
					BinaryOperationsTwoMatrices b = new BinaryOperationsTwoMatrices(first, second);
					try {
						res = b.add();
						makeLabel("You added two matrices:\n", 2);
					} catch (MatrixDimensionException e1) {
						MessageDialog.openError(shlMatrixCalculator, "Error", e1.getMessage());
					}
					first = second = new Matrix(0,0);
				}
			}
		});
		btnAdd.setBounds((shlMatrixCalculator.getSize().x - 20) / 7, 0, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnAdd.setText("Add");
		btnAdd.setToolTipText("Add two matrices");
		
		Button btnSubtract = new Button(shlMatrixCalculator, SWT.NONE);
		btnSubtract.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openNewWindow(2, "Subtract matrices");
				if(first.numOfRows == 0 || second.numOfRows == 0) {
					res = new Matrix(0,0);
				} else {
					BinaryOperationsTwoMatrices b = new BinaryOperationsTwoMatrices(first, second);
					try {
						res = b.subtract();
						makeLabel("You subtracted two matrices:\n", 2);
					} catch (MatrixDimensionException e1) {
						MessageDialog.openError(shlMatrixCalculator, "Error", e1.getMessage());
					}
					first = second = new Matrix(0,0);
				}
			}
		});
		btnSubtract.setBounds(2 * (shlMatrixCalculator.getSize().x - 20) / 7, 0, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnSubtract.setText("Subtract");
		btnSubtract.setToolTipText("Subtract two matrices");
		
		Button btnScalar = new Button(shlMatrixCalculator, SWT.NONE);
		btnScalar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openNewWindow(4, "Multiply matrix with a scalar");
				if(first.numOfRows == 0) {
					res = new Matrix(0,0);
				} else {
					MatrixAndScalarOperations s = new MatrixAndScalarOperations(first, scalar);
					res = s.scalar();
					makeLabel("You multiplied a matrix with a scalar:\n", 4);
					first = new Matrix(0,0);
				}
			}
		});
		btnScalar.setBounds(3 * (shlMatrixCalculator.getSize().x - 20) / 7, 0, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnScalar.setText("Scalar");
		btnScalar.setToolTipText("Multiply a matrix with a scalar");
		
		Button btnMultiply = new Button(shlMatrixCalculator, SWT.NONE);
		btnMultiply.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openNewWindow(2, "Multiply matrices");
				if(first.numOfRows == 0 || second.numOfRows == 0) {
					res = new Matrix(0,0);
				} else {
					System.out.println("Here2: " + first.numOfRows + " " + first.numOfCols);
					System.out.println("Here3: " + second.numOfRows + " " + second.numOfCols);
					BinaryOperationsTwoMatrices b = new BinaryOperationsTwoMatrices(first, second);
					try {
						res = b.multiply();
						makeLabel("You multiplied two matrices:\n", 2);
					} catch (MatrixDimensionException e1) {
						MessageDialog.openError(shlMatrixCalculator, "Error", e1.getMessage());
					}
					first = second = new Matrix(0,0);
				}
			}
		});
		btnMultiply.setBounds(4 * (shlMatrixCalculator.getSize().x - 20) / 7, 0, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnMultiply.setText("Multiply");
		btnMultiply.setToolTipText("Multiply two matrices");
		
		Button btnPotentiate = new Button(shlMatrixCalculator, SWT.NONE);
		btnPotentiate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openNewWindow(4, "Potentiate matrix");
				if(first.numOfRows == 0) {
					res = new Matrix(0,0);
				} else {
					MatrixAndScalarOperations s = new MatrixAndScalarOperations(first, exponent);
					try {
						res = s.potentiate();
						makeLabel("You multiplied a matrix n times:\n", 4);
					} catch (SquareMatrixException | MatrixDimensionException | ImpermissibleExponentException e1) {
						MessageDialog.openError(shlMatrixCalculator, "Error", e1.getMessage());
					}
					first = new Matrix(0,0);
				}
			}
		});
		btnPotentiate.setBounds(5 * (shlMatrixCalculator.getSize().x - 20) / 7, 0, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnPotentiate.setText("A^n");
		btnPotentiate.setToolTipText("Calculate A^n, where A is a matrix and n is a natural number between 0 and 15");
		
		Button btnPolynomial = new Button(shlMatrixCalculator, SWT.NONE);
		btnPolynomial.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openNewWindow(3, "Polynomial");
				if(first.numOfRows == 0) {
					res =  new Matrix(0,0);
				} else {
					OtherOperations o = new OtherOperations(first, polynomialOrLinSysb);
					try {
						res = o.calculatePolynomialValue();
						makeLabel("The resulting value is:\n", 3);
					} catch (SquareMatrixException | MatrixDimensionException | ImpermissibleExponentException e1) {
						MessageDialog.openError(shlMatrixCalculator, "Error", e1.getMessage());
					}
					first = new Matrix(0,0);
				}
			}
		});
		btnPolynomial.setBounds(6 * (shlMatrixCalculator.getSize().x - 20) / 7, 0, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnPolynomial.setText("Poly");
		btnPolynomial.setToolTipText("Calculate p(A), where A is a matrix and p is a polynomial");
		
		Button btnLU = new Button(shlMatrixCalculator, SWT.NONE);
		btnLU.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openNewWindow(1, "Find the LU decomposition of the matrix");
				if(first.numOfRows == 0) {
					res = new Matrix(0,0);
				} else {
					UnaryOperations u = new UnaryOperations(first);
					try {
						LU = u.LUDecomposition();
						makeLabelLU("The matrix used for calculations is:\n");
					} catch (MatrixDimensionException | InterruptedException e1) {
						MessageDialog.openError(shlMatrixCalculator, "Error", e1.getMessage());
					}
					first = new Matrix(0,0);
				}
			}
		});
		btnLU.setBounds(0, 30, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnLU.setText("LU");
		btnLU.setToolTipText("Calculate the LU decomposition of a matrix");
		
		Button btnTrace = new Button(shlMatrixCalculator, SWT.NONE);
		btnTrace.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openNewWindow(1, "Find the trace of the matrix");
				if(first.numOfRows == 0) {
					res = new Matrix(0,0);
				} else {
					UnaryOperations u = new UnaryOperations(first);
					try {
						lblText.setBounds(300, 70, 200, 150);
						String s = "The trace of the matrix is: ";
						lblText.setText(s + u.trace());
					} catch (SquareMatrixException e1) {
						MessageDialog.openError(shlMatrixCalculator, "Error", e1.getMessage());
					}
					first = new Matrix(0,0);
				}
			}
		});
		btnTrace.setBounds((shlMatrixCalculator.getSize().x - 20) / 7, 30, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnTrace.setText("Trace");
		btnTrace.setToolTipText("Calculate the trace of a matrix");
		
		Button btnRank = new Button(shlMatrixCalculator, SWT.NONE);
		btnRank.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openNewWindow(1, "Find the rank of the matrix");
				if(first.numOfRows == 0) {
					res = new Matrix(0,0);
				} else {
					UnaryOperations u = new UnaryOperations(first);
					lblText.setBounds(300, 70, 200, 150);
					String s = "The rank of the matrix is: ";
					try {
						lblText.setText(s + u.rank());
					} catch (MatrixDimensionException | InterruptedException e1) {
						MessageDialog.openError(shlMatrixCalculator, "Error", e1.getMessage());
					}
					first = new Matrix(0,0);
				}
			}
		});
		btnRank.setBounds(2 * (shlMatrixCalculator.getSize().x - 20) / 7, 30, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnRank.setText("Rank");
		btnRank.setToolTipText("Calculate the rank of a matrix");
		
		Button btnDeterminant = new Button(shlMatrixCalculator, SWT.NONE);
		btnDeterminant.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openNewWindow(1, "Find the determinant of the matrix");
				if(first.numOfRows == 0) {
					res = new Matrix(0,0);
				} else {
					UnaryOperations u = new UnaryOperations(first);
					try {
						lblText.setBounds(300, 70, 300, 150);
						String s = "The determinant of the matrix is: ";
						lblText.setText(s + Double.toString(u.determinant()));
					} catch (MatrixDimensionException | InterruptedException | SquareMatrixException e1) {
						MessageDialog.openError(shlMatrixCalculator, "Error", e1.getMessage());
					}
					first = new Matrix(0,0);
				}
			}
		});
		btnDeterminant.setBounds(3 * (shlMatrixCalculator.getSize().x - 20) / 7, 30, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnDeterminant.setText("Det");
		btnDeterminant.setToolTipText("Calculate the determinant of a matrix");
		
		Button btnLinearSystems = new Button(shlMatrixCalculator, SWT.NONE);
		btnLinearSystems.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openNewWindow(3, "Solve linear system");
				if(first.numOfRows == 0) {
					res = new Matrix(0,0);
				} else {
					OtherOperations o = new OtherOperations(first, polynomialOrLinSysb);
					try {
						double[] vect = o.solveLinearSystem();
						String temp = "Solve linear system:\n";
						temp += "The matrix used in this operation is:\n";
						for(int i = 0; i < first.numOfRows; i++) {
							for(int j = 0; j < first.numOfCols; j++) {
								temp += String.format("%.2f", first.elements[i][j]);
								temp += " ";
							}
							temp += "\n";
						}
						temp += "The vector used is:\n";
						for(int i = 0; i < polynomialOrLinSysb.length; i++) {
							temp += (String.format("%.2f", polynomialOrLinSysb[i]) + " ");
						}
						temp += "\n";
						temp += "The resulting vector is:\n";
						temp += Arrays.toString(vect);
						
						lblText.setText(temp);
					} catch (SquareMatrixException | MatrixDimensionException | InterruptedException | MatrixIsSingularException e1) {
						MessageDialog.openError(shlMatrixCalculator, "Error", e1.getMessage());
					}
					first = new Matrix(0,0);
				}
			}
		});
		btnLinearSystems.setBounds(4 * (shlMatrixCalculator.getSize().x - 20) / 7, 30, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnLinearSystems.setText("Lin Sys");
		btnLinearSystems.setToolTipText("For Ax=b, where A is a mĂ—n matrix and b is mĂ—1 matrix, calculate x");
		
		Button btnTranspose = new Button(shlMatrixCalculator, SWT.NONE);
		btnTranspose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openNewWindow(1, "Find transposed matrix");
				if(first.numOfRows == 0) {
					res = new Matrix(0,0);
				} else {
					UnaryOperations u = new UnaryOperations(first);
					res = u.transpose();
					makeLabel("The transposed matrix is:\n", 1);
					first = new Matrix(0,0);
				}
			}
		});
		btnTranspose.setBounds(5 * (shlMatrixCalculator.getSize().x - 20) / 7, 30, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnTranspose.setText("Trans");
		btnTranspose.setToolTipText("Calculate the transposed matrix of the given one");
		
		Button btnInverse = new Button(shlMatrixCalculator, SWT.NONE);
		btnInverse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openNewWindow(1, "Find matrix inverse");
				if(first.numOfRows == 0) {
					res = new Matrix(0,0);
				} else {
					UnaryOperations u = new UnaryOperations(first);
					try {
						res = u.inverse();
						makeLabel("The inverse of the given matrix is:\n", 1);
					} catch (SquareMatrixException | MatrixDimensionException | InterruptedException | MatrixIsSingularException e1) {
						MessageDialog.openError(shlMatrixCalculator, "Error", e1.getMessage());
					}
					first = new Matrix(0,0);
				}
			}
		});
		btnInverse.setBounds(6 * (shlMatrixCalculator.getSize().x - 20) / 7, 30, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnInverse.setText("Inverse");
		btnInverse.setToolTipText("Calculate the inverse of the given matrix");
		
		textInsert = new Text(shlMatrixCalculator, SWT.BORDER);
		textInsert.setBounds(28, 97, 201, 108);
		
		Label lblInsertMatrix1 = new Label(shlMatrixCalculator, SWT.NONE);
		lblInsertMatrix1.setBounds(28, 71, 96, 20);
		lblInsertMatrix1.setText("Insert Matrix:");
		
		Button btnInsert = new Button(shlMatrixCalculator, SWT.NONE);
		btnInsert.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Insert i = new Insert();
				try {
					i.insertFromTextBox(textInsert.getText());
					Label tick = new Label(shlMatrixCalculator, SWT.NONE);
					Rectangle rect = btnInsert.getBounds();
					tick.setBounds(rect.x + rect.width + 5, rect.y, 25, 25);
					tick.setText("✓");
					db.insert_matrix(i.lastInserted);
				} catch (NullPointerException | NumberFormatException | WrongInsertException e1) {
					MessageDialog.openError(shlMatrixCalculator, "Error", e1.getMessage());
				} catch(StringIndexOutOfBoundsException e2) {
					MessageDialog.openError(shlMatrixCalculator, "Error", "Please write your matrix into the textbox");
				}
			}
		});
		btnInsert.setBounds(139, 211, 90, 30);
		btnInsert.setText("Insert Matrix");
		btnInsert.setToolTipText("Insert matrix from textbox");
		
		Button btnHelp = new Button(shlMatrixCalculator, SWT.NONE);
		btnHelp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openNewWindow(0, "Help");
			}
		});
		btnHelp.setBounds(0, 163 * shlMatrixCalculator.getSize().y / 192, 90, 30);
		btnHelp.setText("Help");
		btnHelp.setToolTipText("Some tips about how to use the app");
	
		shlMatrixCalculator.setVisible(true);
		
		Label lblInsertMatrix2 = new Label(shlMatrixCalculator, SWT.NONE);
		lblInsertMatrix2.setBounds(28, 259, 118, 20);
		lblInsertMatrix2.setText("Insert File Name:");
		
		textBoxForFileNames = new Text(shlMatrixCalculator, SWT.BORDER);
		textBoxForFileNames.setBounds(28, 285, 201, 26);
		
		Button btnInsertFileName = new Button(shlMatrixCalculator, SWT.NONE);
		btnInsertFileName.setBounds(139, 317, 90, 30);
		btnInsertFileName.setText("Insert Name");
		btnInsertFileName.setToolTipText("Insert matrix from a .txt file");
		btnInsertFileName.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Insert insert = new Insert();
				try {
					insert.insertFromFile(textBoxForFileNames.getText());
					Label tick = new Label(shlMatrixCalculator, SWT.NONE);
					Rectangle rect = btnInsertFileName.getBounds();
					tick.setBounds(rect.x + rect.width + 5, rect.y, 25, 25);
					tick.setText("✓");
					db.insert_matrix(insert.lastInserted);
				} catch (IOException | WrongInsertException e1) {
					MessageDialog.openError(shlMatrixCalculator, "Warning", e1.getMessage());
				}
			}
		});
		
		Label insertIntoDb = new Label(shlMatrixCalculator, SWT.NONE);
		insertIntoDb.setBounds(shlMatrixCalculator.getBounds().width - 350, 163 * shlMatrixCalculator.getSize().y / 192 + 5, 200, 30);
		insertIntoDb.setText("Insert last result into database: ");
		
		Button btnDB = new Button(shlMatrixCalculator, SWT.NONE);
		btnDB.setBounds(shlMatrixCalculator.getBounds().width - 140, 163 * shlMatrixCalculator.getSize().y / 192, 120, 30);
		btnDB.setText("Insert into DB");
		btnDB.setToolTipText("Insert into your database (last 10 matrices inserted are kept)");
		btnDB.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				db.insert_matrix(res);
			}
		});
	}
	
	/**
	 * Make the label for the LU decomposition
	 * @param start text to start the label
	 */
	public void makeLabelLU(String start) {
		lblText.setBounds(300, 70, 400, 300);
		for(int i = 0; i < this.first.numOfRows; i++) {
			for(int j = 0; j < this.first.numOfCols; j++) {
				start += (String.format("%.2f", first.elements[i][j]) + " ");
			}
			start += "\n";
		}
		start += "The resulting matrices (pivot, L and U) are:\n";
		for(int i = 0; i < LU.get(0).numOfRows; i++) {
			for(int j = 0; j < LU.get(0).numOfCols; j++) {
				start += (String.format("%.2f", LU.get(0).elements[i][j]) + " ");
			}
			start += " | ";
			for(int j = 0; j < LU.get(1).numOfCols; j++) {
				start += (String.format("%.2f", LU.get(1).elements[i][j]) + " ");
			}
			start += " | ";
			for(int j = 0; j < LU.get(2).numOfCols; j++) {
				start += (String.format("%.2f", LU.get(2).elements[i][j]) + " ");
			}
			start += "\n";
		}
		this.lblText.setText(start);
	}
	
	/**
	 * Make the label for the result
	 * @param temp text to start the label
	 * @param whichOperation integer representing a group of the operations
	 */
	public void makeLabel(String temp, int whichOperation) {
		lblText.setBounds(300, 70, Math.max(400, 30 * res.numOfCols), Math.max(200, 30 * (res.numOfRows + 1)));
		
		switch(whichOperation) {
			case 1:
				temp += "The matrix used in this operation is:\n";
				for(int i = 0; i < first.numOfRows; i++) {
					for(int j = 0; j < first.numOfCols; j++) {
						temp += String.format("%.2f", first.elements[i][j]);
						temp += " ";
					}
					temp += "\n";
				}
				break;
			case 2:
				temp += "The matrices used in this operation are:\n";
				int len = 0;
				for(int i = 0; i < Math.max(first.numOfRows, first.numOfCols); i++) {
					for(int j = 0; j < first.numOfCols; j++) {
						if(i >= first.numOfRows) {
							for(int k = 0; k < len; k++) {
								temp += " ";
							}
							break;
						} else {
							len += (String.format("%.2f", first.elements[i][j]).length() + 1);
							temp += String.format("%.2f", first.elements[i][j]);
							temp += " ";
						}
					}
					temp += "\t | ";
					for(int j = 0; j < second.numOfCols; j++) {
						if(i >= second.numOfRows) {
							break;
						} else {
							temp += String.format("%.2f", second.elements[i][j]);
							temp += " ";
						}
					}
					temp += "\n";
				}
				break;
			case 3:
				temp += "The matrix used in this operation is:\n";
				for(int i = 0; i < first.numOfRows; i++) {
					for(int j = 0; j < first.numOfCols; j++) {
						temp += String.format("%.2f", first.elements[i][j]);
						temp += " ";
					}
					temp += "\n";
				}
				temp += "The vector used is:\n";
				for(int i = 0; i < this.polynomialOrLinSysb.length; i++) {
					temp += (String.format("%.2f", this.polynomialOrLinSysb[i]) + " ");
				}
				temp += "\n";
				break;
			case 4:
				temp += "The matrix used in this operation is:\n";
				for(int i = 0; i < first.numOfRows; i++) {
					for(int j = 0; j < first.numOfCols; j++) {
						temp += String.format("%.2f", first.elements[i][j]);
						temp += " ";
					}
					temp += "\n";
				}
				temp += "The scalar used in this operation is: ";
				if(this.scalar != 0) {
					temp += (String.format("%.2f", this.scalar) + "\n");
					this.scalar = 0;
				} else {
					temp += (this.exponent + "\n");
				}
				break;
			default:
				break;
		}
		
		temp += "The result is:\n";
		for(int i = 0; i < res.numOfRows; i++) {
			for(int j = 0; j < res.numOfCols; j++) {
				temp += String.format("%.2f", res.elements[i][j]);
				temp += " ";
			}
			temp += "\n";
		}
		
		lblText.setText(temp);
	}
	
	
	/**
	 * Method needed to open and customize a new window
	 * @param id needed to distinguish between different types of windows
	 * @param whichOne which button was clicked
	 */
	public void openNewWindow(int id, String whichOne) {
		Shell newWindow = new Shell(SWT.SHELL_TRIM & (~SWT.RESIZE));
		Display display = Display.getDefault();
		
		switch(id) {
			case 0:
				customizeHelp(newWindow);
				break;
			case 1: // not wrong, it only means that the same thing is done
			case 2:
				customizeOperations(newWindow, id, whichOne);
				break;
			case 3:
				customizeOtherOperations(newWindow, whichOne);
				break;
			case 4:
				customizeScalarOperations(newWindow, whichOne);
				break;
			default:
				break;
		};
        
		newWindow.open();
		newWindow.layout();
		while (!newWindow.isDisposed()) {
			shlMatrixCalculator.setEnabled(false); // main window not clickable while this window is opened
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		shlMatrixCalculator.setEnabled(true);
		shlMatrixCalculator.setActive();
	}
	
	
	/**
	 * Method used for customizing the window for Help
	 * @param newWindow shell representing a new window
	 */
	public void customizeHelp(Shell newWindow) {
		newWindow.setBounds(new Rectangle(100, 100, 700, 855));
        newWindow.setText("Help");
        
        Label test = new Label(newWindow, SWT.NONE);
        test.setBounds(5, 5, 690, 845);
        
        String temp = "How to use Matrix Calculator app?\n";
        temp += "Our app is fairly intuitive and easy to use!\n";
        temp += "On the top of the window you can see 2 rows of buttons. Each button represents an operation\n with matrices.";
        temp += "There are 4 kind of operations, binary operations with 2 matrices, \nbinary operations with a matrix and a scalar,"
        		+ " unary operations using a single matrix and \noperations that use a matrix and a vector.";
        temp += "You can just hover over the buttons for more information \nabout the operations.";
        temp += "Once you choose the operation and click the button, a new window will open.\n";
        temp += "The new window will look different depending on the nature of the operation.\n";
        temp += "If you chose to do an operation with two matrices, \na window will have 2 identical columns with 2 textboxes and 3 buttons.\n";
        temp += "The first column is for the first matrix, and the second is for the second matrix.\n";
        temp += "In each column, the first textbox (the larger one) is for directly typing in the matrix.\n";
        temp += "The matrix must be typed surrounded by brackets, with each row also surrounded by brackets, e.g.\n";
        temp += "{{1,2,3},{4,5,6}} represents a matrix with 2 rows and 3 columns.\n";
        temp += "The smaller textbox is used for inserting from .txt files, e.g.\n";
        temp += "C://Users/Mario/Desktop/matrix.txt will find that text file and read the matrix from it.\n";
        temp += "CAUTION: The first 2 numbers in the .txt file must be \nnumber of rows and number of columns of the matrix!\n";
        temp += "The button below the each of those textboxes is used to \ntemporarily save the inserted matrix and exactly one of them needs to be clicked.\n.";
        temp += "If you chose an unary operation, the window will \nhave 1 instead of two columns which behave in the same way.\n";
        temp += "If you chose an operation with a scalar or other operation, \nthe window will have 1 column with an additional \ntextbox and a button near the bottom of the screen.";
        temp += "They are used for inserting the scalar or a vector, depending on the operation.\n";
        temp += "CAUTION: The vector is inserted so that a space divides each number, e.g.\n";
        temp += "-1 2.3 9 -0.7 represents a vector [-1, 2.3, 9, -0.7].\n";
        temp += "IMPORTANT: At the bottom of each of that window there is a button \"Calculate\"\n which needs to be clicked after you insert all the needed data!\n";
        temp += "All the new windows have buttons for inserting the matrices from the database.\n";
        temp += "Once you click that button, a new window will open with <=10 radio buttons.\n";
        temp += "Just hover with your mouse over each to see which matrix they hold, \nbut be careful, once you leave that window, there will always be one chosen matrix.\n";
        temp += "But don't worry! You can just type in a new matrix or a path to a file in a textbox or choose another \nmatrix from database before clicking \"Calculate\"";
        temp += "Except the 14 buttons, on the main screen \nthere is a column with 2 textboxes and 2 buttons and a button in the lower right corner.\n";
        temp += "They are all used for inserting your matrices in a database and saving them for later.\n";
        temp += "The column behaves exactly as the ones in the new window.\n";
        temp += "The button in the lower right corner is used for saving the resulting matrices. \nClick it if you want to save the result for later.";
        
        test.setText(temp);
	}
	
	/**
	 * Method for customizing new window for unary and binary operations
	 * @param newWindow shell representing a new window
	 * @param howMany integer, is the operation unary (1) or binary (2)
	 * @param whichOne string representing an operation
	 * @return true if creation succeeded, false otherwise
	 */
	public void customizeOperations(Shell newWindow, int howMany, String whichOne) {
		newWindow.setBounds(200, 200, howMany * 350, 450);
		newWindow.setText(whichOne);
		Text textboxes[] = new Text[howMany * 2];
		Button buttons[] = new Button[howMany * 3];
		
		Insert insert = new Insert();
		
		for(int i = 0; i < howMany; i++) {
			int curr = 2 * i, curr2 = 3 * i;
			textboxes[curr] = new Text(newWindow, SWT.NONE);
			textboxes[curr].setBounds(28 + i * 350, 5, 201, 108);
			
			buttons[curr2] = new Button(newWindow, SWT.NONE);
			buttons[curr2].setText("Insert matrix");
			buttons[curr2].setBounds(125 + i * 350, 130, 100, 30);
			
			textboxes[curr + 1] = new Text(newWindow, SWT.NONE);
			textboxes[curr + 1].setBounds(28 + i * 350, 180, 201, 50);
			
			buttons[curr2 + 1] = new Button(newWindow, SWT.NONE);
			buttons[curr2 + 1].setText("Insert from file");
			buttons[curr2 + 1].setBounds(100 + i * 350, 250, 130, 30);
			
			buttons[curr2 + 2] = new Button(newWindow, SWT.NONE);
			buttons[curr2 + 2].setText("From database");
			buttons[curr2 + 2].setBounds(100 + i * 350, 290, 130, 30);
		}
		
		Button calc = new Button(newWindow, SWT.NONE);
		calc.setText("Calculate");
		calc.setBounds(newWindow.getBounds().width / 2 - 65, 330, 130, 30);
		calc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent evt) {
				newWindow.close();
			}
		});
		
		for(int i = 0; i < howMany; i++) {
			int curr = 2 * i, curr2 = 3 * i;

			buttons[curr2].addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					try {
						insert.insertFromTextBox(textboxes[curr].getText());
						Label tick = new Label(newWindow, SWT.NONE);
						Rectangle rect = buttons[curr2].getBounds();
						tick.setBounds(rect.x + rect.width + 5, rect.y, 25, 25);
						tick.setText("✓");
						if(curr2 == 0) {
							first = insert.lastInserted;
						} else {
							second = insert.lastInserted;
						}
					} catch (NullPointerException | NumberFormatException | WrongInsertException e1) {
						MessageDialog.openError(newWindow, "Warning", e1.getMessage());
					} catch(StringIndexOutOfBoundsException e2) {
						MessageDialog.openError(shlMatrixCalculator, "Error", "Please write your matrix into the textbox");
					}
				}
			});

			buttons[curr2 + 1].addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					try {
						insert.insertFromFile(textboxes[curr + 1].getText());
						Label tick = new Label(newWindow, SWT.NONE);
						Rectangle rect = buttons[curr2 + 1].getBounds();
						tick.setBounds(rect.x + rect.width + 5, rect.y, 25, 25);
						tick.setText("✓");
						if(curr2 == 0) {
							first = insert.lastInserted;
						} else {
							second = insert.lastInserted;
						}
					} catch (NullPointerException | NumberFormatException | IOException | WrongInsertException e1) {
						MessageDialog.openWarning(newWindow, "Warning", e1.getMessage());
					}
				}
			});

			buttons[curr2 + 2].addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					makeDatabaseChooserWindow(newWindow, curr2 == 0);
					Label tick = new Label(newWindow, SWT.NONE);
					Rectangle rect = buttons[curr2 + 2].getBounds();
					tick.setBounds(rect.x + rect.width + 5, rect.y, 25, 25);
					tick.setText("✓");
				}
			});
		}
	}
	
	/**
	 * Method for customizing new window for other operations
	 * @param newWindow shell representing a new window
	 * @param whichOne string representing an operation
	 * @return true if creation succeeded, false otherwise
	 */
	public void customizeOtherOperations(Shell newWindow, String whichOne) {
		newWindow.setBounds(200, 200, 300, 600);
		newWindow.setText(whichOne);
		
		Insert insert = new Insert();
		
		Label lbl = new Label(newWindow, SWT.NONE);
		lbl.setText("Insert matrix: ");
		lbl.setBounds(28, 5, 100, 25);
		
		Text textbox1 = new Text(newWindow, SWT.NONE);
		textbox1.setBounds(28, 35, 201, 108);
		
		Button btn1 = new Button(newWindow, SWT.NONE);
		btn1.setText("Insert matrix");
		btn1.setBounds(125, 160, 100, 30);
		
		Text textbox2 = new Text(newWindow, SWT.NONE);
		textbox2.setBounds(28, 210, 201, 50);
		
		Button btn2 = new Button(newWindow, SWT.NONE);
		btn2.setText("Insert from file");
		btn2.setBounds(100, 280, 130, 30);
		
		Button btn3 = new Button(newWindow, SWT.NONE);
		btn3.setText("From database");
		btn3.setBounds(100, 320, 130, 30);
		
		Label lbl2 = new Label(newWindow, SWT.NONE);
		lbl2.setText("Insert polynomial/column:");
		lbl2.setBounds(28, 360, 200, 25);
		
		Text textbox3 = new Text(newWindow, SWT.NONE);
		textbox3.setBounds(28, 390, 201, 50);
		
		Button btn4 = new Button(newWindow, SWT.NONE);
		btn4.setText("Insert poly/column");
		btn4.setBounds(100, 450, 130, 30);
		
		Button calc = new Button(newWindow, SWT.NONE);
		calc.setText("Calculate");
		calc.setBounds(newWindow.getBounds().width / 2 - 65, 490, 130, 30);
		calc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent evt) {
				newWindow.close();
			}
		});
		
		// actions on click
		btn4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String nums[] = textbox3.getText().split(" ");
				polynomialOrLinSysb = new double[nums.length];
				for(int i = 0; i < nums.length; i++) {
					try {
						polynomialOrLinSysb[i] = Double.parseDouble(nums[i]);
						Label tick = new Label(newWindow, SWT.NONE);
						Rectangle rect = btn4.getBounds();
						tick.setBounds(rect.x + rect.width + 5, rect.y, 25, 25);
						tick.setText("✓");
					} catch(NullPointerException | NumberFormatException e1) {
						MessageDialog.openError(newWindow, "Error", e1.getMessage());
					}
				}
			}
		});

		btn3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				makeDatabaseChooserWindow(newWindow, true);
				Label tick = new Label(newWindow, SWT.NONE);
				Rectangle rect = btn3.getBounds();
				tick.setBounds(rect.x + rect.width + 5, rect.y, 25, 25);
				tick.setText("✓");
			}
		});

		btn2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					insert.insertFromFile(textbox2.getText());
					first = insert.lastInserted;
					Label tick = new Label(newWindow, SWT.NONE);
					Rectangle rect = btn2.getBounds();
					tick.setBounds(rect.x + rect.width + 5, rect.y, 25, 25);
					tick.setText("✓");
				} catch (IOException | WrongInsertException e1) {
					MessageDialog.openError(newWindow, "Warning", e1.getMessage());
				}
			}
		});

		btn1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					insert.insertFromTextBox(textbox1.getText());
					Label tick = new Label(newWindow, SWT.NONE);
					Rectangle rect = btn1.getBounds();
					tick.setBounds(rect.x + rect.width + 5, rect.y, 25, 25);
					tick.setText("✓");
					first = insert.lastInserted;
				} catch (NullPointerException | NumberFormatException | WrongInsertException e1) {
					MessageDialog.openError(newWindow, "Warning", e1.getMessage());
				} catch(StringIndexOutOfBoundsException e2) {
					MessageDialog.openError(shlMatrixCalculator, "Error", "Please write your matrix into the textbox");
				}
			}
		});
	}
	
	
	public void customizeScalarOperations(Shell newWindow, String whichOne) {
		newWindow.setBounds(200, 200, 300, 600);
		newWindow.setText(whichOne);
		
		Insert insert = new Insert();
		
		Label lbl = new Label(newWindow, SWT.NONE);
		lbl.setText("Insert matrix: ");
		lbl.setBounds(28, 5, 100, 25);
		
		Text textbox1 = new Text(newWindow, SWT.NONE);
		textbox1.setBounds(28, 35, 201, 108);
		
		Button btn1 = new Button(newWindow, SWT.NONE);
		btn1.setText("Insert matrix");
		btn1.setBounds(125, 160, 100, 30);
		
		Text textbox2 = new Text(newWindow, SWT.NONE);
		textbox2.setBounds(28, 210, 201, 50);
		
		Button btn2 = new Button(newWindow, SWT.NONE);
		btn2.setText("Insert from file");
		btn2.setBounds(100, 280, 130, 30);
		
		Button btn3 = new Button(newWindow, SWT.NONE);
		btn3.setText("From database");
		btn3.setBounds(100, 320, 130, 30);
		
		Label lbl2 = new Label(newWindow, SWT.NONE);
		lbl2.setText("Insert scalar:");
		lbl2.setBounds(28, 360, 200, 25);
		
		Text textbox3 = new Text(newWindow, SWT.NONE);
		textbox3.setBounds(28, 390, 201, 50);
		
		Button btn4 = new Button(newWindow, SWT.NONE);
		btn4.setText("Insert scalar");
		btn4.setBounds(100, 450, 130, 30);
		
		Button calc = new Button(newWindow, SWT.NONE);
		calc.setText("Calculate");
		calc.setBounds(newWindow.getBounds().width / 2 - 65, 500, 130, 30);
		calc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent evt) {
				newWindow.close();
			}
		});
		
		// actions on click
		btn4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if(whichOne.equalsIgnoreCase("Multiply matrix with a scalar")) {
						scalar = Double.parseDouble(textbox3.getText());
					} else {
						exponent = Integer.parseInt(textbox3.getText());
					}
					Label tick = new Label(newWindow, SWT.NONE);
					Rectangle rect = btn4.getBounds();
					tick.setBounds(rect.x + rect.width + 5, rect.y, 25, 25);
					tick.setText("✓");
				} catch(NullPointerException | NumberFormatException e1) {
					MessageDialog.openError(newWindow, "Warning", e1.getMessage());
				}
			}
		});

		btn3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				makeDatabaseChooserWindow(newWindow, true);
				Label tick = new Label(newWindow, SWT.NONE);
				Rectangle rect = btn3.getBounds();
				tick.setBounds(rect.x + rect.width + 5, rect.y, 25, 25);
				tick.setText("✓");
			}
		});

		btn2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					insert.insertFromFile(textbox2.getText());
					Label tick = new Label(newWindow, SWT.NONE);
					Rectangle rect = btn2.getBounds();
					tick.setBounds(rect.x + rect.width + 5, rect.y, 25, 25);
					tick.setText("✓");
					first = insert.lastInserted;
				} catch (IOException | WrongInsertException e1) {
					MessageDialog.openError(newWindow, "Warning", e1.getMessage());
				}
			}
		});

		btn1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					insert.insertFromTextBox(textbox1.getText());
					Label tick = new Label(newWindow, SWT.NONE);
					Rectangle rect = btn1.getBounds();
					tick.setBounds(rect.x + rect.width + 5, rect.y, 25, 25);
					tick.setText("✓");
					first = insert.lastInserted;
				} catch (NullPointerException | NumberFormatException | WrongInsertException e1) {
					MessageDialog.openError(newWindow, "Warning", e1.getMessage());
				} catch(StringIndexOutOfBoundsException e2) {
					MessageDialog.openError(shlMatrixCalculator, "Error", "Please write your matrix into the textbox");
				}
			}
		});
	}
	
	
	public void makeDatabaseChooserWindow(Shell newWindow, boolean firstMatrix) {
		Shell DBWindow = new Shell(SWT.SHELL_TRIM & (~SWT.RESIZE));
		Display display = Display.getDefault();
		
		DBWindow.setBounds(220, 220, 550, 650);
		DBWindow.setText("Choose from database");
		
		List<Matrix> all = db.selectAll();
		Button[] rbtns = new Button[all.size()];
		
		Label hover = new Label(DBWindow, SWT.NONE);
		hover.setBounds(5, 5, 520, 30);
		hover.setText("Hover over the buttons to see the matrices! Click \"Choose\" to choose one:");
		
		for(int i = 0; i < all.size(); i++) {
			rbtns[i] = new Button(DBWindow, SWT.RADIO);
			rbtns[i].setBounds(5 + (i % 2) * 250, 30 + (i / 2) * 45, 200, 30);
			rbtns[i].setText("Matrix number " + (i + 1));
			rbtns[i].setToolTipText(all.get(i).toString());
			rbtns[i].setSelection(false);
			int k = i;
			
			rbtns[i].addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if(firstMatrix) {
						first = all.get(k);
					} else {
						second = all.get(k);
					}
				}
			});
		}
		
		Button choose = new Button(DBWindow, SWT.NONE);
		choose.setBounds(200, 550, 130, 30);
		choose.setText("Choose");
		choose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent evt) {
				DBWindow.close();
			}
		});
        
		DBWindow.open();
		DBWindow.layout();
		while (!DBWindow.isDisposed()) {
			//newWindow.setEnabled(false); // main window not clickable while this window is opened
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		newWindow.setEnabled(true);
		newWindow.setActive();
	}
}