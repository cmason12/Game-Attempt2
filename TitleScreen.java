/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levelattempt2;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author cmason12
 */
public class TitleScreen {
    private ImageView playButton = new ImageView("https://upload.wikimedia.org/wikipedia/commons/thumb/a/a3/Next.svg/600px-Next.svg.png");
    private Text t = new Text();
    private StackPane root = new StackPane();
    private Scene scene;
    protected static  ImageView backImage = new ImageView("/Resources/giphy.gif");
    protected  static VBox backHolder = new VBox();
    
    TitleScreen(Stage primaryStage){
        playButton.setFitWidth(200);
        playButton.setFitHeight(200);
        playButton.setTranslateX(260);
        playButton.setTranslateY(-35);
        playButton.setOpacity(.7);
        playButton.setCursor(Cursor.HAND);
        
        InnerShadow is = new InnerShadow();
        is.setOffsetX(4.0f);
        is.setOffsetY(4.0f);

        
        t.setEffect(is);
        t.setX(20);
        t.setY(100);
        t.setText("Glitched");
        t.setFill(Color.ALICEBLUE);
        t.setFont(Font.font(null, FontWeight.BOLD, 120));
        t.setTranslateY(000);
        t.setTranslateX(-280);
        t.setRotate(270);
        t.setScaleY(-1);
        t.setScaleX(-1);
        t.setOpacity(.7);
        t.setMouseTransparent(true);
        
        
        PreDefMenuBar mBar = new PreDefMenuBar(primaryStage, false);
        WindowOptions winOp = new WindowOptions(primaryStage);
        backHolder.setAlignment(Pos.TOP_LEFT);
        backHolder.getChildren().addAll(backImage,mBar.retMB(), 
                winOp.retExit(), winOp.retMin());
        
        backHolder.setTranslateY(35);
        root.getChildren().addAll(backHolder, playButton, t, Player.getGlitch());
        scene = new Scene(root, 800, 600);
        
            
        backImage.setFitHeight(scene.getHeight());
        backImage.setFitWidth(scene.getWidth());
        
         primaryStage.setScene(scene);
         playButton.setOnMouseEntered((MouseEvent event) -> {
             playButton.setOpacity(.4);
        });
        playButton.setOnMouseExited((MouseEvent event) -> {
            playButton.setOpacity(.7);
        });
        playButton.setOnMouseClicked((MouseEvent event) -> {
            Level1 l1 = new Level1(primaryStage);
        });
    }
    
    
}
