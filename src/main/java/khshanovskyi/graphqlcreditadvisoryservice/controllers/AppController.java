package khshanovskyi.graphqlcreditadvisoryservice.controllers;

import jakarta.persistence.EntityNotFoundException;
import khshanovskyi.graphqlcreditadvisoryservice.domain.Advisor;
import khshanovskyi.graphqlcreditadvisoryservice.domain.Applicant;
import khshanovskyi.graphqlcreditadvisoryservice.domain.Application;
import khshanovskyi.graphqlcreditadvisoryservice.respository.AdvisorRepository;
import khshanovskyi.graphqlcreditadvisoryservice.respository.ApplicantRepository;
import khshanovskyi.graphqlcreditadvisoryservice.respository.ApplicationRepository;
import khshanovskyi.graphqlcreditadvisoryservice.services.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AppController {

    private final ApplicationService applicationService;
    private final ApplicationRepository applicationRepository;
    private final AdvisorRepository advisorRepository;
    private final ApplicantRepository applicantRepository;

    @MutationMapping
    public Application assignApplication(@Argument Long advisorId) {
        return applicationService.assignApplication(advisorId);
    }

    @QueryMapping
    public Advisor advisor(@Argument Long id) {
        return advisorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Advisor not found"));
    }

    @QueryMapping
    public Applicant applicant(@Argument Long id) {
        return applicantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Applicant not found"));
    }

    @QueryMapping
    public Application application(@Argument Long id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Application not found"));
    }

    @QueryMapping
    public List<Advisor> allAdvisors() {
        return advisorRepository.findAll();
    }

    @QueryMapping
    public List<Applicant> allApplicants() {
        return applicantRepository.findAll();
    }

    @QueryMapping
    public List<Application> allApplications() {
        return applicationRepository.findAll();
    }
}
