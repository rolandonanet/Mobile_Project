package com.project.presence.model.adaptermodel;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolSubjectAdapterModel implements Serializable {
    private String schoolSubject;
    private String classroom;
    private Integer studentAmmount;
    private String schoolTheme;
    private List<String> schedule;
    private String subjectId;
}
