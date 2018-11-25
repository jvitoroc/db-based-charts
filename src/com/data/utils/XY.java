package com.data.utils;

import java.util.ArrayList;
import java.util.Date;

public class XY<X, Y> {

    private ArrayList<X> x;
    private ArrayList<Y> y;

    public XY() {
        x = new ArrayList<X>();
        y = new ArrayList<Y>();
    }

    public ArrayList<X> getX() {
        return x;
    }

    public ArrayList<Y> getY() {
        return y;
    }

    public void addXY(X x, Y y) {
        this.x.add(x);
        this.y.add(y);
    }
}
