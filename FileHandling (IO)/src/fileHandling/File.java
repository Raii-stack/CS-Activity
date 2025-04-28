package fileHandling;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class File {
    static Scanner input;
    static PrintWriter output;
    static String rows[] = new String[6];
    static String rowData;
    static String name, logic, dataStructure, english, programming;
    static int students;
    static double averageGrades, lGrades, dsGrades, eGrades, pGrades;
    static double lAve, dsAve, eAve, pAve;
    static double ltotal, dstotal, etotal, ptotal;
    static String highestStudent = "";
    static double highestStudentGrade = 0.0;
    static String highestSubject = "";
    static double highestSubjectGrade = 0.0;

    public static void inputFile() {
        try {
            input = new Scanner(new FileReader("student_data.txt"));
            output = new PrintWriter("analysis_report.txt");
        } catch (FileNotFoundException e) {
            output.println("Error: " + e);
        }

    }

    public static void closeFile() {
        input.close();
        output.close();
    }

    public static void processFile() {

        while (input.hasNext()) {
            rowData = input.nextLine();
            rows = rowData.split(",");
            name = rows[1];
            logic = rows[2];
            dataStructure = rows[3];
            english = rows[4];
            programming = rows[5];

            lGrades = Double.parseDouble(logic);
            dsGrades = Double.parseDouble(dataStructure);
            eGrades = Double.parseDouble(english);
            pGrades = Double.parseDouble(programming);

            ltotal += lGrades;
            dstotal += dsGrades;
            etotal += eGrades;
            ptotal += pGrades;

            averageGrades = (lGrades + dsGrades + eGrades + pGrades) / 4;
            student_print();
            if (averageGrades > highestStudentGrade) {
                highestStudentGrade = averageGrades;
                highestStudent = name;
            }
            students++;
        }
        
        lAve = ltotal / students;
        dsAve = dstotal / students;
        eAve = etotal / students;
        pAve = ptotal / students;

        if (lAve > highestSubjectGrade) {
            highestSubjectGrade = lAve;
            highestSubject = "Logic";
        }
        if (dsAve > highestSubjectGrade) {
            highestSubjectGrade = dsGrades;
            highestSubject = "Data Structure";
        }
        if (eGrades > highestSubjectGrade) {
            highestSubjectGrade = eAve;
            highestSubject = "English";
        }
        if (pAve > highestSubjectGrade) {
            highestSubjectGrade = pAve;
            highestSubject = "Programming";
        }
    }

    public static void student_print() {
        output.println(name);
        output.println("\tAverage Grade: " + averageGrades);
    }

    public static void print() {
        output.println("---Student Average Grades---");
        processFile();
        output.println("\n---Subject Average Grades");
        output.println("Logic: Ave Grade: " + lAve);
        output.println("Data Structure: Ave Grade: " + dsAve);
        output.println("English: Ave Grade: " + eAve);
        output.println("Programming: Ave Grade: " + pAve);
        output.println();
        output.println("---Highest Performing Student---");
        output.println("Highest Performing Student: " + highestStudent);
        output.println("Average Grade: " + highestStudentGrade);
        output.println();
        output.println("---Highest Performing Subject---");
        output.println("Highest Performing Subject: " + highestSubject);
        output.println("Highest Grade: " + highestSubjectGrade);
    }

    public static void main(String[] args) {
        inputFile();
        print();
        closeFile();
    }
}
