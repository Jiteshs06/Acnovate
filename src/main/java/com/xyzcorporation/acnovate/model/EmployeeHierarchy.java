package com.xyzcorporation.acnovate.model;

import lombok.*;

@Getter
@NoArgsConstructor
@Data
public class EmployeeHierarchy {
    private String supervisor;

    private String supervisorOfSupervisor;

    public EmployeeHierarchy(String supervisor, String supervisorOfSupervisor) {
        this.supervisor = supervisor;
        this.supervisorOfSupervisor = supervisorOfSupervisor;
    }
}
