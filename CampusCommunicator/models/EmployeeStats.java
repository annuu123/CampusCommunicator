package CampusCommunicator.models;

public class EmployeeStats {
    private String name;
    private int totalDays;
    private int daysWorked;
    private int leaves;
    private double salary;

    public EmployeeStats(String name, int totalDays, int daysWorked, double dailyRate) {
        this.name = name;
        this.totalDays = totalDays;
        this.daysWorked = daysWorked;
        this.leaves = totalDays - daysWorked;
        this.salary = dailyRate * daysWorked;
    }

    public String getName() { return name; }
    public int getTotalDays() { return totalDays; }
    public int getDaysWorked() { return daysWorked; }
    public int getLeaves() { return leaves; }
    public double getSalary() { return salary; }

    public String toString() {
        return "👤 " + name + " | 📅 Days Worked: " + daysWorked +
               " | 💤 Leaves: " + leaves + " | 💰 Salary: ₹" + salary;
    }
}
