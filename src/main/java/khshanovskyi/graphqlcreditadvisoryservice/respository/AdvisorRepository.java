package khshanovskyi.graphqlcreditadvisoryservice.respository;

import khshanovskyi.graphqlcreditadvisoryservice.domain.Advisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvisorRepository extends JpaRepository<Advisor, Long> {
}
