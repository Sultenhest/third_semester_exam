import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    private HashMap<Vertex, LinkedEdgeList> listWithEdges;

    public Graph() {
        listWithEdges = new HashMap<Vertex, LinkedEdgeList>();
    }

    public void addVertex( Vertex v ) {
        listWithEdges.put( v, new LinkedEdgeList() );
    }

    public void addEdge( Edge e, boolean undirected ) {
        LinkedEdgeList linkedEdgeList = listWithEdges.get( e.getSource() );
        linkedEdgeList.add(e);

        if ( undirected ) {
            addEdge( new Edge( e.getDestination(), e.getSource(), e.getWeight() ), false );
        }
    }

    public String getVertices() {
        String str = "";

        for ( Vertex key : listWithEdges.keySet() ) {
            str += key.getName() + ", ";
        }

        return str.substring( 0, str.length() - 2 );
    }

    public String getAdjacentVertices( Vertex v ) {
        return listWithEdges.get( v ).getAdjacentNotes().toString();
    }

    public String getEdges( Vertex v ) {
        return listWithEdges.get( v ).toString();
    }

    public boolean hasEdge( Vertex source, Vertex destination ) {
        if ( !source.equals( destination ) ) {
            //first vertex edges
            ArrayList<Vertex> verticesOne = listWithEdges.get( source ).getAdjacentNotes();

            for ( Vertex v : verticesOne ) {
                if ( v.equals( destination ) ) {
                    return true;
                }
            }

            //second vertex edges
            ArrayList<Vertex> verticesTwo = listWithEdges.get( destination ).getAdjacentNotes();

            for ( Vertex v : verticesTwo ) {
                if ( v.equals( source ) ) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getWeights( Vertex source, Vertex destination ) {
        LinkedEdgeList linkedEdgeList = listWithEdges.get( source );

        Edge current = linkedEdgeList.getFront();
        while ( current != null ) {
            if ( current.getDestination().equals( destination ) ) {
                return current.getWeight();
            }

            current = current.getNext();
        }

        return -1;
    }
}
