package io.stdwal.seckill.service;

import io.stdwal.seckill.dto.Exposer;
import io.stdwal.seckill.dto.SeckillExecution;
import io.stdwal.seckill.entity.Seckill;
import io.stdwal.seckill.exception.RepeatKillException;
import io.stdwal.seckill.exception.SeckillCloseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void testGetSeckillList() {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}", list);
    }

    @Test
    public void testGetById() {
        long id = 1000L;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}", seckill);
    }

    @Test
    public void testExportSeckillUrl() {
        long id = 1000L;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer={}", exposer);
    }

    @Test
    public void testExecuteSeckill() {
        long id = 1000l;
        long phone = 13307232233L;
        String md5 = "8f60ffcbbb697aa1c503dd3a338d0cb3";
        try {
            SeckillExecution seckillExecution = seckillService.excuteSeckill(id, phone, md5);
            logger.info("result={}", seckillExecution);
        } catch (RepeatKillException e) {
            logger.error(e.getMessage());
        } catch (SeckillCloseException e) {
            logger.error(e.getMessage());
        }
    }

    @Test
    public void testSeckillLogic() {
        long id = 1000L;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if (exposer.isExposed()) {
            logger.info("exposer={}", exposer);
            long phone = 13307231246L;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution seckillExecution = seckillService.excuteSeckill(id, phone, md5);
                logger.info("result={}", seckillExecution);
            } catch (RepeatKillException e) {
                logger.error(e.getMessage());
            } catch (SeckillCloseException e) {
                logger.error(e.getMessage());
            }
        } else {
            // 秒杀未开启
            logger.warn("exposer={}", exposer);
        }
    }

    @Test
    public void executeSeckillProduce() {
        long seckill = 1001;
        long phone = 13340802323L;
        Exposer exposer = seckillService.exportSeckillUrl(seckill);
        if (exposer.isExposed()) {
            String md5 = exposer.getMd5();
            SeckillExecution execution = seckillService.excuteSeckillProceduce(seckill, phone, md5);
            logger.info(execution.getStateInfo());
        }
    }
}