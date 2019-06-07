package application;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

abstract class CriarVerificarArquivos implements Arquivos {
	String localArq;
	
	public abstract void salvarTexto(String nomeImg) throws IOException;
	
	//Verifica se o arquivo já existe
	public boolean verificaExiste () {
		localArq = System.getProperty("user.dir") + "\\Historico arquivos.txt";
		File tempAqr = new File (localArq);
		boolean existe = tempAqr.exists();
		
		return existe;
	}
	
	//Le o arquivo onde é armazenado o historico dos arquivos abertos e criados
	String leArquivo () {
		StringBuilder contentBuilder = new StringBuilder ();
		
		if (verificaExiste()) {
			try (Stream <String> stream = Files.lines( Paths.get(localArq), StandardCharsets.UTF_8)){
				stream.forEach(s -> contentBuilder.append(s).append ("\n"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return contentBuilder.toString();
		}
		return null;
	}
	
	
}
