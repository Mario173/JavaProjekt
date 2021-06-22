package main;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
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
		shlMatrixCalculator.setBounds(new Rectangle(100, 100, 1024, 512));
		shlMatrixCalculator.setText("Matrix Calculator");
		
		Button btnIsEqual = new Button(shlMatrixCalculator, SWT.NONE);
		btnIsEqual.setBounds(0, 0, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnIsEqual.setText("isEqual");
		
		
		Button btnAdd = new Button(shlMatrixCalculator, SWT.NONE);
		btnAdd.setBounds((shlMatrixCalculator.getSize().x - 20) / 7, 0, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnAdd.setText("Add");
		
		Button btnSubtract = new Button(shlMatrixCalculator, SWT.NONE);
		btnSubtract.setBounds(2 * (shlMatrixCalculator.getSize().x - 20) / 7, 0, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnSubtract.setText("Subtract");
		
		Button btnScalar = new Button(shlMatrixCalculator, SWT.NONE);
		btnScalar.setBounds(3 * (shlMatrixCalculator.getSize().x - 20) / 7, 0, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnScalar.setText("Scalar");
		
		Button btnMultiply = new Button(shlMatrixCalculator, SWT.NONE);
		btnMultiply.setBounds(4 * (shlMatrixCalculator.getSize().x - 20) / 7, 0, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnMultiply.setText("Multiply");
		
		Button btnPotentiate = new Button(shlMatrixCalculator, SWT.NONE);
		btnPotentiate.setBounds(5 * (shlMatrixCalculator.getSize().x - 20) / 7, 0, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnPotentiate.setText("A^n");
		
		Button btnPolynomial = new Button(shlMatrixCalculator, SWT.NONE);
		btnPolynomial.setBounds(6 * (shlMatrixCalculator.getSize().x - 20) / 7, 0, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnPolynomial.setText("Poly");
		
		Button btnLU = new Button(shlMatrixCalculator, SWT.NONE);
		btnLU.setBounds(0, 30, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnLU.setText("LU");
		
		Button btnTrace = new Button(shlMatrixCalculator, SWT.NONE);
		btnTrace.setBounds((shlMatrixCalculator.getSize().x - 20) / 7, 30, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnTrace.setText("Trace");
		
		Button btnRank = new Button(shlMatrixCalculator, SWT.NONE);
		btnRank.setBounds(2 * (shlMatrixCalculator.getSize().x - 20) / 7, 30, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnRank.setText("Rank");
		
		Button btnDeterminant = new Button(shlMatrixCalculator, SWT.NONE);
		btnDeterminant.setBounds(3 * (shlMatrixCalculator.getSize().x - 20) / 7, 30, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnDeterminant.setText("Det");
		
		Button btnLinearSystems = new Button(shlMatrixCalculator, SWT.NONE);
		btnLinearSystems.setBounds(4 * (shlMatrixCalculator.getSize().x - 20) / 7, 30, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnLinearSystems.setText("Lin Sys");
		
		Button btnTranspose = new Button(shlMatrixCalculator, SWT.NONE);
		btnTranspose.setBounds(5 * (shlMatrixCalculator.getSize().x - 20) / 7, 30, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnTranspose.setText("Trans");
		
		Button btnInverse = new Button(shlMatrixCalculator, SWT.NONE);
		btnInverse.setBounds(6 * (shlMatrixCalculator.getSize().x - 20) / 7, 30, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnInverse.setText("Inverse");
		
		textInsert = new Text(shlMatrixCalculator, SWT.BORDER);
		textInsert.setBounds(28, 97, 201, 108);
		
		Label lblInsertMatrix1 = new Label(shlMatrixCalculator, SWT.NONE);
		lblInsertMatrix1.setBounds(28, 71, 96, 20);
		lblInsertMatrix1.setText("Insert Matrix:");
		
		Button btnInsert = new Button(shlMatrixCalculator, SWT.NONE);
		btnInsert.setBounds(139, 211, 90, 30);
		btnInsert.setText("Insert Matrix");
		
		Button btnHelp = new Button(shlMatrixCalculator, SWT.NONE);
		btnHelp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnHelp.setBounds(0, 163 * shlMatrixCalculator.getSize().y / 192, 90, 30);
		btnHelp.setText("Help");
	
		shlMatrixCalculator.setVisible(true);
		
		Label lblInsertMatrix2 = new Label(shlMatrixCalculator, SWT.NONE);
		lblInsertMatrix2.setBounds(28, 259, 118, 20);
		lblInsertMatrix2.setText("Insert File Name:");
		
		textBoxForFileNames = new Text(shlMatrixCalculator, SWT.BORDER);
		textBoxForFileNames.setBounds(28, 285, 201, 26);
		
		Button btnInsertFileName = new Button(shlMatrixCalculator, SWT.NONE);
		btnInsertFileName.setBounds(139, 317, 90, 30);
		btnInsertFileName.setText("Insert Name");

	}
}
