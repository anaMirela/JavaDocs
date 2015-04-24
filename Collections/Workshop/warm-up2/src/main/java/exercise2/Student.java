package exercise2;

/**
 * Created by Radu.Hoaghe on 20.04.2015.
 */
public class Student {
    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final Double averageGrade;

    public Student(Integer id, String firstName, String lastName, Double averageGrade) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.averageGrade = averageGrade;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Double getAverageGrade() {
        return averageGrade;
    }

    // TODO Exercise 2 a) Override the toString() method
    @Override
    public String toString() {

        return "Student " + this.firstName + " " + this.lastName + " with id: " + String.valueOf(this.id) +
                " and with the average grade: " + String.valueOf(this.averageGrade);
    }

    // TODO Exercise 2 c) Override the equals() method
    @Override
    public boolean equals(Object o) {
        // TODO Exercise 2 c1) Check if the current instance is the same instance as the one from Object o
        if (this == o)
            return true;
        // TODO Exercise 2 c2) Check if Object o is null
        if (o == null)
            return false;
        // TODO Exercise 2 c3) Check if Object o class type is the same as the current instance's type
        if (!getClass().equals(o.getClass()))
            return false;
        // TODO Exercise 2 c4) Now you know for sure that the Object o is of type Student so you
        // TODO need to cast it to a Student type object
        Student s = (Student)o;
        // TODO Exercise 2 c5) Check if all the fields from Student class are equal to the ones from
        // TODO Exercise 2 c5) Object o (id, lastName, firstName, averageGrade)
        if (this.id == s.id && this.lastName.equals(s.lastName)
                    && this.firstName.equals(s.firstName) && this.averageGrade.equals(s.averageGrade))
            return true;
        return false;

        // TODO Exercise 2 d) After you finished implementing equals method go to TODO Exercise 2 e) from Exercise2 class
    }

    // TODO Exercise 2 g) Override the hashCode() method
    // TODO Exercise 2 g) Hint: Don't forget to include in the hashCode result all the fields from
    // TODO Exercise 2 g) the Student class
    @Override
    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + this.lastName.hashCode();
        hash = hash * 31 + this.id.hashCode();
        hash = hash * 31 + this.averageGrade.hashCode();
        return hash;
        // TODO Exercise 2 h) After you finished implementing hashCode go to TODO Exercise 2 i) from Exercise2 class
    }

}
