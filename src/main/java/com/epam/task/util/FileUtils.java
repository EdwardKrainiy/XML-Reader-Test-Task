package com.epam.task.util;

import com.epam.task.domain.GeneralProperty;
import com.epam.task.domain.SoftwareProperty;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;

public class FileUtils {
    public static File getFileByPath(String filePath){
        return new File(filePath);
    }

    public static SoftwareProperty getSoftwarePropertyByNode(Node node){
        SoftwareProperty softwareProperty = new SoftwareProperty();

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            softwareProperty.setName(getElementByTag("name", element));
            softwareProperty.setSoftwareType(getElementByTag("softwareType", element));
            softwareProperty.setVersion(getElementByTag("version", element));
            softwareProperty.setSize(Double.parseDouble(getElementByTag("size", element)));
            softwareProperty.setCost(Double.parseDouble(getElementByTag("cost", element)));
        }
        return softwareProperty;
    }

    private static String getElementByTag(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

    public static Node createGeneralPropertyNode(Document doc, GeneralProperty generalProperty) {
        Element generalPropertyElement = doc.createElement("GeneralProperty");

        generalPropertyElement.appendChild(createElement(doc, "totalCost", Double.toString(generalProperty.getTotalCost())));

        generalPropertyElement.appendChild(createElement(doc, "totalSize", Double.toString(generalProperty.getTotalSize())));
        return generalPropertyElement;
    }

    private static Node createElement(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
}
