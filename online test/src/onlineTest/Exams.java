package onlineTest;

import java.util.ArrayList;
import java.io.Serializable;
public  class Exams implements Serializable {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Exam> exams;
	public Exams() 
	{
	 exams = new ArrayList<Exam>();
	}
	
	public boolean addExam1(int examId, String title) 
	{
		for(int i = 0; i < this.exams.size(); i++)
		{
			if (this.exams.get(i).examId == examId)//if exam already exists, return false.
			{
				return false;
			}
		}
		//else add the exam and return true.
		this.exams.add(new Exam( examId, title));
		return true;
	}
	
}

