package com.natzuj.leitor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LeitorXML {
	// Caminho da pasta que vai ser lida
	String path = "C:\\Users\\gabri\\Desktop\\teste";
	// Tags que vão ser verificadas
	String[] palavrasNecessarias = {"registroANS", "numeroProtocolo", "tipoTransacao", "padrao", "nomeContratado", "numeroGuiaOrigem"};

	//Método que faz a leitura dos arquivos
	public void verificarPalavras() {
	    File folder = new File(path);
	    File[] files = folder.listFiles((dir, name) -> name.endsWith(".xml"));

	    for (File file : files) {
	        try {
	            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	            Document doc = dBuilder.parse(file);
	            doc.getDocumentElement().normalize();

	            List<String> palavrasFaltantes = new ArrayList<>();
	            List<String> palavrasComCaracteresEspeciais = new ArrayList<>();

	            for (String palavra : palavrasNecessarias) {
	                boolean palavraEncontrada = false;

	                NodeList nodeList = doc.getElementsByTagName("*");
	                for (int i = 0; i < nodeList.getLength(); i++) {
	                    Node node = nodeList.item(i);
	                    if (node.getNodeType() == Node.ELEMENT_NODE) {
	                        if (node.getNodeName().toLowerCase().endsWith(":" + palavra.toLowerCase()) || node.getNodeName().equals(palavra.toLowerCase())) {
	                            palavraEncontrada = true;
	                            break;
	                        }
	                    }
	                }

	                if (!palavraEncontrada) {
	                    palavrasFaltantes.add(palavra);
	                }
	            }

	            // Verifica se há caracteres especiais
	            String isCaractereEspecial = getCaractereEspecial(file);
	            String[] words = isCaractereEspecial.split("\\s+"); // Separar palavras pelo espaço em branco

	            for (String word : words) {
	                if (containsSpecialCharacters(word) && !containsNumbers(word)) {
	                    palavrasComCaracteresEspeciais.add(word);
	                }
	            }

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

	public String getCaractereEspecial(File file) throws IOException {
	    StringBuilder conteudo = new StringBuilder();
	    java.nio.file.Path arquivo = file.toPath();
	    List<String> linhas = java.nio.file.Files.readAllLines(arquivo); // Faz leitura de todas as linhas do arquivo
	    for (String linha : linhas) {
	        conteudo.append(linha.replaceAll("<.*?>", " "));
	    }
	    return conteudo.toString();
	}

	private boolean containsSpecialCharacters(String word) {
	    return !word.matches("[a-zA-Z]+");
	}

	private boolean containsNumbers(String word) {
	    return word.matches(".*\\d.*");
	}

	public static void main(String[] args) {
		LeitorXML leitor = new LeitorXML();
		leitor.verificarPalavras();
	}
}
