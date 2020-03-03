package com.slotvinskiy;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class Task1StaticalShapes extends Application {

    private static final int BOARD_WIDTH = 800;
    private static final int BOARD_HEIGHT = 500;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Simple figures ");
        Canvas canvas = new Canvas();
        canvas.setWidth(BOARD_WIDTH);
        canvas.setHeight(BOARD_HEIGHT);
        BorderPane group = new BorderPane(canvas);
        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.show();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        test(gc);
    }

    private void test(GraphicsContext gc) {
        gc.setFill(Color.YELLOW);
        gc.fillOval(500, 50, 120, 120);
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.GREEN);
        gc.setLineWidth(5);
        gc.strokeLine(0, 480, 800, 480);
        gc.fillOval(50, 210, 100, 180);
        gc.fillOval(200, 290, 150, 150);
        gc.fillOval(400, 330, 80, 80);
        gc.setStroke(Color.BROWN);
        gc.strokeLine(100, 480, 100, 390);
        gc.setFill(Color.BROWN);
        gc.fillRect(265, 440, 20, 40);
        gc.strokeLine(440, 480, 440, 410);
        gc.setStroke(Color.BURLYWOOD);
        gc.strokeRoundRect(600, 380, 120, 60, 10, 10);
        gc.strokeLine(630, 475, 620, 445);
        gc.strokeLine(730, 475, 720, 445);
        gc.strokeLine(600, 475, 610, 445);
        gc.strokeLine(700, 475, 710, 445);
        gc.strokeOval(560, 340, 50, 50);
        gc.strokeLine(720, 385, 730, 410);
        gc.strokeLine(570, 315, 580, 340);
        gc.strokeLine(600, 315, 590, 340);
        gc.strokeArc(670, 422, 35, 35, 180, 180, ArcType.CHORD);
        gc.strokeLine(675, 465, 675, 455);
        gc.strokeLine(682, 465, 682, 455);
        gc.strokeLine(690, 465, 690, 455);
        gc.strokeLine(695, 465, 695, 455);
    }
}
