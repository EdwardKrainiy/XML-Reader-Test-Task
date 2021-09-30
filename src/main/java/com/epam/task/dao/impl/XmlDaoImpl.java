package com.epam.task.dao.impl;

import com.epam.task.dao.XmlDao;
import com.epam.task.domain.GeneralProperty;
import com.epam.task.domain.SoftwareProperty;
import com.epam.task.util.FileUtils;
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

import static com.epam.task.util.FileUtils.createGeneralPropertyNode;
import static com.epam.task.util.FileUtils.getSoftwarePropertyByNode;

public class XmlDaoImpl implements XmlDao {

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder;

    {
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<SoftwareProperty> getSoftwareProperties(String path) {
        try {
            Document document = builder.parse(FileUtils.getFileByPath(path));
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
    public Boolean writeSoftwareProperty(GeneralProperty property, String fileName) {
        try {
            Document doc = builder.newDocument();

            Element rootElement = doc.createElement("GeneralProperties");

            doc.appendChild(rootElement);

            rootElement.appendChild(createGeneralPropertyNode(doc, property));

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            StreamResult file = new StreamResult(new File("src/main/resources/" + fileName + ".xml"));

            transformer.transform(source, file);
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
