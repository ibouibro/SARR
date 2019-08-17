package main;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class inscriptionController {

    @FXML
    private AnchorPane ap1;

    @FXML
    private Label l2;

    @FXML
    private ComboBox<?> classe;

    @FXML
    private AnchorPane nonpaye;

    @FXML
    private Label l1;

    @FXML
    private Label regle;

    @FXML
    private AnchorPane ap2;

    @FXML
    private Label l3;

    @FXML
    private TextField chercher;

    @FXML
    private ListView<?> lv;

    @FXML
    void nonPaye(MouseEvent event) {

    }

    @FXML
    void textChanged(InputMethodEvent event) {

    }
    
    public inscriptionController()
    {
    	
    }

}

