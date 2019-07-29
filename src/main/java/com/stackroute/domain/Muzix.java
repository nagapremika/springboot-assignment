package com.stackroute.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//Using lombok to create setters and getters
public class Muzix {
    @Id //primary key
    private int trackId;

    private String trackName;

    private String comments;
}
