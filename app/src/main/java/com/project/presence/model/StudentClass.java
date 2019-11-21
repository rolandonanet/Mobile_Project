package com.project.presence.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentClass {
    private String _id;
    private String name;
    private List<SchoolSubject> schoolSubjects;
}
