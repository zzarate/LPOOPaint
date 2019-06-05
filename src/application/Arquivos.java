package application;

import java.io.IOException;

interface Arquivos {
	
	void salvarTexto(String nomeImg) throws IOException;
	
	void abrirImagem();
	
	void salvarImagem();
	
	boolean verificaExiste ();

}
