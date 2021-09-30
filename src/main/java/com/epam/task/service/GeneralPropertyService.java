package com.epam.task.service;

import com.epam.task.domain.GeneralProperty;
import com.epam.task.domain.SoftwareProperty;

import java.util.List;

public class GeneralPropertyService {
    public static GeneralProperty obtainGeneralProperty(List<SoftwareProperty> properties){
        double totalCost = 0;
        double totalSize = 0;

        for(SoftwareProperty property: properties){
            totalCost += property.getCost();
            totalSize += property.getSize();
        }

        return new GeneralProperty(totalCost, totalSize);
    }
}
