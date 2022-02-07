package model;

public class TableElement extends TagElement implements Element
{  
	private Element[][] table; 
	         
	public TableElement(int rows,  int cols, java.lang.String attributes) 
	{
		super("table", true, null, attributes); 
	    table = new Element[rows][cols]; 
	} 
	        
	public void addItem(int rowIndex, int colIndex,Element item) 
	{ 
		//if the TextElement element is null, it assigns that entry as null.
		if (item instanceof TextElement && item.genHTML(0).equals("null"))
		{
			table[rowIndex][colIndex] = null;
		}
	    else
	    {
	    	table[rowIndex][colIndex] = item; 	
	    }
	} 
	      
	public double getTableUtilization()
	{ 
		int count=0;
		
        if (table.length == 0) 
        {
        	return 0; 
        }
                
        //keeps count of every entry in the table that is an element.
	    for (int i = 0; i < table.length; i++) 
	    { 
	    	for (int j = 0; j < table[i].length; j++) 
	        { 
	    		if (table[i][j] instanceof Element) 
	    		{
	    			count++; 
	    		}
	        } 
	                                 
	     } 
	   
	    //rounds the decimal value.
	   return Math.round((count * 100.0) / (table.length * table[0].length) 
			   * 100.0) / 100.0; 
	} 
	        
	
	public java.lang.String genHTML(int indentation) 
	{ 
		String content = ""; 
		String returnVal = Utilities.spaces(indentation) + "<" + getStartTag()
							+ ">\n"; 
		
	    for (int i = 0; i < table.length; i++) 
	    { 
	    	returnVal += Utilities.spaces(indentation + Utilities.DEFAULT_INDENTATION) 
	    			+ "<tr>"; 
	    	
	        for (int j = 0; j < table[i].length; j++) 
	        { 
	        	//if the entry is null it doesn't get printed.
	        	if (table[i][j] == null) 
	            {
	        		content = ""; 
	            }
	            else 
	            {
	            	content = table[i][j].genHTML(0);  
	            }
	        	
	        	returnVal += "<td>" + content + "</td>";
	        } 
	        
	        returnVal += "</tr>\n"; 
	                                 
	     }
	    
	    return returnVal += Utilities.spaces(indentation) + getEndTag(); 
	        
	} 
} 