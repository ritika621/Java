package listClasses;

import java.util.Comparator;

public class SortedLinkedList<T> extends BasicLinkedList<T>
{
	Comparator<T> comparator;
	
	public SortedLinkedList(java.util.Comparator<T> comparator)
	{
		super();
		this.comparator = comparator;
	}
	
	public SortedLinkedList<T> add(T element)
	{
		Node addNode = new Node(element);
		Node current = head;
		Node previous = null;
		
		if (listSize == 0)//updates head and tail when the first element of the list 
			//is to be added.
		{
			head = addNode;
			tail = addNode;
		}
		
		else
		{
			while (current != null)
			{
				if (comparator.compare(current.data, addNode.data) > 0)
				{
					if (current == head)//updates head if the element comes before
						//the head.
					{
						addNode.next = current;
						head = addNode;
					}
					
					else //otherwise it just updates the node added and the previous node.
					{
						addNode.next = current;
						previous.next = addNode;
					}
					
					current = null;//escapes while loop once the node is added.
				}
					
				else if (current.next == null)//updates tail if the node to be added
					//is greater than the last node of the list.
					{
						current.next = addNode;
						tail = addNode;
						current = null;
					}
				
				else //updates previous and current to traverse through the array.
				{
					previous = current;
					current = current.next;
				}
			}
		}
		
		listSize++;
		return this;
	}
	
	public SortedLinkedList<T> remove(T targetData)
	{
		remove(targetData, comparator);//calls BasicLinkedList's remove() method.
		return this;
	}
	
	public BasicLinkedList<T> addToEnd(T data)
	{
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}
	
	public BasicLinkedList<T> addToFront(T data)
	{
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}
}
