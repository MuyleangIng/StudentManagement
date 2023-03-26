import util.Option;
import util.Action;
import util.Input;
import util.Student;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentProgram extends Option implements Action {
    public ArrayList<Student> students;
    public Integer option;
    public final ArrayList<Integer> defaultOption;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public StudentProgram() {
        this.students = new ArrayList<>(){
            {
            add(new Student(1,"Ing Muyleang","Male",21,"DevOps",100.00));
            }
        };
        this.defaultOption = new ArrayList<>(){
            {
            for (int i=1;i<7;i++){
                add(i);
                }
            }
        };
    }

    public void start(){
        super.menu();
        do {
            this.option = Input.setField(Integer.class, "Choose your option 1-6: ");
            if (!defaultOption.contains(option)){
                Option.alertMessage("invalid option should be 1-6");
            }
        }while (!defaultOption.contains(option));
    }

    @Override
    public void create() {
        System.out.println(ANSI_GREEN+"==============="+ "Insert student" + "==============="+ANSI_RESET);
        //super.topMessage("Insert student");
        Student student = new Student();
        do {
            student.id = Input.setField(Integer.class, "Id");
            if (this.checkStudentId(student.id)){
                Option.alertMessage("student id:"+ student.id +" already exist");
            }
        }while (this.checkStudentId(student.id));

        student.name = Input.setField(String.class, "Name");
        student.gender = Input.setField(String.class, "gender");
        student.age = Input.setField(Integer.class, "age");
        student.className = Input.setField(String.class, "Class Name");
        student.score = Input.setField(Double.class, "score");
        this.students.add(student);
        super.successMessage("Insert");
        super.enterContinue();
    }

    @Override
    public void show() {
        System.out.println(ANSI_GREEN+"==============="+ "Show students information" + "==============="+ANSI_RESET);
        //super.topMessage("Show students information");
        System.out.println(this.formatHeader());
        if (students.isEmpty()){
            alertMessage("The students is empty.");
            super.enterContinue();
            return;
        }
        for (Student student: students){
            System.out.println(this.formatStudent(student));
        }
        super.enterContinue();
    }

    @Override
    public void search() {
        if (students.isEmpty()){
            Option.alertMessage("The students is empty.");
            super.enterContinue();
            return;
        }
        ArrayList<Integer> defaultSearchOption= new ArrayList<>(List.of(1,2,3,4,5));
        Integer sOption;
        do {
            System.out.println(ANSI_GREEN+"==============="+ "Search students information" + "==============="+ANSI_RESET);
            //super.topMessage("Search students information");
            super.searchMenu();
            do {
                sOption = Input.setField(Integer.class, "Choose search option 1-5");
                if (!defaultSearchOption.contains(sOption)){
                    Option.alertMessage("invalid value should be 1-5");
                }
            }while (!defaultSearchOption.contains(sOption));

            if (sOption.equals(5)){
                return;
            }
            HashMap<Integer,String> searchBy = new HashMap<>(){{
                put(1,"id");
                put(2,"name");
                put(3,"gender");
                put(4,"className");
            }};
            String field = Input.setField(String.class, searchBy.get(sOption)+" to search");
            this.showBy(searchBy.get(sOption),field);
            super.enterContinue();
        }while (defaultSearchOption.contains(sOption));
    }
    @Override
    public void update() {
        System.out.println(ANSI_GREEN+"==============="+ "Update students information" + "==============="+ANSI_RESET);
        //super.topMessage("Update students information");
        if (students.isEmpty()){
            Option.alertMessage("The students is empty.");
            super.enterContinue();
            return;
        }
        Integer id = Input.setField(Integer.class,"id to update");
        if (this.checkStudentId(id)){
            Student student = this.getStudent(id);
            System.out.println("ID: "+ student.id +
                    "\t Name: "+ student.name +
                    "\t Gender: "+ student.gender +
                    "\t Age: "+ student.age +
                    "\t Class Name: "+ student.className +
                    "\t Score: "+ student.score
            );
            student.name = Input.setField(String.class, "Name");
            student.gender = Input.setField(String.class, "gender");
            student.age = Input.setField(Integer.class, "age");
            student.className = Input.setField(String.class, "class name");
            student.score = Input.setField(Double.class, "score");
            students.set(students.indexOf(student),student);
            super.successMessage("updated");
        }else {
            Option.alertMessage("Student id:"+ id +" isn't exist.");
        }
        super.enterContinue();
    }
    @Override
    public void delete() {
        System.out.println(ANSI_GREEN+"==============="+ "Delete students information" + "==============="+ANSI_RESET);
        //super.topMessage("Delete students information");
        if (students.isEmpty()){
            Option.alertMessage("The students is empty.");
            super.enterContinue();
            return;
        }
        Integer id = Input.setField(Integer.class,"id to delete");
        if (this.checkStudentId(id)){
            students.remove(this.getStudent(id));
            super.successMessage("deleted");
        }else {
            Option.alertMessage("Student id:"+ id +" isn't exist.");
        }
        super.enterContinue();
    }
    @Override
    public void stop() {
        System.out.println("Are you sure you want to exit");
        System.out.println(ANSI_GREEN+"Press Y/y to confirm."+ANSI_RESET);
        System.out.println(ANSI_RED+"Press N/n to cancel."+ANSI_RESET);
        String confirm;
        do {
            confirm = Input.setField(String.class,"Please input to exit:");
            if (confirm.equalsIgnoreCase("y")){
                System.exit(0);
            }else if (confirm.equalsIgnoreCase("n")){
                break;
            }else {
                Option.alertMessage("invalid value should be y or n");
            }
        }while (true);
    }
    private Student getStudent(Integer id){
        for (Student student: students){
            if (student.id.equals(id)){
                return student;
            }
        }
        return new Student();
    }
    private boolean checkStudentId(Integer id){
        if (!students.isEmpty()){
            ArrayList<Integer> checkId = new ArrayList<>();
            for (Student student : students) {
                checkId.add(student.id);
            }
            return checkId.contains(id);
        }
        return false;
    }

    private void showBy(String field, String value){
        System.out.println(this.formatHeader());
        for (Student student: students){
            //String str="";
            switch (field){
                case "id":
                    if (student.id.equals(Integer.parseInt(value))){
                        System.out.println(this.formatStudent(student));
                    }
                    break;
                case "name":
                    if (student.name.toLowerCase().startsWith(value.toLowerCase())){
                        System.out.println(this.formatStudent(student));
                    }
                    break;
                case "gender":
                    if (student.gender.toLowerCase().startsWith(value.toLowerCase())){
                        System.out.println(this.formatStudent(student));
                    }
                case "className":
                    if (student.className.toLowerCase().startsWith(value.toLowerCase())){
                        System.out.println(this.formatStudent(student));
                    }
                    break;
                default:
                    alertMessage("Wrong option");
            }
        }
    }

    private String formatHeader(){
        String head = "ID";
        head+= String.format("%-15s","");
        head += String.format("%-15s", "NAME");
        head += String.format("%-15s", "GENDER");
        head += String.format("%-15s", "AGE");
        head += String.format("%-15s", "CLASSNAME");
        head += String.format("%-15s", "SCORE");
        return head;
    }

    private String formatStudent(Student student){
        String str="";
        str = String.format("%-15s",String.format("%04d",student.id));
        str+= String.format("%-18s",student.name);
        str+= String.format("%-15s",student.gender);
        str+= String.format("%-15s",student.age);
        str+= String.format("%-15s",student.className);
        str+= String.format("%-15s",student.score);
        return str;
    }
    public static class Program {
        public static void main(String[] args) {
            StudentProgram stupro = new StudentProgram();
            do {
                stupro.start();
                switch (stupro.option) {
                    case 1 -> stupro.create();
                    case 2 -> stupro.update();
                    case 3 -> stupro.search();
                    case 4 -> stupro.delete();
                    case 5 -> stupro.show();
                    case 6 -> stupro.stop();
                    default -> Option.alertMessage("Something wrong");
                }
            }while (stupro.defaultOption.contains(stupro.option));
        }
    }
}

