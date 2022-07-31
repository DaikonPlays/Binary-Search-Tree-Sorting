/**
 * Name: Kevin Yan
 * Email: kevinyan904@gmail.com
 * Sources used: None
 * 
 * Custom tests for MyBST.java, MyBSTIterator.java,
 * and MyCalendar.java. Focuses on edge cases
 */
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * class containing all tests
 */
public class CustomTester {
    MyBST<Integer, Integer> testTree;
    /**
     * sets up the BST 
     */
    @Before
    public void setup() {
        MyBST.MyBSTNode<Integer, Integer> root = 
            new MyBST.MyBSTNode<>(6, 5, null);
        MyBST.MyBSTNode<Integer, Integer> left1 = 
            new MyBST.MyBSTNode<>(4, 1, root);
        MyBST.MyBSTNode<Integer, Integer> right1 = 
            new MyBST.MyBSTNode<>(8, 3, root);
        MyBST.MyBSTNode<Integer, Integer> left2 = 
            new MyBST.MyBSTNode<>(2, 2, left1);
        MyBST.MyBSTNode<Integer, Integer> right2 = 
            new MyBST.MyBSTNode<>(5, 10, left1);
        MyBST.MyBSTNode<Integer, Integer> left3 = 
            new MyBST.MyBSTNode<>(7, 11, right1);
        this.testTree = new MyBST<>();
        this.testTree.root = root;
        root.setLeft(left1);
        root.setRight(right1);
        left1.setLeft(left2);
        left1.setRight(right2);
        right1.setLeft(left3);
        this.testTree.size = 6;
    }
    /**
     * tests predecessor if node is the smallest
     */
    @Test
    public void testNodeSmallest() {
        MyBST.MyBSTNode<Integer, Integer> smallest = testTree.root;
        assertNull("smallest node return null",smallest.getLeft().getLeft().predecessor());
    }
    /**
     * tests insert when key is null
     */
    @Test
    public void testInsert() {
        boolean test = false;
        try{
            testTree.insert(null, 110);
        }
        catch(Exception e) {
            test = true;
        }
        assertTrue(test);
    }   
    /**
    * tests insert when key is the root
    */
   @Test
   public void testInsertRoot() {
    MyBST.MyBSTNode<Integer, Integer> root = testTree.root; 
    testTree.insert(6, 55);
    assertEquals((Integer) 55, root.getValue());
    assertEquals(6, testTree.size);
   }
    /**
     * tests search when key is null
     */
    @Test
    public void testSearch() {
        assertNull(testTree.search(null));
    }
    /**
     * tests search when key is not present
     */
    @Test
    public void testSearchNonExist() {
        assertNull(testTree.search(10));
    }
    /**
     * tests remove when key is null
     */
    @Test
    public void testRemove() {
        assertNull(testTree.remove(null));
        assertEquals(6 ,testTree.size);
    }    
    /**
    * tests remove when node removed has a single child
    */
   @Test
   public void testRemoveSingle() {
       testTree.remove(8);
       assertEquals((Integer) 7 ,testTree.root.getRight().getKey());
       assertEquals((Integer) 11 ,testTree.root.getRight().getValue());

   }
    /**
     * tests inorder when tree is empty
     */
    @Test
    public void testInorder(){
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> expected 
            = new ArrayList<>();
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> actual 
            = testTree.inorder();
        for (int i = 0; i < expected.size(); i++){
            assertEquals(expected.get(i), actual.get(i));
        }
    }
    /**
     * tests nextNode if there is no next node
     */
    @Test
    public void testnextNode(){
        boolean test = false;
        MyBSTIterator<Integer, Integer> iterator = new MyBSTIterator<>();
        iterator.root = testTree.root.getLeft().getLeft();
        MyBSTIterator<Integer, Integer>.MyBSTValueIterator start = 
            iterator.new MyBSTValueIterator(iterator.root);
        start.next = null;
        try{
            start.nextNode();
        }
        catch(Exception e) {
            test = true;
        }
        assertTrue(test);
    }
    /**
     * tests calendar when start is 
     */
    @Test
    public void testCalender(){
        MyCalendar calendar = new MyCalendar();
        boolean test = false;
        try{
            assertTrue(calendar.book(50, 5));
        }
        catch(Exception e) {
            test = true;
        }
        assertTrue(test);
    }
}
