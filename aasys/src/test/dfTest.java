package test;

import java.text.DecimalFormat;

public class dfTest {

	public dfTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Double num = Double.valueOf(10.0);
		double avg = num/3;
		System.out.println((avg+avg+avg) == num);
	}
	
	public void formatTest(){
		DecimalFormat df = new DecimalFormat("0.00");
		String out = df.format(3343344.225);
		System.out.println(out);
	}

}
