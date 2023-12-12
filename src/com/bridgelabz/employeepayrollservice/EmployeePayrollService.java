package com.bridgelabz.employeepayrollservice;

public class EmployeePayrollService {
    private String employeeId;
    private String employeeName;
    private long employeeSalary;

    /**
     * @desc Parametrized constructor to initialize the values of the object of EmployeePayrollService class
     * @param employeeId ID of employee
     * @param employeeName Employee Name
     * @param employeeSalary Salary of Employee
     */
    public EmployeePayrollService(String employeeId, String employeeName, long employeeSalary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeSalary = employeeSalary;
    }

    /**
     * @desc Method to print all the Details of the Employee
     * @return string to be printed containing all details of employee
     */
    @Override
    public String toString() {
        return "{" +
                "employeeId=" + employeeId + ",\n" +
                "employeeName=" + employeeName + ",\n" +
                "employeeSalary=" + employeeSalary + ",\n" +
                '}';
    }
}