package unl.cse.sorting;

import java.util.Arrays;

public class SortingAlgorithms {

	public static Location[] javaSort(Location list[]) {
		Location result[] = Arrays.copyOf(list, list.length);
		Arrays.sort(result);
		return result;
	}

	public static Location[] selectionSort(Location list[]) {
		Location result[] = Arrays.copyOf(list, list.length);
		for (int i = 0; i < result.length; i++) {
			int minIndex = i;
			for (int j = i + 1; j < result.length; j++) {
				if (result[j].compareTo(result[minIndex]) < 0)
					minIndex = j;
			}
			Location tmp = result[i];
			result[i] = result[minIndex];
			result[minIndex] = tmp;
		}
		return result;
	}

	public static Location[] insertionSort(Location list[]) {
		Location result[] = Arrays.copyOf(list, list.length);

		int i, j;

		for (i = 1; i < list.length; i++) {
			Location temp = list[i];
			j = i;
			while (j > 0 && list[j - 1].getCity().compareTo(temp.getCity()) > 0) {
				list[j] = list[j - 1];
				--j;
			}
			list[j] = temp;
		}

		return result;
	}

	public static Location[] quickSort(Location list[]) {
		Location result[] = Arrays.copyOf(list, list.length);
		quickSortRecursive(result, 0, result.length - 1);
		return result;
	}

	private static void quickSortRecursive(Location list[], int low, int high) {

		int idx = partition(list, low, high);

		if (low < idx - 1) {
			quickSortRecursive(list, low, idx - 1);
		}

		if (high > idx) {
			quickSortRecursive(list, idx, high);
		}

	}

	public static int partition(Location[] list, int left, int right) {
		Location pivot = list[left];
		while (left <= right) {
			while (list[left].compareTo(pivot) < 0) {
				left++;
			}
			while (list[right].compareTo(pivot) > 0) {
				right--;
			}

			if (left <= right) {
				Location tmp = list[left];
				list[left] = list[right];
				list[right] = tmp;
				left++;
				right--;
			}
		}
		return left;
	}
	
	public static void main(String args[]) {
		
		
		SortingPerformance sp = new SortingPerformance(64000);
		
		long start = System.nanoTime();
		Location stored[] = SortingAlgorithms.insertionSort(sp.getLocations());
		long end = System.nanoTime();
		long elapsedTime = (end - start);
		double seconds  = (double)elapsedTime/1000000000.00;
		
		System.out.println("The insertion sort took " + seconds + " seconds");
		
		start = System.nanoTime();
		Location stored2[] = SortingAlgorithms.quickSort(sp.getLocations());
		end = System.nanoTime();
		elapsedTime = (end - start);
		seconds  = (double)elapsedTime/1000000000.00;
		
		System.out.println("The quick sort took " + seconds + " seconds");
		
		start = System.nanoTime();
		Location stored3[] = SortingAlgorithms.javaSort(sp.getLocations());
		end = System.nanoTime();
		elapsedTime = (end - start);
		seconds  = (double)elapsedTime/1000000000.00;
		
		System.out.println("The java sort took " + seconds + " seconds");
		
		start = System.nanoTime();
		Location stored4[] = SortingAlgorithms.selectionSort(sp.getLocations());
		end = System.nanoTime();
		elapsedTime = (end - start);
		seconds  = (double)elapsedTime/1000000000.00;
		
		System.out.println("The selection sort took " + seconds + " seconds");
	}
}
