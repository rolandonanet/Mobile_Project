package com.project.presence.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Schedule implements Serializable {

    private String themeId;
    private String schedule;

}
