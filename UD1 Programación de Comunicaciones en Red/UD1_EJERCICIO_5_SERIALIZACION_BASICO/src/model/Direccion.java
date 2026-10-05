package model;

import java.io.Serializable;

/**
Se desea desarrollar una aplicación cliente-servidor en Java que permita 
enviar y recibir objetos a través de sockets TCP.
	1. Define una clase llamada Persona que tenga los siguientes atributos:
    	• String nombre.
    	• int edad.
    	• String contraseña (este atributo no debe transmitirse al enviar 
    	el objeto).
    2. Implementa un servidor TCP que:
    	• Espere conexiones de un cliente.
    	• Reciba un objeto Persona enviado desde el cliente.
    	• Muestre por consola los datos recibidos, comprobando que uno de 
    	los atributos no ha sido transmitido.
    3. Implementa un cliente TCP que:
    	• Cree un objeto Persona con datos reales (incluyendo nombre, edad 
    	y una contraseña).
    	• Envíe dicho objeto al servidor.
    4. Verifica que, al recibir el objeto en el servidor, los atributos 
    llegan correctamente salvo el que no debe transmitirse.
    5. Si el cliente y el servidor se ejecutan en máquinas diferentes, ¿qué 
    debes tener en cuenta respecto a la clase Persona para que la transmisión 
    de objetos funcione correctamente en ambos lados de la aplicación?
    6. Ahora quieres añadir a la clase Persona un atributo de tipo Dirección. 
    La clase Dirección está definida de la siguiente forma:
    	• String calle.
    	• String ciudad.
    	• int codigoPostal.

El cliente crea una Persona con su correspondiente dirección y la envía al servidor.
Sin embargo, en tiempo de ejecución se lanza la excepción:
java.io.NotSerializableException: Direccion
Prueba y responde a las siguientes preguntas:
    1. ¿Por qué ocurre este error?
    2. ¿Qué modificación habría que hacer en la clase Direccion para que el 
    objeto Persona pueda enviarse correctamente?
    3. ¿Qué pasaría si la clase Direccion tuviera dentro otro objeto como atributo? 
**/

public class Direccion implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String calle;
	private String ciudad;
	private int codigoPostal;
	  
	public Direccion() {
		this.calle = "";
		this.ciudad = "";
		this.codigoPostal = 00000;
	}
	  
	public Direccion(String calle, String ciudad, int codigoPostal) {
		super();
		this.calle = calle;
		this.ciudad = ciudad;
		this.codigoPostal = codigoPostal;
	}
	
	public String getCalle() {
		return calle;
	}
	
	public void setCalle(String calle) {
		this.calle = calle;
	}
	
	public String getCiudad() {
		return ciudad;
	}
	
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	public int getCodigoPostal() {
		return codigoPostal;
	}
	
	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	@Override
	public String toString() {
		return "Direccion [calle=" + calle + ", ciudad=" + ciudad + ", codigoPostal=" + codigoPostal + "]";
	}
}
