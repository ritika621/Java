package onlineTest;

import java.io.Serializable;

public class Answers implements Serializable
{


/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int examId, questionNumber;
	String[] answer;
	public Answers( int examId, int questionNumber, String[] answer) 
	{
	
		this.examId = examId;
		this.questionNumber = questionNumber;
		this.answer = answer;
	} 
}
