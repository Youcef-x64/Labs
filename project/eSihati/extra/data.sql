-- Role
INSERT INTO role (name) VALUES ('ROLE_ADMIN');
INSERT INTO role (name) VALUES ('ROLE_USER');
INSERT INTO role (name) VALUES ('ROLE_DOCTOR');
INSERT INTO role (name) VALUES ('ROLE_HOSPITAL_OR_CLINIC');
INSERT INTO role (name) VALUES ('ROLE_PATIENT');

-- Gender
INSERT INTO gender (value) VALUES ('Male');
INSERT INTO gender (value) VALUES ('Female');

-- User
INSERT INTO _user (first_name, last_name, email, phone, encrypted_password, role_id) 
VALUES 
  ('John', 'Doe', 'john@example.com', '123456789', 'password', 1),
  ('Alice', 'Smith', 'alice@example.com', '987654321', 'password', 2),
  ('Bob', 'Johnson', 'bob@example.com', '456789123', 'password', 3),
  ('Emily', 'Brown', 'emily@example.com', '987123456', 'password', 4),
  ('Michael', 'Wilson', 'michael@example.com', '654321987', 'password', 5);

-- Patient
INSERT INTO patient (gender_id, id) VALUES (1, 5);