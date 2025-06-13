let selectedFlight = null;
let selectedFlightId = null;
let allFlights = [];

// Precios por tipo de servicio
const servicePrices = {
    BUSINESS: 150000,
    TURISTA: 80000,
    ECONOMY: 50000,
};

function showSection(sectionName) {
    document.querySelectorAll("section").forEach((section) => {
        section.classList.remove("active");
    });

    const sectionEl = document.getElementById(sectionName + "-section");
    if (sectionEl) sectionEl.classList.add("active");

    document.querySelectorAll(".nav-link").forEach((link) => {
        link.classList.remove("active");
    });

    const activeLink = document.querySelector(`[data-section="${sectionName}"]`);
    if (activeLink) activeLink.classList.add("active");
}

document.querySelectorAll(".nav-link").forEach((link) => {
    link.addEventListener("click", (e) => {
        e.preventDefault();
        const section = e.target.getAttribute("data-section");
        showSection(section);
    });
});

async function loadFlights() {
    const flightsSelect = document.getElementById("flights-select");
    const searchBtn = document.querySelector(".search-btn");

    try {
        searchBtn.textContent = "Cargando...";
        searchBtn.disabled = true;
        flightsSelect.innerHTML = '<option value="">Cargando vuelos...</option>';

        let response;
        let attempts = 0;
        const maxAttempts = 3;

        while (attempts < maxAttempts) {
            try {
                response = await fetch("http://localhost:9000/vuelos", {
                    method: "GET",
                    headers: { "Content-Type": "application/json" },
                });
                if (response.ok) break; // Salir si ok
                else throw new Error(`HTTP error: ${response.status}`);
            } catch (fetchError) {
                attempts++;
                console.log(`Intento ${attempts} fallido:`, fetchError.message);
                if (attempts < maxAttempts) await new Promise((r) => setTimeout(r, 1000));
                else throw fetchError;
            }
        }

        const vuelos = await response.json();
        allFlights = vuelos;

        if (!vuelos || vuelos.length === 0) {
            flightsSelect.innerHTML = '<option value="">No hay vuelos disponibles</option>';
            return;
        }

        flightsSelect.innerHTML = '<option value="">Selecciona un vuelo...</option>';

        vuelos.forEach((vuelo, index) => {
            const option = document.createElement("option");
            option.value = index;
            const origen = vuelo.origen.nombreCiudad;
            const destino = vuelo.destino.nombreCiudad;
            const fecha = vuelo.salida;
            const numero = vuelo.numeroVuelo || vuelo.id;
            option.textContent = `#${numero} | ${origen} ✈️ ${destino} | ${fecha}`;
            flightsSelect.appendChild(option);
        });
    } catch (error) {
        console.error("Error al cargar vuelos:", error);
        let errorMessage = "Error al cargar vuelos";
        if (error.message.includes("Failed to fetch")) {
            errorMessage = "No se puede conectar al servidor. ¿Está ejecutándose en localhost:9000?";
        }
        flightsSelect.innerHTML = `<option value="">⚠️ ${errorMessage}</option>`;
    } finally {
        searchBtn.textContent = "Explorar Vuelos";
        searchBtn.disabled = false;
    }
}

function loadServiceTypes() {
    const select = document.getElementById("service-type");
    select.innerHTML = `
    <option value="">Selecciona un tipo de servicio</option>
    <option value="BUSINESS">BUSINESS</option>
    <option value="TURISTA">TURISTA</option>
    <option value="ECONOMY">ECONOMY</option>
  `;
}

function loadCardTypes() {
    const select = document.getElementById("card-type");
    select.innerHTML = `
    <option value="">Selecciona un tipo</option>
    <option value="DEBITO">DÉBITO</option>
    <option value="CREDITO">CRÉDITO</option>
  `;
}

function updateTotalPrice() {
    const serviceType = document.getElementById("service-type").value;
    const cardType = document.getElementById("card-type").value;
    const totalPriceInput = document.getElementById("total-price");

    if (serviceType && servicePrices[serviceType]) {
        const basePrice = selectedFlight?.precio || 0;
        const servicePrice = servicePrices[serviceType];
        let totalPrice = basePrice + servicePrice;

        // Aplicar recargo del 21% si es tarjeta de crédito
        if (cardType === 'CREDITO') {
            const creditSurcharge = totalPrice * 0.21;
            totalPrice = totalPrice + creditSurcharge;
            totalPriceInput.value = `$${totalPrice.toFixed(2)} (incluye 21% recargo por crédito)`;
        } else {
            totalPriceInput.value = `$${totalPrice.toFixed(2)}`;
        }
    } else {
        let basePrice = selectedFlight?.precio || 0;
        if (cardType === 'CREDITO' && basePrice > 0) {
            const creditSurcharge = basePrice * 0.21;
            const totalWithSurcharge = basePrice + creditSurcharge;
            totalPriceInput.value = `$${totalWithSurcharge.toFixed(2)} (incluye 21% recargo por crédito)`;
        } else {
            totalPriceInput.value = selectedFlight?.precio ? `$${selectedFlight.precio}` : "";
        }
    }
}

document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("search-form");
    const flightsSelect = document.getElementById("flights-select");
    const flightInfo = document.getElementById("flight-info");
    const flightDetails = document.getElementById("flight-details");
    const reserveBtn = document.getElementById("reserve-btn");
    const serviceTypeSelect = document.getElementById("service-type");
    const cardTypeSelect = document.getElementById("card-type");
    const bookingForm = document.getElementById("booking-form");

    loadFlights();
    loadCardTypes();
    loadServiceTypes();

    if (serviceTypeSelect) {
        serviceTypeSelect.addEventListener("change", updateTotalPrice);
    }

    if (cardTypeSelect) {
        cardTypeSelect.addEventListener("change", updateTotalPrice);
    }
    form.addEventListener("submit", async (e) => {
        e.preventDefault();
        await loadFlights();
    });

    flightsSelect.addEventListener("change", (e) => {
        const selectedIndex = e.target.value;

        if (selectedIndex === "") {
            flightInfo.style.display = "none";
            reserveBtn.style.display = "none";
            selectedFlight = null;
            selectedFlightId = null;
            document.getElementById("total-price").value = "";
        } else {
            selectedFlight = allFlights[selectedIndex];
            selectedFlightId = selectedFlight.id;

            const origen = selectedFlight.origen.nombreCiudad;
            const destino = selectedFlight.destino.nombreCiudad;

            flightDetails.innerHTML = `
            <strong>🛫 Origen:</strong> ${origen}<br>
            <strong>🛬 Destino:</strong> ${destino}<br>
            <strong>📅 Fecha:</strong> ${selectedFlight.salida || "N/A"}<br>
            <strong>💺 Número de Vuelo:</strong> ${selectedFlight.id}<br>
            <strong>💲 Precio Base:</strong> $${selectedFlight.precio || "Consultar"}
        `;

            flightInfo.style.display = "block";
            reserveBtn.style.display = "block";

            updateTotalPrice();
        }
    });

    if (serviceTypeSelect) {
        serviceTypeSelect.addEventListener("change", updateTotalPrice);
    }

    reserveBtn.addEventListener("click", () => {
        if (!selectedFlight) return;

        const bookingFlightDetails = document.getElementById("booking-flight-details");
        const origen = selectedFlight.origen.nombreCiudad;
        const destino = selectedFlight.destino.nombreCiudad;

        bookingFlightDetails.innerHTML = `
      <strong>🛫 Origen:</strong> ${origen}<br>
      <strong>🛬 Destino:</strong> ${destino}<br>
      <strong>📅 Fecha:</strong> ${selectedFlight.salida || "N/A"}<br>
      <strong>💺 Número de Vuelo:</strong> ${selectedFlight.numeroVuelo || selectedFlight.id}<br>
      <strong>💲 Precio Base:</strong> $${selectedFlight.precio || "Consultar"}
    `;

        document.getElementById("total-price").value = selectedFlight.precio ? `$${selectedFlight.precio}` : "";

        showSection("booking");
    });

    bookingForm.addEventListener("submit", async (e) => {
        e.preventDefault();

        if (!selectedFlight) {
            alert("Por favor selecciona un vuelo antes de reservar.");
            return;
        }

        const serviceType = document.getElementById("service-type").value;
        const cardType = document.getElementById("card-type").value;
        const basePrice = selectedFlight.precio || 0;
        const servicePrice = servicePrices[serviceType] || 0;
        let totalPrice = basePrice + servicePrice;

        // Aplicar recargo del 21% si es tarjeta de crédito
        if (cardType === 'CREDITO') {
            const creditSurcharge = totalPrice * 0.21;
            totalPrice = totalPrice + creditSurcharge;
        }

        const tarjetaInput = document.getElementById('card-number');
        const tarjetaNumero = tarjetaInput ? tarjetaInput.value.replace(/\s/g, '') : "";

        const reservaCompleta = {
            personaDni: Number(document.getElementById('passenger-dni').value),
            personaNombre: document.getElementById('passenger-name').value.trim(),
            personaApellido: document.getElementById('passenger-lastname').value.trim(),
            personaCorreo: document.getElementById('passenger-email').value.trim(),
            vueloId: Number(selectedFlightId),
            pagoCantidad: Number(totalPrice.toFixed(2)),
            tarjetaNumero: tarjetaNumero,
            tarjetaTipo: cardType,
            tipoServicio: serviceType,
        };

        try {
            console.log("Enviando reserva completa:", reservaCompleta);

            const response = await fetch("http://localhost:9000/reservas/crear", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json;charset=UTF-8",
                },
                body: JSON.stringify(reservaCompleta),
            });

            if (response.ok) {
                const result = await response.json();
                console.log("Reserva guardada exitosamente:", result);

                const numeroReserva = result.reservaId || result.id || "N/D"; // adaptalo según tu backend
                const recargoText = cardType === 'CREDITO' ? '\n(Incluye 21% recargo por tarjeta de crédito)' : '';

                alert(
                    `¡Reserva confirmada!\n` +
                    `Número de reserva: ${numeroReserva}\n` +
                    `Pasajero: ${reservaCompleta.personaNombre} ${reservaCompleta.personaApellido}\n` +
                    `DNI: ${reservaCompleta.personaDni}\n` +
                    `Total pagado: $${totalPrice.toFixed(2)}${recargoText}\n` +
                    `Tipo de servicio: ${serviceType}\n` +
                    `Tipo de tarjeta: ${cardType}\n` +
                    `Tarjeta: **** **** **** ${reservaCompleta.tarjetaNumero.slice(-4)}`
                );
                bookingForm.reset();
                showSection("reservations");
                loadReservations();
            } else {
                const errorText = await response.text();
                console.error("Error del servidor:", errorText);
                throw new Error(`Error del servidor: ${response.status} - ${errorText}`);
            }
        } catch (error) {
            console.error("Error al procesar la reserva:", error);
            alert(`Error al procesar la reserva: ${error.message}\nPor favor, inténtalo nuevamente.`);
        }
    });

    async function loadReservations() {
        const dniInput = document.getElementById("usuario-id");
        const reservationsList = document.getElementById("reservations-list");
        const dni = dniInput.value.trim();

        if (!dni) {
            reservationsList.innerHTML = '<p style="color: red;">Por favor ingresa un DNI.</p>';
            return;
        }

        reservationsList.innerHTML = "<p>Cargando reservas...</p>";

        try {
            const response = await fetch(`http://localhost:9000/personas/dni/${dni}`, {
                method: "GET",
                headers: { Accept: "application/json" },
            });

            if (response.ok) {
                const persona = await response.json();
                console.log("Datos recibidos:", persona); // Para debugging

                if (persona && persona.reservas && persona.reservas.length > 0) {
                    let reservasHtml = `
                <div style="margin-bottom: 20px; padding: 15px; background-color: #f5f5f5; border-radius: 8px;">
                    <h3>📋 Información del Pasajero</h3>
                    <strong>Nombre:</strong> ${persona.nombre} ${persona.apellidos || ''} <br>
                    <strong>DNI:</strong> ${persona.dni} <br>
                    <strong>Correo:</strong> ${persona.correo} <br>
                </div>
                <h3>✈️ Reservas Encontradas:</h3>
                `;

                    persona.reservas.forEach((reserva, index) => {
                        // Extraer información del vuelo si está disponible
                        const vueloInfo = reserva.vuelo || {};
                        const origen = vueloInfo.origen?.nombreCiudad || 'No disponible';
                        const destino = vueloInfo.destino?.nombreCiudad || 'No disponible';
                        const fechaSalida = vueloInfo.salida || 'No disponible';

                        // Calcular precio total usando los precios del servicio
                        const servicePrices = {
                            BUSINESS: 1500,
                            TURISTA: 800,
                            ECONOMY: 500,
                        };

                        const tipoServicio = reserva.tipoServicio || 'N/D';
                        const precioServicio = servicePrices[tipoServicio] || 0;
                        const precioBase = reserva.vuelo?.precio || 0;
                        const precioTotal = precioBase + precioServicio;

                        // Mostrar precio desglosado si hay servicio definido
                        let precioDisplay = '';
                        if (tipoServicio !== 'N/D' && precioServicio > 0) {
                            precioDisplay = `${precioTotal} (Base: ${precioBase} + ${tipoServicio}: ${precioServicio})`;
                        } else {
                            precioDisplay = `${reserva.pago?.cantidad || precioTotal || 'N/D'}`;
                        }

                        reservasHtml += `
                    <div style="border: 2px solid #007bff; padding: 15px; margin-bottom: 15px; border-radius: 8px; background-color: #f8f9fa;">
                        <h4>🎫 Reserva #${index + 1}</h4>
                        <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 10px;">
                            <div>
                                <strong>🛫 Origen:</strong> ${origen}<br>
                                <strong>🛬 Destino:</strong> ${destino}<br>
                                <strong>📅 Salida:</strong> ${fechaSalida}<br>
                            </div>
                            <div>
                                <strong>💺 Vuelo ID:</strong> ${reserva.vuelo?.id || reserva.vueloId || 'N/D'}<br>
                                <strong>💰 Precio Total:</strong> ${precioDisplay}<br>
                                <strong>🎭 Tipo de Servicio:</strong> ${tipoServicio}<br>
                            </div>
                        </div>
                        ${reserva.pago?.tarjeta ? `<div style="margin-top: 10px;"><strong>💳 Tarjeta:</strong> **** **** **** ${String(reserva.pago.tarjeta.numeroReserva || '').slice(-4)}</div>` : ''}
                    </div>
                    `;
                    });

                    reservationsList.innerHTML = reservasHtml;
                } else {
                    reservationsList.innerHTML = `
                <div style="padding: 20px; text-align: center; color: #666;">
                    <h3>😔 No se encontraron reservas</h3>
                    <p>No hay reservas registradas para el DNI: <strong>${dni}</strong></p>
                </div>`;
                }
            } else if (response.status === 404) {
                reservationsList.innerHTML = `
            <div style="padding: 20px; text-align: center; color: #dc3545;">
                <h3>❌ Persona no encontrada</h3>
                <p>No existe una persona registrada con el DNI: <strong>${dni}</strong></p>
            </div>`;
            } else {
                throw new Error(`Error del servidor: ${response.status}`);
            }
        } catch (error) {
            console.error("Error al cargar reservas:", error);
            reservationsList.innerHTML = `
        <div style="padding: 20px; text-align: center; color: #dc3545;">
            <h3>⚠️ Error de conexión</h3>
            <p>No se pudieron cargar las reservas: ${error.message}</p>
            <p>Verifica que el servidor esté ejecutándose en localhost:9000</p>
        </div>`;
        }
    }
    window.loadReservations = loadReservations;
    const consultarBtn = document.getElementById("consultar-reservas-btn");
    if (consultarBtn) {
        consultarBtn.addEventListener("click", loadReservations);
    }

});
