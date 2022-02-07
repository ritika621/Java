package model;

public class AnchorElement extends TagElement implements Element{
	private String url, linkText, attributes;
	
	
	public AnchorElement(java.lang.String url,java.lang.String linkText,java.lang.String attributes)
	{
		super ("a", true, null, null);
		this.url = url;
		this.linkText = linkText;
		//if the attributes is null, it assigns attributes to an empty string.
		if (attributes == null)
		{
			this.attributes = "";
		}
		else
		{
			this.attributes = attributes;
		}
	}
	
	public java.lang.String getLinkText()
	{
		return this.linkText;
	}
	
	
	public java.lang.String getUrlText()
	{
		return url;
	}
	
	@Override
	public java.lang.String genHTML(int indentation)
	{
		String space = "";
		
		//if condition ensures there's a space between the url and attributes only
		//if the attributes aren't null.
		if(attributes != null)
		{
			space=" ";
		}
		return Utilities.spaces(indentation) + "<" + getStartTag() + " href=" +
	       	"\"" + url + "\"" + space + attributes + ">" + linkText + "</a>";
	} 

}
