package Compressor;

public class PQNode<T,V> {

	T priority;
	V value;
	PQNode(T priority, V value) {
		this.priority=priority;
		this.value=value;
	}
	
}
