
import java.util.*;

interface Dao<T, K> {
    void insert(T obj);
    T findById(K id);
    List<T> findAll();
    void update(T obj);
    void delete(K id);
}

class Student {
    int id;
    String name;
    String department;

    Student(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public String toString() {
        return id + " " + name + " " + department;
    }
}

class StudentDao implements Dao<Student, Integer> {

    Map<Integer, Student> data = new HashMap<>();

    public void insert(Student s) {
        data.put(s.id, s);
        System.out.println("Student Inserted");
    }

    public Student findById(Integer id) {
        return data.get(id);
    }

    public List<Student> findAll() {
        return new ArrayList<>(data.values());
    }

    public void update(Student s) {
        data.put(s.id, s);
        System.out.println("Student Updated");
    }

    public void delete(Integer id) {
        data.remove(id);
        System.out.println("Student Deleted");
    }
}

public class DaoDemo {
    public static void main(String[] args) {

        StudentDao dao = new StudentDao();

        Student s = new Student(101, "Arun", "CSE");

        dao.insert(s);
        System.out.println(dao.findById(101));

        dao.update(new Student(101, "Arun", "IT"));
        dao.delete(101);
    }
}
