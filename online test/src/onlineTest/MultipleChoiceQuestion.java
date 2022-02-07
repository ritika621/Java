package onlineTest;
import java.io.Serializable;
public class MultipleChoiceQuestion extends Question implements Serializable
{
	private static final long serialVersionUID = 1L;

	public MultipleChoiceQuestion(int examId, int questionNumber, String text, double points, String[] answer)
	{
		super(examId, questionNumber, text, points, answer);
	}
}
