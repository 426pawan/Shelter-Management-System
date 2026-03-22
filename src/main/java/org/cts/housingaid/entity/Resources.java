package org.cts.housingaid.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.cts.housingaid.enums.ResourcesStatus;
import org.cts.housingaid.enums.ResourcesType;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Resources {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourcesId;

    @Enumerated(EnumType.STRING)
    private ResourcesType resourcesType;

    @Enumerated(EnumType.STRING)
    private ResourcesStatus resourcesStatus;

    private Long resourcesQuantity;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "housingProjectId")
    private HousingProject housingProject;

    @ToString.Exclude
    @OneToMany(mappedBy = "resources", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HousingUnit> housingUnit;

}