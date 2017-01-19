package com.ejt.endereco;

public class Endereco {
	private String endereco;
	private String longitude;
	private String latitude;
	
	public Endereco(String logradouro, String numero, String bairro) {
		this.endereco = logradouro;
		this.longitude = numero;
		this.latitude = bairro;
	}
	
	public Endereco(){
		
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String logradouro) {
		this.endereco = logradouro;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String numero) {
		this.longitude = numero;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String bairro) {
		this.latitude = bairro;
	}

	@Override
	public String toString() {
		return "Endereco [logradouro=" + endereco + ", numero=" + longitude + ", bairro=" + latitude + "]";
	}
	
	
}
