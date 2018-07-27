import java.util.ArrayList;

//werken met singeton principe
public class CombinatieLijst {
	
	private static CombinatieLijst instance;
	private ArrayList<String> list = null;
    
    public static CombinatieLijst getCombinatieLijst() {
    	if(instance == null) {
    		instance = new CombinatieLijst();
    	}
    	return instance;
    }
    
   private CombinatieLijst() {
	   list = new ArrayList<String>();
   }

   // retrieve array from anywhere
   public ArrayList<String> getArray() {
    return this.list;
   }
   
   //Add element to array
   public void addToArray(String value) {
    list.add(value);
   }
   
   public void print() {
	   for(String s : list) {System.out.println(s);}
   }
   
   public void clear() {
	   list.clear();
   }

	public static void vulCombinatiesIn(int[] getallenLijst) {
		
		genereerCombinaties(getallenLijst, 0);
		
		
	}
    
	static void genereerCombinaties(int []array, int k){
		
		//als de combinatie afgewerkt is,
		//aka als het aantal elementen in de tijdelijke verzameling
		//gelijk is aan het aantal nodige elementen
		if (k == array.length){

			//dan is de array klaar om te printen
			//overloop de array
			StringBuilder sb = new StringBuilder();
			
			for(int i=0 ; i<array.length ; i++){

				//print het karakter
				sb.append(array[i]);
			}

			//voeg de combinatie toe aan de arrayList
			CombinatieLijst.getCombinatieLijst().addToArray(sb.toString());


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



	}


}
