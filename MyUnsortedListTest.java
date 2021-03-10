package datastruct;

import datastruct.MyUnsortedList;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class MyUnsortedListTest {

    public final int DEFAULT_TIMEOUT = 3000;

    @Test(timeout = DEFAULT_TIMEOUT)
    public void isEmptyTest(){
        MyUnsortedList<Integer> testEmpty = MyUnsortedList.of();
        assertTrue("Empty list, should be true", testEmpty.isEmpty());
        MyUnsortedList<Integer> test123 = MyUnsortedList.of(1, 2, 3);
        assertFalse("None Empty list, should be false", test123.isEmpty());
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void prependTest(){
        MyUnsortedList<Integer> testEmpty = MyUnsortedList.of();
        MyUnsortedList<Integer> test1 = MyUnsortedList.of(1);
        testEmpty.prepend(1);
        assertTrue("Prepend test int from empty", testEmpty.equals(test1));

        MyUnsortedList<Integer> test21 = MyUnsortedList.of(2, 1);
        testEmpty.prepend(2);
        assertTrue("Prepend test int from none empty", testEmpty.equals(test21));

        testEmpty.prepend(null);
        assertFalse("Prepend test null", testEmpty.equals(test21));
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void appendTest(){
        MyUnsortedList<Integer> testEmpty = MyUnsortedList.of();
        MyUnsortedList<Integer> test1 = MyUnsortedList.of(1);
        testEmpty.append(1);
        assertTrue("Append test int from empty", testEmpty.equals(test1));

        MyUnsortedList<Integer> test12 = MyUnsortedList.of(1, 2);
        testEmpty.append(2);
        assertTrue("Append test int from none empty", testEmpty.equals(test12));

        testEmpty.append(null);
        assertFalse("Append test null", testEmpty.equals(test12));
    }



    @Test(timeout = DEFAULT_TIMEOUT)
    public void sizeTest(){
        MyUnsortedList<Integer> testEmpty = MyUnsortedList.of();
        MyUnsortedList<Integer> test1 = MyUnsortedList.of(1);
        MyUnsortedList<Integer> test5000 = randomIntListHelper(5000);
        assertTrue("Size test on empty", testEmpty.size() == 0);
        assertTrue("Size on singleton", test1.size() == 1);
        assertTrue("Size on list with 5000 elem", test5000.size() == 5000);
    }

    @Test(timeout = DEFAULT_TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void insertTest() throws Exception{
        MyUnsortedList<Integer> test13 = MyUnsortedList.of(1,3);
        MyUnsortedList<Integer> test123 = MyUnsortedList.of(1,2,3);
        test13.insert(2, 1);
        assertTrue("insert at pos 1", test123.equals(test13));
        //insert out of bounds :
        test13.insert(10, 5);
    }

    @Test(timeout = DEFAULT_TIMEOUT, expected = EmptyListException.class)
    public void popTest() throws Exception{
        //n elements test
        MyUnsortedList<Integer> test23 = MyUnsortedList.of(2,3);
        MyUnsortedList<Integer> test123 = MyUnsortedList.of(1,2,3);
        int popResult = test123.pop();
        assertTrue("Pop in n element list test", (test23.equals(test123) && popResult == 1));
        //1 element test
        MyUnsortedList<Integer> testEmpty = MyUnsortedList.of();
        MyUnsortedList<Integer> test1 = MyUnsortedList.of(1);
        popResult = test1.pop();
        assertTrue("Pop in singleton test", (testEmpty.equals(test1) && popResult == 1));
        //Empty list test:
        testEmpty.pop();
    }

    @Test(timeout = DEFAULT_TIMEOUT, expected = EmptyListException.class)
    public void popLastTest() throws Exception {
        //n elements test
        MyUnsortedList<Integer> test12 = MyUnsortedList.of(1, 2);
        MyUnsortedList<Integer> test123 = MyUnsortedList.of(1, 2, 3);
        int popResult = test123.popLast();
        assertTrue("PopLast in n element list test", (test12.equals(test123) && (popResult == 3)));
        //1 element test
        MyUnsortedList<Integer> testEmpty = MyUnsortedList.of();
        MyUnsortedList<Integer> test1 = MyUnsortedList.of(1);
        popResult = test1.pop();
        assertTrue("PopLast in singleton test", (testEmpty.equals(test1) && popResult == 1));
        //Empty list test:
        testEmpty.popLast();
    }

    @Test(timeout = DEFAULT_TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void removeTest() throws Exception {
        //Remove singleton
        MyUnsortedList<Integer> testEmpty = MyUnsortedList.of();
        MyUnsortedList<Integer> test1 = MyUnsortedList.of(1);
        int removedResult = test1.remove(0);
        assertTrue("Remove from  singleton", test1.equals(testEmpty)&&removedResult==1);
        //Remove from n element list
        //Remove nth element
        MyUnsortedList<Integer> test123 = MyUnsortedList.of(1, 2, 3);
        MyUnsortedList<Integer> test12 = MyUnsortedList.of(1, 2);
        removedResult = test123.remove(2);
        //assertTrue("Remove last element in n size list", test12.equals(test123)&&removedResult==3);
        //remove 0th element
        test123 = MyUnsortedList.of(0, 1, 2);
        removedResult = test123.remove(0);
        assertTrue("Remove 0th element", test12.equals(test123)&&removedResult==0);

        //remove 0<kth<n element
        MyUnsortedList<Integer> test13 = MyUnsortedList.of(1, 3);
        test123 = MyUnsortedList.of(1, 2, 3);
        removedResult = test123.remove(1);
        assertTrue("Remove element in the middle of the list", test13.equals(test123)&&removedResult==2);

        //Remove at -1:
        test1 = MyUnsortedList.of(1);
        test1.remove(-1);
        //remove out of bound
        test1.remove(3);
    }



        /**
         *
         * Helper methodes below
         *
         */

    private MyUnsortedList<Integer> randomIntListHelper (int nbr){
        ArrayList<Integer> liste = new ArrayList<Integer>();
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        for(int i=0; i<nbr; i++){
            liste.add(rand.nextInt());
        }
        return MyUnsortedList.of(liste);
    }
    /*
    private <E> MyUnsortedList<E> randomListHelper (int nbr){
        ArrayList<E> liste = new ArrayList<>();
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        if (liste.isAssignableFrom(Integer.class)) {
            for (int i = 0; i < n; i++) {
                Integer r = rand.nextInt();
                list.add(r);
            }
        }
        else {
            throw new IllegalArgumentException("Unsupported class: " + clazz.getName());
        }
    }

     */
}
