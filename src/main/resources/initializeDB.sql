-- Inserting default user roles
insert into user_roles(id, name, role_identifier, description) values (10,'SysAdmin', 'SYS', 'System administrator')
insert into user_roles(id, name, role_identifier, description) values (11,'Admin', 'ADMIN', 'Administrator')
insert into user_roles(id, name, role_identifier, description) values (12,'endUser', 'USER', 'User generated')
insert into user_roles(id, name, role_identifier, description) values (13,'AccAdmin', 'ACCAD', 'Accounting')

insert into simple_user(id, login, password, user_role_id) values (11, 'sys', '5y5@dm15tr@t0r', 10)
insert into simple_user(id, login, password, user_role_id) values (12, 'root', 'r00t@dm15tr@t0r', 10)
insert into simple_user(id, login, password, user_role_id) values (13, 'admin', '@dm15tr@t0r', 10)

-- Insert default lapse
INSERT INTO lapse(id, name, days) VALUES (10, 'Diario', 1)
INSERT INTO lapse(id, name, days) VALUES (11, 'Semanal', 7)
INSERT INTO lapse(id, name, days) VALUES (12, 'Quincenal', 15)
INSERT INTO lapse(id, name, days) VALUES (13, 'Mensual', 30)
INSERT INTO lapse(id, name, days) VALUES (14, 'Bimestral', 60)
INSERT INTO lapse(id, name, days) VALUES (15, 'Trimestral', 90)
INSERT INTO lapse(id, name, days) VALUES (16, 'Cuatrimestral', 120)
INSERT INTO lapse(id, name, days) VALUES (17, 'Semestral', 180)
INSERT INTO lapse(id, name, days) VALUES (18, 'Anual', 365)

-- Insert default plans
INSERT INTO plan(id, name, is_active, referrer_amount, sales_percentaje, campaign_amount, campaign_lapse_ref, report_lapse_id) VALUES (10, 'Plan Basico', true, 100, 0.5, 1, 14, 13)
INSERT INTO plan(id, name, is_active, referrer_amount, sales_percentaje, campaign_amount, campaign_lapse_ref, report_lapse_id) VALUES (11, 'Enterprice',  true, 300, 0.5, 2, 14, 12)
INSERT INTO plan(id, name, is_active, referrer_amount, sales_percentaje, campaign_amount, campaign_lapse_ref, report_lapse_id) VALUES (12, 'Corporacion', true, 500, 0.5, 2, 13, 11)

-- Insert default order status
INSERT INTO order_status(id, name, description) VALUES (10, 'NEW', 'Created')
INSERT INTO order_status(id, name, description) VALUES (11, 'PLACED', 'Placed')
INSERT INTO order_status(id, name, description) VALUES (12, 'PENDING', 'Pending')
INSERT INTO order_status(id, name, description) VALUES (13, 'CONFIRMED', 'Order paid')

-- INSERT DEFAULT currencies
INSERT INTO currency (id, currency_id, currency_description) VALUES (10, 'CRC', 'Costa Rican Colon')
INSERT INTO currency (id, currency_id, currency_description) VALUES (11, 'USD', 'US Dollar')
INSERT INTO currency (id, currency_id, currency_description) VALUES (12, 'EUR', 'Euro')

-- Insert DEFAULT payments type
INSERT INTO payment_type (id, type_description) VALUES (10, 'Bank Transaction')
INSERT INTO payment_type (id, type_description) VALUES (11, 'Electronic Transaction')
INSERT INTO payment_type (id, type_description) VALUES (12, 'Credit Card')
INSERT INTO payment_type (id, type_description) VALUES (13, 'Cash')
INSERT INTO payment_type (id, type_description) VALUES (14, 'Check')

--INSERT DEFAULT Prizes
INSERT INTO prize (id, description) VALUES (10, 'Money')
INSERT INTO prize (id, description) VALUES (11, 'Discount')
INSERT INTO prize (id, description) VALUES (12, 'Bonus')
INSERT INTO prize (id, description) VALUES (13, 'Prize')
INSERT INTO prize (id, description) VALUES (14, 'Improve service/product')