package demo.controller;

import demo.model.CourseStatus;
import demo.model.dto.response.ApiResponse;
import demo.model.dto.response.CourseResponse;
import demo.model.dto.response.CourseResponseV2;
import demo.model.dto.response.PageResponse;
import demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<CourseResponse>>> getPagedCourses(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sortBy", defaultValue = "") String sortBy,
            @RequestParam(value = "direction", defaultValue = "DESC") Sort.Direction direction,
            @RequestParam(value = "status", defaultValue = "ACTIVE") CourseStatus status) {
        return new ResponseEntity<>(new ApiResponse<>(
                true,
                "Đã lấy ra danh sách",
                courseService.getPagedCoursesByStatus(page - 1, size, sortBy, direction,status),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @GetMapping("/v2")
    public ResponseEntity<ApiResponse<PageResponse<CourseResponseV2>>> getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "") String sortBy,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction

    ) {

        return new ResponseEntity<>(new ApiResponse<>(
                true,
                "Lấy danh sách thành công",
                courseService.getPagedCoursesV2(page-1,size,sortBy,direction),
                null,
                HttpStatus.OK
        ),HttpStatus.OK);
    }

    @GetMapping("/v3")
    public ResponseEntity<ApiResponse<PageResponse<CourseResponseV2>>> getCourses(
            @RequestParam(required = false) CourseStatus status,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) Sort.Direction direction
    ) {
        return new ResponseEntity<>(new ApiResponse<>(
                true,
                "Đã lấy ra danh sách thành công",
                courseService.getPagedCourses(page,size,sortBy,direction,status,keyword),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }
}
