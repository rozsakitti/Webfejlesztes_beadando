package com.example.pet;

import com.example.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String chipszam;
    @Column(length=50, nullable = false, name="kisallatneve")
    private String kisallatneve;

    @Column(length = 50, nullable = false, name="fajtaja")
    private String fajtaja;

    @Column(length = 15, nullable = false, name="szine")
    private String szine;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChipszam() {
        return chipszam;
    }

    public void setChipszam(String chipszam) {
        this.chipszam = chipszam;
    }

    public String getKisallatneve() {
        return kisallatneve;
    }

    public void setKisallatneve(String kisallatneve) {
        this.kisallatneve = kisallatneve;
    }

    public String getFajtaja() {
        return fajtaja;
    }

    public void setFajtaja(String fajtaja) {
        this.fajtaja = fajtaja;
    }

    public String getSzine() {
        return szine;
    }

    public void setSzine(String szine) {
        this.szine = szine;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", chipszam='" + chipszam + '\'' +
                ", kisallatneve='" + kisallatneve + '\'' +
                ", fajtaja='" + fajtaja + '\'' +
                ", szine='" + szine + '\'' +
                ", user=" + user +
                '}';
    }
}

