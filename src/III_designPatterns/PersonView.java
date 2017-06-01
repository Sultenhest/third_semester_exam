package III_designPatterns;

public class PersonView {
    public void print( String personName, int personAge ){
        System.out.println( "Person: " );
        System.out.println( "\tName: " + personName );
        System.out.println( "\tAge: " + personAge + "\n");
    }
}
