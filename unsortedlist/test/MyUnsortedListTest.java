import datastruct.EmptyListException;
import datastruct.MyUnsortedList;
import datastruct.UnsortedList;
import org.junit.*;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertEquals;

public class MyUnsortedListTest{
    private UnsortedList<Integer> list;

    @Before
    public void initList(){
        list = MyUnsortedList.of();
    }

    @After
    public void clean(){

    }

    @Test
    public void testIsEmpty_empty(){
        assertTrue("testIsEmpty avec une liste vide : ",list.isEmpty());
    }

    @Test
    public void testIsEmpty_not_empty(){
        list.append(1);
        assertFalse("testIsEmpty avec une liste non vide : ",list.isEmpty());
    }

    @Test
    public void testSize_0(){
        assertEquals("testSize avec une liste de taille 0 :",0,list.size());
    }

    @Test
    public void testSize_x(){
        list.append(1);
        assertEquals("testSize avec une liste de taille 1 :",1,list.size());
    }

    @Test
    public void testPrepend_empty() {
        list.prepend(2);
        MyUnsortedList goal = MyUnsortedList.of(2);
        assertEquals("testPrepend avec une liste vide :",goal, list);
    }

    @Test
    public void testPrepend_not_empty(){
        list = MyUnsortedList.of(1,1,1,1);
        list.prepend(2);
        MyUnsortedList goal = MyUnsortedList.of(2,1,1,1,1);
        assertEquals("testPrepend avec une liste non vide :",goal, list);
    }
    @Test
    public void testAppend_empty() {
        list.prepend(2);
        MyUnsortedList goal = MyUnsortedList.of(2);
        assertEquals("testAppend avec une liste vide :",goal, list);
    }

    @Test
    public void testAppend_not_empty(){
        list = MyUnsortedList.of(1,1,1,1);
        list.append(2);
        MyUnsortedList goal = MyUnsortedList.of(1,1,1,1,2);
        assertEquals("testAppend avec une liste non vide :",goal, list);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testInsert_out_of_range() throws IndexOutOfBoundsException{
        list.insert(2,1);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testInsert_out_of_range_minus() throws IndexOutOfBoundsException{
        list.insert(2,-1);
    }

    @Test
    public void testInsert_empty(){
        list.insert(2,0);
        MyUnsortedList goal = MyUnsortedList.of(2);
        assertEquals("testInsert avec une liste vide :",goal, list);
    }

    @Test
    public void testInsert_first(){
        list.append(1);
        list.insert(2,0);
        MyUnsortedList goal = MyUnsortedList.of(2,1);
        assertEquals("testInsert avec un element en tete de liste :",goal, list);
    }

    @Test
    public void testInsert_last(){
        list.append(1);
        list.insert(2,list.size());
        MyUnsortedList goal = MyUnsortedList.of(1,2);
        assertEquals("testInsert avec un element en queue de liste :",goal, list);
    }

    @Test
    public void testInsert_middle(){
        list.append(1);
        list.append(1);
        list.insert(2,1);
        MyUnsortedList goal = MyUnsortedList.of(1,2,1);
        assertEquals("testInsert avec un element entre deux autres  :",goal, list);
    }


    @Test (expected = EmptyListException.class)
    public void testPop_empty() throws EmptyListException{
        list.pop();
    }

    @Test
    public void testPop_check_list(){
        list.append(2);
        list.append(1);
        list.append(1);
        list.pop();
        MyUnsortedList goal = MyUnsortedList.of(1,1);
        assertEquals("testPop verification de la liste resultante :",goal, list);
    }

    @Test
    public void testPop_check_elem(){
        list.append(2);
        list.append(1);
        list.append(1);
        assertEquals("testPop verification de l'element :",new Integer(2), list.pop());
    }

    @Test (expected = EmptyListException.class)
    public void testPopLast_empty() throws EmptyListException{
        list.popLast();
    }

    @Test
    public void testPopLast_check_list(){
        list.append(1);
        list.append(1);
        list.append(2);
        list.popLast();
        MyUnsortedList goal = MyUnsortedList.of(1,1);
        assertEquals("testPopLast verification de la liste resultante :",goal, list);
    }

    @Test
    public void testPopLast_check_elem(){
        list.append(1);
        list.append(1);
        list.append(2);
        assertEquals("testPopLast verification de l'element :",new Integer(2), list.popLast());
    }


    @Test (expected = IndexOutOfBoundsException.class)
    public void testRemove_empty() throws EmptyListException{
        list.remove(0);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testRemove_minus() throws EmptyListException{
        list.remove(-1);
    }

    @Test
    public void testRemove_elem(){
        list.append(2);
        Integer elem = list.remove(0);
        assertEquals("testRemove verification de l'element resultant :",new Integer(2), elem);
    }


    @Test
    public void testRemove_first_list(){
        list.append(2);
        list.append(1);
        list.remove(0);
        MyUnsortedList goal = MyUnsortedList.of(1);
        assertEquals("testRemove avec un element en tete de liste :",goal, list);
    }

    @Test
    public void testRemove_last_list(){
        list.append(1);
        list.append(2);
        list.remove(list.size()-1);
        MyUnsortedList goal = MyUnsortedList.of(1);
        assertEquals("testRemove avec un element en queue de liste :",goal, list);
    }

    @Test
    public void testRemove_middle_list(){
        list.append(1);
        list.append(2);
        list.append(1);
        list.remove(1);
        MyUnsortedList goal = MyUnsortedList.of(1,1);
        assertEquals("testRemove avec un element entre deux autres  :",goal, list);
    }
}