package khshanovskyi.graphqlcreditadvisoryservice.respository;

import khshanovskyi.graphqlcreditadvisoryservice.domain.Advisor;
import khshanovskyi.graphqlcreditadvisoryservice.domain.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
}
