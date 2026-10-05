package empleados.psp.controller;

import empleados.psp.model.Empleado;
import empleados.psp.service.Servicio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class ControladorServicio {

	private final Servicio service;

	// http://localhost:8080/api/employees
	public ControladorServicio(Servicio service) {
		this.service = service;
	}

	// GET http://localhost:8080/api/employees
	@GetMapping
	public ResponseEntity<List<Empleado>> getAll() {
		return ResponseEntity.ok(service.getEmpleados());
		//return ResponseEntity.status(200).body(service.getEmpleados());
		//return ResponseEntity.status(HttpStatus.OK).body(service.getEmpleados());
	}

	// GET http://localhost:8080/api/employees/1
	@GetMapping("/{id}")
	public ResponseEntity<Empleado> getById(@PathVariable Long id) {
		if (id == null) {
			return ResponseEntity.badRequest().build();
			//return ResponseEntity.status(400).build();
			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		Empleado e = service.getById(id);

		return e != null ? ResponseEntity.ok(e) : ResponseEntity.notFound().build();
		//return e != null ? ResponseEntity.status(200).body(e) : ResponseEntity.status(404).build();
		//return e != null ? ResponseEntity.status(HttpStatus.OK).body(e) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	// POST http://localhost:8080/api/employees
	// EXISTENTE: raw JSON: {"id":1, "firstName": "Ana", "lastName": "García", "position": "Desarrolladora Backend", "salary": 3200, "hireDate": "2025-11-27"}
	// NUEVO: raw JSON: {"id":2, "firstName": "Jurdana", "lastName": "Hierbas", "position": "Profesora de Ingles", "salary": 2200, "hireDate": "2026-09-15"}
	@PostMapping
	public ResponseEntity<Empleado> postEmpleado(@RequestBody Empleado emp) {
		if (emp == null) {
			return ResponseEntity.badRequest().build();
			//return ResponseEntity.status(400).build();
			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		if (service.employeeExists(emp)) {
	        //return ResponseEntity.status(409).build();
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

		Empleado empleado = service.addEmpleado(emp);

	    //return ResponseEntity.created().body(empleado);
	    //return ResponseEntity.status(201).body(empleado);
		return ResponseEntity.status(HttpStatus.CREATED).body(empleado);
	}

	// GET http://localhost:8080/api/employees/search?name=Ana&lastname=García
	@GetMapping("/search")
	public ResponseEntity<Empleado> getByName(@RequestParam String name, @RequestParam String lastname) {
		Empleado e = service.getByName(name, lastname);

		return e != null ? ResponseEntity.ok(e) : ResponseEntity.notFound().build();
		//return e != null ? ResponseEntity.status(200).body(e) : ResponseEntity.status(404).build();
		//return e != null ? ResponseEntity.status(HttpStatus.OK).body(e) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	// PUT http://localhost:8080/api/employees/1
	// EXISTENTE: raw JSON: {"id":1, "firstName": "Ana", "lastName": "García", "position": "Desarrolladora Backend", "salary": 3200, "hireDate": "2025-11-27"}
	// NUEVO: raw JSON: {"id":2, "firstName": "Jurdana", "lastName": "Hierbas", "position": "Profesora de Ingles", "salary": 2200, "hireDate": "2026-09-15"}
	@PutMapping("/{id}")
	public ResponseEntity<Empleado> putEmployee(@PathVariable Long id, @RequestBody Empleado e) {
		if (e.getId() != null && !e.getId().equals(id)) {
			return ResponseEntity.badRequest().build();
			//return ResponseEntity.status(400).build();
			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		Empleado actualizado = service.updateEmpleado(e);

		return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
		//return actualizado != null ? ResponseEntity.status(200).body(actualizado) : ResponseEntity.status(404).build();
		//return actualizado != null ? ResponseEntity.status(HttpStatus.OK).body(actualizado) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	// DELETE http://localhost:8080/api/employees/1
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		if (id == null) {
			return ResponseEntity.badRequest().build();
			//return ResponseEntity.status(400).build();
			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		return service.deleteEmpleado(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
		//return servicio.deleteAlumno(id) ? ResponseEntity.status(204).build() : ResponseEntity.status(404).build();
		//return servicio.deleteAlumno(id) ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
