package org.example;

import org.example.model.Employee;
import org.example.model.PensionPlan;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(1, "Daniel", "Agar", LocalDate.of(2018, 1, 17),
                105945.5, new PensionPlan("EX1089", LocalDate.of(2023, 1, 17), 100)));
        employees.add(new Employee(2, "Benard", "Shaw", LocalDate.of(2019, 4, 3),
                197750, null));
        employees.add(new Employee(3, "Carly", "Agar", LocalDate.of(2014, 5, 16),
                842000.75, new PensionPlan("SM2307", LocalDate.of(2019, 11, 4), 1555.5)));
        employees.add(new Employee(4, "Wesley", "Schneider", LocalDate.of(2019, 5, 2),
                74500, null));

        // Task B-1
        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                int lastNameComparison = e1.getLastName().compareTo(e2.getLastName());
                if (lastNameComparison != 0) {
                    return lastNameComparison;
                }
                return Double.compare(e1.getSalary(), e2.getSalary());
            }
        });

        System.out.println("Printed in JSON Format");
        System.out.println(convertEmployeeListToJson(employees));

        // Task B-2
        List<Employee> filteredEmployees = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        LocalDate lastDayOfNextMonth = LocalDate.of(currentDate.getYear(),
                currentDate.getMonth().plus(1), 1).plusMonths(1).minusDays(1);
        for (Employee employee : employees) {
            LocalDate fiveYearsAfterStartDate = employee.getEmploymentDate().plusYears(5);
            if (employee.getPensionPlan() == null && fiveYearsAfterStartDate.isBefore(lastDayOfNextMonth)) {
                filteredEmployees.add(employee);
            }
        }

        System.out.println("Printed in JSON Format");
        System.out.println(convertEmployeeListToJson(filteredEmployees));
    }

    private static String convertEmployeeListToJson(List<Employee> employeeList) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[\n");

        for (int i = 0; i < employeeList.size(); i++) {
            Employee employee = employeeList.get(i);
            jsonBuilder.append("{");

            // Add product attributes to JSON object
            jsonBuilder.append("\"id\": ").append(employee.getId()).append(", ");
            jsonBuilder.append("\"firstName\": \"").append(employee.getFirstName()).append("\", ");
            jsonBuilder.append("\"lastName\": \"").append(employee.getLastName()).append("\", ");
            jsonBuilder.append("\"employmentDate\": ").append(employee.getEmploymentDate()).append(", ");
            jsonBuilder.append("\"yearlySalary\": ").append(employee.getYearlySalary());

            if (employee.getPensionPlan() != null) {
                jsonBuilder.append("\"pensionPlan\": {\"planReferenceNumber\": ")
                        .append(employee.getPensionPlan().getPlanReferenceNumber()).append(", ");
                jsonBuilder.append("\"enrollmentDate\": ")
                        .append(employee.getPensionPlan().getEnrollmentDate()).append(", ");
                jsonBuilder.append("\"monthlyContribution\": ")
                        .append(employee.getPensionPlan().getMonthlyContribution()).append(", ");

                jsonBuilder.append("}");
            }

            jsonBuilder.append("}");

            // Add comma if not the last element
            if (i < employeeList.size() - 1) {
                jsonBuilder.append(", \n");
            }
        }

        jsonBuilder.append("\n]");

        return jsonBuilder.toString();
    }

}