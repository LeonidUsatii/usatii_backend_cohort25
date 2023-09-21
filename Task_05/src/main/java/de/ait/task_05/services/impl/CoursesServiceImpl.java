package de.ait.task_05.services.impl;


import de.ait.task_05.dto.CourseDto;
import de.ait.task_05.dto.NewCourseDto;
import de.ait.task_05.models.Course;
import de.ait.task_05.repositories.CoursesRepository;
import de.ait.task_05.services.CoursesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import static de.ait.task_05.dto.CourseDto.from;

@RequiredArgsConstructor
@Service
public class CoursesServiceImpl implements CoursesService {

    private final CoursesRepository coursesRepository;

    @Override
    public CourseDto addCourse(NewCourseDto newCourse) {

        Course course = Course.builder()
                .title(newCourse.getTitle())
                .description(newCourse.getDescription())
                .price(newCourse.getPrice())
                .beginDate(LocalDate.parse(newCourse.getBeginDate()))
                .endDate(LocalDate.parse(newCourse.getEndDate()))
                .state(Course.State.DRAFT)
                .build();

        coursesRepository.save(course);

        return from(course);
    }

    @Override
    public List<CourseDto> getCourses() {
        List<Course> courses = coursesRepository.findAll();
        return from(courses);
    }

}
