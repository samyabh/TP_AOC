package TP_AOC.v1.IHM.java;

import java.net.URL;
import java.util.ResourceBundle;

import TP_AOC.v1.Controller.IController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class IHM  extends Application implements Initializable, BStartandStop, IIHM {

	/**
	 * Yo ma2thieu, petite liste de chose à faire :
	 *  - Passer l'état initial du bouton start à FAUX au démarrage
	 *  - Bien passer par setEtat quand tu changes l'état, ça appelle le controller
	 *  - Faudrait supprimer l'afficheur mesure, parce que sinon on devrra produire du code dnas al V2 après
	 *  - Faire en sorte que Materiel_afficheur, allume les bonnes leds enfonction des paramètes
	 *  - remplir emettreSonTempo emettreSonMesure (tu peux mettre des sons différents)
	 */
	private static IController controller;

	@FXML
	private AnchorPane layout; //fx:id="layout"
	@FXML
	private Button startandstop;//fx:id="startandstop"
	@FXML
	private Button buttonplus; //fx:id="buttonplus"
	@FXML
	private Button buttonmoins;//fx:id="buttonmoins"
	@FXML
	private Label affichagetempo;//fx:id="affichagetempo"
	@FXML
	private Label affichagetpparmesure;//fx:id="affichagetpparmesure"
	@FXML
	private Circle ledgauche;//fx:id="ledgauche"
	@FXML
	private Circle leddroite;//fx:id="leddroite"
	@FXML
	private Slider slider;//fx:id="slider"
	
	private static Boolean etat;
	/**
	 * Controller
	 */
	public IHM(){
		etat = false;
	}


	/***************************************    Fenêtre graphique   ***************************************/

	@Override
	public void demarrer(){
		launch();
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../assets/TP_AOC.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root,600,320);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
     * Initialisation des caractéristiques de la fenêtre
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		slider.setMin(30);
		slider.setMax(300);
		slider.setValue(60);
		slider.setBlockIncrement(10);
		double slidervalue = slider.getValue();
		String valueslider=String.valueOf(slidervalue);
		affichagetempo.setText("OFF");
		//Number oldValue = Double.parseDouble(text);
		initialisationStart();
		slider.valueProperty().addListener(new ChangeListener<Number>() {
		      @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
		        if (newValue == null) {
		          //affichagetempo.setText("150");
		          return;
		        }
		       // affichagetempo.setText(Math.round(newValue.intValue()) + "");
		      }
		    });
	}

	/***************************************     Afficheur Tempo   ***************************************/

	public void changerAffichageTempo(String value){
		//affichagetempo.setText(value);
	}



	/***************************************     BStartandStop   ***************************************/

	/**
	 * permet de gerer les évenement du bouton start and stop
	 * @param event
	 */
	@FXML
	@Override
	public void gestioneventstartandstop(ActionEvent event){
		ObservableList<String> cssclass;
		cssclass=startandstop.getStyleClass();
		if(cssclass.contains("startbutton")){BoutonEtatStop(cssclass);
		}else{BoutonEtatStart(cssclass);}
	}

	/**
	 * Initialise le bouton start et l'état à true
	 */
	@Override
	public void initialisationStart(){
		startandstop.getStyleClass().add(0,"startbutton");
		startandstop.setText("START");		
	}

	/**
	 * permet de définir le css de l'état start
	 * @param css
     */
	@Override
	public void BoutonEtatStart(ObservableList<String> css){
		css.remove(0);
		css.add(0, "startbutton");
		setEtat(true);
		startandstop.setText("START");
	}

	/**
	 * permet de définir le css de l'état stop
	 * @param css
     */
	@Override
	public void BoutonEtatStop(ObservableList<String> css){
		css.remove(0);
		css.add(0, "stopbutton");
		setEtat(false);
		startandstop.setText("STOP");
	}
	@Override
	public boolean getEtat(){
		return etat;
	}
	@Override
	public void setEtat(boolean etat){
		this.etat=etat;
		if(this.etat)
			controller.demarrer();
		else{
			controller.arreter();
		}


	}

	/***************************************    Mesure    ***************************************/

	/**
	 * Permet d'augmenter la mesure
	 * @param event
     */
	@FXML
	public void augmenterMesure(ActionEvent event){
		controller.augmenterMesure();
	}

	/**
	 * Permet de réduire la mesure
	 * @param event
     */
	@FXML
	public void reduireMesure(ActionEvent event){
		controller.decrementerMesure();
	}



	/***************************************   getter and setters   ***************************************/

	public IController getController() {
		return controller;
	}
	public void setController(IController controller) { this.controller = controller; }

}
