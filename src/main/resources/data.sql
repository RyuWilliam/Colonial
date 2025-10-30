-- Insertar usuarios de prueba
INSERT INTO users (name, email, phone, role) VALUES
('William', 'admin@colonial.com', '3001234567', 'ADMIN'),
('Vendedor', 'vendedor@colonial.com', '3007654321', 'SELLER');

-- Insertar productos de prueba
INSERT INTO products (name, description, acquisition_price, stock, category)
VALUES
('Laptop Dell', 'Laptop empresarial', 200.0, 10, 'OTHER'),
('Mouse Logitech', 'Mouse inalámbrico', 50.0, 50, 'OTHER'),
('Teclado Mecánico', 'Teclado RGB', 15.0, 20, 'OTHER');
