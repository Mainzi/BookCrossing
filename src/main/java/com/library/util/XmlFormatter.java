package com.library.util;

import com.library.DOM.DOM;
import com.library.model.Book;
import javafx.collections.ObservableList;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Utility Class for formatting XML
 */
public class XmlFormatter {

    public XmlFormatter() {
    }
    /**
     * @param unformattedXml - XML String
     * @return Properly formatted XML String
     */
    public String format(String unformattedXml) {
        try {
            Document document = parseXmlFile(unformattedXml);

            OutputFormat format = new OutputFormat(document);
            format.setLineWidth(65);
            format.setIndenting(true);
            format.setIndent(2);
            format.setStandalone(true);
            Writer out = new StringWriter();
            XMLSerializer serializer = new XMLSerializer(out, format);
            serializer.serialize(document);

            return out.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

    }

    /**
     * This function converts String XML to Document object
     *
     * @param in - XML String
     * @return Document object
     */
    private Document parseXmlFile(String in) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(in));
            return db.parse(is);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Takes an XML Document object and makes an XML String. Just a utility
     * function.
     *
     * @param doc - The DOM document
     * @return the XML String
     */
    public String makeXMLString(Document doc) {
        String xmlString = "";
        if (doc != null) {
            try {
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer trans = transformerFactory.newTransformer();
                trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                trans.setOutputProperty(OutputKeys.INDENT, "yes");
                StringWriter sw = new StringWriter();
                StreamResult result = new StreamResult(sw);
                DOMSource source = new DOMSource(doc);
                trans.transform(source, result);
                xmlString = sw.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return xmlString;
    }

    public void saveBooksInXML(ObservableList<Book> books, String name) {
            StringBuilder xmlString = new StringBuilder("");
            xmlString.append("\n<Книги>");
            for (Book b :
                    books) {
                xmlString.append("<Книга>");
                xmlString.append("<Название>").append(b.getTitle()).append("</Название>");
                xmlString.append("<Авторы>");
                for (String a :
                        b.getAuthorsArrayList()) {
                    xmlString.append("<Автор>").append(a).append("</Автор>");
                }
                xmlString.append("</Авторы>");
                xmlString.append("<Категория>").append(b.getCategory()).append("</Категория>");
                xmlString.append("<Издательство>").append(b.getPublisher()).append("</Издательство>");

                xmlString.append("<Год>").append(b.getYearString()).append("</Год>");
                xmlString.append("<Библиотеки>");
                for (String a :
                        b.getLibrariesArrayList()) {
                    xmlString.append("<Библиотека>").append(a).append("</Библиотека>");
                }
                xmlString.append("</Библиотеки>");
                xmlString.append("<Описание>").append(b.getDescription()).append("</Описание>");
                xmlString.append("</Книга>");
            }
            xmlString.append("</Книги>");
            XmlFormatter formatter = new XmlFormatter();
            Document document = formatter.parseXmlFile(formatter.format(xmlString.toString()));
            DOM.writeDocument(document, name);
    }
}