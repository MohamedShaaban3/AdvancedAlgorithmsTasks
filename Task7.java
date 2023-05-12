import java.util.*;

public class HeapSort {

    public static void main(String[] args) {
        int[] arr = {5, 9, 3, 1, 8, 6, 4, 2, 7};
        System.out.println("Unsorted array: " + Arrays.toString(arr));

        heapSort(arr);
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }

    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Build the min heap
        for (int i = n/2 - 1; i >= 0; i--) {
            minHeapify(arr, n, i);
        }

        // Extract the minimum element from the heap one by one
        for (int i = n-1; i >= 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Call minHeapify on the reduced heap
            minHeapify(arr, i, 0);
        }
    }

    public static void minHeapify(int[] arr, int n, int i) {
        int smallest = i; // Initialize smallest as root
        int left = 2*i + 1; // Left child index
        int right = 2*i + 2; // Right child index

        // check whether the left child is smaller than the root of the subtree
        if (left < n && arr[left] < arr[smallest]) {
            smallest = left;
        }

       
        if (right < n && arr[right] < arr[smallest]) {
            smallest = right;
        }

        // making the smallest variable the root
        if (smallest != i) {
            // Swapping
            int temp = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = temp;

            // call the function recursively
            minHeapify(arr, n, smallest);
        }
    }
}
