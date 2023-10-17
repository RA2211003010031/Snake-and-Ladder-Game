package com.example.snakeandladder;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DiceRoleSnake extends Application {

    public int rand;
    public Label randResult;

    public int cirPos[][] = new int[10][10];
    public int ladderPosition[][] = new int[6][3];

    public static final int Tile_Size = 80;
    public static final int width = 10;
    public static final int height = 10;

    public Circle player1;
    public Circle player2;

    public int playerPosition1 = 1;
    public int playerPosition2 = 1;

    public boolean player1Turn = true;
    public boolean player2Turn = true;

    public static int player1XPos = 40;
    public static int player1YPos = 760;

    public static int player2XPos = 40;
    public static int player2YPos = 760;

    public int posCir1 = 1;
    public int posCir2 = 1;

    public boolean gameStart = false;
    public Button gameButton;

    private Group tileGroup = new Group();

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(width * Tile_Size, (height * Tile_Size) + 80);
        root.getChildren().addAll(tileGroup);

        for(int i=0; i<height;i++) {
            for(int j = 0; j<width; j++) {
                Tile tile = new Tile(Tile_Size, Tile_Size);
                tile.setTranslateX(j * Tile_Size);
                tile.setTranslateY(i * Tile_Size);
                tileGroup.getChildren().add(tile);

//                cirPos[i][j] = j*(Tile_Size-40);
            }
        }

//        for (int i = 0; i<height; i++) {
//            for (int j = 0; j<width; j++) {
//                System.out.print(cirPos[i][j]+" ");
//            }
//            System.out.println();
//        }

        player1 = new Circle(40);
        player1.setId("player1");
        player1.setFill(Color.AQUA);
        player1.getStyleClass().add("sample/style.css");
        player1.setTranslateX(player1XPos);
        player1.setTranslateY(player1YPos);

        player2 = new Circle(40);
        player2.setId("player2");
        player2.setFill(Color.CHOCOLATE);
        player2.getStyleClass().add("sample/style.css");
        player2.setTranslateX(player2XPos);
        player2.setTranslateY(player2YPos);

        Button button2Player = new Button("Player 2");
        button2Player.setTranslateX(700);
        button2Player.setTranslateY(820);
        button2Player.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameStart && player2Turn) { // Check player2Turn
                    getDiceValue();
                    randResult.setText(String.valueOf(rand));
                    move2Player();
                    translatePlayer(player2XPos, player2YPos, player2);
                    player2Turn = false;
                    player1Turn = true;
                    playerPosition2+=rand;

                    if (player1XPos == 280 && player1YPos == 760) {
                        translatePlayer(360,600,player1);
                    }
                    else if (player1XPos == 600 && player1YPos == 680) {
                        translatePlayer(440,440,player1);
                    }

                }
            }
        });



        Button button1Player = new Button("Player 1");
        button1Player.setTranslateX(80);
        button1Player.setTranslateY(820);
        button1Player.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                if (gameStart && player1Turn) { // Check player1Turn
                    getDiceValue();
                    randResult.setText(String.valueOf(rand));
                    move1Player();
                    translatePlayer(player1XPos, player1YPos, player1);
                    player1Turn = false;
                    player2Turn = true;
//                    playerPosition1+=rand;

                    if (player2XPos == 280 && player2YPos == 760) {
                        translatePlayer(360,600,player2);
                    }
                    else if (player2XPos == 600 && player2YPos == 680) {
                        translatePlayer(440,440,player2);
                    }
                }
            }
        });



        gameButton = new Button("Start Game");
        gameButton.setTranslateX(380);
        gameButton.setTranslateY(820);
        gameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                gameButton.setText("Game Started");
                player1XPos = 40;
                player1YPos = 760;

                player2XPos = 40;
                player2YPos = 760;

                player1.setTranslateX(player1XPos);
                player1.setTranslateY(player1YPos);
                player2.setTranslateX(player2XPos);
                player2.setTranslateY(player2YPos);
                gameStart = true;
            }
        });

        randResult = new Label("O");
        randResult.setTranslateX(300);
        randResult.setTranslateY(820);

        Image img = new Image("C:\\Users\\adars\\IdeaProjects\\Snake and Ladder Game 1\\src\\main\\java\\com\\example\\snakeandladder\\snakeBG.jpg");
        ImageView bgImage = new ImageView();
        bgImage.setImage(img);
        bgImage.setFitHeight(800);
        bgImage.setFitWidth(800);

        tileGroup.getChildren().addAll(bgImage,player1,player2,button2Player,button1Player,gameButton,randResult);
        return root;
    }

    private void getDiceValue() {
        rand = (int) (Math.random()*6+1);
        System.out.println("Dice Value : "+rand);
    }

    private void move1Player() {
        for (int i = 0; i < rand; i++) {
            if (posCir1 % 2 == 1) {
                player1XPos += 80; // Move right
            } else {
                player1XPos -= 80; // Move left
            }

            if (player1XPos > 760) {
                player1YPos -= 80;
                player1XPos -= 80;
                posCir1++;
            }

            if (player1XPos < 40) {
                player1YPos -= 80;
                player1XPos += 80;
                posCir1++;
            }

            if (player1XPos < 30 || player1YPos < 30) {
                System.out.println("Player One Won!");
                player1XPos = 40;
                player1YPos = 40;
                randResult.setText("Player One Won");
                gameButton.setText("Start Again");
            }
        }
    }

    private void move2Player() {
        for (int i = 0; i < rand; i++) {
            if (posCir2 % 2 == 1) {
                player2XPos += 80; // Move right
            } else {
                player2XPos -= 80; // Move left
            }

            if (player2XPos > 760) {
                player2YPos -= 80;
                player2XPos -= 80;
                posCir2++;
            }

            if (player2XPos < 40) {
                player2YPos -= 80;
                player2XPos += 80;
                posCir2++;
            }

            if (player2XPos < 30 || player2YPos < 30) {
                System.out.println("Player Two Won!");
                player2XPos = 40;
                player2YPos = 40;
                randResult.setText("Player Two Won");
                gameButton.setText("Start Again");
            }
        }
    }


    private void translatePlayer(int x, int y, Circle b) {
        TranslateTransition animate = new TranslateTransition(Duration.millis(1000),b);
        animate.setToX(x);
        animate.setToY(y);
        animate.setAutoReverse(false);
        animate.play();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Snake and Ladder");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        System.out.println("GAME STARTED!");
        launch();
        System.out.println("GAME END!");
    }

}