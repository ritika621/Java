package tree;

import java.util.Collection;





/**
 * This class represents a non-empty search tree. An instance of this class
 * should contain:
 * <ul>
 * <li>A key
 * <li>A value (that the key maps to)
 * <li>A reference to a left Tree that contains key:value pairs such that the
 * keys in the left Tree are less than the key stored in this tree node.
 * <li>A reference to a right Tree that contains key:value pairs such that the
 * keys in the right Tree are greater than the key stored in this tree node.
 * </ul>
 *  
 */
 public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V>
 {
	 private K key;
	 private V value;
	 private Tree<K,V> left;
	 private Tree<K,V> right;

	/**
	 * Only constructor we need.
	 * @param key
	 * @param value
	 * @param left
	 * @param right
	 */
	public NonEmptyTree(K key, V value, Tree<K,V> left, Tree<K,V> right) 
	{
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
		
	}
	
	public V search(K key) 
	{
		int value = key.compareTo(this.key);
		
		if (value == 0)
		{
			return this.value;
		}
		else if(value>0)
		{
			return right.search(key);
		}
		else 
		{
			return left.search(key);
		}
	}
	
	public NonEmptyTree<K, V> insert(K key, V value) 
	{
		int val = key.compareTo(this.key);
		
		if (val == 0) 
		{
			this.value = value;
			return this;
		} 
		
		else if ( val > 0)
		{
			right = right.insert(key, value);
			return this;
		} 
		
		else
		{	
			left = left.insert(key,  value);
			return this;
		}
	}
	
	
	public Tree<K, V> delete(K key) 
	{
		int value = key.compareTo(this.key);
		K maxVal = null;
		
		if (value == 0)
		{
			try 
			{
				maxVal = left.max();
				this.key = maxVal;
				this.value = left.search(maxVal);
				left = left.delete(maxVal);
				return this;
			} 
			catch (TreeIsEmptyException e) 
			{
				maxVal = this.key;
				return right;
			}
		}
		
		else if (value > 0)
		{
			right = right.delete(key); 
			return this;
		}
		else
		{
			left = left.delete(key);  
			return this;
		}
	}
	
	public K max() 
	{
		try 
		{
			return right.max();
		} 
		catch (TreeIsEmptyException e) 
		{
			return key;
		}
	}
	

	public K min() 
	{
		try 
		{
			return left.min();
		} 
		catch (TreeIsEmptyException e) 
		{
			return key;
		}
	}
		
	public int size() 
	{
		return left.size() + 1 + right.size();
	}

	public void addKeysToCollection(Collection<K> c) 
	{
		left.addKeysToCollection(c);
		c.add(this.key);
		right.addKeysToCollection(c);
	}
	
	public Tree<K,V> subTree(K fromKey, K toKey)
	{
		int fromValue = fromKey.compareTo(this.key);
		int toValue=toKey.compareTo(this.key);
		NonEmptyTree<K,V> subTree = new NonEmptyTree(this.key,this.value, 
				EmptyTree.getInstance(), EmptyTree.getInstance());
		
		if (fromValue > 0 && toValue > 0)
		{
			return (this.right).subTree(fromKey, toKey);
		}
		
		else if (fromValue < 0 && toValue < 0)
		{
			return this.left.subTree(fromKey, toKey);
		}
		
		else	
		{
			subTree.right = (this.right).subTree(fromKey, toKey);
			subTree.left = (this.left).subTree(fromKey, toKey);
			return subTree;
		}
	}
	
	public int height() 
	{
		int leftHeight = 1 + left.height();
		int rightHeight = 1 + right.height();
	
		return 1 + Math.max(left.height(), right.height());
	}

	
	
	
	public void inorderTraversal(TraversalTask<K,V> p) 
	{
		left.inorderTraversal(p);
		p.performTask(key, value);
		right.inorderTraversal(p);
	 }
	
	public void rightRootLeftTraversal(TraversalTask<K,V> p) 
	{
		right.rightRootLeftTraversal(p);
		p.performTask(key, value);
		left.rightRootLeftTraversal(p);
	}	
}