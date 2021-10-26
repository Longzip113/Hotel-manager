CREATE TABLE qlks_bill (
  id_bill varchar(255) NOT NULL,
  id_registration_form varchar(255) DEFAULT NULL,
  id_customer varchar(255) NOT NULL,
  id_room varchar(255) NOT NULL,
  room_rent int(11) DEFAULT NULL,
  service_fee int(11) DEFAULT NULL,
  costs_incurred int(11) DEFAULT NULL,
  total_money int(11) DEFAULT NULL,
  day_of_payment BIGINT DEFAULT NULL,
  id_employee varchar(255) DEFAULT NULL,
  is_delete BIT(1) DEFAULT NULL,
  PRIMARY KEY (id_bill)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;