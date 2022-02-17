package com.example.demo.ziben;

import org.springframework.data.neo4j.annotation.QueryResult;

/**
 * 用于查询所有节点和关系的返回结果
 */
@QueryResult
public class TermR {
    private Term startTerm;
    private String rel;  // likes
    private Term endTerm;
    public TermR(){}

    public TermR(Term startTerm, String rel_name, Term endTerm){
        this.startTerm = startTerm;
        this.rel = rel_name;
        this.endTerm = endTerm;
    }

    public Term getStartTerm() {
        return startTerm;
    }

    public void setStartTerm(Term startTerm) {
        this.startTerm = startTerm;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public Term getEndTerm() {
        return endTerm;
    }

    public void setEndTerm(Term endTerm) {
        this.endTerm = endTerm;
    }
}
