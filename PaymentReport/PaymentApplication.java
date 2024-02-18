package com.example.paymentreport_att2;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;
import java.io.IOException;


public class PaymentApplication extends Application{

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PaymentApplication.class.getResource("payment-ui.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 867, 604);
        stage.setTitle("Payment Report Maker");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        launch();
    }

}
