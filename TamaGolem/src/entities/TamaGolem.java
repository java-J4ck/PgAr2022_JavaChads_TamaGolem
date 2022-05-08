package entities;

public class TamaGolem {

	private int hp = 100;   // Vita del tamagolem (Default: 100)
	private Stones[] stones;   // Numero di pietre che il tamagolem puo' ingurgitare. 
							   // La dimensione dell'array viene determinata in base alla diffolta' scelta in fase di setup.	
	
		

	public TamaGolem(int hp, int dimStones) {
		this.hp = hp;
		this.stones = new Stones[dimStones];
	}	
	
	
	
	public int getHp() {
		return hp;
	}

	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	
	public Stones[] getStones() {
		return stones;
	}
	
	
	public void setStones(Stones[] stones) {
		this.stones = stones;
	}
	
	

	
	
	
	
}
