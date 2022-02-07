package model;

public class ImageElement extends TagElement implements Element {
	
	private String imageURL, alt, attributes;
	private int width, height;

	public ImageElement(java.lang.String imageURL, int width, int height, java.lang.String alt, java.lang.String attributes)
	{
		//if attributes are null or an empty strings they don't get passed in with 
		//the default attributes of the img tag.
		super("img", false, null, (attributes == null || attributes == "") ? 
		"src=\"" + imageURL + "\" width=\"" + width + "\" height=\"" + height + 
		"\" alt=\"" + alt + "\"" : "src=\"" + imageURL + "\" width=\"" + width + 
		"\" height=\"" + height + "\" alt=\"" + alt + "\" " + attributes);
		
		this.imageURL = imageURL;
		this.width = width;
		this.height = height;
		this.alt = alt;
		
	}
	
	public java.lang.String getImageURL()
	{
		return imageURL;
	}
}
