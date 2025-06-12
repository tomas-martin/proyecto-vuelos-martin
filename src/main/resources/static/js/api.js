const API_BASE_URL = 'http://localhost:9000';

async function handleResponse(response) {
    if (!response.ok) {
        const error = await response.text();
        throw new Error(error || 'Error en la solicitud');
    }
    return response.json();
}

// Obtener todos los vuelos
async function getAllFlights() {
    const response = await fetch(`${API_BASE_URL}/vuelos`);
    return handleResponse(response);
}

// Obtener detalles de un vuelo específico
async function getFlightDetails(flightId) {
    const response = await fetch(`${API_BASE_URL}/vuelos/${flightId}`);
    return handleResponse(response);
}

// Obtener información de aerolínea
async function getAirlineInfo(airlineId) {
    const response = await fetch(`${API_BASE_URL}/aerolineas/${airlineId}`);
    return handleResponse(response);
}

// Crear una nueva reserva
async function createReservation(reservationData) {
    const response = await fetch(`${API_BASE_URL}/reservas`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(reservationData)
    });
    return handleResponse(response);
}

export default {
    getAllFlights,
    getFlightDetails,
    getAirlineInfo,
    createReservation
};