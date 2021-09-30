package com.epam.task.service;

import com.epam.task.data.SoftwareRepository;
import com.epam.task.domain.SoftwareProperty;

import java.util.List;

public class SoftwarePropertyService {
    static SoftwareRepository softwareRepository = new SoftwareRepository();

    public static List<SoftwareProperty> getAllSoftwareProperties(String path){
        softwareRepository.setFileToReadPath(path);
        return softwareRepository.getAll();
    }
}
