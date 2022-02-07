package onlineTest;

import java.io.Serializable;

public class Course implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	Students courseStudents;
	Exams courseExams;
	Grades cutOffs;
	
	public Course() 
	{
		
	}
	
	public Course(Students courseStudents, Exams courseExams, Grades cutOffs)
	{
		this.courseStudents = courseStudents;
		this.courseExams = courseExams;
		this.cutOffs = cutOffs;
	}
	
	public String getCourseStatisticsGrades()
	{
		String returnValue = "";
		Student currentStudent = null;
		
		for (int i = 0;i < courseStudents.students.size(); i++)
		{
			currentStudent = courseStudents.students.get(i);
			returnValue += currentStudent.name + " " + currentStudent.
			getStudentCourseNumericGrade(courseExams) + " " + 
		currentStudent.getStudentCourseLetterGrade(cutOffs, courseExams)+"\n";
		}
			
		return returnValue;
	}
	
	public double getCourseMaxScore(int examId) 
	{
		double maxScore = 0;
		double storage = 0;
		Student currentStudent = null;
		
		for (int i = 0; i < courseStudents.students.size(); i++)
		{
			currentStudent = courseStudents.students.get(i);
			maxScore = currentStudent.getStudentExamScore(courseExams,  examId);
			if (storage < maxScore)
			{
				storage = currentStudent.getStudentExamScore(courseExams, examId);
			}
		}
		
		return storage;
	}
	
	public double getCourseMinScore(int examId) 
	{
		double minScore = 0;
		double storage = 0;
		Student currentStudent = null;
		
		for (int i = 0; i < courseStudents.students.size(); i++)
		{
			currentStudent = courseStudents.students.get(i);
			minScore = currentStudent.getStudentExamScore(courseExams,  examId);
			if (i == 0)
			{
				storage = minScore + 1;
			}
			if (storage > minScore)
			{
				storage = currentStudent.getStudentExamScore(courseExams, examId);
			}
		}
		return storage;
	}
	
	public double getCourseAverageScore(int examId) 
	{
		Student currentStudent = null;
		double totalScore = 0;
		
		for(int i=0;i<courseStudents.students.size();i++)
		{
			currentStudent = courseStudents.students.get(i);
			totalScore += currentStudent.getStudentExamScore(courseExams, examId);
		}
		
		return totalScore/courseStudents.students.size();
		
	}
}
