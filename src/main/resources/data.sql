INSERT INTO users (username, encrypted_password, role) VALUES ('Jon', '$2y$10$yA7EQGN81wB/Jmmv6f/Xyuc2RzlDldyOTB98neteYOdBTCSBS7zp.', 'VENDOR');

-- Guest users
INSERT INTO users (username, encrypted_password, role) VALUES ('Guest1', '$2y$10$yA7EQGN81wB/Jmmv6f/Xyuc2RzlDldyOTB98neteYOdBTCSBS7zp.', 'GUEST');
INSERT INTO users (username, encrypted_password, role) VALUES ('Guest2', '$2y$10$yA7EQGN81wB/Jmmv6f/Xyuc2RzlDldyOTB98neteYOdBTCSBS7zp.', 'GUEST');
INSERT INTO users (username, encrypted_password, role) VALUES ('Guest3', '$2y$10$yA7EQGN81wB/Jmmv6f/Xyuc2RzlDldyOTB98neteYOdBTCSBS7zp.', 'GUEST');
INSERT INTO users (username, encrypted_password, role) VALUES ('Guest4', '$2y$10$yA7EQGN81wB/Jmmv6f/Xyuc2RzlDldyOTB98neteYOdBTCSBS7zp.', 'GUEST');
INSERT INTO users (username, encrypted_password, role) VALUES ('Guest5', '$2y$10$yA7EQGN81wB/Jmmv6f/Xyuc2RzlDldyOTB98neteYOdBTCSBS7zp.', 'GUEST');

INSERT INTO tickets (name, price, age, gender, phone, address, user_id) VALUES
('Concert Ticket 1', 50.00, 25, 'Male', '123-456-7890', '123 Main St', 2),
('Concert Ticket 2', 50.00, 30, 'Female', '987-654-3210', '456 Oak St', 2),
('Concert Ticket 3', 50.00, 22, 'Male', '111-222-3333', '789 Pine St', 3),
('Concert Ticket 4', 50.00, 28, 'Female', '444-555-6666', '987 Elm St', 3),
('Concert Ticket 5', 50.00, 35, 'Male', '777-888-9999', '654 Birch St', 4),
('Concert Ticket 6', 50.00, 29, 'Female', '000-111-2222', '321 Maple St', 4),
('Concert Ticket 7', 50.00, 26, 'Male', '333-444-5555', '567 Cedar St', 5),
('Concert Ticket 8', 50.00, 31, 'Female', '666-777-8888', '876 Pine St', 5),
('Concert Ticket 9', 50.00, 23, 'Male', '999-000-1111', '432 Oak St', 6),
('Concert Ticket 10', 50.00, 27, 'Female', '234-567-8901', '789 Elm St', 6);