import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Human person1 = new Human("David", 'M', new Date(2008,4,2));
		Human person2 = new Human("Mary", 'F', new Date(2005,3,25));
		
		person1.talk();
		person2.walk();
		person1.whatIsYourAge();
		person2.whatIsYourName();
		
		Student student1 = new Student("Jerry", 'M', new Date(2007,5,2),1,"School1");
		Teacher teacher1 = new Teacher("Jane", 'F', new Date(1985,11,3), "School2", "Maths");
		
		student1.talk();
		teacher1.walk();
		
		student1.whatIsYourAge();
		teacher1.areYouAMan();
		
		student1.enroleCourse("Maths");
		student1.enroleCourse("Physics");
		student1.enroleCourse("Biology");
		student1.whatAreYouLearning();
		
		teacher1.whatAreYouTeaching();
		
		Human person3 = new Student("Cathy",'F',new Date(2010,5,27),2, "School3");
		person3.walk();
		person3.talk();
		
		Student student2 = (Student)person3;
		student2.enroleCourse("English");
		student2.whatAreYouLearning();
		
		/* These is wrong;
		Student student3 = (Student)person1;
		student3.whatAreYouLearning();
		*/
		
		person1.whatIsYourName();
		person3.whatIsYourName();
		
		
		printHumanInfo(person1);
		printHumanInfo(student1);
		
		// printStudentInfo((Student)person1); //This is wrong
		printStudentInfo((Student)person3);
		printStudentInfo(student1);

	}
	
	public static void printHumanInfo(Human human) {
		System.out.println("Name:"+human.name);
		System.out.println("Gender:"+human.gender);
	}
	
	public static void printStudentInfo(Student student) {
		printHumanInfo(student);
		System.out.println("ID:"+student.id);
		System.out.println("School:"+student.school);
	}

}


class Human {
	public String name;
	public char gender;
	public Date birthDate;
	
	public Human (String name, char gender, Date birthDate) {
		this.name = name;
		this.gender = gender;
		this.birthDate = birthDate;
	}
	
	public void talk() {
		System.out.println(this.name+" is talking");
	}
	
	public void walk() {
		System.out.println(this.name+" is walking");
	}
	
	public void whatIsYourName() {
		System.out.println("My name is "+this.name+".");
	}
	
	public void whatIsYourAge() {
		System.out.println("My age is "+(2025-this.birthDate.year)+".");
	}
	
	public void areYouAMan() {
		if (this.gender=='M') {
			System.out.println("Yes");
		} else {
			System.out.println("Nop");
		}
	}
}

class Date {
	int year;
	int month;
	int day;
	
	public Date(int y, int m, int d) {
		this.year = y;
		this.month = m;
		this.day = d;
	}
}

class Student extends Human {
	
	String school;
	ArrayList<String> courses = new ArrayList();
	int id;
	
	
	public Student(String name, char gender, Date birthDate, int id, String school) {
		super(name,gender,birthDate);
		this.school = school;
		this.id = id;
	}
	
	public void enroleCourse(String course) {
		if (!this.courses.contains(course)) {
			this.courses.add(course);
		}
	}
	
	public void whatAreYouLearning() {
		for (String course:this.courses) {
			System.out.print(course+" ");
		}
		System.out.println();
	}
	
	@Override
	public void whatIsYourName() {
		System.out.println("I am a student and my name is "+this.name+".");
	}
}

class Teacher extends Human {
	
	String school;
	String subject;
	public Teacher(String name, char gender, Date birthDate, String school, String subject) {
		super(name, gender, birthDate);
		this.school = school;
		this.subject = subject;
	}
	
	public void whatAreYouTeaching() {
		System.out.println(this.subject);
	}
	
	
}
