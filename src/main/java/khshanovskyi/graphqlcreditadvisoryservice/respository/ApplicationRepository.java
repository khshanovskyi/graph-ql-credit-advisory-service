package khshanovskyi.graphqlcreditadvisoryservice.respository;

import khshanovskyi.graphqlcreditadvisoryservice.domain.Applicant;
import khshanovskyi.graphqlcreditadvisoryservice.domain.Application;
import khshanovskyi.graphqlcreditadvisoryservice.domain.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Optional<Application> findFirstByStatusAndAmountLessThanOrderByCreatedAtAsc(
            ApplicationStatus status, BigDecimal maxAmount);

    Optional<Application> findFirstByStatusAndAmountBetweenOrderByCreatedAtAsc(
            ApplicationStatus status, BigDecimal minAmount, BigDecimal maxAmount);

    Optional<Application> findFirstByStatusAndAmountGreaterThanOrderByCreatedAtAsc(
            ApplicationStatus status, BigDecimal minAmount);
}
