import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;
/*
 * This is the main class for this program for CPSC 319 Assignment 1. It is meant to accept arguments
 * from the command line to test 4 types of sorting methods under 3 different conditions of how an
 * array is sorted. The main outputs are the run time of specified methods and a text file containing 
 * the sorted arrays elements.
 * @author Jonathan Yee
 * @version 1.0
 * @since Jan 20, 2018
 */
public class Assign1 {
	/*
	 * Adapted from Data Structures and Algorithms 2nd Edition, Drozdek p.474
	 */
	public static void selectionsort(int[] data) {
		int i = 0, j, least;
		while(i < data.length - 1) {
			j = i + 1;
			least = i;
			while(j < data.length) {
					if (data[j] < data[least]) {
					least = j;
					}
				if(i != least) {
					swap(data,least,i);
					}
				j++;
				}// end of inner loop
			i++;
			}//end of outer loop
		}
	/*
	 * Adapted from Data Structures and Algorithms 2nd Edition, Drozdek p.474
	 */
	public static void swap(int[] a, int e1, int e2) {
		int tmp = a[e1]; 
		a[e1] = a[e2]; 
		a[e2] = tmp;
		}
	/*
	 * Adapted from Data Structures and Algorithms 2nd Edition, Drozdek p.471
	 */
	public static void insertionsort(int[] data) {
		int i = 1, j;
		while(i < data.length) {
			j = i;
			int tmp = data[i];
			while(j > 0 && tmp < data[j-1]) {
				data[j] = data[j-1];
			j--;
			}//end of inner loop
			data[j] = tmp;
			i++;
		}//end of outer loop
	}
	/*
	 * From https://gist.github.com/cocodrips/5937371
	 */
	public static void mergeSort(int[] array, int low, int high){
		if(low < high){
			int middle = (low + high) / 2;
			mergeSort(array, low, middle);
			mergeSort(array, middle+1, high);
			merge(array, low, middle, high);
		}	
	}
	/*
	 * From https://gist.github.com/cocodrips/5937371
	 */
	public static void merge(int[] array, int low, int middle, int high){
		int[] helper = new int[array.length];
		for (int i = low; i <= high; i++) {
			helper[i] = array[i];
		}
		
		int helperLeft = low;
		int helperRight = middle+1;
		int current = low;
		
		while (helperLeft <= middle && helperRight <=high) {
			if(helper[helperLeft] <= helper[helperRight]){
				array[current] = helper[helperLeft];
				helperLeft++;
				
			}else{
				array[current] = helper[helperRight];
				helperRight++;
			}
			current ++;		
		}
		
		int remaining = middle - helperLeft;
		for (int i = 0; i <= remaining; i++) {
			array[current+i] = helper[helperLeft+ i];
		}
	}
	/*
	 * Adapted from Data Structures and Algorithms 2nd Edition, Drozdek p.489
	 */
	public static void quicksort(int[] data, int first, int last) {
		int lower = first + 1, upper = last;
		swap(data,first,(first+last)/2);
		int bound = data[first];
		while (lower <= upper) {
			while (bound > data[lower])
				lower++;
		while (bound < data[upper]) 
			upper--;
		if (lower < upper)
			swap(data,lower++,upper--);
		else 
			lower++;
		}
		swap(data,upper,first);
		if (first < upper-1)
			quicksort(data,first,upper-1);
		if (upper+1 < last)
			quicksort(data,upper+1,last);
		}
	/*
	 * Adapted from Data Structures and Algorithms 2nd Edition, Drozdek p.489
	 */
	public static void quicksort(int[] data) {
		if (data.length < 2)
			return;
		int max = 0;
		// find the largest element and put it at the end of data;
		for (int i = 1; i < data.length; i++) {
			if (data[max] < data[i]){
				max = i;
			}
		}
		swap(data,data.length-1,max); 		// largest el is now in its
		quicksort(data,0,data.length-2); 	// final position;
		}
	public static void main(String[] args) {
		int size;
		String order;
		String alg;
		String output;
		int[] numbers;
		PrintWriter writer;
		long startTime; 
		long elapsedTime;
		//check if correct number of arguments were given
		if(args.length != 4) {
			System.out.println("Incorrect number of inputs. Quitting...");
			System.exit(-1);
		}		
		//check if negative array size given or is NaN
		 try {
			    Integer.parseInt(args[1]);
			  } catch (NumberFormatException e) {
			    System.out.println("Array size is not a number. Quitting...");
			    System.exit(-1);
			  }
		if(Integer.parseInt(args[1]) <= 0 ) {
			System.out.println("Illegal array size. Must be a positive integer. Quitting...");
			System.exit(-1);
		}
		size = Integer.parseInt(args[1]);
		order = args[0];
		//check if contains proper choices
		if(!order.equals("ascending")&&!order.equals("descending")&&!order.equals("random")){
			System.out.println((!order.equals("ascending"))&&(!order.equals("descending")));
			System.out.println("Unable to identify order used for the test. Quitting...");
			System.exit(-1);
		}
		
		//check if contains proper choices
		if(!args[2].equals("selection")&&!args[2].equals("insertion")&&!args[2].equals("merge")&&!args[2].equals("quick")) 
		{
			System.out.println("Unable to identify sort type. Quitting...");
			System.exit(-1);
		}
		alg = args[2];
		output = args[3];
		
		numbers = new int[size]; 	//adapted from http://www.vogella.com/tutorials/JavaAlgorithmsQuicksort/article.html
		if(order.equals("ascending")) {
			for(int i = 0; i < size; i++) {
				numbers[i] = i + 1; //added 1 so that 0 is not an element so that this array matches the
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
		//call to some sort function, start timing it
		startTime = System.nanoTime(); 
		if(alg.equals("selection")) {
			selectionsort(numbers);
		}
		else if(alg.equals("insertion")) {
			insertionsort(numbers);
		}
		else if(alg.equals("merge")) {
			mergeSort(numbers, 0, numbers.length - 1);
		}
		else if(alg.equals("quick")) {
			quicksort(numbers);
		}
		elapsedTime = System.nanoTime() - startTime;
		System.out.println("Time elapsed for " + alg + " sort: " + elapsedTime/1000000000.0 + " seconds.");
		try {
			writer = new PrintWriter(output);
			writer.println(elapsedTime/1000000000.0);
			writer.println(Arrays.toString(numbers)); //Adapted from https://stackoverflow.com/questions/409784/whats-the-simplest-way-to-print-a-java-array
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("Unable to access file to print sorted array contents.");
			e.printStackTrace();
		}
		System.out.println("Test complete. Exiting.");
	}
}
