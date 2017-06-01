package II_multiClient.tcp_loan;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class LoanServer extends Application {
    private Stage window;
    private TextArea textArea = new TextArea();
    private static ObjectInputStream inputFromClient;
    private static ObjectOutputStream outputToClient;

    @Override
    public void start( Stage primaryStage ) throws Exception {
        window = primaryStage;
        window.setTitle( "Loan Server - TCP" );

        textArea.setEditable( false );

        Scene scene = new Scene( textArea, 600, 250 );

        window.setScene(scene);
        window.show();

        new Thread( () -> {
            try {
                ServerSocket serverSocket = new ServerSocket(6789);
                System.out.println("Server Started!");
                textArea.appendText( new Date() + ": Server started at socket 6789\n" );

                while (true) {
                    Socket socket = serverSocket.accept();

                    inputFromClient = new ObjectInputStream( socket.getInputStream() );
                    outputToClient = new ObjectOutputStream( socket.getOutputStream() );

                    //Type cast into a Loan object
                    Loan loan = (Loan) inputFromClient.readObject();

                    //Calculate at set payments
                    loan.setYearlyPayment( calculateTotalPayment( loan ) );
                    loan.setMonthlyPayment( calculateMonthlyPayment( loan ) );

                    //Append info to textarea
                    textArea.appendText( "\n" + new Date() + ": Client made a request\n" );
                    textArea.appendText( "Annual Interest Rate: " + loan.getAnnualInterestRate() + "\n" );
                    textArea.appendText( "Number of Years: " + loan.getYears() + "\n" );
                    textArea.appendText( "Loan Amount: " + loan.getLoanAmount() + "\n" );
                    textArea.appendText( "Doing some incorrect calculations: \n" );
                    textArea.appendText( "Monthly Payment: " + loan.getMonthlyPayment() + "\n" );
                    textArea.appendText( "Total Payment: " + loan.getYearlyPayment() + "\n" );

                    //Write to client
                    outputToClient.writeObject( loan );
                }
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    inputFromClient.close();
                    outputToClient.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    private double calculateTotalPayment( Loan loan ) {
        return loan.getLoanAmount() * ( 1 + ( ( loan.getAnnualInterestRate() / 100 ) * loan.getYears() ) );
    }

    private double calculateMonthlyPayment( Loan loan ) {
        return ( calculateTotalPayment( loan ) / loan.getYears() ) / 12;
    }

    public static void main( String args[] ) {
        launch( args );
    }
}
