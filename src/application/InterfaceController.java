package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.event.ActionEvent;

public class InterfaceController implements Initializable {

	// Variaveis de seleção de função - lapis selecionado por padrão
	boolean lapisSelect = false;
	boolean desfazerSelect = false;
	boolean refazerSelect = false;
	boolean preencheSelect = false;
	boolean quadradoSelect = false;
	boolean circSelect = false;
	boolean linhaSelect = false;
	boolean textSelect = false;
	boolean borrachaSelect = false;

	GraphicsContext dLapis;
	GraphicsContext dLinha;
	GraphicsContext apagar;
	GraphicsContext dQuadrado;
	GraphicsContext dCirculo;

	@FXML
	private ColorPicker escolheCor;

	@FXML
	private TextField tamanhoSelect;

	@FXML
	private Canvas canvas;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		dLapis = canvas.getGraphicsContext2D();
		apagar = canvas.getGraphicsContext2D();
		dQuadrado = canvas.getGraphicsContext2D();
		dCirculo = canvas.getGraphicsContext2D();
		dLinha = canvas.getGraphicsContext2D();
		
		Line linha = new Line();
		Rectangle ret = new Rectangle();
		Circle circ = new Circle();
		
		
		//efefes
		canvas.setOnMousePressed(e -> {

			if (lapisSelect) {
				double tamanho = Double.parseDouble(tamanhoSelect.getText());
				double x = e.getX() - tamanho/2;
				double y = e.getY() - tamanho/2;
				dLapis.setFill(escolheCor.getValue());
				dLapis.setStroke(escolheCor.getValue());
				dLapis.fillRoundRect(x, y, tamanho, tamanho, tamanho, tamanho);
			} else {
				if (borrachaSelect) {
					apagar.setFill(Color.WHITE);
					apagar.setStroke(Color.WHITE);
					apagar.setLineWidth(Double.parseDouble(tamanhoSelect.getText()));
					apagar.beginPath();
					apagar.lineTo(e.getX(), e.getY());
				} else {
					if (quadradoSelect) {
						dQuadrado.setStroke(escolheCor.getValue());
						ret.setX(e.getX());
						ret.setY(e.getY());
					} else {
						if (linhaSelect) {
							dLinha.setFill(escolheCor.getValue());
							linha.setStartX(e.getX());
							linha.setStartY(e.getY());
						} else {
							if (circSelect) {
								dCirculo.setFill(escolheCor.getValue());
								circ.setCenterX(e.getX());
								circ.setCenterY(e.getY());
							}
						}
					}
				}
			}
		});
		
		//efefefw
		canvas.setOnMouseDragged(e -> {

			if (lapisSelect) {
				double tamanho = Double.parseDouble(tamanhoSelect.getText());
				double x = e.getX() - tamanho/2;
				double y = e.getY() - tamanho/2;
				dLapis.setFill(escolheCor.getValue());
				dLapis.setStroke(escolheCor.getValue());
				dLapis.fillRoundRect(x, y, tamanho, tamanho, tamanho, tamanho);
			} else {
				if (borrachaSelect) {
					apagar.lineTo(e.getX(), e.getY());
					apagar.stroke();
				}
			}

		});
		
		//fefesfs
		canvas.setOnMouseReleased(e -> {
			/*
			double tamanho = Double.parseDouble(tamanhoSelect.getText());
			double x = e.getX() - tamanho / 2;
			double y = e.getY() - tamanho / 2;
			*/

			if (lapisSelect) {
				double tamanho = Double.parseDouble(tamanhoSelect.getText());
				double x = e.getX() - tamanho/2;
				double y = e.getY() - tamanho/2;
				dLapis.setFill(escolheCor.getValue());
				dLapis.setStroke(escolheCor.getValue());
				dLapis.fillRoundRect(x, y, tamanho, tamanho, tamanho, tamanho);
			} else {
				if (borrachaSelect) {
					apagar.lineTo(e.getX(), e.getY());
					apagar.stroke();
					apagar.closePath();
				}
			}
		});
		
	}
	
	//Metodos para desenhar
	
	@FXML
	public void lapisSelect(ActionEvent e) {
		lapisSelect = true;
	}

	@FXML
	public void linhaSelect(ActionEvent e) {
		linhaSelect = true;
	}
	
	@FXML
	public void borrachaSelect(ActionEvent e) {
		borrachaSelect = true;
	}
	
	@FXML
	public void quadradoSelect(ActionEvent e) {
		quadradoSelect = true;
	}
	
	@FXML
	public void circsSelect(ActionEvent e) {
		circSelect = true;
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

	public void textSelect() {

	}
	

	// Metodo para sair da aplicação
	public void exit() {
		Platform.exit();
	}

}
