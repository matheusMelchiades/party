package br.edu.ulbra.election.party.model;


import javax.persistence.*;

@Entity
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private Integer number;

    public long getId() { return id; }

    public void setId(long id) {
        System.out.println(id); this.id = id; }

    public String getCode() { return code; }

    public void setCode(String code) {
        System.out.println(code);this.code = code; }

    public String getName() { return name; }

    public void setName(String nome) {
        System.out.println(nome);
        this.name = nome; }

    public Integer getNumber() {
        return number; }

    public void setNumber(Integer number) {
        System.out.println(number);
        this.number = number; }

}
