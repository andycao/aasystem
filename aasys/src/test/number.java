package test;

import static org.junit.Assert.*;

import java.text.DecimalFormat;

import org.junit.Test;

public class number {

	@Test
	public void test() {
		double num = 0.000133333;
		double result = 1;
		for(int i =0; i <365;i++){
			result *= (1 +num);
		}
		DecimalFormat df = new DecimalFormat("0.0000");
		System.out.println(df.format(result-1));
	}

}
