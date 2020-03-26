import java.util.ArrayList;

public class Node {
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
}
