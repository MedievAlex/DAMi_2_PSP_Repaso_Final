package alumnosGET.psp.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import alumnosGET.psp.model.Alumno;

@Service
public class Servicio {
	private final ObjectMapper mapper = new ObjectMapper();
	private final File archivoJSON;

	// Obtiene el JSON
	public Servicio() throws IOException {
		archivoJSON = new ClassPathResource("alumnos.json").getFile();
	}

	// Lee los registros del JSON y devuelve su contenido
	public ArrayList<Alumno> getAlumnos() {
		//Lee el JSON
		try (InputStream is = new FileInputStream(archivoJSON)) {
			// Crea los objetos y los guarda en el Array
			ArrayList<Alumno> alumnos = mapper.readValue(is, new TypeReference<ArrayList<Alumno>>() {
			});

			return alumnos;
		} catch (Exception ex) {
			return new ArrayList<>();
		}
	}

	// Guarda el contenido recibido en el JSON
	private void addAlumnos(ArrayList<Alumno> alumnos) {
		try {
			mapper.writeValue(archivoJSON, alumnos);
		} catch (Exception ex) {
			System.err.println("Error guardando alumnos: " + ex.getMessage());
		}
	}

	// Añade un registro nuevo en el JSON
	public Alumno addAlumno(Alumno alm) {
		ArrayList<Alumno> alumnos = getAlumnos();

		alumnos.add(alm);

		addAlumnos(alumnos);

		return alm;
	}

	// Actualiza un registro existente del JSON
	public Alumno updateAlumno(Alumno alm) {
		try {
			ArrayList<Alumno> alumnos = getAlumnos();

			if (alm == null) {
				return null;
			}

			for (int i = 0; i < alumnos.size(); i++) {
				Alumno alumno = alumnos.get(i);

				if (alumno.getId() == alm.getId()) {
					alumnos.set(i, alm);
					addAlumnos(alumnos);

					return alm;
				}
			}

			return null;
		} catch (Exception ex) {
			return null;
		}
	}

	// Elimina un registro existente del JSON
	public boolean deleteAlumno(int id) {
		ArrayList<Alumno> alumnos = getAlumnos();

		for (int i = 0; i < alumnos.size(); i++) {
			Alumno alumno = alumnos.get(i);

			if (alumno.getId() == id) {
				alumnos.remove(i);

				addAlumnos(alumnos);

				return true;
			}
		}

		return false;
	}

	// Verifica si un registro concreto existe en el JSON
	public boolean alumnoExists(Alumno alm) {
		ArrayList<Alumno> alumnos = getAlumnos();

		for (Alumno alumno : alumnos) {
			if (alumno.getId() == alm.getId()) {
				return true;
			}
		}

		return false;
	}

	// Busca por ID y si lo encuentra lo devuelve
	public Alumno getById(int id) {
		try {
			ArrayList<Alumno> alumnos = getAlumnos();

			for (Alumno a : alumnos) {
				if (a.getId() == id) {
					return a;
				}
			}

			return null;
		} catch (Exception e) {
			return null;
		}
	}

	// Busca por parametro y devuelve filtrado
	public ArrayList<Alumno> getByName(String nombre) {
		ArrayList<Alumno> alumnosName = new ArrayList<>();
		String nombreLower = nombre.toLowerCase();

		try {
			ArrayList<Alumno> alumnos = getAlumnos();

			for (Alumno alumno : alumnos) {
				if (alumno.getNombre().toLowerCase().contains(nombreLower)) {
					alumnosName.add(alumno);
				}
			}

			return alumnosName;
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	// Busca por parametro y devuelve el resultado filtrado
	public ArrayList<Alumno> getByCurso(String curso) {
		ArrayList<Alumno> alumnosCurso = new ArrayList<>();

		try {
			ArrayList<Alumno> alumnos = getAlumnos();

			for (Alumno alumno : alumnos) {
				if (alumno.getCurso().equalsIgnoreCase(curso)) {
					alumnosCurso.add(alumno);
				}
			}

			return alumnosCurso;
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
}
