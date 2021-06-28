package main;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

import exceptions.WrongInsertException;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;

//import javax.swing.*; 
//import java.awt.*;
//import java.awt.event.*;

public class App {

	protected Shell shlMatrixCalculator;
	private Text textInsert;
	private Text textBoxForFileNames;

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
		
		Button btnIsEqual = new Button(shlMatrixCalculator, SWT.NONE);
		btnIsEqual.setBounds(0, 0, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnIsEqual.setText("isEqual");
		btnIsEqual.setToolTipText("Check if two matrices are equal"); // for displaying text when hovering over the button
		
		
		Button btnAdd = new Button(shlMatrixCalculator, SWT.NONE);
		btnAdd.setBounds((shlMatrixCalculator.getSize().x - 20) / 7, 0, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnAdd.setText("Add");
		btnAdd.setToolTipText("Add two matrices");
		
		Button btnSubtract = new Button(shlMatrixCalculator, SWT.NONE);
		btnSubtract.setBounds(2 * (shlMatrixCalculator.getSize().x - 20) / 7, 0, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnSubtract.setText("Subtract");
		btnSubtract.setToolTipText("Subtract two matrices");
		
		Button btnScalar = new Button(shlMatrixCalculator, SWT.NONE);
		btnScalar.setBounds(3 * (shlMatrixCalculator.getSize().x - 20) / 7, 0, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnScalar.setText("Scalar");
		btnScalar.setToolTipText("Multiply a matrix with a scalar");
		
		Button btnMultiply = new Button(shlMatrixCalculator, SWT.NONE);
		btnMultiply.setBounds(4 * (shlMatrixCalculator.getSize().x - 20) / 7, 0, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnMultiply.setText("Multiply");
		btnMultiply.setToolTipText("Multiply two matrices");
		
		Button btnPotentiate = new Button(shlMatrixCalculator, SWT.NONE);
		btnPotentiate.setBounds(5 * (shlMatrixCalculator.getSize().x - 20) / 7, 0, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnPotentiate.setText("A^n");
		btnPotentiate.setToolTipText("Calculate A^n, where A is a matrix and n is a natural number between 1 and 15");
		
		Button btnPolynomial = new Button(shlMatrixCalculator, SWT.NONE);
		btnPolynomial.setBounds(6 * (shlMatrixCalculator.getSize().x - 20) / 7, 0, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnPolynomial.setText("Poly");
		btnPolynomial.setToolTipText("Calculate p(A), where A is a matrix and p is a polynomial");
		
		Button btnLU = new Button(shlMatrixCalculator, SWT.NONE);
		btnLU.setBounds(0, 30, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnLU.setText("LU");
		btnLU.setToolTipText("Calculate the LU decomposition of a matrix");
		
		Button btnTrace = new Button(shlMatrixCalculator, SWT.NONE);
		btnTrace.setBounds((shlMatrixCalculator.getSize().x - 20) / 7, 30, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnTrace.setText("Trace");
		btnTrace.setToolTipText("Calculate the trace of a matrix");
		
		Button btnRank = new Button(shlMatrixCalculator, SWT.NONE);
		btnRank.setBounds(2 * (shlMatrixCalculator.getSize().x - 20) / 7, 30, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnRank.setText("Rank");
		btnRank.setToolTipText("Calculate the rank of a matrix");
		
		Button btnDeterminant = new Button(shlMatrixCalculator, SWT.NONE);
		btnDeterminant.setBounds(3 * (shlMatrixCalculator.getSize().x - 20) / 7, 30, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnDeterminant.setText("Det");
		btnDeterminant.setToolTipText("Calculate the determinant of a matrix");
		
		Button btnLinearSystems = new Button(shlMatrixCalculator, SWT.NONE);
		btnLinearSystems.setBounds(4 * (shlMatrixCalculator.getSize().x - 20) / 7, 30, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnLinearSystems.setText("Lin Sys");
		btnLinearSystems.setToolTipText("For Ax=b, where A is a m×n matrix and b is m×1 matrix, calculate x");
		
		Button btnTranspose = new Button(shlMatrixCalculator, SWT.NONE);
		btnTranspose.setBounds(5 * (shlMatrixCalculator.getSize().x - 20) / 7, 30, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnTranspose.setText("Trans");
		btnTranspose.setToolTipText("Calculate the transposed matrix of the given one");
		
		Button btnInverse = new Button(shlMatrixCalculator, SWT.NONE);
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
					// umisto ovoga ispod (samo provjera radi li dobro) triba bit ubacivanje u bazu
					System.out.println("Cols: " + i.lastInserted.numOfCols);
					System.out.println("Rows: " + i.lastInserted.numOfRows);
					System.out.print("Elements: [");
					for(int j = 0; j < i.lastInserted.numOfRows; j++) {
						System.out.print("[");
						for(int k = 0; k < i.lastInserted.numOfCols; k++) {
							System.out.print(i.lastInserted.elements[j][k] + " ");
						}
						System.out.print("],");
					}
					System.out.print("]");
				} catch (WrongInsertException e1) {
					// TODO Auto-generated catch block
					MessageDialog.openError(shlMatrixCalculator, "Error", e1.message);
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
				Shell newWindow = new Shell(SWT.SHELL_TRIM & (~SWT.RESIZE));
				newWindow.setBounds(new Rectangle(200, 200, 500, 350));
	            newWindow.setText("Help");
				
	            Display display = Display.getDefault();
	            
	            Label test = new Label(newWindow, SWT.NONE);
	            test.setText("Just a test!");
	            test.setBounds(50, 50, 150, 50);
	            
	            Button test2 = new Button(newWindow, SWT.NONE);
	            test2.setText("Click me!");
	            test2.setBounds(50, 100, 70, 30);
	            test2.addSelectionListener(new SelectionAdapter() {
	            	@Override
	            	public void widgetSelected(SelectionEvent e) {
	            		newWindow.close();
	            	}
	            });
	            
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

	}
}