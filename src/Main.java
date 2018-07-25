
public class Main {

	public static void main(String[] args) {
		
		int[] sequence = new int [3];
		sequence[0] = 1;
		sequence[1] = 2;
		sequence[2] = 3;
		
		permute(sequence, 0);
		
		
		
	}
	
	//https://www.sanfoundry.com/java-program-generate-all-possible-combinations-given-list-numbers/
	static void permute(int[] a, int k) 
    {
        if (k == a.length) 
        {
            for (int i = 0; i < a.length; i++) 
            {
                System.out.print(a[i]);
            }
            System.out.println();
        } 
        else 
        {
            for (int i = k; i < a.length; i++) 
            {
                int temp = a[k];
                a[k] = a[i];
                a[i] = temp;
 
                permute(a, k + 1);
 
                temp = a[k];
                a[k] = a[i];
                a[i] = temp;
            }
        }
    }
	
	
}


