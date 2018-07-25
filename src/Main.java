import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		int[] sequence = new int [3];
		sequence[0] = 1;
		sequence[1] = 2;
		sequence[2] = 3;
		genereerCombinaties(sequence, 0);
		CombinatieLijst.getCombinatieLijst().print();

		
	}
	
	
	////////////////////////////////////////////
	//https://www.sanfoundry.com/java-program-generate-all-possible-combinations-given-list-numbers/
	static void genereerCombinaties(int []array, int k){
		
		//als de combinatie afgewerkt is,
		//aka als het aantal elementen in de tijdelijke verzameling
		//gelijk is aan het aantal nodige elementen
		if (k == array.length){

			StringBuilder sb = new StringBuilder();
			//dan is de array klaar om te printen
			//overloop de array
			for(int i=0 ; i<array.length ; i++){
				
				//voeg het toe aan de StringBuilder
				sb.append(array[i]);

			}
			
			//voeg het toe aan de lijst
			CombinatieLijst.getCombinatieLijst().getArray().add(sb.toString());

		}
		
		//als de combinatie nog niet afgewerkt is
		else{

			//loop van het aantal ingevulde elementen tot het aantal nodige elementen
			for(int i=k ; i<array.length ; i++){

				//schuif alles eentje door
				int temp = array[k];
				array[k] = array[i];
				array[i] = temp;

				genereerCombinaties(array, k+1);

	            temp = array[k];
	            array[k] = array[i];
	            array[i] = temp;

			}
		}

		////////////////////////////////////////////
		
		
		

	}
	
	
}


