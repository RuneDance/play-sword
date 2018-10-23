import java.util.TreeSet;

public class Player {
    private String name;
    private TreeSet<Integer> treeSet = new TreeSet<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreeSet<Integer> getTreeSet() {
        return treeSet;
    }

    public void setTreeSet(TreeSet<Integer> treeSet) {
        this.treeSet = treeSet;
    }
}
