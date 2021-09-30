package com.epam.task.util;

import com.epam.task.domain.SoftwareProperty;
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
            softwareProperty.setName(getValueByTag("name", element));
            softwareProperty.setSoftwareType(getValueByTag("softwareType", element));
            softwareProperty.setVersion(getValueByTag("version", element));
            softwareProperty.setSize(Double.parseDouble(getValueByTag("size", element)));
            softwareProperty.setCost(Double.parseDouble(getValueByTag("cost", element)));
        }
        return softwareProperty;
    }

    private static String getValueByTag(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}
