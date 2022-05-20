
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        MonthlyReport[] reports = new MonthlyReport[12];
        YearlyReport year = null;
        boolean isMonthReport = false;
        boolean isYearReport = false;
        while(true){
            printMenu();
            int command = scanner.nextInt();
            if(command == 1){
                for(int i = 1; i<=3;i++){
                    if(i > 9)
                        reports[i-1] = new MonthlyReport("resources\\m.2021"+i+".csv");
                    else
                        reports[i-1] = new MonthlyReport("resources\\m.20210"+i+".csv");
                }
                isMonthReport = true;
            }
            else if(command == 2){
                year = new YearlyReport("resources\\y.2021.csv");
                isYearReport = true;
            }
            else if(command == 3){
                if(isMonthReport && isYearReport)
                    checkReports(year,reports);
                else
                    System.out.println("Сначала необходимо считать отчёты");
            }
            else if(command == 4){
                if(isMonthReport && isYearReport)
                    printMonthInfo(reports);
                else
                    System.out.println("Сначала необходимо считать отчёты");
            }
            else if(command == 5){
                if(isMonthReport && isYearReport){
                    System.out.println("2021 год:");
                    year.monthlyIncomePrint();
                    year.averagePrint();
                }
                else
                    System.out.println("Сначала необходимо считать отчёты");

            }
            else if(command == 0){
                break;
            }
            else{
                System.out.println("Такой команды нет");
            }

        }

    }
    static void checkReports(YearlyReport year, MonthlyReport[] reports){
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
    static void printMenu(){
        System.out.println("Выберите действие:");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выход");
    }
    static void printMonthInfo(MonthlyReport[] reports){
        String [] months = {"Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь"};
        for(int i = 0; i < 3; i++){
            System.out.println("Месяц: --"+ months[i]+"--");
            reports[i].maxIncomePrint();
            reports[i].maxExpencePrint();
        }
    }
}

