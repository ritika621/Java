package cmdLineInterpreter;

import java.util.Scanner;

import onlineTest.Manager;

import onlineTest.SystemManager;



/**
 * 
 * By running the main method of this class we will be able to
 * execute commands associated with the SystemManager.  This command
 * interpreter is text based. 
 *
 */
public class Interpreter 
{

	public static void main(String[] args)
	{
		Manager manageData = new SystemManager();
		int i = 0;
		manageData = manageData.restoreManager("storage"); 
		
		@SuppressWarnings("resource")
		Scanner intScanner = new Scanner ( System.in );
		 @SuppressWarnings("resource")
		Scanner stringScanner = new Scanner ( System.in );
		 
		 String stringInput = "";
		 Double doubleInput = 0.0;
		 Boolean booleanInput = true;
		 int intInput = 0;
		
		while(i >= 0)
		{
			String menu = "Menu\n1. Add a student\n2. Add an exam\n3. Add a true/false question"
				+ "\n4. Answer a true/false question\n5. Get the exam score for a student";
			
			System.out.print(menu + "\n Please enter an option from the above menu: ");
			intInput = intScanner.nextInt();
			
			if(intInput == 1)
			{	
				System.out.print("Please enter the student's name: ");
				stringInput = stringScanner.nextLine();
				manageData.addStudent( stringInput);
				System.out.print("The student has been added.");
				intInput = 0;
			}
	
			if(intInput==3) 
			{
				int examId = 0;
				int questionNumber = 0;
				String text = "";
				boolean answer = true;
				double points = 0;
			
				System.out.print("Please enter the exam id: ");
				intInput = intScanner.nextInt();
				examId = intInput;
				System.out.print("Please enter the question number: ");
				intInput = intScanner.nextInt();
				questionNumber = intInput;
				System.out.print("Please enter the question text: ");
			
				stringInput = stringScanner.nextLine();
				text = stringInput;
				System.out.print("Please enter the answer: ");
				booleanInput = intScanner.nextBoolean();
				answer = booleanInput;
				System.out.print("Please enter the points: ");
				doubleInput = intScanner.nextDouble();
				points = doubleInput;
				manageData.addTrueFalseQuestion(examId,questionNumber,text, points, answer);
				
				System.out.print("The True/false question has been added.");
				intInput = 0;
			}
		
			
		if (intInput == 2) 
		{
			System.out.print("Please enter the examId: ");
			intInput = intScanner.nextInt();
			
			System.out.print("Please enter the title of the exam: ");
			stringInput = stringScanner.nextLine();
			
			manageData.addExam(intInput, stringInput);
			System.out.print("The exam has been added.");
			intInput = 0;
		}
		
			
		if(intInput==4)
		{
			int examId = 0;
			int questionNumber = 0;
			String studentName = "";
			boolean answer = true;
			
			System.out.print("Please enter the exam id: ");
			intInput = intScanner.nextInt();
			examId = intInput;
			
			System.out.print("Please enter the question number: ");
			intInput = intScanner.nextInt();
			questionNumber = intInput;
			
			System.out.print("Please enter the student's name: ");
			
			stringInput = stringScanner.nextLine();
			studentName = stringInput;
			
			System.out.print("Please enter the answer: ");
			booleanInput = intScanner.nextBoolean();
			answer = booleanInput;
			
			manageData.answerTrueFalseQuestion(studentName, examId, questionNumber, answer);
			intInput = 0;
			
		}
		
		if (intInput == 5) 
		{
			System.out.print("Please enter the student's name: ");
			stringInput = stringScanner.nextLine();
			
			System.out.print("Please enter the exam id: ");
			intInput = intScanner.nextInt();
			
			System.out.print("Exam score: " + manageData.getExamScore(stringInput, intInput));
			intInput = 0;
		}
		
		manageData.saveManager(manageData, "storage");
	
		System.out.print("\nPlease enter true if you would like to go back to "
				+ "the menu and false if you would like to exit the menu: ");
		booleanInput = intScanner.nextBoolean();
		if (booleanInput)
		{
			i++;
		}
		else
		{
			i = -100;
		}
	}
	

		
			
		
			


	}

	
		
	
}
