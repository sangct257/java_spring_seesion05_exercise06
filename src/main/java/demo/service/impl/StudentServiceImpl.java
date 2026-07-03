package demo.service.impl;

import demo.model.dto.response.PageResponse;
import demo.model.dto.response.StudentResponseV2;
import demo.repositoty.StudentRepository;
import demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public PageResponse<StudentResponseV2> getPagedStudents(Integer page, Integer size, String sortBy, Sort.Direction direction, String keyword) {
        if (page < 0){
            page = 0;
        }
        Pageable pageable;

        if(sortBy == null || sortBy.isBlank()){
            pageable = PageRequest.of(page,size);
        } else {
            Sort sort = direction == null
                    ? Sort.unsorted()
                    : Sort.by(direction,sortBy);
            pageable = PageRequest.of(page,size,sort);
        }

        if (keyword == null || keyword.isBlank()){
            keyword = null;
        }

        Page<StudentResponseV2> pageResult = studentRepository.searchStudents(keyword,pageable);
        return PageResponse.<StudentResponseV2>builder()
                .items(pageResult.getContent())
                .page(pageResult.getNumber())
                .size(pageResult.getSize())
                .totalItems((int) pageResult.getTotalElements())
                .totalPages(pageResult.getTotalPages())
                .isLast(pageResult.isLast())
                .build();
    }
}
