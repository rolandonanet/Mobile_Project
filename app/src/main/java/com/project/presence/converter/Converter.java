package com.project.presence.converter;

import com.project.presence.model.Schedule;
import com.project.presence.model.SchoolSubject;
import com.project.presence.model.StudentClass;
import com.project.presence.model.User;
import com.project.presence.model.adaptermodel.SchoolSubjectAdapterModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Converter {
    public List<SchoolSubjectAdapterModel> studentClassToSubjectAdapter(List<StudentClass> studentClasses, User user){


        List<SchoolSubjectAdapterModel> schoolSubjects = new ArrayList<>();
        for(StudentClass studentClass : studentClasses){
            for(SchoolSubject schoolSubject : studentClass.getSchoolSubjects()){
                for(Schedule schedule : user.getSchedules()){
                    if(schedule.getThemeId().equals(schoolSubject.getTheme().get_id())){
                        SimpleDateFormat asd = new SimpleDateFormat("EEEE", Locale.ENGLISH);
                        String currentDayOfWeek = asd.format(new Date());
                        String scheduleDay = schedule.getSchedule().substring(0, schedule.getSchedule().indexOf("_"));
                        if(currentDayOfWeek.toLowerCase().equals(scheduleDay.toLowerCase())) {
                            SchoolSubjectAdapterModel schoolSubjectAdapter = new SchoolSubjectAdapterModel();
                            schoolSubjectAdapter.setSchoolSubject   (schoolSubject.getName());
                            schoolSubjectAdapter.setClassroom       (studentClass.getName());
                            schoolSubjectAdapter.setSchoolTheme     (schoolSubject.getTheme().getName());
                            schoolSubjectAdapter.setStudentAmmount  (schoolSubject.getTheme().getUserIds().size());
                            schoolSubjectAdapter.setStudents        (schoolSubject.getTheme().getUserIds());
                            schoolSubjectAdapter.setSchedule        (schedule.getSchedule().substring(schedule.getSchedule().indexOf("_") + 1));
                            schoolSubjectAdapter.setSubjectId       (schoolSubject.get_id());
                            schoolSubjects.add(schoolSubjectAdapter);
                        }
                    }
                }
            }
        }

        return schoolSubjects;
    }

    public List<SchoolSubjectAdapterModel> studentClassToListSubjectAdapter(List<StudentClass> studentClasses, User user){


        List<SchoolSubjectAdapterModel> schoolSubjects = new ArrayList<>();
        for(StudentClass studentClass : studentClasses){
            for(SchoolSubject schoolSubject : studentClass.getSchoolSubjects()){
                for(Schedule schedule : user.getSchedules()){
                    if(schedule.getThemeId().equals(schoolSubject.getTheme().get_id())){
                        SchoolSubjectAdapterModel schoolSubjectAdapter =  new SchoolSubjectAdapterModel();
                        schoolSubjectAdapter.setSchoolSubject   (schoolSubject.getName());
                        schoolSubjectAdapter.setClassroom       (studentClass.getName());
                        schoolSubjectAdapter.setSchoolTheme     (schoolSubject.getTheme().getName());
                        schoolSubjectAdapter.setStudentAmmount  (schoolSubject.getTheme().getUserIds().size());
                        schoolSubjectAdapter.setStudents        (schoolSubject.getTheme().getUserIds());
                        schoolSubjectAdapter.setSchedule        (schedule.getSchedule());
                        schoolSubjectAdapter.setSubjectId       (schoolSubject.get_id());
                        schoolSubjects.add(schoolSubjectAdapter);
                    }
                }
            }
        }

        return schoolSubjects;
    }

}
