package com.example.pc.halzka;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

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
            String tempBoard[] = new String[9];
            for(int j = 0; j < 9; j++){
                tempBoard[j] = board1[j];
            }
            tempBoard[availableSpots.get(i)] = aiPlayer;
            if(winning(aiPlayer,tempBoard)){
                Log.v("checkMove","+20");
                score += 20;
            }

            else if(blockPlayer(huPlayer,tempBoard,availableSpots.get(i))){
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
        Log.v("checkEmpty","empty cells  = " + emptyIndex.size());
        return emptyIndex;
    }

    public int getRandomNumber(){
        ArrayList<Integer> emptyCells = new ArrayList<>();
        Random random = new Random();
        int randomNumber = random.nextInt((emptyCells.size() - 0));
        return emptyCells.get(randomNumber);
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

    public boolean blockPlayer(String player, String board[], Integer lastMove){
        Boolean boo = false;
        String player2 = "";
        if(player.equals("o")){
            player2 = "x";
        }
        else{
            player2 = "o";
        }
        if(board1[lastMove] == null){
            Log.v("checkBlock","that was last move " + lastMove);
        }
        if(
                ((board[0] != null && board[0].equals(player)) && (board[1] != null && board[1].equals(player)) && (board[2] != null && board[2].equals(player2)) && (lastMove == 2) ) ||
                                ((board[0] != null && board[0].equals(player2)) && (board[1] != null && board[1].equals(player)) && (board[2] != null && board[2].equals(player)) && (lastMove == 0) ) ||
                                ((board[0] != null && board[0].equals(player)) && (board[1] != null && board[1].equals(player2)) && (board[2] != null && board[2].equals(player)) && (lastMove == 1) ) ||

                                ((board[3] != null && board[3].equals(player)) && (board[4] != null && board[4].equals(player)) && (board[5] != null && board[5].equals(player2)) && (lastMove == 5) ) ||
                                ((board[3] != null && board[3].equals(player2)) && (board[4] != null && board[4].equals(player)) && (board[5] != null && board[5].equals(player)) && (lastMove == 3)) ||
                                ((board[3] != null && board[3].equals(player)) && (board[4] != null && board[4].equals(player2)) && (board[5] != null && board[5].equals(player)) && (lastMove == 4)) ||


                                ((board[6] != null && board[6].equals(player)) && (board[7] != null && board[7].equals(player)) && (board[8] != null && board[8].equals(player2)) && (lastMove == 8)) ||
                                ((board[6] != null && board[6].equals(player2)) && (board[7] != null && board[7].equals(player)) && (board[8] != null && board[8].equals(player)) && (lastMove == 6)) ||
                                ((board[6] != null && board[6].equals(player)) && (board[7] != null && board[7].equals(player2)) && (board[8] != null && board[8].equals(player)) && (lastMove == 7)) ||


                                ((board[0] != null && board[0].equals(player)) && (board[3] != null && board[3].equals(player)) && (board[6] != null && board[6].equals(player2)) && (lastMove == 6)) ||
                                ((board[0] != null && board[0].equals(player2)) && (board[3] != null && board[3].equals(player)) && (board[6] != null && board[6].equals(player)) && (lastMove == 0)) ||
                                ((board[0] != null && board[0].equals(player)) && (board[3] != null && board[3].equals(player2)) && (board[6] != null && board[6].equals(player)) && (lastMove == 3)) ||


                                ((board[1] != null && board[1].equals(player)) && (board[4] != null && board[4].equals(player)) && (board[7] != null && board[7].equals(player2)) && (lastMove == 7)) ||
                                ((board[1] != null && board[1].equals(player2)) && (board[4] != null && board[4].equals(player)) && (board[7] != null && board[7].equals(player)) && (lastMove == 1)) ||
                                ((board[1] != null && board[1].equals(player)) && (board[4] != null && board[4].equals(player2)) && (board[7] != null && board[7].equals(player)) && (lastMove == 4)) ||


                                ((board[2] != null && board[2].equals(player)) && (board[5] != null && board[5].equals(player)) && (board[8] != null && board[8].equals(player2)) && (lastMove == 8)) ||
                                ((board[2] != null && board[2].equals(player2)) && (board[5] != null && board[5].equals(player)) && (board[8] != null && board[8].equals(player)) && (lastMove == 2)) ||
                                ((board[2] != null && board[2].equals(player)) && (board[5] != null && board[5].equals(player2)) && (board[8] != null && board[8].equals(player)) && (lastMove == 5)) ||


                                ((board[0] != null && board[0].equals(player)) && (board[4] != null && board[4].equals(player)) && (board[8] != null && board[8].equals(player2)) && (lastMove == 8)) ||
                                ((board[0] != null && board[0].equals(player2)) && (board[4] != null && board[4].equals(player)) && (board[8] != null && board[8].equals(player)) && (lastMove == 0)) ||
                                ((board[0] != null && board[0].equals(player)) && (board[4] != null && board[4].equals(player2)) && (board[8] != null && board[8].equals(player)) && (lastMove == 4)) ||


                                ((board[2] != null && board[2].equals(player)) && (board[4] != null && board[4].equals(player)) && (board[6] != null && board[6].equals(player2)) && (lastMove == 6)) ||
                                ((board[2] != null && board[2].equals(player2)) && (board[4] != null && board[4].equals(player)) && (board[6] != null && board[6].equals(player)) && (lastMove == 2)) ||
                                ((board[2] != null && board[2].equals(player)) && (board[4] != null && board[4].equals(player2)) && (board[6] != null && board[6].equals(player)) && (lastMove == 4))

                ){
            boo = true;
        }
        return boo;
    }



}
