package utilities;
import java.util.Arrays;
import java.util.Random;

import entities.Stones;
/**
 * La classe <b> EQUILIBRIO </b> si occupa:
 * <ul>
 * <li> dei calcoli dei parametri di gioco(numero di tamagolem,quantità di petre nella riserva,ecc..)</li>
 * <li> della generazione di una matrice che desriva l'equilibrio</li>
 * </ul>
 * 
 * la matrice equilibrio è una matrice quadrata di dimensioni pari agli elemanti usati
 * i numeri negativi simboleggiano una debolezza verso un tipo,il contrario per i positivi.
 * esempio: equilibriumTable[pietra1][pietra2]=4 in questo caso pietra1 è forte contro la pietra2 (pietra1--4-->pietra2)
 * esempio 2: equilibriumTable[pietra1][pietra2]=-8 in questo caso pietra1 è debole contro la pietra2 (pietra2--8-->pietra1)
 * 
 *
 */
public class Equilibrio {
	
	private static final int HARD_MODE = 8;//numero di elementi nella modalità difficile
	private static final int MEDIUM_MODE = 6;//numero di elementi nella modalità media
	private static final int EASY_MODE = 4;//numero di elementi nella modalità facile
	private static final int HP = 100;//numero di punti vita di un tamagolem
	private static Stones[] powerStone;//pietre usate
	private static int tamaGolemNumber; // G(numero di tamagolem)
	private static int elementNumber; // N(numero di elementi)
	private static int stoneQuantity; // S(quantità di pietre nella riserva comune)
	private static int stonesPerTamaGolem; // P(pietre per ogni tamagolem)
	private static int stonesPerType; // s/n
	
	private static int[][] equilibriumTable; // matrice equilibrio
	private static Random rand = new Random();
	
	
	/**
	 * metodo che esegue i calcoli dei perametri
	 * prende in input la difficoltà(un numero intero che definisce il numero di elementi usati):
	 * <ul>
	 * <li>0 per la partita facile con 4 elementi</li>
	 * <li>1 per la partita media con 6 elementi</li>
	 * <li>2 per la partita difficile con 8 elementi</li>
	 * </ul>
	 * 
	 * 
	 */
	public static void setDifficulty(int difficulty) {
		switch(difficulty) {
			case 1:
				elementNumber=EASY_MODE;
				break;
			case 2:
				elementNumber=MEDIUM_MODE;
				break;
			case 3:
				elementNumber=HARD_MODE;
				break;
			
		}
		powerStone = new Stones[elementNumber];
		//calcoli utilizzando le formule sulle slide
		powerStone = Arrays.copyOf(Stones.values(), elementNumber);
		stonesPerTamaGolem = ((int) Math.ceil((elementNumber + 1) / 3.0 )) + 1;  
		stonesPerTamaGolem = ((int) Math.ceil((elementNumber+ 1) / 3.0 ))+ 1;
		tamaGolemNumber = (int)Math.ceil((elementNumber- 1)*(elementNumber - 2) / (2.0 * stonesPerTamaGolem));
		stoneQuantity = ((int)Math.ceil((2 * tamaGolemNumber * stonesPerTamaGolem) / elementNumber))* elementNumber;
		stonesPerType = stoneQuantity/elementNumber;
		
	}
	
	
	public static int getTamaGolemNumber() {
		return tamaGolemNumber;
	}

	
	public static int getElementNumber() {
		return elementNumber;
	}

	
	public static int getStoneQuantity() {
		return stoneQuantity;
	}

	
	public static int getStonesPerTamaGolem() {
		return stonesPerTamaGolem;
	}
	
	public static int getStonesPerType() {
		return stonesPerType;
	}
	
	
	public static int getHp() {
		return HP;
	}
	
	
	
	/**
	 * metodo che genera un valore casuale compreso tra due estremi <b> min </b> e <b> max </b>
	 * @param min(estremo inferiore)
	 * @param max(estremo superiore)
	 * @return valore casuale
	 */
	private static int getRandom(int min,int max) {
		int result=0;
		result=rand.nextInt(max-min+1)+min;
		return result;
	}
	
	/**
	 * ritorna la somma degli elementi di un array
	 * @param array
	 * @return
	 */
	private static int getSum(int[] array) {
		int sum=0;
		
		for(int i=0;i<array.length;i++) sum+=array[i];
		
		return sum;
	}
	
	/**
	 * 
	 * metodo che ritorna un numero casuale in base hai pesi dei collegamenti di un nodo:
	 * se la somma e 0 allora si puo generare un numero casuale tra -HP e +HP
	 * se la somma e 1 o -1 allora si può generare un numero casuale tra -HP+1 e +HP-1
	 * se la somma e l a somma  maggiore di 0 cerca di far diventare la somma dei pesi 1 es: se la somma e 20 il programma genera un un numero tra -19 e -1
	 * se la somma e l a somma  minore di 0 cerca di far diventare la somma dei pesi  -1 es: se la somma e -45 il programma genera un un numero tra 1 e 44
	 * 
	 * 
	 * @param sum
	 * 
	 */
	private static int getNum(int sum) {
		int num=0;
		do {
			if(sum==0 ) {
				num=getRandom(-HP,HP);
			}
			else if( sum==1 || sum== -1) {
				num=getRandom(-HP+1,HP-1);
			}
			else if(sum>0){
				if(sum>HP) sum=HP;
				num=getRandom(-sum+1,-1);
			}
			else if(sum<0){
				if(sum<-HP) sum= -HP;
				num=getRandom(1,(-sum)-1);
			}
		}while(num==0);
		
		return num;
	}

	/**
	 * motodo che se rileva la presenza di uno 0 a fine riga lo incrementa e decrementa il primo peso disponibile
	 * 
	 */
	private static void adjustLine(int line) {
		
			equilibriumTable[line][powerStone.length-1]++;
			equilibriumTable[powerStone.length-1][line]= -equilibriumTable[line][powerStone.length-1];
			for(int k=line;k<equilibriumTable[line].length-1;k++) {
				if(equilibriumTable[line][k]!=1 && equilibriumTable[line][k]!=-HP && line!=k) {//il carattere da decrementare non puo essere 1(altrimenti il peso andrebbe a 0) ne -hp altrimenti si sforerebbe
					--equilibriumTable[line][k];
					equilibriumTable[k][line]= -equilibriumTable[line][k];
					break;
				}
			}
		
		
		
	}
	/**
	 * metodo che genera l'EQUILIBRIO.
	 * la matrice generata e' quadra e di dimensione numero degli elementi 
	 * 
	 */
	public static void generateEquilibrium(){
		int sum;
		{
			equilibriumTable = new int[powerStone.length][powerStone.length];
			for(int i=0;i<powerStone.length-1;i++) {
				for(int k=i;k<powerStone.length;k++) {
					if(i==k) equilibriumTable[i][k]=0;
					else if(k<powerStone.length-1){//per ogni riga decide il peso da mettere in base alla somma dei pesi dell'elemento corrente e di quello a cui e collegato
						sum=(Math.abs(getSum(equilibriumTable[i]))>=Math.abs(getSum(equilibriumTable[k]))) ? getSum(equilibriumTable[i]): -getSum(equilibriumTable[k]);//per generare il peso utilizza la somma piu alta tra l'elemento corrente e quello con cui sta facendo il collegamento
						equilibriumTable[i][k]=getNum(sum);//prende il numero da mettere sulla riga 
						equilibriumTable[k][i]= -equilibriumTable[i][k];//ne mette uno speculare cambiato di segno
					}
						
					else {
						equilibriumTable[i][k]= -getSum(equilibriumTable[i]);//per l'ultimo carattere si usa la somma degli altri cambiata di segno per avere la somma totale 0
						equilibriumTable[k][i]= -equilibriumTable[i][k];//ne mette uno speculare cambiato di segno(se A e forte contro B allora B e debole contro A)
						
						if(equilibriumTable[i][k]==0 && i!=powerStone.length-1) {//se l'ultimo e 0 aggiusta i pesi
							adjustLine(i);
						}
						
					}
				}
			
			}
		}while(!checkTable());//ne genera un altra se per caso ci dovesse essere un errore
			
	}
	
	
	/**
	 * metodo che controlla la validita di una tabella di equilibrio.
	 * il metodo controlla:
	 * <ul>
	 * <li>se la somma di tutti i pesi di un elemento fa 0</li>
	 * <li>se ci sono pesi da 0 in elementi diversi </li>
	 * <li>se ci sono pesi che suparano gli HP</li>
	 * </ul>
	 * @return true(se giusta)
	 */
	private static boolean checkTable() {
		for(int i=0;i<powerStone.length;i++) { 
			if(getSum(equilibriumTable[i])!=0) return false;
			for(int k=0;k<powerStone.length;k++) {
				if(i!=k && equilibriumTable[i][k]==0) return false;
				if(equilibriumTable[i][k]<-HP || equilibriumTable[i][k]>HP ) return false;
				
			}
		}
		return true;
	}
	
	/**
	 * visualizza la tabella
	 * 
	 */
	
	public static String visualizeEquilibrium() {
		StringBuffer table= new StringBuffer("");
		table.append(String.format("%11s", " "));
		for(int i=0;i<powerStone.length;i++) {
			table.append(String.format("%11s",powerStone[i]));
		}
		table.append("\n");
		for(int i=0;i<powerStone.length;i++) {
			table.append(String.format("%11s",powerStone[i]));
			for(int k=0;k<powerStone.length;k++) {
				table.append(String.format("%11d", equilibriumTable[i][k]));
			}
			table.append("\n\n");
		}
		
		return table.toString();
	}
	
	
	
	public static int[][] getEquilibriumTable() {
		return equilibriumTable;
	}
	
	

}
