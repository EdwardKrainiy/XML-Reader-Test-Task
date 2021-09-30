package com.epam.task.dao;

import com.epam.task.domain.GeneralProperty;
import com.epam.task.domain.SoftwareProperty;

import java.util.List;

public interface XmlDao {
    List<SoftwareProperty> getSoftwareProperties(String path);
    Boolean writeSoftwareProperties(GeneralProperty property);
}
