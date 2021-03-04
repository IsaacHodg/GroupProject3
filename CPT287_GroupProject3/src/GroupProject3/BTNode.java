package GroupProject3;

public class BTNode<T> {
	public String data;
	public BTNode<String> left, right;
	
	//constructors
	public BTNode(String value) {data = value;}
	public BTNode(String value, BTNode<String> leftChild, BTNode<String> rightChild) {
		data = value;
		left = leftChild;
		right = rightChild;
	}
	
}
