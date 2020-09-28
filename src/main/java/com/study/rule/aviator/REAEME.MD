#### 什么是Aviator

Aviator是一个高性能、轻量级的java语言实现的表达式求值引擎。
Aviator的设计目标是轻量级和高性能 ，相比于Groovy、JRuby的笨重，Aviator非常小，加上依赖包也才450K,不算依赖包的话只有70K；
当然，Aviator的语法是受限的，它不是一门完整的语言，而只是语言的一小部分集合。

Aviator的实现思路与其他轻量级的求值器很不相同，其他求值器一般都是通过解释的方式运行，而Aviator则是直接将表达式编译成Java字节码，交给JVM去执行。
Aviator的定位是介于Groovy这样的重量级脚本语言和IKExpression这样的轻量级表达式引擎之间。

https://developer.aliyun.com/article/608829
