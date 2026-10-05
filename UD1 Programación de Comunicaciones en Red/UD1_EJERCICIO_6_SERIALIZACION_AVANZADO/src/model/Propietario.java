package model;

import java.io.Serializable;

/**
Crea una aplicación que pida al usuario y luego almacene datos en 
un fichero sobre un deportivo. Sobre el deportivo preguntaremos al 
usuario sobre los siguientes datos:
    • String matricula.
    • String marca.
    • double depósito.
    • String modelo.
Esta información se almacena primero en una clase llamada Deportivo.
Aparte de la clase anterior también existirá otra clase, llamada 
Propietario, con dos atributos: el nombre y teléfono del propietario.
También le pediremos al usuario que nos proporcione estos datos.
Suponemos que cada deportivo solo tiene un propietario y cada 
propietario un solo deportivo. 
En el fichero guardaremos únicamente la siguiente información:
    • String matricula.
    • String marca.
    • String modelo.
    • String propietario.
Después de cerrar el fichero lo abriremos de nuevo en modo lectura y 
mostraremos su contenido por pantalla.
¿Qué pasa cuando le pides que muestre el tamaño del depósito 
(excepción, error...), que no está guardado en el fichero?
Reescribe el programa anterior suponiendo que añadimos dos nuevos 
atributos a la clase Propietario:
    • int edad.
    • String paisNacimiento.
sabiendo que la edad queremos guardarlo en el fichero, pero el país.
¿Qué ocurre si el orden en que escribes el nombre y la edad (writeObject) 
no coincide con el orden de lectura (readObject)?
**/

public class Propietario implements Serializable {
	private static final long serialVersionUID = 1L;

    private String nombre;
    private int telefono;
    private int edad;
    private String paisNacimiento;
    
	public Propietario() {
		this.nombre = "";
		this.telefono = 666666666;
		this.edad = 25;
		this.paisNacimiento = "";
	}

	public Propietario(String nombre, int telefono, int edad, String paisNacimiento) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.edad = edad;
		this.paisNacimiento = paisNacimiento;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getPaisNacimiento() {
		return paisNacimiento;
	}

	public void setPaisNacimiento(String paisNacimiento) {
		this.paisNacimiento = paisNacimiento;
	}

	@Override
	public String toString() {
		return "Propietario [nombre=" + nombre + ", telefono=" + telefono + ", edad=" + edad + ", paisNacimiento="
				+ paisNacimiento + "]";
	}
}
