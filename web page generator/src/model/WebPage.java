package model;

import java.util.ArrayList;

public class WebPage implements java.lang.Comparable<WebPage>
{
	String title;
	private static ArrayList<Element> elements;
	TagElement tag;

	public WebPage(java.lang.String title)
	{
		tag = new TagElement("title", true, null, null);
		this.title = title;
		elements = new ArrayList();
	}

	public int addElement(Element element)
	{
		elements.add(element);
		
		//returns -1 if element isn't of type TagElement
		if (element instanceof TagElement == false)
		{
			return -1;
		}
		
		else
		{
			return ((TagElement) element).getId();
		}
	}

	public java.lang.String getWebPageHTML(int indentation)
	{
		//rv is first assigned to the beginning default tags at the start of a webpage.
		String rv = "<!doctype html>\n<html>\n" + Utilities.spaces(indentation)
		+ "<head>\n" + Utilities.spaces(indentation + Utilities.DEFAULT_INDENTATION)
		+ "<meta charset=\"utf-8\"/>\n" + Utilities.spaces(indentation + 
		Utilities.DEFAULT_INDENTATION) + "<" + tag.getStartTag() + ">" + title + 
		tag.getEndTag() + "\n" + Utilities.spaces(indentation) + "</head>\n" + 
		Utilities.spaces(indentation) + "<body>\n";
		
	    // rv then stores the genHTML() of each element in the array depending 
		//on which class it belongs to.
		for (int i = 0; i < elements.size(); i++)
		{
			if(elements.get(i) instanceof AnchorElement)
			{
				rv += ((AnchorElement)elements.get(i)).genHTML(indentation) + "\n";
			}
			else if(elements.get(i) instanceof ImageElement)
			{
				rv += ((ImageElement)elements.get(i)).genHTML(indentation) + "\n";
			}
			else if(elements.get(i) instanceof HeadingElement)
			{
				rv += ((HeadingElement)elements.get(i)).genHTML(indentation) + "\n";
			}
			else if(elements.get(i) instanceof ListElement)
			{
				rv += ((ListElement)elements.get(i)).genHTML(indentation) + "\n";
			}
			else if(elements.get(i) instanceof ParagraphElement)
			{
				rv += ((ParagraphElement)elements.get(i)).genHTML(indentation) + "\n";
			}
			else
			{
				rv += ((TableElement)elements.get(i)).genHTML(indentation) + "\n";
			}
	     }
		
		return rv + Utilities.spaces(indentation) + "</body>\n</html>";
		
	}
	
	public void writeToFile(java.lang.String filename, int indentation) 
	{
		writeToFile(filename, indentation);
	}
	
	public Element findElem(int id) 
	{
		Element returnVal = new TagElement(null, false, null, null);
		int count = 0;
		
		// if count is 1 at the end of the for loop, there is an element in the 
		//array with the required id. 
		//this element is stored in returnVal
		
		for (int i = 0; i < elements.size(); i++)
		{
			if (elements.get(i) instanceof TagElement)
			{
				if (((TagElement) elements.get(i)).getId() == id)
				{
					returnVal = elements.get(i);
					count++;
				}
			}
		}
		
		if (count == 0)
		{
			return null;
		}
		else
		{
			return returnVal;
		}
	}
	
	public java.lang.String stats()
	{
		int lists = 0, tables = 0, paragraphs = 0, count = 0;
		double tableUtil=0;
		
		//keeps count of every ListElement, TableElement, and ParagraphElement.
		for (int i=0; i<elements.size();i++)
		{
			if (elements.get(i) instanceof ListElement)
			{
				lists++;
			}
			if (elements.get(i) instanceof TableElement)
			{
				tables++;
				tableUtil += ((TableElement) elements.get(i)).getTableUtilization();
				count++;
			}
			
			if(elements.get(i) instanceof ParagraphElement)
			{
				paragraphs++;
			}
			
		}
		
		//conditions for the error caused by  0 tables in the web page and the
		//operation tableUtil/count giving 0/0.
		if (tables == 0)
		{
			return "List Count: " + lists + "\nParagraph Count: " + paragraphs +
			 "\nTable Count: " + tables + "\nTable Element Utilization: " + "0";
		}
		else
		{
			return "List Count: " + lists + "\nParagraph Count: " + paragraphs +
		"\nTable Count: " + tables + "\nTable Element Utilization: " +tableUtil / count;
		}
			
	}
	
	public int compareTo(WebPage webPage)
	{
		return compareTo(webPage);
	}
	
	public static void enableId(boolean choice) 
	{
		//if an element in the array is of type TagElement it either enables or
		//disables the id.
		
		for (int i = 0; i < elements.size(); i++)
		{
			if (elements.get(i) instanceof TagElement)
			{
				((TagElement) elements.get(i)).enableId(choice);
			}
		}	
				
	}
}

