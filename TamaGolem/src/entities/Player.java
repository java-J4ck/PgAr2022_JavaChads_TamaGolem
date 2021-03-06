package entities;

import java.util.ArrayDeque;


/**
 * Classe che implementa un giocatore. 
 * <p>
 * Ogni giocatore ha i propri tamagolem, contenuti in un <b>ArrayDeque</b>.  
 * </p>
 *
 */
public class Player {
	
	private String name;   // Nome del giocatore
	private ArrayDeque<TamaGolem> tamagolems = new ArrayDeque<>();  // Tamagolems del player
	
	
	public Player(String name) {
		this.name = name;
	}
	
	/**
	 * @return Il nome del giocatore
	 */
	public String getName() {
		return this.name;
	}
	
	
	/**
	 * Aggiungi un tamagolem al giocatore (In coda alla deque)
	 * @param t Tamagolem da aggiungere
	 */
	public void addTamaGolem(TamaGolem t) {
		this.tamagolems.offer(t);
	}
	
	
	/**
	 * Rimuovi un tamagolem al giocatore (Dalla testa della deque)
	 * @param t Tamagolem da rimuovere
	 */
	public void removeTamaGolem() {
		this.tamagolems.poll();
	}
	
	
	/**
	 * @return Il tamagolem in testa alla deque
	 */
	public TamaGolem getTamaGolem () {
		return tamagolems.peek();
	}

	
	/**
	 * @return ArrayDeque dei tamagolem del giocatore
	 */
	public ArrayDeque<TamaGolem> getTamaGolemsList() {
		return tamagolems;
	}
	
	
	/**
	 * Metodo che inizializza una nuova lista di tamagolems per il player (eliminando eventuali tamagolems residui da vecchie partite)
	 * @param n Numero di tamagolems (Dimensione lista)
	 */
	public void newTamagolemsList(int n) {
		tamagolems = new ArrayDeque<>();
		for(int i=0; i<n; i++) {
			this.addTamaGolem(new TamaGolem());
		}
	}
	
	
	
	
	
	
}
