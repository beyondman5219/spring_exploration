四天课程：
IOC：DI,DL AOP
第一天：讲IOC的原理，基本入门的应用
第二天：讲IOC基于XML和注解的使用案例，新注解
第三天：AOP
第四天：AOP应用：声明式事物处理
框架：有一组可重用的组件按一定的规则组装而成的半成品

一，Spring简介
1.1 spring概述 
spring  是一个优秀的轻量级一站式框架.
EJB:重量级框架，JAVAEE 
1.2 spring发展历程 
2002推出第一个版本，
2017推spring5.0
1.3 spring的优势 
第一个口号：“不重新发明轮子”
第二个口号：“使现有的技术更加易用”
解藕：IOC AOP


1.4 spring的体系结构 

Rod Josn  :计算机专家，音乐学博士    金隔系统设计上，C++，C


二，Spring IOC分析
2.1 编写jdbc的工程代码用于分析程序的耦合 
2.2 编译期依赖 
耦合：依赖   
解藕：提升程序的灵活性和可扩展性.
编译期依赖：
运行期依赖：
测试期依赖：

2.3 程序的耦合和解耦的思路分析1
2.4 曾经代码中的问题分析 
2.5 编写工厂类和配置文件 
思路：
  1,创建一个配置文件：将要创建的类配置在配置文件里
  2，解析配置文件:拿到类的全限定类名，通过反射创建类的对象
//工厂设计 模式：批量生产JavaBean 对象
BeanFactory{
//实例化对象
props = new Properties();
//获取properties文件的流对象
InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
props.load(in);
//根据key获取value
String beanPath = props.getProperty(“userDao”);
Class.forName(beanPath).newInstance();


}

2.6 工厂模式解耦 
2.7 分析工厂模式中的问题并改造 

原来写的工厂存在的问题：
    public static Object getBean(String beanName){
            Object bean = null;
            try {
                String beanPath = props.getProperty(beanName);
    //            System.out.println(beanPath);
                bean = Class.forName(beanPath).newInstance();//每次都会调用默认构造函数创建对象
            }catch (Exception e){
                e.printStackTrace();
            }
            return bean;
        }
每调用一次，都会通过反射创建对象，消耗资源 ，产生的对象是多例的.
更改后的目的：将产生的对象改成单例的。
单例的对象存在多线程 问题，一般我们不建在单例中定义可改变的成员变量.

2.8 工厂模式解耦的升级版
以上案例所采用的单例模式属性饿汉式单例。单例的对象存在多线程 问题，一般我们不建在单例中定义可改变的成员变量.

所存在问题：将原来由具体的实体类的依赖转换成了对BeanFactory





三，Spring 原理分析及spring核心概念 
IOC  AOP
3.1 ioc的概念和作用 
IOC:inverse of controller  控制反转
原来是硬编码的方式创建一个所依赖的对象
private IAccountDao accountDao = new AccountDaoImpl2();
所依赖的对象交给工厂来创建
private IAccountDao accountDao = (IAccountDao)BeanFactory.getBean("accountDao");
而spring框架对BeanFactory进行了封装和升级

IOC: 将原来在程序中通过硬编码的方式来创建所依赖的对象的权限，交给spring容器来创建。
反转：  对象的创建权限 。

3.2 spring中的Ioc前期准备 
IOC:  
DI依赖注入   依赖  注入  
依赖：A类依赖于B类完成某个功能，称A对B有依赖关系
注入：将A所依赖的B类的对象动态注入给A，我们称为注入
被动注入
DL:依赖查找    自动装配
        查找：A会在Spring容器里根据Bean的名字或类型进行自动查找。主动注入

3.3 spring基于XML的IOC环境搭建和入门 
   步骤：
     1，导入所依赖的JAR包   核心容器JAR包
     2，创建一个XML文件，将所要创建的BEAN配置在XML文件里   ApplicationContex.xml
3，根据配置的ID来得到对应的JAVABEAN的实例
3.1约束文件介绍
所引入的约束文件：
xmlns:xsi=http://www.w3.org/2001/XMLSchema-instance引用这个名称空间的目的是为了使用

xsi:schemaLocation="http://www.springframework.org/schema/beans
 http://www.springframework.org/schema/beans/spring-beans.xsd"

以下这个引入是beans标签的名称空间
xmlns="http://www.springframework.org/schema/beans"

3.2 标签介绍
标签介绍：
beans:根标签
<bean id="accountService" class="com.itheima.service.impl.AccountServiceImpl"></bean>
id:代表一个类的标识，spring创建的对象名
class:代表的类的全限定类名
3.3 代码分析
代码分析：
ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
这段代加载了spring的配置文件bean.xml，并且将配置文件里所有的bean进行实例化。返回一个容器.容器里包含所有的实例化对象。
ac.getBean(“id”);得到一个具体单例的对象.


IOC： 控制反转，用来创建bean的对象
       解耦



案例中的IOC: 主要解Bean实例化问题
将原来在代码中创建对象的功能交给Spring框架来创建.
3.4 ApplicationContext的三个实现类 
ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
1，加载bean.xml
2, 解析XML文件
3，获得配置文件里所有 bean的全路径名
4，通过反射将它们全部实例化放在一Map集合里
ApplicationContext四个实现类：(了解)
1,ClassPathXmlApplicationConext  加载类路径的配置文件
2，FileSystemXmlApplicationContext 加载物理路径下的配置文件
3，AnnoationXmlApplicationContext  注解的时候使用的
4，WebApplicationContext 在WEB应用中加载配置文件
3.5 BeanFactory和ApplicationContext的区别 (了解)
ApplicationConext是BeanFactory的子接口
区别：ApplicationConext加载配置的时候实例化所有的类
     BeanFactory 调用Bean的时候才实例化
是不是单例的由scope=”值”来确定
3.6 spring中bean的细节之三种创建Bean对象的方式 
Spring的IOC创建Bean对象提供了三种方式：
  1，调用默认的构造方法  
  2，采用工厂方法来创建类的对象
       A，创建一个工厂类，有一个方法返回所需要的类的对象
    B，<bean>: factory-bean="instanceFactory" 指定的工厂类实例
factory-method="getAccountService"：用来获得所需Bean的工厂方法

     c,静态工厂实例：
   <!-- 第三种方式：使用工厂中的静态方法创建对象（使用某个类中的静态方法创建对象，并存入spring容器)-->
<!--<bean id="accountService" class="com.itheima.factory.StaticFactory" factory-method="getAccountService"></bean>-->


3.7 spring中bean的细节之作用范围 
     scope:
singleton:单例  默认的
                prototype：多例的 
                request: web应用程序中将bean对象保存在request域内
                session：web应用程序中将bean对象保存在session域内
               global-session：基于集群应用
3.8 spring中bean的细节之生命周期 
servlet 生命周期：  创建对象---init---service----destory(释放资源，销毁对象)
Spring中的bean生命周期：
 1,创建对象：调用默认的构造方法
2，初始化：init-method="init"
3，服务 方法调用：除了构造方法和初始化方法外的其它方法
4，销毁：destroy-method="destroy"
注意：单例和多例的销毁方式不同，单例的关闭spring容器时执行销毁;多例交由JVM的垃圾回收机制进行回收销毁。


四，依赖注入
4.1 spring的依赖注入 
DI:dependency injection

比如业务类需要调用DAO类来处理数据，业务类所依赖的对象由spring来进行创建并将其动态注入给业务类.
注入的方式：一个构造方法注入，一个setter方法注入


4.2 构造函数注入 
4.3 构造函数注入 
  先要在bean里创建类的相应的构造方法，然后在spring配置文件里进行配置:
<bean>
    <constractor-arg name=”参数名” value=”值“>
</bean>
4.4 set方法注入 
   依赖的属性必须有setter 方法，然后在spring配置文件里进行配置：
   <bean>
        <property name=”属性” ref=”引用的bean实例”></property>
   </bean>
4.5 注入集合数据 （了解）


A，给数组赋值：
      <array>
          <value>值</value>
       </array>
或
      <set>
          <value>值</value>
       </set>
B,给List集合赋值
<list>
    <value>值</value>
</list>
或
    <array>
       <value>AAA</value>
       <value>BBB</value>
       <value>CCC</value>
    </array>
C,Map集合
<map>
    <entry key=”testA” value=”aaa”></entry>
    <entry key=”testB”>
        <value>BBB</value>
     </entry>
</map>
或
<props>
    <prop key="testC">ccc</prop>
    <prop key="testD">ddd</prop>
</props>
D，Properties
<props>
    <prop key="testC">ccc</prop>
    <prop key="testD">ddd</prop>
</props>
或
<map>
    <entry key="testA" value="aaa"></entry>
    <entry key="testB">
        <value>BBB</value>
    </entry>
</map>


总结：
1，IOC：控制反转   将 bean的创建由原来的代码层次转换为由spring容器创建
     DI:依赖注入
     DL:依赖查找   自动装配
2，IOC案例
spring 如果想管理bean之间的依赖关系：所有的bean必须由spring来进行创建。
bean与bean之间依赖于接口，注入实现.
   A，导包
   B,创建配置文件
C,声明相关的类和接口
D，在配置文件里配置Bean
    bean:id和name区别 ，name支持一些特殊符号

案例中的依赖：controller---service---dao
注入:dao---service---controller

自动装配方式：
    按名字：byName
    按类型：byType   如果有多个符合条件的bean,就发生冲突
<!--配置SERVICE-->
<bean id="userService" class="cn.itcast.service.impl.UserServiceImpl" scope="singleton">
    <property name="userDao" ref="userDao"></property>
</bean>
<!--配置controller-->
<bean id="userController" class="cn.itcast.client.MyController" scope="singleton" autowire="byType" >

</bean>

