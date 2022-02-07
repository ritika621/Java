package model;

public class TagElement implements Element
{
	
	private String tagName, attributes;
	private Element content;
	private boolean endTag;
	private static boolean disabledOrEnabled;
	private static int uniqueId;
	private int instanceId;
	
	public TagElement(java.lang.String tagName, boolean endTag, Element content, java.lang.String attributes)
	{
		this.tagName = tagName;
		//if the attributes are null, it assigns the attributes to an empty string 
		//so that "null" doesn't get printed in genHTML().
		if (attributes == null)
		{
			this.attributes = "";
		}
		else
		{
			this.attributes = attributes;
		}
		
		this.content = content;
		this.endTag = endTag;
		uniqueId++;
		this.instanceId = uniqueId;
	}
	
	public static void enableId(boolean choice)
	{
         disabledOrEnabled = choice;
	}
	public String genHTML(int indentation)
	{
		String message = "";
		
		//assigns message to the genHTML() of content if it isn't null.
		if (this.content != null)
		{	
			message = content.genHTML(0);
		}
		
	   return Utilities.spaces(indentation) + "<" + getStartTag() + ">" + message
			+ getEndTag();
		
	}
	
	public int getId() 
	{
		return this.instanceId;
	}
	
	public java.lang.String getStringId()
	{
		return this.tagName+getId();
		
	}
	
	public java.lang.String getStartTag()
	{
		String space = "";
		
		//if condition ensures there's a space between the tag name or id and 
		//attributes only if the attributes aren't null.
		if (attributes != "")
	   {
			space = " ";
	   }
		
		//returns the string with the ID.
		if (disabledOrEnabled == true)
		{
			return this.tagName +" id=\"" + getStringId() + "\"" + space + 
				   this.attributes;
		}
		//returns the string without the ID.
		else
		{
			return this.tagName + space + this.attributes;
		}
	}
	
	public java.lang.String getEndTag()
	{
		if (endTag == true)
		{
			return "</" + this.tagName + ">";
		}
		else 
		{
			return "";
		}
	}
	
	public void setAttributes(java.lang.String attributes)
	{
		this.attributes = attributes;
	}
	
	public static void resetIds() 
	{
		uniqueId = 0;
	}
	
}
