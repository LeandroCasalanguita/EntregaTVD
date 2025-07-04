function toggleForm(id) {
    document.getElementById('formAgregar').classList.add('d-none');
    document.getElementById('formBuscar').classList.add('d-none');
    document.getElementById(id).classList.remove('d-none');
}

// Agregar socio
document.addEventListener("DOMContentLoaded", () => {
    document.getElementById('agregarSocioForm').addEventListener('submit', async function (e) {
        e.preventDefault();
        const form = e.target;
        const data = Object.fromEntries(new FormData(form));
        const res = await fetch('http://localhost:8080/api/socios', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });
        const from = document.getElementById('formAgregar');
        const mensajeDiv = document.getElementById("mensaje");
        
        if (res.status === 201) {       
            form.reset();
            mensajeDiv.innerHTML = `
                <div class="alert alert-success" role="alert">
                    Socio agregado con éxito.
                    <br>
                    <button class="btn btn-primary mt-2" onclick="volverAlInicio()">Volver al inicio</button>
                </div>
            `;
            mensajeDiv.style.display = "block";
            from.style.display = "none";
        } else {
            mensajeDiv.innerHTML = `
                <div class="alert alert-danger" role="alert">
                    Error al agregar socio.
                    <br>
                    <button class="btn btn-secondary mt-2" onclick="volverAlInicio()">Volver al inicio</button>
                </div>
            `;
            mensajeDiv.style.display = "block";
        }
    });

// Buscar socio por DNI
document.getElementById('buscarSocioForm').addEventListener('submit', async function (e) {
    e.preventDefault();
    const dni = e.target.dni.value;
    const res = await fetch(`http://localhost:8080/api/socios/${dni}`);
    const resultadoDiv = document.getElementById('resultadoSocio');
    if (res.status === 200) {
        const socio = await res.json();
        resultadoDiv.innerHTML = `<div class="alert alert-success">
            <strong>Socio encontrado:</strong><br>
            DNI: ${socio.dni}<br>
            Nombre: ${socio.nombre} ${socio.apellido}<br>
            Edad: ${socio.edad}<br>
            Dirección: ${socio.direccion}<br>
            Nacimiento: ${socio.fechaNacimiento}
        </div>
            <div class="alert alert-success" role="alert">
            <button class="btn btn-primary mt-2" onclick="volverAlInicio()">Volver al inicio</button>
            </div>`;
    } else {
        resultadoDiv.innerHTML = `<div class="alert alert-danger">No se encontró el socio.</div>`;
    }
});
});

function volverAlInicio() {
    window.location.href = "index.html"; 
}