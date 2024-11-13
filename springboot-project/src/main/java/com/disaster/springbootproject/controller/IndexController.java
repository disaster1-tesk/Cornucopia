package com.disaster.springbootproject.controller;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import com.codahale.metrics.Timer;
import io.micrometer.core.annotation.Counted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/index/")
public class IndexController {
    @Autowired
    private Meter requestMeter;

    @Autowired
    private Histogram responseSizes;

    @Autowired
    private Counter pendingJobs;

    @Autowired
    private Timer responses;

    @RequestMapping("login")
    @Counted
    public String login(){
        requestMeter.mark();

        pendingJobs.inc();

        responseSizes.update(new Random().nextInt(10));

        final Timer.Context context = responses.time();
        return "hello world";
    }
}
