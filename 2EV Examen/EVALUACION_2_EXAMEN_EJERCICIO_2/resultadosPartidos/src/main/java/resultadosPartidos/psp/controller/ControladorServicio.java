package resultadosPartidos.psp.controller;

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

import resultadosPartidos.psp.model.Partido;
import resultadosPartidos.psp.service.Servicio;

@RestController
@RequestMapping("/api")
public class ControladorServicio {
	private final Servicio servicio;

	// http://localhost:8080/api
	public ControladorServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	
	// GET http://localhost:8080/api/partido/Barcelona
	@GetMapping("/partido/{local}")
	public ResponseEntity<Object> getPartido(@PathVariable String local) {
		if (local == null || local.trim().isEmpty()) {
			return ResponseEntity.badRequest().body("[ERROR] El nombre del equipo local no puede estar vacío.");
	        //return ResponseEntity.status(400).body("[ERROR] El nombre del equipo local no puede estar vacío.");
	        //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[ERROR] El nombre del equipo local no puede estar vacío.");
		}
		
		try {
			Partido partido = servicio.getPartido(local);
			
			if (partido == null) {
				//return ResponseEntity.notFound().body("[ERROR] No se ha encontrado el partido.");
				return ResponseEntity.status(404).body("[ERROR] No se ha encontrado el partido.");
				//return ResponseEntity.status(HttpStatus.NOT_FOUND).body("[ERROR] No se ha encontrado el partido.");
			}
			
			//return ResponseEntity.ok(partido);
			return ResponseEntity.ok().body(partido);
		    //return ResponseEntity.status(200).body(partido);
		    //return ResponseEntity.status(HttpStatus.OK).body(partido);
		} catch (ResponseStatusException e) {
			return ResponseEntity.internalServerError().body("[ERROR] Al obtener el partido.");
		}
	}
	
	// DELETE http://localhost:8080/api/partido/Barcelona
	@DeleteMapping("/partido/{local}")
	public ResponseEntity<String> deletePartido(@PathVariable String local) {
		if (local == null || local.trim().isEmpty()) {
			return ResponseEntity.badRequest().body("[ERROR] El nombre del equipo local no puede estar vacío.");
	        //return ResponseEntity.status(400).body("[ERROR] El nombre del equipo local no puede estar vacío.");
	        //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[ERROR] El nombre del equipo local no puede estar vacío.");
		}
		
		try {
			if (!servicio.deletePartido(local)) {
				//return ResponseEntity.notFound().body("[ERROR] No se ha encontrado el partido.");
				return ResponseEntity.status(404).body("[ERROR] No se ha encontrado el partido.");
				//return ResponseEntity.status(HttpStatus.NOT_FOUND).body("[ERROR] No se ha encontrado el partido.");	
			}

		    //return ResponseEntity.created().body("Partido eliminado correctamente");
		    return ResponseEntity.status(201).body("Partido eliminado correctamente");
		    //return ResponseEntity.status(HttpStatus.CREATED).body("Partido eliminado correctamente");
		} catch (ResponseStatusException e) {
			return ResponseEntity.internalServerError().body("[ERROR] Al borrar el partido");
		}
	}
	
	// POST http://localhost:8080/api/partido
	// NUEVO: raw JSON: { "equipoLocal": "Barcelona", "equipoVisitante": "Athletic", "resultado": "2-2" }
	@PostMapping("/partido")
	public ResponseEntity<Object> addPartido(@RequestBody Partido par) {
		if (par == null || par.getEquipoLocal() == null || par.getEquipoVisitante() == null || par.getResultado() == null || par.getEquipoLocal().trim().isEmpty() || par.getEquipoVisitante().trim().isEmpty() || par.getResultado().trim().isEmpty()) {
			return ResponseEntity.badRequest().body("[ERROR] El partido debe tener todos los parametros correctos.");
			//return ResponseEntity.status(400).body("[ERROR] El partido debe tener todos los parametros correctos.");
	        //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[ERROR] El partido debe tener todos los parametros correctos.");
		}
		
		try {
			Partido partido = servicio.addPartido(par);
			
			//return ResponseEntity.ok(partido);
			return ResponseEntity.ok().body(partido);
		    //return ResponseEntity.status(200).body(partido);
		    //return ResponseEntity.status(HttpStatus.OK).body(partido);
		} catch (ResponseStatusException e) {
			return ResponseEntity.internalServerError().body("[ERROR] Al crear el partido");
		}
	}
	
	@PutMapping("/partido/{local}")
	public ResponseEntity<Object> updatePartido(@RequestBody Partido par, @PathVariable String local) {
		if (par == null || par.getEquipoLocal() == null || par.getEquipoVisitante() == null || par.getResultado() == null || par.getEquipoLocal().trim().isEmpty() || par.getEquipoVisitante().trim().isEmpty() || par.getResultado().trim().isEmpty()) {
			return ResponseEntity.badRequest().body("[ERROR] El partido debe tener todos los parametros correctos.");
			//return ResponseEntity.status(400).body("[ERROR] El partido debe tener todos los parametros correctos.");
	        //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[ERROR] El partido debe tener todos los parametros correctos.");
		}
		
		try {
			Partido partido = servicio.updatePartido(par);
			
			if (partido == null) {
				//return ResponseEntity.notFound().body("[ERROR] No se ha encontrado el partido.");
				return ResponseEntity.status(404).body("[ERROR] No se ha encontrado el partido.");
				//return ResponseEntity.status(HttpStatus.NOT_FOUND).body("[ERROR] No se ha encontrado el partido.");
			}
			
			//return ResponseEntity.ok(partido);
			return ResponseEntity.ok().body(partido);
		    //return ResponseEntity.status(200).body(partido);
		    //return ResponseEntity.status(HttpStatus.OK).body(partido);
		} catch (ResponseStatusException e) {
			return ResponseEntity.internalServerError().body("[ERROR] Al actualizar el partido.");
		}
	}
}