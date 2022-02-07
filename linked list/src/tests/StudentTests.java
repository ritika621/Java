package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import listClasses.BasicLinkedList;
import listClasses.SortedLinkedList;

public class StudentTests 
{
	public StudentTests()
	{
		
	}
	
	@Test
	public void testGetSize() 
	{
		String answer = "";
		BasicLinkedList<String> linkedList = new BasicLinkedList<String>();
		BasicLinkedList<String> emptyList = new BasicLinkedList<String>();
		
		linkedList.addToEnd("Dog").addToEnd("Cat").addToFront("Rabbit").addToEnd("Cat");
		answer += "Size of the linked list: " + linkedList.getSize() + "\n";
		linkedList.remove("Cat", String.CASE_INSENSITIVE_ORDER);
		//checks if the listSize is updated after removal.
		answer += "Size of the linked list after removing 2 elements: " + 
				linkedList.getSize() + "\n";
		//checks if the listSize is updated after retrieving the first element.
		answer += "Size of the linked list after retrieving the first element: ";  
		linkedList.retrieveFirstElement();
		answer+=linkedList.getSize();
		//checks if the listSize is updated after retrieving the last element.
		answer += "\nSize of the linked list after retrieving the last element: "; 
		linkedList.retrieveLastElement();
		answer += linkedList.getSize();
		//checks if the listSize is updated after all elements are removed.
		answer += "\nSize of the linked list after the list is empty: "; 
		linkedList.retrieveLastElement();
		answer += linkedList.getSize();
		//checks if the listSize of an empty list.
		answer += "\nSize of the an empty linked list: "; 
		answer += emptyList.getSize();
		assertTrue(TestsSupport.isCorrect("studentTestGetSize.txt", answer));
	}

	@Test
	public void testAddToEnd() 
	{
		String answer = "";
		BasicLinkedList<String> linkedList = new BasicLinkedList<String>();
		
		//checks if the elements are added to the end.
		linkedList.addToEnd("Dog").addToEnd("Cat").addToEnd("Rabbit").addToEnd("Cat");
		answer += "Linked list after adding all elements to the end: ";
		for (String entry : linkedList) 
		{
			answer += entry + " ";
		}
		linkedList.remove("Cat",String.CASE_INSENSITIVE_ORDER );
		linkedList.retrieveFirstElement();
		linkedList.retrieveFirstElement();
		answer += "\nLinked list after removing all the elements: ";
		for (String entry : linkedList) 
		{
			answer += entry + " ";
		}
		//checks if the elements are added to the end after the list is emptied.
		linkedList.addToEnd("Zebra").addToEnd("Giraffe").addToEnd("Elephant");
		answer += "\nLinked list after adding elements to the end again: ";
		for (String entry : linkedList)
		{
			answer += entry + " ";
		}
		assertTrue(TestsSupport.isCorrect("studentTestAddToEnd.txt", answer));
	}
	
	@Test
	public void testAddToFront() 
	{
		String answer = "";
		BasicLinkedList<String> linkedList = new BasicLinkedList<String>();
		
		//checks if the elements are added to the front.
		linkedList.addToFront("Dog").addToFront("Cat").addToFront("Rabbit")
		.addToFront("Cat");
		answer += "Linked list after adding all elements to the front: ";
		for (String entry : linkedList) 
		{
			answer += entry + " ";
		}
		linkedList.remove("Cat",String.CASE_INSENSITIVE_ORDER );
		linkedList.retrieveFirstElement();
		linkedList.retrieveFirstElement();
		answer += "\nLinked list after removing all the elements: ";
		for (String entry : linkedList)
		{
			answer += entry + " ";
		}
		//checks if the elements are added to the front after the list is emptied.
		linkedList.addToFront("Zebra").addToFront("Giraffe").addToFront("Elephant");
		answer += "\nLinked list after adding elements to the front again: ";
		for (String entry : linkedList) 
		{
			answer += entry + " ";
		}
		assertTrue(TestsSupport.isCorrect("studentTestAddToFront.txt", answer));
	}
	
	@Test
	public void testGetFirst() 
	{
		String answer = "";
		BasicLinkedList<String> linkedList = new BasicLinkedList<String>();
		BasicLinkedList<String> emptyList = new BasicLinkedList<String>();
		
		linkedList.addToFront("Dog").addToEnd("Cat").addToFront("Rabbit")
		.addToEnd("Deer");
		answer += "Linked list after adding all elements: ";
		for (String entry : linkedList)
		{
			answer += entry + " ";
		}
		//checks if the correct element is returned.
		answer += "\nFirst element: "+linkedList.getFirst();
		linkedList.remove("Cat",String.CASE_INSENSITIVE_ORDER );
		linkedList.retrieveFirstElement();
		linkedList.retrieveFirstElement();
		linkedList.retrieveFirstElement();
		//checks if null is returned for the first element of an empty list.
		answer += "\nReturning the first element after removing all elements "
				+ "from the list: " + linkedList.getFirst();
		answer += "\nReturning the first element from an empty list: "
				+ emptyList.getFirst();
		assertTrue(TestsSupport.isCorrect("studentTestGetFirst.txt", answer));
	}
	
	@Test
	public void testGetLast()
	{
		String answer = "";
		BasicLinkedList<String> linkedList = new BasicLinkedList<String>();
		BasicLinkedList<String> emptyList = new BasicLinkedList<String>();
		
		linkedList.addToFront("Dog").addToEnd("Cat").addToFront("Rabbit").
		addToEnd("Deer");
		answer += "Linked list after adding all elements: ";
		for (String entry : linkedList) 
		{
			answer+=entry + " ";
		}
		//checks if the correct element is returned.
		answer += "\nLast element: " + linkedList.getLast();
		linkedList.remove("Cat",String.CASE_INSENSITIVE_ORDER );
		linkedList.retrieveFirstElement();
		linkedList.retrieveFirstElement();
		linkedList.retrieveFirstElement();
		//checks if null is returned for the last element of an empty list.
		answer += "\nReturning the last element after removing all elements "
				+ "from the list: " + linkedList.getLast();
		answer += "\nReturning the last element from an empty list: "
				+ emptyList.getLast();
		assertTrue(TestsSupport.isCorrect("studentTestGetLast.txt", answer));
	}
	
	@Test
	public void testRetrieveFirstElement() 
	{
		String answer = "";
		BasicLinkedList<String> linkedList = new BasicLinkedList<String>();
		BasicLinkedList<String> emptyList = new BasicLinkedList<String>();
		
		linkedList.addToFront("Dog").addToEnd("Cat").addToFront("Rabbit")
		.addToEnd("Deer");
		answer += "Linked list after adding all elements: ";
		for (String entry : linkedList) 
		{
			answer += entry + " ";
		}
		//checks if the correct element is retrieved.
		answer += "\nRetrieve first element: "+ linkedList.retrieveFirstElement();
		answer += "\nLinked list after retrieving the first element: ";
		for (String entry : linkedList)
		{
			answer += entry + " ";
		}
		
		linkedList.remove("Cat",String.CASE_INSENSITIVE_ORDER );
		linkedList.retrieveFirstElement();
		linkedList.retrieveFirstElement();
		linkedList.retrieveFirstElement();
		//checks if null is returned after retrieving from an empty list.
		answer += "\nRetrieving the first element after removing all elements "
				+ "from the list: " + linkedList.retrieveFirstElement();
		answer += "\nRetrieving the first element from an empty list: "
				+ emptyList.retrieveFirstElement();
		assertTrue(TestsSupport.isCorrect("studentTestRetrieveFirstElement.txt", answer));
	}
	
	@Test
	public void testRetrieveLastElement() 
	{
		String answer = "";
		BasicLinkedList<String> linkedList = new BasicLinkedList<String>();
		BasicLinkedList<String> emptyList = new BasicLinkedList<String>();
		
		linkedList.addToFront("Dog").addToEnd("Cat").addToFront("Rabbit")
		.addToEnd("Deer");
		answer += "Linked list after adding all elements: ";
		for (String entry : linkedList) 
		{
			answer += entry + " ";
		}
		//checks if the correct element is retrieved.
		answer += "\nRetrieve last element: " + linkedList.retrieveLastElement();
		answer += "\nLinked list after retrieving the last element: ";
		for (String entry : linkedList) 
		{
			answer += entry + " ";
		}
		linkedList.remove("Cat",String.CASE_INSENSITIVE_ORDER );
		linkedList.retrieveFirstElement();
		linkedList.retrieveFirstElement();
		linkedList.retrieveFirstElement();
		//checks if null is returned after retrieving from an empty list.
		answer += "\nRetrieving the last element after removing all elements "
				+ "from the list: " + linkedList.retrieveLastElement();
		answer += "\nRetrieving the last element from an empty list: "
				+ emptyList.retrieveLastElement();
		assertTrue(TestsSupport.isCorrect("studentTestRetrieveLastElement.txt", answer));
	}
	
	@Test
	public void testRemove() 
	{
		String answer = "";
		BasicLinkedList<String> linkedList = new BasicLinkedList<String>();
		BasicLinkedList<String> emptyList = new BasicLinkedList<String>();
		
		linkedList.addToFront("Dog").addToEnd("Cat").addToFront("Rabbit")
		.addToEnd("Deer").addToFront("Cat").addToEnd("Cat");
		answer += "Linked list after adding all elements: ";
		for (String entry : linkedList) 
		{
			answer += entry + " ";
		}
		//checks if Dog is removed from the list.
		answer += "\nLinked list after removing one element (Dog) from the list: ";
		linkedList.remove("Dog", String.CASE_INSENSITIVE_ORDER);
 		for (String entry : linkedList) 
 		{
			answer += entry + " ";
		}
 		//checks if all Cat elements are removed from the list.
 		answer += "\nLinked list after removing several elements (Cat) from the list: ";
		linkedList.remove("Cat", String.CASE_INSENSITIVE_ORDER);
 		for (String entry : linkedList) 
 		{
			answer += entry + " ";
		}
 		//checks for no errors when removing from an empty list.
 		answer += "\nList after removing an element from an empty list: ";
		emptyList.remove("Frog", String.CASE_INSENSITIVE_ORDER);
		for (String entry : emptyList) 
 		{
			answer += entry + " ";
		}
		assertTrue(TestsSupport.isCorrect("studentTestRemove.txt", answer));
	}
	
	@Test
	public void testIterator() 
	{
		String answer = "";
		BasicLinkedList<String> linkedList = new BasicLinkedList<String>();
		
		linkedList.addToFront("Dog").addToEnd("Cat").addToFront("Rabbit")
		.addToEnd("Deer").addToFront("Cat");
		//checks if the iterator method is able to iterate through the list.
		answer += "Linked list after adding all elements: ";
		for (String entry : linkedList)
		{
			answer += entry + " ";
		}
		assertTrue(TestsSupport.isCorrect("studentTestIterator.txt", answer));
	}
	
	@Test
	public void testGetReverseArrayList() 
	{
		String answer = "";
		BasicLinkedList<String> linkedList = new BasicLinkedList<String>();
		BasicLinkedList<String> emptyList = new BasicLinkedList<String>();
		
		linkedList.addToFront("Dog").addToEnd("Cat").addToFront("Rabbit")
		.addToEnd("Deer").addToFront("Cat");
		answer += "Linked list after adding all elements: ";
		for (String entry : linkedList) 
		{
			answer += entry + " ";
		}
		//checks if the list is correctly reversed as an array list.
		answer += "\nLinked list after reversing the list as an array list: "
		+ linkedList.getReverseArrayList();
		//checks if an empty list returns an empty list when reversed.
		answer += "\nLinked list after reversing an empty list as an array list: "
				 + emptyList.getReverseArrayList();
		assertTrue(TestsSupport.isCorrect("studentTestGetReverseArrayList.txt", answer));
	}
	
	@Test
	public void testGetReverseList() {
		String answer = "";
		BasicLinkedList<String> linkedList = new BasicLinkedList<String>();
		BasicLinkedList<String> emptyList = new BasicLinkedList<String>();
		
		linkedList.addToFront("Dog").addToEnd("Cat").addToFront("Rabbit")
		.addToEnd("Deer").addToFront("Cat");
		answer += "Linked list after adding all elements: ";
		for (String entry : linkedList) 
		{
			answer+=entry + " ";
		}
		//checks if the list is correctly reversed as a linked list.
		answer += "\nLinked list after reversing the list as a linked list: ";
		linkedList = linkedList.getReverseList();
		for (String entry : linkedList)
		{
			answer+=entry + " ";
		}
		//checks if an empty list returns an empty list when reversed.
		answer += "\nLinked list after reversing an empty list as a linked list: ";
		emptyList=emptyList.getReverseList();
		for (String entry : emptyList)
		{
			answer += entry + " ";
		}
		assertTrue(TestsSupport.isCorrect("studentTestGetReverseList.txt", answer));
	}
	
	@Test
	public void testSortedListAdd() 
	{
		String answer = "";
		SortedLinkedList<String> sortedList = 
		new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		SortedLinkedList<String> anotherSortedList = 
				new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		
		sortedList.add("Dog").add("Cat").add("Rabbit").add("Deer").add("Cat");
		answer += "Sorted linked list after adding all elements where the first "
				+ "element added is greater than the second element: ";
		//checks if elements are added in increasing order when the first element
		//comes after the second element.
		for (String entry : sortedList) 
		{
			answer += entry + " ";
		}
		anotherSortedList.add("Bear").add("Cat").add("Rabbit").add("Deer").add("Cat");
		answer += "\nSorted linked list after adding all elements where the first "
				+ "element added is less than the second element: ";
		//checks if elements are added in increasing order when the first element
				//comes before the second element.
		for (String entry : anotherSortedList) 
		{
			answer += entry + " ";
		}
		assertTrue(TestsSupport.isCorrect("studentTestSortedListAdd.txt", answer));
	}
	
	@Test
	public void testSortedListRemove() 
	{
		String answer = "";
		SortedLinkedList<String> sortedList =
				new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		
		sortedList.add("Dog").add("Cat").add("Rabbit").add("Deer").add("Dog")
		.add("Dog");
		answer += "Sorted linked list after adding all elements: ";
		for (String entry : sortedList) 
		{
			answer += entry + " ";
		}
		//checks if Rabbit is removed from the list.
		sortedList.remove("Rabbit");
		answer += "\nSorted linked list after removing one element (Rabbit)"
				+ " from the list: ";
		for (String entry : sortedList) 
		{
			answer += entry + " ";
		}
		//checks if all Dog elements are removed from the list.
		answer += "\nSorted linked list after removing many elements (Dog)"
				+ " from the list: ";
		sortedList.remove("Dog");
		for (String entry : sortedList) 
		{
			answer += entry + " ";
		}
		assertTrue(TestsSupport.isCorrect("studentTestSortedListRemove.txt", answer));
	}
	
	@Test(expected = UnsupportedOperationException.class)//test passes if exception is thrown.
	public void testSortedListAddToEnd() throws UnsupportedOperationException 
	{
		SortedLinkedList<String> sortedList
		= new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		
		sortedList.addToEnd("Dog").addToEnd("Cat");//results in an exception.
	}
	
	@Test(expected = UnsupportedOperationException.class)//test passes if exception is thrown.
	public void testSortedListAddToFront() throws UnsupportedOperationException 
	{
		SortedLinkedList<String> sortedList
		= new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		 
		sortedList.addToEnd("Dog").addToEnd("Cat");//results in an exception.
			
	}
		
}
		
	
	
	
	
	
	
	
	
	
	


