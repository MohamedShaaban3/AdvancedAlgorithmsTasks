package project1;
import java.util.*;
class Main
{
public static void main(String[] args) 
  {
    // Create arrays, sort them and check for equality
    Object[] a = new Object[] {2,3,5,9};
    Object[] b = new Object[] {2,3,5,9};
    
      
    Arrays.sort(a);
    Arrays.sort(b);
  

    boolean returnVal1 = Arrays.equals(a,b);
    System.out.println("Array a and b equal?: " + returnVal1);

  }
}
