package com.epam.task.service;

import com.epam.task.data.SoftwareRepository;
import com.epam.task.domain.GeneralProperty;
import com.epam.task.domain.SoftwareProperty;

import java.util.List;

public class GeneralPropertyService {
    public static SoftwareRepository softwareRepository = new SoftwareRepository(ServiceDefaults.filesPath + ServiceDefaults.fileToRead);

    public static GeneralProperty obtainGeneralProperty(List<SoftwareProperty> properties){
        double totalCost = 0;
        double totalSize = 0;

        for(SoftwareProperty property: properties){
            totalCost += property.getCost();
            totalSize += property.getSize();
        }

        return new GeneralProperty(totalCost, totalSize);
    }

    public static Boolean rewriteGeneralPropertyToFile(GeneralProperty generalProperty, String fileName){
        try{
            SoftwareRepository.setFileToWritePath(ServiceDefaults.filesPath + fileName);
            softwareRepository.rewrite(generalProperty);
            return true;
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }
}
