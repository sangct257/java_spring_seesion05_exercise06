package demo.repositoty;

import demo.model.Course;
import demo.model.CourseStatus;
import demo.model.dto.response.CourseResponseV2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT c FROM Course c WHERE c.status = :status")
    Page<Course> findAllByStatus(
            @Param("status") CourseStatus status,
            Pageable pageable
    );
    @Query("SELECT new demo.model.dto.response.CourseResponseV2(c.id, c.title, c.status) FROM Course c")
    Page<CourseResponseV2> findAllCourseResponse(Pageable pageable);

    @Query("""
    SELECT new demo.model.dto.response.CourseResponseV2(
        c.id,
        c.title,
        c.status
    )
    FROM Course c
    WHERE
        (:status IS NULL OR c.status = :status)
    AND
        (:keyword IS NULL OR LOWER(c.title) LIKE LOWER(CONCAT('%', :keyword, '%')))
    """)
    Page<CourseResponseV2> searchCourses(
            @Param("status") CourseStatus status,
            @Param("keyword") String keyword,
            Pageable pageable
    );

}
