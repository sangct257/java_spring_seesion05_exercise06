package demo.service;

import demo.model.dto.response.PageResponse;
import demo.model.dto.response.StudentResponseV2;
import org.springframework.data.domain.Sort;

public interface StudentService {
    PageResponse<StudentResponseV2> getPagedStudents(Integer page, Integer size, String sortBy, Sort.Direction direction, String keyword);
}
