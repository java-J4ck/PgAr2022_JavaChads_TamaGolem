
package main;



import java.util.Scanner;

import entities.Player;
import entities.TamaGolem;
import utilities.Battle;
//import utilities.Equilibrio;
import utilities.Equilibrio;


public class Main {
	private static final String TITLE="\r\n"
			+ " _____ ___  ___  ___  ___  _____ _____ _      ________  ___\r\n"
			+ "|_   _/ _ \\ |  \\/  | / _ \\|  __ \\  _  | |    |  ___|  \\/  |\r\n"
			+ "  | |/ /_\\ \\| .  . |/ /_\\ \\ |  \\/ | | | |    | |__ | .  . |\r\n"
			+ "  | ||  _  || |\\/| ||  _  | | __| | | | |    |  __|| |\\/| |\r\n"
			+ "  | || | | || |  | || | | | |_\\ \\ \\_/ / |____| |___| |  | |\r\n"
			+ "  \\_/\\_| |_/\\_|  |_/\\_| |_/\\____/\\___/\\_____/\\____/\\_|  |_/\r\n"
			+ "                                                           \r\n"
			+ "                                                           \r\n";
			
	//public static final String lettoreStdin = null;
	public static  Scanner lettoreStdin = new Scanner(System.in); 
	private final static String[] DIFFICULTY_OPTION= {"EASY MODE","MEDIUM MODE","HARD MODE"};
	private final static String[] ENDGAME_OPTION= {"RIGIOCA CAMBIANDO EQUILIBRIO","RIGIOCA CON LO STESSO EQUILIBRIO"};
	
	public static void main(String[] args) {
		
		
		MyMenu difficultyMenu= new MyMenu("select the difficulty mode",DIFFICULTY_OPTION);
		MyMenu endGameMenu= new MyMenu("how do you want to continue",ENDGAME_OPTION);
		
		System.out.println(TITLE);
		System.out.println();
	
			
		System.out.print("Insert first player name\n--> ");
		Player p1 = new Player(lettoreStdin.next());
		
		System.out.print("Insert second player name\n--> ");
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
				
			System.out.printf("THE WINNER IS %s\n", winner.getName());
				
			
				
			//scelta per nuova partita
						
			scelta2 = endGameMenu.scegli();
			if(scelta2==1) {
				System.out.println("L'EQUILIBRIO DELLA PARTITA ERA: ");
				System.out.println(Equilibrio.visualizeEquilibrium()); /**/
			}
			
		}while(scelta2 != 0);
		
		if(scelta!=0) {
			System.out.println("L'EQUILIBRIO DELLA PARTITA ERA: ");
			System.out.println(Equilibrio.visualizeEquilibrium()); /**/
		}
		
		System.out.println("GRAZIE PER AVER GIOCATO");
		
		
	
	}
	

}

