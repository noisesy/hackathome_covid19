package org.hackathome.covid19.model;

import com.google.gson.Gson;

import java.util.List;

public class Misurazione {
	
	public Misurazione() {}
	
	public Misurazione(int id, Paziente paziente, List<Sintomi> sintomi, float temperaturaCorporea,
			String altrePatologie, String note, String data) {
		super();
		this.id = id;
		this.paziente = paziente;
		this.sintomi = sintomi;
		this.temperaturaCorporea = temperaturaCorporea;
		this.altrePatologie = altrePatologie;
		this.note = note;
		this.data = data;
	}
	
	private int id;

	private Paziente paziente;

	private List<Sintomi> sintomi;

	private float temperaturaCorporea;

	private String altrePatologie;

	private String note;

	private String data;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Paziente getPaziente() {
		return paziente;
	}
	public void setPaziente(Paziente paziente) {
		this.paziente = paziente;
	}
	public List<Sintomi> getSintomi() {
		return sintomi;
	}
	public void setSintomi(List<Sintomi> sintomi) {
		this.sintomi = sintomi;
	}
	public float getTemperaturaCorporea() {
		return temperaturaCorporea;
	}
	public void setTemperaturaCorporea(float temperaturaCorporea) {
		this.temperaturaCorporea = temperaturaCorporea;
	}
	public String getAltrePatologie() {
		return altrePatologie;
	}
	public void setAltrePatologie(String altrePatologie) {
		this.altrePatologie = altrePatologie;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
