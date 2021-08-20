package com.potato369.find.user.utils;

import com.potato369.find.common.utils.Decode;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

/**
 * @author ming
 * 构建SqlSessionFactory
 * 由于数据库连接是宝贵的,需要对数据库连接统一管理,所以使用单例进行管理
 * 这里的单利使用的双重锁
 * SqlSessionFactory为线程不安全类型需要加锁,确保同一时刻,只有一个线程可以使用该对象
 */
public class SqlSessionFactoryUtil {

    /**
     * 类线程锁
     */
    private static final Class<SqlSessionFactoryUtil> CLASS_LOCK = SqlSessionFactoryUtil.class;
    /**
     * 日志管理类
     */
    private static final Logger logger = LogManager.getLogger();
    /**
     * SqlSessionFactory对象
     */
    private static SqlSessionFactory sqlSessionFactory = null;

    /**
     * 单例
     */
    private SqlSessionFactoryUtil() {

    }

    /**
     * @return SqlSessionFactory
     * 初始化SqlSessionFactory对象
     */
    public static SqlSessionFactory initSqlSessionFactory() {
        // 获得输入流
        InputStream cfgStream = null;
        // 阅读流
        Reader cfgReader = null;
        InputStream proStream = null;
        Reader proReader = null;
        // 持久化属性集
        Properties properties = null;
        try {
            // 配置文件流
            cfgStream = Resources.getResourceAsStream("mybatis-config.xml");
            // 获得阅读流
            cfgReader = new InputStreamReader(cfgStream);
            // 读入属性文件
            proStream = Resources.getResourceAsStream("db.properties");
            proReader = new InputStreamReader(proStream);
            // 持久化属性集
            properties = new Properties();
            // 流装载进入属性集合
            properties.load(proReader);
            // 获取当前系统ENV
            String key = System.getenv("KEYWORDES");
            // 进行解密
            properties.setProperty("password", Decode.decryptDecode(properties.getProperty("password"), key));
        } catch (Exception e) {
            logger.error(e);
        }

        if (sqlSessionFactory == null) {
            synchronized (CLASS_LOCK) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(cfgReader, properties);
            }
        }
        return sqlSessionFactory;
    }

    /**
     * 打开SqlSession
     *
     * @return SqlSession
     */
    public static SqlSession openSqlSesion() {
        // 判空处理
        if (sqlSessionFactory == null) {
            initSqlSessionFactory();
        }
        return sqlSessionFactory.openSession();
    }
}
