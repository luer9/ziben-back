package com.example.demo.ziben;


import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.Set;

@QueryResult
public class TermRelation {

    private Term termU;

    private Set<Term> termsV;

    public TermRelation() {}

    public Term getTermU() {
        return termU;
    }

    public void setTermU(Term termU) {
        this.termU = termU;
    }

    public Set<Term> getTermsV() {
        return termsV;
    }

    public void setTermsV(Set<Term> termsV) {
        this.termsV = termsV;
    }
}
