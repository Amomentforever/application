package problem_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

public class Driver {

	public static void main(String[] args) {
		boolean auth = false;
		Vector<Instructor> instrVec = new Vector<Instructor>();
		Vector<TextBook> tbVec = new Vector<TextBook>();
		Vector<Course> csVec = new Vector<Course>();



		Scanner in = new Scanner(System.in);
		HashMap< String, String> users = new HashMap<String, String>();

		menu : while(true){
			String password="";
			System.out.println("Choose mode!");
			System.out.println("\n 1) User \n 2) Admin  \n 3) Exit");
			int choice = in.nextInt();
			if(choice==1){
				user: while(true){
					System.out.println("User mode");
					System.out.println("Enter username");
					System.out.println("\n 1)Return back\n 2) Exit");

					String username = in.next();

					if(username.equals("1"))continue menu;
					else if(username.equals("2")){System.out.println("Bye!!!"); break menu;}
					else{System.out.println("Enter password");
					password = in.next();
					}

					try {
						BufferedReader userRead = new BufferedReader(new FileReader(new File("C:\\программирование\\OOP\\eclipse new\\eclipse\\problems\\" +
								"lab5\\src\\problem_2","users.txt")));
						String user = userRead.readLine();
						while (user!=null) {
							StringTokenizer tkn = new StringTokenizer(user, " ");
							while(tkn.hasMoreTokens()){
								users.put(tkn.nextToken(), tkn.nextToken());
							}
							user = userRead.readLine();

						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

					for(Map.Entry<String, String> entry : users.entrySet()){
						if(entry.getKey().equals(username) && entry.getValue().equals("111")){
							System.out.println("Welcome "+entry.getKey());
							System.out.println("Enter new password:");
							String newPass = in.next();
							entry.setValue(newPass);
							try {	
								BufferedWriter chpas = new BufferedWriter(new FileWriter(new File("C:\\программирование\\OOP\\eclipse new\\eclipse\\problems\\" +
										"lab5\\src\\problem_2","users.txt")));
								for(Map.Entry<String, String> entry2 : users.entrySet()){
									chpas.write(entry2.getKey()+" "+entry2.getValue()+"\n");
								}
								chpas.close();
								System.out.println("Password changed\n");continue user;
							} catch (IOException e) {
								e.printStackTrace();
							}


						}

						else if(entry.getKey().equals(username) && entry.getValue().equals(password)) {auth = true;break;}
						else {auth  = false ;continue;}
					}

					if(auth){
						main:while(true){
							System.out.println("Welcome "+username+"!\n");
							System.out.println("1.List of available courses\n2.All Textbooks\n3.All Instrustors\n4.Change password\n5.Log out");
							choice = in.nextInt();

							if(choice==1){
								courses:while(true){

									System.out.println("---------------------Choose course------------------");
									System.out.println("---------Enter \"0\" to return back -------------");

									try {
										FileInputStream fis = new FileInputStream(new File("C:\\программирование\\OOP\\eclipse new\\eclipse\\problems\\" +
												"lab5\\src\\problem_2","course.txt"));
										ObjectInputStream ois = new ObjectInputStream(fis);
										Vector<Course> csV = (Vector<Course>)ois.readObject();

										for(int i=0;i<csV.size();i++){
											System.out.println((i+1)+") "+csV.get(i).getCourseTittle().toString());
										}
										choice=in.nextInt();
										if(choice==0)break courses;
										else if(choice>=0){
											info:while(true){
												System.out.println("Enter \"0\" to return back ");
												System.out.println(csV.get(choice-1).toString());
												choice = in.nextInt();
												if(choice==0)continue courses;
											}
										}
									} catch (FileNotFoundException e) {
										e.printStackTrace();
									} catch (IOException e) {
										e.printStackTrace();
									} catch (ClassNotFoundException e) {
										e.printStackTrace();
									}
								}
							}
							if(choice==2){

								try {
									FileInputStream fis = new FileInputStream(new File("C:\\программирование\\OOP\\eclipse new\\eclipse\\problems\\" +
											"lab5\\src\\problem_2","textBook.txt"));
									ObjectInputStream ois= new ObjectInputStream(fis);
									Vector<TextBook> tb = (Vector<TextBook>)ois.readObject();
									for(int i=0;i<tb.size();i++)
										System.out.println( (i+1)+") Author: "+tb.get(i).getAuthor().toString()+", Tittle: "+tb.get(i).getTittle().toString());
									fis.close();
									ois.close();								

								} catch (FileNotFoundException e) {
									e.printStackTrace();
								} catch (IOException e) {
									e.printStackTrace();
								} catch (ClassNotFoundException e) {
									e.printStackTrace();
								}
							}
							if(choice==3){
								try {
									FileInputStream fis = new FileInputStream(new File("C:\\программирование\\OOP\\eclipse new\\eclipse\\problems\\" +
											"lab5\\src\\problem_2","instructor.txt"));
									ObjectInputStream ois= new ObjectInputStream(fis);
									Vector<Instructor> tbInstr = (Vector<Instructor>)ois.readObject();
									for(int i=0;i<tbInstr.size();i++)
										System.out.println( (i+1)+") Instructor "+tbInstr.get(i).getFirst().toString()+" "+tbInstr.get(i).getLast().toString()+
												", Department "+tbInstr.get(i).getDepartment().toString()+", Email: "+tbInstr.get(i).getEmail().toString());
									fis.close();
									ois.close();								

								} catch (FileNotFoundException e) {
									e.printStackTrace();
								} catch (IOException e) {
									System.err.println("There aren't any instructors");
									//e.printStackTrace();
								} catch (ClassNotFoundException e) {
									e.printStackTrace();
								}
							}

							if(choice==4){
								System.out.println("Old password:");
								password = in.next();
								System.out.println("New password:");
								String newPass = in.next();
								for(Map.Entry<String, String> entry : users.entrySet()){
									if(entry.getKey().equals(username) && entry.getValue().equals(password)) {entry.setValue(newPass);break;}
								}

								try {	
									BufferedWriter chpas = new BufferedWriter(new FileWriter(new File("C:\\программирование\\OOP\\eclipse new\\eclipse\\problems\\" +
											"lab5\\src\\problem_2","users.txt")));
									for(Map.Entry<String, String> entry : users.entrySet()){
										chpas.write(entry.getKey()+" "+entry.getValue()+"\n");
									}
									chpas.close();
									System.out.println("Password changed\n");continue main ;
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
							else if(choice==5){
								continue user;
							}
						}
					}
					if(!auth) {System.out.println("Bad username or password\n1.Try again\n2.Back to menu\n3.Exit\n");
					choice = in.nextInt();
					if(choice==1)continue user;
					else if(choice==2) continue menu;
					else if(choice==3) {System.out.println("Bye!!!"); break menu;}
					}
					break;
				}
			}

			else if (choice==2){
				boolean adminAuth=false;

				admin: while(true){
					if(!adminAuth){
						System.out.println("Enter username:");
						String userName = in.next();
						System.out.println("Enter password:");
						String pass = in.next();

						try {
							BufferedReader adminRead = new BufferedReader(new FileReader(new File("C:\\программирование\\OOP\\eclipse new\\eclipse\\problems\\" +
									"lab5\\src\\problem_2","admin.txt")));
							String line = adminRead.readLine();
							StringTokenizer adminToken = new StringTokenizer(line," ");
							String adminUser = adminToken.nextToken();
							String adminPass = adminToken.nextToken();
							if(adminUser.equals(userName) && Integer.parseInt(adminPass)==pass.hashCode()){
								adminAuth=true;
							}
							else{
								System.out.println("Bad username or password"); continue admin;
							}



						} catch (FileNotFoundException e2) {
							e2.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}}


					if(adminAuth){
						GregorianCalendar gr = new GregorianCalendar();
						Date d= gr.getTime();
						String adminLi = d+" admin logged into a system\n";


						try {
							RandomAccessFile randAd = new RandomAccessFile(new File("C:\\программирование\\OOP\\eclipse new\\eclipse\\problems\\" +
									"lab5\\src\\problem_2","adminActions.txt"),"rw");
							randAd.seek(randAd.length());
							randAd.writeBytes(adminLi);
							randAd.close();

						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}

						adminPage:while(true){
							System.out.println("Admin mode");
							System.out.println("\n 1) Add TextBook  \n 2) Add Course \n 3) Add Intructor\n 4) Add User \n 5) Log out\n 6) Exit");
							choice = in.nextInt();

							if(choice==1){
								System.out.println("Enter ISBN of textbook:\n");
								int isbn = in.nextInt();
								System.out.println("Enter author of textbook:\n");
								String author = in.next();
								System.out.println("Enter tittle of textbook:\n");
								String tittle = in.next();

								try {

									FileOutputStream fos = new FileOutputStream( new File("C:\\программирование\\OOP\\eclipse new\\eclipse\\problems\\" +
											"lab5\\src\\problem_2","textBook.txt"));
									ObjectOutputStream ous = new ObjectOutputStream(fos);
									TextBook textBook = new TextBook(isbn,tittle,author);
									tbVec.add(textBook);
									ous.writeObject(tbVec);
									ous.flush();
									ous.close();

									String adminTb = new Date()+" admin added new textbook: "+tittle+"\n";
									RandomAccessFile randAd = new RandomAccessFile(new File("C:\\программирование\\OOP\\eclipse new\\eclipse\\problems\\" +
											"lab5\\src\\problem_2","adminActions.txt"),"rw");
									randAd.seek(randAd.length());
									randAd.writeBytes(adminTb);
									randAd.close();

									System.out.println("Textbook added");continue adminPage;



								} catch (FileNotFoundException e) {
									e.printStackTrace();
								} catch (IOException e) {
									e.printStackTrace();
								} 
							}

							if(choice==2){
								boolean cs = false;
								System.out.println("Enter course tittle:");
								String ct = in.next();
								System.out.println("Enter course intructor:");
								String instFirstName = in.next();
								String instLastName = in.next();
								System.out.println("Enter instructor department:");
								String dep = in.next();
								System.out.println("Enter instructor's email:");
								String em = in.next();
								System.out.println("Enter course textbook author:");
								String auth2 = in.next();
								System.out.println("Enter course textbook tittle:");
								String tb = in.next();
								System.out.println("Enter course textbook isbn");
								int tbIsbn=in.nextInt();
								TextBook newTb = new TextBook(tbIsbn,tb,auth2);
								Instructor newInstr = new Instructor(instFirstName,instLastName,dep,em);

								try {
									FileInputStream fisTb = new FileInputStream(new File("C:\\программирование\\OOP\\eclipse new\\eclipse\\problems\\" +
											"lab5\\src\\problem_2","textBook.txt"));
									FileInputStream fisInstr = new FileInputStream(new File("C:\\программирование\\OOP\\eclipse new\\eclipse\\problems\\" +
											"lab5\\src\\problem_2","instructor.txt"));

									FileOutputStream fos = new FileOutputStream(new File("C:\\программирование\\OOP\\eclipse new\\eclipse\\problems\\" +
											"lab5\\src\\problem_2","course.txt"));
									ObjectOutputStream ous = new ObjectOutputStream(fos);

									ObjectInputStream oisTb= new ObjectInputStream(fisTb);
									Vector<TextBook> tbVecD = (Vector<TextBook>)oisTb.readObject();

									ObjectInputStream oisInstr= new ObjectInputStream(fisInstr);
									Vector<Instructor> instrVecD = (Vector<Instructor>)oisInstr.readObject();

									if(tbVecD.contains(newTb) && instrVecD.contains(newInstr)){
										Course nCourse = new Course(ct,newTb,newInstr);
										csVec.add(nCourse);
										cs = true;
										ous.writeObject(csVec);
									}
									ous.close();

									fisTb.close();
									fos.close();
									fisInstr.close();
									oisInstr.close();
									oisTb.close();								

								} catch (FileNotFoundException e) {
									e.printStackTrace();
								} catch (IOException e) {
									e.printStackTrace();
								} catch (ClassNotFoundException e) {
									e.printStackTrace();
								}
								if(cs){
									String adminCr = new Date()+" admin added new Course: "+ct+"\n";
									try {
										RandomAccessFile randAd = new RandomAccessFile(new File("C:\\программирование\\OOP\\eclipse new\\eclipse\\problems\\" +
												"lab5\\src\\problem_2","adminActions.txt"),"rw");
										randAd.seek(randAd.length());
										randAd.writeBytes(adminCr);
										randAd.close();

									} catch (FileNotFoundException e) {
										e.printStackTrace();
									} catch (IOException e) {
										e.printStackTrace();
									}

									System.out.println("Course added");continue adminPage;}
							}

							if(choice==3){

								System.out.println("Enter firstname:");
								String fn = in.next();
								System.out.println("Enter secondname");
								String sn = in.next();
								System.out.println("Enter department:");
								String dep = in.next();
								System.out.println("Enter email:");
								String em = in.next();

								try {
									FileOutputStream fos = new FileOutputStream( new File("C:\\программирование\\OOP\\eclipse new\\eclipse\\problems\\" +
											"lab5\\src\\problem_2","instructor.txt"));

									ObjectOutputStream ous = new ObjectOutputStream(fos);
									Instructor instructor = new Instructor(fn,sn,dep,em);
									instrVec.add(instructor);
									ous.writeObject(instrVec);
									ous.flush();
									ous.close();

									String adminIns = new Date()+" admin added new instructor: "+fn+" "+sn+"\n";
									try {
										RandomAccessFile randAd = new RandomAccessFile(new File("C:\\программирование\\OOP\\eclipse new\\eclipse\\problems\\" +
												"lab5\\src\\problem_2","adminActions.txt"),"rw");
										randAd.seek(randAd.length());
										randAd.writeBytes(adminIns);
										randAd.close();

									} catch (FileNotFoundException e) {
										e.printStackTrace();
									} catch (IOException e) {
										e.printStackTrace();
									}



									System.out.println("Instructor added");continue adminPage;

								} catch (FileNotFoundException e) {
									e.printStackTrace();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}

							if(choice==4){
								try {

									System.out.println("Enter username:");
									String u = in.next();
									u+=" "+"111"+"\n";

									RandomAccessFile rand = new RandomAccessFile(new File("C:\\программирование\\OOP\\eclipse new\\eclipse\\problems\\" +
											"lab5\\src\\problem_2","users.txt"),"rw");
									rand.seek(rand.length());
									rand.writeBytes(u);
									rand.close();

									String adminU = new Date()+" admin added new user: "+u+"\n";
									RandomAccessFile randAd = new RandomAccessFile(new File("C:\\программирование\\OOP\\eclipse new\\eclipse\\problems\\" +
											"lab5\\src\\problem_2","adminActions.txt"),"rw");
									randAd.seek(randAd.length());
									randAd.writeBytes(adminU);
									randAd.close();

									System.out.println("User added");continue adminPage;


								} catch (FileNotFoundException e) {
									e.printStackTrace();
								} catch (IOException e) {
									e.printStackTrace();
								}

							}
							if(choice==5) {
								String adminLo = new Date()+" admin logged out a system\n";
								try {
									RandomAccessFile randAd = new RandomAccessFile(new File("C:\\программирование\\OOP\\eclipse new\\eclipse\\problems\\" +
											"lab5\\src\\problem_2","adminActions.txt"),"rw");
									randAd.seek(randAd.length());
									randAd.writeBytes(adminLo);
									randAd.close();

								} catch (FileNotFoundException e) {
									e.printStackTrace();
								} catch (IOException e) {
									e.printStackTrace();
								}
								continue menu;}
							if(choice==6) {System.out.println("Bye!!!"); break menu;}
							break;
						}
					}
				}
			}
			else if (choice==3){
				System.out.println("Bye!!!"); break menu;
			}
		}
	}
}