package demo.model.dto.response;

import demo.model.CourseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CourseResponse {
    private Long id;

    private String title;

    private CourseStatus status;

    private CourseInstructorResponse instructor;
}
