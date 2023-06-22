package com.natzuj.leitor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ArquivoXML {
    private File file;

    public ArquivoXML(File file) {
        this.file = file;
    }

    public List<String> verificarPalavrasNecessarias(String[] palavrasNecessarias) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize();

        List<String> palavrasFaltantes = new ArrayList<>();

        for (String palavra : palavrasNecessarias) {
            boolean palavraEncontrada = false;

            NodeList nodeList = doc.getElementsByTagName("*");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    if (node.getNodeName().equalsIgnoreCase(palavra)) {
                        palavraEncontrada = true;
                        break;
                    }
                }
            }

            if (!palavraEncontrada) {
                palavrasFaltantes.add(palavra);
            }
        }

        return palavrasFaltantes;
    }

    public List<String> getPalavrasComCaracteresEspeciais() throws IOException {
        List<String> palavrasComCaracteresEspeciais = new ArrayList<>();
        StringBuilder conteudo = new StringBuilder();
        Path arquivo = file.toPath();
        List<String> linhas = Files.readAllLines(arquivo);

        for (String linha : linhas) {
            conteudo.append(linha.replaceAll("<.*?>", " "));
        }

        String isCaractereEspecial = conteudo.toString();
        String[] words = isCaractereEspecial.split("\\s+");

        for (String word : words) {
            if (!word.matches("[a-zA-Z]+") && !word.matches(".*\\d.*")) {
                palavrasComCaracteresEspeciais.add(word);
            }
        }

        return palavrasComCaracteresEspeciais;
    }
}
