package onlineTest;
import java.io.Serializable;

public class Question implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int examId, questionNumber;
	String text;
	double points;
	String[] answer;
	
	
	public Question() {
		
	}
	public Question(int examId, int questionNumber, String text, double points, String[] answer)
	{
		this.examId=examId;
		this.questionNumber=questionNumber;
		this.text=text;
		this.points=points;
	this.answer=answer;
	}
}

