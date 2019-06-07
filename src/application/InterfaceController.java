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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class InterfaceController implements Initializable {

	// Variaveis de seleção de função
	private boolean lapisSelect = false;
	private boolean retanguloSelect = false;
	private boolean circSelect = false;
	private boolean linhaSelect = false;
	private boolean textSelect = false;
	private boolean borrachaSelect = false;
	private boolean preencheSelect = false;
	
	// Variavel para desenho
	GraphicsContext gc;

	//Variaveis do FXML
	@FXML
	private ColorPicker escolheCor;

	@FXML
	private TextField tamanhoSelect;
	
	@FXML
	private TextField tamanhoTexto;

	@FXML
	private TextField tamanhoBorracha;

	@FXML
	private Canvas canvas;
	
	@FXML
	private TextFlow textHist;
	
	@FXML
	private Pane paneCanvas;
	

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		escolheCor.setValue(Color.BLACK);

		gc = canvas.getGraphicsContext2D();

		Line linha = new Line();
		Rectangle ret = new Rectangle();
		Circle circ = new Circle();
		TextArea texto = new TextArea();
		
		// Metodos para desenho

		// Quando o clique do mouse for pressionado
		canvas.setOnMousePressed(e -> {

			if (lapisSelect) {
				double tamanho = Double.parseDouble(tamanhoSelect.getText());
				double x = e.getX() - tamanho / 2;
				double y = e.getY() - tamanho / 2;
				gc.setFill(escolheCor.getValue());
				gc.setStroke(escolheCor.getValue());
				gc.fillRoundRect(x, y, tamanho, tamanho, tamanho, tamanho);
			} else {
				if (borrachaSelect) {
					double tamanho = Double.parseDouble(tamanhoBorracha.getText());
					double x = e.getX() - tamanho / 2;
					double y = e.getY() - tamanho / 2;
					gc.clearRect(x, y, tamanho, tamanho);
				} else {
					if (retanguloSelect) {
						gc.setStroke(escolheCor.getValue());
						gc.setFill(escolheCor.getValue());
						ret.setX(e.getX());
						ret.setY(e.getY());
					} else {
						if (linhaSelect) {
							linha.setStartX(e.getX());
							linha.setStartY(e.getY());
							gc.setLineWidth(Double.parseDouble(tamanhoSelect.getText()));
						} else {
							if (circSelect) {
								gc.setFill(escolheCor.getValue());
								gc.setStroke(escolheCor.getValue());
								circ.setCenterX(e.getX());
								circ.setCenterY(e.getY());
							} else {
								if (textSelect) {
									gc.setFont(Font.getDefault());
									gc.setFill(escolheCor.getValue());
									gc.setStroke(escolheCor.getValue());
									gc.fillText(texto.getText(), e.getX(), e.getY());
									gc.strokeText(texto.getText(), e.getX(), e.getY());
								} else {
									if (preencheSelect) {
										
									}
								}
							}
						}
					}
				}
			}
		});

		// Quando o clique do mouse for arrastado
		canvas.setOnMouseDragged(e -> {

			if (lapisSelect) {
				double tamanho = Double.parseDouble(tamanhoSelect.getText());
				double x = e.getX() - tamanho / 2;
				double y = e.getY() - tamanho / 2;
				gc.setFill(escolheCor.getValue());
				gc.setStroke(escolheCor.getValue());
				gc.fillRoundRect(x, y, tamanho, tamanho, tamanho, tamanho);
			} else {
				if (borrachaSelect) {
					double tamanho = Double.parseDouble(tamanhoBorracha.getText());
					double x = e.getX() - tamanho / 2;
					double y = e.getY() - tamanho / 2;
					gc.clearRect(x, y, tamanho, tamanho);
				}
			}

		});

		// Quando o clique do mouse for solto
		canvas.setOnMouseReleased(e -> {

			if (lapisSelect) {
				double tamanho = Double.parseDouble(tamanhoSelect.getText());
				double x = e.getX() - tamanho / 2;
				double y = e.getY() - tamanho / 2;
				gc.setFill(escolheCor.getValue());
				gc.setStroke(escolheCor.getValue());
				gc.fillRoundRect(x, y, tamanho, tamanho, tamanho, tamanho);
			} else {
				if (borrachaSelect) {
					double tamanho = Double.parseDouble(tamanhoBorracha.getText());
					double x = e.getX() - tamanho / 2;
					double y = e.getY() - tamanho / 2;
					gc.clearRect(x, y, tamanho, tamanho);
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

						gc.strokeRect(ret.getX(), ret.getY(), ret.getWidth(), ret.getHeight());
						gc.fillRect(ret.getX(), ret.getY(), ret.getWidth(), ret.getHeight());
					} else {
						if (linhaSelect) {
							gc.setLineWidth(Double.parseDouble(tamanhoSelect.getText()));
							linha.setEndX(e.getX());
							linha.setEndY(e.getY());
							gc.setStroke(escolheCor.getValue());
							gc.moveTo(linha.getStartX(), linha.getStartY());
							gc.lineTo(linha.getEndX(), linha.getEndY());
							gc.stroke();

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
								gc.fillOval(circ.getCenterX(), circ.getCenterY(), circ.getRadius(),
										circ.getRadius());
								gc.strokeOval(circ.getCenterX(), circ.getCenterY(), circ.getRadius(),
										circ.getRadius());
							}
						}
					}
				}
			}
		});
	}

	// Metodos para selecao de funcao

	@FXML
	void lapisSelect(ActionEvent e) {
		lapisSelect = true;

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
		preencheSelect = false;
		retanguloSelect = false;
		circSelect = false;
		linhaSelect = false;
		borrachaSelect = false;
	}
	
	@FXML
	void preencheSelect(ActionEvent e) {
		preencheSelect = true;

		lapisSelect = false;
		retanguloSelect = false;
		circSelect = false;
		linhaSelect = false;
		textSelect = false;
		borrachaSelect = false;
	}

	// Metodo para salvar

	@FXML
	void salvar(ActionEvent e) {
		FileChooser escolheArq = new FileChooser();
		escolheArq.setTitle("Salvar Arquivo");

		//Filtrar extensao do arquivo
		FileChooser.ExtensionFilter filtrarExt = new FileChooser.ExtensionFilter ("Arquivo JPG (*.jpg)", ("*.jpg"));
		escolheArq.getExtensionFilters().add(filtrarExt);

		//Exibir janela para salvar
		File imagem = escolheArq.showSaveDialog(new Stage());
		
		//Salva a imagem
		if (imagem != null) {
			try {
				HistoricoArquivos nomeImg = new HistoricoArquivos ();
				nomeImg.salvarTexto(imagem.toString());
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
				HistoricoArquivos nomeImg = new HistoricoArquivos ();
				nomeImg.salvarTexto(imagem.toString());
				InputStream is = new FileInputStream (imagem);
				Image img = new Image(is);
				gc.drawImage(img, 0, 0);
			} catch (IOException ex) {
				Logger.getLogger(InterfaceController.class.getName()).log(Level.SEVERE, null, ex);
				System.out.println("Erro: " + ex);
			}
		}

	}
	
	//Exibe o historico de arquivos recentemente abertos no menu
	@FXML
	void recentes (ActionEvent e) {
		HistoricoArquivos exibe = new HistoricoArquivos ();
		Text texto = new Text (exibe.leArquivo());
		texto.setFont(new Font(10));
		texto.setFill(Color.BLACK);
		textHist.setTextAlignment(TextAlignment.LEFT);
		textHist.setLineSpacing(3);
		textHist.getChildren().clear();
		textHist.getChildren().add(texto);		
	}
	
	@FXML
	void desfazerSelect() {

	}
	
	@FXML
	void refazerSelect() {

	}
	

	// Metodo para sair da aplicação
	public void exit() {
		Platform.exit();
	}

}
