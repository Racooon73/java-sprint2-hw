import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithReports {


    void checkReports(YearlyReport year, MonthlyReport[] reports){
        String [] months = {"Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь"};
        for(int i = 0;i < 3;i++)
            if(reports[i].sumExpenses() != year.data.expenses.get(months[i])){
                System.out.println("Траты не сходятся в месяце: "+months[i]);
                return;
            }

        for(int i = 0;i < 3;i++)
            if(reports[i].sumIncome() != year.data.income.get(months[i])){
                System.out.println("Доходы не сходятся в месяце: "+months[i]);
                return;
            }

        System.out.println("Доходы и расхды сходятся!");

    }
    static String readFileContentsOrNull(String path)
    {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
}
