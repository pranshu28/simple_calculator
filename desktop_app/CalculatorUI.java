package func;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;

public class CalculatorUI extends Frame{
	private static final long serialVersionUID = 1L;

	private JFrame mainFrame = new JFrame("Calculator");
	private JLabel editorLabel = new JLabel("Input : ");
	private JLabel editor = new JLabel("0");
	private JLabel statusLabel = new JLabel("Answer : ");
	private JLabel status = new JLabel("0");
	private JLabel note = new JLabel();
	
	public String digitList[] = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "0", ".", "+/-"};
	public int digitCode[] = {103,104,105,100,101,102,97,98,99,96,110,109};
	public String operatorList[] = {"/", "*", "^", "-", "+", "%"};
	public int operatorCode[] = {111, 106, 1,109 ,107, 7};
	public String extraList[] = {"BackSpace", "=", "C"};
	
	public JButton digitButton[] = new JButton[digitList.length];
	public JButton operatorButton[] = new JButton[operatorList.length];
	public JButton extraButton[] = new JButton[extraList.length];
	
	final int BLOCK_Y = 70;
	final int BLOCK_X = 70;
	final int WIDTH = (int)Math.round(.8 * BLOCK_Y);
	final int HEIGHT = (int)Math.round(.8 * BLOCK_X);
	final int START_Y = (int)Math.round(.2 * BLOCK_Y);
	final int START_X = (int)Math.round(.2 * BLOCK_X);
	

/************FRONT - END************/
    
    public void outputCalc() {
   	 	//Frame
		mainFrame.setSize (BLOCK_X *6 + 22, BLOCK_Y *6 + 50);
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
	        }  
		});    
	
		//Panels
		editorLabel.setBounds(START_X, START_Y, (BLOCK_X *2) - START_X, HEIGHT);
		editor.setBounds (START_X + (BLOCK_X *2), START_Y, (BLOCK_X *4) - START_X, HEIGHT); 
		statusLabel.setBounds(START_X, START_Y + BLOCK_Y, (BLOCK_X *2) - START_X, HEIGHT / 2);
		status.setBounds (START_X + (BLOCK_X *2), START_Y + BLOCK_Y, (BLOCK_X *4) - START_X, HEIGHT / 2);
		note.setBounds(START_X, START_Y + BLOCK_Y + HEIGHT / 2 + 7, (BLOCK_X *6) - START_X, HEIGHT /2);
		note.setFont(new Font("Verdana", Font.PLAIN, 10));
		mainFrame.add(editorLabel);
		mainFrame.add(editor);
		mainFrame.add(statusLabel);
		mainFrame.add(status);
		mainFrame.add(note);
		
		//Digits Buttons
		int digit_i = 0;
		for (int i = 0; i < digitList.length; i++) {
			digitButton[i] = new JButton(digitList[i]);
			digitButton[i].setActionCommand(digitList[i]);
			digitButton[i].addActionListener(buttonPressed);
			digitButton[i].getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW)
			  			  .put(KeyStroke.getKeyStroke(digitList[i]), digitList[i]);
			digitButton[i].getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW)
			  			  .put(KeyStroke.getKeyStroke(digitCode[i],0), digitList[i]);
			digitButton[i].getActionMap().put(digitList[i], buttonPressed);
			mainFrame.add(digitButton[i]);
		}
		for (int temp_y = START_Y + BLOCK_Y * 2; temp_y <= START_Y + BLOCK_Y * 5; temp_y += BLOCK_Y) {
			for (int temp_x = START_X; temp_x <= START_X + BLOCK_X * 2; temp_x += BLOCK_X) {
				digitButton[digit_i].setBounds (temp_x, temp_y, WIDTH, HEIGHT);
				digit_i++;
			}
		}
	
		int start_x = START_X + BLOCK_X * 3;
		int start_y = START_Y + BLOCK_Y * 2;
	
		//Operator Buttons
		int operator_i=0;
		for (int i = 0; i < operatorList.length; i++) {
			operatorButton[i] = new JButton(operatorList[i]);
			operatorButton[i].setActionCommand(operatorList[i]);
			operatorButton[i].addActionListener(buttonPressed);
			operatorButton[i].getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW)
			  			  	 .put(KeyStroke.getKeyStroke(operatorCode[i],0), operatorList[i]);
			operatorButton[i].getActionMap().put(operatorList[i], buttonPressed);
			mainFrame.add(operatorButton[i]);
		}
		for (int temp_y = start_y + BLOCK_Y; temp_y <= start_y + BLOCK_Y * 2; temp_y += BLOCK_Y) {
			for (int temp_x = start_x; temp_x <= start_x + BLOCK_X * 2; temp_x += BLOCK_X) {
				operatorButton[operator_i].setBounds (temp_x, temp_y, WIDTH, HEIGHT);
				operator_i++;
			}
		}

		//Extra Buttons
		for (int i = 0; i < extraList.length; i++) {
			extraButton[i] = new JButton(extraList[i]);
			extraButton[i].setActionCommand(extraList[i]);
			extraButton[i].addActionListener(buttonPressed);
			mainFrame.add(extraButton[i]);
		}
		extraButton[0].getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW)
		  			  .put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE,0), extraList[0]);
		extraButton[0].getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW)
		  			  .put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0), extraList[0]);
		extraButton[0].getActionMap().put(extraList[0], buttonPressed);
		extraButton[1].getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW)
		  			  .put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), extraList[1]);
		extraButton[1].getActionMap().put(extraList[1], buttonPressed);
		extraButton[2].getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW)
		  			  .put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), extraList[2]);
		extraButton[2].getActionMap().put(extraList[2], buttonPressed);
		
		extraButton[0].setBounds (start_x, start_y, START_X + WIDTH * 2, HEIGHT);
		extraButton[1].setBounds (start_x, start_y + BLOCK_Y * 3, (BLOCK_X *3) - START_X, HEIGHT);
		extraButton[2].setBounds (start_x + BLOCK_X *2, start_y, WIDTH, HEIGHT);
				
				
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setVisible(true);
	}

/****************BACK - END****************/
	Calculator calc = new Calculator();
	
	public String queue = "";
	
	//Restart Calculation
	private void restartCalculator() {
		queue = "";
		editor.setText(queue);
		status.setText("0");
		calc.currentVariable = 0.0;
	}
	
	//Digit Called
	public void callDigit(String d) {
		//Previous Click: "Equals to"
		if (queue.length() > 1 && queue.charAt(queue.length()-1) == '=' )  {
			restartCalculator();
		}
		//"+/-" Function
		if ("+/-".contains(d) && queue.length() > 0) {
			if (queue.length() > 1) {
				if ("-".contains(queue.substring(1,2)) && queue.length() > 2) {
					queue = queue.substring(0,1) + queue.substring(2);
				}
				else if ("/*+-%^".contains(queue.substring(0,1))) {
					queue = ("0".contains(status.getText()))
							? queue.substring(1)
							: queue.substring(0,1) + "-" + queue.substring(1);
				}
				else {
					queue = "-" + queue;
				}
			}
			else if (queue.length() == 1){
				if ("0".contains(status.getText())) {
					queue = "" ;
				}
				else if ("/*+-%^".contains(queue.substring(0,1))) {
					queue += "-";
				}
				else {
					queue = "-" + queue;
				}
			}
		}
		//Otherwise
		else {
			if ("+/-".contains(d)) { d = "-"; }
			queue += d;
		}
		editor.setText(queue);
	}
	
	//"Operator" + "Number" Condition
	private boolean checkOperatorNumber() {
		return ( "/*+-%^".contains(queue.substring(0,1)) 					//First character is operator
				 || (queue.length() > 2 && queue.substring(1,2) == "-" ) )	//Next character is "+/-" for negative numbers
				 && !queue.matches( status.getText() ); 					//Not a Negative Number
	}

	//Operator Called
	public void callOperator(String op) {
		//Previous Click: "Equals to"
		if ("=".contains( queue.substring( queue.length()-1, queue.length() ) ) ) {
			queue = queue.substring(0,queue.length()-1);	
		}
		//Previous Status: "Operator"
		if ( "/*+-%^".contains( queue.substring( queue.length()-1 ) ) ) {
			queue = queue.substring(0,queue.length()-1) + op;
		}
		//Previous Status: "Operator" + "Number"
		else if ( checkOperatorNumber() ) {
			calc.useCalculator (queue.charAt(0),Double.parseDouble ( queue.substring(1) ) );
			status.setText(Double.toString(calc.currentVariable));
			queue = op;
		} 
		//Previous Status: "Number"
		else {
			calc.currentVariable = Double.parseDouble(queue);
			status.setText(queue);		
			queue = op;
		}
		note.setText(status.getText() + " " + op);
	}
	
	//Extra button Called
	public void callExtra(String e) {
		//Backspace or Delete or "BackSpace"
		if (e.matches("\b") || e.matches("\u007F") || e.matches("BackSpace")) {
			queue = (queue.length() == 1)
					? queue = "0" 
					: queue.substring(0,queue.length()-1); 
			calc.currentVariable = ( "/-%*+^".contains( queue.substring(0,1)) )
								   ? ( queue.length() != 1  
									   ? Double.parseDouble(queue.substring(1))
									   : calc.currentVariable )  
								   : Double.parseDouble(queue);
			editor.setText(queue);
		} 
		//Enter or "="  
		else if (e.matches("\n") || e.matches("=")) {
			String editorText = editor.getText();
			//Last Click: "="
			if ("=".contains( queue.substring( queue.length()-1) )) {
				queue = queue.substring(0,queue.length()-1);	
				calc.currentVariable = Double.parseDouble (queue);
				calc.useCalculator (editorText.charAt(0), Double.parseDouble ( editorText.substring(1) ));
			} 
			//Last Status: "First Input Number"
			else if ("0".matches(status.getText())) {
				calc.currentVariable = Double.parseDouble (queue);
			} 
			else {
				//Last Click: "Operator"
				if ( "/-%*+^".contains( queue.substring( queue.length()-1) ) ) {
					editor.setText(Double.toString(calc.currentVariable));
					calc.useCalculator (queue.charAt(queue.length()-1), calc.currentVariable);			
				} 
				//Last Status: "Operator" + "Number"
				else if ( checkOperatorNumber() ) {
					calc.useCalculator ( queue.charAt(0), Double.parseDouble ( queue.substring(1) ) );
				} 
			}
			status.setText(Double.toString(calc.currentVariable));
			queue = status.getText() + "=";	
			note.setText(" = " + status.getText());
		} 
		//"C" or Cancel or Restart
		else {
			note.setText("");
			restartCalculator();
		}
	}
	
	//Action to be taken on any click
	AbstractAction buttonPressed = new AbstractAction() {
		private static final long serialVersionUID = 1L;
		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			try {
				//Case: Digit Button
				if (Arrays.asList(digitList).contains(command)) {
					callDigit(command);				
				} 
				//Case: Operator Button
				else if (Arrays.asList(operatorList).contains(command)) {
					callOperator(command);
				} 
				//Case: Extra Button
				else {
					callExtra(command);
				}
				//Mathematical Error in previous Answer
				if ("-Infinity".contains(status.getText()) || "NaN".matches(status.getText())) {
					note.setText( "MATHEMATICAL ERROR : " + status.getText() +  ".    Start Again !!!");
					restartCalculator();
				}
				//System.out.println(status.getText() + " : " + queue );
			} 
			catch (Exception error) {
				note.setText("INPUT ERROR.   Try Again !!!");
			}
        	}
     	};
}
