package khshanovskyi.graphqlcreditadvisoryservice.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;


@Embeddable
@Data
public class PhoneNumber {
    private String number;

    @Enumerated(EnumType.STRING)
    private PhoneType type;
}
