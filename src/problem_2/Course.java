package problem_2;

import java.io.Serializable;

public class Course implements Serializable {
	private String courseTittle;
	TextBook textBook;
	Instructor instructor;
	
	{
		setCourseTittle("");
		textBook = null;
		instructor = null;
	}

	public Course( String courseTittle, TextBook textbook, Instructor instructor ) {
		setCourseTittle(courseTittle);
		setTextBook(textbook);
		setInstructor(instructor);
	}
	
	public void setCourseTittle(String courseTittle) {
		this.courseTittle = courseTittle;
	}

	public String getCourseTittle() {
		return courseTittle;
	}
	
	public void setTextBook(TextBook textBook){
		this.textBook = textBook;
	}
	public TextBook getTextBook() {
		return textBook;
	}
	
	public void setInstructor(Instructor instructor){
		this.instructor = instructor;
	}
	public Instructor getInstructor(){
		return instructor;
	}
	
	public String toString(){
		return "Course is: "+courseTittle+", Instructor is "+instructor.getFirst()+" "+instructor.getLast()+", TextBook is "+textBook.getTittle()+
		", Author is: "+textBook.getAuthor()+", isbn: "+textBook.getIsbn();
	}
	public boolean equals(Object o){
		Course c = (Course)o;
		if(getCourseTittle().equals(c.getCourseTittle()) && getInstructor().equals(c.getInstructor()) && getTextBook().equals(c.getTextBook())) return true;
		else return false;
	}
}
