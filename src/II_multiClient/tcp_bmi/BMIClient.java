package II_multiClient.tcp_bmi;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Date;

public class BMIClient extends Application {
    private Stage window = null;
    private TextArea clientLog = new TextArea();
    private ObjectOutputStream toServer = null;
    private ObjectInputStream fromServer = null;
    private int clientNo;

    public void start( Stage primaryStage ) throws Exception {
        //Connect to socket
        doConnect();

        //Set stage
        window = primaryStage;
        window.setTitle("BMI Client #" + clientNo + " - TCP");

        //Layout
        VBox layout = new VBox( 20 );
        GridPane gridPane = new GridPane();

        //Components
        TextField tfWeight = new TextField();
        TextField tfHeight = new TextField();
        Button submitButton = new Button( "Submit" );

        //Adding labels to gridPane
        gridPane.add( new Label( "Weight in kilograms" ), 0, 0 );
        gridPane.add( new Label( "Height in centimeters" ), 0, 1 );

        //Adding text fields to gridPane
        gridPane.add( tfWeight, 1, 0 );
        gridPane.add( tfHeight, 1, 1 );

        //Adding submit button to gridPane
        gridPane.add( submitButton, 2, 1 );

        //Add padding to gridPane
        gridPane.setPadding( new Insets( 10, 10, 10, 10 ) );
        gridPane.setVgap( 10 );
        gridPane.setHgap( 10 );

        //Disable textarea
        clientLog.setEditable( false );

        //Add to VBox
        layout.getChildren().addAll( gridPane, clientLog );

        //Create Scene
        Scene scene = new Scene( layout, 500, 300 );

        //Set Scene and Show Stage
        window.setScene( scene );
        window.show();

        //Handle button click
        submitButton.setOnAction( e -> {
            if ( isDouble( tfWeight ) && isDouble( tfHeight ) ) {
                try {
                    //Get user input
                    double weight = Double.parseDouble( tfWeight.getText().trim() );
                    double height = Double.parseDouble( tfHeight.getText().trim() );

                    //Creating BMI object
                    BMI bmi = new BMI( weight, height );

                    //Send object to server
                    toServer.writeObject( bmi );
                    toServer.flush();

                    //Get response from server
                    Object result = fromServer.readObject();

                    //Add to log
                    clientLog.appendText( result.toString() + "\n" );
                } catch ( ClassNotFoundException ex ) {
                    ex.printStackTrace();
                } catch ( IOException ex ) {
                    ex.printStackTrace();
                }
            }
        } );

        window.setOnCloseRequest( e -> {
            try {
                toServer.writeObject( clientNo );
                toServer.flush();
                e.consume();
                window.close();
            } catch ( IOException ex ) {
                ex.printStackTrace();
            }
        } );
    }

    private void doConnect() {
        try {
            //Connect
            Socket socket = new Socket( "localhost", 7890 );
            clientLog.appendText( new Date() + ": Connected to server at socket 7890\n\n" );

            //Create output stream
            toServer = new ObjectOutputStream( socket.getOutputStream() );

            //Get response from server
            fromServer = new ObjectInputStream( socket.getInputStream() );
        } catch ( IOException ex ) {
            clientLog.appendText( ex.toString() + "\n" );
        }
    }

    private boolean isDouble( TextField input ) {
        try {
            double number = Double.parseDouble( input.getText().trim() );
            return true;
        } catch ( NumberFormatException ex ) {
            AlertBox.DoubleError( input.getText().trim() );
            clientLog.appendText( "Error: " + input.getText().trim() + " is not a number\n\n" );
            return false;
        }
    }

    public void runClient( int clientNo ) {
        try {
            this.clientNo = clientNo;
            start( new Stage() );
        }catch( Exception ex ){
            ex.printStackTrace();
        }
    }

    /*public static void main( String args[] ) {
        launch( args );
    }*/
}
