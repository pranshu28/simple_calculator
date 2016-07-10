package func;

import java.util.Arrays;

public class Logic{
	
	protected String digitList[] = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "0", ".", "+/-"};
	protected String operatorList[] = {"/", "*", "^", "-", "+", "%"};
	public String editor = "0";
	public String status = "0";
	public String note;
	
	CallFunction calc = new CallFunction();
	public String queue = "";

	//Current Answer
	public double getAns() {
		return calc.currentVariable;
	}
	     
	//Call on command
 	protected void caller(String command) {
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
 			if ("-Infinity".contains(status) || "NaN".matches(status)) {
 				note =  "MATHEMATICAL ERROR : " + status +  ".    Start Again !!!";
 				restartCalculator();
 			}
 			//System.out.println(status + " : " + queue );
 		} 
 		catch (Exception error) {
 			note = "INPUT ERROR.   Try Again !!!";
 		}
 	}
 	
 	//Restart Calculation
	public void restartCalculator() {
		queue = "";
		editor = queue;
		status = "0";
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
					queue = ("0".contains(status))
							? queue.substring(1)
							: queue.substring(0,1) + "-" + queue.substring(1);
				}
				else {
					queue = "-" + queue;
				}
			}
			else if (queue.length() == 1){
				if ("0".contains(status)) {
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
		editor = queue;
	}

	//"Operator" + "Number" Condition
	private boolean checkOperatorNumber() {
		return ( "/*+-%^".contains(queue.substring(0,1)) 					//First character is operator
				 || (queue.length() > 2 && queue.substring(1,2) == "-" ) )	//Next character is "+/-" for negative numbers
				 && !queue.matches( status ); 					//Not a Negative Number
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
			status = Double.toString(calc.currentVariable);
			queue = op;
		} 
		//Previous Status: "Number"
		else {
			calc.currentVariable = Double.parseDouble(queue);
			status = queue;		
			queue = op;
		}
		note = status + " " + op;
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
			editor = queue;
		} 
		//Enter or "="  
		else if (e.matches("\n") || e.matches("=")) {
			String editorText = editor;
			//Last Click: "="
			if ("=".contains( queue.substring( queue.length()-1) )) {
				
				queue = queue.substring(0,queue.length()-1);	
				calc.currentVariable = Double.parseDouble (queue);
				calc.useCalculator (editorText.charAt(0), Double.parseDouble ( editorText.substring(1) ));
			} 
			//Last Status: "First Input Number"
			else if ("0".matches(status)) {
				calc.currentVariable = Double.parseDouble (queue);
			} 
			else {
				//Last Click: "Operator"
				if ( "/-%*+^".contains( queue.substring( queue.length()-1) ) ) {
					
					editor = Double.toString(calc.currentVariable);
					calc.useCalculator (queue.charAt(queue.length()-1), calc.currentVariable);			
				} 
				//Last Status: "Operator" + "Number"
				else if ( checkOperatorNumber() ) {
					
					calc.useCalculator ( queue.charAt(0), Double.parseDouble ( queue.substring(1) ) );
				} 
			}
			status = Double.toString(calc.currentVariable);
			queue = status + "=";	
			note = " = " + status;
		}
		//"C" or Cancel or Restart
		else {
			note = "";
			restartCalculator();
		}
	}
}
