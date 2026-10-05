package tiempo.psp.model;

public class Registro {
	private String ciudad;
	private int temperatura;
	private int humedad;
	private String estado;
	
	public Registro() {
		
	}
	
	public Registro(String ciudad, int temperatura, int humedad, String estado) {
		this.ciudad = ciudad;
		this.temperatura = temperatura;
		this.humedad = humedad;
		this.estado = estado;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(int temperatura) {
		this.temperatura = temperatura;
	}

	public int getHumedad() {
		return humedad;
	}

	public void setHumedad(int humedad) {
		this.humedad = humedad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Registro [ciudad=" + ciudad + ", temperatura=" + temperatura + "C, humedad=" + humedad + "%, estado="
				+ estado + "]";
	}
}
