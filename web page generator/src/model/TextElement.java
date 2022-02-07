package model;

public class TextElement implements Element 
{
	public String text;

	public TextElement(java.lang.String text)
	{
		this.text = text;
	}
	
	public java.lang.String genHTML(int indentation)
	{
		return Utilities.spaces(indentation) + text;
	}
}
