package com.slotvinskiy;

public class Ball {
    private int x;
    private int y;
    private int diameter;

    boolean horizontalDirection;
    boolean verticalDirection;

    public Ball(int x, int y, int diameter) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        horizontalDirection = true;
        verticalDirection = true;
    }

    public void switchHorizontalDirection() {
        if (horizontalDirection == true) {
            horizontalDirection = false;
        } else {
            horizontalDirection = true;
        }
    }

    public void switchVerticalDirection() {
        if (verticalDirection == true) {
            verticalDirection = false;
        } else {
            verticalDirection = true;
        }
    }

    public void move() {
        if (horizontalDirection == true) {
            x += 5;
        } else {
            x -= 5;
        }
        if (verticalDirection == true) {
            y += 5;
        } else {
            y -= 5;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDiameter() {
        return diameter;
    }
}
