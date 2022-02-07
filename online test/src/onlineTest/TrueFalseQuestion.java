package onlineTest;
import java.io.Serializable;
public class TrueFalseQuestion extends Question implements Serializable
{
	
	private static final long serialVersionUID = 1L;

	public TrueFalseQuestion() 
	{
		
	}
	
	public TrueFalseQuestion(int examId, int questionNumber, String text, double points, String[] answer)
	{
		super(examId, questionNumber, text, points, answer);
	
	}
}
