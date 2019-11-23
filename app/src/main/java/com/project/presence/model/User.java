package com.project.presence.model;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User implements Serializable {
   private String _id;
   private String email;
   private String name;
   private String userType;
   private Long registration;
   private List<Presence> presences;
   private List<Schedule> schedules;
}
