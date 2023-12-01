import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodoListTest {

    private TodoList todoList;
    private Task task1;
    @BeforeEach
    void setUp() {
        todoList = new TodoList();
         task1 = new Task("titre1","des");
    }
    @Test
    void addTask() {
        assertAll( () -> assertTrue(todoList.addTask("task 1")),
                   () -> assertTrue(todoList.containsTask("task 1"))
        );
    }

    @Test
    void addEmptyTask() {
        assertAll( () -> assertFalse(todoList.addTask("")),
                   () -> assertFalse(todoList.containsTask("")),
                   () -> assertFalse(todoList.addTask(null)),
                   () -> assertFalse(todoList.containsTask(null))
        );
    }

    @Test
    void addExistingTask() {
        todoList.addTask("task 1");
        assertFalse(todoList.addTask("task 1")
        );
    }

    @Test
    void removeTask() {
        todoList.addTask("task 1");
        assertAll(() -> assertTrue(todoList.removeTask("task 1")),
                  () -> assertFalse(todoList.containsTask("task 1"))

        );
    }

    @Test
    void removeUnexistingTask() {
        assertAll( () -> assertFalse(todoList.removeTask("task 1"))

        );
    }

    @Test
    void verifCreatTask () {
        assertAll(() ->assertTrue(todoList.addTaskFor2(task1)),
                  () -> assertTrue(task1.getEtat().equals("ENCOURS"))
        );
    }

    @Test
    void verifArgumentEqualsTask () {
        Task task2 = new Task("titre1","des");
        Task task3 = new Task("titre1","dez");
        todoList.addTaskFor2(task1);
        assertAll(() -> assertFalse(todoList.addTaskFor2(task2)),
                  () -> assertTrue(todoList.addTaskFor2(task3))
        );
        
    }

    @Test
    void setEndStatusTask () {
        assertAll(() -> assertTrue(todoList.endTask(task1)),
                  () -> assertFalse(todoList.endTask(task1))
        );
    }

    @Test
    void setTitelTask() {
        Task taskStatusEnd = new Task("test","desc");
        assertAll(() -> assertTrue(todoList.changeTitle(task1,"titre changé")),
                  () -> assertTrue(todoList.endTask(taskStatusEnd)),
                  () -> assertFalse(todoList.changeTitle(taskStatusEnd,"nop")),
                  () -> assertFalse(todoList.changeTitle(task1,"")),
                  () -> assertFalse(todoList.changeTitle(task1,null))
        );
    }
    @Test
    void setDescTask() {
        Task taskStatusEnd = new Task("test","desc");
        assertAll(() -> assertTrue(todoList.changeDesc(task1,"desc changé")),
                () -> assertTrue(todoList.endTask(taskStatusEnd)),
                () -> assertFalse(todoList.changeDesc(taskStatusEnd,"nop")),
                () -> assertFalse(todoList.changeDesc(task1,"")),
                () -> assertFalse(todoList.changeDesc(task1,null))
        );
    }

    @Test
    void returnPresentTask () {
        assertAll(() -> assertTrue(todoList.addTaskFor2(task1)),
                  () -> assertTrue(todoList.returnTask("titre1","des")),
                  () -> assertFalse(todoList.returnTask("rien","rien"))
        );
    }

    @Test
    void setTaskWithOtherTask () {
        Task task2 = new Task("rien","rien");
        Task task3 = new Task("nop","nop");
        Task task4 = new Task("",null);
        assertAll(() -> assertTrue(todoList.addTaskFor2(task1)),
                  () -> assertTrue(todoList.changeTask(task1,task2)),
                  () -> assertFalse(todoList.changeTask(task4,task3)),
                  () -> assertFalse(todoList.changeTask(task1,task4)),
                  () -> assertFalse(todoList.changeTask(task1,task1))
        );
    }
}
 


