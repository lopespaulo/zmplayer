package br.imd.zmplayer.controller;

import br.imd.zmplayer.*;



import br.imd.zmplayer.controller.utils.OperationalController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.javafx.tk.FontLoader;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLPlayerController implements Initializable {

	@FXML
	public MenuItem closeButton;
	public MenuBar menuBar;
	public MenuItem menuUsuario;
	public MenuItem menuOpenFile;
	public Button btnPlay;
	public Button btnStop;
	public Button btnPause;
	public Text playerTime;
	public Text txtBtnText;
	private PlayerController pc;
	private Font fontAwesome;
	public Label lbUserSession;
	

	@FXML
	private void menuUsuarioAction(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("../view/FXMLUserSettingsScene.fxml"));
		stage.setScene(new Scene(root));
		stage.setTitle("Configurações de Usuário");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(menuBar.getScene().getWindow());
		stage.setResizable(false);
		stage.show();
	}

	@FXML
	private void menuOpenFileAction(ActionEvent event) throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Abrir mp3");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Arquivo mp3", "*.mp4"));
		File selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile != null) {
			try {
				pc.tocar(selectedFile);

				// updateDisplay();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	private void btnPlayAction(ActionEvent event) throws IOException {
		/*File selectedFile = new File("C:\\Users\\Paulo Lopes\\Downloads\\Z-Maguinho do Piauí - Deus.mp3");
		pc.tocar(selectedFile);
		*/
		
		Boolean pause = pc.MediaControl();
		
		if(pause){
			btnPause.setVisible(true);
			btnPlay.setVisible(false);
		}else{
			btnPause.setVisible(false);
			btnPlay.setVisible(true);
		}
		
		
	}
	@FXML
	private void btnStopAction(ActionEvent event) throws IOException {
		pc.parar();
		btnPause.setVisible(false);
		btnPlay.setVisible(true);
		
	}
	
	@FXML
	private void menuLogoutAction(ActionEvent event) throws IOException {

		
		Stage stage = (Stage) btnPlay.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("../view/FXMLLoginScene.fxml"));		Scene scene = new Scene(root);
		stage.setScene(scene);
		OperationalController.iniciarSessao(null);
		stage.show();
	}


	@FXML
	private void closeButtonAction(ActionEvent event) throws IOException {
		OperationalController.closeProgram();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		fontAwesome = Font.loadFont(getClass().getResource("../view/styles/fontawesomewebfont.ttf").toExternalForm(), 12);
		pc = PlayerController.getInstance();
		btnPause.setVisible(false);
		lbUserSession.setText(OperationalController.getSessao().getLt() + " - "+ OperationalController.getSessao().getUser().getNome());
		
		
		
	}

	
}
