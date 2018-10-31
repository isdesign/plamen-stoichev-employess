/*
 * ************************************************************************* 
 *  
 *  @author	Plamen Stoichev  
 * 
 * ************************************************************************* 
 */
package sirmahomework;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author Plamen Stoichev < Copyright: 2015-2018 >
 */
public class ReadFromFile {

    String fileName = "default.txt";
    private static ReadFromFile instance;
    
    public ReadFromFile() {}
    
    public static synchronized ReadFromFile getInstance(){
        if(instance == null){
            instance = new ReadFromFile();
        }
        return instance;
    }
    
    public  List<Employee> getEmployeeList() {
       
       Scanner scanner = new Scanner(getClass().getResourceAsStream(fileName));
       return parseEmployee(scanner);  
}     
    public  List<Employee> getEmployeeList(String customFile) throws FileNotFoundException, NumberFormatException {
      
       Scanner scanner = new Scanner(new FileReader(customFile));      
       return parseEmployee(scanner);  
}     
   
    private List<Employee> parseEmployee(Scanner scanner) {
        
        List<Employee> employees = new ArrayList<>();
        
            while (scanner.hasNextLine()) {
                
                String[] splitData = scanner.nextLine().split(", ");
                
                Employee employee = new Employee();
                employee.setEmpID(Integer.valueOf(splitData[0]));
                employee.setProjectID(Integer.valueOf(splitData[1]));
                employee.setDateFrom(parseMultipleDateFormat(splitData[2]));
                employee.setDateTo(parseMultipleDateFormat(splitData[3]));
                
                // Добавя обект Employee към List<Employees>
                employees.add(employee);
            }
        
      return employees;
    }
    
    private Date parseMultipleDateFormat(String strDate){
        
        // Списък с поддържани формати на дати -> Добавяме при необходимост!
        final List<String> dateFormats = Arrays.asList("yyyy-MM-dd",
                                                       "dd-MM-yyyy",
                                                       "yyyy.MM.dd",
                                                       "dd.MM.yyyy",
                                                       "yyyy/MM/dd",
                                                       "dd/MM/yyyy",
                                                       "MMM d, ''yy",
                                                       "d MMM yyyy"
        );    

    for(String format: dateFormats){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        if (strDate.equalsIgnoreCase("Null")) {
            Date now = new Date();
            strDate = simpleDateFormat.format(now);
        }
        try{
            return simpleDateFormat.parse(strDate);
        } catch (ParseException e) {
            
        }
    }
        throw new IllegalArgumentException("Невалиден формат на календарна дата '" + strDate);
    }
    
    
}
