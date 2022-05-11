package utilities;

import java.util.Scanner;

import entities.*;



public class Battle {

	private static Scanner sc = new Scanner(System.in);
	
	
	
	private static TamaGolem summonTamaGolem(Player p) {
		
		if(p.getTamaGolemsList().size() > 0) {   // Verifica che il player abbia ancora tamagolems, altrimenti ritorna null
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
		Stones[] availableStones = Stones.getNStones(elementNum);
		
		System.out.printf("Select %s stones for your tamagolem\n(Enter any letter to get a list of available elements)", stonesPerTG);
		
		for(int i = 0; i < stonesPerTG; ) {
			int nextInt = 0;
			if(sc.hasNextInt()) {
				nextInt = sc.nextInt();
				s[i] = availableStones[nextInt < 0 | nextInt > availableStones.length ? nextInt : 0];     // Se il numero inserto non corrisponde a nessun elemento, imposta di   
				i++;																					  // default l'elemento 0		
			}
			else if(sc.hasNext()) {
				for(Stones stone : availableStones) {
					System.out.println("AVAILABLE STONES: ");
					System.out.printf("%d --> %s\n", stone.ordinal(), stone.toString());
				}
			}
		}
		
		t.setStones(s);
		
	}
	
	
	
	
}
