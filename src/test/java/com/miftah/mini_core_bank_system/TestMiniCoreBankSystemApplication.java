package com.miftah.mini_core_bank_system;

import org.springframework.boot.SpringApplication;

public class TestMiniCoreBankSystemApplication {

	public static void main(String[] args) {
		SpringApplication.from(MiniCoreBankSystemApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
