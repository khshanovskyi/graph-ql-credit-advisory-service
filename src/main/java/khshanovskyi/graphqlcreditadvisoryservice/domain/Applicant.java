package khshanovskyi.graphqlcreditadvisoryservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Entity
@Table(name = "advisors")
@Data
@EqualsAndHashCode(callSuper = true)
public class Advisor extends User {
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private AdvisorRole role;

    @OneToMany(mappedBy = "advisor")
    private List<Application> applications = new ArrayList<>();
}
