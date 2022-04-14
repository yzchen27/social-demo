package com.tanhua.dubbo.api;

import com.tanhua.model.mongo.RecommendUser;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @program: social-demo
 * @description:
 * @author: YzChen
 * @create: 2022-04-14 09:57
 **/
@DubboService
public class RecommendUserApiImpl implements RecommendUserApi {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public RecommendUser queryWithMaxScore(Long toUserId) {
        // 根据TOUserId查询 根据socre排序

        // 构建Criteria
        Criteria toUserId1 = Criteria.where("toUserId").is(toUserId);
        // 构建Query
        Query score = Query.query(toUserId1).with(Sort.by(Sort.Order.desc("score"))).limit(1);
        // 调用mongoTemplate查询

        return mongoTemplate.findOne(score, RecommendUser.class);
    }
}
