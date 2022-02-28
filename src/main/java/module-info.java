module com.mycompany.csc311hw1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.google.gson;
    
    opens com.mycompany.csc311hw1 to javafx.fxml, com.google.gson;
    exports com.mycompany.csc311hw1;
}
