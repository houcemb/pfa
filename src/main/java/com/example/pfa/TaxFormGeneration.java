import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.Year;

@Entity
public class TaxFormGeneration {
    @Id
    private Integer formId;
    private Year taxYear;
    private String formType;
    @ManyToOne
    Admin admin;
}
