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
	private final static String[] DIFFICULTY_OPTION= {"EASY MODE","MEDIUM MODE","HARD MODE"};
	private final static String[] ENDGAME_OPTION= {"RIGIOCA CAMBIANDO EQUILIBRIO","RIGIOCA CON LO STESSO EQUILIBRIO","ESCI"};
	
	public static void main(String[] args) {
		
		
		MyMenu difficultyMenu= new MyMenu("select the difficulty mode",DIFFICULTY_OPTION);
		MyMenu endGameMenu= new MyMenu("how do you want to continue",ENDGAME_OPTION);
		
		System.out.println("--------------------------------");
		System.out.println("TAMAGOLEM");
		System.out.println("--------------------------------");
		System.out.println();
	
			
		System.out.println("Nome del primo giocatore ");
		Player p1 = new Player(lettoreStdin.next());
		
		System.out.println("Nome del secondo giocatore ");
		Player p2 = new Player(lettoreStdin.next());
		
		int scelta,scelta2;
		do {
		
			scelta=difficultyMenu.scegli();
			if(scelta==0) break;
			Equilibrio.setDifficulty(scelta);
			Equilibrio.generateEquilibrium();  // Genera l'equilibrio
			
			for(int i=0; i < Equilibrio.getTamaGolemNumber(); i++) {
				p1.addTamaGolem(new TamaGolem()); 
				p2.addTamaGolem(new TamaGolem());
		   }	
	
					
	
				
			Player winner = Battle.battleManager(p1, p2);
				
			System.out.printf("THE WINNER IS %s", winner.getName());
				
			
				
			//scelta per nuova partita
						
			scelta2 = endGameMenu.scegli();
			if(scelta2==1) {
				System.out.println("L'EQUILIBRIO DELLA PARTITA ERA: ");
				System.out.println(Equilibrio.visualizeEquilibrium()); /**/
			}
			
		}while(scelta2 != 3);
		
		if(scelta!=0) {
			System.out.println("L'EQUILIBRIO DELLA PARTITA ERA: ");
			System.out.println(Equilibrio.visualizeEquilibrium()); /**/
		}
		
		System.out.println("GRAZIE PER AVER GIOCATO");
		
		
	
	}
	

}
