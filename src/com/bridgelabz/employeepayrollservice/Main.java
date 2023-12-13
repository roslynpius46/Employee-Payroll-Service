package com.bridgelabz.employeepayrollservice;

import java.io.File;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        System.out.println("Employee Payroll Implementation");

        Scanner input = new Scanner(System.in);
        String employeeId, employeeName;
        long employeeSalary;
        System.out.println("Enter employee id: ");
        employeeId = input.next();
        System.out.println("Enter employee name in a single line: ");
        input.nextLine();
        employeeName = input.nextLine();
        System.out.println("Enter employee salary: ");
        employeeSalary = input.nextLong();

        EmployeePayrollService employee = new EmployeePayrollService(employeeId, employeeName, employeeSalary);

        // Specify the folder name where you want to store the employee details
        String folderName = "database";
        File folder = new File(folderName);

        // Create the "database" folder if it doesn't exist
        if (!folder.exists()) {
            if (folder.mkdir()) {
                System.out.println("Folder created: " + folder.getAbsolutePath());
            } else {
                System.out.println("Failed to create folder");
                return;
            }
        }

        // Specify the file path within the "database" folder
        String fileName = folderName + File.separator + "employee_data.txt";

        // Write employee data to the file
        employee.writeToFile(fileName);

        // Count the number of entries in the file
        int entryCount = EmployeePayrollService.countEntries(fileName);
        System.out.println("Number of entries in the file: " + entryCount);
    }
}
