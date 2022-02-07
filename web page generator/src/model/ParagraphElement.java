package model;

import java.util.ArrayList;

public class ParagraphElement extends TagElement implements Element
{
	private ArrayList<Element> para = new ArrayList();
	
	public ParagraphElement(java.lang.String attributes) 
	{
		super("p", true, null, attributes);
	}
	
	public void addItem(Element item) 
	{
		para.add(item);
	}
	
	public java.lang.String genHTML(int indentation)
	{
		String rv=Utilities.spaces(indentation) + "<" + getStartTag() + ">\n";
		
		for (int i = 0; i < para.size(); i++)
			{
				//rv goes through every element in the paragraph and stores the 
				//genHTML() for that element with specific indentation.
				rv += para.get(i).genHTML(indentation + 
						Utilities.DEFAULT_INDENTATION) + "\n";
			}
		
		return rv + Utilities.spaces(indentation) + getEndTag();
	}
}
