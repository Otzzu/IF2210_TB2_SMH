module com.ooopppp.tubes_oop_2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires com.almasb.fxgl.all;
    requires java.smartcardio;
    requires javafx.media;
    requires java.desktop;

    opens com.ooopppp.tubes_oop_2;
    opens com.ooopppp.tubes_oop_2.Entity;
    exports com.ooopppp.tubes_oop_2.Entity;
    exports com.ooopppp.tubes_oop_2;
}