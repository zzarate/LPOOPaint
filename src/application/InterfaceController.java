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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.event.ActionEvent;

public class InterfaceController implements Initializable {

	// Variaveis de sele��o de fun��o - lapis selecionado por padr�o
	boolean lapisSelect = false;
	boolean desfazerSelect = false;
	boolean refazerSelect = false;
	boolean preencheSelect = false;
	boolean retanguloSelect = false;
	boolean circSelect = false;
	boolean linhaSelect = false;
	boolean textSelect = false;
	boolean borrachaSelect = false;

	GraphicsContext dLapis;
	GraphicsContext dLinha;
	GraphicsContext apagar;
	GraphicsContext dRetangulo;
	GraphicsContext dCirculo;

	@FXML
	private ColorPicker escolheCor;

	@FXML
	private TextField tamanhoSelect;

	@FXML
	private TextField tamanhoBorracha;

	@FXML
	private Canvas canvas;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		dLapis = canvas.getGraphicsContext2D();
		apagar = canvas.getGraphicsContext2D();
		dRetangulo = canvas.getGraphicsContext2D();
		dCirculo = canvas.getGraphicsContext2D();
		dLinha = canvas.getGraphicsContext2D();

		Line linha = new Line();
		Rectangle ret = new Rectangle();
		Circle circ = new Circle();

		// efefes
		canvas.setOnMousePressed(e -> {

			if (lapisSelect) {
				double tamanho = Double.parseDouble(tamanhoSelect.getText());
				double x = e.getX() - tamanho / 2;
				double y = e.getY() - tamanho / 2;
				dLapis.setFill(escolheCor.getValue());
				dLapis.setStroke(escolheCor.getValue());
				dLapis.fillRoundRect(x, y, tamanho, tamanho, tamanho, tamanho);
			} else {
				if (borrachaSelect) {
					double tamanho = Double.parseDouble(tamanhoBorracha.getText());
					double x = e.getX() - tamanho / 2;
					double y = e.getY() - tamanho / 2;
					apagar.clearRect(x, y, tamanho, tamanho);
				} else {
					if (retanguloSelect) {
						dRetangulo.setStroke(escolheCor.getValue());
						dRetangulo.setFill(escolheCor.getValue());
						ret.setX(e.getX());
						ret.setY(e.getY());
					} else {
						if (linhaSelect) {
							linha.setStartX(e.getX());
							linha.setStartY(e.getY());
						} else {
							if (circSelect) {
								dCirculo.setFill(escolheCor.getValue());
								dCirculo.setStroke(escolheCor.getValue());
								circ.setCenterX(e.getX());
								circ.setCenterY(e.getY());
							} else {
								if (textSelect) {
								}
							}
						}
					}
				}
			}
		});

		// efefefw
		canvas.setOnMouseDragged(e -> {

			if (lapisSelect) {
				double tamanho = Double.parseDouble(tamanhoSelect.getText());
				double x = e.getX() - tamanho / 2;
				double y = e.getY() - tamanho / 2;
				dLapis.setFill(escolheCor.getValue());
				dLapis.setStroke(escolheCor.getValue());
				dLapis.fillRoundRect(x, y, tamanho, tamanho, tamanho, tamanho);
			} else {
				if (borrachaSelect) {
					double tamanho = Double.parseDouble(tamanhoBorracha.getText());
					double x = e.getX() - tamanho / 2;
					double y = e.getY() - tamanho / 2;
					apagar.clearRect(x, y, tamanho, tamanho);
				}
			}

		});

		// fefesfs
		canvas.setOnMouseReleased(e -> {

			if (lapisSelect) {
				double tamanho = Double.parseDouble(tamanhoSelect.getText());
				double x = e.getX() - tamanho / 2;
				double y = e.getY() - tamanho / 2;
				dLapis.setFill(escolheCor.getValue());
				dLapis.setStroke(escolheCor.getValue());
				dLapis.fillRoundRect(x, y, tamanho, tamanho, tamanho, tamanho);
			} else {
				if (borrachaSelect) {
					double tamanho = Double.parseDouble(tamanhoBorracha.getText());
					double x = e.getX() - tamanho / 2;
					double y = e.getY() - tamanho / 2;
					apagar.clearRect(x, y, tamanho, tamanho);
				} else {
					if (retanguloSelect) {
						ret.setWidth(Math.abs((e.getX() - ret.getX())));
						ret.setHeight(Math.abs((e.getSceneY() - ret.getY())));

						if (ret.getX() > e.getY()) {
							ret.setX(e.getX());
						}

						if (ret.getY() > e.getY()) {
							ret.setY(e.getY());
						}

						dRetangulo.strokeRect(ret.getX(), ret.getY(), ret.getWidth(), ret.getHeight());
						dRetangulo.fillRect(ret.getX(), ret.getY(), ret.getWidth(), ret.getHeight());
					} else {
						if (linhaSelect) {

							linha.setEndX(e.getX());
							linha.setEndY(e.getY());
							dLinha.setStroke(escolheCor.getValue());
							dLinha.moveTo(linha.getStartX(), linha.getStartY());
							dLinha.lineTo(linha.getEndX(), linha.getEndY());
							dLinha.stroke();

						} else {
							if (circSelect) {
								circ.setRadius((Math.abs(e.getX() - circ.getCenterX())
										+ Math.abs(e.getY() - circ.getCenterY())) / 2);
								if (circ.getCenterX() > e.getX()) {
									circ.setCenterX(e.getX());
								}
								if (circ.getCenterY() > e.getY()) {
									circ.setCenterY(e.getY());
								}

								dCirculo.fillOval(circ.getCenterX(), circ.getCenterY(), circ.getRadius(),
										circ.getRadius());
								dCirculo.strokeOval(circ.getCenterX(), circ.getCenterY(), circ.getRadius(),
										circ.getRadius());
							}
						}
					}
				}
			}
		});

	}

	// Metodos para desenhar

	@FXML
	public void lapisSelect(ActionEvent e) {
		lapisSelect = true;
		
		
		desfazerSelect = false;
		refazerSelect = false;
		preencheSelect = false;
		retanguloSelect = false;
		circSelect = false;
		linhaSelect = false;
		textSelect = false;
		borrachaSelect = false;
	}

	@FXML
	public void linhaSelect(ActionEvent e) {
		linhaSelect = true;
		
		lapisSelect = false;
		desfazerSelect = false;
		refazerSelect = false;
		preencheSelect = false;
		retanguloSelect = false;
		circSelect = false;
		textSelect = false;
		borrachaSelect = false;
	}

	@FXML
	public void borrachaSelect(ActionEvent e) {
		borrachaSelect = true;
		
		lapisSelect = false;
		desfazerSelect = false;
		refazerSelect = false;
		preencheSelect = false;
		retanguloSelect = false;
		circSelect = false;
		linhaSelect = false;
		textSelect = false;
	}

	@FXML
	public void retanguloSelect(ActionEvent e) {
		retanguloSelect = true;
		
		lapisSelect = false;
		desfazerSelect = false;
		refazerSelect = false;
		preencheSelect = false;
		circSelect = false;
		linhaSelect = false;
		textSelect = false;
		borrachaSelect = false;
	}

	@FXML
	public void circsSelect(ActionEvent e) {
		circSelect = true;
		
		lapisSelect = false;
		desfazerSelect = false;
		refazerSelect = false;
		preencheSelect = false;
		retanguloSelect = false;
		linhaSelect = false;
		textSelect = false;
		borrachaSelect = false;
	}

	@FXML
	public void textSelect(ActionEvent e) {
		textSelect = true;
		
		lapisSelect = false;
		desfazerSelect = false;
		refazerSelect = false;
		preencheSelect = false;
		retanguloSelect = false;
		circSelect = false;
		linhaSelect = false;
		borrachaSelect = false;
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

	// Metodo para sair da aplica��o
	public void exit() {
		Platform.exit();
	}

}
