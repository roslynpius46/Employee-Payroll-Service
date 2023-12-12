package com.bridgelabz.employeepayrollservice;
import java.util.Scanner;

public class Main {
    public static void main(String [] args)
    {
        System.out.println("Employee Payroll Implementation");

        Scanner input = new Scanner(System.in);
        String employeeId , employeeName;
        long employeeSalary;
        System.out.println("Enter employee id : ");
        employeeId = input.next();
        System.out.println("Enter employee name in a single line : ");
        input.nextLine();
        employeeName = input.nextLine();
        System.out.println("Enter employee salary : ");
        employeeSalary = input.nextLong();

        EmployeePayrollService employee = new EmployeePayrollService(employeeId,employeeName,employeeSalary);

        System.out.println("Employee payroll data : " + employee);
    }
}
