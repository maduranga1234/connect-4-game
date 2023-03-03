package lk.ijse.dep.service;

import lk.ijse.dep.controller.BoardController;
import lk.ijse.dep.service.Board;

public class BoardImpl implements Board {
    private BoardUI boardUI;
    Piece[][] pieces;

    public BoardImpl(BoardUI boardUI) {
        this.boardUI = boardUI;
        pieces = new Piece[NUM_OF_COLS][NUM_OF_ROWS];

        for (int i = 0; i < NUM_OF_COLS; i++) {
            for (int j = 0; j < NUM_OF_ROWS; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }


    }


    @Override
    public BoardUI getBoardUI() {

        System.out.println(pieces[0][0]);
        System.out.println(pieces[0][1]);
        System.out.println(pieces[0][2]);
        return boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        int count = -1;
        for (int i = 0; i < NUM_OF_ROWS; i++) {
            if (pieces[col][i] == Piece.EMPTY) {
                count = i;
                break;
            } else {
                count = -1;
            }
        }

        return count;

    }

    @Override
    public boolean isLegalMove(int col) {
        int count = findNextAvailableSpot(col);
        if (count == -1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean existLegalMoves() {
        boolean tru = false;
        for (int i=0; i<6; i++){
            for(int j=0; j<5; j++){
                if(pieces[i][j] == Piece.EMPTY){
                    tru = true;
                    break;
                }
            }
        }
  return tru;
    }

    @Override
    public void updateMove(int col, Piece move) {
        int count = findNextAvailableSpot(col);
        pieces[col][count] = move;
    }

    @Override
    public Winner findWinner() {

        for (int i = 0; i < NUM_OF_COLS; i++) {
            int countBlue=0;
            int countGreen=0;
            for (int j = 0; j <NUM_OF_ROWS; j++) {
                if (pieces[i][j]==Piece.BLUE){
                    countBlue++;
                    countGreen=0;
                    if (countBlue==4){
                        return new Winner(Piece.BLUE,i,j-3,i,j);
                    }
                }
                if (pieces[i][j]==Piece.GREEN){
                    countGreen++;
                    countBlue=0;
                    if (countGreen==4){
                        return new Winner(Piece.GREEN,i,j-3,i,j);
                    }
                }
            }
        }

        for (int i = 0; i < NUM_OF_ROWS; i++) {
            int countBlue=0;
            int countGreen=0;
            for (int j = 0; j <NUM_OF_COLS; j++) {
                if (pieces[j][i]==Piece.BLUE){
                    countBlue++;
                    countGreen=0;
                    if (countBlue==4){
                        return new Winner(Piece.BLUE,j-3,i,j,i);
                    }
                }
                if (pieces[j][i]==Piece.GREEN){
                    countGreen++;
                    countBlue=0;
                    if (countGreen==4){
                        return new Winner(Piece.GREEN,j-3,i,j,i);
                    }
                }
                if (pieces[j][i]==Piece.EMPTY){
                    countBlue=0;
                    countGreen=0;
                }
            }
        }
        if (existLegalMoves()){
            return new Winner(Piece.EMPTY);
        }
        return null;
    }
    @Override
    public void updateMove(int col, int row, Piece move){
          pieces[col][row]=move;
    }
}