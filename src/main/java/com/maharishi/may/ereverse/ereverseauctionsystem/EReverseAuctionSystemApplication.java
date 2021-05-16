package com.maharishi.may.ereverse.ereverseauctionsystem;

import com.maharishi.may.ereverse.ereverseauctionsystem.account.service.AccountService;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Account;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Address;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.SystemAdmin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Date;
import java.util.List;

@SpringBootApplication()
public class EReverseAuctionSystemApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        ApplicationContext context=SpringApplication.run(EReverseAuctionSystemApplication.class, args);
        AccountService accountService=context.getBean(AccountService.class);
        if(!accountService.exists("admin")) {
            SystemAdmin admin=new SystemAdmin("foo","bar","foo",new Date(),"male","admin","admin",null);
            Account account=new Account("admin","admin", List.of(admin),new Address("123","fairfield","mount@mail.com"));
            accountService.create(account);
        }

    }
}
