package II_multiClient.tcp_bmi;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.Date;

public class old_BMIServer extends Application {
    private Stage window;
    private TextArea textArea = new TextArea();
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private static ObjectInputStream inputFromClient;
    private static ObjectOutputStream outputToClient;

    @Override
    public void start( Stage primaryStage ) throws Exception {
        window = primaryStage;
        window.setTitle( "BMI Server - TCP" );

        //Disable text area
        textArea.setEditable( false );

        //Create Scene
        Scene scene = new Scene( textArea,  425, 250 );

        //Set Scene and Show Stage
        window.setScene( scene );
        window.show();

        new Thread( () -> {
            try {
                //Create server socket
                serverSocket = new ServerSocket( 7890 );
                textArea.appendText( new Date() + ": Server started at socket 7890\n" );

                //Listen for connection request
                socket = serverSocket.accept();

                //Creating input and output streams
                inputFromClient = new ObjectInputStream( socket.getInputStream() );
                outputToClient = new ObjectOutputStream( socket.getOutputStream() );

                while (true) {
                    //Type cast into a Loan object
                    BMI bmi = (BMI) inputFromClient.readObject();

                    //Calculate BMI
                    double currentBMI = calculateBMI( bmi.getWeight(), bmi.getHeight() );
                    DecimalFormat currentBMIFormat = new DecimalFormat("#.##");

                    //Compose log message
                    String result = "Weight: " + bmi.getWeight() + " kg\n";
                    result += "Height: " + bmi.getHeight() + " cm\n";
                    result += "BMI is " + currentBMIFormat.format( currentBMI ) + ". " + bmiVerdict( currentBMI ) + ".\n";

                    //Write to server log
                    textArea.appendText( "\n" + new Date() + ": Client made a request\n" );
                    textArea.appendText( result );

                    //Write to client
                    result = new Date() + ": Response from server\n" + result;
                    outputToClient.writeObject( result );
                }
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    inputFromClient.close();
                    outputToClient.close();
                    socket.close();
                    serverSocket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
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
}
