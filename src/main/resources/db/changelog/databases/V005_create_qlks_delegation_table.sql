CREATE TABLE qlks_delegation (
  id_delegations varchar(255) NOT NULL,
  id_team_manager varchar(255) NOT NULL,
  id_customer TEXT DEFAULT NULL,
  name_delegations varchar(255) NOT NULL,
  name_company varchar(255)  NOT NULL,
  number_of_people BIGINT DEFAULT NULL,
  is_delete bit(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (id_delegations)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;