script 反射获取接口 那么需要注册或者注解吗？ 4.30-5.30

@script 是反射获取 实体类 和 @serverce 一样的 。

`script_1_to_1.`get() → 本质是一个hashmap类 实现 。 那么如何找到 这个 类 ， 通过 反射 在 程序 开始 阶段 获取 所有被 @script 注解的实体bean.  将获取的实体bean 放入 hashmap中 ， 之后 ， 在在hashMap中找。

主要实现 ： 通过 注解 获取实体类 。 

设计：本质是一个hashmap类 实现 。 那么如何找到 这个 类 ， 通过 反射 在 程序 开始 阶段 获取 所有被 @script 注解的实体bean.  将获取的实体bean 放入 hashmap中 ， 之后 ， 在在hashMap中找。