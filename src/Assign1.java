import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class Assign1 {
	/*
	 * From Data Structures and Algorithms 2nd Edition p.474
	 */
	public static void selectionsort(int[] data) {
		int i,j,least;
		for (i = 0; i < data.length-1; i++) {
		for (j = i+1, least = i; j < data.length; j++)
		if (((Comparable<Integer>)data[j]).compareTo(data[least]) < 0)
		least = j;
		if(i != least)
		swap(data,least,i);
			}
		}
	/*
	 * From Data Structures and Algorithms 2nd Edition p.474
	 */
	public static void swap(int[] a, int e1, int e2) {
		int tmp = a[e1]; 
		a[e1] = a[e2]; 
		a[e2] = tmp;
		}
	/*
	 * From Data Structures and Algorithms 2nd Edition p.471
	 */
	public static void insertionsort(int[] data) {
		for (int i = 1,j; i < data.length; i++) {
			int tmp = data[i];
			for (j = i; j > 0 && tmp < data[j-1]; j--)
				data[j] = data[j-1];
			data[j] = tmp;
		}
	}
	/*
	 * From Data Structures and Algorithms 2nd Edition p.495
	 */
	public static void merge(int[] array1, int first, int last) {
		int mid = (first + last) / 2;
		int i1 = 0;
		int i2 = first;
		int i3 = mid + 1;
		int[] temp = new int[array1.length];
		while(i2 < mid && i3 < last) { //both left and right subarrays of array1 contain elements
		if (array1[i2] < array1[i3]) {
			temp[i1++] = array1[i2++];
		}
		else {
			temp[i1++] = array1[i3++];
		//load into temp the remaining elements of array1;
		//load to array1 the content of temp;
			}
		}
	}
	/*
	 * From Data Structures and Algorithms 2nd Edition p.496
	 */
	public static void mergesort (int[] data, int first, int last) {
	if (first < last){
		int mid = (first + last) / 2;
		mergesort(data, first, mid);
		mergesort(data, mid+1, last);
		merge(data, first, last);
		}
	}
	/*
	 * From Data Structures and Algorithms 2nd Edition p.489
	 */
	public static void quicksort(int[] data, int first, int last) {
		int lower = first + 1, upper = last;
		swap(data,first,(first+last)/2);
		Comparable<Integer> bound = (Comparable<Integer>)data[first];
		while (lower <= upper) {
			while (bound.compareTo(data[lower]) < 0) {
				lower++;
		}
		while (bound.compareTo(data[upper]) > 0) 
			upper--;
		if (lower < upper)
			swap(data,lower++,upper--);
		else lower++;
		}
		swap(data,upper,first);
		if (first < upper-1)
		quicksort(data,first,upper-1);
		if (upper+1 < last)
		quicksort(data,upper+1,last);
		}
	/*
	 * From Data Structures and Algorithms 2nd Edition p.489
	 */
	public static void sort(int[] data) {
		if (data.length < 2)
			return;
		int max = 0;
		// find the largest element and put it at the end of data;
		for (int i = 1; i < data.length; i++)
		if (((Comparable<Integer>)data[max]).compareTo(data[i]) < 0)
			max = i;
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
		long stopTime;
		long elapsedTime;
		String[] fargs = { "descending", "1000000", "quick", "out.txt" };
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
		
		//call to some sort function
		startTime = System.currentTimeMillis(); //timing code adapted from http://www.vogella.com/tutorials/JavaAlgorithmsQuicksort/article.html
		if(alg.equals("selection")) {
			selectionsort(numbers);
		}
		else if(alg.equals("insertion")) {
			insertionsort(numbers);
		}
		else if(alg.equals("merge")) {
			mergesort(numbers, 0, numbers.length - 1);
		}
		else if(alg.equals("quick")) {
			sort(numbers);
		}
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println("Time elapsed for " + alg + " sort : " + elapsedTime + "ms.");
		try {
			writer = new PrintWriter(output);
			writer.println(Arrays.toString(numbers)); //Adapted from https://stackoverflow.com/questions/409784/whats-the-simplest-way-to-print-a-java-array
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		

	}

}
