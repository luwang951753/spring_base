package com.atguigu.service;

import com.atguigu.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author ZerlindaLi create at 2020/5/22 10:21
 * @version 1.0.0
 * @description BookService
 */
@Service
public class BookService {
    @Autowired
    private BookDao bookDao;

    /**
     * 事务细节
     * isolation - Isolation : 事务的隔离级别
     * REPEAT-READ: 同一个事务期间：（快照读）第一次读到的是什么，以后就是什么, 即使外界的事务都没了。避免了所有问题
     * READ-COMMITTED: 多个事务，一个事务提交后的修改才能被READ-COMMITTED事务看到
     * READ-UNCOMMITTED：多个事务，一个事务为提交的修改，也能被READ-UNCOMMITTED事务看到
     *
     * propagation - Propagation : 事务的传播行为
     *
     * noRollbackFor - Class[] : 哪些异常事务可以不会滚; 可以让原来默认回滚的异常不回滚
     * noRollbackForClassName - String[] (String全类名):
     *
     * rollbackFor - Class[] : 哪些异常事务需要回滚：可以让原来默认不回滚（编译时异常是不回滚的）的异常回滚
     * rollbackForClassName - Class[] :
     *
     * 异常分类：
     *       运行时异常（非检查异常）：可以不用处理,发生运行时异常时，默认都回滚
     *
     *       编译时异常（检查异常）：要么try-catch, 要么在方法上声明throws，
     *                  发生时默认不回滚
     * 事务的回滚：默认发生运行时异常都回滚，而发生编译时异常不会回滚
     *
     *
     * readOnly - boolean : 设置事务为只读事务，只有查询是允许的。
     * timeout - int (秒为单位): 超时：事务超出指定执行时长后，自动终止并回滚
     */
    @Transactional(timeout = 3, readOnly = false, noRollbackFor = {ArithmeticException.class, NullPointerException.class})
    public void checkOut(String username, String isbn) throws FileNotFoundException {
        Integer price = bookDao.getPrice(isbn);
        // 减库存
        bookDao.updateStock(isbn);
        // 减余额
        bookDao.updateBalance(username, price);
        int i = 10 / 0;
    }

    /**
     * 根据业务特性，进行调整
     * @param isbn
     * @return
     */
    @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
    public int getPrice(String isbn){
        return bookDao.getPrice(isbn);

    }
}
