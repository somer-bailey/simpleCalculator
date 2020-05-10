
	import java.io.BufferedReader;
	import java.io.FileReader;
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.Scanner;
	import java.util.Stack;

	public class EvaluateExpression {
		private ArrayList<String> infixExpressions;
		private ArrayList<String> postfixExpressions;
		private Stack<String> stack;
		private char[] operatorsAndBrackets = {')', '(', '+', '-', '*', '/', '%', '^', 'p', '@'};
		private char[] operatorList = {'+', '-', '*', '/', '%', '^', '%', '@', 'p'};
		
		public EvaluateExpression() {
			infixExpressions = new ArrayList<String>(); 
			postfixExpressions = new ArrayList<String>();
		}
		
		public boolean getExpressionsFromFile(String fileName){
			try {
				FileReader fileObj = new FileReader(fileName);
				BufferedReader buffObj = new BufferedReader(fileObj);
				Scanner lineData;
				String line = buffObj.readLine();//try to read first line
				while(line != null){
					this.infixExpressions.add(line);
					line = buffObj.readLine();
				}
				buffObj.close();
				return true;
			}catch(IOException ex) {
				return false;
			}
		}//end of method

		public boolean isBalanced(String expr) {
			Stack<Character> localStack = new Stack<Character>();
	        boolean balanced = true;
	        int index = 0;
	        expr = expr.trim();
	        expr = expr.replace(" ", "");
			
	        //System.out.println("About to process " + expr);
	        
	        while(index < expr.length() && balanced) {
	            char symbol = expr.charAt(index);
	            if(symbol == '(') {
	            	localStack.push(symbol);
	            	//System.out.println("pushing " + symbol);
	            }
	            else if (symbol == ')') {
	                if(localStack.empty())
	                    balanced = false;
	                else {
	                    char item = localStack.pop();
	                    //System.out.println("popped " + item);
	                    if (item != '(')
	                        balanced = false;
	                }
	            }
	        	
	        	index += 1;
	        }
	        if(balanced && localStack.empty())
	            return true;
			return false;
		}
		
		public boolean contains(char[] charList, char token) {
			for(char ch : charList) {
				if(ch == token)
					return true;
			}
			return false;
		}

		//Check if op is of equal or higher precedence than token
		public boolean isEqualOrHigher(String op, char token) {
			//System.out.println("Checking if op " + op + " is of equal or higher precedence than token " + token);
			if(token == '+' || token == '-') {
	            if(op.contentEquals("+") || op.contentEquals("-") || op.contentEquals("*") || op.contentEquals("/") )
	            	return true;
	            if(op.contentEquals("^") || op.contentEquals("@"))
	                return true;
			}
	        else if (token == '*' || token == '/') {
	            if(op.contentEquals("*") || op.contentEquals("/") || op.contentEquals("^") || op.contentEquals("@"))
	                return true;
	        }
	        else if (token == '^') {
	            if(op.contentEquals("^") || op.contentEquals("@"))
	                return true;
	        }
	        else if (token == '@') {
	            if(op.contentEquals("@"))
	                return true;
	        }
			return false;
		}

		public boolean isValid(String expr) {
			
			return false;
		}
		public ArrayList<String> infix2Postfix(String expr) {
			expr = expr.replace(" ", "");
			String op;
			ArrayList<String> postfixList = new ArrayList<String>();
			char token;
			char[] infixList;
			this.stack = new Stack<String>();
			boolean valid = isBalanced(expr);
			if(valid == false)
	            return null;
			else {
				infixList = expr.toCharArray();
		        int index = 0;
		        while(index < infixList.length) {
		        	token = infixList[index];
		        	if(!contains(this.operatorsAndBrackets, token)) {//is an operand
	                    String num = "" + token;
	                    if(index < (infixList.length-1)) {//#if the list has at least one element
	                        char elem = infixList[index+1];//peek at the next element
	                        while ((Character.isDigit(elem) || elem=='.') && index < (infixList.length-1)) {
	                            index += 1;
	                            String val = "" + infixList[index];//get the next digit in the number
	                            num += val;// append the digit
	                            if(index < infixList.length-1) {//if the list has at least one element
	                                elem = infixList[index+1];//get next element
	                            }
	                        }
	                    }
	                    postfixList.add(num);
		        		
		        	}
		        	else if(token == '(') {
		        		this.stack.push("" + token);
		        	}
		        	else if(token == ')') {
		        		op = this.stack.pop();
			            while (!op.contentEquals("(") && op != null) {
			            	postfixList.add(op);
			            	//postfixList = postfixList + op;
			                op = this.stack.pop();
			            }
		        	}
		        	else if(contains(this.operatorList, token)) {
		        		if(!stack.empty()) {
		        			op = this.stack.peek();//pop equal or higher than token
			                while (op != null && isEqualOrHigher(op, token)) {
			                	op = this.stack.pop();
			                	 postfixList.add(op);
			                	if(this.stack.empty())
			                		op = null;
			                	else
			                		op = this.stack.peek();
			                }
		        		}
		        		this.stack.push("" + token);
		        	}
		        	index += 1;
		        }

	            while(!this.stack.empty()) {
	                String ch = this.stack.pop();
	                if(contains(this.operatorList, ch.trim().charAt(0)))
	                	postfixList.add(ch);
	                	//postfixList = postfixList + ch;
	                else {
	                	postfixList = null;
	                	break;
	                }
	            }
			 }//end of else
			
			return postfixList;
		}//end of method

		public double evaluatePostfix(ArrayList<String> postfixList) {
			double operand1, operand2, result=0;
	        //char[] postfixList = postfix.toCharArray();
	        Stack stack = new Stack<Double>();
	        for(int i=0; i<postfixList.size(); i++) {
	        	String token = postfixList.get(i);
	            if (!contains(this.operatorList, token.trim().charAt(0))) {//then operand
	            	double value = new Double(token).doubleValue();
	            	stack.push(value);
	            }
	            else if(token.contentEquals("+")) {
	                operand2 = (double)stack.pop();
	                operand1 = (double)stack.pop();
	                result = operand1 + operand2;
	                stack.push(result);
	            }
	            else if (token.contentEquals("-")) {
	                operand2 = (double)stack.pop();
	                operand1 = (double)stack.pop();
	                result = operand1 - operand2;
	                stack.push(result);
	            }
	            else if(token.contentEquals("*")){
	                operand2 = (double)stack.pop();
	                operand1 = (double)stack.pop();
	                result = operand1 * operand2;
	                stack.push(result);
	            }
	            else if (token.contentEquals("/")){
	                operand2 = (double)stack.pop();
	                operand1 = (double)stack.pop();
	                result = operand1 / operand2;
	                stack.push(result);
	            }
	            else if (token.contentEquals("^")){
	                operand2 = (double)stack.pop();
	                operand1 = (double)stack.pop();
	                result = Math.pow(operand1, operand2);
	                stack.push(result);
	            }
	            else if (token.contentEquals("@")){
	                operand1 = (double)stack.pop();
	                result = Math.sqrt(operand1);
	                stack.push(result);
	            }
	            
	        	
	        }
	        double answer = (double)stack.pop();
	        return answer;
	 	}

	}//end of class