package com.slotvinskiy;

import java.util.ArrayList;
import java.util.List;

public class Ball {
    private final int BOARD_WIDTH;
    private final int BOARD_HEIGHT;
    private int x;
    private int y;
    private int diameter;
    private int xCenter;
    private int yCenter;
    private Direction hDirection = Direction.RIGHT;
    private Direction vDirection = Direction.DOWN;
    List<Ball> balls = new ArrayList<>();

    public Ball(int x, int y, int diameter, int width, int height) {
        BOARD_WIDTH = width;
        BOARD_HEIGHT = height;
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        xCenter = x + (diameter / 2);
        yCenter = y + (diameter / 2);
    }

    public void moveRight() {
        hDirection = Direction.RIGHT;
    }

    public void moveLeft() {
        hDirection = Direction.LEFT;
    }

    public void moveDown() {
        vDirection = Direction.DOWN;
    }

    public void moveUp() {
        vDirection = Direction.UP;
    }

    public void move() {
        if (hDirection == Direction.RIGHT) {
            x += 1;
            xCenter += 1;
        } else if (hDirection == Direction.LEFT) {
            x -= 1;
            xCenter -= 1;
        }
        if (vDirection == Direction.DOWN) {
            y += 1;
            yCenter += 1;
        } else if (vDirection == Direction.UP) {
            y -= 1;
            yCenter -= 1;
        }
        changeDirectionIfBallsNear();
        bounceOfWall();
    }

    private void changeDirectionIfBallsNear() {

        for (Ball ball : balls) {
            double averageDiameter = (diameter + ball.getDiameter()) / 2.0;
            int xDelta = xCenter - ball.getXCenter();
            int yDelta = yCenter - ball.getYCenter();
            if (Math.abs(xDelta) <= averageDiameter && Math.abs(yDelta) <= averageDiameter) {
                double hypotenuse = Math.hypot(Math.abs(xDelta), Math.abs(yDelta));
                if (hypotenuse <= averageDiameter) {
                    if (Math.abs(xDelta) > Math.abs(yDelta) && xDelta < 0) {
                        moveLeft();
                    } else if (Math.abs(xDelta) > Math.abs(yDelta) && xDelta > 0) {
                        moveRight();
                    } else if (Math.abs(xDelta) < Math.abs(yDelta) && yDelta < 0) {
                        moveUp();
                    } else if (Math.abs(xDelta) < Math.abs(yDelta) && yDelta > 0) {
                        moveDown();
                    }
                }
            }
        }
    }

    private void bounceOfWall() {
        if (x >= BOARD_WIDTH - diameter) {
            moveLeft();
        } else if (x <= 0) {
            moveRight();
        } else if (y >= BOARD_HEIGHT - diameter) {
            moveUp();
        } else if (y <= 0) {
            moveDown();
        }
    }

    public void addBall(Ball ball) {
        if (balls.contains(ball)) {
            return;
        } else {
            balls.add(ball);
            ball.addBall(this);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getXCenter() {
        return xCenter;
    }

    public int getYCenter() {
        return yCenter;
    }

    public int getDiameter() {
        return diameter;
    }
}
