CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       email VARCHAR(255) NOT NULL,
                       cognito_username VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE advisors (
                          id BIGINT PRIMARY KEY REFERENCES users(id),
                          first_name VARCHAR(255) NOT NULL,
                          last_name VARCHAR(255) NOT NULL,
                          role VARCHAR(50) NOT NULL
);

CREATE TABLE applicants (
                            id BIGINT PRIMARY KEY REFERENCES users(id),
                            first_name VARCHAR(255) NOT NULL,
                            last_name VARCHAR(255) NOT NULL,
                            ssn VARCHAR(255) NOT NULL,
                            city VARCHAR(255) NOT NULL,
                            street VARCHAR(255) NOT NULL,
                            number VARCHAR(255) NOT NULL,
                            zip VARCHAR(255) NOT NULL,
                            apt VARCHAR(255)
);

CREATE TABLE phone_numbers (
                               applicant_id BIGINT REFERENCES applicants(id),
                               number VARCHAR(255) NOT NULL,
                               type VARCHAR(50) NOT NULL
);

CREATE TABLE applications (
                              id BIGSERIAL PRIMARY KEY,
                              amount DECIMAL(19,2) NOT NULL,
                              status VARCHAR(50) NOT NULL,
                              created_at TIMESTAMP NOT NULL,
                              assigned_at TIMESTAMP,
                              resolved_at TIMESTAMP,
                              applicant_id BIGINT REFERENCES applicants(id),
                              advisor_id BIGINT REFERENCES advisors(id)
);
