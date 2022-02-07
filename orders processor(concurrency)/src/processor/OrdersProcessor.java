package processor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class OrdersProcessor 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner stringScanner = new Scanner(System.in );
		int numberOfOrders = 0;
		String baseFile = "";
		TreeMap<String, Integer> sharedMap = new TreeMap<String, Integer>();
		String fileName = "";
		String multipleThreads = "";
		String resultFilename = "";
		String currentLine = "";
		String key = "";
		double value = 0;
		ItemsFile itemsData = new ItemsFile();
		
		System.out.print("Enter item's data file name: ");
		fileName = stringScanner.nextLine();    
		
		System.out.print("Enter 'y' for multiple threads, any other character "
				+ "otherwise: ");
		multipleThreads = stringScanner.nextLine();
		
		System.out.print("Enter number of orders to process: ");
		numberOfOrders = Integer.parseInt(stringScanner.nextLine());
		
		System.out.print("Enter order's base filename: ");
		baseFile = stringScanner.nextLine();
		
		System.out.print("Enter result's filename: ");
		resultFilename = stringScanner.nextLine();
		
		//starting time
		long startTime = System.currentTimeMillis();
		
		try 
		{
			BufferedReader bufferedReader = new BufferedReader( new FileReader(fileName));
		
			while ((currentLine = bufferedReader.readLine()) != null)
			{
				key = currentLine.substring(0, currentLine.indexOf(" "));
				value = Double.parseDouble(currentLine.substring( currentLine.
						indexOf(" "), currentLine.length()));
				itemsData.allItems.put(key, value);
			}
	
			bufferedReader.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (multipleThreads.compareTo("y") != 0)
		{
			singleThread(numberOfOrders, baseFile, resultFilename,
					itemsData, sharedMap );
		}
		
		else
		{
			multipleThreads(numberOfOrders, baseFile, resultFilename, itemsData, sharedMap);
		}

		stringScanner.close();
		long endTime = System.currentTimeMillis();
		
		System.out.println("Processing time (msec): " + (endTime - startTime));
		System.out.println("Results can be found in the file: " + resultFilename);
	}
	
	public static void singleThread(int numberOfOrders, String baseFile, String
	resultFile, ItemsFile items, TreeMap<String, Integer> sharedMap) 
	{
		String clientId = "";
		String currentOrderFile = "";
		double summaryCost = 0;
		String currentLine = "";
		OrderFile newOrder;
		String key = "";
		String[] orderData;
		
		try 
		{
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultFile, false));
		
			for (int i = 0; i < numberOfOrders; i++)
			{
				currentOrderFile = baseFile+(i + 1);
				BufferedReader bufferedReader = new BufferedReader( new FileReader
						(currentOrderFile + ".txt"));
				currentLine = bufferedReader.readLine();
				clientId = currentLine.substring(10, currentLine.length());
				System.out.println("Reading order for client with id: " + clientId);
				newOrder = new OrderFile();
				while ((currentLine = bufferedReader.readLine()) != null)
				{	
					key = currentLine.substring(0, currentLine.indexOf(" "));
					if (newOrder.itemsBought.containsKey(key))
					{
						newOrder.itemsBought.replace(key, newOrder.itemsBought.get(key) + 1);
					}
					
					else
					{
						newOrder.itemsBought.put(currentLine.substring(0, 
								currentLine.indexOf(" ")), 1);
					}
					
					if (sharedMap.containsKey(key))
					{
						sharedMap.replace(key, sharedMap.get(key) + 1);
					}
					else
					{
						sharedMap.put(currentLine.substring(0, currentLine.indexOf(" ")), 1);
					}
				}
				
				bufferedReader.close();
				
				orderData=orderDetailsGenerator(items, clientId, newOrder);
				bufferedWriter.write(orderData[0]);
				summaryCost += Double.parseDouble(orderData[1]);	
			}
				summaryGenerator(bufferedWriter, sharedMap, summaryCost, items.allItems);
		}
		
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void multipleThreads(int numberOfOrders, String baseFile, 
			String resultFile,  ItemsFile items, TreeMap<String, Integer> sharedMap) 
	{
		String currentOrderFile = "";
		Thread allThreads[] = new Thread[numberOfOrders];
		BufferedWriter bufferedWriter;
		AllThreadsData threadsData = new AllThreadsData();
		double summaryCost = 0;
	
		try 
		{
			bufferedWriter = new BufferedWriter(new FileWriter(resultFile, false));
			for (int i = 0; i < numberOfOrders; i++)
			{
				currentOrderFile = baseFile + (i + 1);
				Thread currentOrder = new RunThread( threadsData, currentOrderFile, 
						sharedMap, items);
			
				allThreads[i] = currentOrder;
				currentOrder.start();
			}
		
			for (int i = 0; i < allThreads.length; i++)
			{	
				try 
				{
					allThreads[i].join();
				} 
				
				catch (InterruptedException e) 
				{
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}
		
			//sorting all orders based on clientId.
			for (int i = 0; i < threadsData.allThreadsData.size() - 1; i++)
			{ 
				for (int j = 0; j < threadsData.allThreadsData.size() - i - 1; j++) 
				{
					if (Integer.parseInt(threadsData.allThreadsData.get(j).
					substring(40, 44)) > Integer.parseInt(threadsData.allThreadsData.
							get(j + 1).substring(40, 44)))
					{	
						String temp = threadsData.allThreadsData.get(j);
						threadsData.allThreadsData.set(j, threadsData.
								allThreadsData.get(j + 1));
						threadsData.allThreadsData.set(j + 1, temp);}
					}
			}
			
			for(int i = 0; i < threadsData.allThreadsData.size(); i++)
			{
				bufferedWriter.write(threadsData.allThreadsData.get(i));
				summaryCost += threadsData.allThreadsCost.get(i);
			}
	
			summaryGenerator(bufferedWriter, sharedMap, summaryCost, items.allItems);
		}
		catch (IOException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void summaryGenerator(BufferedWriter bufferedWriter, TreeMap<String, Integer> sharedMap,
			double summaryCost, TreeMap<String, Double> items) 
	{
		double itemCost = 0;
		int itemQuantity = 0;
		try 
		{
			bufferedWriter.write("***** Summary of all orders *****");
			bufferedWriter.newLine();
			for (Map.Entry<String, Integer> element: sharedMap.entrySet())
			{	
				itemQuantity = element.getValue();
				itemCost = (double) items.get(element.getKey());
				bufferedWriter.write("Summary - Item's name: " + element.getKey()
				+ ", Cost per item: " + NumberFormat.getCurrencyInstance().
				format(itemCost) + ", Number sold: " + itemQuantity + 
				", Item's Total: " + NumberFormat.getCurrencyInstance().format
				(itemCost * itemQuantity));
			
				bufferedWriter.newLine();
			}
			
			bufferedWriter.write("Summary Grand Total: " + NumberFormat.
					getCurrencyInstance().format(summaryCost));
			bufferedWriter.newLine();
			bufferedWriter.flush();
			bufferedWriter.close();
		}
		
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	public static String[] orderDetailsGenerator(ItemsFile items, String clientId, OrderFile newOrder) 
	{
		int quantity = 0;
		double cost = 0;
		double totalCost = 0;
		String stringData = "";
		String[] returnString = new String[2];
			
		stringData +="----- Order details for client with Id: " + clientId
				 + " -----\n";
			
		for (Map.Entry<String, Integer> element: newOrder.itemsBought.entrySet())
		{
			quantity = element.getValue();
			cost = (double) items.allItems.get(element.getKey());
			stringData += "Item's name: " + element.getKey() + ", Cost per item: "
			+ NumberFormat.getCurrencyInstance().format(cost) + ", Quantity: " +
			quantity +", Cost: " + NumberFormat.getCurrencyInstance().format
			(cost * quantity) + "\n";
			totalCost += (cost * quantity);
		}
			
		stringData += "Order Total: " + NumberFormat.getCurrencyInstance().
				format(totalCost)+ "\n";
		returnString[0] = stringData;
		returnString[1] = Double.toString(totalCost);
		return returnString;
	}
}
