package tn.teamwill.tnrfx.util;

import bsh.EvalError;
import bsh.Interpreter;

public class LL {
public static void main(String[] args) throws EvalError {
	
	Interpreter interpreter = new Interpreter();
	String code ="int i = 0;tn.teamwill.tnrfx.util.LL ll = new tn.teamwill.tnrfx.util.LL();i++;ll.test(i);";
	interpreter.eval(code);
}

public  void test(int i)
{
	System.out.println(i);
	
	
	
}
}
