package de.ait.task_05.services;

import de.ait.task_05.dto.CourseDto;
import de.ait.task_05.dto.NewCourseDto;

import java.util.List;

public interface CoursesService {
    CourseDto addCourse(NewCourseDto newCourse);

    List<CourseDto> getCourses();
}
