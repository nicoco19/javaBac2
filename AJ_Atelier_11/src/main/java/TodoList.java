import java.util.ArrayList;
import java.util.List;

public class TodoList {

    private List<String> tasksForEX1 = new ArrayList<>();
    private List<Task> tasksForEX2 = new ArrayList<>();
    public boolean addTask(String task) {
        if (task == null || task.isBlank()) return false;
        if (containsTask(task)) return false;
        return tasksForEX1.add(task);
    }

    public boolean containsTask(String task) {
        if (task == null || task.isBlank()) return false;
        return tasksForEX1.contains(task);
    }

    public boolean removeTask(String task){
        return tasksForEX1.remove(task);
    }

    public boolean addTaskFor2(Task task){

        if (tasksForEX2.contains(task)){
            return false;
        }
        tasksForEX2.add(task);
        return true;
    }


    public boolean endTask(Task task) {
        // System.out.println(task.titre+""+task.getEtat());
        if(task.getEtat().equals("terminer")){
            return false;
        }
        task.setEtat("terminer");
        return true;
    }


    public boolean changeTitle(Task task, String newTitle) {
        if(task.getEtat().equals("terminer") || newTitle == null || newTitle.isBlank()) return false;

        task.setTitre(newTitle);
        return true;
    }

    public boolean changeDesc(Task task, String newDesc) {
        if(task.getEtat().equals("terminer") || newDesc == null || newDesc.isBlank()) return false;

        System.out.println("description avant : "+task.description);
        task.setDescription(newDesc);
        System.out.println("description apres : "+task.description);

        return true;
    }

    public boolean returnTask(String titre, String desc) {
        Task taskEstPresent = new Task(titre,desc);

        for (Task t : tasksForEX2) {
            if (t.equals(taskEstPresent)){
                System.out.println("la tache est présente la voici :\ntitre : " + t.titre + " \nla description : " + t.description);
                return true;
            }

        }

        System.out.println("la tache : " + taskEstPresent.titre + " " + taskEstPresent.description + " n'est pas présente ");
        return false;
    }

    public boolean changeTask(Task taskBefor, Task taskAfter) {
        if(!tasksForEX2.contains(taskBefor) || taskAfter.titre.equals("")|| taskAfter.description.equals("") || taskAfter.titre == null || taskAfter.description == null|| taskBefor.equals(taskAfter)){
            return false;
        }
        System.out.println("le titre avant : " + taskBefor.getTitre());
        System.out.println("la description avant : " + taskBefor.getDescription());
        taskBefor.setDescription(taskAfter.getDescription());
        taskBefor.setTitre(taskAfter.getTitre());
        System.out.println("le titre apres : " + taskBefor.getTitre());
        System.out.println("la description apres : " + taskBefor.getDescription());
        return true;
    }

}
