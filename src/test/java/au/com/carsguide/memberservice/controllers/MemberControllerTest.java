package au.com.carsguide.memberservice.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import au.com.carsguide.memberservice.BaseTest;
import au.com.carsguide.memberservice.models.Member;
import net.minidev.json.JSONArray;

public class MemberControllerTest extends BaseTest {
   @Override
   @Before
   public void setUp() {
      super.setUp();
   }

   @Test
   public void createMember() throws Exception {
      String uri = "/members";
      Member member = new Member();
      member.setId(2L);
      member.setName("Tom");
      member.setEmail("tommy@gmail.com");
      String inputJson = super.mapToJson(member);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
         .contentType(MediaType.APPLICATION_JSON_VALUE)
         .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(201, status);
      String content = mvcResult.getResponse().getContentAsString();
      assertEquals("Member created successfully", content);
   }

   @Test
   public void getMembersList() throws Exception {
      String uri = "/members";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
    
      DocumentContext jsonContext = JsonPath.parse(content);
      
      JSONArray memberlist = jsonContext.read("$..[0:2]");
      assertTrue(memberlist.size() > 0);

      for (Object memberObj : memberlist) {
        LinkedHashMap<String, String> m = (LinkedHashMap<String, String>)memberObj;
        assertTrue(m.size() > 0);
        assertTrue(m.get("name").length() > 0);
        assertTrue(m.get("email").length() > 0);
      }
   }

   @Test
   public void getMember() throws Exception {
      String uri = "/members/4";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      Member member = super.mapFromJson(content, Member.class);
      assertTrue(member.getId() > 0);
      assertTrue(member.getName().length() > 0);
      assertTrue(member.getEmail().length() > 0);
   }

   @Test
   public void updateMember() throws Exception {
      String uri = "/members/4";
      Member member = new Member();
      member.setName("Jerry");
      member.setEmail("jerry@gmail.com");
      String inputJson = super.mapToJson(member);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
         .contentType(MediaType.APPLICATION_JSON_VALUE)
         .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      assertEquals("Member updated successfully", content);
   }

   @Test
   public void deleteMember() throws Exception {
      String uri = "/members/2";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      assertEquals("Member deleted successfully", content);
   }
}
