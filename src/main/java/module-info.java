module top.udcr.timer {
    requires javafx.controls;
    requires javafx.fxml;


    opens top.udcr.timer to javafx.fxml;
    exports top.udcr.timer;
}