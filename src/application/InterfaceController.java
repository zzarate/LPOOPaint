package application;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class InterfaceController implements Initializable {

	// Variaveis de seleção de função - lapis selecionado por padrão
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
	GraphicsContext imgOpen;

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
	void lapisSelect(ActionEvent e) {
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
	void linhaSelect(ActionEvent e) {
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
	void borrachaSelect(ActionEvent e) {
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
	void retanguloSelect(ActionEvent e) {
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
	void circsSelect(ActionEvent e) {
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
	void textSelect(ActionEvent e) {
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

	@FXML
	void salvar(ActionEvent e) {
		FileChooser escolheArq = new FileChooser();
		escolheArq.setTitle("Salvar Arquivo");

		//Filtrar extensao do arquivo
		FileChooser.ExtensionFilter filtrarExt = new FileChooser.ExtensionFilter ("Arquivo PNG (*.jpg)", ("*.jpg"));
		escolheArq.getExtensionFilters().add(filtrarExt);

		//Exibir janela para salvar
		File imagem = escolheArq.showSaveDialog(new Stage());

		if (imagem != null) {
			try {
				WritableImage escreverImag = new WritableImage (1280, 636);
				canvas.snapshot(null, escreverImag);
				RenderedImage renderImage = SwingFXUtils.fromFXImage(escreverImag, null);
				ImageIO.write(renderImage, "png", imagem);
			} catch (IOException ex) {
				Logger.getLogger(InterfaceController.class.getName()).log(Level.SEVERE, null, ex);
				System.out.println("Falha ao salvar: " + ex);
			}
		}

	}

	@FXML
	void abrirArq (ActionEvent e) {
		imgOpen = canvas.getGraphicsContext2D();
		FileChooser escolherArq = new FileChooser();
		escolherArq.setTitle("Abrir arquivo");
		
		//Filtra tipo de arquivo
		FileChooser.ExtensionFilter filtrarExt = new FileChooser.ExtensionFilter ("Arquivo JPG (*.jpg)", ("*.jpg"));
		escolherArq.getExtensionFilters().add(filtrarExt);
		
		// Exibir tela de selecao
		File imagem = escolherArq.showOpenDialog(new Stage());
		
		//Abrir imagem
		if (imagem != null) {
			try {
				InputStream is = new FileInputStream (imagem);
				Image img = new Image(is);
				imgOpen.drawImage(img, 0, 0);
			} catch (IOException ex) {
				Logger.getLogger(InterfaceController.class.getName()).log(Level.SEVERE, null, ex);
				System.out.println("Erro: " + ex);
			}
		}

	}

	public void desfazerSelect() {

	}

	public void refazerSelect() {

	}

	public void preencheSelect() {

	}

	// Metodo para sair da aplicação
	public void exit() {
		Platform.exit();
	}

}
