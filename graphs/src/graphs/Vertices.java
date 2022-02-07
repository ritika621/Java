package graphs;

import java.util.PriorityQueue;

public class Vertices implements Comparable<Vertices>
{
	
		private String name;
		private int priority;
		

		public Vertices(String name, int priority) 
		{
			this.name = name;
			this.priority = priority;
		}

		public void setPriority(PriorityQueue<Vertices> r, int newPriority)
		{
			for (Vertices v : r)
			{
				if (v.name.equals(this.name) == true)
				{
					v.priority = newPriority;
				}
			}
		}
		
		public int compareTo(Vertices v) 
		{
			return equals(v) ? 0 : (priority < v.priority ? -1 : 1);
		}
	
		public boolean equals(Object obj) 
		{
			if (obj != null && getClass() == obj.getClass())
			{
				return name.equals(((Vertices) obj).name);
			}
			return false;
		}
		
		public String getName()
		{
			return name;
		}
		
		public int getPriority()
		{
			return this.priority;
		}
		
		public int getPriority(PriorityQueue<Vertices> r )
		{
			int returnPriority = 0;
			for (Vertices v : r)
			{
				if (v.name.equals(this.name) == true)
				{
					returnPriority = v.priority;
				}
			}
			
			return returnPriority;
		}

		public int hashCode() 
		{
			return name.charAt(0);
		}
}
