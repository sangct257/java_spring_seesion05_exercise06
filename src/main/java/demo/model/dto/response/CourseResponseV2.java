package demo.model.dto.response;

import demo.model.CourseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponseV2 {
    private Long id;
    private String title;
    private CourseStatus status;
}
