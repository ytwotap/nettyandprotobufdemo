package logtest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * slf4j + log4j test
 * @author: 杨涛
 * @date: 2021/11/26/026
 */
@Slf4j
public class Slf4jTest {


    public static void main(String[] args) {
        log.info("test");
    }
}
