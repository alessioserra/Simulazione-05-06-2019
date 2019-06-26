/**
 * Sample Skeleton for 'Crimes.fxml' Controller Class
 */

package it.polito.tdp.crimes;

import java.net.URL;
import java.time.Year;
import java.util.List;
import java.util.ResourceBundle;

import org.jgrapht.Graphs;

import com.zaxxer.hikari.util.SuspendResumeLock;

import it.polito.tdp.model.District;
import it.polito.tdp.model.DistrictDistance;
import it.polito.tdp.model.Model;
import it.polito.tdp.model.Simulatore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CrimesController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxAnno"
    private ComboBox<Year> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="boxMese"
    private ComboBox<Integer> boxMese; // Value injected by FXMLLoader

    @FXML // fx:id="boxGiorno"
    private ComboBox<Integer> boxGiorno; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaReteCittadina"
    private Button btnCreaReteCittadina; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtN"
    private TextField txtN; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaReteCittadina(ActionEvent event) {
    	
    	//Pulisco
    	txtResult.clear();
    	
    	try {
    	Year year = boxAnno.getValue();
    	
    	//Creo Grafo
    	model.creaGrafo(year);
    	
    	for (District d : model.getGrafo().vertexSet()) {
    		//Ottengo i vicini gia'ordinati
    		List<DistrictDistance> result = model.getVicini(d);
    		
    		txtResult.appendText("Lista distretti per distanza dal distretto: "+d+"\n");
    		
    		//Stampola lista dei distretti ordinati per distanza crescente dal distretto selezionato
    		for (DistrictDistance dd : result) txtResult.appendText(dd.toString()+"\n");
    		
    		txtResult.appendText("\n");
    	}
    	
    	}catch (Exception e) {
			txtResult.appendText("Selezionare un anno corretto!\n");
		}
    	
    }

    @FXML
    void doSimula(ActionEvent event) {
    	
    	Simulatore sim = new Simulatore();

    	//Prendo variabili
    	int N = Integer.parseInt(txtN.getText());
    	Year year = boxAnno.getValue();
    	int month = boxMese.getValue();
    	int day = boxGiorno.getValue();
    	
    	sim.init(N, year, month, day);
    	int result = sim.run();
    	
    	txtResult.appendText("Numero di eventi mal gestiti: "+result);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert boxGiorno != null : "fx:id=\"boxGiorno\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert btnCreaReteCittadina != null : "fx:id=\"btnCreaReteCittadina\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Crimes.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	boxAnno.getItems().addAll(model.getYears());
    	boxMese.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);
    	boxGiorno.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31);
    	
    }
}
