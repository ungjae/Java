package sort;

public class Sort{

	public static <T extends Comparable<T>> void bubbleSort(T[] array){
		for(int i=0; i<array.length; i++){
			for(int j=0; j<array.length - 1; j++){
				if(array[j].compareTo(array[j+1]) > 0){
					T temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
		}
	}

	public static <T extends Comparable<T>> void selectionSort(T[] array){
		for(int i=0; i<array.length; i++){
			int smallest = i;
			for(int j=i; j<array.length; j++){
				if(array[j].compareTo(array[smallest]) < 0)
					smallest = j;
			}
			T temp = array[i];
			array[i] = array[smallest];
			array[smallest] = temp;
		}
	}

	public static <T extends Comparable<T>> void insertionSort(T[] array){
		for(int i=1; i<array.length; i++){
			T temp = array[i];
			int pos = i;
			for(int j=i-1; j>=0; j--){
				if(array[j].compareTo(temp) > 0){
					array[pos--] = array[j];
				}
			}
			array[pos] = temp;
			for(T n : array)
				System.out.print(n + " ");
			System.out.println();
		}
	}

}
