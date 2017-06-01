package VIII_graphs;

public class Vertex {
    private String name;

    public Vertex(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object o) {
        return getName() == ((Vertex) o).getName();
    }

    public String toString() {
        return getName();
    }
}
