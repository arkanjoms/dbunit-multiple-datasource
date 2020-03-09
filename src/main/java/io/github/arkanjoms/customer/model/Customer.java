package io.github.arkanjoms.customer.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(schema = "customer", name = "customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 8964828854901575L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
