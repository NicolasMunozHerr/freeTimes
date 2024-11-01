package com.services.student_service.domain.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private Long id;

    private String firstname;

    private String lastname;

    private Integer age;

    private String address;

    public Student(Builder builder) {
        this.id = builder.id;
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.age = builder.age;
        this.address = builder.address;
    }

    public static Builder build() {
        return new Builder();
    }

    public static class Builder {
        private Long id;

        private String firstname;

        private String lastname;

        private Integer age;

        private String address;


        public Builder id(String id) {
            this.id = fromStringToLong(id);
            return this;
        }

        public Builder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public Builder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Builder age(String age) {
            this.age = fromStringToInteger(age);
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Student build() {
            return new Student(this);
        }

        private Long fromStringToLong(String number) {
            if (number == null || number.trim().isEmpty()) {
                return null;
            }
            try {
                return Long.parseLong(number);
            } catch (NumberFormatException e) {
                // Log error or handle it as needed
                return null;
            }
        }

        private Integer fromStringToInteger(String number) {
            if (number == null || number.trim().isEmpty()) {
                return null;
            }
            try {
                return Integer.parseInt(number);
            } catch (NumberFormatException e) {
                // Log error or handle it as needed
                return null;
            }
        }
    }

}
