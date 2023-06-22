# Leitor de Arquivos XML
 Projeto teste de um Leitor XML a partir de arquivos de uma pasta.

## Introdução
#### Este documento fornece uma explicação passo a passo sobre como usar o código do "Leitor de Arquivos XML". O código em Java permite ler arquivos XML, verificar a presença de palavras ou TAGS necessárias e identificar palavras com caracteres especiais.

<hr>

## Pré-requisitos
Antes de utilizar o código, certifique-se de ter o seguinte:

- Java Development Kit (JDK) instalado em seu sistema.

- Um editor de código Java, como o Eclipse ou o IntelliJ IDEA.

- Baixe o código do "Leitor de Arquivos XML" em um diretório de sua escolha.

- Certifique-se de ter o JDK instalado corretamente. Para verificar, abra um terminal ou prompt de comando e execute o seguinte comando:

```
javac -version
```
<hr>

## Utilizando o código
- A classe principal para utilizar o código é a classe App.

- Crie uma instância da classe ArquivoXML, fornecendo o caminho da pasta onde os aquivos XML que deseja ler se encontram. Por exemplo:

```JAVA
File file = new File("C:\\caminho\\para\\pasta");
ArquivoXML arquivo = new ArquivoXML(file);
```
- Para a verificação das TAGS presentes, Defina as necessárias que você deseja fazer a verificação no arquivo XML. Por exemplo:
```JAVA
String[] tagsNecessarias = { "registroANS", "numeroProtocolo", "tipoTransacao" };
```
- Chame o método verificarTagsNecessarias(tagsNecessarias) da instância arquivo para obter a lista de tags faltantes:
```java
List<String> tagsFaltantes = arquivo.verificarTagsNecessarias(tagsNecessarias);
```
- Chame o método getPalavrasComCaracteresEspeciais() da instância arquivo para obter a lista de palavras com caracteres especiais:
```JAVA
List<String> palavrasComCaracteresEspeciais = arquivo.getPalavrasComCaracteresEspeciais();
```
- Você pode manipular os resultados obtidos da maneira que desejar. Por exemplo, pode exibi-los na tela ou salvá-los em um arquivo.
```JAVA
System.out.println("Tags faltantes:");
for (String palavra : tagsFaltantes) {
    System.out.println("- " + palavra);
}

System.out.println("Palavras com caracteres especiais:");
for (String palavra : palavrasComCaracteresEspeciais) {
    System.out.println("- " + palavra);
}
```
<hr>

## Conclusão
#### Este documento forneceu um guia passo a passo sobre como utilizar o código do "Leitor de Arquivos XML". Ao seguir as etapas descritas, você poderá verificar a presença de palavras necessárias e identificar palavras com caracteres especiais em um arquivo XML.

<hr>

#### Este documento foi criado para estudo de caso.

