package utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import entities.*;



public class Battle {

	private static final int PRINT_ELEMENT_LIST_NUM = 100;
	private static Scanner sc = new Scanner(System.in);
	
	
	public static Player battleManager(Player p1, Player p2) {

		/*
		Equilibrio.setDifficulty(1);
		Equilibrio.generateEquilibrium();  // Genera l'equilibrio
		System.out.println(Equilibrio.visualizeEquilibrium()); 
		*/
		
		int[][] equilibriumTable = Equilibrio.getEquilibriumTable();
		int stonesPerTG = Equilibrio.getStonesPerTamaGolem();
		int power = 0;
		
		Stones.setSharedStones(Equilibrio.getStoneQuantity(), Equilibrio.getStonesPerType());  // Chiama la funzione che genera l'hashtable degli elementi disponibili per questa partita
		
		TamaGolem t1 = summonTamaGolem(p1);  // Evoca il primo tamagolem del giocatore 1
		TamaGolem t2 = summonTamaGolem(p2);  // Evoca il primo tamagolem del giocatore 2
		
		for(;;) {
			
			while(Arrays.equals(t1.getStones(), t2.getStones())) {    // Per evitare che il programma rimanga bloccato in un loop infinito, controlla che i due giocatori non abbiano lo stesso set di pietre
				System.out.println("The tamagolems have the same set of stones!\n");
				for(Stones s : t1.getStones()) Stones.addStone(s);   // Riaggiungi le pietre selezionate alle pietre comuni
				for(Stones s : t2.getStones()) Stones.addStone(s);
				System.out.printf("Turn of %s\n", p1.getName());
				selectStones(t1);   // Riseleziona le pietre per i tamagolem
				System.out.printf("Turn of %s\n", p2.getName());
				selectStones(t2);
			} // Continua a ripetere queste operazioni finche' i due giocatori hanno un set di pietre diverso
				
				
			LOOP_TAG:
			for(;;) {
				
				for(int i=0, j=0; i<stonesPerTG & j<stonesPerTG; i++, j++) {   // Itera sugli array contenenti le pietre dei due tamagolem che si devono sfidare.
					// Vengono usati due indici differenti per i due diversi array dei tamagolem perche' nel caso in cui un tamagolem abbia meno pietre dell'altro	
					// (per esempio se un giocatore sfortunato ha meno pietre disponibili per la selezione), servono due conteggi sfasati per iterare completamente entrambi gli array.	
					
					// Siccome gli array delle pietre dei tamagolem hanno dimensione fissa, se un tamagolem ha meno pietre del normale, le pietre mancanti sono valori null.
					// Dunque la lettura di un valore null dall'array di pietre indica che il tamagolem non ha piu' pietre e che bisogna ricominciare da capo l'iterazione sull'array.
					if(t1.getStones()[i] == null) { 
						i = 0;
					}
					if(t2.getStones()[j] == null) { 
						j = 0;
					}
					
					power = equilibriumTable[t2.getStones()[j].ordinal()][t1.getStones()[i].ordinal()];  // Leggi la matrice dell'equilibrio
					
					if(power < 0) {   // Se la potenza della pietra di t1 contro quella di t2 e' <0, significa che t1 ha scagliato la pietra debole
						t1.decreaseHp(Math.abs(power));  // Decrementa la vita di t1
						System.out.printf("Tamagolem 2 (with %s stone) inflict %d of damage to tamagolem 1 (with %s stone)\n", t2.getStones()[j].toString(), Math.abs(power), t1.getStones()[i].toString());
					}
					else if(power > 0) {  // Se invece la potenza della pietra t1 contro quella di t2 e' >0, t2 subira' il danno in quanto ha scagliato la pietra debole
						t2.decreaseHp(power);     // Decrementa la vita di t2
						System.out.printf("Tamagolem 1 (with %s stone) inflict %d of damage to tamagolem 2 (with %s stone)\n", t1.getStones()[i].toString(), power, t2.getStones()[j].toString());
					}
					else {   // Se la potenza della pietra di t1 contro quella di t2 e' uguale a 0, significa che sono state scagliate pietre dello stesso tipo
						System.out.printf("Tamagolem 1 and Tamagolem 2 hurled the same stone (%s)!\n", t1.getStones()[i].toString());
					}
					if(t1.isDead() | t2.isDead()) break LOOP_TAG; // Se uno dei due tamagolem e' morto, esci dal for e procedi con l'evocazione di un tamagolem sostituto
					System.out.printf("[!] TAMAGOLEM 1 HP : %d [!]\n[!] TAMAGOLEM 2 HP: %d [!]\n\n", t1.getHp(), t2.getHp());  // Stampa gli hp attuali dei due tamagolem
					
				}
				
			}
			
			
			if(t1.isDead()) {  
				p1.removeTamaGolem();   // Rimuovi il tamagolem morto   
				System.out.println("Tamagolem 1 is DEAD!\n");
				t1 = summonTamaGolem(p1);  // Evoca il successivo tamagolem del giocatore 1
				if(t1 == null) return p2;   // Controlla se il tamagolem e' stato evocato con successo. In caso negativo, significa che il giocatore non ha piu'
											// tamagolem disponibili, dunque il giocatore avversario vince.
			}
			if(t2.isDead()) {
				p2.removeTamaGolem();    // Rimuovi il tamagolem morto
				System.out.println("Tamagolem 2 is DEAD!\n");
				t2 = summonTamaGolem(p2);  // Evoca il successivo tamagolem del giocatore 2
				if(t2 == null) return p1;
			}
			
		}
		
		
	}
	
	
	
	
	/**
	 * Metodo per la fase di evocazione del tamagolem
	 * @param p Giocatore che deve evocare il tamagolem
	 * @return Tamagolem evocato
	 */
	private static TamaGolem summonTamaGolem(Player p) {
		
		if(p.getTamaGolem() != null) {   // Verifica che il player abbia ancora tamagolems, altrimenti ritorna null
			System.out.printf("\nTurn of %s\n", p.getName());
			selectStones(p.getTamaGolem());  // Chiama la funzione per selezionare il set di pietre da far ingoiare al tamagolem
			return p.getTamaGolem();
		}
		else {
			return null;
		}
		
	}
	
	
	
	/**
	 * Metodo per la seleziona delle pietre da fare ingurgitare al tamagolem per lo scontro
	 * @param t Tamagolem
	 */
	private static void selectStones(TamaGolem t) {
		
		int stonesPerTG = Equilibrio.getStonesPerTamaGolem();
		Stones[] s  = new Stones[stonesPerTG];  // Crea un nuovo array per contenere le pietre selezionate, che verra' poi assegnato al tamagolem
		
		System.out.printf("Select %d stones for your tamagolem\n(Enter number %d to get a list of available elements)\n", stonesPerTG, PRINT_ELEMENT_LIST_NUM);
		
		for(int i = 0; i < stonesPerTG & Stones.getSharedStones().size() > 0; ) {
			int nextInt = 0;
			while(!sc.hasNextInt()) {    // Attendi l'inserimento di un numero valido 
				System.out.println("Insert a valid number pls.");
				sc.next();  // Se e' stato inserito un carattere che non e' un numero, scartalo
			}
			nextInt = sc.nextInt();  // Leggi il numero inserito
			if(nextInt == PRINT_ELEMENT_LIST_NUM) {   // Se il numero inserito corrisponde a un determinato numero (default : 100) stampa una lista di tutti gli elementi disponibili con i relativi numeri per selezionarli
				System.out.println("AVAILABLE STONES: ");
				for(Stones stone : Stones.getOrderedKeyList()) {
					System.out.printf("%d --> %s (Quantity : %d)\n", stone.ordinal(), stone.toString(), Stones.getSharedStones().get(stone));
				}
				System.out.println("Numbers that does not correspond to an element are setted by defult to a random number.");
			}
			else {
				Stones selectedStone = Stones.values()[nextInt >= 0 ? (nextInt < Stones.values().length ? nextInt : 0) : 0];  // Controlla se il numero inserito corrisponde a un elemento, in caso contrario imposta di default l'elemento 0
				if(Stones.getSharedStones().containsKey(selectedStone)) {   // Controlla se l'elemento selezionato e' ancora disponibile nelle pietre comuni
					s[i] = selectedStone;   
					Stones.removeStone(s[i++]);  // Rimuovi l'elemento selezionato dalle pietre comuni disponibili e incrementa l'indice i
				}
				else {
					s[i] = Stones.getSharedStones().keys().nextElement();   // Se il giocatore seleziona una pietra non disponibile nell'elenco delle pietre comuni, viene selezionata la prima pietra disponibile
					Stones.removeStone(s[i++]);  // Rimuovi l'elemento selezionato dalle pietre comuni disponibili e incrementa i
				}																	             
			}
		}
		
		if(Stones.getSharedStones().size() == 0) System.out.println("NO MORE STONE AVAILABLE!");  // Se non ci sono piu' pietre disponibili, stampa un messaggio
		
		t.setStones(s);  // Assegna il set di pietre appena creato al tamagolem passato come argomento del metodo
		
	}
	
	
	
	
}
