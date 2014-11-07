package problem_2;

import java.io.Serializable;

public class Instructor implements Serializable {
	private String firstName;
	private String lastName;
	private String department;
	private String email;
	
	{
		firstName = "";
		lastName = "";
		department = "";
		email = "";
	}
	public Instructor(String firstName, String lastName, String department, String email){
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.email = email;
	}
	
	public String getFirst(){
		return firstName;
	}
	public String getLast(){
		return lastName;
	}
	public String getDepartment(){
		return department;
	}
	public String getEmail(){
		return email;
	}
	void setFirst(String firstName){
		this.firstName = firstName;
	}
	void setLast(String lastName){
		this.lastName = lastName;
	}
	void setDepartment(String department){
		this.department = department;
	}
	void setEmail(String email){
		this.email = email;
	}
	
	public String toString(){
		return "Instructor of " + department+" department "+ firstName+" " +lastName+". Email: "+email+";";
	}
	public boolean equals(Object o){
		Instructor i = (Instructor)o;
		if(getFirst().equals(i.getFirst()) && getLast().equals(i.getLast())) return true;
		else return false;
	}
}
