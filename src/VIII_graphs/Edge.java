package VIII_graphs;

public class Edge {
    private Vertex source;
    private Vertex destination;
    private int weight;
    private Edge next;

    public Edge(Vertex source, Vertex destination, int weight) {
        setSource(source);
        setDestination(destination);
        setWeight(weight);
    }

    public Vertex getSource() {
        return source;
    }

    public void setSource(Vertex source) {
        this.source = source;
    }

    public Vertex getDestination() {
        return destination;
    }

    public void setDestination(Vertex destination) {
        this.destination = destination;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Edge getNext() {
        return next;
    }

    public void setNext(Edge next) {
        this.next = next;
    }

    public boolean equals(Object o) {
        return getSource() == ((Edge) o).getSource() && getDestination() == ((Edge) o).getDestination();
    }

    public String toString() {
        return source.getName() + " -> " + destination.getName();
    }
}
