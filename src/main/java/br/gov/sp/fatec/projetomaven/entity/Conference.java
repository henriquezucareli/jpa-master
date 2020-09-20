package br.gov.sp.fatec.projetomaven.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "con_conference")
@Entity
public class Conference {
    
    @Column(name = "con_name")
    private String conference;
}