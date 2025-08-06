
public class GradeStatisticsSystem {

    public static void main(String[] args) {
        int[] grades = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};

        int sum = 0;
        int max = grades[0];
        int min = grades[0];
        int countA = 0, countB = 0, countC = 0, countD = 0, countF = 0;

        for (int grade : grades) {
            sum += grade;
            if (grade > max) {
                max = grade;
            }
            if (grade < min) {
                min = grade;
            }

            if (grade >= 90) {
                countA++; 
            }else if (grade >= 80) {
                countB++; 
            }else if (grade >= 70) {
                countC++; 
            }else if (grade >= 60) {
                countD++; 
            }else {
                countF++;
            }
        }

        double average = sum / (double) grades.length;

        int aboveAverageCount = 0;
        for (int grade : grades) {
            if (grade > average) {
                aboveAverageCount++;
            }
        }

        System.out.println("成績報表：");
        System.out.printf("平均分數：%.2f\n", average);
        System.out.println("最高分：" + max);
        System.out.println("最低分：" + min);
        System.out.println("等第人數統計：");
        System.out.println("A：" + countA);
        System.out.println("B：" + countB);
        System.out.println("C：" + countC);
        System.out.println("D：" + countD);
        System.out.println("F：" + countF);
        System.out.println("高於平均分的學生人數：" + aboveAverageCount);
    }
}
