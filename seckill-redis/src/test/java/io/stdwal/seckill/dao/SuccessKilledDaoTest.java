package io.stdwal.seckill.dao;

import io.stdwal.seckill.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void testInsertSuccessKilled() throws Exception {
        long seckillId = 1000L;
        long userPhone = 13333333333L;
        int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
        System.out.println("insertCount = " + insertCount);
    }

    @Test
    public void testQueryByIdWithSeckill() throws Exception {
        long seckillId = 1000L;
        long userPhone = 13333333333L;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }

}