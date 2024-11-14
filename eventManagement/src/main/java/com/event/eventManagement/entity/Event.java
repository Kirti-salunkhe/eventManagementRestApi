package com.event.eventManagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Column(name="entry_fee")
    private Double fee;

    @JsonFormat(pattern = "yyyy-MM-DD")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-DD")
    private Date endDate;

    @ManyToMany(mappedBy = "events")
    private Set<User> users=new HashSet<>();
}
