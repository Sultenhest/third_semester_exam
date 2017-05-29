package II_multiClient;

import java.io.*;
import java.net.Socket;

class HandleASession implements Runnable, Constants {
    private Socket player1;
    private Socket player2;

    private char[][] cell = new char[ BOARDDEPTH ][ BOARDWIDTH ];

    private DataInputStream fromPlayer1;
    private DataOutputStream toPlayer1;
    private DataInputStream fromPlayer2;
    private DataOutputStream toPlayer2;

    public HandleASession(Socket player1, Socket player2 ) {
        this.player1 = player1;
        this.player2 = player2;

        initCells();
    }

    private void initCells() {
        for( int i = 0; i < BOARDDEPTH; i++ ) {
            for( int j = 0; j < BOARDWIDTH; j++ ) {
                cell[i][j] = ' ';
            }
        }
    }

    //Implement run
    public void run() {
        try {
            //Create data input and output streams
            fromPlayer1 = new DataInputStream( player1.getInputStream() );
            toPlayer1 = new DataOutputStream( player1.getOutputStream() );
            fromPlayer2 = new DataInputStream( player2.getInputStream() );
            toPlayer2 = new DataOutputStream( player2.getOutputStream() );

            //Notify player 1 to start
            toPlayer1.writeInt(1);

            while( true ) {
                //Get first move from player1
                int column = fromPlayer1.readInt();
                int row = getRowPlace( column );

                //Add to board and send move back to player 1 gui
                cell[row][column] = 'X';
                sendMove( toPlayer1, row, column );

                //Check if Player 1 wins
                if( isWon( 'X' ) ) {
                    toPlayer1.writeInt( PLAYER1_WON );
                    toPlayer2.writeInt( PLAYER1_WON );

                    sendMove( toPlayer2, row, column );
                    break; //Break loop
                } else if( isFull() ) { //Check if all cells are filled
                    toPlayer1.writeInt( DRAW );
                    toPlayer2.writeInt( DRAW );

                    sendMove( toPlayer2, row, column );
                    break; //Break loop
                } else {
                    //Notify player 2 to take the turn
                    toPlayer2.writeInt( CONTINUE );
                    //Send move to player 2
                    sendMove( toPlayer2, row, column );
                }

                //Receive move from player 2
                column = fromPlayer2.readInt();
                row = getRowPlace( column );

                //Add to board and send move back to player 2 gui
                cell[row][column] = 'O';
                sendMove( toPlayer2, row, column );

                //If player 2 wins
                if( isWon( 'O' ) ) {
                    toPlayer1.writeInt( PLAYER2_WON );
                    toPlayer2.writeInt( PLAYER2_WON );
                    sendMove( toPlayer1, row, column );
                    break; //Break loop
                } else {
                    //Notify player 1 to take the turn
                    toPlayer1.writeInt( CONTINUE );

                    //Send move to player 1
                    sendMove( toPlayer1, row, column );
                }
            }
        } catch( IOException ex ) {
            ex.printStackTrace();
        }
    }

    private void sendMove( DataOutputStream out, int row, int column ) {
        try {
            out.writeInt(row);
            out.writeInt(column);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private int getRowPlace( int column ){
        if( cell[ BOARDDEPTH - 1 ][column] == ' ' ) {
            return BOARDDEPTH - 1;
        }

        for( int i = 0; i < BOARDDEPTH; i++ ) {
            if( cell[i][column] != ' ' ) {
                return i - 1;
            }
        }

        return -1;
    }

    private boolean isFull() {
        for( int i = 0; i < BOARDDEPTH; i++ ) {
            for( int j = 0; j < BOARDWIDTH; j++ ) {
                if( cell[i][j] == ' ' ) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isWon( char token ) {
        //Rows
        for( int j = 0; j < BOARDWIDTH - 3 ; j++ ) {
            for( int i = 0; i < BOARDDEPTH ; i++ ) {
                if( cell[i][j] == token && cell[i][j+1] == token && cell[i][j+2] == token && cell[i][j+3] == token ){
                    return true;
                }
            }
        }

        //Columns
        for( int i = 0; i < BOARDDEPTH - 3 ; i++ ) {
            for( int j = 0; j < BOARDWIDTH ; j++ ) {
                if( cell[i][j] == token && cell[i+1][j] == token && cell[i+2][j] == token && cell[i+3][j] == token ){
                    return true;
                }
            }
        }

        //Ascending Diagonal
        for( int i = 3; i < BOARDDEPTH ; i++ ){
            for ( int j = 0; j < BOARDWIDTH-3; j++ ){
                if( cell[i][j] == token && cell[i-1][j+1] == token && cell[i-2][j+2] == token && cell[i-3][j+3] == token ) {
                    return true;
                }
            }
        }

        //Descending Diagonal
        for( int i = 3; i < BOARDDEPTH; i++ ){
            for ( int j = 3; j < BOARDWIDTH; j++ ){
                if( cell[i][j] == token && cell[i-1][j-1] == token && cell[i-2][j-2] == token && cell[i-3][j-3] == token ) {
                    return true;
                }
            }
        }

        //No winner
        return false;
    }
}
