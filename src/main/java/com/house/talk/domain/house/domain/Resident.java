package com.house.talk.domain.house.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "residents")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Resident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "home_id", updatable = false)
    private Home homeId;

    @OneToOne
    @JoinColumn(name = "user_id", updatable = false)
    private User userId;

    @Column(name = "room_number")
    private String roomNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Resident resident = (Resident) o;
        return id != null && Objects.equals(id, resident.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
