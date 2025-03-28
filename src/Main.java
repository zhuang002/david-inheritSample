import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.GroupLayout.Group;

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
		
		
		Teachers teachers = new Teachers();
		teachers.addTeacher(teacher1);
		teachers.printTeachersByOrder();

		
		Students students = new Students();
		students.addStudent(student1);
		students.addStudent((Student)person3);
		students.printStudentsByOrder();
		
		
		MyGroup teachers2 = new Teachers();
		teachers2.add(teacher1);
		teachers2.printByOrder();
		
		MyGroup students2 = new Students();
		students2.add(student1);
		students2.add((Student)person3);
		students.printByOrder();
		

		MyGroup group1 = new Teachers();
		addToGroup(group1, teacher1);
		group1.printByOrder();
		
		MyGroup group2 = new Students();
		addToGroup(group2,student1);
		addToGroup(group2,person3);
		group2.printByOrder();
	}
	
	public static void addToGroup(MyGroup group, Human human) {
		group.add(human);
	}
	
	public static void printGroup(MyGroup group) {
		group.printByOrder();
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

class Teachers implements MyGroup {
	ArrayList<Teacher> teachers = new ArrayList<>();
	
	public void addTeacher(Teacher teacher) {
		this.teachers.add(teacher);
	}
	
	public void printTeachersByOrder() {
		Collections.sort(this.teachers,(x,y) ->x.name.compareTo(y.name));
		for (Teacher t:this.teachers) {
			System.out.print(t.name+" ");
		}
		System.out.println();
	}

	@Override
	public void add(Human human) {
		this.addTeacher((Teacher)human);
		
	}

	@Override
	public void printByOrder() {
		// TODO Auto-generated method stub
		this.printTeachersByOrder();
	}
}

class Students implements MyGroup {
	Student[]  students = new Student[100];
	int size = 0;
	
	public void addStudent(Student student) {
		this.students[size] = student;
		size++;
	}
	
	public void printStudentsByOrder() {
		Arrays.sort(this.students, (x,y)->{ 
			if (x==null) return 1000;
			if (y==null) return -1;
			return x.name.compareTo(y.name);
			});
		
		for (int i=0;i<size;i++) {
			System.out.print(this.students[i].name+" ");
		}
		System.out.println();
	}

	@Override
	public void add(Human human) {
		this.addStudent((Student)human);
		
	}

	@Override
	public void printByOrder() {
		// TODO Auto-generated method stub
		this.printStudentsByOrder();
	}
}

interface MyGroup {
	void add(Human human);
	void printByOrder();
} 
