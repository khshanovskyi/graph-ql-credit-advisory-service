-- Clear existing data (if needed)
TRUNCATE applications CASCADE;
TRUNCATE phone_numbers CASCADE;
TRUNCATE applicants CASCADE;
TRUNCATE advisors CASCADE;
TRUNCATE users CASCADE;

-- Insert users and advisors with different roles
INSERT INTO users (id, email, cognito_username) VALUES
                                                    (1, 'john.associate@bank.com', 'john_associate'),
                                                    (2, 'mary.partner@bank.com', 'mary_partner'),
                                                    (3, 'david.senior@bank.com', 'david_senior'),
                                                    (4, 'alice.smith@email.com', 'alice_smith'),
                                                    (5, 'bob.jones@email.com', 'bob_jones'),
                                                    (6, 'carol.wilson@email.com', 'carol_wilson');

-- Insert advisors
INSERT INTO advisors (id, first_name, last_name, role) VALUES
                                                           (1, 'John', 'Associate', 'ASSOCIATE'),
                                                           (2, 'Mary', 'Partner', 'PARTNER'),
                                                           (3, 'David', 'Senior', 'SENIOR');

-- Insert applicants with addresses
INSERT INTO applicants (id, first_name, last_name, ssn, city, street, number, zip, apt) VALUES
                                                                                            (4, 'Alice', 'Smith', '123-45-6789', 'New York', 'Broadway', '123', '10001', '4A'),
                                                                                            (5, 'Bob', 'Jones', '234-56-7890', 'Los Angeles', 'Sunset Blvd', '456', '90028', '12B'),
                                                                                            (6, 'Carol', 'Wilson', '345-67-8901', 'Chicago', 'Michigan Ave', '789', '60601', '15C');

-- Insert phone numbers
INSERT INTO phone_numbers (applicant_id, number, type) VALUES
                                                           (4, '212-555-1234', 'HOME'),
                                                           (4, '917-555-5678', 'MOBILE'),
                                                           (5, '323-555-9012', 'HOME'),
                                                           (5, '213-555-3456', 'WORK'),
                                                           (6, '312-555-7890', 'MOBILE');

-- Insert applications with different amounts and statuses
INSERT INTO applications (
    id,
    amount,
    status,
    created_at,
    assigned_at,
    resolved_at,
    applicant_id,
    advisor_id
) VALUES
-- New applications (unassigned)
(1, 5000.00, 'NEW', CURRENT_TIMESTAMP - INTERVAL '5 days', NULL, NULL, 4, NULL),
(2, 25000.00, 'NEW', CURRENT_TIMESTAMP - INTERVAL '4 days', NULL, NULL, 5, NULL),
(3, 75000.00, 'NEW', CURRENT_TIMESTAMP - INTERVAL '3 days', NULL, NULL, 6, NULL),
(4, 8000.00, 'NEW', CURRENT_TIMESTAMP - INTERVAL '2 days', NULL, NULL, 4, NULL),
(5, 40000.00, 'NEW', CURRENT_TIMESTAMP - INTERVAL '1 day', NULL, NULL, 5, NULL),

-- Assigned applications
(6, 7500.00, 'ASSIGNED', CURRENT_TIMESTAMP - INTERVAL '10 days', CURRENT_TIMESTAMP - INTERVAL '9 days', NULL, 4, 1),
(7, 35000.00, 'ASSIGNED', CURRENT_TIMESTAMP - INTERVAL '8 days', CURRENT_TIMESTAMP - INTERVAL '7 days', NULL, 5, 2),
(8, 60000.00, 'ASSIGNED', CURRENT_TIMESTAMP - INTERVAL '6 days', CURRENT_TIMESTAMP - INTERVAL '5 days', NULL, 6, 3),

-- Applications with other statuses
(9, 15000.00, 'APPROVED', CURRENT_TIMESTAMP - INTERVAL '15 days', CURRENT_TIMESTAMP - INTERVAL '14 days', CURRENT_TIMESTAMP - INTERVAL '13 days', 4, 2),
(10, 45000.00, 'DECLINED', CURRENT_TIMESTAMP - INTERVAL '12 days', CURRENT_TIMESTAMP - INTERVAL '11 days', CURRENT_TIMESTAMP - INTERVAL '10 days', 5, 2),
(11, 90000.00, 'ON_HOLD', CURRENT_TIMESTAMP - INTERVAL '9 days', CURRENT_TIMESTAMP - INTERVAL '8 days', NULL, 6, 3),
(12, 4500.00, 'CANCELED', CURRENT_TIMESTAMP - INTERVAL '7 days', CURRENT_TIMESTAMP - INTERVAL '6 days', CURRENT_TIMESTAMP - INTERVAL '5 days', 4, 1);

-- Set the sequence values to continue after our inserted ids
SELECT setval('users_id_seq', (SELECT MAX(id) FROM users));
SELECT setval('applications_id_seq', (SELECT MAX(id) FROM applications));