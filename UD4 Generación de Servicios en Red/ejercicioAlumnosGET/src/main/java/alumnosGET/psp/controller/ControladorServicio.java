package alumnosGET.psp.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import alumnosGET.psp.model.Alumno;
import alumnosGET.psp.service.*;

@RestController
@RequestMapping("/alumnos")
public class ControladorServicio {
	private final Servicio servicio;

	// http://localhost:8080/alumnos
	public ControladorServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	// GET http://localhost:8080/alumnos
	@GetMapping
	public ResponseEntity<ArrayList<Alumno>> getAll() {
		return ResponseEntity.ok(servicio.getAlumnos());
		//return ResponseEntity.status(200).body(servicio.getAlumnos());
		//return ResponseEntity.status(HttpStatus.OK).body(servicio.getAlumnos());
	}

	// GET http://localhost:8080/alumnos/1
	@GetMapping("/{id}")
	public ResponseEntity<Alumno> getById(@PathVariable int id) {
		Alumno alumno = servicio.getById(id);
		
		return alumno == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(alumno);
		//return alumno == null ? ResponseEntity.status(404).build() : ResponseEntity.status(200).body(alumno);
		//return alumno == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.status(HttpStatus.OK).body(alumno);	
	}

	// GET http://localhost:8080/alumnos/curso/DAM2
	@GetMapping("/curso/{curso}")
	public ResponseEntity<ArrayList<Alumno>> getByCurso(@PathVariable String curso) {
		ArrayList<Alumno> alumnos = servicio.getByCurso(curso);
		
		return alumnos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(alumnos);
		//return alumnos.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(alumnos);
		//return alumnos.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() : ResponseEntity.status(HttpStatus.OK).body(alumnos);	
	}
	
	// GET http://localhost:8080/alumnos/count
	@GetMapping("/count")
	public ResponseEntity<HashMap<String, Integer>> getCount() {
	    HashMap<String, Integer> response = new HashMap<>();
	    response.put("total", servicio.getAlumnos().size());
	    
	    return ResponseEntity.ok(response);
	    //return ResponseEntity.status(200).body(response);
	    //return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	// GET http://localhost:8080/alumnos/buscar?nombre=Alex
	@GetMapping("/buscar")
	public ResponseEntity<ArrayList<Alumno>> getByName(@RequestParam String nombre) {
		ArrayList<Alumno> alumnos = servicio.getByName(nombre);
		
		return alumnos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(alumnos);
		//return alumnos.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(alumnos);
		//return alumnos.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() : ResponseEntity.status(HttpStatus.OK).body(alumnos);	
	}

	// POST http://localhost:8080/alumnos
	// EXISTENTE: raw JSON: { "id": 2, "nombre": "Iker", "curso": "DAM2" }
	// NUEVO: raw JSON: { "id": 9, "nombre": "Jurdana", "curso": "CAP" }
	@PostMapping
	public ResponseEntity<Alumno> postAlumnos(@RequestBody Alumno alm) {
		if (alm == null) {
	        return ResponseEntity.badRequest().build();
	        //return ResponseEntity.status(400).build();
	        //return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	    }
		
		if (servicio.alumnoExists(alm)) {
	        //return ResponseEntity.status(409).build();
	        return ResponseEntity.status(HttpStatus.CONFLICT).build();
	    }
		
		Alumno alumno = servicio.addAlumno(alm);

	    //return ResponseEntity.created().body(alumno);
	    //return ResponseEntity.status(201).body(alumno);
	    return ResponseEntity.status(HttpStatus.CREATED).body(alumno);
	}
	
	// PUT http://localhost:8080/alumnos/1
	// EXISTENTE: raw JSON: { "id": 2, "nombre": "Iker", "curso": "DAM2" }
	// NUEVO: raw JSON: { "id": 9, "nombre": "Jurdana", "curso": "CAP" }
	@PutMapping("/{id}")
	public ResponseEntity<Alumno> putAlumno(@PathVariable int id, @RequestBody Alumno alm) {
		if (alm.getId() != id) {
	        return ResponseEntity.badRequest().build();
	        //return ResponseEntity.status(400).build();
	        //return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	    }
		
		Alumno actualizado = servicio.updateAlumno(alm);

	    return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
		//return actualizado != null ? ResponseEntity.status(200).body(actualizado) : ResponseEntity.status(404).build();
		//return actualizado != null ? ResponseEntity.status(HttpStatus.OK).body(actualizado) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
	}
	
	// DELETE http://localhost:8080/alumnos/2
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAlumno(@PathVariable int id) {
	    return servicio.deleteAlumno(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
		//return servicio.deleteAlumno(id) ? ResponseEntity.status(204).build() : ResponseEntity.status(404).build();
		//return servicio.deleteAlumno(id) ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
	}
}
