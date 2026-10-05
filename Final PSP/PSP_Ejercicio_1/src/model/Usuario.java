package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nombre;
	private int edad;
	private transient Timestamp tiempoConexion;

	public Usuario(String nombre, int edad) {
		this.nombre = nombre;
		this.edad = edad;
	}

	public void TiempoDeConexion() {
		tiempoConexion = new Timestamp(System.currentTimeMillis());
		System.out.println("Tiempo de conexión: " + tiempoConexion);
	}

	public void Print() {
		System.out.println("- Usuario " + nombre + " con " + edad + " años.");
	}
	
	@Override
	public String toString() {
		return nombre + " [edad=" + edad + ", tiempoConexion=" + tiempoConexion + "]";
	}
}