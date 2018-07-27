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
			
			//generatie van de verschillende combinaties
			CombinatieLijst.vulCombinatiesIn(getallenLijst);
			
			//volgende 2 lijnen mogen nog weg
			//System.out.println("klaar met invullen combinaties");
			//CombinatieLijst.getCombinatieLijst().print();
			
			//nemen van het rechterdeel
			rechterDeel = pakRechterDeel(rekenSom);
			
			//wissen van het rechterdeel in de string
			rekenSom = wisRechterDeel(rekenSom);
					//nu is rekensom "12 * B + C * A * 14 * 15 + 11 + 5 - 9 * E + 4 * F * D + 13 * 10" bijvoorbeeld
			
			//vooraf vereenvoudigen wanner het kan,
			//als er bijvoorbeeld staat A * 5 + 3 + 2 + 5 + B
			//dan kan dit al herleid worden naar A * 5 + 10 + B
			//variabelen waarmee we werken:
				//			CombinatieLijst.getCombinatieLijst(), waarin de volgorde van de te vervangen letters inzitten
				//			rekenSom , waarin het linkerdeel van de som zit, dit moeten we vervangen
				//			rechterDeel::int, waarmee we moeten vergelijken
			
			//vervangen van alle letters in de linkerdeel
			int size = CombinatieLijst.getCombinatieLijst().getArray().size();
			
			
			//voor elke mogelijke set van getallen
			for(int i=0; i<size; i++) {
				
				String teVervangen = CombinatieLijst.getCombinatieLijst().getArray().get(i);
				
				String rekenSomVervangd = vervangGetallen(rekenSom, teVervangen);
				
				//rekensom is nu van de vorm 
				//12 * 2 + 4 * 1 * 14 * 15 + 11 + 5 - 9 * 8 + 4 * 9 * 7 + 13 * 10
				
				int getal = rekenvergelijkingUit(rekenSomVervangd);
				//System.out.println("getal = "+getal);

				if(getal == rechterDeel) {
					StringBuilder antwoord = new StringBuilder();
					antwoord.append(rkslng+1 +" ");

					for(int u=0 ; u<teVervangen.length(); u++) {
						
						antwoord.append(Character.toChars('A'+u));
						antwoord.append("=" + teVervangen.charAt(u));
						if(u != teVervangen.length()-1) {antwoord.append(" ");}
					}
					
					System.out.println(antwoord.toString());
				}
			}	
		}	
	}
	
	
	
	
	
	
	
	/*
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
	
	
	private static int[] vulGetallenInLijst(String getallenString) {
		
		int aantalGetallen = (getallenString.length()+1)/2;
		//System.out.println("aantal getallen in te vullen :"+aantalGetallen);
		
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
			//System.out.println(som.charAt(i));
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
	
	/*
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * 
	 * alle methodes voor rekenuit, en de methode zelf
	 * 
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
	
	
	private static int rekenvergelijkingUit(String vergelijking) {
		
		vergelijking = vergelijking.replaceAll("\\s","");
		
		int [] waardes = telAantalKeerBewerkingen(vergelijking);
		
		int aantalPlus = waardes[0];
		int aantalMin = waardes[1];
		int aantalMaal = waardes[2];
		int aantalDelen = waardes[3];
		
		//System.out.println(aantalPlus +" " +aantalMin + " "+aantalMaal + " "+aantalDelen);
		int tellerPlus = 0;
		int tellerMaal = 0;
		int tellerMin = 0;
		int tellerDelen = 0;
		
		//verwerken van de delingen
		//hier if deling gaat niet op, return 0
		while(tellerDelen != aantalDelen) {
			
			
			int i = locatieEerstVolgende(vergelijking, ':');
			String stukDervoor = voorDeling(vergelijking, i);
			
			int aantalDigitsDervoor = stukDervoor.length();
			//het stukDervoor bestaat enkel uit getallen en maals, we kunnen het dus makkelijk volledig uitwerken
			int getalDervoor = rekenvergelijkingUit(stukDervoor);
			
			StringBuilder nieuweVgl = new StringBuilder(vergelijking);
			nieuweVgl.delete(i-aantalDigitsDervoor, i);
			nieuweVgl.insert(i-aantalDigitsDervoor , getalDervoor+"");
			vergelijking = nieuweVgl.toString();
			
			//nu kunnen we het effectieve getal nemen en delen 
			i = locatieEerstVolgende(vergelijking, ':');
			
			aantalDigitsDervoor = aantalDigits(getalDervoor);
			int getalDerna = pakGetalDerna(vergelijking, i);
			int aantalDigitsDerna = aantalDigits(getalDerna);
			
			if(getalDervoor % getalDerna != 0) {return 0;}
			else {
				//dus als de rest na deling wel 0 is, dan kunnen we de deling uitvoeren
				int nieuwGetal = getalDervoor / getalDerna;
				
				//volgende 3 lijnen mogen nog weg, zijn enkel ter controle van wat er gebeurt
				String x = vergelijking.substring(i-aantalDigitsDervoor, i+1+aantalDigitsDerna);
				//System.out.println(x +" wordt vervangen door ");
				//System.out.println(nieuwGetal);
				
				StringBuilder sb =new StringBuilder(vergelijking);
				sb.delete(i-aantalDigitsDervoor,i+aantalDigitsDerna+1);
				sb.insert(i-aantalDigitsDervoor, nieuwGetal+"");
				vergelijking = sb.toString();
	
				//System.out.println(vergelijking);
				
				tellerDelen++;
			}
		}
		
		aantalMaal = telAantalMaal(vergelijking);
		
		while(tellerMaal != aantalMaal) {
			int i = locatieEerstVolgende(vergelijking, '*');
			int getalDervoor = pakGetalDervoor(vergelijking, i);
			int aantalDigitsDervoor = aantalDigits(getalDervoor);
			int getalDerna = pakGetalDerna(vergelijking, i);
			int aantalDigitsDerna = aantalDigits(getalDerna);
			
			int nieuwGetal = getalDervoor * getalDerna;
			
			String x = vergelijking.substring(i-aantalDigitsDervoor, i+1+aantalDigitsDerna);
			//System.out.println(x +" wordt vervangen door ");
			//System.out.println(nieuwGetal);
			
			StringBuilder sb =new StringBuilder(vergelijking);
			sb.delete(i-aantalDigitsDervoor,i+aantalDigitsDerna+1);
			sb.insert(i-aantalDigitsDervoor, nieuwGetal+"");
			vergelijking = sb.toString();

			//System.out.println(vergelijking);
			
			
			tellerMaal++;
		};
		
		//voor de recursie in het delen
		if(aantalPlus == 0 && aantalMin == 0) {return Integer.parseInt(vergelijking);}
		
		
		//nu nog de plussen en de mins, opletten met negatieve getallen?
		
		return doePlusMin(vergelijking);
		//vergelijking nu van de vorm 12+13+2100-81+17+11+160+12+49
		//hiervoor mss een aparte methode a broer
		
		//readVolgendeBewerking
		//readVolgendGetal
		
	}
	
	
	
	
	/*
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * 
	 * alle hulpmethodes hiervoor, voor de optimalisatie dervan
	 * 
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
	
	
	
	
	//12+13+2100-81+17+11+160+12+49
		private static int doePlusMin(String vergelijking) {
			
			int maximum = vergelijking.length();
			int getal = leesGetal(vergelijking,0);

			int teller = aantalDigits(getal);
			
			while(teller != maximum) {
				char bewerking = vergelijking.charAt(teller);
				//System.out.println(bewerking);
				int volgendGetal = pakGetalDerna(vergelijking, teller);
				
				if(bewerking == '+') {
					//System.out.println(getal +"+" + volgendGetal +"=");
					getal = getal + volgendGetal;
					//System.out.println(getal);
				}
				else if(bewerking == '-') {
					//System.out.println(getal +"-" + volgendGetal +"=");
					getal = getal - volgendGetal;
					//System.out.println(getal);
				}
				else {
					//System.out.println("fout in doeplusmin");
				}
				
				teller = teller + aantalDigits(volgendGetal)+1;
			}
			return getal;
		}






		private static int leesGetal(String vergelijking, int beginPlek) {
			StringBuilder sb = new StringBuilder();
			for(int i=beginPlek; i<vergelijking.length() ; i++) {
				
				char c = vergelijking.charAt(i);
				if(Character.isDigit(c)) {sb.append(c);}
				else {return Integer.parseInt(sb.toString());}
				
			}
			System.out.println("fout in de leesGetal methode");
			return 0;
		}






		private static int telAantalMaal(String vergelijking) {
			int aantalMaal = 0;
			for(int i=0; i<vergelijking.length(); i++) {
				if(vergelijking.charAt(i) =='*') {aantalMaal++;}
			}
			return aantalMaal;
		}






		private static int pakGetalDerna(String vergelijking, int i) {
			//i is de locatie van de bewerking, we lezen dus tot het geen digit meer is
			StringBuilder getal = new StringBuilder();
			
			for(int x=i+1; x<vergelijking.length(); x++) {
				
				char karakter = vergelijking.charAt(x);
				if(Character.isDigit(karakter)) {getal.append(karakter-48);}
				else {
					//als het dus geen cijfer meer is, dan is het terug een bewerking
					return Integer.parseInt(getal.toString());
				}
			}
			
			return Integer.parseInt(getal.toString());
		}






		private static String voorDeling(String vergelijking, int indexOfDeel) {
			
			StringBuilder sb = new StringBuilder();
			
			for(int i =indexOfDeel-1 ; i>-1 ; i--) {
				char c = vergelijking.charAt(i);
				if(Character.isDigit(c) || c =='*') {
					sb.append(c);
				}
				else {
					return sb.reverse().toString();
				}
			}
			return sb.reverse().toString();
		}






		private static int[] telAantalKeerBewerkingen(String vergelijking) {
			int aantalPlus = 0;
			int aantalMin = 0;
			int aantalMaal =0;
			int aantalDelen = 0;
			
			char c;
			
			for(int i=0; i<vergelijking.length(); i++) {
				c = vergelijking.charAt(i);
				if(c == '+') {aantalPlus++;}
				else if(c == '-') {aantalMin++;}
				else if(c == '*') {aantalMaal++;}
				else if(c ==':') {aantalDelen++;}
			}
			
			int [] waardes = new int[4];
			waardes[0] = aantalPlus;
			waardes[1] = aantalMin;
			waardes[2] = aantalMaal;
			waardes[3] = aantalDelen;
			
			return waardes;
		}
		
		private static int locatieEerstVolgende (String vergelijking , char c) {
			for(int i=0; i<vergelijking.length() ; i++) {
				
				if(vergelijking.charAt(i) == c) {
					return i;
					}
			}
			
			System.out.println("probleem in lociatieEerstVolgende, er is geen bewerking: "+c+" gevonden");
			return -1;
		}
		
		
		private static int aantalDigits(int getalDerna) {
			String getal = getalDerna+"";
			return getal.length();
		}

		private static int pakGetalDervoor(String vergelijking, int i) {
			StringBuilder getal = new StringBuilder();
			
			for(int x=i-1 ; x>-1; x--) {
				char karakter = vergelijking.charAt(x);
				if(Character.isDigit(karakter)) {
					int a = karakter -48;
					getal.append(a);
					}
				else {
					//als het dus geen character meer is
					getal = getal.reverse();
					return (Integer.parseInt(getal.toString()));
				}
			}
			getal = getal.reverse();
			return (Integer.parseInt(getal.toString()));
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


