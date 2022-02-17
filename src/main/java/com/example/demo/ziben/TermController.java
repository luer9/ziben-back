package com.example.demo.ziben;

import com.example.demo.JsonSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "term")
public class TermController {
    @Autowired
    private TermRepository termRepository;

    // 根据name获取term节点
    @GetMapping(value = "getByName")
    public Term GetTermByName(@RequestParam(value = "name") String name) {
        return termRepository.findByName(name);
    }
    // 获取所有节点
    @GetMapping(value = "getAll")
    public List<Term> getAll() {
        List<Term> all = termRepository.getTermList();
        return all;
    }

    // 根据name获取与其相关的节点
    @GetMapping(value = "getByNameByRel")
    public List<TermRelation> GetTermByNameByRel(@RequestParam(value = "name") String name) {
        return termRepository.findTermByNameByRel(name);
    }


    // 前端用
    // 根据name模糊查询 返回TermR
    @GetMapping(value = "selectByName")
    public List<TermR> SelectTermByName(String name) {
        System.out.println("这是怎么一回事啊");
        // 先精确 后模糊
        List<TermR> termRSPrecise = termRepository.selectTermByNamePrecise(name);
//        System.out.println("----->" + termRSPrecise);
        // 精确为空
        if(!termRSPrecise.isEmpty()) {
            return termRSPrecise;
        }else {
            return termRepository.selectTermByNameObscure(name);
        }

    }
    // 前端用
    // 获取所有的节点和关系  limit 100
    @GetMapping(value = "getTermAndRel")
    public List<TermR> GetTermAndRel(){
        return termRepository.findTermAll();
    }

    // 获取term排名  limit  50
    @GetMapping(value = "getTermRank")
    public List<String> GetTermRank(){
        return termRepository.rankTerm();
    }

    // 获取所有term Name
    @GetMapping(value = "getAllTermName")
    public List<String> GetTermAllName(){
        return termRepository.getTermAllName();
    }


    // 随机获取节点，返回《资本论》核心词的termR
    @GetMapping(value = "getRandomTerm")
    public List<TermR> GetRandomTerm() {
        List<TermR> randomTerm = new ArrayList<>();
        Integer randomoffset = new Random().nextInt(30) + 30;
        Integer randomcount = new Random().nextInt(50) + 50;
        List<Term> enTerms = termRepository.getRandomTerm(randomoffset, randomcount);
        Term stTerm = new Term("0", "《资本论》", "《资本论》术语知识图谱，包括1738个术语，相似关系34760条");
        stTerm.setId(Long.valueOf(0));
        for (Term enTerm: enTerms) {
            TermR termR = new TermR(stTerm, "contains", enTerm);
            randomTerm.add(termR);
        }
        System.out.println(JsonSimple.toJson(randomTerm));
        return randomTerm;
        //return termRepository.getRandomTerm();
    }
}
