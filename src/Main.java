import java.io.File;
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

                        reports[i-1] = new MonthlyReport();
                        reports[i-1].convertFile( "resources"+ File.separator +"m.20210"+i+".csv");

                }
                isMonthReport = true;
            }
            else if(command == 2){
                year = new YearlyReport();
                year.convertFile("resources"+ File.separator +"y.2021.csv");
                isYearReport = true;
            }
            else if(command == 3){
                if(isMonthReport && isYearReport){
                    WorkWithReports work = new WorkWithReports();
                    work.checkReports(year,reports);

                }
                else
                    System.out.println("Сначала необходимо считать отчёты");
            }
            else if(command == 4){
                if(isMonthReport)
                    printMonthInfo(reports);
                else
                    System.out.println("Сначала необходимо считать отчёты");
            }
            else if(command == 5){
                if(isYearReport){
                    System.out.println("2021 год:");
                    year.monthlyIncomePrint();
                    year.averagePrint();
                }
                else
                    System.out.println("Сначала необходимо считать отчёт");

            }
            else if(command == 0){
                break;
            }
            else{
                System.out.println("Такой команды нет");
            }

        }

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

