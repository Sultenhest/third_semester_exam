package II_multiClient.tcp_loan;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class LoanClient extends Application {
    private Stage window;
    private VBox vBox = new VBox( 20 );
    private GridPane layout = new GridPane();
    private Label lAnnualInterestRate, lYears, lLoanAmount;
    private TextField tfAnnualInterestRate = new TextField();
    private TextField tfYears = new TextField();
    private TextField tfLoanAmount = new TextField();
    private Button btSubmit = new Button( "Submit" );
    private TextArea taInfomationArea = new TextArea();

    @Override
    public void start( Stage primaryStage ) throws Exception {
        window = primaryStage;
        window.setTitle( "Loan Client - TCP" );

        lAnnualInterestRate = new Label( "Annual Interest Rate" );
        lYears = new Label( "Number Of Years" );
        lLoanAmount = new Label( "Loan Amount" );

        //Adding Labels
        layout.add( lAnnualInterestRate, 0, 0 );
        layout.add( lYears, 0, 1 );
        layout.add( lLoanAmount, 0, 2 );

        //Adding TextFields
        layout.add( tfAnnualInterestRate , 1, 0 );
        layout.add( tfYears, 1, 1 );
        layout.add( tfLoanAmount, 1, 2 );

        //Adding Button
        layout.add( btSubmit, 1, 3 );

        btSubmit.setOnAction( new ButtonListener() );

        //Create layout
        layout.setPadding( new Insets( 10, 10, 10, 10 ) );
        layout.setVgap( 8 );
        layout.setHgap( 10 );

        //Disable textarea
        taInfomationArea.setEditable( false );

        vBox.getChildren().addAll( layout, taInfomationArea );

        //Create Scene
        Scene scene = new Scene( vBox, 400, 300 );

        window.setScene(scene);
        window.show();
    }

    private class ButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle( ActionEvent e ) {
            try {
                //Connect
                Socket socket = new Socket( "localhost", 6789 );
                System.out.println( "Client started!" );

                //Create output stream
                ObjectOutputStream toServer = new ObjectOutputStream( socket.getOutputStream() );

                //Get input
                String annualInterestRate = tfAnnualInterestRate.getText().trim();
                String years = tfYears.getText().trim();
                String loanAmount = tfLoanAmount.getText().trim();

                //Convert input to correct types
                double annualInterestRateToDouble = Double.parseDouble( annualInterestRate );
                int yearsToInteger = Integer.parseInt( years );
                int loanAmountToInteger = Integer.parseInt( loanAmount );

                //Creating Loan object
                Loan loan = new Loan( annualInterestRateToDouble, yearsToInteger, loanAmountToInteger );

                //Send object to server
                toServer.writeObject( loan );

                //Get object back from server with calculations
                ObjectInputStream fromServer = new ObjectInputStream( socket.getInputStream() );

                //Type cast into a Loan object
                loan = (Loan) fromServer.readObject();

                //Append info to textarea
                taInfomationArea.appendText( "Annual Interest Rate: " + loan.getAnnualInterestRate() + "\n" );
                taInfomationArea.appendText( "Number of Years: " + loan.getYears() + "\n" );
                taInfomationArea.appendText( "Loan Amount: " + loan.getLoanAmount() + "\n" );
                taInfomationArea.appendText( "Doing some incorrect calculations: \n" );
                taInfomationArea.appendText( "Monthly Payment: " + loan.getMonthlyPayment() + "\n" );
                taInfomationArea.appendText( "Total Payment: " + loan.getYearlyPayment() + "\n" );

                //Close the socket
                socket.close();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main( String[] args ) {
        launch( args );
    }
}
