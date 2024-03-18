package com.example.temperature_converter;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Temperature_Converter extends Application {

    @Override
    public void start(Stage primaryStage) {
        ObservableList<String> temperature = FXCollections.observableArrayList("Fahrenheit", "Celsius", "Kelvin");

        // Source Temperature
        Label fromLabel = new Label("From: ");
        ComboBox<String> fromTemperature = new ComboBox<>(temperature);
        fromTemperature.getSelectionModel().selectFirst(); // Select first Temperature by default

        // Target Temperature
        Label toLabel = new Label("To: ");
        ComboBox<String> toTemperature = new ComboBox<>(temperature);
        toTemperature.getSelectionModel().selectFirst(); // Select first Temperature by default

        // Amount to convert
        Label amountLabel = new Label("Amount:");
        TextField amountTextField = new TextField();

        // Button to trigger conversion
        Button convertButton = new Button("Convert");

        // Result display
        Label resultLabel = new Label("Result:");
        TextField resultTextField = new TextField();
        resultTextField.setEditable(false); // Make result display read-only

        // Set up the action for the convert button
        convertButton.setOnAction(e -> {
            String sourceTemperature = fromTemperature.getValue();
            String targetTemperature = toTemperature.getValue();
            double amount = Double.parseDouble(amountTextField.getText()); // Parse the user input
            double result = output(amount, sourceTemperature, targetTemperature); // Perform conversion
            String temperatureSymbol = getTemperatureSymbol(targetTemperature); // new line
            resultTextField.setText(String.format("%.2f", result) + temperatureSymbol); // Display the result
        });

        VBox vbox = new VBox(10, fromLabel, fromTemperature, toLabel,
                toTemperature, amountLabel, amountTextField, convertButton,
                resultLabel, resultTextField);

        vbox.setPadding(new Insets(20));

        Scene scene = new Scene(vbox, 300, 400);
        primaryStage.setTitle("Temperature Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private double output(Double amount, String temperatureInput, String temperatureOutput) {
        // Conversion logic
        double newValue;
        if (temperatureInput.equals(temperatureOutput)) {
            return amount;
        } else if (temperatureInput.equals("Fahrenheit") && temperatureOutput.equals("Celsius")) {
            newValue = (amount-32) * 5/9;
        } else if (temperatureInput.equals("Fahrenheit") && temperatureOutput.equals("Kelvin")) {
            newValue = (amount-32) * 5/9 + 273.15;
        } else if (temperatureInput.equals("Celsius") && temperatureOutput.equals("Fahrenheit")) {
            newValue = ((double) 9/5 * amount) + 32;
        } else if (temperatureInput.equals("Celsius") && temperatureOutput.equals("Kelvin")) {
            newValue = amount + 273.15;
        } else if (temperatureInput.equals("Kelvin") && temperatureOutput.equals("Fahrenheit")) {
            newValue = (amount - 273.15) * 9/5 + 32;
        } else if (temperatureInput.equals("Kelvin") && temperatureOutput.equals("Celsius")) {
            newValue = amount - 273.15;
        } else {
            // If the conversion case is not handled, return the original amount
            newValue = amount;
        }
        return newValue;
    }

    private String getTemperatureSymbol(String tempCode) {
        switch (tempCode)  {
            case "Fahrenheit":
                return "°F";
            case "Celsius":
                return "°C";
            case "Kelvin":
                return"K";
            default:
                return"";


        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
