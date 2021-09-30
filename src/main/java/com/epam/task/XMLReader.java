package com.epam.task;

import com.epam.task.dao.impl.XmlDaoImpl;
import com.epam.task.domain.SoftwareProperty;

import java.util.List;

public class XMLReader {
    public static void main(String[] args) {
        XmlDaoImpl daoObject = new XmlDaoImpl();
        List<SoftwareProperty> softwareProperties = daoObject.getSoftwareProperties("src/main/resources/SoftwareProperties.xml");

        for(SoftwareProperty property: softwareProperties){
            System.out.println(property.toString());
        }
    }
}
