package com.sergey.prykhodko.parser.managment;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.sergey.prykhodko.parser.domain.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class ClientManagerImplTest {

    @Autowired
    private ClientManager clientManager;

    @Test
    @ExpectedDatabase("/db/client/expectedAddClient.xml")
    public void addsClientSuccessfully() {
        Client client = new Client();
        client.setFirstName("Bob");
        client.setLastName("Bin");
        client.setMiddleName("Nick");
        client.setInn(1232323);
        clientManager.save(client);
    }
}