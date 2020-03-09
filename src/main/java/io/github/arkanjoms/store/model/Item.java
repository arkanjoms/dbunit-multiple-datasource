package io.github.arkanjoms.store.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(schema = "store", name = "item")
public class Item implements Serializable {

    private static final long serialVersionUID = 269810374625350467L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String description;
}
