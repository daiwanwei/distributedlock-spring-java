package com.example.distributedlockspringjava.controller;


import com.example.distributedlockspringjava.pkg.Resp;
import com.example.distributedlockspringjava.pkg.lock.HelloLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/hello")
@RestController
public class HelloController {
    @Autowired
    private HelloLock helloLock;
    @GetMapping(value="/{name}")
    public Resp.DataResp<String> hello(@PathVariable("name") String name) {
        for (int i=0;i<10;i++){
            int num=i;
            new Thread(){
                public void run(){
                    System.out.printf("hello start to lock:%d\n",num);
                    helloLock.lock("hello");
                    System.out.printf("hello locking:%d\n",num);
                    try {
                        Thread.sleep(2000);
                    }catch (Exception e){
                        System.out.println(e);
                    }finally {
                        helloLock.unlock("hello");
                        System.out.printf("hello unlock:%d\n",num);
                    }
                }
            }.start();
        }
        return new Resp.DataResp<>(200,"OK","hello "+name);
    }
}
