import java.util.ArrayList;

public class Node implements Comparable<Node> {
    int id;
    String title;
    ArrayList<String> categories;

    public Node(int id, String title) {
        this.id = id;
        this.title = title;
        this.categories = new ArrayList<String>();
    }

    public int id() {
        return id;
    }

    public String title() {
        return title;
    }

    public ArrayList<String> categories() {
        return categories;
    }

    public void addCategory(String category) {
        categories.add(category);
    }

	@Override
	public int compareTo(Node o) {
		return this.title.compareTo(o.title());
	}

	public static class SubstringComparator {
		public int compare(Node a, Node b) {
			int alen = a.title().length();
			int blen = b.title().length();
			return (alen <= blen) && (a.title().substring(0, alen).equals(b.title().substring(0, alen))) ? 0 : a.compareTo(b);
		}
	}
}
