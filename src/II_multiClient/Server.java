package II_multiClient;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server extends Application implements Constants {
    private int sessionNo = 1;

    @Override
    public void start(Stage primaryStage) {
        Button addClient = new Button( "Add Client" );
        Button closeServer = new Button( "Close Server and Clients" );

        HBox topMenu = new HBox();
        topMenu.getChildren().addAll( addClient, closeServer );

        addClient.setOnAction( e -> new Client().runClient( sessionNo ) );
        closeServer.setOnAction( e -> {
            primaryStage.close();
            System.exit(1);
        } );

        TextArea taLog = new TextArea();

        BorderPane layout = new BorderPane();
        layout.setTop( topMenu );
        layout.setCenter( new ScrollPane( taLog ) );

        Scene scene = new Scene( layout, 450, 200 );
        primaryStage.setTitle( "Server" );
        primaryStage.setScene( scene );
        primaryStage.show();

        new Thread( () -> {
            try {
                ServerSocket serverSocket = new ServerSocket( PORT );

                Platform.runLater( () -> taLog.appendText( new Date() + ": Server started at socket " + PORT +"\n" ) );

                while(true) {
                    Platform.runLater( () -> taLog.appendText( new Date() + ": Wait for players to join session " + sessionNo + "\n" ) );

                    //Player 1
                    Socket player1 = serverSocket.accept();

                    //Notify player 1
                    new DataOutputStream( player1.getOutputStream() ).writeInt( PLAYER1 );

                    Platform.runLater( () -> {
                        taLog.appendText( new Date() + ": player 1 joined session " + sessionNo + "\n" );
                        taLog.appendText( "Player 1's IP Address " + player1.getInetAddress().getHostAddress() + "\n" );
                    } );

                    //Player 2
                    Socket player2 = serverSocket.accept();

                    //Notify player 2
                    new DataOutputStream( player2.getOutputStream() ).writeInt( PLAYER2 );

                    Platform.runLater( () -> {
                        taLog.appendText( new Date() + ": player2 joined session " + sessionNo + "\n" );
                        taLog.appendText( "Player 2's IP Address " + player2.getInetAddress().getHostAddress() + "\n" );
                    } );

                    //Display this session and increment session number
                    Platform.runLater( () ->
                        taLog.appendText( new Date() + ": Start a thread for session " + sessionNo++ + "\n" )
                    );

                    //Launch a new thread for this session of two players
                    new Thread( new HandleASession( player1, player2 ) ).start();
                }
            } catch( IOException ex ) {
                ex.printStackTrace();
            }
        } ).start();
    }
}
