package onlineTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;



public class SystemManager implements Serializable, Manager
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Exams allExams;
	Students allStudents;
	Grades gradeCutoffs ;
	Course course;
	
	public SystemManager()
	{
		allExams = new Exams();
		allStudents = new Students();
		gradeCutoffs = new Grades();
		course = new Course();
	}
	
	public boolean addExam(int examId, String title) 
	{
		return allExams.addExam1(examId, title);
	}
	
	/**
	 * Adds a true and false question to the specified exam.  If the question
	 * already exists it is overwritten.
	 * @param examId
	 * @param questionNumber
	 * @param text Question text
	 * @param points total points
	 * @param answer expected answer
	 */
	public void addTrueFalseQuestion(int examId, int questionNumber, String text, double points, boolean answer)
	{
		Exam current = null;
		
		current = getCurrentExam(examId);
		current.addTFQuestion(examId, questionNumber, text, points, answer);
	}
	
	
	/**
	 * Adds a multiple choice question to the specified exam.   If the question
	 * already exists it is overwritten.
	 * @param examId
	 * @param questionNumber
	 * @param text Question text
	 * @param points total points
	 * @param answer expected answer
	 */
	public void addMultipleChoiceQuestion(int examId, int questionNumber, String text, double points, String[] answer) 
	{
		Exam current = null;
		
		Arrays.sort(answer);
		current = getCurrentExam(examId);
		current.addMultipleChoiceQuestion1(examId,  questionNumber,text,  points, answer);
	}
	
	
	
	/**
	 * Adds a fill-in-the-blanks question to the specified exam.  If the question
	 * already exits it is overwritten.  Each correct response is worth
	 * points/entries in the answer.
	 * @param examId
	 * @param questionNumber
	 * @param text Question text
	 * @param points total points
	 * @param answer expected answer
	 */
	public void addFillInTheBlanksQuestion(int examId, int questionNumber, String text, double points, String[] answer)
	{
		Exam current = null;
		
		Arrays.sort(answer);//sorts the answer to compare the students answers.
		current = getCurrentExam(examId);
		current.addFillInTheBlanksQuestion1(examId,  questionNumber,text,  points, answer);
	}
	
	
	/**
	 * Returns a string with the following information per question:<br />
	 * "Question Text: " followed by the question's text<br />
	 * "Points: " followed by the points for the question<br />
	 * "Correct Answer: " followed by the correct answer. <br />
	 * The format for the correct answer will be: <br /> 
	 *    a. True or false question: "True" or "False"<br />
	 *    b. Multiple choice question: [ ] enclosing the answer (each entry separated by commas) and in
	 *       sorted order. <br />
	 *    c. Fill in the blanks question: [ ] enclosing the answer (each entry separated by commas) and
	 *       in sorted order. <br />
	 * @param examId
	 * @return "Exam not found" if exam not found, otherwise the key
	 */
	public String getKey(int examId)
	{
		for(int i = 0; i < allExams.exams.size(); i++)
		{	if(allExams.exams.get(i).examId==examId)
			{
				return allExams.exams.get(i).getExamKey(examId, allExams);
			}
		}
			
		return "";
	}
	
	
	public boolean addStudent(String name) 
	{
		return allStudents.addStudent(name);
	}
	
	public void answerTrueFalseQuestion(String studentName, int examId, int questionNumber, boolean answer) 
	{
		Student currentStudent = null;
		String[] answerArray = new String[1];;
		if (answer)
		{
			answerArray[0]="True";
		}
		else
		{
			answerArray[0] = "False";
		}
		
		currentStudent = getCurrentStudent(studentName);
		currentStudent.answers(studentName, examId, questionNumber, answerArray);
	}
	/**
	 * Enters the question's answer into the database.
	 * @param studentName
	 * @param examId
	 * @param questionNumber
	 * @param answer
	 */
	public void answerMultipleChoiceQuestion(String studentName, int examId, int questionNumber,String[] answer) 
	{
		Student currentStudent = null;
		
		Arrays.sort(answer);
		currentStudent = getCurrentStudent(studentName);
		currentStudent.answers(studentName, examId, questionNumber, answer);
	}
	
	
	/**
	 * Enters the question's answer into the database.
	 * @param studentName
	 * @param examId
	 * @param questionNumber
	 * @param answer
	 */
	public void answerFillInTheBlanksQuestion(String studentName, int examId, int questionNumber, String[] answer)
	{
		Student currentStudent = null;
		
		Arrays.sort(answer);
		currentStudent = getCurrentStudent(studentName);
		currentStudent.answers(studentName, examId, questionNumber, answer);
	}

	/**
	 * Returns the score the student got for the specified exam.
	 * @param studentName
	 * @param examId
	 * @return score
	 */
	public double getExamScore(String studentName, int examId)
	{
		Student currentStudent=null;
		
		currentStudent = getCurrentStudent(studentName);
		return currentStudent.getStudentExamScore(allExams, examId);
	}
	
	public String getGradingReport(String studentName, int examId) {
		Student currentStudent=null;
		
		currentStudent = getCurrentStudent(studentName);
		return currentStudent.studentTestReport(allExams, examId);
	}
	
	public void setLetterGradesCutoffs(String[] letterGrades, double[] cutoffs)
	{
		gradeCutoffs = new Grades(letterGrades, cutoffs);
	}
	
	public double getCourseNumericGrade(String studentName) 
	{
		Student currentStudent = null;
		
		currentStudent = getCurrentStudent(studentName);
		return currentStudent.getStudentCourseNumericGrade(allExams);
	}
	
	
	/** 
	 * Computes a letter grade based on cutoffs provided.  It is assumed the cutoffs have
	 * been set before the method is called.
	 * @param studentName
	 * @return letter grade
	 */
	public String getCourseLetterGrade(String studentName)
	{
		Student currentStudent = null;
		
		currentStudent = getCurrentStudent(studentName);
		return currentStudent.getStudentCourseLetterGrade(gradeCutoffs, allExams);
	}
	
	
	public String getCourseGrades()
	{
		sort(allStudents.students);
		this.course = new Course(allStudents, allExams, gradeCutoffs);
		return course.getCourseStatisticsGrades();
	}
	
	
	/**
	 * Returns the maximum score (among all the students) for the specified exam.
	 * @param examId
	 * @return maximum
	 */
	public double getMaxScore(int examId) 
	{
		this.course = new Course(allStudents, allExams, gradeCutoffs);
		return course.getCourseMaxScore(examId);
	}
	
	
	/**
	 * Returns the minimum score (among all the students) for the specified exam.
	 * @param examId
	 * @return minimum
	 */
	public double getMinScore(int examId) 
	{
		this.course = new Course(allStudents, allExams, gradeCutoffs);
		return course.getCourseMinScore(examId);
	}
	
	
	/**
	 * Returns the average score for the specified exam.
	 * @param examId
	 * @return average
	 */
	public double getAverageScore(int examId) 
	{
		this.course = new Course(allStudents, allExams, gradeCutoffs);
		return course.getCourseAverageScore(examId);
	}
	
	
	/**
	 * It will serialize the Manager object and store it in the
	 * specified file.
	 */
	public void saveManager(Manager manager, String fileName) 
	{
		File file = new File(fileName);

		try
		{
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
			output.writeObject(manager);
			output.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Manager restoreManager(String fileName)
	{
		File file = new File(fileName);
		Manager managerObject = null;
		
		try
		{
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
			try
			{
				 managerObject = (Manager) input.readObject();
			} 
			catch (ClassNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			input.close();
		}
		catch (IOException e)
		{
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return managerObject;
	}

	
	private void sort(ArrayList<Student> students)
	{
		for (int i = 0; i < students.size(); i++)
		{ 
			for (int j = 0; j < students.size() - i - 1; j++)
			{ 
				if (students.get(j).name.compareTo(students.get(j + 1).name) > 0)
             {
                Student temp = students.get(j);
                 students.set(j,students.get(j + 1));
                 students.set(j + 1,temp);
             }
			}
		}
	}
		
	private Student getCurrentStudent(String studentName)
	{
		Student currentStudent = null;
		
		for (int i = 0; i < allStudents.students.size(); i++)
		{	
			if (allStudents.students.get(i).name.contentEquals(studentName))
			{
				currentStudent = allStudents.students.get(i);
			}
		}
		return currentStudent;
	}
	
	private Exam getCurrentExam(int examId) 
	{
		Exam examCurrent = null;
		
		for (int i = 0; i < allExams.exams.size(); i++)
		{	
			if (allExams.exams.get(i).examId == examId)
			{
				examCurrent = allExams.exams.get(i);
			}
		}
		
		return examCurrent;
	}
}


