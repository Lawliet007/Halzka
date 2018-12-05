package com.example.pc.halzka.views;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewCompat;

public class DrawRenderer {
    public static void renderModel(Canvas canvas, DrawModel drawModel, Paint paint, int i) {
        Paint paint2 = paint;
        paint2.setAntiAlias(true);
        int lineSize = drawModel.getLineSize();
        for (int i2 = i; i2 < lineSize; i2++) {
            DrawModel.Line line = drawModel.getLine(i2);
            paint2.setColor(ViewCompat.MEASURED_STATE_MASK);
            int elemSize = line.getElemSize();
            if (elemSize >= 1) {
                DrawModel.LineElem elem = line.getElem(0);
                int i3 = 0;
                float f = elem.f11x;
                float f2 = elem.f12y;
                float f3 = f;
                while (i3 < elemSize) {
                    DrawModel.LineElem elem2 = line.getElem(i3);
                    float f4 = elem2.f11x;
                    float f5 = elem2.f12y;
                    canvas.drawLine(f3, f2, f4, f5, paint2);
                    i3++;
                    f3 = f4;
                    f2 = f5;
                }
            }
        }
    }
}
