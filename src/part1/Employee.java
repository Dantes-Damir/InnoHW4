package part1;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Employee {

    private int age;
    private String name;
    private int salary;
    private String job;

    public Employee() {
    }

    public Employee(int age) {
        this.age = age;
    }

    public Employee(int age, String name, int salary, String job) {
        this.age = age;
        this.name = name;
        this.salary = salary;
        this.job = job;
    }

    public static boolean save(Employee employee) throws FileNotFoundException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employee.txt", true));
             BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employee.txt"), StandardCharsets.UTF_8))) {
            boolean contains = false;
            try {
                writer.write(employee.toString() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static boolean saveOrUpdate(Employee employee) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employee.txt", true));
             BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employee.txt"), StandardCharsets.UTF_8));
        ) {
            List<String> list = new ArrayList<>();
            boolean contains = false;

            while (reader.ready()) {
                String stringEmployee = reader.readLine();

                if (!employee.toString().equals(stringEmployee)) {
                    list.add(stringEmployee);
                } else {
                    contains = true;
                    list.add(employee.toString());
                }

            }
            if (contains == false) list.add(employee.toString());
            try (BufferedWriter writerFlush = new BufferedWriter(new FileWriter("employee.txt"))) {
                writerFlush.write("");
            }
            for (String temp : list) {
                writer.write(temp + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    static boolean changeAllWork(String jobOld, String jobNew) {
        List<String> list = new ArrayList<>();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employee.txt", true));
             BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employee.txt"), StandardCharsets.UTF_8));
        ) {
            String stringEmployee;
            while ((stringEmployee = reader.readLine()) != null) {
                int indexJob = stringEmployee.indexOf("job=");

                String jobEmployee = stringEmployee.substring(indexJob + 5, stringEmployee.length() - 2);

                if (!jobEmployee.equals(jobOld)) {
                    list.add(stringEmployee);
                } else {

                    list.add(stringEmployee.substring(0, indexJob + 5) + jobNew + stringEmployee.substring(stringEmployee.length() - 2));
                }
            }
            try (BufferedWriter writerFlush = new BufferedWriter(new FileWriter("employee.txt"))) {
                writerFlush.write("");
            }
            for (String temp : list) {
                writer.write(temp + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean delete(Employee employee) {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("employee.txt"))) {
            //
            try {
                String stringEmployee;
                while ((stringEmployee = reader.readLine()) != null) {

                    if (!(employee.toString().equals(stringEmployee))) {
                        list.add(stringEmployee);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employee.txt", false))) {
            for (String temp : list) {
                writer.write(temp + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return true;
    }

    public static Employee getByName(String name) {
        Employee employee = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("employee.txt"))) {
            String stringEmployee;

            while ((stringEmployee = reader.readLine()) != null) {
                int indexAge = stringEmployee.indexOf("age=");
                int indexName = stringEmployee.indexOf("name='");
                int indexSalary = stringEmployee.indexOf("salary=");
                int indexJob = stringEmployee.indexOf("job=");

                String nameEmployee = stringEmployee.substring(indexName + 6, indexSalary - 3);
                int age = Integer.parseInt(stringEmployee.substring(indexAge + 4, indexName - 2));
                int salary = Integer.parseInt(stringEmployee.substring(indexSalary + 7, indexJob - 2));
                String job = stringEmployee.substring(indexJob + 5, stringEmployee.length() - 2);

                if (nameEmployee.equals(name)) {
                    employee = new Employee(age, nameEmployee, salary, job);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public static List<Employee> getByJob(String job) {
        List<Employee> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("employee.txt"))) {
            String stringEmployee;

            while ((stringEmployee = reader.readLine()) != null) {
                int indexAge = stringEmployee.indexOf("age=");
                int indexName = stringEmployee.indexOf("name='");
                int indexSalary = stringEmployee.indexOf("salary=");
                int indexJob = stringEmployee.indexOf("job=");

                String nameEmployee = stringEmployee.substring(indexName + 6, indexSalary - 3);
                int age = Integer.parseInt(stringEmployee.substring(indexAge + 4, indexName - 2));
                int salary = Integer.parseInt(stringEmployee.substring(indexSalary + 7, indexJob - 2));
                String jobEmployee = stringEmployee.substring(indexJob + 5, stringEmployee.length() - 2);

                if (job.equals(jobEmployee)) {
                    Employee employee = new Employee(age, nameEmployee, salary, job);
                    list.add(employee);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", job='" + job + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age &&
                salary == employee.salary &&
                Objects.equals(name, employee.name) &&
                Objects.equals(job, employee.job);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name, salary, job);
    }
}


