import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args){

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        String string  = dateFormat.format(new Date()).replaceAll("[- :]","");
        System.out.println(string);
    }
}
