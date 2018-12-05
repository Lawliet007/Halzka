package com.example.pc.halzka.views;

import java.util.ArrayList;
import java.util.List;

public class DrawModel {
    private Line mCurrentLine;
    private int mHeight;
    private List<Line> mLines = new ArrayList();
    private int mWidth;

    public static class Line {
        private List<LineElem> elems;

        private Line() {
            this.elems = new ArrayList();
        }

        private void addElem(LineElem lineElem) {
            this.elems.add(lineElem);
        }

        public int getElemSize() {
            return this.elems.size();
        }

        public LineElem getElem(int i) {
            return (LineElem) this.elems.get(i);
        }
    }

    public static class LineElem {
        /* renamed from: x */
        public float f11x;
        /* renamed from: y */
        public float f12y;

        private LineElem(float f, float f2) {
            this.f11x = f;
            this.f12y = f2;
        }
    }

    public DrawModel(int i, int i2) {
        this.mWidth = i;
        this.mHeight = i2;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public void startLine(float f, float f2) {
        this.mCurrentLine = new Line();
        this.mCurrentLine.addElem(new LineElem(f, f2));
        this.mLines.add(this.mCurrentLine);
    }

    public void endLine() {
        this.mCurrentLine = null;
    }

    public void addLineElem(float f, float f2) {
        if (this.mCurrentLine != null) {
            this.mCurrentLine.addElem(new LineElem(f, f2));
        }
    }

    public int getLineSize() {
        return this.mLines.size();
    }

    public Line getLine(int i) {
        return (Line) this.mLines.get(i);
    }

    public void clear() {
        this.mLines.clear();
    }
}
