INSERT INTO ciudades (nombre_ciudad) VALUES
('Buenos Aires'),
('Madrid'),
('Londres'),
('París'),
('Roma'),
('Nueva York'),
('Tokio'),
('Sídney'),
('Lima'),
('Bogotá');

INSERT INTO aeropuerto (nombre, ciudad_id)
VALUES
    ('Aeropuerto Ezeiza', 1),
    ('Aeropuerto El Prat', 4),
    ('Aeropuerto Barajas', 2),
    ('Aeropuerto Heathrow', 3),
    ('Aeropuerto Fiumicino', 5);

INSERT INTO aerolinea (nombre) VALUES
('Aerolíneas Argentinas'),
('LATAM'),
('Sky Airline');

INSERT INTO aviones (numero_avion, tipo_avion, tipo_turbina) VALUES
('AR-A123', 'Boeing 737', 'Turbofan'),
('LA-B456', 'Airbus A320', 'Turbofan'),
('SK-C789', 'Embraer 190', 'Turbofan');


INSERT INTO vuelos (salida, origen_id, destino_id, aerolinea_id, avion_id) VALUES
('2025-06-11 10:00:00', 1, 2, 1, 1),
('2025-06-12 15:30:00', 3, 5, 2, 2),
('2025-06-13 20:45:00', 4, 1, 3, 3);


