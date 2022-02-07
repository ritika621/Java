package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.AnchorElement;
import model.ImageElement;
import model.TableElement;
import model.TagElement;
import model.TextElement;

public class StudentTests {

	@Test
	public void pubTableTestNoRowsOrColumns() {
		int indentation = 0;
		
		String attributes = "border=\"1\"", answer = "";
		
		TagElement.resetIds();
		TagElement.enableId(false);
		TableElement tableElement = new TableElement(0,0, attributes);
answer+=tableElement.genHTML(indentation);
answer+="\nTable Utilization: "+ tableElement.getTableUtilization();
		
		assertTrue(TestsSupport.isCorrect("tableElement1.txt", answer));
	}
	@Test
	public void pubTableUtilizationTest() {
		int indentation = 3;
		
		String attributes = null, answer = "";
		
		TagElement.resetIds();
		TagElement.enableId(false);
		TableElement tableElement = new TableElement(2,3, attributes);
		tableElement.addItem(0, 0,new TextElement("John"));
		tableElement.addItem(0, 1,  new TextElement("Laura"));
			tableElement.addItem(1, 0, new TextElement("Rose"));
			tableElement.addItem(1, 1, new TextElement("Carly"));
			answer+=tableElement.genHTML(indentation);
			answer+="\nTable Utilization: "+ tableElement.getTableUtilization();
			
					
		
		assertTrue(TestsSupport.isCorrect("tableElement2.txt", answer));
	}
	
	@Test
	public void pubTableNullValuesTest() {
		int indentation = 4;
		
		String attributes = null, answer = "";
		
		TagElement.resetIds();
		TagElement.enableId(false);
		TableElement tableElement = new TableElement(2,2, attributes);
		tableElement.addItem(0, 0,new TextElement(null));
			tableElement.addItem(0, 1, null);
			tableElement.addItem(1, 0, new TextElement("Rose"));
			tableElement.addItem(1, 1, new TextElement("Carly"));
			answer+=tableElement.genHTML(indentation);
			answer+="\nTable Utilization: "+ tableElement.getTableUtilization();
					
		
		assertTrue(TestsSupport.isCorrect("tableElement3.txt", answer));
	}
	

}
