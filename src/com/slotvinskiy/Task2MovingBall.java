package com.slotvinskiy;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//3*) нарисовть несколько шаров которые отбивается от краев экрана и друг от друга

public class Task2MovingBall extends Application {

    private static final int BOARD_WIDTH = 500;
    private static final int BOARD_HEIGHT = 500;
    private static final int FPS = 60;
    private static Ball ball = new Ball(150, 100, 50, BOARD_HEIGHT, BOARD_HEIGHT);
    private static Ball ball2 = new Ball(400, 100, 100, BOARD_HEIGHT, BOARD_HEIGHT);
    private static Ball ball3 = new Ball(100, 400, 150, BOARD_HEIGHT, BOARD_HEIGHT);
    private static Ball ball4 = new Ball(400, 350, 100, BOARD_HEIGHT, BOARD_HEIGHT);

    static {
        ball.addBall(ball2);
        ball.addBall(ball3);
        ball.addBall(ball4);
        ball2.addBall(ball3);
        ball2.addBall(ball4);
        ball3.addBall(ball4);
    }

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
                int pauseBetweenFramesMillis = 500 / FPS;
                Thread.sleep(pauseBetweenFramesMillis);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private void drawFrame() {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.setFill(Color.RED);
        initOval(ball);
        gc.setFill(Color.GREEN);
        initOval(ball2);
        gc.setFill(Color.BLUE);
        initOval(ball3);
        gc.setFill(Color.YELLOW);
        initOval(ball4);
        ball.move();
        ball2.move();
        ball3.move();
        ball4.move();
    }

    private void initOval(Ball ball) {
        gc.fillOval(ball.getX(), ball.getY(), ball.getDiameter(), ball.getDiameter());
        gc.strokeOval(ball.getX(), ball.getY(), ball.getDiameter(), ball.getDiameter());
    }
}
