package com.project.presence.converter;

import com.project.presence.model.Schedule;
import com.project.presence.model.SchoolSubject;
import com.project.presence.model.StudentClass;
import com.project.presence.model.User;
import com.project.presence.model.adaptermodel.SchoolSubjectAdapterModel;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    public List<SchoolSubjectAdapterModel> studentClassToSubjectAdapter(List<StudentClass> studentClasses, User user){

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
                        schoolSubjectAdapter.setSchedule        (schoolSubject.getTheme().getSchedules());
                        schoolSubjectAdapter.setSubjectId       (schoolSubject.get_id());
                        schoolSubjects.add(schoolSubjectAdapter);
                    }
                }
            }
        }
        return schoolSubjects;
    }
}
