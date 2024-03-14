
/**
 * Program description:to make a record details for employee salary 
 *
 * Programmer:BUJAL
 * Date:14 March 2024
 */
import java.io.*;
import java.util.StringTokenizer;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
public class employeeSalaries
{
    public static void main(String[] args) throws IOException
    {
         DecimalFormat DF = new DecimalFormat("0.00");
         try
        {
            //input file
            BufferedReader employeeInput = new BufferedReader(new FileReader("employeeSalaries.txt"));
            //output files
            PrintWriter employeeOutput = new PrintWriter(new FileWriter("employeeData.txt"));
            
            
            //Declare the variables
            String inputData = null;
            String employname = "";
            double employSalary = 0.00;
            int employWorkingYear = 0;
            
            //variable for top performing employee
            String top_employname = "";
            double top_employSalary = 0.00;
            int top_employWorkingYear = 0;
            
            //variable for newest employee
            String latest_employname = "";
            double latest_employSalary = 0.00;
            int latest_employWorkingYear = 0;
            
            //Write the title header
            employeeOutput.println("-*-*-*-*-*-*-*-*-*-*-List of Employees-*-*-*-*-*-*-*-*-*-*-*-");
            
            while((inputData = employeeInput.readLine()) != null)
            {
                StringTokenizer strT = new StringTokenizer(inputData,"|");
                employname = strT.nextToken();
                employSalary = Double.parseDouble(strT.nextToken());
                employWorkingYear = Integer.parseInt(strT.nextToken());
                
                
                double AnnualSalary = employSalary + (employSalary * 0.05);
                
                //to test for the negative number
                if(employSalary < 0 || employWorkingYear <0)
                  throw new IllegalArgumentException();
                
                //find top performing employee
                if(AnnualSalary > top_employSalary){
                    top_employname = employname;
                    top_employSalary = AnnualSalary;
                    top_employWorkingYear = employWorkingYear;
                }
                //find the employee with the least years of service
                if(latest_employWorkingYear == 0 || employWorkingYear < latest_employWorkingYear){
                    latest_employname = employname;
                    latest_employSalary = AnnualSalary;
                    latest_employWorkingYear = employWorkingYear;
                    
                }
                
                //store list of employees
                String employData = employname+"\t\t $ "+AnnualSalary+"\t\t "+employWorkingYear+" years";
                employeeOutput.println(employData);
    
            }
            //top performing employee
            employeeOutput.println("\n\n -*-*-*-*-*-*-*-*-*- Top Performing Employee -*-*-*-*-*-*-*-*-*-");
            String top_employData = top_employname+"\t\t $ "+top_employSalary+"\t\t "+top_employWorkingYear+" years";
            employeeOutput.println(top_employData);
            //display top performing employee
            JOptionPane.showMessageDialog(null,"-*-*-*-*-*-*-*-*-Top performing Employee Details -*-*-*-*-*-*-*-\n"+top_employData);
            
            //latest employee
            employeeOutput.println("\n\n -*-*-*-*-*-*-*- Details of Employee with the least years of service -*-*-*-*-*-*-*-");
            String latest_employData = latest_employname+"\t\t $ "+latest_employSalary+"\t\t "+latest_employWorkingYear+" years";
            employeeOutput.println(latest_employData);
            JOptionPane.showMessageDialog(null,"-*-*-*-*-*-*-*-*-*-*-Details of Employee with the least years of service -*-*-*-*-*-*-*-*-*-*-*-\n"+latest_employData);

            //close all files
            employeeInput.close();
            employeeOutput.close();
            
        }
        catch(FileNotFoundException ex)
        {
            String output="File not found";
            JOptionPane.showMessageDialog(null, output);
        }
        catch(IllegalArgumentException iae)
        {
            String output="Invalid input!";
            JOptionPane.showMessageDialog(null, output);
        }
    }
}
