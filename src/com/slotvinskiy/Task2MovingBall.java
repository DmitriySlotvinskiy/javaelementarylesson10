package com.slotvinskiy;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//2*) нарисовть шар который сам двигается и отбивается от краев экрана

public class Task2MovingBall extends Application {

    private static final int BOARD_WIDTH = 800;
    private static final int BOARD_HEIGHT = 500;
    private static final int FPS = 60;
    private static Ball ball = new Ball(100, 200, 50);

    private boolean closed;
    private GraphicsContext gc;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFxSample");
        Canvas canvas = new Canvas();
        canvas.setWidth(BOARD_WIDTH);
        canvas.setHeight(BOARD_HEIGHT);
        BorderPane group = new BorderPane(canvas);
        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.show();
        gc = canvas.getGraphicsContext2D();
        new Thread(this::runMainGameLoopInThread).start();
    }

    @Override
    public void stop() {
        closed = true;
    }


    private void runMainGameLoopInThread() {
        while (!closed) {
            // run in UI thread
            Platform.runLater(this::drawFrame);
            try {
                int pauseBetweenFramesMillis = 1000 / FPS;
                Thread.sleep(pauseBetweenFramesMillis);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private void drawFrame() {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        gc.setFill(Color.RED);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.fillOval(ball.getX(), ball.getY(), ball.getDiameter(), ball.getDiameter());
        gc.strokeOval(ball.getX(), ball.getY(), ball.getDiameter(), ball.getDiameter());

        if (ball.getX() >= BOARD_WIDTH - ball.getDiameter() || ball.getX() <= 0) {
            ball.switchHorizontalDirection();
        }
        if (ball.getY() >= BOARD_HEIGHT - ball.getDiameter() || ball.getY() <= 0) {
            ball.switchVerticalDirection();
        }
        ball.move();
    }
}
