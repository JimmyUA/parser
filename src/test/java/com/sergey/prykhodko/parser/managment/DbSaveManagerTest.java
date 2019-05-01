package com.sergey.prykhodko.parser.managment;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class DbSaveManagerTest {

    @Autowired
    private DbSaveManager saveManager;

    @Test
    public void savesTestFileResult() {
        boolean result = saveManager.save("src/test/resources/test.xml");
        assertTrue(result);
    }

    @Test
    @ExpectedDatabase("/db/save/allTransactions.xml")
    public void savesTestFileResultCorrectly() {
        boolean result = saveManager.save("src/test/resources/test.xml");
        assertTrue(result);
    }
}