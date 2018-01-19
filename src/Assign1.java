import java.util.Random;

public class Assign1 {

	public static void main(String[] args) {
		int size;
		String order;
		String alg;
		String output;
		int[] numbers;
		String[] fargs = { "random", "10", "selection", "out.txt" };
		//System.out.println(args.length);
		//if(args.length != 6) {
		//	System.out.println("Incorrect number of inputs. Quitting...");
		//}
				
		size = Integer.parseInt(fargs[1]);
		//check if negative
		order = fargs[0];
		//check if contains proper choices
		alg = fargs[2];
		//check if contains proper choices
		output = fargs[3];
		
		numbers = new int[size]; 	//adapted from http://www.vogella.com/tutorials/JavaAlgorithmsQuicksort/article.html
		if(order.equals("ascending")) {
			for(int i = 0; i < size; i++) {
				numbers[i] = i + 1; //add 1 so that 0 is not an element so that this array matches the
									//descending array in reverse
			}
		}
		else if(order.equals("descending")) {
			for(int i = 0; i < size; i++) {
				numbers[i] = size - i;
			}
		}
		else if(order.equals("random")) {
			Random generator = new Random(); //
			for(int i = 0; i < size; i++) {
				numbers[i] = generator.nextInt(size);
			}
		}
		
		
		for(int i = 0; i < size; i++) {
			System.out.println(numbers[i]);
		}
		

	}

}
