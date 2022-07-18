package br.com.coffe.explorer.coffe.service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "coffes")
public class CoffeTableEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "description")
    private String description;

    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "flavor_code")
    private String flavorCode;

    @OneToMany(mappedBy = "coffeId")
    private List<CoffeImageTableEntity> images;

}
