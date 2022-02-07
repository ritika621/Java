package onlineTest;

import java.util.ArrayList;
import java.io.Serializable;
public class Student implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	ArrayList<Student> students=new ArrayList<Student>();
	ArrayList<Answers> answers=new ArrayList<Answers>();
	
	public Student() 
	{
	
	}
	public Student( String name)
	{
		this.name=name;
	}
	
	public void answers(String studentName, int examId, int questionNumber, String[] answer)
	{
		this.answers.add(new Answers(examId, questionNumber, answer));
	}
	
	public double getStudentExamScore(Exams allExams, int examId) 
	{
		ArrayList<Double> scores = new ArrayList<>(); 
		Exam examCurrent = null; 
		double score = 0; 
		int j = 0; 
		FillInTheBlanksQuestion q1 = new FillInTheBlanksQuestion(); 
		boolean yes = false; 
		
		for (int i = 0; i < allExams.exams.size(); i++) 
		{       
			if (allExams.exams.get(i).examId == examId )
			{
				examCurrent = allExams.exams.get(i); 
			} 
		} 
		
		for(int i = 0; i < this.answers.size(); i++) 
		{       
			score=0; 
			
			if (this.answers.get(i).examId == examId) 
			{ 
				if(examCurrent.questions.get(j).getClass().equals(q1.getClass())) 
				{
					for(int k = 0; k < this.answers.get(i).answer.length; k++) 
					{
						for(int l = 0; l < examCurrent.questions.get(j).answer.length; l++) 
						{	if(this.answers.get(i).answer[k].equals(examCurrent.
								questions.get(j).answer[l])) 
							{
								score += examCurrent.questions.get(j).points 
								/ examCurrent.questions.get(j).answer.length; 
							}
						}                        
					} 
				} 
			
				else if (examCurrent.questions.get(j).answer.length - 
						this.answers.get(i).answer.length != 0) 
				{
					score = 0; 
				} 
				else 
			   {
					for (int k = 0; k < this.answers.get(i).answer.length; k++) 
					{
						if (this.answers.get(i).answer[k].equals
								(examCurrent.questions.get(j).answer[k]) )
			             {
							yes = true; 
			             }
						else 
						{
							yes = false; 
						}
					} 
			          if (yes) 
			          {
			        	  score = examCurrent.questions.get(j).points; 
			          }
			          else
			          {
			        	  score = 0; 
			       	  }
			       } 
				
				scores.add(score);
				j++; 
			} 
		} 
		score = 0; 
		for (int i = 0;i < scores.size(); i++) 
		{
			score += scores.get(i); 
		}
		return score; 
	}
	
	public String studentTestReport( Exams allExams, int examId)
	{
		double score = 0;
		String returnValue = "";
		Exam examCurrent = null;
		double totalScore = 0;
		boolean yes = false;
		FillInTheBlanksQuestion q1 = new FillInTheBlanksQuestion();
		int j = 0;
		
		for(int i = 0; i < allExams.exams.size(); i++)
		{
			if (allExams.exams.get(i).examId == examId)
			{
				examCurrent=allExams.exams.get(i);
			}
		}
		
		for (int i = 0; i < this.answers.size(); i++)
		{	
			score=0;
			if (this.answers.get(i).examId == examId)
			{
				returnValue += "Question #" + examCurrent.questions.get(j).
						questionNumber + " ";
			
				 if (examCurrent.questions.get(j).getClass().equals(q1.getClass()))
				{
					 for (int k = 0; k < this.answers.get(i).answer.length; k++)
					 {
						 for (int l = 0; l < examCurrent.questions.get(j).answer.length; l++)
						 {
							 if (this.answers.get(i).answer[k].equals
									 (examCurrent.questions.get(j).answer[l]))
							 {
								 score += examCurrent.questions.get(j).points
								/ examCurrent.questions.get(j).answer.length;
							 }
						 }
					 }
				}
				 
				else if (examCurrent.questions.get(j).answer.length - 
						this.answers.get(i).answer.length != 0)
				{
					score = 0;
				}
				
				else
				{
					for (int k = 0; k < this.answers.get(i).answer.length; k++)
					{
						if (this.answers.get(i).answer[k].equals(examCurrent.
								questions.get(j).answer[k]))
						{
							yes = true;
						}
						else
						{
							yes = false;
						}
					}
					if (yes)
					{
						score = examCurrent.questions.get(j).points;
					}
					else 
					{
						score = 0;
					}
				}
			
				returnValue += score + " points out of " + examCurrent.questions
						.get(j).points + "\n";
				j++;
			}
		}
		score = 0;
		for (int i = 0; i < examCurrent.questions.size(); i++)
		{
			totalScore += examCurrent.questions.get(i).points;
		}
		
		returnValue += "Final Score: " + getStudentExamScore(allExams, examId)
		+ " out of " + totalScore;
		return returnValue;
	}

	public double getStudentCourseNumericGrade(Exams allExams) 
	{
		double score = 0;
		double totalScore = 0;
		int currentExamId = 0;
		int nextExamId = 1;
		int j = 0;
		Exam currentExam = null;
	
		for (int i = 0; i < this.answers.size() - 1; i++)
		{
			totalScore = 0;
			if(currentExamId != nextExamId)
			{
				currentExamId = this.answers.get(i).examId;
				currentExam = allExams.exams.get(j);
				for(int k = 0;k < currentExam.questions.size(); k++)
				{
					totalScore += currentExam.questions.get(k).points;
				}
			
			score += (this.getStudentExamScore(allExams, currentExamId))
					/ (totalScore);
			j++;
			}
			nextExamId = this.answers.get(i + 1).examId;
		}
		
		return (score / j) * 100;
		
	}
	
	public String getStudentCourseLetterGrade(Grades cutOffs, Exams allExams)
	{
		int i = 0;
		String returnValue = "";
		
		while (i < cutOffs.percentage.length && i >= 0)
		{
			if (this.getStudentCourseNumericGrade(allExams) >= cutOffs.percentage[i])
			{
				returnValue = cutOffs.letterGrade[i];
				i =- 10;
			}
			else
			{
				i++;
			}
		}
		return returnValue;
	}
}


