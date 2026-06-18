package com.ecolemusique.entity;

import com.ecolemusique.enumeration.Jour;
import com.ecolemusique.enumeration.Niveau;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Jour jour;

    @NotNull
    private LocalTime heureDebut;

    @NotNull
    @Min(15)
    @Max(180)
    private Integer dureeMin;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Niveau niveau;

    @NotNull
    @ManyToOne
    private Prof prof;
}
