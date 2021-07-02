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
		btnIsEqual.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openNewWindow(2, "Are matrices Equal");
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
			}
		});
		btnPotentiate.setBounds(5 * (shlMatrixCalculator.getSize().x - 20) / 7, 0, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnPotentiate.setText("A^n");
		btnPotentiate.setToolTipText("Calculate A^n, where A is a matrix and n is a natural number between 1 and 15");
		
		Button btnPolynomial = new Button(shlMatrixCalculator, SWT.NONE);
		btnPolynomial.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openNewWindow(3, "Polynomial");
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
			}
		});
		btnLinearSystems.setBounds(4 * (shlMatrixCalculator.getSize().x - 20) / 7, 30, (shlMatrixCalculator.getSize().x - 20) / 7, 30);
		btnLinearSystems.setText("Lin Sys");
		btnLinearSystems.setToolTipText("For Ax=b, where A is a m×n matrix and b is m×1 matrix, calculate x");
		
		Button btnTranspose = new Button(shlMatrixCalculator, SWT.NONE);
		btnTranspose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openNewWindow(1, "Find transposed matrix");
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

	}
	
	
	/**
	 * Method needed to open and customize a new window
	 * @param id needed to distinguish between different types of windows
	 * @param whichOne which button was clicked
	 */
	public void openNewWindow(int id, String whichOne) {
		Shell newWindow = new Shell(SWT.SHELL_TRIM & (~SWT.RESIZE));
		Display display = Display.getDefault();
		boolean check = true;
		
		switch(id) {
			case 0:
				customizeHelp(newWindow);
				break;
			case 1: // not wrong, it only means that the same thing is done
			case 2:
				check = customizeOperations(newWindow, id, whichOne);
				break;
			case 3:
				check = customizeOtherOperations(newWindow, whichOne);
				break;
			case 4:
				check = customizeScalarOperations(newWindow, whichOne);
				break;
			default:
				break;
		};
		
		if(!check) {
			// obrisi sve i stavi label
		}
        
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
		newWindow.setBounds(new Rectangle(200, 200, 500, 350));
        newWindow.setText("Help");
        
        Label test = new Label(newWindow, SWT.NONE);
        test.setText("Just a test! Here goes the text for help.");
        test.setBounds(50, 50, 150, 50);
	}
	
	/**
	 * Method for customizing new window for unary and binary operations
	 * @param newWindow shell representing a new window
	 * @param howMany integer, is the operation unary (1) or binary (2)
	 * @param whichOne string representing an operation
	 * @return true if creation succeded, false otherwise
	 */
	public boolean customizeOperations(Shell newWindow, int howMany, String whichOne) {
		newWindow.setBounds(200, 200, howMany * 350, 400);
		newWindow.setText(whichOne);
		Text textboxes[] = new Text[howMany * 2];
		Button buttons[] = new Button[howMany * 3];
		boolean success = true;
		
		Insert insert = new Insert();
		
		for(int i = 0; i < howMany; i++) {
			int curr = 2 * i, curr2 = 3 * i;
			textboxes[curr] = new Text(newWindow, SWT.NONE);
			textboxes[curr].setBounds(28 + i * 350, 5, 201, 108);
			
			buttons[curr2] = new Button(newWindow, SWT.NONE);
			buttons[curr2].setText("Insert matrix");
			buttons[curr2].setBounds(125 + i * 350, 130, 100, 30);
			buttons[curr2].addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					try {
						insert.insertFromTextBox(textboxes[curr].getText()); // ima jos par exceptiona, to cu posli
					} catch (WrongInsertException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						buttons[curr2].setEnabled(false);
					}
				}
			});
			if(!buttons[curr2].isEnabled()) {
				success = false;
				break;
			}
			
			textboxes[curr + 1] = new Text(newWindow, SWT.NONE);
			textboxes[curr + 1].setBounds(28 + i * 350, 180, 201, 50);
			
			buttons[curr2 + 1] = new Button(newWindow, SWT.NONE);
			buttons[curr2 + 1].setText("Insert from file");
			buttons[curr2 + 1].setBounds(100 + i * 350, 250, 130, 30);
			buttons[curr2 + 1].addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					insert.insertFromFile(textboxes[curr + 1].getText());
				}
			});
			
			buttons[curr2 + 2] = new Button(newWindow, SWT.NONE);
			buttons[curr2 + 2].setText("From database");
			buttons[curr2 + 2].setBounds(100 + i * 350, 290, 130, 30);
			buttons[curr2 + 2].addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					insert.insertFromDatabase();
				}
			});
		}
		return success;
	}
	
	/**
	 * Method for customizing new window for other operations
	 * @param newWindow shell representing a new window
	 * @param whichOne string representing an operation
	 * @return true if creation succeded, false otherwise
	 */
	public boolean customizeOtherOperations(Shell newWindow, String whichOne) {
		newWindow.setBounds(200, 200, 500, 400);
		newWindow.setText(whichOne);
		boolean success = true;
		
		Insert insert = new Insert();
		
		Text textbox1 = new Text(newWindow, SWT.NONE);
		textbox1.setBounds(28, 5, 180, 108);
		
		Button btn1 = new Button(newWindow, SWT.NONE);
		btn1.setText("Insert matrix");
		btn1.setBounds(125, 130, 100, 30);
		btn1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					insert.insertFromTextBox(textbox1.getText()); // ima jos par exceptiona, to cu posli
				} catch (WrongInsertException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					btn1.setEnabled(false);
				}
			}
		});
		if(!btn1.isEnabled()) {
			success = false;
		}
		
		Text textbox2 = new Text(newWindow, SWT.NONE);
		textbox2.setBounds(28, 180, 180, 50);
		
		Button btn2 = new Button(newWindow, SWT.NONE);
		btn2.setText("Insert from file");
		btn2.setBounds(100, 250, 130, 30);
		btn2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				insert.insertFromFile(textbox2.getText());
			}
		});
		
		Button btn3 = new Button(newWindow, SWT.NONE);
		btn3.setText("From database");
		btn3.setBounds(100, 290, 130, 30);
		btn3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				insert.insertFromDatabase();
			}
		});
		
		Text textbox3 = new Text(newWindow, SWT.NONE);
		textbox3.setBounds(28 + 180 + 10, 180, 180, 50);
		
		Button btn4 = new Button(newWindow, SWT.NONE);
		btn4.setText("Insert poly/column");
		btn4.setBounds(280, 250, 130, 30);
		btn4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btn4.setEnabled(false);
			}
		});
		if(!btn4.isEnabled()) {
			// String nums[] = textbox3.getText().split(" ");
			// strpaj to u neki double[]
		}
		
		return success;
	}
	
	
	public boolean customizeScalarOperations(Shell newWindow, String whichOne) {
		newWindow.setBounds(200, 200, 500, 400);
		newWindow.setText(whichOne);
		boolean success = true;
		
		Insert insert = new Insert();
		
		Text textbox1 = new Text(newWindow, SWT.NONE);
		textbox1.setBounds(28, 5, 180, 108);
		
		Button btn1 = new Button(newWindow, SWT.NONE);
		btn1.setText("Insert matrix");
		btn1.setBounds(125, 130, 100, 30);
		btn1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					insert.insertFromTextBox(textbox1.getText()); // ima jos par exceptiona, to cu posli
				} catch (WrongInsertException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					btn1.setEnabled(false);
				}
			}
		});
		if(!btn1.isEnabled()) {
			success = false;
		}
		
		Text textbox2 = new Text(newWindow, SWT.NONE);
		textbox2.setBounds(28, 180, 180, 50);
		
		Button btn2 = new Button(newWindow, SWT.NONE);
		btn2.setText("Insert from file");
		btn2.setBounds(100, 250, 130, 30);
		btn2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				insert.insertFromFile(textbox2.getText());
			}
		});
		
		Button btn3 = new Button(newWindow, SWT.NONE);
		btn3.setText("From database");
		btn3.setBounds(100, 290, 130, 30);
		btn3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				insert.insertFromDatabase();
			}
		});
		
		Text textbox3 = new Text(newWindow, SWT.NONE);
		textbox3.setBounds(28 + 180 + 10, 180, 180, 50);
		
		Button btn4 = new Button(newWindow, SWT.NONE);
		btn4.setText("Insert scalar");
		btn4.setBounds(280, 250, 130, 30);
		btn4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btn4.setEnabled(false);
			}
		});
		if(!btn4.isEnabled()) {
			// Double scalar = Double.parseDouble(textbox3.getText());
			// ako je potenciranje parseInt
		}
		
		return success;
	}
}