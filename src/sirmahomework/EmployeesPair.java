/*
 * ************************************************************************* 
 * 
 *  @author	Plamen Stoichev
 * 
 * ************************************************************************* 
 */
package sirmahomework;

import java.text.SimpleDateFormat;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.Date;

/**
 *
 * @author Plamen Stoichev < Copyright: 2015-2018 >
 */
public class EmployeesPair {
    
    private final int firstEmpID;
    private final int secondEmpID;
    private final int projectID;
    private final int totalDays;
    private final Date dateFrom;
    private final Date dateTo;

    public EmployeesPair(int firstEmpID, int secondEmpID, int projectID,  Date dateFrom, Date dateTo) {
        this.firstEmpID = firstEmpID;
        this.secondEmpID = secondEmpID;
        this.projectID = projectID;
        this.totalDays = getTotalDays(dateFrom, dateTo);
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public int getFirstEmpID() {
        return firstEmpID;
    }

    public int getSecondEmpID() {
        return secondEmpID;
    }

    public int getProjectID() {
        return projectID;
    }

    public int getTotalDays() {
        return this.totalDays;
    }
    
    
    //Метод за изчисляване на брой дни между две дати
    private int getTotalDays(Date fromDate, Date toDate){
        
        long difference = Math.abs(toDate.getTime() - fromDate.getTime());
        long differenceDates = difference / (24 * 60 * 60 * 1000);
        return (int) differenceDates;
    }
    
    @Override
    public String toString() {
        return "Employee{" + "FirstID=" + firstEmpID + ", SecondID=" + secondEmpID + ", ProjectID=" + projectID + ", DateFrom=" + new SimpleDateFormat("dd-MM-yyyy").format(dateFrom) + ", DateTo=" + new SimpleDateFormat("dd-MM-yyyy").format(dateTo) + ", Days=" + totalDays +  '}';
    }
}
