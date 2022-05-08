package entities;

import java.util.ArrayList;

public class Player {
	
	private String name;   // Nome del giocatore
	private ArrayList<TamaGolem> tamagolems = new ArrayList<TamaGolem>();   // Tamagolems del player 
	
	
	public Player(String name) {
		this.name = name;
	}
	
	
	public void addTamaGolem(TamaGolem t) {
		this.tamagolems.add(t);
	}
	
	
	public void removeTamaGolem(TamaGolem t) {
		this.tamagolems.remove(t);
	}
	
	
	
	
}
