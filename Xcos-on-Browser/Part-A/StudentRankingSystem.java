/*
	Resources: https://tutorialspoint.com
	           https://docs.oracle.com/javase/7/docs/api/java/lang/Comparable.html
	           https://www.javapractices.com/topic/TopicAction.do?Id=10
*/
import java.util.Scanner;
import java.util.Comparator;
import java.util.Arrays;
import java.util.Arrays.*;

class StudentRankingSystem implements Comparable<StudentRankingSystem>{
        //Initiating the input scanner
	Scanner reader = new Scanner(System.in);
	private int rollNo;
	private String name;
	private float lang1, lang2, math, science, es;
	private float total;
	//Default constructor in which I have given the output to enter the values.
	public StudentRankingSystem() {
		
		System.out.print("Roll No: ");
		rollNo = reader.nextInt();
		
		System.out.print("Name: ");
		name = reader.next();
		
		System.out.print("Marks in Language1: ");
		lang1 = reader.nextFloat();
		
		System.out.print("Marks in Language2: ");
		lang2 = reader.nextFloat();
		
		System.out.print("Marks in Mathematics: ");
		math = reader.nextFloat();
		
		System.out.print("Marks in Science: ");
		science = reader.nextFloat();
		
		System.out.print("Marks in Environmental Studies: ");
		es = reader.nextFloat();

		System.out.println("");
                //Assigning the value of total marks.
		total = lang1 + lang2 + math + science + es;
		
	}
	//Public methods that return the value of the private datatypes
	public String getName() {

		return name;
	}
	
	public int getRoll() {

		return rollNo;
	}
	
	public float getLang1() {

		return lang1;
	}
	
	public float getLang2() {

		return lang2;
	}
	
	public float getMath() {

		return math;
	}
	
	public float getScience() {

		return science;
	}
	
	public float getES() {

		return es;
	}
	
	public float getTotal() {

		return total;
	}
	//Overriding the compareTo method to compare two students based on the given criterias.
	public int compareTo(StudentRankingSystem student) {
                //Checking the total marks
		if(this.total > student.getTotal())
			return 1;
		if(this.total < student.getTotal())
			return -1;
                //If the total marks are the same, checking the maths mark.
		if(this.math > student.getMath())
			return 1;
		if(this.math < student.getMath())
			return -1;
                //If the maths mark is also the same, checking the science mark.
		if(this.science > student.getScience())
			return 1;
		if(this.science < student.getScience())
			return -1;
                //If the science mark is also the same, checking the es mark.
		if(this.es > student.getES())
			return 1;
		if(this.es < student.getES())
			return -1;
                //If the es mark is also the same, checking the language 1 mark.
		if(this.lang1 > student.getLang1())
			return 1;
		if(this.lang1 < student.getLang1())
			return -1;
                //If the language 1 mark is also the same, checking the language 2 mark.
		if(this.lang2 > student.getLang2())
			return 1;
		if(this.lang2 < student.getLang2())
			return -1;
                //If unable to order with the marks, return the alphabetical order.
		return -1*this.name.compareTo(student.name);
	}
	
	
	public static void main(String[] args) {

		Scanner reader = new Scanner(System.in);
		//Variable to store the number of students.
		int n;
		System.out.println("\n************************ Student Ranking System ************************\n");
		System.out.print("Enter the number of students: ");
		n = reader.nextInt();
		//Minimum number of students must be atleast 5, else ask to enter again.
		while(n<5) {
			
			System.out.println("\nNumber of students must be atleast 5. Try again! \n");
			System.out.print("Enter the number of students: ");
			n = reader.nextInt();
		}
                // n objects are declared.
		StudentRankingSystem[] student = new StudentRankingSystem[n];
		//Initialize the n objects with their marks.
		for(int i = 0; i < n; i++) {
			
			System.out.printf("Enter the following details of Student %d \n" , i+1);
			student[i] = new StudentRankingSystem();
		}
                //Sort the given array of objects(student[]), in the order preferred(compareTo)
		Arrays.sort(student, 0, n);
                //Print the output after sorting.
		System.out.println("Rank\t\tRoll No\t\tName\t\tTotal\t\tMathes\t\tScience\t\tES\t\tLang 1\t\tLang 2");
		for(int i = n-1; i >= 0; i--) {
			
	 		System.out.println((n-i) + "\t\t" + student[i].rollNo + "\t\t" + student[i].name + "\t\t" + student[i].total + "\t\t" +
	 			           student[i].math + "\t\t" + student[i].science + "\t\t" + student[i].es + "\t\t" + student[i].lang1 +
	 			           "\t\t" + student[i].lang2); 		
	 	}
                //Close the input scanner.
		reader.close();
	}
}
