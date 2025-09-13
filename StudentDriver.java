import java.util.*;

class Student implements Comparable<Student>{
    int rollNo;
    String name;
    String department;
    double cgpa;

    Student(int rollNo, String name, String department, double cgpa){
        this.rollNo = rollNo;
        this.name = name;
        this.department = department;
        this.cgpa = cgpa;
    }

    @Override
    public String toString(){
        return name;
    }

    public int compareTo(Student other){
        return Double.compare(other.cgpa, this.cgpa);
    }

    public static Comparator<Student> NameComparator = Comparator.comparing(s -> s.name);

    public static Comparator<Student> rollComparator = Comparator.comparingInt(r -> r.rollNo);

}

public class StudentDriver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter the number of Students: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Roll No: ");
            int roll = sc.nextInt();
            sc.nextLine();

            System.out.print("Student Name: ");
            String name = sc.nextLine();
            System.out.print("Department: ");
            String dept = sc.nextLine();

            System.out.print("Enter CGPA: ");
            double cgpa = sc.nextDouble();
            sc.nextLine();

            students.add(new Student(roll, name, dept, cgpa));
        }

        //------------------------ Register Student -----------------------
        System.out.println("Registration Order:");
        printNames(students);

        //------------------------ Merit List ------------------------------
        List<Student> meritList = new ArrayList<>(students);
        Collections.sort(meritList);
        System.out.println("Merit List: ");
        printMeritList(meritList);

        //-------------------- Alphabetical List ----------------------------
        List<Student> alphaList = new ArrayList<>(students);
        Collections.sort(alphaList, Student.NameComparator);
        System.out.println("Alphabetical List: ");
        printNames(alphaList);

        //------------------- Department Grouping ---------------------------
        Map<String, List<Student>> deptName = new LinkedHashMap();
        for(Map.Entry<String, List<Student>> entry : deptName.entrySet()){
            System.out.print(entry.getKey() + ": " + entry.getValue());
            for(int i = 0; i < deptName.size(); i++){
                if (i < deptName.size()) {
                    System.out.print(", ");
                }
            }
        }

        //------------------------- Unique Name --------------------------------
        Set<String> unique = new HashSet<>();
        for(Student s : students){
            unique.add(s.name);
        }

        System.out.println("Unique Names: " + unique);


        //----------------------- Roll Nos Sorting ----------------------------------
        Set<Student> rollCalls = new TreeSet<>(Student.rollComparator);
        rollCalls.addAll(students);
        System.out.print("Roll No Sorting: ");
        printNames(new ArrayList<>(rollCalls));

        //--------------------- Performance Filter ---------------------------------
        List<Student> filtered = new ArrayList<>(students);
        Iterator<Student> it = filtered.iterator();
        while (it.hasNext()) {
            if (it.next().cgpa >= 5.0) {
                it.remove();
            }
        }
        System.out.print("After Filter (CGPA >= 5.0): ");
        printNames(filtered);

        //--------------------- Recent Registration ----------------------------------
        Stack<Student> register = new Stack<>();
        register.addAll(students);
        System.out.print("Students (LIFO Order): ");
        while (!register.isEmpty()) {
            System.out.print(register.pop().name);
            if (!register.isEmpty()) {
                System.out.print(", ");
            }
        }
        System.out.println();


        //---------------------- Scholarship Queue -------------------------------------
        Queue<Student> scholars = new LinkedList<>();
        System.out.print("Scholarship Queue (FIFO order): ");
        while (!scholars.isEmpty()) {
            System.out.print(scholars.poll().name);
            if (!scholars.isEmpty()) {
                System.out.print(", ");
            }
        }
        System.out.println();

        //--------------------------  Hostel Application ---------------------------------
        LinkedList<Integer> hostel = new LinkedList<>();
        System.out.print("Hostel Queue: ");
        System.out.println("Hostel Queue:");
        hostel.addLast(105);
        System.out.println("Add regular(105): " + hostel);
        hostel.addFirst(101);
        System.out.println("Add priority(101): " + hostel);
        hostel.pollFirst();
        System.out.println("Remove front: " + hostel);
    }


    static void printNames(List<Student> student){
        for(int i = 0; i < student.size(); i++){
            Student s = student.get(i);
            System.out.print(s.name);
            if(i < student.size()-1){
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    static void printMeritList(List<Student> student){
        for(int i = 0; i < student.size(); i++){
            Student s = student.get(i);
            System.out.print(s.name + "(" + s.cgpa + ")");
            if(i < student.size() - 1){
                System.out.print(", ");
            }
        }
        System.out.println();
    }
    
}
