module org.example.otp2laksyviikko3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.otp2laksyviikko3 to javafx.fxml;
    exports org.example.otp2laksyviikko3;
}