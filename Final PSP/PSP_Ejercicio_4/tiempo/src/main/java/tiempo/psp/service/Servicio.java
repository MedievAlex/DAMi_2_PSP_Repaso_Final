package tiempo.psp.service;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.annotation.PostConstruct;
import tiempo.psp.model.Registro;

@Service
public class Servicio {
	private ArrayList<Registro> Registros = new ArrayList<Registro>();

	@PostConstruct
	public void init() {
		fillData();
	}

	private void fillData() {
		Registros.add(new Registro("Bilbo", 28, 15, "SOLEADO"));
		Registros.add(new Registro("Elgoibar", 25, 37, "NUBLADO"));
		Registros.add(new Registro("Sofia", 4, 60, "LLUVIA"));
	}
	
	// GET http://localhost:8080/api/registro/Barcelona
	public Registro consultarRegistro(String ciudad) {
		try {
			for(Registro reg : Registros) {
				if (reg.getCiudad().equalsIgnoreCase(ciudad)) {
					return reg;
				}
			}
			
			return null;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// DELETE http://localhost:8080/api/registro/Barcelona
	public boolean eliminarRegistro(String ciudad) {
		int id = -1;
		
		try {
			for(int i = 0; i < Registros.size(); i++) {
				if (Registros.get(i).getCiudad().equalsIgnoreCase(ciudad)) {
					id = i;
					break;
				}
			}
			
			if (id != -1) {
				Registros.remove(id);
				return true;
			}
			
			return false;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// POST http://localhost:8080/api/registro
	public Registro nuevoRegistro(Registro registro) {
		try {
			Registros.add(registro);
			
			return registro;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// PUT http://localhost:8080/api/Barcelona
	public Registro actualizarRegistro(Registro registro) {
		Registro reg = null;
		
		try {
			for(Registro r : Registros) {
				if (r.getCiudad().equalsIgnoreCase(registro.getCiudad())) {
					reg = r;
				}
			}

			if (reg == null) {
				return null;
			}
			
			reg.setCiudad(registro.getCiudad());
			reg.setTemperatura(registro.getTemperatura());
			reg.setHumedad(registro.getHumedad());
			reg.setEstado(registro.getEstado());
			
			return reg;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}