-----------------------------------------------------------------------------
-- 1- Creation of Tables
-----------------------------------------------------------------------------

-- -----------------------------------------------------
-- Table user
-- -----------------------------------------------------
DROP TABLE IF EXISTS user;

CREATE TABLE IF NOT EXISTS user (
    id INT NOT NULL,
    first_name VARCHAR(45) NOT NULL,
    last_name VARCHAR(45) NOT NULL,
    phone VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table address
-- -----------------------------------------------------
DROP TABLE IF EXISTS address;

CREATE TABLE IF NOT EXISTS address (
    id INT NOT NULL,
    street VARCHAR(45) NOT NULL,
    city VARCHAR(45) NOT NULL,
    state VARCHAR(45) NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table patient
-- -----------------------------------------------------
DROP TABLE IF EXISTS patient;

CREATE TABLE IF NOT EXISTS patient (
    user_id INT NOT NULL,
    mailing_address_id INT NOT NULL,
    dob DATE NOT NULL,
    PRIMARY KEY (user_id),
    CONSTRAINT fk_patient_user
        FOREIGN KEY (user_id)
        REFERENCES user (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT fk_patient_address
        FOREIGN KEY (mailing_address_id)
        REFERENCES address (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table speciality
-- -----------------------------------------------------
DROP TABLE IF EXISTS speciality;

CREATE TABLE IF NOT EXISTS speciality (
    id INT NOT NULL,
    name VARCHAR(45) NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table dentist
-- -----------------------------------------------------
DROP TABLE IF EXISTS dentist;

CREATE TABLE IF NOT EXISTS dentist (
    user_id INT NOT NULL,
    speciality_id INT NOT NULL,
    PRIMARY KEY (user_id),
    CONSTRAINT fk_dentist_user
        FOREIGN KEY (user_id)
        REFERENCES user (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT fk_dentist_speciality
        FOREIGN KEY (speciality_id)
        REFERENCES speciality (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table office_manager
-- -----------------------------------------------------
DROP TABLE IF EXISTS office_manager;

CREATE TABLE IF NOT EXISTS office_manager (
    user_id INT NOT NULL,
    PRIMARY KEY (user_id),
    CONSTRAINT fk_office_manager_user
        FOREIGN KEY (user_id)
        REFERENCES user (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table surgery
-- -----------------------------------------------------
DROP TABLE IF EXISTS surgery;

CREATE TABLE IF NOT EXISTS surgery (
    id INT NOT NULL,
    name VARCHAR(45) NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table ads_branch
-- -----------------------------------------------------
DROP TABLE IF EXISTS ads_branch;

CREATE TABLE IF NOT EXISTS ads_branch (
    id INT NOT NULL,
    address_id INT NOT NULL,
    name VARCHAR(45) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_ads_branch_address
        FOREIGN KEY (address_id)
        REFERENCES address (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table appointment
-- -----------------------------------------------------
DROP TABLE IF EXISTS appointment;

CREATE TABLE IF NOT EXISTS appointment (
    id INT NOT NULL,
    patient_user_id INT NOT NULL,
    dentist_user_id INT NOT NULL,
    surgery_id INT NOT NULL,
    ads_branch_id INT NOT NULL,
    date_time DATETIME NOT NULL,
    is_approved TINYINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_appointment_patient
        FOREIGN KEY (patient_user_id)
        REFERENCES patient (user_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT fk_appointment_dentist
        FOREIGN KEY (dentist_user_id)
        REFERENCES dentist (user_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT fk_appointment_surgery
        FOREIGN KEY (surgery_id)
        REFERENCES surgery (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT fk_appointment_ads_branch
        FOREIGN KEY (ads_branch_id)
        REFERENCES ads_branch (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE = InnoDB;

-----------------------------------------------------------------------------
-- 2- Insert Fake Data
-----------------------------------------------------------------------------

-- Insert fake data into the user table
INSERT INTO user (id, first_name, last_name, phone, email)
VALUES
    (1, 'John', 'Doe', '1234567890', 'john.doe@example.com'),
    (2, 'Jane', 'Smith', '0987654321', 'jane.smith@example.com'),
    (3, 'Michael', 'Johnson', '5555555555', 'michael.johnson@example.com'),
    (4, 'Alice', 'Johnson', '1112223333', 'alice.johnson@example.com'),
    (5, 'Bob', 'Williams', '4445556666', 'bob.williams@example.com'),
    (6, 'Emily', 'Brown', '7778889999', 'emily.brown@example.com'),
    (7, 'David', 'Taylor', '1231231234', 'david.taylor@example.com');

-- Insert fake data into the address table
INSERT INTO address (id, street, city, state)
VALUES
    (1, '123 Main St', 'Anytown', 'CA'),
    (2, '456 Elm St', 'Otherville', 'NY'),
    (3, '789 Maple St', 'Smalltown', 'TX');

-- Insert fake data into the patient table
INSERT INTO patient (user_id, mailing_address_id, dob)
VALUES
    (1, 1, '1990-05-15'),
    (2, 2, '1985-10-20'),
    (6, 1, '1980-08-25'),
    (7, 2, '1995-04-12');

-- Insert fake data into the speciality table
INSERT INTO speciality (id, name)
VALUES
    (1, 'Orthodontics'),
    (2, 'Endodontics'),
    (3, 'Periodontics');

-- Insert fake data into the dentist table
INSERT INTO dentist (user_id, speciality_id)
VALUES
    (3, 1), -- Michael Johnson, Orthodontics
    (5, 2); -- Bob Williams, Endodontics

-- Insert fake data into the office_manager table
INSERT INTO office_manager (user_id)
VALUES
    (4),
    (7);

-- Insert fake data into the surgery table
INSERT INTO surgery (id, name)
VALUES
    (1, 'Tooth Extraction'),
    (2, 'Root Canal'),
    (3, 'Gum Surgery');

-- Insert fake data into the ads_branch table
INSERT INTO ads_branch (id, address_id, name)
VALUES
    (1, 1, 'Main Street Dental Clinic'),
    (2, 2, 'Elm Street Dental Care'),
    (3, 3, 'Maple Street Dental Center');

-- Insert fake data into the appointment table
INSERT INTO appointment (id, patient_user_id, dentist_user_id, surgery_id, ads_branch_id, date_time, is_approved)
VALUES
    (1, 1, 3, 1, 1, '2024-04-08 10:00:00', 1),
    (2, 2, 5, 2, 2, '2024-04-09 11:00:00', 1),
    (3, 6, 3, 3, 3, '2024-04-10 12:00:00', 1),
    (4, 7, 5, 1, 1, '2024-04-11 13:00:00', 1);

-----------------------------------------------------------------------------
-- 3- Retreive Data
-----------------------------------------------------------------------------

-- 1- Display the list of ALL Dentists registered in the system, sorted in ascending order of their lastNames 

SELECT u.first_name, u.last_name, s.name AS speciality
FROM dentist d
JOIN user u ON d.user_id = u.id
JOIN speciality s ON d.speciality_id = s.id
ORDER BY u.last_name ASC;

-- 2- Display the list of ALL Appointments for a given Dentist by their dentist_Id number. Include in the result, the Patient information. 

SELECT 
    a.id AS appointment_id,
    a.date_time AS appointment_date_time,
    u.first_name AS patient_first_name,
    u.last_name AS patient_last_name,
    u.phone AS patient_phone,
    u.email AS patient_email
FROM 
    appointment a
JOIN 
    patient p ON a.patient_user_id = p.user_id
JOIN 
    user u ON p.user_id = u.id
WHERE 
    a.dentist_user_id = 3;

-- 3- Display the list of ALL Appointments that have been scheduled at a Surgery Location

SELECT 
    a.id AS appointment_id,
    a.date_time AS appointment_date_time,
    CONCAT(u.first_name, ' ', u.last_name) AS patient_name,
    ab.name AS branch_name
FROM 
    appointment a
JOIN 
    ads_branch ab ON a.ads_branch_id = ab.id
JOIN 
    patient p ON a.patient_user_id = p.user_id
JOIN 
    user u ON p.user_id = u.id
WHERE 
    ab.id = 1;

-- 4- Display the list of the Appointments booked for a given Patient on a given Date. 

SELECT 
    a.id AS appointment_id,
    a.date_time AS appointment_date_time,
    CONCAT(u.first_name, ' ', u.last_name) AS patient_name,
    ab.name AS branch_name,
    s.name AS surgery_name
FROM 
    appointment a
JOIN 
    ads_branch ab ON a.ads_branch_id = ab.id
JOIN 
    patient p ON a.patient_user_id = p.user_id
JOIN 
    user u ON p.user_id = u.id
JOIN 
    surgery s ON a.surgery_id = s.id
WHERE 
    a.patient_user_id = 1
    AND DATE(a.date_time) = '2024-04-08';
