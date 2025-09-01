import java.util.Scanner;

public class StudentGrade 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n");
        System.out.println("=======================================");
        System.out.println("       STUDENT RESULT PORTAL      ");
        System.out.println("======================================\n");

        System.out.print("Enter number of subjects: ");
        int n = sc.nextInt();

        int total = 0;

        System.out.println("\nEnter marks (out of 100):\n");
        for (int i = 1; i <= n; i++) 
        {
            System.out.print(" Subject " + i + ": ");
            int marks = sc.nextInt();
            total += marks;
        }

        double average = (double) total / n;

        String grade;
        switch ((int) average / 10) 
        {
            case 10:
            case 9: grade = "A (Outstanding)"; break;
            case 8: grade = "B (Very Good)"; break;
            case 7: grade = "C (Good)"; break;
            case 6: grade = "D (Needs Improvement)"; break;
            default: grade = "F (Fail)";
        }

        System.out.println();
        System.out.println("================= FINAL RESULT =================");
        System.out.println("Total Subjects     : " + n);
        System.out.println("Total Marks        : " + total + " / " + (n * 100));

        System.out.println("Average Percentage : " + (int) average + "%");

        System.out.println("Grade              : " + grade);

        System.out.print("\nRemark             : ");
        if (average >= 90) 
        {
            System.out.println("Excellent performance, keep it up!");
        } else if (average >= 75) 
        {
            System.out.println("Well done, you’re on the right track!");
        } else if (average >= 50) 
        {
            System.out.println("Decent effort, but aim higher!");
        } else 
        {
            System.out.println("Don’t lose hope, work harder next time!");
        }

        sc.close();
    }
}
