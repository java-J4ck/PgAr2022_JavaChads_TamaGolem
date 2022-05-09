package utilities;
import java.util.Random;
import entities.Stones;

public class Equilibrio {
	private static final int HP=10;
	private static Stones[] powerStone= Stones.values();
	private static int[][] EquilibriumTable = new int[powerStone.length][powerStone.length];
	private static Random rand=new Random();
	
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
			else if( sum==1 || sum==-1) {
				num=getRandom(-HP+1,HP-1);
			}
			else if(sum>0){
				num=getRandom(-sum+1,-1);
			}
			else if(sum<0){
				num=getRandom(1,(-sum)-1);
			}
		}while(num==0);
		
		return num;
	}
	
	public static void generateEquilibrium(){
		for(int i=0;i<powerStone.length;i++) {
			for(int k=i;k<powerStone.length;k++) {
				if(i==k) EquilibriumTable[i][k]=0;
				else if(k!=powerStone.length-1){
					EquilibriumTable[i][k]=getNum(getSum(EquilibriumTable[i]));
					EquilibriumTable[k][i]= -EquilibriumTable[i][k];
				}
				else {
					EquilibriumTable[i][k]= -getSum(EquilibriumTable[i]);
					EquilibriumTable[k][i]= -EquilibriumTable[i][k];
				}
			}
		}
		
	}

	public static String visualizeEquilibrium() {
		StringBuffer table= new StringBuffer("");
		for(int i=0;i<powerStone.length;i++) {
			for(int k=0;k<powerStone.length;k++) {
				table.append(String.format("%4d", EquilibriumTable[i][k]));
			}
			table.append("\n");
		}
		return table.toString();
	}
	
	
	public static int[][] getEquilibriumTable() {
		return EquilibriumTable;
	}
	
	

}
