package com.industries.stark.springbootNinja.converter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestCrypt {

	public static void main(String[] args) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		System.out.println(pe.encode("user"));
		// $2a$10$q62KTDC58wkjGb6hzuDHeeugmP.9dxxbozGo23mndCePUhR.IQrkS
		// $2a$10$2OGo1JALauN.ch8qVMWIXeVKY0k3SvX3iFOgupwFUm0kdb4CZnPpK
	}

}
