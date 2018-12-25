package com.example.pc.halzka;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Davka on 12/6/2018.
 */

public class MiniMax {
    private String board1[];
    private Integer step = 0;
    private String aiPlayer;
    private String huPlayer;

    public MiniMax(String board1[], String player){
        Log.v("checkMinimax","board = " + board1 + " player = " + player);
        this.board1 = board1;
        this.huPlayer = player;
        if(huPlayer.equals("x")){
            aiPlayer = "o";
        }
        else{
            aiPlayer = "x";
        }
    }

    public int getBestMove(){
        ArrayList<Integer> availableSpots = getEmptyIndexies(board1);
        ArrayList<Integer> scores = new ArrayList<>();

        for(int i = 0; i < availableSpots.size(); i++){
            int score = 0;
            String tempBoard[] = board1;
            tempBoard[availableSpots.get(i)] = aiPlayer;
            if(winning(aiPlayer,tempBoard)){
                Log.v("checkMove","+20");
                score += 20;
            }

            else if(blockPlayer(huPlayer,tempBoard)){
                Log.v("checkMove","+10");
                score += 10;
            }

            else if(straight(aiPlayer,tempBoard)){
                Log.v("checkMove","+5");
                score += 5;
            }

            else if(!straight(aiPlayer,tempBoard) && !straight(huPlayer,tempBoard)){
                Log.v("checkMove","+1");
                score += 1;
            }


            tempBoard[availableSpots.get(i)] = null;

            scores.add(score);
        }
        int index = -1;
        index = Collections.max(scores);
        index = scores.indexOf(index);

        Log.v("checkMiniMax","index1 = " + index);

        Log.v("checkMiniMax","index2 = " + index);
        return availableSpots.get(index);
    }

    public ArrayList<Integer> getEmptyIndexies(String board[]){
        ArrayList<Integer> emptyIndex = new ArrayList<>();
        for(int i = 0; i < 9; i++){
            if(board[i] == null){
                emptyIndex.add(i);
            }
        }
        return emptyIndex;
    }

    public boolean winning(String player, String board[]){
        if (
                (board[0] == player && board[1] == player && board[2] == player) ||
                        (board[3] == player && board[4] == player && board[5] == player) ||
                        (board[6] == player && board[7] == player && board[8] == player) ||
                        (board[0] == player && board[3] == player && board[6] == player) ||
                        (board[1] == player && board[4] == player && board[7] == player) ||
                        (board[2] == player && board[5] == player && board[8] == player) ||
                        (board[0] == player && board[4] == player && board[8] == player) ||
                        (board[2] == player && board[4] == player && board[6] == player)
                ) {
            return true;
        } else {
            return false;
        }
    }

    public boolean straight(String player, String board[]){
        if(
                (board[0] == player && board[1] == player && board[2] == null) ||
                        (board[0] == null && board[1] == player && board[2] == player) ||
                        (board[3] == player && board[4] == player && board[5] == null) ||
                        (board[3] == null && board[4] == player && board[5] == player) ||
                        (board[6] == player && board[7] == player && board[8] == null) ||
                        (board[6] == null && board[7] == player && board[8] == player) ||
                        (board[0] == player && board[3] == player && board[6] == null) ||
                        (board[0] == null && board[3] == player && board[6] == player) ||
                        (board[1] == player && board[4] == player && board[7] == null) ||
                        (board[1] == null && board[4] == player && board[7] == player) ||
                        (board[2] == player && board[5] == player && board[8] == null) ||
                        (board[2] == null && board[5] == player && board[8] == player) ||
                        (board[0] == player && board[4] == player && board[8] == null) ||
                        (board[0] == null && board[4] == player && board[8] == player) ||
                        (board[2] == player && board[4] == player && board[6] == null) ||
                        (board[2] == null && board[4] == player && board[6] == player)

                ){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean blockPlayer(String player, String board[]){

        String player2 = "";
        if(player.equals("o")){
            player2 = "x";
        }
        else{
            player2 = "o";
        }
        if(board == null){
            Log.v("chekcBoard","not null");
        }
        Log.v("chekcBoard","player1 = " + player + " player2 = " + player2);
        for(int i = 0; i < board.length; i++){
            if(board[i] != null){
                Log.v("chekcBoard","index = " + i + "  " + board[i]);
            }
        }
        if(
                (board[0] == player && board[1] == player && board[2] == player2) ||
                        (board[0] == player2 && board[1] == player && board[2] == player) ||
                        (board[3] == player && board[4] == player && board[5] == player2) ||
                        (board[3] == player2 && board[4] == player && board[5] == player) ||
                        (board[6] == player && board[7] == player && board[8] == player2) ||
                        (board[6] == player2 && board[7] == player && board[8] == player) ||
                        (board[0] == player && board[3] == player && board[6] == player2) ||
                        (board[0] == player2 && board[3] == player && board[6] == player) ||
                        (board[1] == player && board[4] == player && board[7] == player2) ||
                        (board[1] == player2 && board[4] == player && board[7] == player) ||
                        (board[2] == player && board[5] == player && board[8] == player2) ||
                        (board[2] == player2 && board[5] == player && board[8] == player) ||
                        (board[0] == player && board[4] == player && board[8] == player2) ||
                        (board[0] == player2 && board[4] == player && board[8] == player) ||
                        (board[2] == player && board[4] == player && board[6] == player2) ||
                        (board[2] == player2 && board[4] == player && board[6] == player)
                ){

            return true;
        }
        else{
            return false;
        }
    }
}
