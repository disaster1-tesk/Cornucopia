package com.disaster;

import com.disaster.ioc.assembly.CompactDisc;
import com.disaster.ioc.assembly.MediaPlayer;
import com.disaster.ioc.condition.ConditionConfig;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConditionConfig.class)
//@ImportResource({"classpath:spring-bean.xml","classpath:spring-dev.xml"})
//@ActiveProfiles("dev")
public class ProjectTest {


    @Autowired(required = false)
    private CompactDisc compactDisc;

    @Autowired(required = false)
    private MediaPlayer mediaPlayer;

    @Autowired
    private String dataSource;

    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertNotNull( compactDisc);
    }

    @Test
    public void mediaPlayerTest(){
        mediaPlayer.play();
//        assertEquals("log testing",log.getLog());
    }

    @Test
    public void conditionTest(){
        System.out.println(dataSource);
    }
}
