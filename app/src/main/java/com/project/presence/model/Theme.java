package com.project.presence.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Theme {
    private String _id;
    private String teacherId;
    private List<String> schedules;
    private List<String> userIds;
}
