package com.epam.task.data;

import com.epam.task.domain.GeneralProperty;
import com.epam.task.domain.SoftwareProperty;
import com.epam.task.data.util.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.task.data.util.FileUtils.createGeneralPropertyNode;
import static com.epam.task.data.util.FileUtils.getSoftwarePropertyByNode;

public class SoftwareRepository implements XmlRepository {
    private static String fileToReadPath;
    private static String fileToWritePath;

    public SoftwareRepository() {
    }

    public SoftwareRepository(String fileToReadPath) {
        this.fileToReadPath = fileToReadPath;
    }

    public static void setFileToWritePath(String fileToWritePath) {
        SoftwareRepository.fileToWritePath = fileToWritePath;
    }

    public static void setFileToReadPath(String fileToReadPath) {
        SoftwareRepository.fileToReadPath = fileToReadPath;
    }

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    static DocumentBuilder builder;

    {
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<SoftwareProperty> getAll() {
        try {
            Document document = builder.parse(FileUtils.getFileByPath(fileToReadPath));
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("SoftwareProperty");

            List<SoftwareProperty> softwareProperties = new ArrayList<>();

            for (int i = 0; i < nodeList.getLength(); i++) {
                softwareProperties.add(getSoftwarePropertyByNode(nodeList.item(i)));
            }
            return softwareProperties;

        } catch (SAXException | IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public Boolean rewrite(GeneralProperty property) {
        try {
            Document doc = builder.newDocument();

            Element rootElement = doc.createElement("GeneralProperties");

            doc.appendChild(rootElement);

            rootElement.appendChild(createGeneralPropertyNode(doc, property));

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            StreamResult file = new StreamResult(new File(fileToWritePath + ".xml"));

            transformer.transform(source, file);
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
