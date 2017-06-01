package VIII_graphs;

import java.util.ArrayList;

public class LinkedEdgeList {
    private Edge front;

    public LinkedEdgeList() {
        front = null;
    }

    public Edge getFront() {
        return front;
    }

    public void add(Edge e) {
        if (front == null) {
            front = e;
        } else {
            Edge current = front;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(e);
        }
    }

    public Edge get(Vertex v) {
        Edge current = front;
        while (current != null) {

            if (current.getSource().equals(v)) {
                return current;
            }

            current = current.getNext();
        }

        return null;
    }

    public ArrayList<Vertex> getAdjacentNotes() {
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();

        if (front != null) {
            Edge current = front;
            while (current != null) {
                vertices.add( current.getDestination() );
                current = current.getNext();
            }
            return vertices;
        }

        return vertices;
    }

    public String toString() {
        if (front == null) {
            return "[]";
        } else {
            String result = "{ [" + front.getSource() + " -> " + front.getDestination() + "]";
            Edge current = front.getNext();
            while (current != null) {
                result += ", [" + current.getSource() + " -> " + current.getDestination() + "]";
                current = current.getNext();
            }
            result += " }";
            return result;
        }
    }
}
