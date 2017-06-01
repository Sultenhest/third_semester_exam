package II_multiClient.tcp_bmi;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    public static void DoubleError( String error ) {
        Stage window = new Stage();
        VBox layout = new VBox( 10 );
        Label message = new Label( error + " is not a number." );
        Button closeButton = new Button( "Okay" );

        window.initModality( Modality.APPLICATION_MODAL );
        window.setTitle( "Error!" );
        window.setMinWidth( 250 );

        layout.getChildren().addAll( message, closeButton );
        layout.setAlignment( Pos.CENTER );
        layout.setPadding( new Insets( 10, 10, 10, 10 ) );

        closeButton.setOnAction( e -> window.close() );

        window.setScene( new Scene( layout ) );
        window.showAndWait();
    }
}