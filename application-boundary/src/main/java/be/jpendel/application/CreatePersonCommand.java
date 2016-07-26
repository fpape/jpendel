package be.jpendel.application;

import java.time.LocalDate;

public class CreatePersonCommand {
    public final String firstName;
    public final String lastName;
    public final LocalDate birthDate;
    public final String phone;

    private CreatePersonCommand(Builder builder) {
        firstName = builder.firstName;
        lastName = builder.lastName;
        birthDate = builder.birthDate;
        phone = builder.phone;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String firstName;
        private String lastName;
        private LocalDate birthDate;
        private String phone;

        private Builder() {
        }

        public Builder withFirstName(String val) {
            firstName = val;
            return this;
        }

        public Builder withLastName(String val) {
            lastName = val;
            return this;
        }

        public Builder withBirthDate(LocalDate val) {
            birthDate = val;
            return this;
        }

        public Builder withPhone(String val) {
            phone = val;
            return this;
        }

        public CreatePersonCommand build() {
            return new CreatePersonCommand(this);
        }
    }
}
