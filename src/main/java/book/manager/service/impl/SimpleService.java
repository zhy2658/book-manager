package book.manager.service.impl;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SimpleService {

//    @PreAuthorize("hasRole('admin')")
    @PreFilter("filterObject.equals('zhy')")  //过滤
    public void test(List<String> list){
        System.out.println(list);
    }
}
