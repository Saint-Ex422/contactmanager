package com.singlestone;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class AppTest 
{
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CallListService callListService;


    HttpHeaders headers;

    @Before
    public void setUp(){


            String createContactUrl = "http://localhost:" + port + "/contacts";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);


            String json = "{\n" +
                    "    \"name\": {\n" +
                    "        \"first\": \"Harold\",\n" +
                    "        \"middle\": \"Francis\",\n" +
                    "        \"last\": \"Gilkey\"\n" +
                    "    },\n" +
                    "    \"address\": {\n" +
                    "        \"street\": \"8360 High Autumn Row\",\n" +
                    "        \"city\": \"Cannon\",\n" +
                    "        \"state\": \"Delaware\",\n" +
                    "        \"zip\": \"19797\"\n" +
                    "    },\n" +
                    "    \"phone\": [\n" +
                    "        {\n" +
                    "            \"number\": \"302-611-9148\",\n" +
                    "            \"type\": \"home\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"number\": \"302-532-9427\",\n" +
                    "            \"type\": \"mobile\"\n" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"email\": \"harold.gilkey@yahoo.com\"\n" +
                    "}";

            HttpEntity<String> request =
                    new HttpEntity<String>(json, headers);

            String result = restTemplate.postForObject(createContactUrl, request, String.class);

            json = "{\n" +
                    "    \"name\": {\n" +
                    "        \"first\": \"Kyle\",\n" +
                    "        \"middle\": \"Stephen\",\n" +
                    "        \"last\": \"Bauer\"\n" +
                    "    },\n" +
                    "    \"address\": {\n" +
                    "        \"street\": \"8360 High Autumn Row\",\n" +
                    "        \"city\": \"Cannon\",\n" +
                    "        \"state\": \"Delaware\",\n" +
                    "        \"zip\": \"19797\"\n" +
                    "    },\n" +
                    "    \"phone\": [\n" +
                    "        {\n" +
                    "            \"number\": \"203-368-8858\",\n" +
                    "            \"type\": \"home\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"number\": \"302-532-9427\",\n" +
                    "            \"type\": \"mobile\"\n" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"email\": \"harold.gilkey@yahoo.com\"\n" +
                    "}";

            request =
                    new HttpEntity<String>(json, headers);

            restTemplate.postForObject(createContactUrl, request, String.class);


            json = "{\n" +
                    "    \"name\": {\n" +
                    "        \"first\": \"Andrew\",\n" +
                    "        \"middle\": \"Francis\",\n" +
                    "        \"last\": \"Gilkey\"\n" +
                    "    },\n" +
                    "    \"address\": {\n" +
                    "        \"street\": \"8360 High Autumn Row\",\n" +
                    "        \"city\": \"Cannon\",\n" +
                    "        \"state\": \"Delaware\",\n" +
                    "        \"zip\": \"19797\"\n" +
                    "    },\n" +
                    "    \"phone\": [\n" +
                    "        {\n" +
                    "            \"number\": \"123-456-7890\",\n" +
                    "            \"type\": \"home\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"number\": \"302-532-9427\",\n" +
                    "            \"type\": \"mobile\"\n" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"email\": \"harold.gilkey@yahoo.com\"\n" +
                    "}";

            request =
                    new HttpEntity<String>(json, headers);

            restTemplate.postForObject(createContactUrl, request, String.class);




    }




    @Test
    public void testContactList(){

        String contactUrl = "http://localhost:"+port+"/contacts/call-list";

        CallListObj[] callList = this.restTemplate.getForObject(contactUrl,CallListObj[].class);

        CallListObj first = callList[0];
        CallListObj second = callList[1];
        CallListObj third = callList[2];

        assertTrue(first.getName().getFirst().equals("Kyle"));
        assertTrue(first.getName().getLast().equals("Bauer"));
        assertTrue(first.getPhone().equals("203-368-8858"));

        assertTrue(second.getName().getFirst().equals("Andrew"));
        assertTrue(second.getName().getLast().equals("Gilkey"));
        assertTrue(second.getPhone().equals("123-456-7890"));

        assertTrue(third.getName().getFirst().equals("Harold"));
        assertTrue(third.getName().getLast().equals("Gilkey"));
        assertTrue(third.getPhone().equals("302-611-9148"));
    }

    @Test
    public void unitTestContactList(){

        Contact contact1 = new Contact();
        contact1.setName(new Name("Harold","Francis","Gilkey"));
        Contact contact2 = new Contact();
        contact2.setName(new Name("Kyle","Stephen","Bauer"));
        Contact contact3 = new Contact();
        contact3.setName(new Name("Andrew","Francis","Gilkey"));

        ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
        PhoneNumber phone1 = new PhoneNumber();
        phone1.setNumber("302-611-9148");
        phone1.setContact(contact1);
        phoneNumbers.add(phone1);

        PhoneNumber phone2 = new PhoneNumber();
        phone2.setNumber("203-368-8858");
        phone2.setContact(contact2);
        phoneNumbers.add(phone2);

        PhoneNumber phone3 = new PhoneNumber();
        phone3.setNumber("123-456-7890");
        phone3.setContact(contact3);
        phoneNumbers.add(phone3);

        List<CallListObj> callList = callListService.convertContactsToCallListObj(phoneNumbers);

        CallListObj first = callList.get(0);
        CallListObj second = callList.get(1);
        CallListObj third = callList.get(2);

        assertTrue(first.getName().getFirst().equals("Kyle"));
        assertTrue(first.getName().getLast().equals("Bauer"));
        assertTrue(first.getPhone().equals("203-368-8858"));

        assertTrue(second.getName().getFirst().equals("Andrew"));
        assertTrue(second.getName().getLast().equals("Gilkey"));
        assertTrue(second.getPhone().equals("123-456-7890"));

        assertTrue(third.getName().getFirst().equals("Harold"));
        assertTrue(third.getName().getLast().equals("Gilkey"));
        assertTrue(third.getPhone().equals("302-611-9148"));
    }



}
