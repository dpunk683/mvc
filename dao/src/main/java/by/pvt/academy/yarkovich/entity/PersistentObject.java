package by.pvt.academy.yarkovich.entity;

import jdk.nashorn.internal.objects.annotations.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class PersistentObject implements LongID {
    @Getter
    public Long getId() {
        return id;
    }

    @Setter
    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)

    private Long id;

    @Override
    public String toString() {
        if (getId() != null) {
            return Long.toString(getId());
        } else {
            return "";
        }
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + (getId() == null ? 0 : getId().hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (!Hibernate.getClass(other).equals(Hibernate.getClass(this))) {
            return false;
        }

        return getId().equals(((PersistentObject) other).getId());
    }
}

