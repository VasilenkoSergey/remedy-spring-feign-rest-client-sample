package io.vasilenko.remedy.spring.feign.restclient.sample;

import com.bmc.arsys.api.Value;
import com.bmc.arsys.pluginsvr.plugins.ARFilterAPIPlugin;
import com.bmc.arsys.pluginsvr.plugins.ARPluginContext;
import com.bmc.thirdparty.org.slf4j.Logger;
import com.bmc.thirdparty.org.slf4j.LoggerFactory;
import io.vasilenko.remedy.spring.feign.restclient.sample.dto.User;
import io.vasilenko.remedy.spring.feign.restclient.sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan
public class SpringFeignRestClientSamplePlugin extends ARFilterAPIPlugin {

    private final Logger log = LoggerFactory.getLogger(SpringFeignRestClientSamplePlugin.class);

    private AnnotationConfigApplicationContext applicationContext;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(ARPluginContext context) {
        applicationContext = new AnnotationConfigApplicationContext(SpringFeignRestClientSamplePlugin.class);
        applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
        log.info("initialized");
    }

    @Override
    public List<Value> filterAPICall(ARPluginContext context, List<Value> list) {
        List<User> users = userService.getUsers();
        log.info("users: {}", users);
        return new ArrayList<>();
    }

    @Override
    public void terminate(ARPluginContext context) {
        applicationContext.close();
    }
}
