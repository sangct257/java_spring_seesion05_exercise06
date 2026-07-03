package demo.service;

import demo.model.CourseStatus;
import demo.model.dto.response.CourseResponse;
import demo.model.dto.response.CourseResponseV2;
import demo.model.dto.response.PageResponse;
import org.springframework.data.domain.Sort;

public interface CourseService {
    PageResponse<CourseResponse> getPagedCoursesByStatus(int page, int size, String sortBy, Sort.Direction direction, CourseStatus status);
    PageResponse<CourseResponseV2> getPagedCoursesV2(int page, int size, String sortBy, Sort.Direction direction);
    PageResponse<CourseResponseV2> getPagedCourses(Integer page, Integer size, String sortBy, Sort.Direction direction, CourseStatus status, String keyword);
}
