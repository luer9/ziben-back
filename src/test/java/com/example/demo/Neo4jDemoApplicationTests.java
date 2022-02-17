package com.example.demo;

import com.example.demo.ziben.Term;
import com.example.demo.ziben.TermR;
import com.example.demo.ziben.TermRelation;
import com.example.demo.ziben.TermRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Neo4jDemoApplicationTests {
    @Autowired
    MovieRepository movieRepo;
    @Autowired
    PersonRepository personRepo;

    @Test
    public void contextLoads() {}

    @Test
    public void testSaveMovie() {
        
     movieRepo.deleteAll();
     personRepo.deleteAll();
     Movie m1 = new Movie("无问西东", "2018");
     Movie m2 = new Movie("罗曼蒂克消亡史", "2016");
     movieRepo.save(m1);
     movieRepo.save(m2);

    }

    @Test
    public void testSavePerson() {
    Person p1 = new Person("章子怡", "1979");
    Person p2 = new Person("李芳芳", "1976");
    Person p3 = new Person("程耳", "1970");
    Movie m1 = movieRepo.findByTitle("罗曼蒂克消亡史");
    Movie m2 = movieRepo.findByTitle("无问西东");   
    if (m1!=null) {
        p1.addActor(m1);
        p3.addDirector(m1);
    }
    if (m2!=null) {
        p1.addActor(m2);
        p2.addDirector(m2);
    }
    personRepo.save(p1);
    personRepo.save(p2);
    personRepo.save(p3);
    }
  
    @Test
    public void testfindByTitle() {
        Movie movie = movieRepo.findByTitle("罗曼蒂克消亡史");
        System.out.println(JsonSimple.toJson(movie));

    }
    
    @Test
    public void testfindByName() {
        Person person = personRepo.findByName("章子怡");
        System.out.println(JsonSimple.toJson(person));

    }

// ----------------------------------------------------------------
    @Autowired
    TermRepository termRepo;

    // 创建节点
    @Test
    public void testSaveTerm() {

        termRepo.deleteAll();
        Term t1 = new Term("1784", "鲁宾孙漂流记11","asdsadasdsadasdasdasahh哈哈哈阿斯达克建行卡号我看好卡");
        Term t2 = new Term("1", "传说","asdsadas啊实打实的了就卡死dsadasdasdasahh看好卡");
        termRepo.save(t1);
        termRepo.save(t2);
    }
    // 测试通过name得到term
    @Test
    public void testfindByTermName() {
        Term t1 = termRepo.findByName("赋税");
        System.out.println(JsonSimple.toJson(t1));

    }
    // 测试通过id得到term
    @Test
    public void testfindByTermId() {
        Term t1 = termRepo.findById("1");
        System.out.println(JsonSimple.toJson(t1));
    }
    // 测试得到所有的term
    @Test
    public void testfindAllNode() {
        List<Term> termList = termRepo.getTermList();
        System.out.println(JsonSimple.toJson(termList));
    }
    // 测试与name节点相关的节点
    @Test
    public void testfindTermByNameByRel() {
        List<TermRelation> termList = termRepo.findTermByNameByRel("赋税");
        System.out.println(JsonSimple.toJson(termList));
    }
    // 测试得到所有的节点和关系
    @Test
    public void testfindTermAndRel(){
        List<TermR> termAll = termRepo.findTermAll();
        System.out.println(JsonSimple.toJson(termAll));
    }


    @Test
    public void testselectTermByName(){
        // 模糊
        List<TermR> termAll = termRepo.selectTermByNameObscure("货币流通");
        System.out.println(JsonSimple.toJson(termAll));
    }
    // --------------------------------


    @Test
    public void testGetRandomTerm() {
        List<Term> term = termRepo.getRandomTerm(40, 50);
        System.out.println(JsonSimple.toJson(term));
    }

}

/**
 * MATCH (n)
 * OPTIONAL MATCH (n)-[r]-()
 * DELETE n,r
 *
 *
 * START t=node(*)
 * MATCH (a)-[:likes]->(t)
 * RETURN a
 * SKIP {randomoffset} LIMIT {randomcount}
 */
