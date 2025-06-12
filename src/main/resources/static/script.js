let selectedFlight = null;
let selectedFlightId = null;
let allFlights = [];

// Precios por tipo de servicio
const servicePrices = {
    BUSINESS: 1500,
    TURISTA: 800,
    ECONOMY: 500,
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
    const totalPriceInput = document.getElementById("total-price");

    if (serviceType && servicePrices[serviceType]) {
        const basePrice = selectedFlight?.precio || 0;
        const servicePrice = servicePrices[serviceType];
        const totalPrice = basePrice + servicePrice;
        totalPriceInput.value = `$${totalPrice}`;
    } else {
        totalPriceInput.value = selectedFlight?.precio ? `$${selectedFlight.precio}` : "";
    }
}

document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("search-form");
    const flightsSelect = document.getElementById("flights-select");
    const flightInfo = document.getElementById("flight-info");
    const flightDetails = document.getElementById("flight-details");
    const reserveBtn = document.getElementById("reserve-btn");
    const serviceTypeSelect = document.getElementById("service-type");
    const bookingForm = document.getElementById("booking-form");

    loadFlights();
    loadCardTypes();
    loadServiceTypes();

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
        const basePrice = selectedFlight.precio || 0;
        const servicePrice = servicePrices[serviceType] || 0;
        const totalPrice = basePrice + servicePrice;
        const tarjetaInput = document.getElementById('card-number');
        const tarjetaNumero = tarjetaInput ? tarjetaInput.value.replace(/\s/g, '') : "";


        const reservaCompleta = {
            personaDni: Number(document.getElementById('passenger-dni').value),
            personaNombre: document.getElementById('passenger-name').value.trim(),
            personaApellido: document.getElementById('passenger-lastname').value.trim(),
            personaCorreo: document.getElementById('passenger-email').value.trim(),
            vueloId: Number(selectedFlightId),
            pagoCantidad: Number(totalPrice),
            tarjetaNumero: tarjetaNumero,
            tarjetaTipo: document.getElementById('card-type').value,
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

                alert(
                    `¡Reserva confirmada!\n` +
                    `Pasajero: ${reservaCompleta.personaNombre} ${reservaCompleta.personaApellido}\n` +
                    `DNI: ${reservaCompleta.personaDni}\n` +
                    `Total pagado: $${totalPrice}\n` +
                    `Tipo de servicio: ${serviceType}\n` +
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

                if (persona && persona.reservas && persona.reservas.length > 0) {
                    let reservasHtml = `
                    <div style="margin-bottom: 20px;">
                        <strong>Nombre:</strong> ${persona.nombre} ${persona.apellido} <br>
                        <strong>DNI:</strong> ${persona.dni} <br>
                        <strong>Correo:</strong> ${persona.correo} <br>
                    </div>
                    <h3>Reservas encontradas:</h3>
                    `;

                    persona.reservas.forEach((reserva) => {
                        reservasHtml += `
                        <div style="border:1px solid #ccc; padding:10px; margin-bottom:10px;">
                          <strong>Vuelo:</strong> ${reserva.vuelo_id} <br>
                          <strong>Fecha:</strong> ${reserva.fecha_reserva || "N/D"} <br>
                          <strong>Precio:</strong> $${reserva.precio || "N/D"} <br>
                          <strong>Tipo de servicio:</strong> ${reserva.tipo_servicio || "N/D"}
                        </div>
                      `;
                    });

                    reservationsList.innerHTML = reservasHtml;
                } else {
                    reservationsList.innerHTML = "<p>No se encontraron reservas para este DNI.</p>";
                }
            } else {
                throw new Error(`Error al obtener reservas: ${response.status}`);
            }
        } catch (error) {
            console.error("Error al cargar reservas:", error);
            reservationsList.innerHTML = `<p>Error al cargar reservas: ${error.message}</p>`;
        }
    }
    window.loadReservations = loadReservations;
    const consultarBtn = document.getElementById("consultar-reservas-btn");
    if (consultarBtn) {
        consultarBtn.addEventListener("click", loadReservations);
    }

});
