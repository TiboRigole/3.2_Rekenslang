import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		/*int[] sequence = new int [3];
		sequence[0] = 1;
		sequence[1] = 2;
		sequence[2] = 3;
		genereerCombinaties(sequence, 0);
		CombinatieLijst.getCombinatieLijst().print();
		*/
		
		Scanner sc = new Scanner(System.in);
		
		int aantalRekenslangen = Integer.parseInt(sc.nextLine());
		
		//variabelen voor input
		String getallenString;
		int[] getallenLijst;
		String rekenSom;
		int rechterDeel;
		
		for(int rkslng=0 ; rkslng < aantalRekenslangen ; rkslng++) {
			
			//wissen van de gegevens van de vorige slang
			CombinatieLijst.getCombinatieLijst().clear();
			
			getallenString = sc.nextLine();
			rekenSom = sc.nextLine();
			
			//invullen in een arrayList
			getallenLijst = vulGetallenInLijst(getallenString);
			
			//nemen van het rechterdeel
			rechterDeel = pakRechterDeel(rekenSom);
			
			//wissen van het rechterdeel in de string
			rekenSom = wisRechterDeel(rekenSom);
					//nu is rekensom "12 * B + C * A * 14 * 15 + 11 + 5 - 9 * E + 4 * F * D + 13 * 10" bijvoorbeeld
			
			//variabelen waarmee we werken:
				//			CombinatieLijst.getCombinatieLijst(), waarin de volgorde van de te vervangen letters inzitten
				//			rekenSom , waarin het linkerdeel van de som zit, dit moeten we vervangen
				//			rechterDeel::int, waarmee we moeten vergelijken
			
			//vervangen van alle letters in de linkerdeel
			int size = CombinatieLijst.getCombinatieLijst().getArray().size();
			
			for(int i=0; i<size; i++) {
				String teVervangen = CombinatieLijst.getCombinatieLijst().getArray().get(i);
				
				rekenSom = vervangGetallen(rekenSom, teVervangen);
				
				//rekensom is nu van de vorm 
				//12 * 2 + 4 * 1 * 14 * 15 + 11 + 5 - 9 * 8 + 4 * 9 * 7 + 13 * 10
				
				int getal = rekenSomUit(rekenSom);
				
				if(getal == rechterDeel) {
					//print die shit uit niffo
				}
			}
			
			
			
		}


		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static int rekenSomUit(String rekenSom) {
		//laat deze methode vroegtijdig 0 returnen als het spel niet klopt
	}





































	private static int[] vulGetallenInLijst(String getallenString) {
		
		int aantalGetallen = (getallenString.length()+1)/2;
		System.out.println("aantal getallen in te vullen :"+aantalGetallen);
		
		int[] getallenLijst = new int[aantalGetallen];
		int teller = 0;
		
		for(int i=0; i<getallenString.length(); i++) {
			int getal = getallenString.charAt(i);
			
			getallenLijst[teller] = getal-48;
			teller++;
			i++;
			
		}
		return getallenLijst;
	}
	
	
	private static int pakRechterDeel(String som) {
		
		StringBuilder getal = new StringBuilder();
		int i = som.length()-1;
		while(Character.isDigit(som.charAt(i))) {
			getal.append(som.charAt(i));
			i--;
		}
		getal= getal.reverse();
		return Integer.parseInt(getal.toString());
	}
	
	private static String wisRechterDeel(String som) {
		
		StringBuilder linkerDeel = new StringBuilder(som);
		int i = som.length()-1;
		
		int a = som.charAt(i);
		
		while(a != 61) {
			i--;
			System.out.println(som.charAt(i));
			a = som.charAt(i);
		}
		
		i = i-1;
		return linkerDeel.substring(0, i);
		
	}



	private static String vervangGetallen(String linkerDeel, String getallenVolgorde) {
		
		//A is niet de eerste letter in de string
		//we kiezen er wel voor om het eerste getal van de getallenVolgordeString in plek van A te 
		// en 2e getal als B,op deze manier is het makkelijker om de output dan te printen
		
		int aantalGetallen = getallenVolgorde.length();
		
		int lDeelLength = linkerDeel.length();
	
		//de letters
		char letter = 'A';
		
		//voor elk getal dat moet vervangen worden
		for(int x=0; x<getallenVolgorde.length(); x++) {
			
			//pak het getal
			int getal = getallenVolgorde.charAt(x)-48;	
			

			
			//overloop de rij
			int lengte = linkerDeel.length();
			for(int i=0; i<lengte; i++) {
				
				//als het overlopen karakter een A is, 
				if(linkerDeel.charAt(i) == letter) {
					
					//vervang het karakter op deze plek door het getal op plek x in de rij
					linkerDeel = linkerDeel.replace(letter+"", getal+"");
							
					//pas de A aan naar een B
					letter = (char)(letter+1);
					
					i = lengte-1;
					
				}			
			}		
		}
		return linkerDeel;	
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


