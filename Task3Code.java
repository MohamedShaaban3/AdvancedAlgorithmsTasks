import java.util.NoSuchElementException;
public class Task3Code {
	public static class TwoColorDoubleStack<T> {
	    private T[] arr;
	    private int redtop;
	    private int bluetop;
	    
	    public TwoColorDoubleStack(int capacity) {
	        arr = (T[]) new Object[capacity];
	        redtop = -1;
	        bluetop = capacity;
	    }
	    
	    public void rPush(T item) {
	        if (redtop + 1 >= bluetop) {
	            throw new StackOverflowError("Stack overflow");
	        }
	        arr[++redtop] = item;
	    }
	    
	    public T rPop() {
	        if (redtop < 0) {
	            throw new NoSuchElementException("Stack underflow");
	        }
	        return arr[redtop--];
	    }
	    
	    public boolean isRedEmpty() {
	        return redtop < 0;
	    }
	    
	    public void bPush(T item) {
	        if (bluetop - 1 <= redtop) {
	            throw new StackOverflowError("Stack overflow");
	        }
	        arr[--bluetop] = item;
	    }
	    
	    public T bPop() {
	        if (bluetop >= arr.length) {
	            throw new NoSuchElementException("Stack underflow");
	        }
	        return arr[bluetop++];
	    }
	    
	    public boolean isBlueEmpty() {
	        return bluetop >= arr.length;
	    }
	}
	    public static void main(String[] args) {
        TwoColorDoubleStack<String> stack = new TwoColorDoubleStack<>(5);

    
        stack.rPush("r1");
        stack.rPush("r2");

  
        stack.bPush("b1");
        stack.bPush("b2");

     
        System.out.println(stack.rPop()); 
        System.out.println(stack.rPop()); 

       
        System.out.println(stack.bPop()); 
        System.out.println(stack.bPop()); 

        
        try {
            stack.rPop();
        } catch (NoSuchElementException e) {
            System.out.println("Caught exception: " + e.getMessage()); 
        }
    }

}
