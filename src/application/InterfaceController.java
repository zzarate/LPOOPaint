package application;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class InterfaceController implements Initializable {

	// Variaveis de seleção de função - lapis selecionado por padrão
	boolean lapisSelect = true;
	boolean desfazerSelect = false;
	boolean refazerSelect = false;
	boolean preencheSelect = false;
	boolean quadradoSelect = false;
	boolean circsSelect = false;
	boolean linhaSelect = false;
	boolean textSelect = false;
	boolean borrachaSelect = false;

	@FXML
	private ColorPicker escolheCor;

	@FXML
	private TextField tamanhoSelect;

	@FXML
	private Canvas canvas;

	GraphicsContext desenhoLapis;
	GraphicsContext desenharLinha;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		desenhoLapis = canvas.getGraphicsContext2D();

		canvas.setOnMouseDragged(e -> {
			double tamanho = Double.parseDouble(tamanhoSelect.getText());
			double x = e.getX() - tamanho / 2;
			double y = e.getY() - tamanho / 2;

			if (lapisSelect && !tamanhoSelect.getText().isEmpty()) {
				desenhoLapis.setFill(escolheCor.getValue());
				desenhoLapis.fillRoundRect(x, y, tamanho, tamanho, tamanho, tamanho);
			}
		});
	}

	

	@FXML
	public void lapisSelect(ActionEvent e) {
		lapisSelect = true;
	}
	
	@FXML
	public void linhaSelect (ActionEvent e) {
		linhaSelect = true;
	}

	// Metodo para salvar
	public void salvar() {

	}

	public void desfazerSelect() {

	}

	public void refazerSelect() {

	}

	public void preencheSelect() {

	}

	public void quadradoSelect() {

	}

	public void circsSelect() {

	}

	public void linhaSelect() {

	}

	public void textSelect() {

	}

	public void borrachaSelect() {

	}
	/*
	public void borrachaSelect() {

	}
	
	public void borrachaSelect() {

	}
	
	public void borrachaSelect() {

	}
	*/

	// Metodo para sair da aplicação
	public void exit() {
		Platform.exit();
	}

}
