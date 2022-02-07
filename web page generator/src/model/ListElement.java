package model;

import java.util.ArrayList;

public class ListElement extends TagElement implements Element {
	private ArrayList<Element> list = new ArrayList();
	
	public ListElement(boolean ordered, java.lang.String attributes)
	{
		super((ordered == true) ? "ol" : "ul", true, null, attributes);
	}
	
	public void addItem(Element item) 
	{
		list.add(item);
	}
	
	public java.lang.String genHTML(int indentation)
	{
		String rv=Utilities.spaces(indentation) + "<" + getStartTag() + ">\n";
		
		for (int i = 0; i < list.size(); i++)
		{ 
			//rv goes through every element in the list and stores the genHTML()
			//for that element with specific indentation.
			rv += Utilities.spaces(indentation + Utilities.DEFAULT_INDENTATION) 
				+ "<li>\n" + list.get(i).genHTML(indentation + 2 * 
			Utilities.DEFAULT_INDENTATION) + "\n" + Utilities.spaces(indentation 
			+Utilities.DEFAULT_INDENTATION) + "</li>\n";
		}
		
		return rv + Utilities.spaces(indentation) + getEndTag();
	}
}
