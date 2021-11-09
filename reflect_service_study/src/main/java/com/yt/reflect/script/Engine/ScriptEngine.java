package com.yt.reflect.script.Engine;

import com.yt.reflect.script.annotion.Iscript;
import com.yt.reflect.script.service.Anime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实现 @service 根据 接口 发现实现类 。
 */
public class ScriptEngine {
    //存放实体类
    private static Map<Class<?>,Iscript> implementClass_1_to_n = new HashMap();
    //默认调用代码块
    {
        addToHashMap();
    }

    //根据相应的class 返回相应的实现该接口的类

    /**
     *  返回相应的实体类
     * @param animeClass
     * @param <T>
     * @return
     */
    public static <T extends Iscript> T getOne(Class<T> clazz) {
        //从hashmap 中拿东西
        T ret = (T)implementClass_1_to_n.get(clazz);
        //没有拿到
        if (ret != null) {
            return ret;
        } else {

            //拿到了

            List<Iscript> list = (List)script_1_to_n.get(clazz);

            if (list != null && !list.isEmpty()) {

                System.out.println("1对N类型的脚本被当做1对1类型来使用，脚本接口："+ clazz.getName());

                return (Iscript)list.get(0);

            } else {

                return null;

            }
        }
        return null;
    }


    /**
     * 通过 获取全部实现接口 ， 将他 加入 到 hashMap中;
     */
    public void addToHashMap(){
        //反射扫描当前包下的 类 注解

        //将clasS加入 到 hashMap中


        //返回

    }
}
