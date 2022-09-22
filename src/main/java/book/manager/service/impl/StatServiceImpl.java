package book.manager.service.impl;

import book.manager.entity.GlobalStat;
import book.manager.mapper.BookMapper;
import book.manager.mapper.UserMapper;
import book.manager.service.StatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StatServiceImpl implements StatService {

    @Resource
    BookMapper bookMapper;

    @Resource
    UserMapper userMapper;

    @Override
    public GlobalStat getGlobalStat() {
        return new GlobalStat(userMapper.getUserCount(),
                                bookMapper.getBookCount(),
                                bookMapper.getBorrowCount());
    }
}
