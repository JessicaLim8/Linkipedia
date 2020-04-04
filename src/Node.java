import java.util.ArrayList;
import java.util.Comparator;

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

	public static class SubstringComparator implements Comparator<Node> {
		public int compare(Node a, Node b) {
			int alen = a.title().length();
            int blen = b.title().length();

            if (alen <= blen)
                return (a.title().equals(b.title().substring(0, alen))) ? 0 : a.compareTo(b);
            return (b.title().equals(a.title().substring(0, blen))) ? 0 : b.compareTo(a);
		}
	}
}
