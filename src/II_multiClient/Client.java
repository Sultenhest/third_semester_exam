package II_multiClient;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class Client extends Application implements Constants{
    private Stage window;
    private Socket socket;
    private int sessionNo;

    private boolean myTurn = false;
    private char myToken = ' ';
    private char otherToken = ' ';
    private Cell[][] cell = new Cell[ BOARDDEPTH ][ BOARDWIDTH ];

    private int columnSelected;

    private DataInputStream fromServer;
    private DataOutputStream toServer;

    private boolean continueToPlay = true;
    private boolean waiting = true;

    private String username;
    private Label statusLabel;
    private TextArea taLog = new TextArea();

    @Override
    public void start( Stage primaryStage ) {
        window = primaryStage;

        GridPane pane = new GridPane();

        for( int i = 0; i < BOARDDEPTH; i++ ) {
            for( int j = 0; j < BOARDWIDTH; j++ ) {
                pane.add( cell[i][j] = new Cell( i, j ), j, i );
            }
        }

        VBox bottomLayout = new VBox(10);
        statusLabel = new Label( "Waiting for the other player." );
        bottomLayout.getChildren().addAll( statusLabel, taLog );

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter( pane );
        borderPane.setBottom( bottomLayout );

        Scene scene = new Scene( borderPane, CLIENTWINDOWHEIGHT, CLIENTWINDOWWIDTH );
        window.setTitle( "Client on session #" + sessionNo );
        window.setScene( scene );
        window.show();

        connectToServer();
    }

    private void connectToServer() {
        try {
            socket = new Socket( LOCALHOST, PORT );

            fromServer = new DataInputStream( socket.getInputStream() );
            toServer = new DataOutputStream( socket.getOutputStream() );
        } catch ( IOException ex ) {
            ex.printStackTrace();
        }

        username = ClientPopUp.getUsername();

        //Control the game on a separate thread
        new Thread( () -> {
            try {
                //Get notification from server
                int player = fromServer.readInt();
                addToTextLog( "Hello " + username + "! You are player " + player + "!" );

                //Am i player 1 or 2
                if( player == PLAYER1 ) {
                    myToken = 'X';
                    otherToken = 'O';

                    addToTextLog( "Waiting for player 2 to join" );
                    addToTextLog( "Your color is BLUE" );

                    //Receive startup notification from server
                    fromServer.readInt();

                    yourTurn();

                    myTurn = true;
                } else if( player == PLAYER2 ) {
                    myToken = 'O';
                    otherToken = 'X';

                    addToTextLog( "Your color is RED" );

                    notYourTurn();
                }

                while( continueToPlay ) {
                    if( player == PLAYER1 ) {
                        waitForPlayerAction();
                        sendMove();
                        receiveInfoFromServer();
                    }else{
                        receiveInfoFromServer();
                        waitForPlayerAction();
                        sendMove();
                    }
                }
            } catch( IOException ex ) {
                ex.printStackTrace();
            }
        } ).start();
    }

    private void addToTextLog( String str ) {
        Platform.runLater( () -> taLog.appendText( str + "\n" ) );
    }

    private void yourTurn() {
        Platform.runLater( () -> statusLabel.setText( "Your turn!" ) );
    }

    private void notYourTurn() {
        Platform.runLater( () -> statusLabel.setText( "Waiting for opponent to make a move!" ) );
    }

    private void waitForPlayerAction() {
        try {
            while( waiting ) {
                Thread.sleep( 100 );
            }
            waiting = true;
        } catch( InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void sendMove() {
        try {
            notYourTurn();
            toServer.writeInt( columnSelected );

            receiveMove( myToken );
        } catch( IOException ex ) {
            ex.printStackTrace();
        }
    }

    private void receiveInfoFromServer() {
        try {
            int status = fromServer.readInt();

            if( status == PLAYER1_WON ) {
                continueToPlay = false;
                if( myToken == 'X' ) {
                    addToTextLog( "You Won! Congratulations" );
                } else if ( myToken == 'O' ) {
                    addToTextLog( "You lost! You have brought shame upon your kin!" );
                    receiveMove( otherToken );
                }
            } else if( status == PLAYER2_WON ) {
                continueToPlay = false;
                if( myToken == 'O' ) {
                    addToTextLog( "You Won! Congratulations" );
                } else if( myToken == 'X' ) {
                    addToTextLog( "You lost! You have brought shame upon your kin!" );
                    receiveMove( otherToken );
                }
            } else if( status == DRAW ) {
                continueToPlay = false;
                addToTextLog( "Draw! Game is over" );

                if( myToken == 'O' ) {
                    receiveMove( otherToken );
                }
            } else {
                yourTurn();
                receiveMove( otherToken );
                myTurn = true;
            }
        } catch( IOException ex ) {
            ex.printStackTrace();
        }
    }

    private void receiveMove( char token ) {
        try {
            int row = fromServer.readInt();
            int column = fromServer.readInt();

            Platform.runLater( () -> cell[row][column].setToken( token ) );
        } catch( IOException ex ) {
            ex.printStackTrace();
        }
    }

    public void runClient( int session ) {
        sessionNo = session;
        start( new Stage() );
    }

    public class Cell extends Pane {
        private int row;
        private int column;

        private char token = ' ';

        public Cell( int row, int column ) {
            this.row = row;
            this.column = column;
            this.setPrefSize( 2000, 2000 );
            setStyle( "-fx-border-color: black" );
            this.setOnMouseClicked( e -> handleMouseClick() );
        }

        public void setToken(char c) {
            token = c;
            repaint();
        }

        protected void repaint() {
            Ellipse ellipse = new Ellipse(this.getWidth() / 2, this.getHeight() / 2, this.getWidth() / 2 - 10, this.getHeight() / 2 - 10);
            ellipse.centerXProperty().bind(this.widthProperty().divide(2));
            ellipse.centerYProperty().bind(this.heightProperty().divide(2));
            ellipse.radiusXProperty().bind(this.widthProperty().divide(2).subtract(10));
            ellipse.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));
            ellipse.setStroke(Color.BLACK);

            if (token == 'X') {
                ellipse.setFill(Color.BLUE);
            } else {
                ellipse.setFill(Color.RED);
            }

            getChildren().add( ellipse );
        }

        private void handleMouseClick() {
            if( token == ' ' && myTurn ) {
                myTurn = false;
                columnSelected = column;
                waiting = false;
            }
        }
    }
}
