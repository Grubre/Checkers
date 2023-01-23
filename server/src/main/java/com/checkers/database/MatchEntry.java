package com.checkers.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Matches")
public class MatchEntry implements DatabaseEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="board_width")
    private Integer board_width;

    @Column(name="board_height")
    private Integer board_height;

    @ManyToOne
    @JoinColumn(name = "variant_id")
    private VariantEntry variant;
}
