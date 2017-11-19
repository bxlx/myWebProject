package com.xiaojiyun.www.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailService implements UserDetailsService {   
	 
    //��½��֤ʱ��ͨ��username��ȡ�û�������Ȩ����Ϣ��  
    //������User�ŵ�spring��ȫ�ֻ���SecurityContextHolder�У��Թ���Ȩ��ʹ��  
    public UserDetails loadUserByUsername(String username)   
            throws UsernameNotFoundException, DataAccessException {     
        Collection<GrantedAuthority> auths=new ArrayList<GrantedAuthority>();   
 
        GrantedAuthorityImpl auth2=new GrantedAuthorityImpl("ROLE_ADMIN");   
        GrantedAuthorityImpl auth1=new GrantedAuthorityImpl("ROLE_USER");   
 
        if(username.equals("lcy")){   
            auths=new ArrayList<GrantedAuthority>();   
            auths.add(auth1);  
            auths.add(auth2);        
        }       
 
        User user = new User(username, "lcy", true, true, true, true, auths);   
        return user;    
        }   
    }
