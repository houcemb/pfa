import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.List;
@Entity
public class Employee {
    @Id
    private  Integer id;
    private String  name;
    private String  email;
    private String  password;
    private Date hireDate;
    private Date terminationDate;
    private String department;
    private String postion;
    @OneToMany(mappedBy = "Employee")
    private List<Attendance> attendances;
    @OneToMany(mappedBy = "Employee")
    private List<PerformanceReview> performanceReviewList;
    @OneToMany(mappedBy = "Employee")
    private List<Payroll> payrollList;
    @OneToMany(mappedBy = "Employee")
    private List<Pto> ptoList ;

    public Employee(Integer id, String name, String email, String password, Date hireDate, Date terminationDate, String department, String postion) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.hireDate = hireDate;
        this.terminationDate = terminationDate;
        this.department = department;
        this.postion = postion;
    }

    public Employee(Integer id, String name, String email, String password, Date hireDate, String department, String postion) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.hireDate = hireDate;
        this.department = department;
        this.postion = postion;
    }

    public Employee() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPostion() {
        return postion;
    }

    public void setPostion(String postion) {
        this.postion = postion;
    }

}
