package com.bridgelabz.employeepayrollservice;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Employee Payroll Service class that stores employee details and provides file operations.
 */
public class EmployeePayrollService implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String employeeId;
    private String employeeName;
    private long employeeSalary;

    /**
     * Constructor to initialize employee details
     * @param employeeId Employee ID
     * @param employeeName Employee Name
     * @param employeeSalary Employee Salary
     */
    public EmployeePayrollService(String employeeId, String employeeName, long employeeSalary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeSalary = employeeSalary;
    }

    /**
     * @desc Returns a string representation of the EmployeePayrollService object.
     * @return A string containing employee details.
     */
    @Override
    public String toString() {
        return "{" +
                "employeeId='" + employeeId + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", employeeSalary=" + employeeSalary +
                '}';
    }

    /**
     * @desc Writes the employee data to a file in CSV format.
     * @param fileName The name of the file to write to.
     */
    public void writeToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(employeeId + "," + employeeName + "," + employeeSalary);
            writer.newLine();  // Add a new line for each entry
            System.out.println("Employee data written to file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @desc Counts the number of entries in the specified file. [UC-5]
     * @param fileName The name of the file to count entries in.
     * @return The number of entries in the file.
     */
    public static int countEntries(String fileName) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * @desc Reads employee data from the specified file.
     * @param fileName The name of the file to read from.
     * @return A list of EmployeePayrollService objects containing the read data.
     */
    public static List<EmployeePayrollService> readFromFile(String fileName) {
        List<EmployeePayrollService> employeeList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    String employeeId = data[0];
                    String employeeName = data[1];
                    long employeeSalary = Long.parseLong(data[2]);
                    employeeList.add(new EmployeePayrollService(employeeId, employeeName, employeeSalary));
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    /**
     * @desc Displays the employee details stored in the specified file.
     * @param fileName The name of the file to display employee details from.
     */
    public static void displayEmployeeDetails(String fileName) {
        List<EmployeePayrollService> employeeList = readFromFile(fileName);
        System.out.println("Employee details read from file:");
        for (EmployeePayrollService employee : employeeList) {
            System.out.println(employee);
        }
    }
}