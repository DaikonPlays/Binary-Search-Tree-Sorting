/**
 * Name: Kevin Yan
 * Email: kevinyan904@gmail.com
 * Sources used: zybooks, tutors
 * 
 * API for a binary search tree. Contains
 * all the methods used to create a bst
 */
import java.util.ArrayList;

import org.junit.runners.Suite.SuiteClasses;

/**
 * class containing methods for BST. edits the 
 * BST
 * Instance variables:
 * root, root node of BST
 * size, size of BST
 */
public class MyBST<K extends Comparable<K>,V>{
    MyBSTNode<K,V> root = null;
    int size = 0;
    /**
     * gets size
     * @return size of BST
     */
    public int size(){
        return size;
    }
    /**
     * inserts a node into the BST based off BST
     * properties
     * @param key place to put the value
     * @param value vale to be inserted
     * @return null if no value is replaced
     * @return vale of replaced key
     */
    public V insert(K key, V value){
        if(key == null) {
            throw new NullPointerException();
        }
        // //creates node to be inserted 
        MyBSTNode<K, V> insertNode = new MyBSTNode<K,V>(key, value, null);
        MyBST.MyBSTNode<K, V> curr = this.root;
        //inserts at root if no root
        if(curr == null) {
            root = insertNode;
            size++;
        }
        //when curr is not null loop
        while(curr != null){
            //checks to go left on BST
            if(key.compareTo(curr.getKey()) < 0) {
                if(curr.getLeft() == null) {
                    curr.setLeft(insertNode);
                    insertNode.setParent(curr);
                    size++;
                    return null;
                }
                curr = curr.getLeft();
            }
            //checks to go right on BST
            else if(key.compareTo(curr.getKey()) > 0) {
                if(curr.getRight() == null) {
                    curr.setRight(insertNode);
                    insertNode.setParent(curr);
                    size++;
                    return null;
                }
                curr = curr.getRight();
            }
            else {
                //saves deketed vakue
                V deletedVal = curr.getValue();
                //overrides the value
                curr.setValue(value);
                return deletedVal;
            }
        }
        return null;
    }
    /**
     * finds the value at certain key
     * @param key of node to get value
     * @return value of key
     */
    public V search(K key){
        MyBSTNode<K,V> curr = root;
        if (key == null) {
            return null;
        }
        while(curr != null) {
            //if the node is at the key
            if(key == curr.key) {
                return curr.value;
            }
            //if key is greater than curr key
            else if(key.compareTo(curr.getKey()) > 0){
                 curr = curr.getRight();
            }
            //if key is less than curr key
            else if(key.compareTo(curr.getKey()) < 0){
                curr = curr.getLeft();
           }
        }
        return null;
    }
    /**
     * removes the key at the input
     * @param key of node to be removed
     * @return the value to be removed 
     */
    public V remove(K key){
        MyBSTNode<K,V> curr = root;
        V removed;
        if(key == null) {
            return null;
        }
        while(curr != null) {
            if(key == curr.key) {
                removed = curr.value;
                //checks if it's a leaf node
                if(curr.getLeft() == null && curr.getRight() == null) {
                    //checks if root is the leaf node
                    if(curr == root) {
                        root = null;
                    }
                    //checks if the node is the right child
                    else if(curr == curr.parent.getRight()) {
                        curr.getParent().setRight(null);
                    }
                    else{
                        curr.getParent().setLeft(null); 

                    }
                    size--;
                }
                //checks if there is no right child node
                else if(curr.getRight() == null) {
                    if(curr == root) {
                        root = curr.getLeft();
                    }
                    else if(curr == curr.parent.getRight()) {
                        //updates curr properly to remove it
                        curr.getLeft().setParent(curr.getParent());
                        curr.getParent().setRight(curr.getLeft());
                    }
                    else{
                        //updates curr properly to remove it
                        curr.getLeft().setParent(curr.getParent());
                        curr.getParent().setLeft(curr.getLeft()); 
                    }
                    size--;
                }
                //checks if there is no left child
                else if(curr.getLeft() == null) {
                    if(curr == root) {
                        root = curr.getRight();
                    }
                    else if(curr == curr.getParent().getRight()) {
                        //updates curr properly to remove it
                        curr.getRight().setParent(curr.getParent());
                        curr.getParent().setRight(curr.getRight());

                    }
                    else{
                        //updates curr properly to remove it
                        curr.getRight().setParent(curr.getParent());
                        curr.getParent().setLeft(curr.getRight()); 
                    }
                    size--;
                }
                else{
                    MyBSTNode<K,V> next = curr.successor();
                    if(curr == curr.getParent().getLeft()) {
                        curr.getParent().setLeft(next); 
                    }
                    else{
                        curr.getParent().setRight(next);
                    }
                    //updates curr and next children
                    curr.getLeft().setParent(next);
                    next.left = curr.getLeft();
                    size--;
                }
                return removed;
            }
            //moves curr down the tree accordingly
            else if(curr.key.compareTo(key) < 0) {
                curr = curr.getRight();
            }
            else{
                curr = curr.getLeft();
            }
        }
        return null;
    }
    /**
     * arranges ArrayList of nodes in order 
     * @return the ArrayList of ordered nodes
     */
    public ArrayList<MyBSTNode<K, V>> inorder(){
        ArrayList<MyBSTNode<K, V>> nodes = new ArrayList<>();
        MyBSTNode<K,V> curr = root;
        if(curr == null) {
            return new ArrayList<MyBSTNode<K, V>>();
        }
        //if there is a left node
        while(curr.getLeft() != null) {
            curr = curr.getLeft();
        }
        //if curr isn't the biggest
        while(curr != null) {
            nodes.add(curr);
            curr = curr.successor();
        }
        return nodes;
    }
    /**
     * class methods to get and set nodes in BST
     * Instance variables:
     * key, key of a certain node
     * value, value of the key at a certain node
     * parent, parent of a node
     */
    static class MyBSTNode<K,V>{
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";      
        private K key;
        private V value;
        private MyBSTNode<K,V> parent;
        private MyBSTNode<K,V> left = null;
        private MyBSTNode<K,V> right = null;

        /**
         * Creates a MyBSTNode<K,V> storing specified data
         * @param key the key the MyBSTNode<K,V> will
         * @param value the data the MyBSTNode<K,V> will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent){
            this.key = key;
            this.value = value;
            this.parent = parent; 
        }

        /**
         * Return the key stored in the the MyBSTNode<K,V>
         * @return the key stored in the MyBSTNode<K,V>
         */
        public K getKey(){
            return key;
        }

        /**
         * Return data stored in the MyBSTNode<K,V>
         * @return the data stored in the MyBSTNode<K,V>
         */
        public V getValue(){
            return value;
        }

        /**
         * Return the parent
         * @return the parent
         */
        public MyBSTNode<K,V> getParent(){
            return parent;
        }

        /**
         * Return the left child 
         * @return left child
         */
        public MyBSTNode<K,V> getLeft(){
            return left;
        }

        /**
         * Return the right child 
         * @return right child
         */
        public MyBSTNode<K,V> getRight(){
            return right;
        }

        /**
         * Set the key stored in the MyBSTNode<K,V>
         * @param newKey the key to be stored
         */
        public void setKey(K newKey){
            this.key = newKey;
        }

        /**
         * Set the data stored in the MyBSTNode<K,V>
         * @param newValue the data to be stored
         */
        public void setValue(V newValue){
            this.value = newValue;
        }

        /**
         * Set the parent
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K,V> newParent){
            this.parent = newParent;
        }

        /**
         * Set the left child
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K,V> newLeft){
            this.left = newLeft;
        }

        /**
         * Set the right child
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K,V> newRight){
            this.right = newRight;
        }

        /**
         * TODO: add inline comments for this method to demonstrate your
         *   understanding of this method. The predecessor can be implemented
         *   in a similar way.
         *
         * This method returns the in order successor of current node object.
         * It can be served as a helper method when implementing inorder().
         * @return the successor of current node object
         */
        public MyBSTNode<K, V> successor(){
            //checks if there is a successor
            if(this.getRight() != null){
                //saves the said successor 
                MyBSTNode<K,V> curr = this.getRight();
                //gets the left node until it is the last one
                while(curr.getLeft() != null){
                    //continuously updates curr till the end
                    curr = curr.getLeft();
                }
                //returns the last left node
                return curr;
            }
            else{
                //saves the parent node
                MyBSTNode<K,V> parent = this.getParent();
                MyBSTNode<K,V> curr = this;
                //loops while there is a parent node and curr is the right child of parent node
                while(parent != null && curr == parent.getRight()){
                    //updates curr up the tree to the parent
                    curr = parent;
                    //updates parent to its parent
                    parent = parent.getParent();
                }
                return parent;
            }
        }
        /**
         * returns the node before the current node in order. If current key is the
         * smallest, return null.
         * @return the inorder predecessor of a node
         */
        public MyBSTNode<K, V> predecessor(){
            if(this.getLeft() != null) {
                MyBSTNode<K,V> curr = this.getLeft();
                //keeps going to the right node
                while(curr.getRight() != null){
                    curr = curr.getRight();
                }
                return curr;
            }
            else {
                //saves the parent node
                MyBSTNode<K,V> parent = this.getParent();
                MyBSTNode<K,V> curr = this;
                //goes up the tree to the root if conditions are met
                while(parent != null && curr == parent.getLeft()) {
                    curr = parent;
                    parent = parent.getParent();
                }
                return parent;
            }
        }

        /** This method compares if two node objects are equal.
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        public boolean equals(Object obj){
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K,V> comp = (MyBSTNode<K,V>)obj;
            
            return( (this.getKey() == null ? comp.getKey() == null : 
                this.getKey().equals(comp.getKey())) 
                && (this.getValue() == null ? comp.getValue() == null : 
                this.getValue().equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         * @return "Key:Value" that represents the node object
         */
        public String toString(){
            return String.format(
                    TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }

}