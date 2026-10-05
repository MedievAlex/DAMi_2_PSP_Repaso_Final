package resultadosPartidos.psp.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import resultadosPartidos.psp.model.Partido;

@Service
public class Servicio {
	private ArrayList<Partido> partidos = new ArrayList<Partido>();

	@PostConstruct
	public void init() {
		cargarPartidos();
	}

	private void cargarPartidos() {
		partidos.add(new Partido("Barcelona", "Real Madrid", "5-0"));
		partidos.add(new Partido("Athletic", "Real Sociedad", "2-1"));
		partidos.add(new Partido("Getafe", "Espanyol", "0-0"));
	}
	
	public Partido getPartido(String local) {
		try {
			for(Partido p : partidos) {
				if (p.getEquipoLocal().equalsIgnoreCase(local)) {
					return p;
				}
			}
			
			return null;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public boolean deletePartido(String local) {
		int id = -1;
		
		try {
			for(int i = 0; i < partidos.size(); i++) {
				if (partidos.get(i).getEquipoLocal().equalsIgnoreCase(local)) {
					id = i;
					break;
				}
			}
			
			if (id != -1) {
				partidos.remove(id);
				return true;
			}
			
			return false;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public Partido addPartido(Partido partido) {
		try {
			partidos.add(partido);
			
			return partido;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public Partido updatePartido(Partido partido) {
		try {
			Partido p = getPartido(partido.getEquipoLocal());
			
			if (p == null) {
				return null;
			}
			
			p.setEquipoLocal(partido.getEquipoLocal());
			p.setEquipoVisitante(partido.getEquipoVisitante());
			p.setResultado(partido.getResultado());
			
			return p;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}