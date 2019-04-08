package com.pl.service.impl;

import com.pl.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LogManager.getLogger();

    @Override
    public boolean placeOrder() {
        log.info("下订单咯");
        return true;
    }
}
