package com.epam.task;

import com.epam.task.dao.impl.XmlDaoImpl;
import com.epam.task.domain.SoftwareProperty;
import com.epam.task.service.GeneralPropertyService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class XMLReader {
    public static void main(String[] args) throws IOException {
        XmlDaoImpl daoObject = new XmlDaoImpl();
        List<SoftwareProperty> softwareProperties = daoObject.getSoftwareProperties("src/main/resources/SoftwareProperties.xml");

        for(SoftwareProperty property: softwareProperties){
            System.out.println(property.toString());
            System.out.println(" ");
        }

        System.out.println("Enter name of xml file you want to create:");

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bf.readLine();

        if(daoObject.writeSoftwareProperty(GeneralPropertyService.obtainGeneralProperty(softwareProperties), fileName)){
            System.out.println("Succeed!");
        }
    }
}
