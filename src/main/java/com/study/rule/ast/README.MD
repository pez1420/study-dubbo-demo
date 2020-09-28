
#### 利用ANTLR4实现一个简单的四则运算计算器
https://www.cnblogs.com/sld666666/p/6145854.html

####  Antlr4简易快速入门
https://zhuanlan.zhihu.com/p/114982293

####  复杂的动态布尔表达式性能评估(1)--Antlr4实现
https://www.cnblogs.com/mumuxinfei/p/8474375.html


#### https://zhuanlan.zhihu.com/p/114982293
what?
Antlr (ANother Tool for Language Recognition) 
Antlr (ANother Tool for Language Recognition) 是一个强大的跨语言语法解析器，可以用来读取、处理、执行或翻译结构化文本或二进制文件。
它被广泛用来构建语言，工具和框架。Antlr可以从语法上来生成一个可以构建和遍历解析树的解析器。

常见的词法分析工具：
+ Antlr
+ Javacc
+ SqlParser (位于Alibaba的Druid库中)

其中Antlr和Javacc都是现代的语法解析器，两者都很优秀，其中Antlr要更胜一筹。而SqlParser只能解析sql语句，功能比较单一

抽象语法树 (Abstract Syntax Tree,AST) 抽象语法树是源代码结构的一种抽象表示，它以树的形状表示语言的语法结构。抽象语法树一般可以用来进行代码语法的检查，代码风格的检查，代码的格式化，代码的高亮，代码的错误提示以及代码的自动补全等等。
语法解析器 (Parser) 语法解析器通常作为编译器或解释器出现。它的作用是进行语法检查，并构建由输入单词(Token)组成的数据结构(即抽象语法树)。语法解析器通常使用词法分析器(Lexer)从输入字符流中分离出一个个的单词(Token)，并将单词(Token)流作为其输入。实际开发中，语法解析器可以手工编写，也可以使用工具自动生成。
词法分析器 (Lexer) 词法分析是指在计算机科学中，将字符序列转换为单词(Token)的过程。执行词法分析的程序便称为词法分析器。词法分析器(Lexer)一般是用来供语法解析器(Parser)调用的。

