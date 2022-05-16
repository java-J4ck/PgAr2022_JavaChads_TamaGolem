package main;



import java.util.Scanner;

import entities.Player;
import entities.TamaGolem;
import utilities.Battle;
//import utilities.Equilibrio;
import utilities.Equilibrio;


public class Main {

	//public static final String lettoreStdin = null;
	public static  Scanner lettoreStdin = new Scanner(System.in); 

	
	public static void main(String[] args) {
		
		
		
		System.out.println("--------------------------------");
		System.out.println("TAMAGOLEM");
		System.out.println("--------------------------------");
		System.out.println();
		
		Player p1 = new Player("Andrea");
		Player p2 = new Player("Giulia");
		
		p1.addTamaGolem(new TamaGolem());
		p1.addTamaGolem(new TamaGolem());
		p1.addTamaGolem(new TamaGolem());
		
		p2.addTamaGolem(new TamaGolem());
		p2.addTamaGolem(new TamaGolem());
		p2.addTamaGolem(new TamaGolem());
		
		Equilibrio.setDifficulty(1);
		Equilibrio.generateEquilibrium();
		
		Player winner = Battle.battleManager(p1, p2);
		
		System.out.printf("\t\nTHE WINNER IS: %s\n", winner.getName());
		
	
	}
	

}
