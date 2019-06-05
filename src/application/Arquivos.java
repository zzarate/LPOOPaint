package application;

import java.io.IOException;

interface Arquivos {
	
	void salvarTexto(String nomeImg) throws IOException;
	
	boolean verificaExiste ();

}
