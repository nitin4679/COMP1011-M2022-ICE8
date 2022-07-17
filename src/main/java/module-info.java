module ca.georgiancollege.comp1011m2022ice8 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.google.gson;


    opens ca.georgiancollege.comp1011m2022ice8 to javafx.fxml, com.google.gson;
    exports ca.georgiancollege.comp1011m2022ice8;
}