package com.example.pc.halzka.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class DrawView extends View {
    private int mDrawnLineSize = 0;
    private Matrix mInvMatrix = new Matrix();
    private Matrix mMatrix = new Matrix();
    private DrawModel mModel;
    private Bitmap mOffscreenBitmap;
    private Canvas mOffscreenCanvas;
    private Paint mPaint = new Paint();
    private boolean mSetuped = false;
    private float[] mTmpPoints = new float[2];

    public DrawView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setModel(DrawModel drawModel) {
        this.mModel = drawModel;
    }

    public DrawModel getmModel() {
        return this.mModel;
    }

    public void reset() {
        this.mDrawnLineSize = 0;
        if (this.mOffscreenBitmap != null) {
            this.mPaint.setColor(-1);
            this.mOffscreenCanvas.drawRect(new Rect(0, 0, this.mOffscreenBitmap.getWidth(), this.mOffscreenBitmap.getHeight()), this.mPaint);
        }
    }

    private void setup() {
        this.mSetuped = true;
        float width = (float) getWidth();
        float height = (float) getHeight();
        float width2 = (float) this.mModel.getWidth();
        float height2 = (float) this.mModel.getHeight();
        float f = width / width2;
        float f2 = height / height2;
        if (f > f2) {
            f = f2;
        }
        width = (width / 2.0f) - ((width2 * f) / 2.0f);
        height = (height / 2.0f) - ((height2 * f) / 2.0f);
        this.mMatrix.setScale(f, f);
        this.mMatrix.postTranslate(width, height);
        this.mMatrix.invert(this.mInvMatrix);
        this.mSetuped = true;
    }

    public void onDraw(Canvas canvas) {
        if (this.mModel != null) {
            if (!this.mSetuped) {
                setup();
            }
            if (this.mOffscreenBitmap != null) {
                int i = this.mDrawnLineSize - 1;
                if (i < 0) {
                    i = 0;
                }
                DrawRenderer.renderModel(this.mOffscreenCanvas, this.mModel, this.mPaint, i);
                canvas.drawBitmap(this.mOffscreenBitmap, this.mMatrix, this.mPaint);
                this.mDrawnLineSize = this.mModel.getLineSize();
            }
        }
    }

    public void calcPos(float f, float f2, PointF pointF) {
        this.mTmpPoints[0] = f;
        this.mTmpPoints[1] = f2;
        this.mInvMatrix.mapPoints(this.mTmpPoints);
        pointF.x = this.mTmpPoints[0];
        pointF.y = this.mTmpPoints[1];
    }

    public void onResume() {
        createBitmap();
    }

    public void onPause() {
        releaseBitmap();
    }

    private void createBitmap() {
        if (this.mOffscreenBitmap != null) {
            this.mOffscreenBitmap.recycle();
        }
        this.mOffscreenBitmap = Bitmap.createBitmap(this.mModel.getWidth(), this.mModel.getHeight(), Config.ARGB_8888);
        this.mOffscreenCanvas = new Canvas(this.mOffscreenBitmap);
        reset();
    }

    private void releaseBitmap() {
        if (this.mOffscreenBitmap != null) {
            this.mOffscreenBitmap.recycle();
            this.mOffscreenBitmap = null;
            this.mOffscreenCanvas = null;
        }
        reset();
    }

    public float[] getPixelData() {
        if (this.mOffscreenBitmap == null) {
            return null;
        }
        int width = this.mOffscreenBitmap.getWidth();
        int height = this.mOffscreenBitmap.getHeight();
        int[] iArr = new int[(width * height)];
        this.mOffscreenBitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        float[] fArr = new float[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            fArr[i] = (float) (255 - (iArr[i] & 255));
        }
        return fArr;
    }
}
