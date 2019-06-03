package application;

import application.Formas;

abstract class Ferramentas implements Formas {
	
	public void lapis (boolean lapisOpt) {
		/*
		canvas.setOnMousePressed(e -> {
				double tamanho = Double.parseDouble(tamanhoSelect.getText());
				double x = e.getX() - tamanho / 2;
				double y = e.getY() - tamanho / 2;
				dLapis.setFill(escolheCor.getValue());
				dLapis.setStroke(escolheCor.getValue());
				dLapis.fillRoundRect(x, y, tamanho, tamanho, tamanho, tamanho);
		});

		canvas.setOnMouseDragged(e -> {
				double tamanho = Double.parseDouble(tamanhoSelect.getText());
				double x = e.getX() - tamanho / 2;
				double y = e.getY() - tamanho / 2;
				dLapis.setFill(escolheCor.getValue());
				dLapis.setStroke(escolheCor.getValue());
				dLapis.fillRoundRect(x, y, tamanho, tamanho, tamanho, tamanho);
		});

		// fefesfs
		canvas.setOnMouseReleased(e -> {
				double tamanho = Double.parseDouble(tamanhoSelect.getText());
				double x = e.getX() - tamanho / 2;
				double y = e.getY() - tamanho / 2;
				dLapis.setFill(escolheCor.getValue());
				dLapis.setStroke(escolheCor.getValue());
				dLapis.fillRoundRect(x, y, tamanho, tamanho, tamanho, tamanho);
		});*/
	}
	
	public void linha (boolean linhaOpt) {
		
	}
	
	public void retangulo (boolean retOpt) {
		
	}
	
	public void circulo (boolean circOpt) {
		
	}
	
	public void borracha (boolean  borrachaOpt) {
		
	}
	
	public void text (boolean textOpt) {
		
	}
	
}
