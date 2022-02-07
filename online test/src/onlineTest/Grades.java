package onlineTest;
import java.io.Serializable;

public class Grades implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[] letterGrade;
	double[] percentage;
	
	public Grades()
	{
		
	}

	public Grades(String[] letterGrade, double[] percentage)
	{
		this.letterGrade=letterGrade;
		this.percentage=percentage;
		
		
	}
	
	

}
