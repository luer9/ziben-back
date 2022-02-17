package com.example.demo.ziben;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;

public class ETerm {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String term_id;
    private String interpret;
    private String flag;
    public ETerm() {}
    // 终止term flag 属性为 0
    public ETerm(String term_id, String name, String msg){
        this.name = name;
        this.term_id = term_id;
        this.interpret = msg;
        this.flag = "0";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTerm_id() {
        return term_id;
    }

    public void setTerm_id(String term_id) {
        this.term_id = term_id;
    }

    public String getInterpret() {
        return interpret;
    }

    public void setInterpret(String interpret) {
        this.interpret = interpret;
    }
}
