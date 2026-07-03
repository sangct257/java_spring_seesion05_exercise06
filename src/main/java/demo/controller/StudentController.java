package demo.controller;

import demo.model.dto.response.ApiResponse;
import demo.model.dto.response.PageResponse;
import demo.model.dto.response.StudentResponseV2;
import demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<StudentResponseV2>>> getStudents(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) Sort.Direction direction,
            @RequestParam(required = false) String keyword
    ) {

        return new ResponseEntity<>(new ApiResponse<>(
                true,
                "Đã lấy danh sách thành công",
                studentService.getPagedStudents(page,size,sortBy,direction,keyword),
                null,
                HttpStatus.OK
        ),HttpStatus.OK);
    }

}