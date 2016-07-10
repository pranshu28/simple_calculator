package func;

public class Calculator {
	public double currentVariable = 0;
	
	private void add(double secondOperand) {
		this.currentVariable += secondOperand;
	}
	
	private void subtract(double secondOperand) {
		this.currentVariable -= secondOperand;
	}
	
	private void multiply(double secondOperand) {
		this.currentVariable *= secondOperand;
	}
	
	private void divide(double secondOperand) {
		this.currentVariable /= secondOperand;
	}
	
	private void modulo(double secondOperand) {
		this.currentVariable %= secondOperand;
	}
	
	private void power(double secondOperand) {
		this.currentVariable = (double)Math.pow(currentVariable, secondOperand);
	}
	
	//Call Functions
	public void useCalculator( char operator, double input) {
		switch (operator) {
			case '+':
				add(input);
				break;
			case '-':
				subtract(input);
				break;
			case '*':
				multiply(input);
				break;
			case '/':
				divide(input);
				break;
			case '%':
				modulo(input);
				break;
			case '^':
				power(input);
				break;
		}
	}
}
