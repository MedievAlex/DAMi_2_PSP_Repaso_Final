package empleados.psp.service;

import empleados.psp.model.Empleado;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

@Service
public class Servicio {
	// Para modificar JSON
	private final ObjectMapper mapper = new ObjectMapper();
	private final File archivoJSON;

	// Obtiene el JSON
	public Servicio() throws IOException {
		archivoJSON = new ClassPathResource("data/employees.json").getFile();
		mapper.registerModule(new JavaTimeModule());
	}

	// Lee los registros del JSON y devuelve su contenido
	public ArrayList<Empleado> getEmpleados() {
		//Lee el JSON
	    try (InputStream is = new FileInputStream(archivoJSON)) {
			// Crea los objetos y los guarda en el Array
	        ArrayList<Empleado> empleados = mapper.readValue(is, new TypeReference<ArrayList<Empleado>>() {});
	        
	        return empleados;
	    } catch (Exception ex) {
	    	return new ArrayList<>();
	    }
	}

	// Guarda el contenido recibido en el JSON
	private void addEmpleados(ArrayList<Empleado> empleados) {
		try {
			mapper.writeValue(archivoJSON, empleados);
		} catch (Exception ex) {
			System.err.println("Error guardando empleados: " + ex.getMessage());
		}
	}

	// Añade un registro nuevo en el JSON
	public Empleado addEmpleado(Empleado empleado) {
		ArrayList<Empleado> empleados = getEmpleados();

		empleados.add(empleado);

		addEmpleados(empleados);

		return empleado;
	}

	// Actualiza un registro existente del JSON
	public Empleado updateEmpleado(Empleado empleado) {
		try {
			ArrayList<Empleado> empleados = getEmpleados();

			if (empleado == null || empleado.getId().equals(null)) {
				return null;
			}

			for (int i = 0; i < empleados.size(); i++) {
				Empleado emp = empleados.get(i);

				if (emp.getId().equals(empleado.getId())) {
					empleados.set(i, empleado);

					addEmpleados(empleados);

					return empleado;
				}
			}

			return null;
		} catch (Exception ex) {
			return null;
		}
	}

	// Elimina un registro existente del JSON
	public boolean deleteEmpleado(Long id) {
		ArrayList<Empleado> empleados = getEmpleados();

		for (int i = 0; i < empleados.size(); i++) {
			Empleado emp = empleados.get(i);

			if (emp.getId().equals(id)) {
				empleados.remove(i);

				addEmpleados(empleados);

				return true;
			}
		}

		return false;
	}

	// Verifica si un registro concreto existe en el JSON
	public boolean employeeExists(Empleado emp) {
			ArrayList<Empleado> empleados = getEmpleados();
			
			for (Empleado empleado : empleados) {
				if (empleado.getId() == emp.getId()) {
					return true;
				}
			}

			return false;
	}
	
	// Busca por ID y si lo encuentra lo devuelve
	public Empleado getById(Long id) {
		ArrayList<Empleado> empleados = getEmpleados();

		for (Empleado empleado : empleados) {
			if (empleado.getId().equals(id))
				return empleado;
		}

		return null;
	}

	// Busca por parametro y devuelve filtrado
	public Empleado getByName(String name, String lastname) {
		ArrayList<Empleado> empleados = getEmpleados();

		for (Empleado empleado : empleados) {
			if (empleado.getFirstName().equalsIgnoreCase(name) && empleado.getLastName().equalsIgnoreCase(lastname)) {
				return empleado;
			}
		}
		return null;
	}
}
