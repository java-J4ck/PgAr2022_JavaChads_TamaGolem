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
		
			int scelta = 0;
			do {	
				System.out.println("Digita il numero dell'opzione desiderata >");	
				System.out.println("--------------------------------");
				System.out.println("1- EASY MODE");	
				System.out.println("2- MEDIUM MODE");
				System.out.println("3- HARD MODE");
				System.out.println("--------------------------------");							
				System.out.print("risposta: ");			
				scelta = Main.lettoreStdin.nextInt()-1; // METTI IL TUO LETTORE			
				
			}while(scelta  > 3 || scelta < 0);
			
			
			System.out.println("Nome del primo giocatore ");
<<<<<<< HEAD
			String nome= Main.lettoreStdin.next();
=======
			String nome = Main.LettoreStdin.next();
>>>>>>> 67b5367ade5701410cdb817133cb7443af70a224
			System.out.println("Nome del secondo giocatore ");
			String nome2 = Main.lettoreStdin.next();			
			Player p1 = new Player(nome);
			Player p2 = new Player(nome2);
			System.out.println();
			
			
			
			
			System.out.print("Difficoltà: ");						  	
			
		
		
			Equilibrio.setDifficulty(scelta);
			Equilibrio.generateEquilibrium();  // Genera l'equilibrio
		
			int scelta2;
			do {
		
			for(int i=0; i < Equilibrio.getTamaGolemNumber(); i++) {
				
				p1.addTamaGolem(new TamaGolem()); 
				p2.addTamaGolem(new TamaGolem());
				
				
		
<<<<<<< HEAD
		    }	
=======
			}	
>>>>>>> 67b5367ade5701410cdb817133cb7443af70a224
			
			Player winner = Battle.battleManager(p1, p2);			
			System.out.printf("THE WINNER IS %s", winner.getName());
			//scelta per nuova partita
			
			System.out.println("COME VUOI CONTINUARE?");
			System.out.println("--------------------------------");
			System.out.println("1- RIGIOCA CAMBIANDO EQUILIBRIO");	
			System.out.println("2- RIGIOCA CON LO STESSO EQUILIBRIO");
			System.out.println("3- ESCI");
			System.out.println("--------------------------------");
			
			scelta2 = lettoreStdin.nextInt();
			
			if(scelta2==1) {
			System.out.println("L'EQUILIBRIO DELLA PARTITA ERA: ");
			System.out.println(Equilibrio.visualizeEquilibrium()); /**/
			}
		
			}while(scelta2 != 3);
		
		
	
	}
	

}
