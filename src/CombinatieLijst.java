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
    

}
