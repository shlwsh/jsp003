package com.com.winning.sx.controller.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;
//import java.*;
import com.sun.org.apache.xpath.internal.operations.String;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/hello")
public class HelloController {
///Web003
    @RequestMapping("/Web003")
    public ModelAndView web003(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        try {

            java.lang.String first_name,second_name,code;
            first_name = request.getParameter("first_name");
            second_name =request.getParameter("second_name");


            Demo.mysql.mysqlDao db = new Demo.mysql.mysqlDao();
            db.connSQL();

            db.InsertNameList(first_name,second_name);
//            request.getRequestDispatcher("namelist.jsp").forward(request,response);


//        }catch (Exception err){
//            out.println("Db err");
//            out.println(err.getMessage());
//
//        }
//        finally {
//
//            out.close();
//        }
//        Map<java.lang.String, java.lang.String> map = new HashMap<java.lang.String, java.lang.String>();
//        map.put("userName", first_name);
        return new ModelAndView("/namelist");
//        java.lang.String strName="Hello";
//        Map<java.lang.String, java.lang.String> map = new HashMap<java.lang.String, java.lang.String>();
//        map.put("userName", strName);
//        return new ModelAndView("/hello",map);
    }

    @RequestMapping("/greeting")
    public ModelAndView greeting(@RequestParam(value="name", defaultValue="World") String name) {
        System.out.println("Hello " + name);
        Map<java.lang.String, String> map = new HashMap<java.lang.String, String>();
        map.put("userName", name);
        return new ModelAndView("/hello",map);
    }
    @RequestMapping("/hello")
    public ModelAndView Demo() {
        System.out.println("Hello World!" );
        Map<java.lang.String, java.lang.String> map = new HashMap<java.lang.String, java.lang.String>();
        map.put("userName", "Hello World!" );
        return new ModelAndView("/demo",map);
    }
    @ResponseBody
    @RequestMapping("/list")
    public List<java.lang.String> list(ModelMap modelMap) {
//        String hql = "select c from Clothing c ";
//        Page<Clothing> page = new Page<Clothing>();
//        page.setPageSize(6);
//        page  = clothingServiceImpl.queryForPageByHql(page, hql);
        List<java.lang.String> strList = new ArrayList<java.lang.String>();
        strList.add("aaaaaaaa");
        strList.add("bbbbbbbb");

        java.lang.String[] arr = new java.lang.String[] {"aaaaaaaa", "bbbbbbbb"};
        strList = Arrays.asList(arr);

        return strList;


    }
    @RequestMapping(value="/show/{name}", method = RequestMethod.GET)
    public @ResponseBody shop getShopInJSON(@PathVariable java.lang.String name) {
        System.out.println("-----请求json数据--------");
        shop shop1 = new shop();
        shop1.setName(name);
        shop1.setStaffName(new java.lang.String[]{"mkyong1", "mkyong2"});

        return shop1;

    }
    @RequestMapping("/namelist")
    @ResponseBody
    public Object namelist() throws IOException, SQLException {
        Demo.mysql.mysqlDao db = new Demo.mysql.mysqlDao();
        db.connSQL();
//        ResultSet rs = db.selectnamelist001();
        java.lang.String strJson = db.selectnamelist001();
        return strJson;
    }
}