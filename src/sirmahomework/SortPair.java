/*
 * ************************************************************************* 
 *  
 *  @author	Plamen Stoichev
 * 
 * ************************************************************************* 
 */
package sirmahomework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Plamen Stoichev < Copyright: 2015-2018 >
 */
public class SortPair {

    List<Employee> employees = new ArrayList<>();
    List<EmployeesPair> empPair = new ArrayList<>();
    
    public SortPair(List<Employee> employeesList) {
        this.employees = employeesList;
    }
    
    public List<EmployeesPair> getLongestTime(){
        
        addToPair(this.employees);
        Collections.sort(this.empPair, new PairComparator());
        return this.empPair;
    }
    
   
    // Сортира готовия списък в низходящ ред
    class PairComparator implements Comparator<EmployeesPair> {
    @Override
    public int compare(EmployeesPair pair1, EmployeesPair pair2) {
        return pair2.getTotalDays() - pair1.getTotalDays();
    }
}
    private List<EmployeesPair> addToPair(List<Employee> empList){
        for (int i = 0; i < empList.size(); i++) {
            Employee firstEmployee = empList.get(i);
            for (int j = 0; j < empList.size(); j++) {
                if(i != j) {
                Employee secondEmployee = empList.get(j);
                    if(firstEmployee.getProjectID() == secondEmployee.getProjectID()){
                        boolean overlap = checkDateOverlaps(firstEmployee.getDateFrom(), firstEmployee.getDateTo(), secondEmployee.getDateFrom(), secondEmployee.getDateTo());
                            if(overlap){
                                Date overlapStart = getStartDateOverlap(firstEmployee.getDateFrom(), secondEmployee.getDateFrom());
                                Date overlapEnd = getEndDateOverlap(firstEmployee.getDateTo(), secondEmployee.getDateTo());
                        
                                EmployeesPair pair = new EmployeesPair(firstEmployee.getEmpID(), 
                                                                        secondEmployee.getEmpID(), 
                                                                        firstEmployee.getProjectID(), 
                                                                        overlapStart, 
                                                                        overlapEnd);
                    
                                empPair.add(pair);
                            }
                    }
                } 
                
            }
            
        }
        Set<Integer> attributes = new HashSet<>();
        List duplicates = new ArrayList<>();
        for(EmployeesPair pair : empPair) {
            if(attributes.contains(pair.getProjectID())) {
                duplicates.add(pair);
            }
            attributes.add(pair.getProjectID());
        }
        empPair.removeAll(duplicates);
        return empPair;
    }
    
    private boolean checkDateOverlaps(Date startDate1, Date endDate1, Date startDate2, Date endDate2){
        if (startDate1 == null || endDate1 == null || startDate2 == null || endDate2 == null)
           return false;

        if ((startDate1.getTime() <= endDate2.getTime()) && (startDate2.getTime() <= endDate1.getTime()))
           return true;

        return false;
    }
    
   private Date getStartDateOverlap(Date employeeDate1, Date employeeDate2){
       if (employeeDate1.getTime() >= employeeDate2.getTime()){
           return employeeDate1;
       }else{
           return employeeDate2;
       }
   }
   
   private Date getEndDateOverlap(Date employeeDate1, Date employeeDate2){
       if (employeeDate1.getTime() <= employeeDate2.getTime()){
           return employeeDate1;
       }else{
           return employeeDate2;
       }
   }
       
}
