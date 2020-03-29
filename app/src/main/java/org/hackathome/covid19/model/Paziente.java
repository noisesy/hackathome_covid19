package org.hackathome.covid19.model;

import com.google.gson.Gson;

public class Paziente {
	
	public Paziente() {}
	
	public Paziente(String codiceFiscale, String nome, String cognome) {
		super();
		this.codiceFiscale = codiceFiscale;
		this.nome = nome;
		this.cognome = cognome;
	}

	private String codiceFiscale;

	private String nome;

	private String cognome;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
