/*
 * ************************************************************************* 
 * 
 *  @author	Plamen Stoichev  
 * 
 * ************************************************************************* 
 */
package sirmahomework;


import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author Plamen Stoichev < Copyright: 2015-2018 >
 */
public class Employee {
    
    private int empID;
    private int projectID;
    private Date dateFrom;
    private Date dateTo;
    

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public String toString() {
        return "Employee{" + "EmpID=" + empID + ", ProjectID=" + projectID + ", DateFrom=" + new SimpleDateFormat("dd-MM-yyyy").format(dateFrom) + ", DateTo=" + new SimpleDateFormat("dd-MM-yyyy").format(dateTo) + '}';
    }
    
    
    
}
