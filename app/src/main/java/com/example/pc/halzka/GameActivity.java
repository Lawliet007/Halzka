package com.example.pc.halzka;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PointF;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.pc.halzka.models.Classification;
import com.example.pc.halzka.models.Classifier;
import com.example.pc.halzka.models.TensorFlowClassifier;
import com.example.pc.halzka.views.DrawModel;
import com.example.pc.halzka.views.DrawView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import me.panavtec.drawableview.DrawableView;
import me.panavtec.drawableview.DrawableViewConfig;

public class GameActivity extends Activity implements View.OnClickListener{
    private static final int PIXEL_WIDTH = 28;

    private DrawableView drawableView[];
    private DrawableViewConfig config = new DrawableViewConfig();
    private Classifier mClassifier;
    private Button btnUndo;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btnUndo = findViewById(R.id.undoButton);
        btnUndo.setOnClickListener(this);
        initUi();
        loadModel();
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
                                    "opt_mnist_convnet-tf.pb", "labels.txt", PIXEL_WIDTH,
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

    private void initUi() {
//    drawableView = (DrawableView) findViewById(R.id.paintView);
        drawableView = new DrawableView[9];
        drawableView[0] = (DrawableView) findViewById(R.id.paintView1);
        drawableView[1] = (DrawableView) findViewById(R.id.paintView2);
        drawableView[2] = (DrawableView) findViewById(R.id.paintView3);
        drawableView[3] = (DrawableView) findViewById(R.id.paintView4);
        drawableView[4] = (DrawableView) findViewById(R.id.paintView5);
        drawableView[5] = (DrawableView) findViewById(R.id.paintView6);
        drawableView[6] = (DrawableView) findViewById(R.id.paintView7);
        drawableView[7] = (DrawableView) findViewById(R.id.paintView8);
        drawableView[8] = (DrawableView) findViewById(R.id.paintView9);


        Button strokeWidthMinusButton = (Button) findViewById(R.id.strokeWidthMinusButton);
        Button strokeWidthPlusButton = (Button) findViewById(R.id.strokeWidthPlusButton);
        Button changeColorButton = (Button) findViewById(R.id.changeColorButton);
        Button undoButton = (Button) findViewById(R.id.undoButton);

        config.setStrokeColor(getResources().getColor(android.R.color.black));
        config.setShowCanvasBounds(true);
        config.setStrokeWidth(20.0f);
        config.setMinZoom(1.0f);
        config.setMaxZoom(3.0f);
        config.setCanvasHeight(512);
        config.setCanvasWidth(512);
//    drawableView.setConfig(config);

        for(int i = 0; i < drawableView.length; i++){
            drawableView[i].setConfig(config);
        }

        strokeWidthPlusButton.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View v) {
                config.setStrokeWidth(config.getStrokeWidth() + 10);
            }
        });
        strokeWidthMinusButton.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View v) {
                config.setStrokeWidth(config.getStrokeWidth() - 10);
            }
        });
        changeColorButton.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View v) {
                Random random = new Random();
                config.setStrokeColor(
                        Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            }
        });

//    undoButton.setOnClickListener(new View.OnClickListener() {
//
//      @Override public void onClick(View v) {
//        drawableView.undo();
//      }
//    });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.undoButton:
                recognizeBitmap();
                break;
        }
    }

    public float[] getPixelData(Bitmap mOffscreenBitmap) {
        if (mOffscreenBitmap == null) {
            return null;
        }

        int width = mOffscreenBitmap.getWidth();
        int height = mOffscreenBitmap.getHeight();

        // Get 28x28 pixel data from bitmap
        int[] pixels = new int[width * height];
        mOffscreenBitmap.getPixels(pixels, 0, width, 0, 0, width, height);

        float[] retPixels = new float[pixels.length];
        for (int i = 0; i < pixels.length; ++i) {
            // Set 0 for white and 255 for black pixel
            int pix = pixels[i];
            int b = pix & 0xff;
            retPixels[i] = (float)((0xff - b)/255.0);
        }
        return retPixels;
    }

    public void recognizeBitmap(){
        String text = "";
        float pixels[] = getPixelData(drawableView[0].obtainBitmap());
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
    }

}
