INSERT INTO department (department_floor, department_name, department_floor_number) VALUES ('3 Fauster', 'Mother and Baby', 3);
INSERT INTO department (department_floor, department_name, department_floor_number) VALUES ('3 West', 'Intensive Care', 3);
INSERT INTO department (department_floor, department_name, department_floor_number) VALUES ('3 Neuman', 'Labor and Delivery', 3);
INSERT INTO department (department_floor, department_name, department_floor_number) VALUES ('4 Neuman', 'Cardiology', 4);
INSERT INTO department (department_floor, department_name, department_floor_number) VALUES ('4 South', 'CICU', 4);
INSERT INTO department (department_floor, department_name, department_floor_number) VALUES ('4 Fauster', 'Peds', 4);
INSERT INTO department (department_floor, department_name, department_floor_number) VALUES ('5 Fauster', 'Pediatric', 5);
INSERT INTO department (department_floor, department_name, department_floor_number) VALUES ('5 Neuman', 'PICU', 5);
INSERT INTO department (department_floor, department_name, department_floor_number) VALUES ('5 South', 'Surgery Recovery', 5);




INSERT INTO housekeeper (housekeeper_first_name, housekeeper_last_name, housekeeper_pager) VALUES ('Lauren', 'Andrews', '2345');
INSERT INTO housekeeper (housekeeper_first_name, housekeeper_last_name, housekeeper_pager) VALUES ('Cami', 'Cams', '4512');
INSERT INTO housekeeper (housekeeper_first_name, housekeeper_last_name, housekeeper_pager) VALUES ('Edward', 'Rose', '5611');
INSERT INTO housekeeper (housekeeper_first_name, housekeeper_last_name, housekeeper_pager) VALUES ('Bluey', 'Heeler', '0589');

INSERT INTO department_housekeeper (department_id, housekeeper_id) VALUES (1, 1);
INSERT INTO department_housekeeper (department_id, housekeeper_id) VALUES (2, 1);
INSERT INTO department_housekeeper (department_id, housekeeper_id) VALUES (3, 1);
INSERT INTO department_housekeeper (department_id, housekeeper_id) VALUES (4, 2);
INSERT INTO department_housekeeper (department_id, housekeeper_id) VALUES (5, 2);
INSERT INTO department_housekeeper (department_id, housekeeper_id) VALUES (6, 2);
INSERT INTO department_housekeeper (department_id, housekeeper_id) VALUES (7, 3);
INSERT INTO department_housekeeper (department_id, housekeeper_id) VALUES (8, 3);
INSERT INTO department_housekeeper (department_id, housekeeper_id) VALUES (9, 3);
INSERT INTO department_housekeeper (department_id, housekeeper_id) VALUES (7, 4);
INSERT INTO department_housekeeper (department_id, housekeeper_id) VALUES (3, 4);
INSERT INTO department_housekeeper (department_id, housekeeper_id) VALUES (5, 4);