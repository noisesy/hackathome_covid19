package org.hackathome.covid19.model;

import com.google.gson.Gson;

public class Sintomi {
	
	public Sintomi() {}
	
	public Sintomi(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	private int id;

	private String nome;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
