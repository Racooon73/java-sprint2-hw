public class YearlyReport {
    YearlyData data;
    String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};

    YearlyReport() {

        data = new YearlyData();

    }

    void convertFile(String path) {
        String fileContents = WorkWithReports.readFileContentsOrNull(path);
        String[] lines = fileContents.split(System.lineSeparator());
        String[] lineContents;
        for (int i = 1; i < lines.length; i++) {
            lineContents = lines[i].split(",");
            for (int j = 0; j < lineContents.length; j += 3) {

                if (lineContents[j + 2].equals("true"))
                    data.expenses.put(months[Integer.parseInt(lineContents[j]) - 1], Integer.parseInt(lineContents[j + 1]));
                else if (lineContents[j + 2].equals("false"))
                    data.income.put(months[Integer.parseInt(lineContents[j]) - 1], Integer.parseInt(lineContents[j + 1]));
            }
        }
    }

    int averageIncome() {
        int sum = 0;
        int i = 0;
        for (String monthKey : data.income.keySet()) {
            sum += data.income.get(monthKey);
            i++;
        }
        return sum / i;
    }

    int averageExpense() {
        int sum = 0;
        int i = 0;
        for (String monthKey : data.expenses.keySet()) {
            sum += data.expenses.get(monthKey);
            i++;
        }
        return sum / i;
    }

    void averagePrint() {
        System.out.println("Средний доход за год: " + averageIncome());
        System.out.println("Средний расход за год: " + averageExpense());
    }

    void monthlyIncomePrint() {
        int income;
        for (String monthKey : data.income.keySet()) {
            if (!data.income.containsKey(monthKey) && data.expenses.containsKey(monthKey))
                income = -data.expenses.get(monthKey);
            if (data.income.containsKey(monthKey) && !data.expenses.containsKey(monthKey))
                income = data.income.get(monthKey);
            income = data.income.get(monthKey) - data.expenses.get(monthKey);
            System.out.println("Прибыль за " + monthKey + ": " + income);
        }
    }
}
