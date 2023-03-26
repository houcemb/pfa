import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.Date;

@Entity
public class Payroll {
    @Id
    private Integer payrollId;
    @ManyToOne
    private Employee employee;
    private  Date date;
    private double amount;
    private double taxes;

}
