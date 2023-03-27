import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Admin {
    @Id
    private Integer adminId;
    private String adminName;
    private String adminPassword;
    private String adminMailAddress;
    @OneToMany(mappedBy = "Admin")


    private List<TaxFormGeneration> taxFormGenera;
    @OneToMany(mappedBy = "Admin")
    private List<Payroll> payrollList;
    @OneToMany(mappedBy = "Admin")
    private List<PerformanceReview> performance;
    @OneToMany(mappedBy = "Admin")
    private List<Pto> ptoList;


}
