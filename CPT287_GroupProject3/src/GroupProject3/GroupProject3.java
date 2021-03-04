package GroupProject3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GroupProject3 {

	public static void main(String[] args) throws IOException {
		//Reads in and evaluates expressions from input.txt
		
		File file = new File("input.txt");
		
		try (BufferedReader br = new BufferedReader(new FileReader(file)))
		{
            String st;
            
            while ((st = br.readLine()) != null)
            {
            	BTNode<String> head = TreeBuilder.infixToTree(st);
        		System.out.println(Evaluation.evaluateTree(head));
            }
        }
		
	}
}
