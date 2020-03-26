import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataParser {	
	public static ArrayList<Node> parseNodes(String nodeFile, String catFile) {	
		ArrayList<Node> nodeList = new ArrayList<Node>();
		Node n;
		
		try {
			// read from input file
			Scanner input = new Scanner(new File(nodeFile));
			while (input.hasNext()) {
				String curr = input.nextLine();
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
		ArrayList<Pair<String, ArrayList<Integer>>> categories = parseCategories(catFile);
		setCategories(nodeList, categories);
		return nodeList;
	}
	
	public static ArrayList<Pair<String, ArrayList<Integer>>> parseCategories(String catFile) {
		ArrayList<Pair<String, ArrayList<Integer>>> catList = new ArrayList<Pair<String, ArrayList<Integer>>>();
		ArrayList<Integer> verticies;
				
		try {
			// read from input file
			Scanner input = new Scanner(new File(catFile));
			while (input.hasNext()) {
				String curr = input.nextLine();
				// split text into category name and vertices list
				String[] elements = curr.split(":", 2)[1].split("; ");
				String name = elements[0];
				// split vertex list into individual vertices
				verticies = new ArrayList<Integer>();
				// check if verticies exist
				if (elements.length > 1) {
					String[] stringV = elements[1].split(" ");
					// add each vertex to arraylist
					for (int i = 0; i < stringV.length; i++) 
			            verticies.add(Integer.parseInt(stringV[i])); 
					// add node to list
				}
				Pair<String, ArrayList<Integer>> tempTuple = new Pair(name, verticies);
				catList.add(tempTuple);
			};	
			input.close();

		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return catList;
	
	}
	
	public static ArrayList<Integer[]> parseConnections(String connectFile) {		
		ArrayList<Integer[]> connectList = new ArrayList<Integer[]>();
			
		try {
			// read from input file
			Scanner input = new Scanner(new File(connectFile));
			while (input.hasNext()) {
				String curr = input.nextLine();
				// split text into int and string
				String[] elements = curr.split(" ");
				Integer[] tempList = {Integer.parseInt(elements[0]), Integer.parseInt(elements[1])};
				connectList.add(tempList);
			};	
			input.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return connectList;
	}
	
	public static void setCategories(ArrayList<Node> nodes, ArrayList<Pair<String, ArrayList<Integer>>> categories) {
		for (int i = 0; i < categories.size(); i++) {
			String name = categories.get(i).getA();
			for (int j = 0; j < categories.get(i).getB().size(); j++) {
				nodes.get(j).addCategory(name);
			}
		}
	}
	
}