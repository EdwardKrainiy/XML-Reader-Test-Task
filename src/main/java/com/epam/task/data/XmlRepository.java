package com.epam.task.data;

import com.epam.task.domain.GeneralProperty;
import com.epam.task.domain.SoftwareProperty;

import java.util.List;

public interface XmlRepository {

    List<SoftwareProperty> getAll() throws Exception;
    Boolean rewrite(GeneralProperty property);
}
