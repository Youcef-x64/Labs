INSERT INTO property (ref, city, state, monthly_rental_rate)
VALUES ('1210 Kilo Road', 'Denver', 'CO', 3945.50),
       ('1A Galaria', 'Dallas', 'TX', 950);

INSERT INTO lease (ref, start_date, end_date, property_id)
VALUES ('5121543109', '2023-09-17', '2024-03-17', 1),
       ('7000511568', '2023-10-20', '2024-10-20', 1),
       ('6927458265', '2022-12-09', '2023-12-09', 1);