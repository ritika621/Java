package onlineTest;
import java.io.Serializable;
public class FillInTheBlanksQuestion extends Question implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FillInTheBlanksQuestion() {
		
	}
	
	public FillInTheBlanksQuestion(int examId, int questionNumber, String text, double points, String[] answer)
	{
		super(examId, questionNumber, text, points, answer);

		
		
		
	}

}
