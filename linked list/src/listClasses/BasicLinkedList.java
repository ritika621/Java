package listClasses;

import java.util.ArrayList;
import java.util.Iterator;

public class BasicLinkedList<T> extends java.lang.Object implements java.lang.Iterable<T>
{
	protected class Node 
	{
		protected T data;
		protected Node next;

		protected Node(T data) 
		{
			this.data = data;
			next = null; 
		}
	}
	
	protected int listSize = 0;
	protected Node head, tail;
	
	public BasicLinkedList()
	{
		BasicLinkedList<T> newList = null;//initializes an empty list
		head = null;
		tail = null;
	}
	
	public int getSize()
	{
		return listSize;
	}
	
	public BasicLinkedList<T> addToEnd(T data)
	{
		Node addNode = new Node(data);
		
		if (listSize == 0)
		{
			head = addNode;
			tail = addNode;
		}
		
		else
		{
			tail.next = addNode;
			tail = addNode;
		}
		
		listSize++;
		return this;
	}
	
	public BasicLinkedList<T> addToFront(T data)
	{
		Node addNode = new Node(data);
		
		if (listSize == 0)
		{
			head = addNode;
			tail = addNode;
		}
		
		else
		{
			addNode.next = head;
			head = addNode;
		}
		
		listSize++;
		return this;
	}
	
	public T getFirst()
	{
		if (listSize == 0)
		{
			return null;
		}
		else
		{
			return head.data;
		}
	}
	
	public T getLast()
	{
		if (listSize == 0)
		{
			return null;
		}
		else
		{
			return tail.data; 
		}
	}
	
	public T retrieveFirstElement()
	{
		T firstElement;
		
		if (listSize == 0)
		{
			return null;
	 	}
		else
		{
			if (listSize == 1)
			{
				tail=null;//updates tail if there is only one element in the list.
			}
			firstElement = head.data; 
			head = head.next;
			listSize--;//decreases listSize by 1 after removing an element.
			return firstElement;
		}
	
    }
	public T retrieveLastElement()
	{
		T lastElement;
		Node current = head;
		
		if (listSize == 0)
		{
			return null;
		}
		
		lastElement=tail.data;
		
		if (listSize == 1)//if there's only one element, updates the tail and head to null.
		{	
			tail = null;
			head = null;
			listSize--;//decreases listSize by 1 after removing an element.
			return lastElement;
		}
		else
		{
			while (current.next != tail)//traverses through the list until current
				//is the second last element in the list.
			{
				current = current.next;
			}
			tail = current;//updates tail to the second last element in the list after
			//removing the last element.
			current.next = null;
			listSize--;//decreases listSize by 1 after removing an element.
			return lastElement;
		}
	}
	
	public BasicLinkedList<T> remove(T targetData, java.util.Comparator<T> comparator)
	{
		Node current = head;
		Node previous = null;
		
		if (listSize != 0)//to avoid errors
		{
			while (current != null)
			{
				if (comparator.compare(current.data, targetData) == 0)
				{
					if (current == head)//updates head if the user removes the first element.
					{
						head = current.next;
					}
					else
					{
						previous.next = current.next;
					}
					listSize--;
				}
				else
				{
					tail = current;//updates tail for the last element not removed.
				}
				previous = current;//traverses through the list by updating 
				//previous and current repeatedly.
				current = current.next;
			}
		
			tail.next = null;
		}
		return this;
	}
	
	public java.util.Iterator<T> iterator()
	{
		return new Iterator<T>() 
		{
			T storage = null;
			private int count = 0;
			Node current = head;
			
			public boolean hasNext() 
			{
				return count < listSize;
			}
			
			public T next() 
			{
				count++;
				storage = current.data;//stores the data of current before it changes.
				if (current.next != null)
				{
					current = current.next;
				}
				return storage;
			}
		};
	}
	
	public java.util.ArrayList<T> getReverseArrayList()
	{
		ArrayList<T> reverse = new ArrayList<T>();
		Node current = head;
		
		for (int i = 0; i < listSize; i++)
		{
			reverse.add(null);//creates the indices of reverse with no values.
		}
		
		helperRecursion(current, reverse, listSize-1);//calls the auxiliary method
		return reverse;
	}
	
	private void helperRecursion(Node current, ArrayList<T> array, int i)
	{
		if(current != null)
		{
			array.set(i, current.data);//sets the last unfilled index with the data.
			if (i != 0)
			{
				helperRecursion(current.next, array, i-1);//recursively calls itself 
				//and stops when i is 0 as the array list is completely fulfilled by then.
			}
		}
	}
	
	public BasicLinkedList<T> getReverseList()
	{
		BasicLinkedList<T> reverseList = new BasicLinkedList<T>();
		Node current = head;
		
		helperRecursionList(current, reverseList);//calls the auxiliary method
		return reverseList;
	}
	
	private void helperRecursionList(Node current, BasicLinkedList<T> list)
	{
		if(current != null)
		{
			list.addToFront(current.data);//adds the elements in reverse order by adding 
			//each one to the front of the previous element.
			helperRecursionList(current.next, list);
		}
	}
}

