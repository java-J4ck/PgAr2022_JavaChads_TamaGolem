package main;

import entities.Player;
import entities.TamaGolem;
import utilities.Battle;


public class Main {

	public static void main(String[] args) {
		
		
		Player p1 = new Player("Bare");
		Player p2 = new Player("Pintas");
		
		p1.addTamaGolem(new TamaGolem());   
		p1.addTamaGolem(new TamaGolem());
		p1.addTamaGolem(new TamaGolem());
		
		
		p2.addTamaGolem(new TamaGolem());
		p2.addTamaGolem(new TamaGolem());
		p2.addTamaGolem(new TamaGolem());
		
		
		Player winner = Battle.battleManager(p1, p2);
		
		System.out.printf("THE WINNER IS %s", winner.getName());
		
		
	}

}
