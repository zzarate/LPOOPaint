package application;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

public class HistoricoArquivos extends CriarVerificarArquivos {

	@Override
	public void salvarTexto(String nomeImg) throws IOException {
		String nomeArq = "Historico arquivos.txt";
		
		if (super.verificaExiste()) {
			try {
				Date dataAtual = new Date();
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy 'as' HH:mm:ss");
				String nomeAdd = nomeImg + "  |  " + formato.format(dataAtual) + ".";
				FileWriter fileWriter = new FileWriter (localArq, true);
				PrintWriter saidaTexto = new PrintWriter(fileWriter);
				saidaTexto.println(nomeAdd);
				saidaTexto.close();
				System.out.println("Nome do arquivo ADICIONADO com sucesso");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			try {
				PrintWriter saidaTexto = new PrintWriter(nomeArq);
				Date dataAtual = new Date();
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy 'as' HH:mm:ss");
				saidaTexto.println(nomeImg + "  |  " + formato.format(dataAtual) + ".");
				saidaTexto.close();
				System.out.println("Nome do arquivo ESCRITO com sucesso");
				System.out.println("Arquivo salvo na pasta do projeto: " + System.getProperty("user.dir"));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	String exibeMenu () {
		StringBuilder contentBuilder = new StringBuilder ();
		
		if (super.verificaExiste()) {
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
