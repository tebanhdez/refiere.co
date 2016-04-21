-- Inserting default user roles
insert into user_roles(id, name, role_identifier, description) values (0,'SysAdmin', 'SYS', 'System administrator')
insert into user_roles(id, name, role_identifier, description) values (1,'Admin', 'ADMIN', 'Administrator')
insert into user_roles(id, name, role_identifier, description) values (2,'endUser', 'USER', 'User generated')
insert into user_roles(id, name, role_identifier, description) values (3,'AccAdmin', 'ACCAD', 'Accounting')

-- Insert default lapse
INSERT INTO lapse(id, name, days) VALUES (0, 'Diario', 1)
INSERT INTO lapse(id, name, days) VALUES (1, 'Semanal', 7)
INSERT INTO lapse(id, name, days) VALUES (2, 'Quincenal', 15)
INSERT INTO lapse(id, name, days) VALUES (3, 'Mensual', 30)
INSERT INTO lapse(id, name, days) VALUES (4, 'Bimestral', 60)
INSERT INTO lapse(id, name, days) VALUES (5, 'Trimestral', 90)
INSERT INTO lapse(id, name, days) VALUES (6, 'Cuatrimestral', 120)
INSERT INTO lapse(id, name, days) VALUES (7, 'Semestral', 180)
INSERT INTO lapse(id, name, days) VALUES (8, 'Anual', 365)

-- Insert default plans
INSERT INTO plan(id, name, is_active, referrer_amount, sales_percentaje, campaign_amount) VALUES (0, 'Plan Basico', true, 100, 0.5, 1)
INSERT INTO plan(id, name, is_active, referrer_amount, sales_percentaje, campaign_amount) VALUES (1, 'Enterprice',  true, 300, 0.5, 2)
INSERT INTO plan(id, name, is_active, referrer_amount, sales_percentaje, campaign_amount) VALUES (2, 'Corporacion', true, 500, 0.5, 2)

-- Insert default order status
INSERT INTO order_status(id, name, description) VALUES (0, 'NEW', 'Created')
INSERT INTO order_status(id, name, description) VALUES (1, 'PLACED', 'Placed')
INSERT INTO order_status(id, name, description) VALUES (2, 'PENDING', 'Pending')
INSERT INTO order_status(id, name, description) VALUES (3, 'CONFIRMED', 'Order paid')

-- INSERT DEFAULT currencies
INSERT INTO currency (id, currency_id, currency_description) VALUES (0, 'CRC', 'Costa Rican Colon')
INSERT INTO currency (id, currency_id, currency_description) VALUES (1, 'USD', 'US Dollar')
INSERT INTO currency (id, currency_id, currency_description) VALUES (2, 'EUR', 'Euro')

-- Insert DEFAULT payments type
INSERT INTO payment_type (id, type_description) VALUES (0, 'Bank Transaction')
INSERT INTO payment_type (id, type_description) VALUES (1, 'Electronic Transaction')
INSERT INTO payment_type (id, type_description) VALUES (2, 'Credit Card')
INSERT INTO payment_type (id, type_description) VALUES (3, 'Cash')
INSERT INTO payment_type (id, type_description) VALUES (4, 'Check')
