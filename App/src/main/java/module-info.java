module com.ooopppp.tubes_oop_2 {
    requires transitive javafx.base;
    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    requires transitive javafx.fxml;
    requires transitive javafx.media;


    requires org.controlsfx.controls;
    requires java.smartcardio;
    requires java.desktop;

    opens com.ooopppp.tubes_oop_2;
    opens com.ooopppp.tubes_oop_2.Entity;
    exports com.ooopppp.tubes_oop_2.Entity;
    exports com.ooopppp.tubes_oop_2;
}