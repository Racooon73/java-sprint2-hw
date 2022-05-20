import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {


MonthData monthToData;

    public MonthlyReport(String path){
        monthToData = new MonthData();

        String fileContents = readFileContentsOrNull(path);
        String[] lines = fileContents.split(System.lineSeparator());
        String[] lineContents;

        for(int i = 1; i < lines.length;i++){
            lineContents = lines[i].split(",");
            for(int j = 0; j < lineContents.length;j+=4){
                ArrayList<Integer> qAndSum = new ArrayList<>();
                qAndSum.add(Integer.parseInt(lineContents[j+2]));
                qAndSum.add(Integer.parseInt(lineContents[j+3]));
                if(lineContents[j+1].equals("TRUE"))
                    monthToData.expenses.put(lineContents[j],qAndSum);
                else if (lineContents[j+1].equals("FALSE"))
                    monthToData.income.put(lineContents[j],qAndSum);
            }
        }
    }
    int sumIncome(){

        int sum = 0;
        for(String productName : monthToData.income.keySet()){
            sum += monthToData.income.get(productName).get(0) * monthToData.income.get(productName).get(1);

        }
        return sum;
    }
    int sumExpenses(){

        int sum = 0;
        for(String productName : monthToData.expenses.keySet()){
            sum += monthToData.expenses.get(productName).get(0) * monthToData.expenses.get(productName).get(1);

        }
        return sum;
    }
    void maxExpencePrint(){
        int maxProductPrice = 0;
        String maxProductName = "";
        int productPrice;
        for(String productName : monthToData.expenses.keySet()){
            productPrice = monthToData.expenses.get(productName).get(1);
            if(productPrice > maxProductPrice){
                maxProductPrice = productPrice;
                maxProductName = productName;
            }
        }
        System.out.println("Самая большая трата: "+maxProductName+" - "+maxProductPrice);
    }
    void maxIncomePrint(){
        int maxProductPrice = 0;
        String maxProductName = "";
        int productPrice;
        for(String productName : monthToData.income.keySet()){
            productPrice = monthToData.income.get(productName).get(0) * monthToData.income.get(productName).get(1);
            if(productPrice > maxProductPrice){
                maxProductPrice = productPrice;
                maxProductName = productName;
            }
        }
        System.out.println("Самый прибыльный товар: "+maxProductName+" - "+maxProductPrice);
    }

    private static String readFileContentsOrNull(String path)
    {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }


}
class MonthData{

    HashMap <String, ArrayList<Integer>> expenses;
    HashMap <String,ArrayList<Integer>> income;
    MonthData(){
        expenses = new HashMap<>();
        income = new HashMap<>();
    }
}
