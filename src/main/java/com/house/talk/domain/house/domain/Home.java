package com.house.talk.domain.house.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "homes")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "host_id", updatable = false)
    private User host;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Home home = (Home) o;
        return id != null && Objects.equals(id, home.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Home(Long id) {
        this.id = id;
    }
}
