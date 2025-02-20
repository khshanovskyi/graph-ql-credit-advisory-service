package khshanovskyi.graphqlcreditadvisoryservice.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import khshanovskyi.graphqlcreditadvisoryservice.domain.Advisor;
import khshanovskyi.graphqlcreditadvisoryservice.domain.AdvisorRole;
import khshanovskyi.graphqlcreditadvisoryservice.domain.Application;
import khshanovskyi.graphqlcreditadvisoryservice.domain.ApplicationStatus;
import khshanovskyi.graphqlcreditadvisoryservice.respository.AdvisorRepository;
import khshanovskyi.graphqlcreditadvisoryservice.respository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final AdvisorRepository advisorRepository;

    public Application assignApplication(Long advisorId) {
        Advisor advisor = advisorRepository.findById(advisorId)
                .orElseThrow(() -> new EntityNotFoundException("Advisor not found"));

        if (!advisor.getApplications().isEmpty()) {
            throw new IllegalStateException("Advisor already has an assigned application");
        }

        Optional<Application> applicationOpt = findApplicationForRole(advisor.getRole());
        if (applicationOpt.isEmpty()) {
            throw new EntityNotFoundException("No suitable applications found");
        }

        Application application = applicationOpt.get();
        application.setAdvisor(advisor);
        application.setStatus(ApplicationStatus.ASSIGNED);
        application.setAssignedAt(LocalDateTime.now());

        return applicationRepository.save(application);
    }

    private Optional<Application> findApplicationForRole(AdvisorRole role) {
        return switch (role) {
            case ASSOCIATE -> applicationRepository
                    .findFirstByStatusAndAmountLessThanOrderByCreatedAtAsc(
                            ApplicationStatus.NEW, BigDecimal.valueOf(10_000));
            case PARTNER -> applicationRepository
                    .findFirstByStatusAndAmountBetweenOrderByCreatedAtAsc(
                            ApplicationStatus.NEW,
                            BigDecimal.valueOf(10_000),
                            BigDecimal.valueOf(50_000));
            case SENIOR -> applicationRepository
                    .findFirstByStatusAndAmountGreaterThanOrderByCreatedAtAsc(
                            ApplicationStatus.NEW, BigDecimal.valueOf(50_000));
        };
    }
}
