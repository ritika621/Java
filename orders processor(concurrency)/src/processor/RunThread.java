package processor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class RunThread extends Thread 
{
	
	private String currentOrderFile;
	private TreeMap<String, Integer> sharedMap;
	private ItemsFile items;
	 String threadData="";
	protected AllThreadsData allThreadsData;


	public RunThread(AllThreadsData a, String currentOrderFile, TreeMap<String,
			Integer> sharedMap, ItemsFile items) 
	{
		this.currentOrderFile = currentOrderFile;
		this.sharedMap = sharedMap;
		this.items = items;
		allThreadsData=a;
	}
	
	public void run()
	{
		String currentLine = "";
		String clientId = "";
		OrderFile newOrder;
		String[] orderData;
	
		try 
		{
			BufferedReader bufferedReader = new BufferedReader( new FileReader
					(currentOrderFile + ".txt"));
		
			currentLine = bufferedReader.readLine();
			clientId = currentLine.substring(10, currentLine.length());
			newOrder = new OrderFile();
			String key = "";
		
			while ((currentLine = bufferedReader.readLine()) != null)
			{	
				key = currentLine.substring(0, currentLine.indexOf(" "));
				if(newOrder.itemsBought.containsKey(key))
				{
					newOrder.itemsBought.replace(key, newOrder.itemsBought.get(key)+1);
				}
			
				else
				{
					newOrder.itemsBought.put(currentLine.substring(0, 
							currentLine.indexOf(" ")), 1);
				}
			}
		
			bufferedReader.close();
		
			System.out.println("Reading order for client with id: " + clientId);
			orderData = OrdersProcessor.orderDetailsGenerator(items, clientId, newOrder);
	
			synchronized(items) 
			{
				for(Map.Entry<String, Integer> element: newOrder.itemsBought.entrySet())
				{
					if(sharedMap.size()!=0 && sharedMap.containsKey(element.getKey()))
					{
						sharedMap.replace(element.getKey(), sharedMap.get
								(element.getKey())+element.getValue());
					}
					
					else
					{
						sharedMap.put(element.getKey(), element.getValue());
					}
				}
				
				allThreadsData.allThreadsData.add(orderData[0]);
				allThreadsData.allThreadsCost.add(Double.parseDouble(orderData[1]));
			}
		}
		catch (IOException e) 
		{
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
}
