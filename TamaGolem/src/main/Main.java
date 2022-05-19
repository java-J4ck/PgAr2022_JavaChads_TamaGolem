
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
	private final static String[] DIFFICULTY_OPTION= {"EASY MODE","MEDIUM MODE","HARD MODE"};//opzioni per la difficolta
	private final static String[] ENDGAME_OPTION= {"RIGIOCA CAMBIANDO EQUILIBRIO","RIGIOCA CON LO STESSO EQUILIBRIO"};//opzioni per il fine gioco
	
	public static void main(String[] args) {
		
		// creo i due menu che servono
		MyMenu difficultyMenu= new MyMenu("select the difficulty mode",DIFFICULTY_OPTION);
		MyMenu endGameMenu= new MyMenu("how do you want to continue",ENDGAME_OPTION);
		
		System.out.println(TITLE);//stampo il titolo
		System.out.println();
	
		//prendo in ingresso il nome dei due giocatori
		System.out.print("Insert first player name\n--> ");
		Player p1 = new Player(lettoreStdin.next());
		
		System.out.print("Insert second player name\n--> ");
		Player p2 = new Player(lettoreStdin.next());
		
		int scelta=0,scelta2=1;
		do {
			//si inizia scegliendo la difficolta
			if(scelta2 ==1) {//si scelgie la difficolta solo se no si sceglie di rigiocare con lo stesso equilibrio
				scelta=difficultyMenu.scegli();
				if(scelta==0) break;
				Equilibrio.setDifficulty(scelta);//imposta la difficolta
				Equilibrio.generateEquilibrium();  // Genera l'equilibrio
			}
			
			for(int i=0; i < Equilibrio.getTamaGolemNumber(); i++) {//inserisce i  tamagolem in base al numero calcolato nell'equilibrio
				if(p1.getTamaGolemsList().size()<=Equilibrio.getTamaGolemNumber()) p1.addTamaGolem(new TamaGolem()); //li aggiunge ai giocatori
				if(p1.getTamaGolemsList().size()<=Equilibrio.getTamaGolemNumber()) p2.addTamaGolem(new TamaGolem());
		   }	
	
					
	
				
			Player winner = Battle.battleManager(p1, p2);//avvia la battaglia e prende il vincitore
				
			System.out.printf("THE WINNER IS %s\n", winner.getName());//dichiara il vincitore
				
			
				
			//scelta per nuova partita
						
			scelta2 = endGameMenu.scegli();//scegli se  rigiocare
			if(scelta2==1) {//se la scelta e 1 allora si rigioca cambiando equilibrio
				System.out.println("L'EQUILIBRIO DELLA PARTITA ERA: ");
				System.out.println(Equilibrio.visualizeEquilibrium()+"\n"); //visualizza l'equilibrio
			}
			
		}while(scelta2 != 0);// continua finche non si decide di uscire
		
		if(scelta!=0) {//se non si sceglie di uscire all'inizio(quando si deve sciegliere la difficolta) allora vuol dire che la partita e finita allora si stampa l'equilibrio
			System.out.println("L'EQUILIBRIO DELLA PARTITA ERA: ");
			System.out.println(Equilibrio.visualizeEquilibrium()); //visualizza l'equilibrio
		}
		
		System.out.println("GRAZIE PER AVER GIOCATO");// frase di uscita
		
		
	
	}
	

}

