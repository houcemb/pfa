import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity
public class Attendance {
    @Id
    private Integer attendanceId;
    private Date attendanceDate;
    private String status;
    @ManyToOne
    Employee employee;
}
