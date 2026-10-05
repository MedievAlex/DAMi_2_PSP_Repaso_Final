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

public class Deportivo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String matricula;
	private String marca;
	private String modelo;
	private transient double deposito;
	private Propietario propietario;
	
	public Deportivo() {
		this.matricula = "";
		this.marca = "";
		this.modelo = "";
		this.deposito = 50;
		this.propietario = new Propietario();
	}
	
	public Deportivo(String matricula, String marca, String modelo, double depósito) {
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.deposito = depósito;
		this.propietario = new Propietario();
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public double getDepósito() {
		return deposito;
	}

	public void setDepósito(double depósito) {
		this.deposito = depósito;
	}

	public Propietario getPropietario() {
		return propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}

	@Override
	public String toString() {
		return "Deportivo [matricula=" + matricula + ", marca=" + marca + ", modelo="
				+ modelo + ", depósito=" + deposito + ", propietario=" + propietario.toString() + "]";
	}
}
