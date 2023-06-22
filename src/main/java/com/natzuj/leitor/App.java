package com.natzuj.leitor;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class App {

		public static void main(String[] args) {
	        LeitorXML leitor = new LeitorXML();
	        leitor.verificarPalavras();
	    }


	    // Caminho da pasta que vai ser lida
	    private String path = "C:\\caminho\\para\\pasta";
	    // Tags que vão ser verificadas
	    private String[] tagsNecessarias = {"registroANS", "numeroProtocolo", "tipoTransacao", "padrao", "nomeContratado", "numeroGuiaOrigem"};

	    public void verificarPalavras() {
	        File folder = new File(path);
	        File[] files = folder.listFiles((dir, name) -> name.endsWith(".xml"));

	        for (File file : files) {
	            try {
	                ArquivoXML arquivoXML = new ArquivoXML(file);
	                List<String> tagsAusentes = arquivoXML.verificarTagsNecessarias(tagsNecessarias);
	                List<String> palavrasComCaracteresEspeciais = arquivoXML.getPalavrasComCaracteresEspeciais();

	                if (tagsAusentes.isEmpty()) {
	                    System.out.println("Arquivo: " + file.getName() + " - Sem Tags Faltantes");
	                } else {
	                    System.out.println("Arquivo: " + file.getName() + " - Tags faltantes: " + tagsAusentes);
	                }

	                if (!palavrasComCaracteresEspeciais.isEmpty()) {
	                    System.out.println("Arquivo: " + file.getName() + " || Caracteres especiais encontrados! " + palavrasComCaracteresEspeciais + "\n");
	                }
	            } catch (ParserConfigurationException | SAXException | IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	}

