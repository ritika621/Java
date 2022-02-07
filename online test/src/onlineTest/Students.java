package onlineTest;

import java.util.ArrayList;
import java.io.Serializable;
public class Students implements Serializable
{
	 
	private static final long serialVersionUID = 1L;
	ArrayList<Student> students;
	
	public Students() 
	{
		students = new ArrayList<Student>();
	}
		
	public boolean addStudent(String studentName) 
	{
		if(students!=null)
		{for (int i = 0; i < this.students.size(); i++)
		{
			if (this.students.get(i).name.equals(studentName))
			{
				return false;
			}
		}
		}
						
		this.students.add(new Student(studentName));
		return true;
	}
}

