package be.jpendel.domain.person;

import java.time.LocalDate;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

public class Person {
    private final UUID uuid;
    private String firstName;
    private String lastName;
    private String phone;
    private LocalDate birthDate;

    Person(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getId() {
        return uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        checkNotNull(firstName, "The firstname is required");
        checkState(!firstName.isEmpty(), "The firstname is required");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        checkNotNull(lastName, "The lastName is required");
        checkState(!lastName.isEmpty(), "The lastName is required");
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        checkNotNull(phone, "The lastName is required");
        this.phone = phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        checkNotNull(birthDate, "The birthDate is required");
        checkState(birthDate.isBefore(LocalDate.now()), "The birthDate can not be in the future");
        this.birthDate = birthDate;
    }
}
