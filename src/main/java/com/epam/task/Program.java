package com.epam.task;

import com.epam.task.domain.SoftwareProperty;
import com.epam.task.service.GeneralPropertyService;
import com.epam.task.service.ServiceDefaults;
import com.epam.task.service.SoftwarePropertyService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Program {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter name of xml file you want to obtain:");
        String fileToReadName = bf.readLine();

        List<SoftwareProperty> softwareProperties = null;

        try{
             softwareProperties = SoftwarePropertyService.getAllSoftwareProperties(ServiceDefaults.filesPath + fileToReadName + ".xml");
        }
        catch (IOException exception){
            System.out.println(exception.getMessage());
        }

        for(SoftwareProperty property: softwareProperties){
            System.out.println(property.toString());
            System.out.println(" ");
        }

        System.out.println("Enter name of xml file you want to create:");

        String fileToWriteName = bf.readLine();

        try{
            GeneralPropertyService.rewriteGeneralPropertyToFile(GeneralPropertyService.obtainGeneralProperty(softwareProperties), fileToWriteName);
            System.out.println("Succeed!");
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}
