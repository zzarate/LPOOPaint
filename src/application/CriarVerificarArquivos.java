package application;

import java.io.File;
import java.io.IOException;

abstract class CriarVerificarArquivos implements Arquivos {
	String localArq;
	
	public abstract void salvarTexto(String nomeImg) throws IOException;
	
	public boolean verificaExiste () {
		localArq = System.getProperty("user.dir") + "\\Historico arquivos.txt";
		File tempAqr = new File (localArq);
		boolean existe = tempAqr.exists();
		
		return existe;
	}
	
	
}
