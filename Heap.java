package Compressor;

import java.util.ArrayList;

public class Heap<V> {

	ArrayList<PQNode<Integer, V>> data= new ArrayList<>();
	Heap()
	{
		data.add(null);
	}
	public int size()
	{
		return data.size()-1;
	}
	public boolean isEmpty()
	{
		return size()==0;
	}
	public PQNode<Integer, V> giveMin()
	{
		if(isEmpty())
		{
			return null;
		}
		return data.get(1);
	}
	public PQNode<Integer, V> removeMin()
	{
		if(isEmpty())
		{
			return null;
		}
		PQNode<Integer, V> minNode=data.get(1);
		PQNode<Integer, V> lastNode=data.get(data.size()-1);
		data.set(1, lastNode);
		data.remove(data.size()-1);
		heapify(1);
		return minNode;
	}
	private void heapify(int index) {

		int leftindex=2*index;
		int rightindex=2*index +1;
		int mindex=index;
		if(leftindex>data.size()-1)
		{
			return;
		}
		PQNode<Integer, V> leftNode = data.get(leftindex);
		if(leftNode.priority < data.get(index).priority)
		{
			mindex=leftindex;
		}
		if(rightindex<=data.size()-1 && data.get(rightindex).priority<data.get(mindex).priority)
		{
			mindex=rightindex;
		}
		if(mindex!=index)
		{
			PQNode<Integer, V> temp= data.get(index);
			data.set(index,data.get(mindex));
			data.set(mindex,temp);
			heapify(mindex);
		}
		else
		{
			return;
		}
	}
	public void insert(int pri, V val)
	{
		PQNode<Integer, V> newnode = new PQNode<>(pri,val);
		data.add(newnode);
		int childIndex =data.size()-1;
		int parentIndex = childIndex/2;
		while(childIndex>1)
		{
			PQNode<Integer, V> child=data.get(childIndex);
			PQNode<Integer, V> parent = data.get(parentIndex);
 			if(data.get(childIndex).priority<data.get(parentIndex).priority)
 			{
 				data.set(childIndex, parent);
 				data.set(parentIndex, child);
 				childIndex=parentIndex;
 				parentIndex=childIndex/2;
 			}
 			else
 			{
 				break;
 			}
		}
	}
	
}
