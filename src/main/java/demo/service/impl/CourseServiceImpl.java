package demo.service.impl;

import demo.model.Course;
import demo.model.CourseStatus;
import demo.model.dto.response.CourseInstructorResponse;
import demo.model.dto.response.CourseResponse;
import demo.model.dto.response.CourseResponseV2;
import demo.model.dto.response.PageResponse;
import demo.repositoty.CourseRepository;
import demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public PageResponse<CourseResponse> getPagedCoursesByStatus(int page, int size, String sortBy, Sort.Direction direction, CourseStatus status) {
        if (page < 0){
            page = 0;
        }

        if (sortBy == null || sortBy.isBlank()){
            sortBy = "id";
        }

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(direction, sortBy)
        );

        Page<Course> courses = courseRepository.findAllByStatus(status,pageable);

        Page<CourseResponse> responsePage = courses
                .map(course -> CourseResponse.builder()
                        .id(course.getId())
                        .title(course.getTitle())
                        .status(course.getStatus())
                        .instructor(
                                CourseInstructorResponse.builder()
                                        .id(course.getInstructor().getId())
                                        .name(course.getInstructor().getName())
                                        .build()
                        )
                        .build());

        return PageResponse.<CourseResponse>builder()
                .items(responsePage.getContent())
                .page(responsePage.getNumber())
                .size(responsePage.getSize())
                .totalItems((int) responsePage.getTotalElements())
                .totalPages(responsePage.getTotalPages())
                .isLast(responsePage.isLast())
                .build();
    }

    @Override
    public PageResponse<CourseResponseV2> getPagedCoursesV2(int page, int size, String sortBy, Sort.Direction direction) {
        if (page < 0){
            page = 0;
        }

        if (sortBy == null || sortBy.isBlank()){
            sortBy = "id";
        }

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(direction, sortBy)
        );

        Page<CourseResponseV2> responsePage = courseRepository.findAllCourseResponse(pageable);

        return PageResponse.<CourseResponseV2>builder()
                .items(responsePage.getContent())
                .page(responsePage.getNumber())
                .size(responsePage.getSize())
                .totalItems((int) responsePage.getTotalElements())
                .totalPages(responsePage.getTotalPages())
                .isLast(responsePage.isLast())
                .build();
    }

    @Override
    public PageResponse<CourseResponseV2> getPagedCourses(Integer page, Integer size, String sortBy, Sort.Direction direction, CourseStatus status, String keyword) {
        if (page < 0){
            page = 0;
        }

        Pageable pageable;

        if (sortBy == null || sortBy.isBlank()) {
            pageable = PageRequest.of(page, size);
        } else {
            Sort sort = direction == null
                    ? Sort.unsorted()
                    : Sort.by(direction, sortBy);

            pageable = PageRequest.of(page, size, sort);
        }

        if (keyword == null || keyword.isBlank()){
            keyword = null;
        }

        Page<CourseResponseV2> pageResult = courseRepository.searchCourses(status, keyword, pageable);

        return PageResponse.<CourseResponseV2>builder()
                .items(pageResult.getContent())
                .page(pageResult.getNumber())
                .size(pageResult.getSize())
                .totalItems((int) pageResult.getTotalElements())
                .totalPages(pageResult.getTotalPages())
                .isLast(pageResult.isLast())
                .build();
    }
}
