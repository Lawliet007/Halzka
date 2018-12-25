package com.example.pc.halzka;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PointF;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.halzka.models.Classification;
import com.example.pc.halzka.models.Classifier;
import com.example.pc.halzka.models.TensorFlowClassifier;
import com.example.pc.halzka.views.DrawModel;
import com.example.pc.halzka.views.DrawView;

import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import me.panavtec.drawableview.DrawableView;
import me.panavtec.drawableview.DrawableViewConfig;

public class GameActivity extends Activity implements View.OnClickListener{
    private static final int PIXEL_WIDTH = 28;
    private DrawView  drawViews[], drawableView1, drawableView2, drawableView3, drawableView4, drawableView5, drawableView6, drawableView7, drawableView8, drawableView9;
    private DrawModel drawModels[], drawModel1, drawModel2, drawModel3, drawModel4, drawModel5, drawModel6, drawModel7, drawModel8, drawModel9;
    private TextView textViews[], textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9;
    private ImageView imageViews[], imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9;
    private DrawableViewConfig config = new DrawableViewConfig();
    private Classifier mClassifier;
    private Button btnReset;
    private static String board[] = new String[9];
    private static ArrayList<Integer> filledCells = new ArrayList<>();
    private ArrayList<Integer> boardValue = new ArrayList<>();
    private PointF mTmpPiont = new PointF();
    private float mLastX;
    private float mLastY;
    private int code = 1;
    private String lastTurn = "";


    class GameActivity01 implements View.OnTouchListener {
        GameActivity01() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return GameActivity.this.onTouuuuuu(motionEvent, GameActivity.this.drawModel1, GameActivity.this.drawableView1);
        }
    }

    class GameActivity02 implements View.OnTouchListener {
        GameActivity02() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return GameActivity.this.onTouuuuuu(motionEvent, GameActivity.this.drawModel2, GameActivity.this.drawableView2);
        }
    }

    class GameActivity03 implements View.OnTouchListener {
        GameActivity03() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return GameActivity.this.onTouuuuuu(motionEvent, GameActivity.this.drawModel3, GameActivity.this.drawableView3);
        }
    }

    class GameActivity04 implements View.OnTouchListener {
        GameActivity04() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return GameActivity.this.onTouuuuuu(motionEvent, GameActivity.this.drawModel4, GameActivity.this.drawableView4);
        }
    }

    class GameActivity05 implements View.OnTouchListener {
        GameActivity05() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return GameActivity.this.onTouuuuuu(motionEvent, GameActivity.this.drawModel5, GameActivity.this.drawableView5);
        }
    }

    class GameActivity06 implements View.OnTouchListener {
        GameActivity06() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return GameActivity.this.onTouuuuuu(motionEvent, GameActivity.this.drawModel6, GameActivity.this.drawableView6);
        }
    }

    class GameActivity07 implements View.OnTouchListener {
        GameActivity07() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return GameActivity.this.onTouuuuuu(motionEvent, GameActivity.this.drawModel7, GameActivity.this.drawableView7);
        }
    }

    class GameActivity08 implements View.OnTouchListener {
        GameActivity08() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return GameActivity.this.onTouuuuuu(motionEvent, GameActivity.this.drawModel8, GameActivity.this.drawableView8);
        }
    }

    class GameActivity09 implements View.OnTouchListener {
        GameActivity09() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return GameActivity.this.onTouuuuuu(motionEvent, GameActivity.this.drawModel9, GameActivity.this.drawableView9);
        }
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btnReset = findViewById(R.id.btn_clear);
        btnReset.setOnClickListener(this);
        code = getIntent().getIntExtra("code",1);
        loadModel();
        filledCells.clear();
        drawModel1 = new DrawModel(PIXEL_WIDTH,PIXEL_WIDTH);
        drawModel2 = new DrawModel(PIXEL_WIDTH,PIXEL_WIDTH);
        drawModel3 = new DrawModel(PIXEL_WIDTH,PIXEL_WIDTH);
        drawModel4 = new DrawModel(PIXEL_WIDTH,PIXEL_WIDTH);
        drawModel5 = new DrawModel(PIXEL_WIDTH,PIXEL_WIDTH);
        drawModel6 = new DrawModel(PIXEL_WIDTH,PIXEL_WIDTH);
        drawModel7 = new DrawModel(PIXEL_WIDTH,PIXEL_WIDTH);
        drawModel8 = new DrawModel(PIXEL_WIDTH,PIXEL_WIDTH);
        drawModel9 = new DrawModel(PIXEL_WIDTH,PIXEL_WIDTH);

        drawModels = new DrawModel[9];
        drawModels[0] = drawModel1;
        drawModels[1] = drawModel2;
        drawModels[2] = drawModel3;
        drawModels[3] = drawModel4;
        drawModels[4] = drawModel5;
        drawModels[5] = drawModel6;
        drawModels[6] = drawModel7;
        drawModels[7] = drawModel8;
        drawModels[8] = drawModel9;

        this.drawableView1 = findViewById(R.id.draw00);
        this.drawableView2 = findViewById(R.id.draw01);
        this.drawableView3 = findViewById(R.id.draw02);
        this.drawableView4 = findViewById(R.id.draw10);
        this.drawableView5 = findViewById(R.id.draw11);
        this.drawableView6 = findViewById(R.id.draw12);
        this.drawableView7 = findViewById(R.id.draw20);
        this.drawableView8 = findViewById(R.id.draw21);
        this.drawableView9 = findViewById(R.id.draw22);

        drawViews = new DrawView[9];
        drawViews[0] = drawableView1;
        drawViews[1] = drawableView2;
        drawViews[2] = drawableView3;
        drawViews[3] = drawableView4;
        drawViews[4] = drawableView5;
        drawViews[5] = drawableView6;
        drawViews[6] = drawableView7;
        drawViews[7] = drawableView8;
        drawViews[8] = drawableView9;

        this.textView1 = findViewById(R.id.tv_draw00);
        this.textView2 = findViewById(R.id.tv_draw01);
        this.textView3 = findViewById(R.id.tv_draw02);
        this.textView4 = findViewById(R.id.tv_draw10);
        this.textView5 = findViewById(R.id.tv_draw11);
        this.textView6 = findViewById(R.id.tv_draw12);
        this.textView7 = findViewById(R.id.tv_draw20);
        this.textView8 = findViewById(R.id.tv_draw21);
        this.textView9 = findViewById(R.id.tv_draw22);

        textViews = new TextView[9];
        textViews[0] = textView1;
        textViews[1] = textView2;
        textViews[2] = textView3;
        textViews[3] = textView4;
        textViews[4] = textView5;
        textViews[5] = textView6;
        textViews[6] = textView7;
        textViews[7] = textView8;
        textViews[8] = textView9;

        this.imageView1 = findViewById(R.id.image00);
        this.imageView2 = findViewById(R.id.image01);
        this.imageView3 = findViewById(R.id.image02);
        this.imageView4 = findViewById(R.id.image10);
        this.imageView5 = findViewById(R.id.image11);
        this.imageView6 = findViewById(R.id.image12);
        this.imageView7 = findViewById(R.id.image20);
        this.imageView8 = findViewById(R.id.image21);
        this.imageView9 = findViewById(R.id.image22);

        imageViews = new ImageView[9];
        imageViews[0] = imageView1;
        imageViews[1] = imageView2;
        imageViews[2] = imageView3;
        imageViews[3] = imageView4;
        imageViews[4] = imageView5;
        imageViews[5] = imageView6;
        imageViews[6] = imageView7;
        imageViews[7] = imageView8;
        imageViews[8] = imageView9;

        this.drawableView1.setModel(this.drawModel1);
        this.drawableView1.reset();
        this.drawableView1.setOnTouchListener(new GameActivity01());

        this.drawableView2.setModel(this.drawModel2);
        this.drawableView2.reset();
        this.drawableView2.setOnTouchListener(new GameActivity02());

        this.drawableView3.setModel(this.drawModel3);
        this.drawableView3.reset();
        this.drawableView3.setOnTouchListener(new GameActivity03());

        this.drawableView4.setModel(this.drawModel4);
        this.drawableView4.reset();
        this.drawableView4.setOnTouchListener(new GameActivity04());

        this.drawableView5.setModel(this.drawModel5);
        this.drawableView5.reset();
        this.drawableView5.setOnTouchListener(new GameActivity05());

        this.drawableView6.setModel(this.drawModel6);
        this.drawableView6.reset();
        this.drawableView6.setOnTouchListener(new GameActivity06());

        this.drawableView7.setModel(this.drawModel7);
        this.drawableView7.reset();
        this.drawableView7.setOnTouchListener(new GameActivity07());

        this.drawableView8.setModel(this.drawModel8);
        this.drawableView8.reset();
        this.drawableView8.setOnTouchListener(new GameActivity08());

        this.drawableView9.setModel(this.drawModel9);
        this.drawableView9.reset();
        this.drawableView9.setOnTouchListener(new GameActivity09());

        reset();
    }

    private void loadModel() {
        //The Runnable interface is another way in which you can implement multi-threading other than extending the
        // //Thread class due to the fact that Java allows you to extend only one class. Runnable is just an interface,
        // //which provides the method run.
        // //Threads are implementations and use Runnable to call the method run().
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //add 2 classifiers to our classifier arraylist
                    //the tensorflow classifier and the keras classifier
                    mClassifier =
                            TensorFlowClassifier.create(getAssets(), "TensorFlow",
                                    "opt_xo_differ.pb", "labels.txt", PIXEL_WIDTH,
                                    "input", "output", true);
//                    mClassifiers.add(
//                            TensorFlowClassifier.create(getAssets(), "Keras",
//                                    "opt_mnist_convnet-keras.pb", "labels.txt", PIXEL_WIDTH,
//                                    "conv2d_1_input", "dense_2/Softmax", false));
                } catch (final Exception e) {
                    //if they aren't found, throw an error!
                    throw new RuntimeException("Error initializing classifiers!", e);
                }
            }
        }).start();
    }


    public ArrayList<Integer> checkempty() {
        ArrayList<Integer> arrayList = new ArrayList();
        arrayList.clear();
        if (this.textView1.getText().toString().equals("-")) {
            arrayList.add(Integer.valueOf(0));
        }
        if (this.textView2.getText().toString().equals("-")) {
            arrayList.add(Integer.valueOf(1));
        }
        if (this.textView3.getText().toString().equals("-")) {
            arrayList.add(Integer.valueOf(2));
        }
        if (this.textView4.getText().toString().equals("-")) {
            arrayList.add(Integer.valueOf(3));
        }
        if (this.textView5.getText().toString().equals("-")) {
            arrayList.add(Integer.valueOf(4));
        }
        if (this.textView6.getText().toString().equals("-")) {
            arrayList.add(Integer.valueOf(5));
        }
        if (this.textView7.getText().toString().equals("-")) {
            arrayList.add(Integer.valueOf(6));
        }
        if (this.textView8.getText().toString().equals("-")) {
            arrayList.add(Integer.valueOf(7));
        }
        if (this.textView9.getText().toString().equals("-")) {
            arrayList.add(Integer.valueOf(8));
        }
        return arrayList;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_clear:
                reset();
                break;
        }
    }

    @Override
    //OnResume() is called when the user resumes his Activity which he left a while ago,
    // //say he presses home button and then comes back to app, onResume() is called.
    protected void onResume() {
        this.drawableView1.onResume();
        this.drawableView2.onResume();
        this.drawableView3.onResume();
        this.drawableView4.onResume();
        this.drawableView5.onResume();
        this.drawableView6.onResume();
        this.drawableView7.onResume();
        this.drawableView8.onResume();
        this.drawableView9.onResume();
        super.onResume();
    }

    @Override
    //OnPause() is called when the user receives an event like a call or a text message,
    // //when onPause() is called the Activity may be partially or completely hidden.
    protected void onPause() {
        this.drawableView1.onPause();
        this.drawableView2.onPause();
        this.drawableView3.onPause();
        this.drawableView4.onPause();
        this.drawableView5.onPause();
        this.drawableView6.onPause();
        this.drawableView7.onPause();
        this.drawableView8.onPause();
        this.drawableView9.onPause();
        super.onPause();
    }

    public void reset(){
        lastTurn = "";
        filledCells.clear();
        for(int i = 0; i < 9; i ++){
            board[i] = null;
        }
        onClear(this.drawModel1,this.drawableView1);
        onClear(this.drawModel2,this.drawableView2);
        onClear(this.drawModel3,this.drawableView3);
        onClear(this.drawModel4,this.drawableView4);
        onClear(this.drawModel5,this.drawableView5);
        onClear(this.drawModel6,this.drawableView6);
        onClear(this.drawModel7,this.drawableView7);
        onClear(this.drawModel8,this.drawableView8);
        onClear(this.drawModel9,this.drawableView9);

        this.textView1.setText("-");
        this.textView2.setText("-");
        this.textView3.setText("-");
        this.textView4.setText("-");
        this.textView5.setText("-");
        this.textView6.setText("-");
        this.textView7.setText("-");
        this.textView8.setText("-");
        this.textView9.setText("-");

        this.drawableView1.setVisibility(View.VISIBLE);
        this.drawableView2.setVisibility(View.VISIBLE);
        this.drawableView3.setVisibility(View.VISIBLE);
        this.drawableView4.setVisibility(View.VISIBLE);
        this.drawableView5.setVisibility(View.VISIBLE);
        this.drawableView6.setVisibility(View.VISIBLE);
        this.drawableView7.setVisibility(View.VISIBLE);
        this.drawableView8.setVisibility(View.VISIBLE);
        this.drawableView9.setVisibility(View.VISIBLE);

        this.imageView1.setVisibility(View.GONE);
        this.imageView2.setVisibility(View.GONE);
        this.imageView3.setVisibility(View.GONE);
        this.imageView4.setVisibility(View.GONE);
        this.imageView5.setVisibility(View.GONE);
        this.imageView6.setVisibility(View.GONE);
        this.imageView7.setVisibility(View.GONE);
        this.imageView8.setVisibility(View.GONE);
        this.imageView9.setVisibility(View.GONE);
    }

    public void onClear(DrawModel drawModel, DrawView drawView) {
        Log.v("checkFuckingOnClear","its called");
        drawModel.clear();
        drawView.reset();
        drawView.invalidate();
    }

    public void moveHuman(String result, int index){
        if(board[index] == null){
            board[index] = result;
            textViews[index].setText(result);
            filledCells.add(index);
            if(result.equals("x")){
                lastTurn = "x";
            }
            else if(result.equals("o")){
                lastTurn = "o";
            }
            if(checkWinner() != null){
                Toast.makeText(getApplicationContext(),"Winner is " + checkWinner(),Toast.LENGTH_SHORT).show();
            }
            else if(checkWinner() == null && filledCells.size() == 9){
                Toast.makeText(getApplicationContext(),"Draw",Toast.LENGTH_SHORT).show();
            }

            else if(checkWinner() == null && filledCells.size() < 9){
                if(code == 2 && filledCells.size() < 9){
                    String tempboard[] = board;
                    MiniMax miniMax = new MiniMax(tempboard,lastTurn);
                    int bestMove = miniMax.getBestMove();
                    Log.v("checkMinMax","bestMove = " + bestMove);
//                    moveBot(getRandomNumber());
                    moveBot(bestMove);
                }
            }
        }

    }

    public void moveBot(int index){
        Log.v("botMove","moveBot called, last turn = " + lastTurn + " index = " + index);
        for(int i = 0; i < 9; i++){
            Log.v("botMove","moveBot called, value = " + i + " " + board[i]);
        }
        if(board[index] == null){
            Log.v("botMove","index = " + index);
            String result = "";
            filledCells.add(index);
            if(lastTurn.equals("x")){
                result = "o";
            }
            else if(lastTurn.equals("o")){
                result = "x";
            }
            board[index] = result;
            textViews[index].setText(result);
            if(result.equals("x")){
                lastTurn = "x";
            }
            else if(result.equals("o")){
                lastTurn = "o";
            }
            if(checkWinner() != null){
                Toast.makeText(getApplicationContext(),"Winner is " + checkWinner(),Toast.LENGTH_SHORT).show();
            }
            else if(checkWinner() == null && filledCells.size() == 9){
                Toast.makeText(getApplicationContext(),"Draw",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public int getRandomNumber(){
        ArrayList<Integer> emptyCells = new ArrayList<>();
        for(int i = 0; i < 9; i++){
            if(!filledCells.contains(i)){
                emptyCells.add(i);
            }
        }
        Random random = new Random();
        int randomNumber = random.nextInt((emptyCells.size() - 0));
        return emptyCells.get(randomNumber);
    }



    public void toastShowWinner(){

    }

    static String checkWinner() {
        for (int a = 0; a < 8; a++) {
            String line = null;
            switch (a) {
                case 0:
                    line = board[0] + board[1] + board[2];
                    break;
                case 1:
                    line = board[3] + board[4] + board[5];
                    break;
                case 2:
                    line = board[6] + board[7] + board[8];
                    break;
                case 3:
                    line = board[0] + board[3] + board[6];
                    break;
                case 4:
                    line = board[1] + board[4] + board[7];
                    break;
                case 5:
                    line = board[2] + board[5] + board[8];
                    break;
                case 6:
                    line = board[0] + board[4] + board[8];
                    break;
                case 7:
                    line = board[2] + board[4] + board[6];
                    break;
            }
            if (line.equals("xxx")) {
                return "X";
            } else if (line.equals("ooo")) {
                return "O";
            }
        }

//        for (int a = 0; a < 9; a++) {
//            if (Arrays.asList(board).contains(String.valueOf(a+1))) {
//                break;
//            }
//            else if (a == 8) return "draw";
//        }

//        System.out.println(turn + "'s turn; enter a slot number to place " + turn + " in:");
        return null;
    }
    //draw line down

    public String recognizeBitmap(DrawView drawView){
        String text = "";
        float pixels[] = drawView.getPixelData();
        final Classification res = mClassifier.recognize(pixels);
        //if it can't classify, output a question mark
        if (res.getLabel() == null) {
            text += mClassifier.name() + ": ?\n";
        } else {
            //else output its name
            text += String.format("%s: %s, %f\n", mClassifier.name(), res.getLabel(),
                    res.getConf());
        }

        Log.v("checkRecognitionResult","result = " + text);
        return res.getLabel();
    }

    private boolean onTouuuuuu(MotionEvent motionEvent, DrawModel drawModel, DrawView drawView) {
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            processTouchDown(motionEvent, drawModel, drawView);
            return true;
        } else if (action == 2) {
            processTouchMove(motionEvent, drawModel, drawView);
            return true;
        } else if (action != 1) {
            return Boolean.parseBoolean(null);
        } else {
            processTouchUp(drawModel);
            initializeRecognition(drawView);
            return true;
        }
    }

    public void initializeRecognition(DrawView drawView){
        String result = recognizeBitmap(drawView);
        int index = getIndex(drawView);
        Log.v("checkRecognitionFunc","index = " + index + " result = " + result + " lastTurn = " + lastTurn);
        if(board[index] == null){
            if(lastTurn.equals(result)){
                onClear(drawModels[index],drawViews[index]);
            }
            else{
                moveHuman(result,index);
            }
        }
    }

    public int getIndex(DrawView drawView){
        int index = -1;
        for(int i = 0; i < drawViews.length; i++){
            if(drawView == drawViews[i]){
                index = i;
                break;
            }
        }
        return index;
    }

    private void processTouchDown(MotionEvent event, DrawModel drawModel, DrawView drawView) {
        //calculate the x, y coordinates where the user has touched
        mLastX = event.getX();
        mLastY = event.getY();
        //user them to calcualte the position
        drawView.calcPos(mLastX, mLastY, mTmpPiont);
        //store them in memory to draw a line between the
        //difference in positions
        float lastConvX = mTmpPiont.x;
        float lastConvY = mTmpPiont.y;
        //and begin the line drawing
        drawModel.startLine(lastConvX, lastConvY);
    }

    //the main drawing function
    //it actually stores all the drawing positions
    //into the drawmodel object
    //we actually render the drawing from that object
    //in the drawrenderer class
    private void processTouchMove(MotionEvent event, DrawModel drawModel, DrawView drawView) {
        float x = event.getX();
        float y = event.getY();

        drawView.calcPos(x, y, mTmpPiont);
        float newConvX = mTmpPiont.x;
        float newConvY = mTmpPiont.y;
        drawModel.addLineElem(newConvX, newConvY);

        mLastX = x;
        mLastY = y;
        drawView.invalidate();
    }

    private void processTouchUp(DrawModel drawModel) {
        drawModel.endLine();
    }

}
