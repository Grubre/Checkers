package com.checkers.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Moves")
public class MoveEntry implements DatabaseEntry {
    @Id
    private Integer turn_number;

    @Id
    private Integer move_number;

    @Column(name = "starting_x")
    private Integer starting_x;

    @Column(name = "starting_y")
    private Integer starting_y;

    @Column(name = "target_x")
    private Integer target_x;

    @Column(name = "target_y")
    private Integer target_y;
    
    @ManyToOne
    @JoinColumn(name = "match_id")
    private MatchEntry match;
}
