package org.cts.housingaid.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.cts.housingaid.enums.HousingProjectStatus;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class HousingProject{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long housingProjectId;

    private Long numberOfHouses;

    private String housingProjectTitle;

    private String housingProjectDescription;

    private LocalDate housingProjectStartDate;

    private LocalDate housingProjectEndDate;

    private Double housingProjectBudget;

    @ToString.Exclude
    @OneToMany(mappedBy = "housingProject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Resources> resources;

    @Enumerated(EnumType.STRING)
    private HousingProjectStatus housingProjectStatus;


}