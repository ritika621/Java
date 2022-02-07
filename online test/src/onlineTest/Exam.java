package onlineTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.io.Serializable;

public class Exam implements Serializable
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String title="";
	int examId=0;

	 ArrayList<Question> questions=new ArrayList<Question>();
	 public Exam() {
	 }
	 
	public Exam (int examId, String title)
	{
		this.title=title;
		this.examId=examId;
	}
	
	public void addTFQuestion(int examId, int questionNumber, String text, double points, boolean answer) 
	{
		int j = 0;
		String[] answerArray = new String[1];
		sortQuestions(this.questions);
		
		if (answer)
		{
			answerArray[0] = "True";
		}
		else
		{
			answerArray[0] = "False";	
		}
		while(j >= 0 && j < this.questions.size())
		{
			if (this.questions.get(j).questionNumber==questionNumber)
			{
				this.questions.set(j,new TrueFalseQuestion(examId,
						questionNumber, text, points, answerArray));
				j=-100;
			}
			else
			{
				j++;
			}
		}
	
		if(j >= 0)
		{
			this.questions.add(new TrueFalseQuestion(examId, questionNumber, 
					text, points, answerArray));
		}
	
	
	}
	public void addMultipleChoiceQuestion1(int examId, int questionNumber, String text, double points, String[] answer)
	{	
		sortQuestions(this.questions);
		int j = 0;
		while(j >= 0 && j < this.questions.size())
		{
			if (this.questions.get(j).questionNumber==questionNumber)
			{
				this.questions.set(j,new MultipleChoiceQuestion(examId,
						questionNumber, text, points, answer));
				j =- 100;
			}
			else
			{
				j++;
			}
		}

		if (j >= 0)
		{
			this.questions.add(new MultipleChoiceQuestion(examId, questionNumber, 
				text, points, answer));
		}

	}
	public  void addFillInTheBlanksQuestion1(int examId, int questionNumber, String text, double points, String[] answer)	
	{
		this.sortQuestions(this.questions);
		int j = 0;
		while(j >= 0 && j < this.questions.size())
		{
			if (this.questions.get(j).questionNumber==questionNumber)
			{
				this.questions.set(j,new FillInTheBlanksQuestion(examId,
					questionNumber, text, points, answer));
				j =- 100;
			}
			else
			{
				j++;
			}
		}

		if (j >= 0)
		{
			this.questions.add(new FillInTheBlanksQuestion(examId, questionNumber, 
				text, points, answer));
		}
	}
	public String getExamKey(int examId, Exams e)
	{
		String returnValue="";
		String answer="";

		for (int i = 0;i < this.questions.size(); i++)
		{
			answer = "";
			returnValue += "Question Text: " + this.questions.get(i).text + 
					"\nPoints: " + this.questions.get(i).points + 
					"\nCorrect Answer: ";
			TrueFalseQuestion t = new TrueFalseQuestion();//to find out what class "this" belongs to
			if (this.questions.get(i).getClass().equals(t.getClass()))
			{
				answer = this.questions.get(i).answer[0];
			}
			else
			{ 
				answer += Arrays.toString(this.questions.get(i).answer);
			}
			
			answer += "\n";
			returnValue += answer;
		}
		if (returnValue.length() == 0)
		{
			returnValue="Exam not found";
		}
		
		return returnValue;
	}
	
	private void sortQuestions(ArrayList<Question> questions)
	{
		for (int i = 0; i < questions.size(); i++)
		 {
			for (int j = 0; j < questions.size() - i - 1; j++)
             if (questions.get(j).questionNumber > questions.get(j + 1).questionNumber)
             {
                 Question storage = questions.get(j);
                 questions.set(j,questions.get(j + 1));
                 questions.set(j + 1, storage);
             }
		 }
	}
}
