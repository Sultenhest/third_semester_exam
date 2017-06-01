package VIII_graphs;

public class Main {
    private static Vertex kbh = new Vertex("København");
    private static Vertex hel = new Vertex("Helsingør");
    private static Vertex slg = new Vertex("Slagelse");
    private static Vertex nyb = new Vertex("Nyborg");
    private static Vertex odn = new Vertex("Odense");
    private static Vertex vej = new Vertex("Vejle");
    private static Vertex arh = new Vertex("Aarhus");

    private static Graph g = createGraph();

    private static Graph createGraph() {
        Graph g = new Graph();

        //Add vertices
        g.addVertex( kbh );
        g.addVertex( hel );
        g.addVertex( slg );
        g.addVertex( nyb );
        g.addVertex( odn );
        g.addVertex( vej );
        g.addVertex( arh );

        //Add edges
        boolean undirected = true;

        //København
        g.addEdge( new Edge(kbh, hel, 120), undirected );
        g.addEdge( new Edge(kbh, slg, 140), undirected );
        g.addEdge( new Edge(kbh, odn, 260), undirected );

        //Helsingør
        g.addEdge( new Edge(hel, odn, 380), undirected );

        //Slagelse
        g.addEdge( new Edge(slg, nyb, 90), undirected );
        g.addEdge( new Edge(slg, arh, 370), undirected );

        //Nyborg
        g.addEdge( new Edge(nyb, odn, 30), undirected );
        g.addEdge( new Edge(nyb, vej, 90), undirected );
        g.addEdge( new Edge(nyb, arh, 280), undirected );

        //Odense
        g.addEdge( new Edge(odn, vej, 60), undirected );

        //Vejle
        g.addEdge( new Edge(vej, arh, 190), undirected );

        return g;
    }

    public static void main(String[] args) {
        System.out.println( "Vertices: " + g.getVertices() );

        System.out.println( "Get Aarhus edges: " + g.getEdges(arh) );

        System.out.println( "Nyborg is connected to: " + g.getAdjacentVertices(nyb) );

        System.out.println( "Is Aarhus & Vejle connected: " + g.hasEdge(arh, vej) );

        System.out.println( "Is København & Aarhus connected: " + g.hasEdge(kbh, arh) );

        System.out.println( "Helsingør to Odense: " + g.getWeights(hel, odn) );

        g.addEdge( new Edge(odn, vej, 60), true );
    }
}
