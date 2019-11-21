package com.project.presence.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Presence {
    private String themeId;
    private String day;
    private Boolean present;
}
