module com.example.temperature_converter {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.temperature_converter to javafx.fxml;
    exports com.example.temperature_converter;
}