import java.io.IOException;
import java.util.LinkedHashMap;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GUI extends Application {
    
    Label labelExpl, labelUrlPopup, labelPagePopup, labelSearchPopup;
    TextField SearchTextField, SearchTextFieldPopup, UrlTextField, PagesTextField;
    TextArea resultText;
    Button btn, btnCrawler, btnCrawlerPopup, btnPopupClose;   
    
    // Primary & Secondary(popup):
    GridPane pane, paneCenter, paneCenterPopup;
    HBox hbox, hboxPopup;
    BorderPane border, borderPopup;
    Scene scene, scenePopup;
    Stage Stage, newStage;

    @Override
    public void start(Stage primaryStage) {
        //Objects
        SearchTextField = new TextField();
        
        SearchTextFieldPopup = new TextField();

        labelExpl = new Label("Input Search word: ");
        labelExpl.setTextFill(Color.web("#0076a3"));
        
        labelUrlPopup = new Label("Input URL: ");
        labelUrlPopup.setTextFill(Color.web("#0076a3"));
        
        labelPagePopup = new Label("Sub-pages to crawl: ");
        labelPagePopup.setTextFill(Color.web("#0076a3"));
        
        labelSearchPopup = new Label("Input Search word: ");
        labelSearchPopup.setTextFill(Color.web("#0076a3"));
                
        resultText = new TextArea();
        resultText.setText("Search Results: \n");
        resultText.setPrefWidth(450);
        
        UrlTextField = new TextField("http://www.");
        
        PagesTextField = new TextField();
        
        btn = new Button("Search");
        btn.setPrefWidth(170);
        btn.setOnAction(new SearchHandler<ActionEvent>(SearchTextField, resultText));
        
        btnCrawler = new Button("Crawler");
        btnCrawler.setPrefWidth(170);
        btnCrawler.setOnAction(e-> ButtonClicked(e));
            
        btnCrawlerPopup = new Button("Search & Crawl");
        btnCrawlerPopup.setPrefWidth(170);
        btnCrawlerPopup.setOnAction(e-> ButtonClicked(e));
        
        btnPopupClose = new Button("Close window");
        btnPopupClose.setPrefWidth(170);
        btnPopupClose.setOnAction(e-> ButtonClicked(e));
        
        //GridPane (grid - top)
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.BOTTOM_LEFT);
        pane.setHgap(5);
        pane.setVgap(5);
        pane.setPadding(new Insets(5, 0, 0, 45));
        pane.setBorder(Border.EMPTY);  
        //Add all elements to pane (into grid)
        //GridLayout(int rows, int columns, int horizontalGap, int verticalGap)
        pane.add(labelExpl,0,0,2,1);
        pane.add(SearchTextField,0,1,2,2);
    
        //GridPane (grid - center)
        GridPane paneCenter = new GridPane();
        paneCenter.setAlignment(Pos.BOTTOM_LEFT);
        paneCenter.setHgap(10);
        paneCenter.setVgap(10);
        paneCenter.setPadding(new Insets(0, 0, 0, 45));
        paneCenter.setBorder(Border.EMPTY);
        //Add all elements to pane (into grid)
        //GridLayout(int rows, int columns, int horizontalGap, int verticalGap)  
        paneCenter.add(resultText,0,1,4,4);
        
        GridPane paneCenterPopup = new GridPane();
        paneCenterPopup.setAlignment(Pos.BOTTOM_LEFT);
        paneCenterPopup.setHgap(10);
        paneCenterPopup.setVgap(10);
        paneCenterPopup.setPadding(new Insets(5, 10, 10, 15));
        paneCenterPopup.setBorder(Border.EMPTY);
        paneCenterPopup.add(labelSearchPopup, 0, 0);
        paneCenterPopup.add(SearchTextFieldPopup, 1, 0);
        paneCenterPopup.add(labelUrlPopup, 0, 1);
        paneCenterPopup.add(UrlTextField, 1, 1);
        paneCenterPopup.add(labelPagePopup, 0, 2);
        paneCenterPopup.add(PagesTextField, 1, 2);
        
        //HBox
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(5, 10, 10, 45));
        hbox.setSpacing(110);
        hbox.getChildren().addAll(btn, btnCrawler);
        
        HBox hboxPopup = new HBox();
        hboxPopup.setPadding(new Insets(5, 10, 10, 5));
        hboxPopup.setSpacing(5);
        hboxPopup.getChildren().addAll(btnCrawlerPopup, btnPopupClose);
    
        //BorderPane (border)
        BorderPane border = new BorderPane();
        border.setBottom(hbox); //add hbox to border from method
        border.setTop(pane); //add grid to border from method
        border.setCenter(paneCenter);
        
        BorderPane borderPopup = new BorderPane();
        borderPopup.setBottom(hboxPopup); //add hbox to border from method
        borderPopup.setCenter(paneCenterPopup); 
   
        //Scene: (contains border)
        Scene scene = new Scene(border, 550, 300);
        Scene scenePopup = new Scene(borderPopup, 350, 170);
   
        //Stage:
        primaryStage = new Stage();
        primaryStage.setTitle("Java Search Engine");
        primaryStage.setScene(scene); // Show Scene
        primaryStage.show();
        
        newStage = new Stage();
        newStage.setScene(scenePopup);
        //tell stage it is meannt to pop-up (Modal)
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setTitle("Crawler window");
       }
    
    private void ButtonClicked(ActionEvent e) {       
        if (e.getSource() == btnCrawler) {
            newStage.showAndWait(); //internal java function
        } 
        if (e.getSource() == btnCrawlerPopup) {           
            btnCrawlerPopup.setOnAction(new SearchHandler<ActionEvent>(SearchTextFieldPopup, resultText, UrlTextField, PagesTextField));
        }       
        if (e.getSource() == btnPopupClose) {
                newStage.close();  
        }
    }

    public static void main (String[] args) throws IOException {
        LinkedHashMap hashMap = Setup.initialise(args[0]);
        
        while(hashMap != null){
            launch(args);
        }
    }
}
