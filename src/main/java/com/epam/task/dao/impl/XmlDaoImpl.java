package com.epam.task.dao.impl;

import com.epam.task.dao.XmlDao;
import com.epam.task.domain.GeneralProperty;
import com.epam.task.domain.SoftwareProperty;
import com.epam.task.util.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

import static com.epam.task.util.FileUtils.getSoftwarePropertyByNode;

public class XmlDaoImpl implements XmlDao {

    @Override
    public List<SoftwareProperty> getSoftwareProperties(String path) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(FileUtils.getFileByPath(path));
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("SoftwareProperty");

            List<SoftwareProperty> softwareProperties = new ArrayList<>();

            for (int i = 0; i < nodeList.getLength(); i++) {
                softwareProperties.add(getSoftwarePropertyByNode(nodeList.item(i)));
            }
            return softwareProperties;

        } catch (Exception exc) {
            exc.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean writeSoftwareProperties(GeneralProperty property) {
        return null;
    }
}
