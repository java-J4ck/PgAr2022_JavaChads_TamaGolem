package utilities;

import java.util.Scanner;

import entities.*;



public class Battle {

	private static final int PRINT_ELEMENT_LIST_NUM = 100;
	private static Scanner sc = new Scanner(System.in);
	
	
	
	
	public static Player battleManager(Player p1, Player p2) {
<<<<<<< HEAD
=======
		
		// System.out.println(Equilibrio.visualizeEquilibrium()); /**/
>>>>>>> 67b5367ade5701410cdb817133cb7443af70a224
	
			
		int[][] equilibriumTable = Equilibrio.getEquilibriumTable();
		int power = 0;
		
		Stones.setSharedStones(Equilibrio.getStoneQuantity());   // Setta la quantita' di pietre in comune ai due giocatori
		
		TamaGolem t1 = summonTamaGolem(p1);  // Evoca il primo tamagolem del giocatore 1
		TamaGolem t2 = summonTamaGolem(p2);  // Evoca il primo tamagolem del giocatore 2
		
		for(;;) {
			LOOP_TAG:
			for(;;) {
				
				for(int i=0; i<Equilibrio.getStonesPerTamaGolem(); i++) {
					power = equilibriumTable[t2.getStones()[i].ordinal()][t1.getStones()[i].ordinal()];
					if(power < 0) {   // Se la potenza della pietra di t1 contro quella di t2 e' <0, significa che t1 ha scagliato la pietra debole
						t1.decreaseHp(Math.abs(power));
						System.out.printf("Tamagolem 2 (with %s stone) inflict %d of damage to tamagolem 1 (with %s stone)\n", t2.getStones()[i].toString(), Math.abs(power), t1.getStones()[i].toString());
					}
					else if(power > 0) {
						t2.decreaseHp(power);
						System.out.printf("Tamagolem 1 (with %s stone) inflict %d of damage to tamagolem 2 (with %s stone)\n", t1.getStones()[i].toString(), power, t2.getStones()[i].toString());
					}
					else {
						System.out.printf("Tamagolem 1 and Tamagolem 2 hurled the same stone (%s)!\n", t1.getStones()[i].toString());
					}
					if(t1.isDead() | t2.isDead()) break LOOP_TAG; // Controlla se uno dei due tamagolem e' morto e in caso positivo, termina lo scontro fra i due tamagolem
					System.out.printf("[!] TAMAGOLEM 1 HP : %d [!]\n[!] TAMAGOLEM 2 HP: %d[!]\n\n", t1.getHp(), t2.getHp());		
				}
				
			}
			
			if(t1.isDead()) {
				p1.removeTamaGolem(t1);
				System.out.println("Tamagolem 1 is DEAD!\n");
				t1 = summonTamaGolem(p1);  // Evoca il successivo tamagolem del giocatore 1
				if(t1 == null) return p2;   // Controlla se il tamagolem e' stato evocato con successo. In caso negativo, significa che il giocatore non ha piu'
											// tamagolem disponibili, dunque il giocatore avversario vince
			}
			if(t2.isDead()) {
				p2.removeTamaGolem(t2);
				System.out.println("Tamagolem 2 is DEAD!\n");
				t2 = summonTamaGolem(p2);  // Evoca il successivo tamagolem del giocatore 2
				if(t2 == null) return p1;
			}
			
		}
			
	}
	
	
	
	
	private static TamaGolem summonTamaGolem(Player p) {
		
		if(p.getTamaGolemsList().size() > 0) {   // Verifica che il player abbia ancora tamagolems, altrimenti ritorna null
			System.out.printf("\nTurn of %s\n", p.getName());
			selectStones(p.getTamaGolem());
			return p.getTamaGolem();
		}
		else {
			return null;
		}
		
	}
	
	

	
	private static void selectStones(TamaGolem t) {
		
		int stonesPerTG = Equilibrio.getStonesPerTamaGolem();
		int elementNum = Equilibrio.getElementNumber();
		Stones[] s  = new Stones[stonesPerTG];
		Stones[] availableStones = Stones.getNStones(elementNum);   // Salva in un array tutti i tipi di pietre disponibili per l'attuale match (il numero viene determinato in base alla difficolta' del gioco)
		
		System.out.printf("Select %d stones for your tamagolem\n(Enter number %d to get a list of available elements)", stonesPerTG, PRINT_ELEMENT_LIST_NUM);
		
		for(int i = 0; i < stonesPerTG & Stones.getSharedstones() > 0; ) {
			int nextInt = 0;
			while(!sc.hasNextInt()) {    // Attendi l'inserimento di un numero 
				System.out.println("Insert a valid number pls.");
				sc.next();
			}
			nextInt = sc.nextInt();   // Leggi il numero inserito
			if(nextInt == PRINT_ELEMENT_LIST_NUM) {   // Se il numero inserito corrisponde a un determinato numero (default : 100) stampa una lista di tutti gli elementi con i relativi numeri per selezionarli
				System.out.printf("THERE ARE %d AVAILABLE STONES: \n", elementNum);
				for(Stones stone : availableStones) {
					System.out.printf("%d --> %s\n", stone.ordinal(), stone.toString());
				}
				System.out.println("Numbers that does not correspond to an element are setted by default to 0");
			}
			else {
				s[i] = availableStones[nextInt >= 0 ? (nextInt < availableStones.length ? nextInt : 0) : 0];    // Se il numero inserto non corrisponde a nessun elemento, imposta di   
				i++;																					   		// default l'elemento 0		
				Stones.setSharedStones(Stones.getSharedstones() - 1);					
			}
		}
		
		t.setStones(s);    // Setta le pietre selezionate per il tamagolem appena evocato 
		
	}
	
	
	
	
}
