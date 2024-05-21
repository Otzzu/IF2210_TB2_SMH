module com.ooopppp.tubes_oop_2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires com.almasb.fxgl.all;
    requires java.smartcardio;
    requires javafx.media;

    opens com.ooopppp.tubes_oop_2 to javafx.fxml;
    exports com.ooopppp.tubes_oop_2;
}