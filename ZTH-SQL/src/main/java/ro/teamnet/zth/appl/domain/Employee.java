package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

/**
 * Created by Mi on 4/29/2015.
 */
@Table(name="employees")
public class Employee {

    @Id(name="employee_id")
    private int employeeId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lasttName;

    @Column(name="email")
    private String email;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="hire_date")
    private String hireDate;

    @Column(name="job_id")
    private int jobId;

    @Column(name="salary")
    private float salary;

    @Column(name="commsion_pct")
    private float commisionPct;

    @Column(name="manager_id")
    private int managerId;

    @Column(name="department_id")
    private int departmentId;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLasttName() {
        return lasttName;
    }

    public void setLasttName(String lasttName) {
        this.lasttName = lasttName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public float getCommisionPct() {
        return commisionPct;
    }

    public void setCommisionPct(float commisionPct) {
        this.commisionPct = commisionPct;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (employeeId != employee.employeeId) return false;
        if (jobId != employee.jobId) return false;
        if (Float.compare(employee.salary, salary) != 0) return false;
        if (Float.compare(employee.commisionPct, commisionPct) != 0) return false;
        if (managerId != employee.managerId) return false;
        if (departmentId != employee.departmentId) return false;
        if (firstName != null ? !firstName.equals(employee.firstName) : employee.firstName != null) return false;
        if (lasttName != null ? !lasttName.equals(employee.lasttName) : employee.lasttName != null) return false;
        if (email != null ? !email.equals(employee.email) : employee.email != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(employee.phoneNumber) : employee.phoneNumber != null)
            return false;
        return !(hireDate != null ? !hireDate.equals(employee.hireDate) : employee.hireDate != null);

    }

    @Override
    public int hashCode() {
        int result = employeeId;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lasttName != null ? lasttName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (hireDate != null ? hireDate.hashCode() : 0);
        result = 31 * result + jobId;
        result = 31 * result + (salary != +0.0f ? Float.floatToIntBits(salary) : 0);
        result = 31 * result + (commisionPct != +0.0f ? Float.floatToIntBits(commisionPct) : 0);
        result = 31 * result + managerId;
        result = 31 * result + departmentId;
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lasttName='" + lasttName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", hireDate='" + hireDate + '\'' +
                ", jobId=" + jobId +
                ", salary=" + salary +
                ", commisionPct=" + commisionPct +
                ", managerId=" + managerId +
                ", departmentId=" + departmentId +
                '}';
    }
}
