import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI extends Application {

    TextField SearchTextField;
    Label labelResult;
    //HashMap hashMap = this.hashMap;
    
    @Override
    public void start(Stage primaryStage) {
    
    //Objects
    SearchTextField = new TextField();
        SearchTextField.setPrefWidth(210);         

    Label labelExpl = new Label(" Input Search word: ");
        labelExpl.setTextFill(Color.web("#0076a3"));

    Text resultText = new Text();
        resultText.setText(" Search Results: ");
        resultText.setFill(Color.GRAY);
        
    Button btn = new Button("Search"); 
        btn.setPrefWidth(170);
    
    //Add handle to btn (Search:)
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event){
                String userInput = SearchTextField.getText();
                
                System.out.println(userInput);

                try {
                    
                    //Test for input from user:
                    SearchCmd.searchConstruct(userInput /*, hashMap*/);
                
                } catch (IOException ex) {
                    
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                
                }
            }
        });
    
    //GridPane (grid)
    GridPane pane = new GridPane();
    pane.setAlignment(Pos.BOTTOM_LEFT);
    pane.setHgap(5);
    pane.setVgap(5);
    pane.setPadding(new Insets(5, 10, 10, 45));
    pane.setBorder(Border.EMPTY);
        
    //Add all elements to pane (into grid)
    //GridLayout(int rows, int columns, int horizontalGap, int verticalGap)
    pane.add(labelExpl,0,0,2,1);
    pane.add(SearchTextField,0,1,2,2);   
    pane.add(resultText,0,3,4,4);
    
    //HBox
    HBox hbox = new HBox();
    hbox.setPadding(new Insets(5, 10, 10, 45));
    hbox.setSpacing(10); 
    hbox.getChildren().addAll(btn);
    
    //BorderPane (border)
    BorderPane border = new BorderPane(); 
    border.setBottom(hbox); //add hbox to border from method
    border.setTop(pane); //add grid to border from method
   
    //Scene: (contains border)
    Scene scene = new Scene(border, 300, 400);
   
    //Stage:
    primaryStage.setTitle("JavaSearchEngine");
    primaryStage.setScene(scene); // Show Scene
    primaryStage.show();
    } 
    
    public static void main (String[] args) throws IOException {
    
        //HashMap hashMap = HtmlReader.readHtmlList();
    
        launch(args);
    
    }
    
    public static void printResults(StringSet results) {

        
    }
}


    
