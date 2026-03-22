package org.cts.housingaid.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cts.housingaid.enums.CitizenGender;
import org.cts.housingaid.enums.CitizenStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Citizen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long citizenId;

    private LocalDate citizenDOB;

    @Enumerated(EnumType.STRING)
    private CitizenGender citizenGender;

    private String citizenAddress;

    private String citizenContactInfo;

    @Enumerated(EnumType.STRING)
    private CitizenStatus citizenStatus;

    @OneToOne
    @JoinColumn(name = "citizenId")
    private Users user;

    @OneToMany(mappedBy = "citizen", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CitizenDocument> documents = new ArrayList<>();

}