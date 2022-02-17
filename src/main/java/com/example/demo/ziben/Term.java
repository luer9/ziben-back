package com.example.demo.ziben;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Term {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String term_id;
    private String interpret;
    public Term() {}

    public Term(String term_id, String name, String msg){
        this.name = name;
        this.term_id = term_id;
        this.interpret = msg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
// 查找所有的节点和关系

// 查找指定的节点及对应的关系

// 查找节点之间的关系