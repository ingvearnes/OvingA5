module no.ntnu.oving5.ovinga5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens no.ntnu.oving5.ovinga5 to javafx.fxml;
    exports no.ntnu.oving5.ovinga5;
}