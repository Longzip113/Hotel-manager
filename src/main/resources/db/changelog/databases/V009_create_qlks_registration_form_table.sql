CREATE TABLE qlks_registration_form (
    id_registration_form varchar(255) NOT NULL,
    booking_date BIGINT DEFAULT NULL,
    check_in_date BIGINT DEFAULT NULL,
    check_out_date BIGINT DEFAULT NULL,
    note varchar(255) DEFAULT NULL,
    id_room TEXT DEFAULT NULL,
    id_customer TEXT DEFAULT NULL,
    id_employee varchar(255) NOT NULL,
    number_of_adult INT DEFAULT NULL,
    number_of_child INT DEFAULT NULL,
    into_money INT(11) NOT NULL,
    PRIMARY KEY (id_registration_form)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;