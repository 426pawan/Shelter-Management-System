package org.cts.housingaid.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.cts.housingaid.enums.HousingUnitStatus;
import org.cts.housingaid.enums.HousingUnitType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class HousingUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long housingUnitId;

    private String housingUnitLocation;

    @Enumerated(EnumType.STRING)
    private HousingUnitType housingUnitType;

    private Long housingUnitCapacity;

    @Enumerated(EnumType.STRING)
    private HousingUnitStatus housingUnitStatus;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "resourcesId")
    private Resources resources;

}