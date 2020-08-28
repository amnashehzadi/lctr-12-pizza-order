package sample;

import com.sun.javafx.charts.ChartLayoutAnimator;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;



import java.util.concurrent.Flow;


public class Main extends Application {

    private TextField customerTf ,PhoneTf ,AddressTf;

    private RadioButton smallRb , largeRb , mediumRb , thickRb , thinRb;
    private CheckBox  pepperoniCb , sausageCb , linguicaCb ,
            olivesCb , mushroomsCb , tomatoesCb , anchoviesCb;



    @Override
    public void start(Stage primaryStage) throws Exception{
        Label textHeading = new Label("Order Your Pizza Now!");
        HBox paneTop = new HBox(20,textHeading);
        paneTop.setPadding(new Insets(20));
        Label  cName = new Label("Name : ");
        customerTf = new TextField();
        customerTf.setPrefColumnCount(20);
        customerTf.setPromptText("Enter the customer's name here");
        customerTf.setMaxWidth(Double.MAX_VALUE);
        Label cPhone = new Label("Phone Number : " + " ");
        cPhone.setPrefWidth(100);
        PhoneTf = new TextField();
        PhoneTf.setPrefColumnCount(20);
        PhoneTf.setPromptText("Enter the customer's phone number here");
        PhoneTf.setMaxWidth(Double.MAX_VALUE);

        Label cAddress = new Label("Address : ");
        cAddress.setPrefWidth(100);
        AddressTf = new TextField();
        AddressTf.setPrefColumnCount(20);
        AddressTf.setPromptText("Enter the customer's address here");

        VBox labelField=new VBox(13,cName,cPhone,cAddress);
        VBox textField=new VBox(5,customerTf,PhoneTf,AddressTf);
        HBox pane=new HBox(labelField,textField);
        Label lSize = new Label("Size ");
        smallRb = new RadioButton("Small ");
        mediumRb = new RadioButton("Medium");
        largeRb = new RadioButton("Large");
        mediumRb.setSelected(true);
        ToggleGroup groupSize = new ToggleGroup();
        smallRb.setToggleGroup(groupSize);
        mediumRb.setToggleGroup(groupSize);
        largeRb.setToggleGroup(groupSize);


        VBox paneSize = new VBox(lSize , smallRb , mediumRb , largeRb);
        paneSize.setSpacing(10);
        Label lCurst = new Label("Crust");
        thinRb = new RadioButton("Thin");
        thickRb = new RadioButton("Thick");
        thinRb.setSelected(true);
        ToggleGroup groupCrust = new ToggleGroup();
        thinRb.setToggleGroup(groupCrust);
        thickRb.setToggleGroup(groupCrust);

        VBox paneCrust = new VBox(lCurst , thinRb , thickRb);
        paneCrust.setSpacing(10);

        Label lTopping  = new Label("Toppings");
        pepperoniCb = new CheckBox("Pepperoni");
        sausageCb = new CheckBox("Sausage");
        linguicaCb = new CheckBox("Linguica");
        olivesCb = new CheckBox("Olives");
        mushroomsCb = new CheckBox("Mushrooms");
        tomatoesCb = new CheckBox("Tomatoes");
        anchoviesCb = new CheckBox("Anchovies");

        FlowPane paneToppings = new FlowPane(Orientation.VERTICAL ,
                pepperoniCb , sausageCb , linguicaCb ,
                olivesCb , mushroomsCb , tomatoesCb , anchoviesCb);
        paneToppings.setPadding(new Insets(10,0,10,0));
        paneToppings.setHgap(20);
        paneToppings.setVgap(10);
        paneToppings.setPrefWrapLength(100);

        VBox paneTopping = new VBox(lTopping , paneToppings);

        HBox paneOrder = new HBox(50 , paneSize , paneCrust ,
                paneTopping);

        VBox paneCenter = new VBox(20, pane, paneOrder);
        paneCenter.setPadding(new Insets(0,10, 0, 10));

        Button btnOK = new Button("OK");
        btnOK.setPrefWidth(80);
        btnOK.setOnAction(e -> btnOK_Click() );
        Button btnCancel = new Button("Cancel");
        btnCancel.setPrefWidth(80);
        btnCancel.setOnAction(e ->
                primaryStage.close()
        );
        Region spacer = new Region();
        HBox paneBottom = new HBox(10, spacer, btnOK, btnCancel);
        paneBottom.setHgrow(spacer, Priority.ALWAYS);
        paneBottom.setPadding(new Insets(20, 10, 20, 10));

        BorderPane paneMain = new BorderPane();
        paneMain.setTop(paneTop);
        paneMain.setCenter(paneCenter);
        paneMain.setBottom(paneBottom);
        Scene scene = new Scene(paneMain);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pizza Order");
        primaryStage.show();


    }




    private void btnOK_Click() {
        String msg = "Customer:\n\n";
        msg += "\t" + customerTf.getText() + "\n";
        msg += "\t" + AddressTf.getText() + "\n";
        msg += "\t" + PhoneTf.getText() + "\n\n";
        msg += "You have ordered a ";
        // Add the pizza size
        if (smallRb.isSelected())
            msg += "small ";
        if (mediumRb.isSelected())
            msg += "medium ";
        if (largeRb.isSelected())
            msg += "large ";
        // Add the crust style
        if (thinRb.isSelected())
            msg += "thin crust pizza with ";
        if (thickRb.isSelected())
            msg += "thick crust pizza with ";
        String toppings = "";
        toppings = buildToppings(pepperoniCb,toppings);
        toppings = buildToppings(sausageCb,toppings);
        toppings = buildToppings(linguicaCb,toppings);
        toppings = buildToppings(olivesCb,toppings);
        toppings = buildToppings(tomatoesCb,toppings);
        toppings = buildToppings(mushroomsCb,toppings);
        toppings = buildToppings(anchoviesCb,toppings);


        if (toppings.equals(""))
            msg += "no toppings.";
        else
            msg += "the following toppings:\n"
                    + toppings;

        // Display the message
        Alert alert = new Alert(Alert.AlertType.INFORMATION ,"Order Details ");
        alert.setContentText(msg);
        alert.show();
    }

    public String buildToppings(CheckBox chk, String msg)
    {
        if (chk.isSelected())
        {
            if (!msg.equals(""))
            {
                msg += ", ";
            }
            msg += chk.getText();
        }
        return msg;
    }

    public void btnCancel_Click()
    {

    }




    public static void main(String[] args) {
        launch(args);
    }
}
