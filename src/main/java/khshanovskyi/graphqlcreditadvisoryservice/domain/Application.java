package khshanovskyi.graphqlcreditadvisoryservice.domain;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;


@Entity
@Table(name = "applicants")
@Data
@EqualsAndHashCode(callSuper = true)
public class Applicant extends User {
    private String firstName;
    private String lastName;
    private String ssn;

    @Embedded
    private Address address;

    @ElementCollection
    @CollectionTable(name = "phone_numbers")
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

    @OneToMany(mappedBy = "applicant")
    private List<Application> applications = new ArrayList<>();
}
