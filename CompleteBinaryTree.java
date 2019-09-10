/*
Date: 4/30/19
Input: none
Output: none
Purpose: To implement operations on a reference-based tree data structure, and uphold characteristics
of a complete binary tree while making use of generics. This implementation makes use of recursion to
traverse through the current CBT object and it's children, depending on the method being called.
*/
public class CompleteBinaryTree<T> {


    private T item; // the item stored at this node
    private int size; // the number of nodes in the subtree
    private CompleteBinaryTree<T> leftChild; // reference to the left subtree
    private CompleteBinaryTree<T> rightChild; // reference to the right subtree



    // This constructor adds the new item, sets both children to null
    // and the size (of the subtree rooted at this node) to 1.
    public CompleteBinaryTree(T newItem) {
        this.item = newItem;
        this.size = 1;
        this.leftChild = null;
        this.rightChild = null;
    } // end constructor



    // This method returns the number of items within the subtree
    // rooted at this node.
    public int size() {
        return this.size;
    } // end size



    // This method is a helper function used by the add method that
    // determines, at a given CompleteBinaryTree node, whether or not
    // the next recursive call should occur on the left subtree or not.
    private boolean goLeft() {

        //CITATION: in order to find log base 2 of a number, (which is required for this
        //method to determine what height the size is currently located at) I looked up
        //a way to "find log base 2 of a number" in java. I found the answer here:
        //http://blog.dreasgrech.com/2010/02/finding-logarithm-of-any-base-in-java.html
        double log = Math.floor(Math.log(this.size)  / Math.log(2));

        //calculate the max number of nodes on the bottom level of the current CBT object
        //by raising 2 to the power of the log base 2 calculated via size
        //resulting number is is the maximum amount of nodes possible at the bottom level
        int max = (int)Math.floor(Math.pow( 2 , log ));

        //calculate how many nodes are available in the bottom level of the current CBT object
        //formula was created by observing patterns in max nodes and nodes taken up via any
        //given size value
        int usableAtBottom = Math.abs(this.size - ((max * 2) - 1));

        //if there are no usable nodes on bottom, the "add" will occur on the next row
        //of leaf slots (which is like multiplying "max" by an additional power of 2)
        if(usableAtBottom == 0)
            usableAtBottom = max * 2;

        //return if current usableAtBottom value is greater than half the possible nodes
        //value. if it is, return true since they're are available spots on the left
        return (usableAtBottom > (max / 2));

    } // end goLeft



    // This method creates a new CompleteBinaryTree node containing
    // the newItem and adds it to the complete tree as a leaf in the
    // next available location
    public void add(T newItem) {

        //recursively call "add" function: tree is traversed after determining
        //which direction to go via goLeft method.
        //if BOTH children do not equal "null" there are more CBTs to traverse
        //until a potential leaf position is reached
        if (this.leftChild != null && this.rightChild != null){
            if ( this.goLeft() ) {
                leftChild.add(newItem);
                this.size += 1;

            }
            else {
                rightChild.add(newItem);
                this.size += 1;
            }
        }

        //if previous if statement does not run, a potential leaf position has been found
        //create CBT to be appended to current CBT object at located position
        //this CBT object will be added on left or right depending on open slots
        CompleteBinaryTree<T> addition = new CompleteBinaryTree<T>(newItem);

        //new leaf will be added on left since its currently null
        //Takes priority to uphold CBT characteristics
        if (this.leftChild == null) {
            this.leftChild = addition;
            this.size += 1;

        }
        //OR------------------------------------------------------

        //new leaf will be added on right since its currently null
        else if (this.rightChild == null) {
            this.rightChild = addition;
            this.size += 1;

        }

    } // end add



    // This method determines whether or not checkItem is in the tree
    public boolean contains(T checkItem) {

    //2 base cases
        //if checkItem is located at current CBT, return true since item was found
        if (this.item == checkItem) {
            return true;
        }
        //if a leaf is found and item was never located, return false
        else if (this.leftChild == null){
            return false;
        }
    //end base cases

        //if right child is null but left is not, recursively call contains method on left child
        if(this.rightChild == null){
            leftChild.contains(checkItem);
        }

        //else recursively call the contains method on both the left and right children on the
        //current CBT object until true is returned
        else{
            boolean search;

            //search the left subtree for the item recursively
            //if item is found, return true
            search = leftChild.contains(checkItem);
            if (search)
                return true;

            //search the right subtree for the item recursively
            //if item is found, return true
            search = rightChild.contains(checkItem);
            if (search)
                return true;
        }

        //return "false" if base case was never reached
        return false;

    } // end contains



    // This method converts the data structure into a Java String.
    public String toString() {
        if ((leftChild == null) && (rightChild == null))
            return "(" + this.item.toString() + ", "
                    + this.size + ", null, null)";
        else if (leftChild == null)
            return "(" + this.item.toString() + ", "
                    + this.size + ", null, "
                    + rightChild.toString() + ")";
        else if (rightChild == null)
            return "(" + this.item.toString() + ", "
                    + this.size + ", "
                    + leftChild.toString() + ", null)";
        else
            return "(" + this.item.toString() + ", "
                    + this.size + ", "
                    + leftChild.toString() + ", "
                    + rightChild.toString() + ")";
    } // end toString
} // end CompleteBinaryTree