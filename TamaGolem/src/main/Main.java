package main;

import utilities.Equilibrio;

public class Main {

	public static void main(String[] args) {
		Equilibrio.setDifficulty(2);
		Equilibrio.generateEquilibrium();
		
		System.out.println(Equilibrio.visualizeEquilibrium());
	}

}
