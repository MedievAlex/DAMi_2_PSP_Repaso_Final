package tiempo.psp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import tiempo.psp.model.Registro;
import tiempo.psp.service.Servicio;

@RestController
@RequestMapping("/api")
public class Controlador {
	private final Servicio servicio;

	// http://localhost:8080/api
	public Controlador(Servicio servicio) {
		this.servicio = servicio;
	}
	
	// GET http://localhost:8080/api/registro/Barcelona
	@GetMapping("/registro/{ciudad}")
	public ResponseEntity<Object> consultarRegistro(@PathVariable String ciudad) {
		if (ciudad == null || ciudad.trim().isEmpty()) {
			return ResponseEntity.badRequest().body("[ERROR] El nombre de la ciudad no puede estar vacío.");
	        //return ResponseEntity.status(400).body("[ERROR] El nombre de la ciudad no puede estar vacío.");
	        //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[ERROR] El nombre de la ciudad no puede estar vacío.");
		}
		
		try {
			Registro registro = servicio.consultarRegistro(ciudad);
			
			if (registro == null) {
				//return ResponseEntity.notFound().body("[ERROR] No se ha encontrado el registro.");
				return ResponseEntity.status(404).body("[ERROR] No se ha encontrado el registro.");
				//return ResponseEntity.status(HttpStatus.NOT_FOUND).body("[ERROR] No se ha encontrado el registro.");
			}
			
			//return ResponseEntity.ok(registro);
			return ResponseEntity.ok().body(registro);
		    //return ResponseEntity.status(200).body(registro);
		    //return ResponseEntity.status(HttpStatus.OK).body(registro);
		} catch (ResponseStatusException e) {
			return ResponseEntity.internalServerError().body("[ERROR] Al obtener el registro.");
		}
	}
	
	// DELETE http://localhost:8080/api/registro/Barcelona
	@DeleteMapping("/registro/{ciudad}")
	public ResponseEntity<String> eliminarRegistro(@PathVariable String ciudad) {
		if (ciudad == null || ciudad.trim().isEmpty()) {
			return ResponseEntity.badRequest().body("[ERROR] El nombre de la ciudad no puede estar vacío.");
	        //return ResponseEntity.status(400).body("[ERROR] El nombre de la ciudad no puede estar vacío.");
	        //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[ERROR] El nombre de la ciudad no puede estar vacío.");
		}
		
		try {
			if (!servicio.eliminarRegistro(ciudad)) {
				//return ResponseEntity.notFound().body("[ERROR] No se ha encontrado el registro.");
				return ResponseEntity.status(404).body("[ERROR] No se ha encontrado el registro.");
				//return ResponseEntity.status(HttpStatus.NOT_FOUND).body("[ERROR] No se ha encontrado el registro.");
			}

		    //return ResponseEntity.created().body("Registro eliminado correctamente");
		    return ResponseEntity.status(201).body("Registro eliminado correctamente");
		    //return ResponseEntity.status(HttpStatus.CREATED).body("Registro eliminado correctamente");
		} catch (ResponseStatusException e) {
			return ResponseEntity.internalServerError().body("[ERROR] Al borrar el registro");
		}
	}
	
	// POST http://localhost:8080/api/registro
	// NUEVO: raw JSON: { "ciudad": "Barcelona", "temperatura": 32, "humedad": 20, "estado": "NUBLADO" }
	@PostMapping("/registro")
	public ResponseEntity<Object> nuevoRegistro(@RequestBody Registro reg) {
		if (reg == null || reg.getCiudad() == null || reg.getEstado() == null || reg.getCiudad().trim().isEmpty()) {
			return ResponseEntity.badRequest().body("[ERROR] El registro debe tener todos los parametros correctos.");
			//return ResponseEntity.status(400).body("[ERROR] El registro debe tener todos los parametros correctos.");
	        //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[ERROR] El registro debe tener todos los parametros correctos.");
		}
		
		try {
			Registro registro = servicio.nuevoRegistro(reg);
			
			//return ResponseEntity.ok(registro);
			return ResponseEntity.ok().body(registro);
		    //return ResponseEntity.status(200).body(registro);
		    //return ResponseEntity.status(HttpStatus.OK).body(registro);
		} catch (ResponseStatusException e) {
			return ResponseEntity.internalServerError().body("[ERROR] Al crear el registro");
		}
	}
	
	// PUT http://localhost:8080/api/registro/Bilbao
	// MODIFICADO: raw JSON: { "ciudad": "Bilbao", "temperatura": 32, "humedad": 27, "estado": "LLUVIA" }
	@PutMapping("/registro/{ciudad}")
	public ResponseEntity<Object> actualizarRegistro(@RequestBody Registro reg, @PathVariable String ciudad) {
		if (reg == null || reg.getCiudad() == null || reg.getEstado() == null || reg.getCiudad().trim().isEmpty()) {
			return ResponseEntity.badRequest().body("[ERROR] El registro debe tener todos los parametros correctos.");
			//return ResponseEntity.status(400).body("[ERROR] El partido debe tener todos los parametros correctos.");
	        //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[ERROR] El partido debe tener todos los parametros correctos.");
		}
		
		try {
			Registro registro = servicio.actualizarRegistro(reg);
			
			if (registro == null) {
				//return ResponseEntity.notFound().body("[ERROR] No se ha encontrado el registro.");
				return ResponseEntity.status(404).body("[ERROR] No se ha encontrado el registro.");
				//return ResponseEntity.status(HttpStatus.NOT_FOUND).body("[ERROR] No se ha encontrado el registro.");
			}
			
			//return ResponseEntity.ok(registro);
			return ResponseEntity.ok().body(registro);
		    //return ResponseEntity.status(200).body(registro);
		    //return ResponseEntity.status(HttpStatus.OK).body(registro);
		} catch (ResponseStatusException e) {
			return ResponseEntity.internalServerError().body("[ERROR] Al actualizar el registro.");
		}
	}
}