package utilities;
import java.util.Arrays;
import java.util.Random;

import entities.Stones;

public class Equilibrio {
	private static final int HARD_MODE = 8;
	private static final int MEDIUM_MODE = 6;
	private static final int EASY_MODE = 4;
	private static final int HP=10;
	private static Stones[] powerStone;
	private static int tamaGolemNumber;//G
	private static int stoneElementNumber;//N
	private static int stoneQuantity;//S
	
	private static int[][] equilibriumTable;
	private static Random rand=new Random();
	
	public static void setDifficulty(int difficulty) {
		int P;//P
		switch(difficulty) {
			case 0:
				stoneElementNumber=EASY_MODE;
				break;
			case 1:
				stoneElementNumber=MEDIUM_MODE;
				break;
			case 2:
				stoneElementNumber=HARD_MODE;
				break;
			
		}
		powerStone=new Stones[stoneElementNumber];
		powerStone=Arrays.copyOf(Stones.values(), stoneElementNumber);
		P=((int) Math.ceil((stoneElementNumber+ 1) / 3.0 ))+ 1;
		tamaGolemNumber=(int)Math.ceil((stoneElementNumber- 1)*(stoneElementNumber - 2) / (2.0 * stoneQuantity));
		stoneQuantity=((int)Math.ceil((2 * tamaGolemNumber * P) / stoneElementNumber))* stoneElementNumber;
		
	}
	
	public static int getTamaGolemNumber() {
		return tamaGolemNumber;
	}

	public static int getStoneElementNumber() {
		return stoneElementNumber;
	}

	public static int getStoneQuantity() {
		return stoneQuantity;
	}

	private static int getRandom(int min,int max) {
		int result=0;
		result=rand.nextInt(max-min+1)+min;
		return result;
	}
	
	private static int getSum(int[] array) {
		int sum=0;
		
		for(int i=0;i<array.length;i++) sum+=array[i];
		
		return sum;
	}
	
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


	private static void adjustLine(int line) {
		
			equilibriumTable[line][powerStone.length-1]++;
			equilibriumTable[powerStone.length-1][line]= -equilibriumTable[line][powerStone.length-1];
			for(int k=line;k<equilibriumTable[line].length-1;k++) {
				if(equilibriumTable[line][k]!=1 && equilibriumTable[line][k]!=-10 && line!=k) {
					--equilibriumTable[line][k];
					equilibriumTable[k][line]= -equilibriumTable[line][k];
					break;
				}
			}
		
		
		
	}
	
	public static void generateEquilibrium(){
		int sum;
		do {
			equilibriumTable = new int[powerStone.length][powerStone.length];
			for(int i=0;i<powerStone.length-1;i++) {
				for(int k=i;k<powerStone.length;k++) {
					if(i==k) equilibriumTable[i][k]=0;
					else if(k<powerStone.length-1){
						sum=(Math.abs(getSum(equilibriumTable[i]))>=Math.abs(getSum(equilibriumTable[k]))) ? getSum(equilibriumTable[i]): -getSum(equilibriumTable[k]);
						equilibriumTable[i][k]=getNum(sum);
						equilibriumTable[k][i]= -equilibriumTable[i][k];
					}
						
					else {
						equilibriumTable[i][k]= -getSum(equilibriumTable[i]);
						equilibriumTable[k][i]= -equilibriumTable[i][k];
						
						if(equilibriumTable[i][k]==0 && i!=powerStone.length-1) {
							adjustLine(i);
						}
						
					}
				}
			
			}
		}while(!checkTable());
		
	}
	
	
	public static boolean checkTable() {
		for(int i=0;i<powerStone.length;i++) { 
			if(getSum(equilibriumTable[i])!=0) return false;
			for(int k=0;k<powerStone.length;k++) {
				if(i!=k && equilibriumTable[i][k]==0) return false;
				if(equilibriumTable[i][k]<-HP || equilibriumTable[i][k]>HP ) return false;
				
			}
		}
		return true;
	}
	
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
