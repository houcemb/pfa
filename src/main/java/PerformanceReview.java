import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class PerformanceReview {
    @Id
    private Integer performanceReviewId;

    private Date reviewDate;
    private Integer performanceReview;
    @ManyToOne
    Employee employee;


}
