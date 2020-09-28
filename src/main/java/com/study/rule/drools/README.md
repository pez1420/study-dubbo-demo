
### Drools 简介
Drools官网在2020-07-09发布了最新版本：Drools 7.40.0.Final.

#### 1.2 drl文件语法

##### 1.2.1 drl实现远离

Drools使用了自己的drl语法，是一种DSL通过ASM实现了它自己的Parser(ConsequenceGenerator)，在启动时将读取META-INF/kmodule.xml中的配置，并将drl反序列化为RuleImpl与JVM字节码。


优点：
策略规则和执行逻辑解耦方便维护。

缺点：
+ DRL语法奇特，过于接近AST，不亚于又学了一门新语言
+ 生成代码过程是黑盒，而且代码中不能打断点
+ 业务分析师无法独立完成规则配置：由于规则主体DSL是编程语言（支持Java, Groovy, Python），因此仍然需要开发工程师维护。
+ 规则规模变大以后也会变得不好维护，相对硬编码的优势便不复存在。
+ 规则的语法仅适合扁平的规则，对于嵌套条件语义（then里嵌套when...then子句）的规则只能将条件进行笛卡尔积组合以后进行配置，不利于维护。

https://miao1007.github.io/%E8%A7%84%E5%88%99%E5%BC%95%E6%93%8E%E7%9A%84%E4%BB%8B%E7%BB%8D%E4%B8%8Edrools%E7%9A%84%E6%B5%81%E7%A8%8B%E5%88%86%E6%9E%90/
https://blog.csdn.net/dongqing0812/article/details/82424241
https://blog.csdn.net/lei32323/article/details/74524489
https://tech.meituan.com/2017/06/09/maze-framework.html

#### 1.2.2 drl语法说明
规则Sample:

```
rule "name"
       no-loop true
       when
               $message:Message(status == 0)
       then
               System.out.println("fit");
               $message.setStatus(1);
               update($message);
end
```

+ package

对一个规则文件而言，package是必须定义的，必须放在规则文件第一行。特别的是，package的名字是随意的，不必必须对应物理路径，跟java的package的概念不同，这里只是逻辑上的一种区分。同样的package下定义的function和query等可以直接使用。

比如：package com.drools.demo.point

import：导入规则文件需要使用到的外部变量，这里的使用方法跟java相同，但是不同于java的是，这里的import导入的不仅仅可以是一个类，也可以是这个类中的某一个可访问的静态方法。

比如：

import com.drools.demo.point.PointDomain;

import com.drools.demo.point.PointDomain.getById;

+ rule

定义一个规则。rule "ruleName"。一个规则可以包含三个部分：
属性部分：定义当前规则执行的一些属性等，比如是否可被重复执行、过期时间、生效时间等。
条件部分：即LHS，定义当前规则的条件，如  when Message(); 判断当前workingMemory中是否存在Message对象。
结果部分：即RHS，这里可以写普通java代码，即当前规则条件满足后执行的操作，可以直接调用Fact对象的方法来操作应用。



+ no-loop 

定义当前的规则是否不允许多次循环执行，默认是false

+ salience

优先级，数值越大越先执行，这个可以控制规则的执行顺序

+ when

规则条件开始
规则的结果部分
当规则条件满足，则进入规则结果部分执行，结果部分可以是纯java代码，比如：

+ then 
```
then
       System.out.println("OK"); //会在控制台打印出ok
end
```



### Drools workbench

如果不希望业务人员在开发工具里写规则，那么Drools提供一个核心的Business Rules Engine(BRE) 和一个网页编写规则的管理系统（Drools Workbench）和一个Eclipse IDE 的插件，
共同构成Drools的生态。Drools workbench在6.4版本之后改名为Business center，开发人员可以在平台上对规则进行CURD，更高级的模式包括决策表、规则流等。
Drools Workbench组织业务逻辑，所有的规则都放在drools workbench中编写，代码中来整合数据，执行drools workbench中的规则.

#### 2.1 Drools Workbench 6.5.0 安装过程


+ 1、在tomcat的conf下新建文件btm-config.properties，并添加如下配置：

>bitronix.tm.serverId=tomcat-btm-node0
 bitronix.tm.journal.disk.logPart1Filename=/Users/luyb/soft/tomcat7/work/btm1.tlog
 bitronix.tm.journal.disk.logPart2Filename=/Users/luyb/soft/tomcat7/work/btm2.tlog
 bitronix.tm.resource.configuration=/Users/luyb/soft/tomcat7/conf/resources.properties

+ 2、在tomcat的conf文件夹下新建文件resources.properties，并添加如下配置：
>resource.ds1.className=bitronix.tm.resource.jdbc.lrc.LrcXADataSource
 resource.ds1.uniqueName=jdbc/jbpm
 resource.ds1.minPoolSize=10
 resource.ds1.maxPoolSize=20
 resource.ds1.driverProperties.driverClassName=com.mysql.jdbc.Driver
 resource.ds1.driverProperties.url=jdbc:mysql://localhost:3306/drools?useUnicode=true&characterEncoding=UTF-8
 resource.ds1.driverProperties.user=root
 resource.ds1.driverProperties.password=root
 resource.ds1.allowLocalTransactions=true

+ 3、修改tomcat 的conf文件夹下的context.xml，在其Context 标签中添加如下配置

> <Resource name="jdbc/jbpm" auth="Container" type="javax.sql.DataSource" driverClassName="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/drools?useUnicode=true&characterEncoding=UTF-8" username="root" password="root" maxActive="20" maxIdle="1" maxWait="5000"/>

+ 4、修改tomcat 的conf文件夹下的tomcat-users.xml，在其tomcat-users标签中添加如下配置

> <role rolename="admin"/>
  <role rolename="analyst"/>
  <user username="drools" password="drools" roles="admin,analyst"/>
  
+ 5、修改tomcat 的conf文件夹下的server.xml ，在其文件末端Host标签中添加如下配置
> <Valve className="org.kie.integration.tomcat.JACCValve" />

+ 6、在tomcat 的bin文件夹下新建文件setenv.sh，并添加如下配置：

> CATALINA_OPTS="-Xmx512M -XX:MaxPermSize=512m -Dbtm.root=/home/work/drools/tomcat/apache-tomcat-7 \
      -Dbitronix.tm.configuration=/home/work/drools/tomcat/apache-tomcat-7/conf/btm-config.properties \
      -Djbpm.tsr.jndi.lookup=java:comp/env/TransactionSynchronizationRegistry \
      -Djava.security.auth.login.config=/home/work/drools/tomcat/apache-tomcat-7/webapps/kie-drools-wb/WEB-INF/classes/login.config \
      -Dorg.jboss.logging.provider=jdk"

+ 7、修改配置文件persistence.xml
apache-tomcat-7/webapps/kie-drools-wb/WEB-INF/classes/META-INF，在此目录下找到persistence.xml文件，将

> <property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect"/>
>  改为（这里使用mysql数据库）
>  <property name="hibernate.dialect" value="org.hibernate.dialect.MySQL5Dialect"/>

+ 8、启动验证

> 启动tomcat 在浏览器中输入http://localhost:8080/kie-drools-wb/kie-drools-wb.jsp。在账号密码输入框中输入tomcat-users.xml文件中配置的帐号密码就可登录进入Drools Workbench
