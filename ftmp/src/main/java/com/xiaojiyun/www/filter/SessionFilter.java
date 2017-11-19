package com.xiaojiyun.www.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

public class SessionFilter extends OncePerRequestFilter {
	
     @Override
     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
             throws ServletException, IOException {
 
         // �����˵�uri
         String[] notFilter = new String[] {"/login"};
 
         // �����uri
         String uri = request.getRequestURI();
         // �Ƿ����
         boolean doFilter = true;
         for (String s : notFilter) {
             if (uri.indexOf(s) != -1) {
                 // ���uri�а��������˵�uri���򲻽��й���
            	 doFilter = false;
                 break;
             }
         }
 
         if (doFilter) {
             // ִ�й���
             // ��session�л�ȡ��¼��ʵ��
             Object obj = request.getSession().getAttribute("sessionUser");
             if (null == obj) {
                 boolean isAjaxRequest = isAjaxRequest(request);
                if (isAjaxRequest) {
                     response.setCharacterEncoding("UTF-8");
                     response.sendError(HttpStatus.UNAUTHORIZED.value(), "���Ѿ�̫��ʱ��û�в���,��ˢ��ҳ��");
                     return;
                 }
                 response.sendRedirect("/ftmp/login");
                 return;
             } else {
                 // ���session�д��ڵ�¼��ʵ�壬�����
                 filterChain.doFilter(request, response);
             }
         } else {
             // �����ִ�й��ˣ������
             filterChain.doFilter(request, response);
         }
     }
 
     /**
      * �ж��Ƿ�ΪAjax���� <������ϸ����>
      * 
      * @param request
      * @return ��true, ��false
      * @see [�ࡢ��#��������#��Ա]
      */
     public static boolean isAjaxRequest(HttpServletRequest request) {
         String header = request.getHeader("X-Requested-With");
         if (header != null && "XMLHttpRequest".equals(header))
            return true;
         else
             return false;
     }
 }
