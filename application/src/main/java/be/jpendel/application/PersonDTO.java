package be.jpendel.application;

import java.time.LocalDate;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;

public class PersonDTO {

    public final UUID uuid;
    public final String firstName;
    public final String lastName;
    public final LocalDate birthDate;
    public final String phone;

    private PersonDTO(Builder builder) {
        uuid = builder.uuid;
        firstName = builder.firstName;
        lastName = builder.lastName;
        birthDate = builder.birthDate;
        phone = builder.phone;
    }



    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private UUID uuid;
        private String firstName;
        private String lastName;
        private LocalDate birthDate;
        private String phone;

        private Builder() {
        }

        public Builder withUuid(UUID val) {
            uuid = val;
            return this;
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

        public PersonDTO build() {
            checkNotNull(uuid);
            checkNotNull(firstName);
            checkNotNull(lastName);
            checkNotNull(birthDate);
            checkNotNull(phone);
            return new PersonDTO(this);
        }
    }
}
