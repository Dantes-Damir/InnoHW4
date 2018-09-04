package part1;

import java.io.FileNotFoundException;

import static part1.Employee.save;
import static part1.Employee.saveOrUpdate;

public class Main {

    public static void main(String[] args) {

        Employee employee = new Employee(25, "John", 20000, "Driver");
        Employee employee2 = new Employee(22, "John", 20000, "Driver");
        Employee employee3 = new Employee(25, "Joh", 20000, "Clean");
        try {
            save(employee);


            saveOrUpdate(employee2);
            saveOrUpdate(employee3);

            //changeAllWork("Driver", "Manager");
            //changeAllWork("Manager", "Cleaning");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
