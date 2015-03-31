
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package multiplayerchess;
import java.awt.*;
import java.io.Serializable;
import javax.swing.*;
import multiplayerchess.Piece.COLOR;
import multiplayerchess.Piece.TYPE;


public class ChessBoard extends JFrame implements Serializable {
    
    //public static Piece[][] board = new Piece[8][8];
    public Piece[][] board = new Piece[8][8];
    public  COLOR gamestate;
    View v;

    
    
    public ChessBoard(){
        initializeBoard();
        v = new View();
    
    }
   
    public void initializeBoard(){
        for(int i=0;i<8;i++)
           addPiece(COLOR.BLACK, TYPE.PAWN, 6, i);
       addPiece(COLOR.BLACK, TYPE.ROOK,7,0);
       addPiece(COLOR.BLACK, TYPE.KNIGHT,7,1);
       addPiece(COLOR.BLACK, TYPE.BISHOP,7,2);
       addPiece(COLOR.BLACK, TYPE.QUEEN,7,3);
       addPiece(COLOR.BLACK, TYPE.KING,7,4);
       addPiece(COLOR.BLACK, TYPE.BISHOP,7,5);
       addPiece(COLOR.BLACK, TYPE.KNIGHT,7,6);
       addPiece(COLOR.BLACK, TYPE.ROOK,7,7);
       for(int i=0;i<8;i++)
           addPiece(COLOR.WHITE, TYPE.PAWN, 1, i);
       addPiece(COLOR.WHITE, TYPE.ROOK,0,0);
       addPiece(COLOR.WHITE, TYPE.KNIGHT,0,1);
       addPiece(COLOR.WHITE, TYPE.BISHOP,0,2);
       addPiece(COLOR.WHITE, TYPE.QUEEN,0,3);
       addPiece(COLOR.WHITE, TYPE.KING,0,4);
       addPiece(COLOR.WHITE, TYPE.BISHOP,0,5);
       addPiece(COLOR.WHITE, TYPE.KNIGHT,0,6);
       addPiece(COLOR.WHITE, TYPE.ROOK,0,7);

       addPiece(COLOR.WHITE, TYPE.QUEEN, 3,4);
       addPiece(COLOR.BLACK, TYPE.QUEEN, 0,2);
       
       /*   
       addPiece(COLOR.WHITE, TYPE.PAWN,0,6);
       addPiece(COLOR.BLACK, TYPE.PAWN,1,1);
       
       addPiece(COLOR.WHITE, TYPE.PAWN,1,1);
       addPiece(COLOR.BLACK, TYPE.QUEEN,1,6);
       */
       this.gamestate= COLOR.WHITE;
    }
    
    
    
    
    

    public void addPiece(COLOR color, TYPE type, int row, int col){
        board[col][row] = new Piece(color,type,row,col);  
    }
    
    public void changeState(){   
        switch(gamestate){
            case WHITE: gamestate= COLOR.BLACK; break;
            case BLACK: gamestate=COLOR.WHITE; break;
        }
    }
}