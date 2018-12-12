package project2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

import generics.Stack;
import generics.StackEmptyException;
import generics.StackFullException;

/**
 * <b>Title:</b> Project 2<br>
 * <b>Filename:</b> CalculatorFrame.java<br>
 * <b>Date Written:</b> October 28, 2018<br>
 * <b>Due Date:</b> October 28, 2018<br>
 * <p>
 * <b>Description:</b><br>
 * A calculator that converts an infix expression to a postfix expression
 * and calculates the expression
 * </p>


 *@author Abreu (edited by Ung Jae Yun)
 */



@SuppressWarnings("serial")
class CalculatorFrame extends JFrame implements ActionListener  {
	JTextField jtfInfix = new JTextField(21); // for infix get string from here
	JTextField jtfPostfix = new JTextField();  // for postix return postfix to here
	JTextField result = new JTextField("0");   // for result
	
	JButton[][] calcButton = new JButton[4][5];
	
	JPanel calcPanel = new JPanel();	
	JPanel topPanel = new JPanel();    

	
	public CalculatorFrame(){
		String[][] buttonText = 
				new String[][]{{"7","8","9","/","C"},{"4","5","6","*","B"},
				{"1","2","3","-","R"},{"0","(",")","+","="}};
				
		this.setTitle("CSCI212 Calculator");
		this.setLayout(new BorderLayout(2,1));

		jtfInfix.setHorizontalAlignment(JTextField.RIGHT);
		jtfPostfix.setHorizontalAlignment(JTextField.RIGHT);
		result.setHorizontalAlignment(JTextField.RIGHT);
		jtfPostfix.setEnabled(false);
		result.setEnabled(false);
		//jtfInfix.setEditable(false); // hide text caret
		
		// set the font size to 34 for the text fields
		Font textFieldFont=new Font(jtfPostfix.getFont().getName(),jtfPostfix.getFont().getStyle(),24);
		jtfInfix.setFont(textFieldFont);
		jtfPostfix.setFont(textFieldFont);
		result.setFont(textFieldFont);
		
		topPanel.setLayout(new GridLayout(3,1));				
		topPanel.add(jtfInfix);		
		topPanel.add(jtfPostfix);
		topPanel.add(result);
		
		calcPanel.setLayout(new GridLayout(4,5,3,3));
		
		for (int i = 0; i < 4; i++) {
			for(int j = 0; j < 5; j++) {
				calcButton[i][j]= new JButton("" + buttonText[i][j]);
				calcButton[i][j].setForeground(Color.blue);
				calcButton[i][j].setFont(new Font("sansserif",Font.BOLD,42));
				calcButton[i][j].addActionListener(this);
				calcButton[i][j].setBorder(BorderFactory.createRaisedBevelBorder());
				calcPanel.add(calcButton[i][j]);
			}
		}
		this.add(topPanel,BorderLayout.NORTH);
		this.add(calcPanel,BorderLayout.CENTER);
	}
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 4; i++) {
			for(int j = 0; j < 5; j++) {				
				if(e.getSource() == calcButton[i][j]){
					// clear
					if(i==0 && j == 4){
						jtfInfix.setText(null);
						jtfPostfix.setText(null);
						result.setText("0");
					}
					// backspace
					else if(i==1 && j == 4){
						if(jtfInfix.getDocument().getLength()>0)
							try {
								jtfInfix.setText(jtfInfix.getText(0, jtfInfix.getDocument().getLength()-1));
							} catch (BadLocationException e1) {
								e1.printStackTrace();
							}
						
					}
					// number or operator
					else if(j<4){
						jtfInfix.setText(jtfInfix.getText()
							+ calcButton[i][j].getText());
						}
					// = button pressed
					else if(i==3&&j==4){
						// erase contents of the postfix textfield
						jtfPostfix.setText(null);  
						// update the postfix textfield with the String returned  
						try {
							jtfPostfix.setText(infixToPostfix());
						} catch (StackEmptyException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						// update the result textfield with the result of the computation
						result.setText("" + calculate());
					}
				}
			}
		}
    }
	/**
	 * A helper function that checks if a string is a number
	 * @param token the string to be checked 
	 * @return <i>true</i> if token is a number
	 */
	private boolean isNumber(String token) {
		//for every index/character in the String token, the code checks if it is a number with .isDigit
		for (int i = 0; i < token.length(); i++) {
			if (!Character.isDigit(token.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	/**
	 * A helper function that evaluates an arithmetic expression
	 * written in String form into an answer as a type double
	 * @param left the left-hand operand
	 * @param op the operation to be processed between left and right
	 * @param right the right-hand operand 
	 * @return answer the double representation of the answer derived from doing op on left and right
	 */
	private double evaluate(String left, String op, String right) {
		double answer = 0;
			//checks which String form of operation is being fed, and executes the expression accordingly
			if (op.charAt(0) == "+".charAt(0)) {
				answer = Double.parseDouble(left) + Double.parseDouble(right);
			} else if (op.charAt(0) == "-".charAt(0)) {
				answer = Double.parseDouble(left) - Double.parseDouble(right);
			} else if (op.charAt(0) == "*".charAt(0)) {
				answer = Double.parseDouble(left) * Double.parseDouble(right);
			} else if (op.charAt(0) == "/".charAt(0)) {
				answer = Double.parseDouble(left) / Double.parseDouble(right);
			}
		return answer;
	}
	/**
	 * Helper function to compare two operators' precedence
	 * @param token operator String; current token
	 * @param peek operator String; current top of opStack 
	 * @return <i>1</i> if token has higher precedence; <i>0</i> if equal precedence; <i>-1</i> if token has less precedence
	 */
	private int hasPrec(String token, String peek) {
		// character form of both inputs
		char tokenChar = token.charAt(0);
		char peekChar = peek.charAt(0);
		int tokenInt = 0;
		int peekInt = 0;
		
		
		char[][] precedence = new char[2][2];
		precedence[0][0] = '*';
		precedence[0][1] = '/';
		precedence[1][0] = '+';
		precedence[1][1] = '-';
		
		// int value of input = 'row' index of precedence array
		// row 0 has higher precedence than row 1
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				if (precedence[i][j] == tokenChar) {
					tokenInt = i;
				} else if (precedence[i][j] == peekChar) {
					peekInt = i;
				}
			}
		}
		
		// subtract the int values of inputs to see precedence
		int prec = peekInt - tokenInt;
		return prec;
	}
	/**
	 * A function that takes in an infix arithmetic expression in the form of a String
	 * and converts it to a String of postfix arithmetic expression
	 * @return	result the string of the postfix expression
	 * @throws StackEmptyException thrown when the operator stack is empty
	 */
	public String infixToPostfix() throws StackEmptyException{		
		String result = "";
		
		// stack to push and pop operators to/from
		Stack opStack = new Stack();
		
		// take in String from the textfield and tokenizes it
		String expression = jtfInfix.getText();
		String delims = "*/+-() ";
		StringTokenizer strToken = new StringTokenizer(expression, delims, true);
		
		while(strToken.hasMoreTokens()){
			String token = strToken.nextToken();
			
			if(isNumber(token)) {
				// append any number into the String result
				result += token + " ";
			} else if (token.charAt(0) == " ".charAt(0)) {
				// skips over whitespace
				continue;
			} else if (token.charAt(0) == "(".charAt(0)) {
				// if the token is a left parenthesis, push onto stack for the time being
				// later reference point to pop until when the right parenthesis is encountered
				opStack.push(token);
				continue;
			} else if (delims.contains(token)) {
				
				try {
					// character expression of the top of the operator stack
					String strOp = "" + opStack.peek();
					char op = strOp.charAt(0);
					
					// if the top of the stack is a left paren, push to stack
					if (op == "(".charAt(0)) {
						opStack.push(token);
						
					// if we encounter the right parenthesis,
					// pop off all the operators in the stack until the left parenthesis
					} else if (token.charAt(0) == ")".charAt(0)) {
						while (op != "(".charAt(0)) {
							result += opStack.pop() + " ";
							strOp = "" + opStack.peek();
							op = strOp.charAt(0);
						}
						opStack.pop();
						
					// if the token has higher precedence than the top of the stack, push onto stack
					} else if (hasPrec(token, strOp) > 0) {
						opStack.push(token);
						
					// if the token has lower precedence than the top of the stack, 
					// pop off the stack until we encounter an operator of lower precedence
					// or until top of the stack is a left paren and then push the token onto the stack
					} else if (hasPrec(token, strOp) <= 0) {
						while (hasPrec(token, strOp) <= 0) {
							result += opStack.pop() + " ";
							strOp = "" + opStack.peek();
							if (strOp.charAt(0) == "(".charAt(0)) {
								break;
							}
						}
						opStack.push(token);
					} 
					
				//when the stack is empty, push the token onto the stack
				} catch (StackEmptyException e) {
					opStack.push(token);
				} 
			}
		}
		// after going through the entire infix String, pop off any remaining operators
		for (int i = 0; i <= opStack.getSize(); i++) {
			result += opStack.pop() + " ";
		}
		return result;
	}
	/**
	 * A function that takes a postfix arithmetic expression
	 * and calculates it into an answer with the helper function evaluate()
	 * @return ans the result of the arithmetic expression
	 */
	public String calculate() {
		String ans = "";
		
		// stack to push/pop operands to/from
		Stack evalStack = new Stack();
		
		// takes postfix String and tokenizes the expression
		String expression = jtfPostfix.getText();
		String delims = "*/+-() ";
		StringTokenizer strToken = new StringTokenizer(expression, delims, true);
		
		while (strToken.hasMoreTokens()){
			try {
				String token = strToken.nextToken();
				
				// numbers in the string are pushed onto the stack
				if (isNumber(token)) {
					evalStack.push(token);
					
				// skip over white spaces
				} else if (token.charAt(0) == " ".charAt(0)) {
					continue;
					
				// if the token is an operator, evaluate the expression
				} else if (delims.contains(token)) {
					String right = "" + evalStack.pop();
					String left = "" + evalStack.pop();
					
					// prints message if expression tries to divide by 0
					if (token.charAt(0) == "/".charAt(0) && right.charAt(0) == "0".charAt(0)) {
						ans = "You Can't Divide By Zero!";
						return ans;
					}
					double calc = evaluate(left, token, right);
					evalStack.push(calc);
				}
			} catch (StackEmptyException see) {
				System.out.println("line 213" + see);
				continue;
			}
		}
		// convert ans, the result of the expression, into a String
		try {
			ans = String.valueOf(evalStack.pop());
		} catch (StackEmptyException see) {
			System.out.println("line 219" + see);
		}
		return ans;
	}
	
	static final int MAX_WIDTH = 398, MAX_HEIGHT = 440;
	
	public static void main(String arg[]){
		JFrame frame = new CalculatorFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(MAX_WIDTH,MAX_HEIGHT);	
		frame.setBackground(Color.white);		
		frame.setResizable(false);				
		frame.setVisible(true);
	}
}
