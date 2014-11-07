package problem_2;

import java.io.Serializable;

public class TextBook implements Serializable{
	private int isbn;
	private String tittle;
	private String author;
	
	{
		isbn =0;
		tittle="";
		author="";
	}
	public TextBook(int isbn, String tittle, String author){
		
		this.isbn = isbn;
		this.tittle = tittle;
		this.author = author;
	}
	
	public String getTittle(){
		return tittle;
	}
	public String getAuthor(){
		return author;
	}
	public int getIsbn(){
		return isbn;
	}
	void setTittle(String tittle){
		this.tittle = tittle;
	}
	void setAuthor(String author){
		this.author = author;
	}
	void setIsbn(int isbn){
		this.isbn = isbn;
	}
	
	public String toString(){
		return "Tittle is: "+ tittle+", author is:"+author+", Isbn is: "+isbn+";";
	}
	public boolean equals(Object o){
		TextBook t = (TextBook)o;
		if(getTittle().equals(t.getTittle()) && getAuthor().equals(t.getAuthor()) && getIsbn()==t.getIsbn()) return true;
		else if(getIsbn()==t.getIsbn()) return true;
		else return false;
	}
	
}
