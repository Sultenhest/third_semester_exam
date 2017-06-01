package III_designPatterns;

public class Main {
    public static void main(String[] args) {
        Person model = database();

        PersonView view = new PersonView();

        PersonController controller = new PersonController( model, view );

        controller.updateView();

        controller.setPersonName( "Bob" );

        controller.updateView();

        //System.out.println( Singleton.getValue() );
    }

    private static Person database() {
        Person model = new Person( "Simon", 24 );

        return model;
    }
}
