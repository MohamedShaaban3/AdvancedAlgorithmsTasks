import java.util.Scanner;
class Node {
    // construct node class
    char data;
    Node left, right, parent;
    boolean isVariable;
    int variableValue;

    Node(char data) {
        this.data = data;
        this.left = this.right = this.parent = null;
        this.isVariable = false;
    }
}


public class ExpressionTree {
    static int variableCount = 0;
    // main function
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a fully parenthesized arithmetic expression: ");
        String input = scanner.nextLine();

        Node root = buildExpressionTree(input);

        printExpressionTree(root);

        evaluateExpressionTree(root);

        scanner.close();
    }

   public static Node buildExpressionTree(String input) {
    Node root = null;
    Node current = null;
    // go through the expression and build the tree
    for (int i = 0; i < input.length(); i++) {
        char c = input.charAt(i);

        if (c == '(') {
            if (current != null) {
                if (current.left == null) {
                    current.left = new Node('(');
                    current.left.parent = current; // set parent of new node
                    current = current.left;
                } else {
                    current.right = new Node('(');
                    current.right.parent = current; // set parent of new node
                    current = current.right;
                }
            } else {
                current = new Node('(');
            }

            if (root == null) {
                root = current;
            }
        } else if (c == ')') {
            current = current.parent; 
        } else if (Character.isDigit(c)) {
            Node number = new Node(c);
            number.parent = current; 

            if (current.left == null) {
                current.left = number;
            } else {
                current.right = number;
            }
        } else if (Character.isLetter(c)) {
            Node variable = new Node(c);
            variable.isVariable = true;
            variableCount++;
            variable.parent = current; 

            if (current.left == null) {
                current.left = variable;
            } else {
                current.right = variable;
            }
        } else if (c == '+' || c == '-' || c == '*' || c == '/') {
            current.data = c;
        }
    }

    return root;
}


    public static void printExpressionTree(Node root) {
        System.out.println("Expression Tree:");
        printExpressionTreeHelper(root, 0);
        System.out.println();
    }

    public static void printExpressionTreeHelper(Node node, int indentLevel) {
        // display the tree with indentation
        if (node == null) {
            return;
        }

        printExpressionTreeHelper(node.right, indentLevel + 1);

        for (int i = 0; i < indentLevel; i++) {
            System.out.print("\t");
        }

        if (node.isVariable) {
            System.out.println("x" + variableCount + " = " + node.variableValue);
        } else {
            System.out.println(node.data);
        }

        printExpressionTreeHelper(node.left, indentLevel + 1);
    }

    public static void evaluateExpressionTree(Node root) {
        // perform arithmatic operations on tree
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null && root.isVariable) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a value for x" + variableCount + ": ");
            root.variableValue = scanner.nextInt();
        } else {
            evaluateExpressionTree(root.left);
            evaluateExpressionTree(root.right);

            int left = root.left.isVariable ? root.left.variableValue : Character.getNumericValue(root.left.data);
            int right = root.right.isVariable ? root.right.variableValue : Character.getNumericValue(root.right.data);

            switch (root.data) {
                case '+':
                root.variableValue = left + right;
                break;
                case '-':
                root.variableValue = left - right;
                break;
                case '*':
                root.variableValue = left * right;
                break;
                case '/':
                root.variableValue = left / right;
                break;
                }
                }
                printExpressionTree(root);
}
}

