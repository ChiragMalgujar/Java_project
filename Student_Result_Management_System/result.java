import java.util.Scanner;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class result{
    public static void main(String[] args);{
        Scanner sc=new Scanner(System.in);
        System.out.println("Student Result Management System");
        String resultfile="result.csv";
    
        do { 
            System.out.println("1.Add student details");
            System.out.println("2.Calculate Average CGPA/Percentage of class");
            System.out.println("3.Display report card");
            System.out.println("4.Exit");
            System.out.println("Enter want you want to do:--");
            int choice=sc.nextInt();
            switch(choice){
                case 1:
                    try (FileWriter wr= new FileWriter(csvFile)){
                        System.out.println("Give Student Detail to Add");
                        System.out.println("Enter student name:--");
                        String name = sc.nextLine();
                        System.out.println("Enter Student Class number:--");
                        int classes=sc.nextInt();
                        System.out.println("Enter Student Roll Number:--");
                        int roll_no=sc.nextInt();
                        System.out.println("Enter Total_Marks:--");
                        int mark=sc.nextInt();
                        writer.append(name,classes,roll_no,mark);
                        System.out.println("Added Successfully");
                      
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                case 2:
                    System.out.println("2.Calculate Average CGPA/Percentage of class");
                    try(BufferedReader br= new BufferedReader(new FileReader(csvFile))){
                        String line;
                        boolean header=true;
                        while((line=br.readLine())!=null){
                            String[] mark=line.split(",");
                            int average=0;
                            if (header){
                                header=false;
                                continue;
                            }
                            int a+=mark[3];
                            average=a/mark.length;
                            System.out.println("Average mark of class is :--"+average);
                            break;
                        }
                    }catch(IOExceptions e){
                        e.printStackTrace();
                    }
                case 3:
                    System.out.println("Display report card");
                    


                    



                    

            }
        } while (true);
    }
}
