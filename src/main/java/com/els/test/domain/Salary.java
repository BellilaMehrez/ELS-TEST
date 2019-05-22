package com.els.test.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * A salary
 */

@Document(collection = "salary")
public class Salary implements Serializable {
    @Id
    private String id;

    @NotNull
    @Field("first_name")
    private String firstName;

    @NotNull
    @Field("last_name")
    private String lastName;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Field("phone_number")
    private String phoneNumber;

    @NotNull
    private float salary;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salary salary1 = (Salary) o;
        return Float.compare(salary1.salary, salary) == 0 &&
                Objects.equals(id, salary1.id) &&
                Objects.equals(firstName, salary1.firstName) &&
                Objects.equals(lastName, salary1.lastName) &&
                Objects.equals(email, salary1.email) &&
                Objects.equals(phoneNumber, salary1.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, phoneNumber, salary);
    }

    @Override
    public String toString() {
        return "Salary{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", salary=" + salary +
                '}';
    }
}
