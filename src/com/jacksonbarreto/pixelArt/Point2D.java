package com.jacksonbarreto.pixelArt;

public class Point2D {
    private double coordinateX;
    private double coordinateY;

    public Point2D(double coordinateX, double coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public Point2D(Point2D point2D) {
        if (point2D == null)
            throw new IllegalArgumentException();
        this.coordinateX = point2D.getCoordinateX();
        this.coordinateY = point2D.getCoordinateY();
    }

    public void offset(double incrementX, double incrementY) {
        this.coordinateX += incrementX;
        this.coordinateY += incrementY;
    }

    public void offsetX(double incrementX) {
        this.offset(incrementX, 0.0);
    }

    public void offsetY(double incrementY) {
        this.offset(0.0, incrementY);
    }

    public void setCoordinate(double coordinateX, double coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public double getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(double coordinateY) {
        this.coordinateY = coordinateY;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || (obj.getClass() != this.getClass()))
            return false;
        Point2D point2D = (Point2D) obj;
        return ((this.coordinateX == point2D.getCoordinateX()) && (this.coordinateY == point2D.getCoordinateY()));
    }

    @Override
    public String toString() {
        return "Point: " +
                getCoordinateX() +
                ", " +
                getCoordinateY();
    }

    @Override
    public Point2D clone() {
        return new Point2D(this);
    }
}
