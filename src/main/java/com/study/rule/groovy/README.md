

#### 如何动态地执行代码

https://wsztrush.github.io/%E7%BC%96%E7%A8%8B%E6%8A%80%E6%9C%AF/2015/07/21/Dynamic-Compilation.html


#### 用groovy实现根据规则校验单据数据

https://www.kankanzhijian.com/2019/02/20/groovy_validating_form_on%20dynamic_rules/


#### groovy运行沙盒

groovy sandbox的实现 -> https://github.com/jenkinsci/groovy-sandbox

沙盒原理也叫沙箱，英文sandbox。在计算机领域指一种虚拟技术，且多用于计算机安全技术。安全软件可以先让它在沙盒中运行，如果含有恶意行为，则禁止程序的进一步运行，而这不会对系统造成任何危害。

举个例子:
docker容器可以理解为在沙盒中运行的进程。这个沙盒包含了该进程运行所必须的资源。不同的容器之间相互隔离。CGroup实现资源控制, Namespace实现访问隔离, rootfs实现文件系统隔离。


对于嵌入Groovy的Java系统, 如果暴露接口, 可能存在的隐患有：
+ 通过Java的Runtime.getRuntime().exec()方法执行shell, 操作服务器…
+ 执行System.exit(0)
+ dump 内存中的Class, 修改内存中的缓存数据


https://blog.csdn.net/jiangqian6481/article/details/83717442?utm_medium=distribute.pc_relevant.none-task-blog-baidujs-1&spm=1001.2101.3001.4242#groovyscriptengine

Spring对Groovy以及动态语言的支持
https://blog.csdn.net/jiangqian6481/article/details/83717442?utm_medium=distribute.pc_relevant.none-task-blog-baidujs-1&spm=1001.2101.3001.4242#spring%E5%AF%B9groovy%E4%BB%A5%E5%8F%8A%E5%8A%A8%E6%80%81%E8%AF%AD%E8%A8%80%E7%9A%84%E6%94%AF%E6%8C%81