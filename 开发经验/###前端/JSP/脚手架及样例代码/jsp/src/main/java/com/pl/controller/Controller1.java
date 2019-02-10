package com.pl.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "controller1", urlPatterns = "/controller1")
public class Controller1 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Controller1接收请求");
		Enumeration<String> attributeNames = req.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			String attributeName = attributeNames.nextElement();
			String attributeValue = (String) req.getAttribute(attributeName);
			System.out.println(attributeName + "   :   " + attributeValue);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Controller1接收请求");
		Enumeration<String> attributeNames = req.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			String attributeName = attributeNames.nextElement();
			String attributeValue = (String) req.getAttribute(attributeName);
			System.out.println(attributeName + "   :   " + attributeValue);
		}
	}

}
