import java.util.HashSet;

public class Project1 {
    public static void removeDuplicates(char arr[], int n) {
        // create hashset
        HashSet<Character> uniqueChars = new HashSet<>();
        int j = 0;
        // remove not unique characters
        for (int i = 0; i < n; i++) {
            if (!uniqueChars.contains(arr[i])) {
                arr[j] = arr[i];
                uniqueChars.add(arr[i]);
                j++;
            }
        }
        // print the array without duplication
        for (int k = 0; k < j; k++) {
            System.out.print(arr[k] + " ");
        }
    }
    
    public static void main(String[] args) {
        char arr[] = {'a', 'b', 'a', 'c', 'd'};
        int length = arr.length;
        removeDuplicates(arr, length);
    }
}
