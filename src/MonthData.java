import java.util.ArrayList;
import java.util.HashMap;

class MonthData{

    HashMap<String, ArrayList<Integer>> expenses;
    HashMap <String,ArrayList<Integer>> income;

    MonthData(){
        expenses = new HashMap<>();
        income = new HashMap<>();
    }
}
