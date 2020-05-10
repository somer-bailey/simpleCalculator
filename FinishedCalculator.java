import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;



public class FinishedCalculator extends JFrame {
	JTextArea displayArea;
	JPanel mainPanel;
	Font font1 = new Font("SansSerif", Font.BOLD, 35);
	Color[] colors = {Color.pink, Color.white, Color.lightGray, Color.magenta};
	int colorNum = 0;
	double answer;
	String expr;
	JButton AC;

	public FinishedCalculator(String title){
		super(title);
		setLayout(new GridLayout(2,1));
		setSize(750,700);
		setLocation(100,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		expr = "";

		this.displayArea = new JTextArea();
		this.displayArea.setBackground(Color.lightGray);
		this.displayArea.setEditable(false);
		add(this.displayArea);

		this.mainPanel = new JPanel(new GridLayout(6,4));
		this.createButtons();
	
		add(this.mainPanel);
		setVisible(true);
		
	}//end constructor
	
	public void createButtons(){
		//create buttons, add to listener
		
		JButton AC = new JButton("AC");
		AC.addActionListener(new ClearListener());
		AC.setFont(new Font("Arial", Font.PLAIN, 35));
		
		JButton plusmin = new JButton("+/-");
		plusmin.addActionListener(new OperatorListener());
		plusmin.setFont(new Font("Arial", Font.PLAIN, 35));
		
		JButton percent = new JButton("%");
		percent.addActionListener(new OperatorListener());
		percent.setFont(new Font("Arial", Font.PLAIN, 35));
		
		JButton divide = new JButton("÷");
		divide.addActionListener(new OperatorListener());
		divide.setFont(new Font("Arial", Font.PLAIN, 35));
		
		JButton openpar = new JButton("(");
		openpar.addActionListener(new OperatorListener());
		openpar.setFont(new Font("Arial", Font.PLAIN, 35));
		
		JButton closedpar = new JButton(")");
		closedpar.addActionListener(new OperatorListener());
		closedpar.setFont(new Font("Arial", Font.PLAIN, 35));
		
		JButton sqrt = new JButton("@");
		sqrt.addActionListener(new OperatorListener());
		sqrt.setFont(new Font("Arial", Font.PLAIN, 35));
		
		JButton exponent = new JButton("^");
		exponent.addActionListener(new OperatorListener());
		exponent.setFont(new Font("Arial", Font.PLAIN, 35));
		
		JButton seven = new JButton("7");
		seven.addActionListener(new ButtonListener());
		seven.setFont(new Font("Arial", Font.PLAIN, 35));
		
		JButton eight = new JButton("8");
		eight.addActionListener(new ButtonListener());
		eight.setFont(new Font("Arial", Font.PLAIN, 35));
		
		JButton nine = new JButton("9");
		nine.addActionListener(new ButtonListener());
		nine.setFont(new Font("Arial", Font.PLAIN, 35));
		
		JButton multiply = new JButton("*");
		multiply.addActionListener(new OperatorListener());
		multiply.setFont(new Font("Arial", Font.PLAIN, 35));
		
		JButton four = new JButton("4");
		four.addActionListener(new ButtonListener());
		four.setFont(new Font("Arial", Font.PLAIN, 35));
		
		JButton five = new JButton("5");
		five.addActionListener(new ButtonListener());
		five.setFont(new Font("Arial", Font.PLAIN, 35));
		
		JButton six = new JButton("6");
		six.addActionListener(new ButtonListener());
		six.setFont(new Font("Arial", Font.PLAIN, 35));
		
		JButton subtract = new JButton("-");
		subtract.addActionListener(new OperatorListener());
		subtract.setFont(new Font("Arial", Font.PLAIN, 35));
		
		JButton one = new JButton("1");
		one.addActionListener(new ButtonListener());
		one.setFont(new Font("Arial", Font.PLAIN, 35));
		
		JButton two = new JButton("2");
		two.addActionListener(new ButtonListener());
		two.setFont(new Font("Arial", Font.PLAIN, 35));
		
		JButton three = new JButton("3");
		three.addActionListener(new ButtonListener());
		three.setFont(new Font("Arial", Font.PLAIN, 35));
		
		JButton add = new JButton("+");
		add.addActionListener(new OperatorListener());
		add.setFont(new Font("Arial", Font.PLAIN, 35));
		
		JButton zero = new JButton("0");
		zero.addActionListener(new ButtonListener());
		zero.setFont(new Font("Arial", Font.PLAIN, 35));
		
		JButton blank = new JButton(" ");
		
		JButton decimal = new JButton(".");
		decimal.addActionListener(new ButtonListener());
		decimal.setFont(new Font("Arial", Font.PLAIN, 35));
		
		JButton equal = new JButton("=");
		equal.addActionListener(new EqualListener());
		equal.setFont(new Font("Arial", Font.PLAIN, 35));

		
	

			
		//add buttons to calculator in order
		
		this.mainPanel.add(AC);
		this.mainPanel.add(plusmin);
		this.mainPanel.add(percent);
		this.mainPanel.add(divide);
		this.mainPanel.add(openpar);
		this.mainPanel.add(closedpar);
		this.mainPanel.add(sqrt);
		this.mainPanel.add(exponent);
		this.mainPanel.add(seven);
		this.mainPanel.add(eight);
		this.mainPanel.add(nine);
		this.mainPanel.add(multiply);
		this.mainPanel.add(four);
		this.mainPanel.add(five);
		this.mainPanel.add(six);
		this.mainPanel.add(subtract);
		this.mainPanel.add(one);
		this.mainPanel.add(two);
		this.mainPanel.add(three);
		this.mainPanel.add(add);
		this.mainPanel.add(zero);
		this.mainPanel.add(blank);
		this.mainPanel.add(decimal);
		this.mainPanel.add(equal);
		
		
		
	}//end createButtons
	
	class ButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent ae){
			
			//making button clicks appear
			
			JButton button = (JButton)ae.getSource();
			String text = button.getText();
			
			displayArea.setFont(font1);
			button.setBackground(colors[colorNum++ % 2]);
			displayArea.append(text);
			expr = expr.concat(text);
			
		}
	}//end ButtonListener
	
	class OperatorListener implements ActionListener{
			
			public void actionPerformed(ActionEvent ae){
				
				//making button clicks appear
				JButton button = (JButton)ae.getSource();
				button.setBackground(colors[colorNum++ % 2]);
				String text = button.getText();
				
				//if +/- button is clicked then create new button that will equal -1 for postfix evaluation
				if(text=="+/-"){
					JButton plusmin2 = new JButton("(1-2)*");
					String text2 = plusmin2.getText();
					displayArea.setFont(font1);
					displayArea.append(text2);					
					expr = expr.concat(text2);
					
				//if % button is clicked then create new button that will divide button selected before
				}else if(text=="%"){
					JButton percent2 = new JButton("/100");
					String text2 = percent2.getText();
					displayArea.setFont(font1);
					displayArea.append(text2);
					expr = expr.concat(text2);
					
				}else{
					displayArea.setFont(font1);
					displayArea.append(text);
					expr = expr.concat(text);
				}	
			
		}
	}//end of operator listener		
	
	class ClearListener implements ActionListener{
			//clear text screen to perform different calculation
		
			public void actionPerformed(ActionEvent ae){
				JButton button = (JButton)ae.getSource();
				button.setBackground(colors[colorNum++ % 2]);
				String text = button.getText();
				if(button.getText().equals("AC")){
					displayArea.setText(null);
					button.setText("C");
					expr = "";
				}else if(button.getText().equals("C")){
					expr = expr.substring(0, expr.length() -1);
					displayArea.setText(expr);
					button.setText("AC");
				}else{
					button.setText("AC");
				}
					
			}
		}//end of clearlistener
	
	class EqualListener implements ActionListener{
		
		public void actionPerformed(ActionEvent ae){
			
			//getting String text from calculator screen
			String calculation = displayArea.getText();
			
			//prints to console what calculation will be performed, allows to keep up with calculations if desired
			System.out.println("You entered: "+ calculation);
			
			//converts calc expression to postfix and evaluates with type double result
			//try catch block if user inputs operators beside each other
			try{
				EvaluateExpression expression = new EvaluateExpression();
				ArrayList<String> postfixList = expression.infix2Postfix(calculation);
				double answer = expression.evaluatePostfix(postfixList);
				//prints to console what answer is to previous calculation, allows to keep up with calculations if desired
				System.out.println("Answer: " +answer);
				
				//display answer IN the calculator
				
				displayArea.setFont(font1);
				displayArea.append("= " + answer);
				
			}catch(Exception e){
				System.out.println("Invalid calculation. Try Again");
				displayArea.setText(null);
				
			}
			
		}
	}//end of equallistener
	
	
}//end class
