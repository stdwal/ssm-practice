CREATE DATABASE seckill;

USE seckill;

CREATE TABLE seckill(
    seckill_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品库存ID',
    name VARCHAR(120) NOT NULL COMMENT '商品名称',
    number INT NOT NULL COMMENT '库存数量',
    start_time TIMESTAMP NOT NULL COMMENT '秒杀开启时间',
    end_time TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (seckill_id),
    KEY idx_start_time(start_time),
    KEY idx_end_time(end_time),
    KEY idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

INSERT INTO
    seckill(name, number, start_time, end_time)
VALUES
    ('3000元秒杀IphoneX', 100, '2019-3-18 00:00:00', '2019-3-19  00:00:00'),
    ('2000元秒杀Ipad', 200, '2019-3-18 00:00:00', '2019-3-19  00:00:00'),
    ('5000元秒杀MacBook', 150, '2019-3-18 00:00:00', '2019-3-19  00:00:00'),
    ('1000元秒杀Iphone8', 100, '2019-3-18 00:00:00', '2019-3-19  00:00:00');


CREATE TABLE success_killed(
    seckill_id BIGINT NOT NULL COMMENT '秒杀商品ID',
    user_phone BIGINT NOT NULL COMMENT '用户手机号',
    state TINYINT NOT NULL DEFAULT 0 COMMENT '状态标识 -1无效 0成功',
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (seckill_id, user_phone),
    KEY idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';

