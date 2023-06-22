package com.natzuj.leitor;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Main {

	    // Caminho da pasta que vai ser lida
	    private String path = "C:\\Users\\gabri\\Desktop\\teste";
	    // Tags que vão ser verificadas
	    private String[] palavrasNecessarias = {"registroANS", "numeroProtocolo", "tipoTransacao", "padrao", "nomeContratado", "numeroGuiaOrigem"};

	    public void verificarPalavras() {
	        File folder = new File(path);
	        File[] files = folder.listFiles((dir, name) -> name.endsWith(".xml"));

	        for (File file : files) {
	            try {
	                ArquivoXML arquivoXML = new ArquivoXML(file);
	                List<String> palavrasFaltantes = arquivoXML.verificarPalavrasNecessarias(palavrasNecessarias);
	                List<String> palavrasComCaracteresEspeciais = arquivoXML.getPalavrasComCaracteresEspeciais();

	                if (palavrasFaltantes.isEmpty()) {
	                    System.out.println("Arquivo: " + file.getName() + " - Sem Tags Faltantes");
	                } else {
	                    System.out.println("Arquivo: " + file.getName() + " - Tags faltantes: " + palavrasFaltantes);
	                }

	                if (!palavrasComCaracteresEspeciais.isEmpty()) {
	                    System.out.println("Arquivo: " + file.getName() + " || Caracteres especiais encontrados! " + palavrasComCaracteresEspeciais + "\n");
	                }
	            } catch (ParserConfigurationException | SAXException | IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    public static void main(String[] args) {
	        LeitorXML leitor = new LeitorXML();
	        leitor.verificarPalavras();
	    }
	}

