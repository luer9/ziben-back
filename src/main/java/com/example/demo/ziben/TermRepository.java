package com.example.demo.ziben;


import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TermRepository extends Neo4jRepository<Term, Long> {

    // 根据name获取节点
    Term findByName(String name);  // get_term_by_name

    // 获取所有的节点
    @Query("MATCH (n:Term) RETURN n ")
    List<Term> getTermList(); // all

    // 根据id获取节点
    @Query("MATCH (n:Term) WHERE n.term_id = {id} RETURN n")
    Term findById(@Param("id")String term_id);  // get_term_by_id

    // 查找与term具有likes关系的所有节点 demo

    // eg: match (n:Term)-[r:likes]->(b:Term) where n.name = '赋税' return b
    @Query("MATCH (n:Term)-[r:likes]->(b:Term) where n.name = {name} return b")
    List<Term> findTermByNameByRel_demo(@Param("name") String name);


    // 查找与term具有likes关系的所有节点
    // eg: match (n:Term)-[r:likes]->(b:Term) where n.name = '赋税' return b
    @Query("MATCH (n:Term)-[r:likes]->(b:Term) where n.name = {name} return n as termU, collect(b) as termsV")
    List<TermRelation> findTermByNameByRel(@Param("name") String name);


    // 获取所有的节点和关系 limit 100
    @Query("MATCH (n:Term)-[r:likes]->(b:Term) RETURN n as startTerm, 'likes' as rel ,b as endTerm LIMIT 120")
    List<TermR> findTermAll();



    // 根据name进行模糊查询  eg:match(emp) where emp.name =~'.*货币流通.*' return emp
    //@Query("MATCH (n:Term)-[r:likes]->(b:Term) WHERE n.name =~ {name} RETURN n as startTerm, 'likes' as rel, b as endTerm")
    @Query("MATCH (n:Term)-[r:likes]->(b:Term) WHERE n.name ={name} RETURN n as startTerm, 'likes' as rel, b as endTerm")
    List<TermR> selectTermByNamePrecise(@Param("name") String name);

    // 根据name进行模糊查询  eg:match(emp) where emp.name =~'.*货币流通.*' return emp
    //@Query("MATCH (n:Term)-[r:likes]->(b:Term) WHERE n.name =~ {name} RETURN n as startTerm, 'likes' as rel, b as endTerm")
    @Query("MATCH (n:Term)-[r:likes]->(b:Term) WHERE n.name =~('.*'+$name+'.*') RETURN n as startTerm, 'likes' as rel, b as endTerm")
    List<TermR> selectTermByNameObscure(@Param("name") String name);

    // 根据出度+入度返回Term排名
    @Query("match (n:Term) with n, size((n)-[]->() + (n)<-[]-()) as s order by s desc return n.name limit 100")
    List<String> rankTerm();

    // 返回所有的术语名
    @Query("match (n:Term) with n return n.name")
    List<String> getTermAllName();

    // 随机返回一部分节点
    @Query("START t=node(*) \n" +
            "MATCH (a)-[:likes]->(t) \n" +
            "RETURN a\n" +
            "SKIP {randomoffset} LIMIT {randomcount}")
    List<Term> getRandomTerm(Integer randomoffset, Integer randomcount);

}

// START t=node(*)
//MATCH (a)-[:likes]->(t)
//RETURN a
//SKIP {randomoffset} LIMIT {randomcount}