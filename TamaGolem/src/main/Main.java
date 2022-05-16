package main;



import java.util.Scanner;

import entities.Player;
import entities.TamaGolem;
import utilities.Battle;
//import utilities.Equilibrio;
import utilities.Equilibrio;


public class Main {

	//public static final String LettoreStdin = null;
	public static  Scanner LettoreStdin = new Scanner(System.in); 

	public static void main(String[] args) {
		
		
		
		System.out.println("--------------------------------");
		System.out.println("TAMAGOLEM");
		System.out.println("--------------------------------");
		System.out.println();
		
		int scegli;
	  		//stampaMenu();
			int scelta = 0;
			do {	
				System.out.println("Digita il numero dell'opzione desiderata >");	
				System.out.println("--------------------------------");
				System.out.println("1- EASY MODE");	
				System.out.println("2- MEDIUM MODE");
				System.out.println("3- HARD MODE");
				System.out.println("--------------------------------");							
				System.out.print("risposta: ");			
				scelta = Main.LettoreStdin.nextInt()-1; // METTI IL TUO LETTORE			
				
			}while(scelta  > 3 || scelta < 0);
			
			
			System.out.println("Nome del primo giocatore ");
			String nome= Main.LettoreStdin.next();
			System.out.println("Nome del secondo giocatore ");
			String nome2 = Main.LettoreStdin.next();			
			Player p1 = new Player(nome);
			Player p2 = new Player(nome2);
			System.out.println();
			
			
			
			
			System.out.print("Diffficoltà: ");						  	
			System.out.println();
		
		
		
			Equilibrio.setDifficulty(scelta);
			Equilibrio.generateEquilibrium();  // Genera l'equilibrio
		
		
		
		
			for(int i=0; i < Equilibrio.getElementNumber(); i++) {
				
				p1.addTamaGolem(new TamaGolem()); 
				p2.addTamaGolem(new TamaGolem());
				
				
		
		}	
			
			Player winner = Battle.battleManager(p1, p2);			
			System.out.printf("THE WINNER IS %s", winner.getName());
		
		
		
		
		
	
	}
	

}
