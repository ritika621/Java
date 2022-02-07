package tests;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import junit.framework.TestCase;
import tree.PlaceKeysValuesInArrayLists;
import tree.PolymorphicBST;

public class StudentTests extends TestCase 
{
	
	public StudentTests()
	{
		
	}
	
	@Test
	public void testPutInCorrectOrder() 
	{
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		PlaceKeysValuesInArrayLists<Integer, String> treeArrayList = 
				new PlaceKeysValuesInArrayLists<Integer, String>();
		
		//testing empty tree
		ptree.inorderTraversal(treeArrayList);
		assertEquals(treeArrayList.getKeys().toString(), "[]");
		assertEquals(treeArrayList.getValues().toString(), "[]");
		
		//testing non empty tree
		ptree.put(23, "twenty three");
		ptree.put(5, "five");
		ptree.put(2, "two");
		ptree.put(75, "seventy five");
		ptree.put(11, "eleven");
		ptree.put(51, "fifty one");
		ptree.inorderTraversal(treeArrayList);
		assertEquals(treeArrayList.getKeys().toString(), "[2, 5, 11, 23, 51, 75]");
		assertEquals(treeArrayList.getValues().toString(), 
				"[two, five, eleven, twenty three, fifty one, seventy five]");
	}
	
	@Test
	public void testGetValue() 
	{
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		
		//testing empty tree
		assertEquals(null, ptree.get(23));
		
		//testing non empty tree
		ptree.put(23,  "twenty three");
		ptree.put(5, "five");
		ptree.put(2, "two");
		ptree.put(75, "seventy five");
		ptree.put(11, "eleven");
		ptree.put(51, "fifty one");
		assertEquals("twenty three", ptree.get(23));
	}
	
	@Test
	public void testSize()
	{
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		//testing empty tree
		assertEquals(0, ptree.size());
		
		//testing non empty tree
		ptree.put(23, "twenty three");
		ptree.put(5, "five");
		ptree.put(2, "two");
		ptree.put(75, "seventy five");
		ptree.put(11, "eleven");
		ptree.put(51, "fifty one");
		assertEquals(6, ptree.size());
	}
	
	@Test
	public void testRemove() 
	{
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		PlaceKeysValuesInArrayLists<Integer, String> treeArrayList = 
				new PlaceKeysValuesInArrayLists<Integer, String>();
		
		//testing empty tree
		ptree.remove(8);
		ptree.inorderTraversal(treeArrayList);
		assertEquals(treeArrayList.getKeys().toString(), "[]");
		
		//testing non empty tree
		ptree.put(23,  "twenty three");
		ptree.put(5, "five");
		ptree.put(2, "two");
		ptree.put(75, "seventy five");
		ptree.put(11,  "eleven");
		ptree.put(51,  "fifty one");
		ptree.remove(2);//removing leaf
		ptree.inorderTraversal(treeArrayList);
		assertEquals(treeArrayList.getKeys().toString(), "[5, 11, 23, 51, 75]");
		
		treeArrayList = new PlaceKeysValuesInArrayLists<Integer, String>();
		
		ptree.put(2, "two");
		ptree.remove(5);//removing interior node
		ptree.inorderTraversal(treeArrayList);
		assertEquals(treeArrayList.getKeys().toString(), "[2, 11, 23, 51, 75]");
		
		treeArrayList = new PlaceKeysValuesInArrayLists<Integer, String>();;
		ptree.remove(23);//removing root node
		ptree.inorderTraversal(treeArrayList);
		assertEquals(treeArrayList.getKeys().toString(), "[2, 11, 51, 75]");
	}
	
	@Test
	public void testKeySet()
	{
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		Set<Integer> setOfKeys = new HashSet<Integer>();
		
		//testing empty tree
		assertEquals(setOfKeys.toString(), "[]");
		
		//testing non empty tree
		ptree.put(23,  "twenty three");
		ptree.put(5, "five");
		ptree.put(2, "two");
		ptree.put(75, "seventy five");
		ptree.put(11,  "eleven");
		ptree.put(51,  "fifty one");
		setOfKeys = ptree.keySet();
		assertEquals(setOfKeys.contains(23), true);
		assertEquals(setOfKeys.contains(5), true);
		assertEquals(setOfKeys.contains(2), true);
		assertEquals(setOfKeys.contains(75), true);
		assertEquals(setOfKeys.contains(11), true);
		assertEquals(setOfKeys.contains(51), true);
	}
	
	@Test
	public void testGetMin()
	{		
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		int min = 0;
		
		//testing tree with only one node.
		ptree.put(23,  "twenty three");
		min =  ptree.getMin();
		assertEquals(23, min);
		
		//testing tree with several nodes
		ptree.put(5, "five");
		ptree.put(2, "two");
		ptree.put(75, "seventy five");
		ptree.put(11,  "eleven");
		ptree.put(51,  "fifty one");
		min = ptree.getMin();
		assertEquals(2, min);
	}
	
	@Test
	public void testGetMax()
	{		
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		int max = 0;
		//testing tree with only one node.
		ptree.put(23,  "twenty three");
		max = ptree.getMax();
		assertEquals(23, max);
		//testing tree with several nodes
		ptree.put(5, "five");
		ptree.put(2, "two");
		ptree.put(75, "seventy five");
		ptree.put(11, "eleven");
		ptree.put(51, "fifty one");
		max = ptree.getMax();
		assertEquals(75, max);
	}
	
	@Test
	public void testClear()
	{		
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		PlaceKeysValuesInArrayLists<Integer, String> treeArrayList =
				new PlaceKeysValuesInArrayLists<Integer, String>();
		
		ptree.put(23,  "twenty three");
		ptree.put(5, "five");
		ptree.put(2, "two");
		ptree.put(75, "seventy five");
		ptree.put(11,  "eleven");
		ptree.put(51,  "fifty one");
		ptree.clear();
		ptree.inorderTraversal(treeArrayList);
		assertEquals(treeArrayList.getKeys().toString(), "[]");
	}
	
	@Test
	public void testHeight()
	{
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		//testing height of empty tree
		assertEquals(ptree.height(), 0);
		
		//testing height of non empty tree
		ptree.put(23,  "twenty three");
		ptree.put(5, "five");
		ptree.put(2, "two");
		assertEquals(ptree.height(), 3);
		
		ptree.put(75, "seventy five");
		ptree.put(11,  "eleven");
		ptree.put(51,  "fifty one");
		assertEquals(ptree.height(), 3);
	}
	
	@Test
	public void testInOrderTraversal()
	{
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		PlaceKeysValuesInArrayLists<Integer, String> treeArrayList 
		= new PlaceKeysValuesInArrayLists<Integer, String>();
		
		//testing empty tree
		ptree.inorderTraversal(treeArrayList);
		assertEquals(treeArrayList.getKeys().toString(), "[]");
		
		//testing non empty tree
		ptree.put(23,  "twenty three");
		ptree.put(5, "five");
		ptree.put(2, "two");
		ptree.put(75, "seventy five");
		ptree.put(11,  "eleven");
		ptree.put(51,  "fifty one");
		ptree.inorderTraversal(treeArrayList);
		assertEquals(treeArrayList.getKeys().toString(), "[2, 5, 11, 23, 51, 75]");
	}
	
	@Test
	public void testRightRootLeftTraversal()
	{
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		PlaceKeysValuesInArrayLists<Integer, String> treeArrayList = 
				new PlaceKeysValuesInArrayLists<Integer, String>();
		
		//testing empty tree
		ptree.rightRootLeftTraversal(treeArrayList);
		assertEquals(treeArrayList.getKeys().toString(), "[]");
	
		//testing non empty tree
		ptree.put(23,  "twenty three");
		ptree.put(5, "five");
		ptree.put(2, "two");
		ptree.put(75, "seventy five");
		ptree.put(11,  "eleven");
		ptree.put(51,  "fifty one");
		ptree.rightRootLeftTraversal(treeArrayList);
		assertEquals(treeArrayList.getKeys().toString(), "[75, 51, 23, 11, 5, 2]");
	
	}
	
	@Test
	public void testSubTree()
	{
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		PolymorphicBST<Integer,String> subTree = new PolymorphicBST<Integer,String>();
		PlaceKeysValuesInArrayLists<Integer, String> treeArrayList = 
				new PlaceKeysValuesInArrayLists<Integer, String>();
		//testing empty tree
		subTree = ptree.subMap(11,  65);
		subTree.inorderTraversal(treeArrayList);
		assertEquals(treeArrayList.getKeys().toString(), "[]");
		
		//testing non empty tree
		ptree.put(25,  "twenty five");
		ptree.put(10, "ten");
		ptree.put(40, "forty");
		ptree.put(75,  "seventy five");
		ptree.put(12, "twelve");
		ptree.put(5,  "five");
		ptree.put(30,  "thirty");
		ptree.put(65, "sixty five");
		subTree = ptree.subMap(11,  65);
		subTree.inorderTraversal(treeArrayList);
		assertEquals(treeArrayList.getKeys().toString(), "[12, 25, 30, 40, 65]");
		
		//testing if original tree is retained
		treeArrayList = new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.inorderTraversal(treeArrayList);
		assertEquals(treeArrayList.getKeys().toString(), "[5, 10, 12, 25, 30, "
				+ "40, 65, 75]");
	}
}