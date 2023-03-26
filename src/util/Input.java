package util;

import java.util.Scanner;
public class Input {
    public static <T> T setField(Class<T> field, String fieldName){
        Scanner scanner = new Scanner(System.in);
        boolean check;
        T CheckInput;
        do {
            System.out.print(""+ fieldName +" : ");
            String inputString = scanner.nextLine();
            CheckInput = null;
            try {
                CheckInput = field.getConstructor(String.class).newInstance(inputString);
                check=false;
            } catch (Exception e) {
                Option.alertMessage("INVALID INPUT...! ");
                check=true;
            }
        }while (check);
        return CheckInput;
    }
}
