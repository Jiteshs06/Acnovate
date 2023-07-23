package com.xyzcorporation.acnovate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    private String name;

    private String supervisor;

    public void setName(String name) {
        this.name = name.toLowerCase();
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor.toLowerCase();
    }
}
