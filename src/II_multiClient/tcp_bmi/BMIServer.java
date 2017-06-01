package II_multiClient.tcp_bmi;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.Date;

public class BMIServer extends Application {
    private Stage window;
    private TextArea textArea = new TextArea();
    private ServerSocket serverSocket = null;
    private int clientNo = 1;
    private static int activeClients = 1;

    @Override
    public void start( Stage primaryStage ) throws Exception {
        window = primaryStage;
        window.setTitle( "BMI Server - TCP" );

        //Layout
        BorderPane layout = new BorderPane();
        HBox topMenu = new HBox( 10 );

        //Create top menu
        Button addAnotherClientButton = new Button( "Add a new Client" );
        Button closeProgram = new Button( "Exit program" );
        Label activeClientsText = new Label( "Active Clients: " );
        Label activeClientsNumber = new Label( "0" );

        //Assemble layout
        topMenu.getChildren().addAll( addAnotherClientButton, closeProgram, activeClientsText, activeClientsNumber );
        layout.setTop( topMenu );
        layout.setCenter( textArea );

        //Disable text area
        textArea.setEditable( false );

        //Create Scene
        Scene scene = new Scene( layout, 500, 700 );

        //Set Scene and Show Stage
        window.setScene( scene );
        window.show();

        //Handle button clicks
        addAnotherClientButton.setOnAction( e -> {
            activeClientsNumber.textProperty().bind( new SimpleIntegerProperty( activeClients++ ).asString() );
            new BMIClient().runClient( clientNo );
        } );

        new Thread( () -> {
            try {
                //Create server socket
                serverSocket = new ServerSocket( 7890 );
                textArea.appendText( new Date() + ": Server started at socket 7890\n" );

                while (true) {
                    //Listen for connection request
                    Socket socket = serverSocket.accept();

                    //Add to client 'login' to log
                    textArea.appendText( "\n*** " + new Date() + ": Client #" + clientNo + " connected. ***\n" );

                    //Create a new thread for the new client
                    new Thread( new HandleAClient( socket, clientNo ) ).start();

                    //Increase clientNo
                    clientNo++;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    private double calculateBMI( double weight, double height ) {
        return ( weight / ( height * height ) ) * 10000;
    }

    private String bmiVerdict( double bmi ) {
        if( bmi < 18.5 ) {
            return "Underweight";
        } else if ( bmi <= 24.9 ) {
            return "Normal weight";
        } else if ( bmi <= 29.9 ) {
            return "Overweight";
        } else if ( bmi <= 39.0 ) {
            return "Obese";
        } else {
            return "Extreme obesity";
        }
    }

    public static void main( String args[] ) {
        launch( args );
    }

    class HandleAClient implements Runnable {
        private Socket socket;
        private int clientNo;

        public HandleAClient( Socket socket, int clientNo ) {
            this.socket = socket;
            this.clientNo = clientNo;
        }

        public void run() {
            try {
                //Creating input and output streams
                ObjectInputStream inputFromClient = new ObjectInputStream( socket.getInputStream() );
                ObjectOutputStream outputToClient = new ObjectOutputStream( socket.getOutputStream() );

                while ( true ) {
                    //Get object returned from client
                    Object objectReturnedFromClient = inputFromClient.readObject();

                    if( objectReturnedFromClient instanceof Integer ) {
                        //Client returns id when window closes
                        int clientNoDC = (int) objectReturnedFromClient;
                        textArea.appendText( "\n*** " + new Date() + ": Client #" + clientNoDC + " disconnected. ***\n" );
                    } else {
                        //Type cast into a Loan object
                        BMI bmi = (BMI) objectReturnedFromClient;

                        //Calculate BMI
                        double currentBMI = calculateBMI(bmi.getWeight(), bmi.getHeight());
                        DecimalFormat currentBMIFormat = new DecimalFormat("#.##");

                        //Compose log message
                        String result = "\tWeight: " + bmi.getWeight() + " kg\n";
                        result += "\tHeight: " + bmi.getHeight() + " cm\n";
                        result += "\tBMI is " + currentBMIFormat.format(currentBMI) + ". " + bmiVerdict(currentBMI) + ".\n";

                        //Write to server log
                        textArea.appendText( "\n" + new Date() + ": Client #" + clientNo + " made a request\n" );
                        textArea.appendText(result);

                        //Write to client
                        result = new Date() + ": Response from server\n" + result;
                        outputToClient.writeObject(result);
                    }
                }
            } catch ( ClassNotFoundException ex ) {
                ex.printStackTrace();
            } catch ( IOException ex ) {
                ex.printStackTrace();
            }
        }
    }
}
