package io.stdwal.seckill.service;

import io.stdwal.seckill.dto.Exposer;
import io.stdwal.seckill.dto.SeckillExecution;
import io.stdwal.seckill.entity.Seckill;
import io.stdwal.seckill.exception.RepeatKillException;
import io.stdwal.seckill.exception.SeckillCloseException;
import io.stdwal.seckill.exception.SeckillException;

import java.util.List;

/**
 * 业务接口：站在使用者角度设计
 * 三个方面：方法定义粒度，参数，返回类型
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开启时输出秒杀接口地址
     * 否则输出系统时间和秒杀时间
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution excuteSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatKillException, SeckillCloseException;

    /**
     * 执行秒杀操作 by proceduce
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution excuteSeckillProceduce(long seckillId, long userPhone, String md5);

}
