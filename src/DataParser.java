import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import org.javatuples.Pair;

public class DataParser {
	private ArrayList<Node> nodeList;
	private ArrayList<Pair<Integer, Integer>> connectList;
	private ArrayList<Pair<String, ArrayList<Integer>>> catList;
	private Scanner input;	

	
	public ArrayList<Node> parseNodes(String nodeFile) {	
		nodeList = new ArrayList<Node>();
		Node n;
		
		try {
			// read from input file
			input = new Scanner(new File(nodeFile));
			while (input.hasNext()) {
				String curr = input.next();
				// split text into int and string
				String[] elements = curr.split(" ", 2);
				int index = Integer.parseInt(elements[0]);
				String name = elements[1];
				// add node to list
				n = new Node(index, name);
				nodeList.add(index, n);
			};	
			input.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return nodeList;
	}
	
	public ArrayList<Pair<String, ArrayList<Integer>>> parseCategories(String catFile) {
		catList = new ArrayList<Pair<String, ArrayList<Integer>>>();
		Pair<String, ArrayList<Integer>> tempTuple;
		ArrayList<Integer> verticies;
				
		try {
			// read from input file
			input = new Scanner(new File(catFile));
			while (input.hasNext()) {
				String curr = input.next();
				// split text into category name and vertices list
				String[] elements = curr.split(":", 2)[1].split(";");
				String name = elements[0];
				// split vertex list into individual vertices
				String[] stringV = elements[1].split(" ");
				verticies = new ArrayList<Integer>();
				// add each vertex to arraylist
				for (int i = 0; i < stringV.length; i++) 
		            verticies.add(Integer.parseInt(stringV[i])); 
				// add node to list
				tempTuple = new Pair(name, verticies);
				catList.add(tempTuple);
			};	
			input.close();

		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return catList;
	
	}
	
	public ArrayList<Pair<Integer, Integer>> parseConnections(String connectFile) {		
		connectList = new ArrayList<Pair<Integer, Integer>>();
		
		
		try {
			// read from input file
			input = new Scanner(new File(connectFile));
			while (input.hasNext()) {
				String curr = input.next();
				// split text into int and string
				String[] elements = curr.split(" ");
				int v1 = Integer.parseInt(elements[0]);
				int v2 = Integer.parseInt(elements[1]);
				// add node to list
				Pair<Integer, Integer> tempTuple = new Pair(v1, v2);
				connectList.add(tempTuple);
			};	
			input.close();

		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return connectList;
	}
	
	public void setCategories(ArrayList<Pair<String, ArrayList<Integer>>> categories) {
		for (int i = 0; i < categories.size(); i++) {
			String name = categories.get(i).getValue0();
			for (int j = 0; j < categories.get(i).getValue1().size(); j++) {
				nodeList.get(j).addCategory(name);
			}
		}
	}
	
}