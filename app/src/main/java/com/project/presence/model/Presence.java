package com.project.presence.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Presence {
    private String themeId;
    private Date day;
    private Boolean present;
}
