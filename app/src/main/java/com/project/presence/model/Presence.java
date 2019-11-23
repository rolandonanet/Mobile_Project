package com.project.presence.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Presence implements Serializable {
    private String themeId;
    private String day;
    private Boolean present;
}
