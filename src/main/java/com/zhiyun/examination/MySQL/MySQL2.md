## 常见操作

### 1.mysql如何创建一个库，添加一个表，在表中插入数据。

```mysql
drop table student;
create table student
(
id int primary key,
name nvarchar2(50) not null,
score number not null
);
insert into student values(1,'Aaron',78);

```

57 表空间的创建、迁移

### 56 索引的创建

```mysql
（index) create index index_table(id);
```

### 133 会写存储过程吗 如何利用存储过程提高数据库读取的性能。

```
　存储过程是一组为了完成特定功能的SQL语句集，经编译后存储在数据库中。用户通过指定存储过程的名字并给出参数(如果该存储过程带有参数)来执行它。存储过程可由应用程序通过一个调用来执行，而且允许用户声明变量 。同时，存储过程可以接收和输出参数、返回执行存储过程的状态值，也可以嵌套调用。

　　存储过程的优点

　　作为存储过程，有以下这些优点：

　　(1) 减少网络通信量。调用一个行数不多的存储过程与直接调用SQL语句的网络通信量可能不会有很大的差别，可是如果存储过程包含上百行SQL语句，那么其性能绝对比一条一条的调用SQL语句要高得多。

　　(2) 执行速度更快。存储过程创建的时候，数据库已经对其进行了一次解析和优化。其次，存储过程一旦执行，在内存中就会保留一份这个存储过程，这样下次再执行同样的存储过程时，可以从内存中直接中读取。

　　(3) 更强的安全性。存储过程是通过向用户授予权限(而不是基于表)，它们可以提供对特定数据的访问，提高代码安全，比如防止 SQL注入。

　　(4) 业务逻辑可以封装存储过程中，这样不仅容易维护，而且执行效率也高

　　当然存储过程也有一些缺点，比如：

　　(1) 可移植性方面：当从一种数据库迁移到另外一种数据库时，不少的存储过程的编写要进行部分修改。

　　(2) 存储过程需要花费一定的学习时间去学习，比如学习其语法等。
```



### 156 给mysql创建个用户，并对表test有访问权限

```mysql
#远程登录mysql
mysql -h ip -u root -p 密码
#创建用户格式：grant 权限 on 数据库.* to 用户名@登录主机 identified by “密码”;
#例1：增加一个test1用户，密码为123456，可以在任何主机上登录，并对所有数据库有查询，增加，修改和删除的功能。#需要在mysql的root用户下进行
grant select,insert,update,delete on . to test1@”%” identified by “123456″；
flush privileges;
#增加test2用户密码为123456只能192.168.2.12上登录，并对student库有增删改查功能需要在mysql的root用户下进行
grant select,insert,update,delete on student.* to test2@192.168.2.12 identified by “123456″;
flush privileges;
#例3：授权用户test3拥有数据库student的所有权限
grant all privileges on student.* to test3@localhost identified by ’123456′;
flush privileges;
#3.修改用户密码
update mysql.user set password=password(’123456′) where User=’test1′ and Host=’localhost’;
flush privileges;
#4.删除用户
delete from user where user=’test2′ and host=’localhost’;
flush privileges;
#6.删除账户及权限
drop user 用户名@’%’
drop user 用户名@localhost
```

### 159 如何在mysql某个表中随机抽取10条记录

```mysql
#数据量小于1000行的时候，上面的 sql 执行的快
SELECT * FROM tb_brand ORDER BY RAND() LIMIT 10;
#法2
SELECT * FROM `tb_brand`
WHERE id >= (SELECT floor(RAND() * (SELECT MAX(id) FROM `tb_brand`)))
ORDER BY id LIMIT 10;
#法3
SELECT
	* 
FROM
	`tb_brand` AS t1
	JOIN (
SELECT
	ROUND(
	RAND( ) * ( ( SELECT MAX( id ) FROM `tb_brand` ) - ( SELECT MIN( id ) FROM `tb_brand` ) ) + ( SELECT MIN( id ) FROM `tb_brand` ) 
	) AS id 
	) AS t2 
WHERE
	t1.id >= t2.id 
ORDER BY
	t1.id 
	LIMIT 10;
```

### 160 如何查看连接mysql的当前用户

```mysql
SELECT user();
select database();
show full processlist，在user字段中查看有哪些用户
```

### 162 写出mysql怎么修改密码？怎么修复损坏的表？

```mysql
有两种方法，一种方法使用mysql的check table和repair table 的sql语句，另一种方法是使用MySQL提供的多个myisamchk, isamchk数据检测恢复工具
```

19 “增删改查 你觉得那个最占用资源

### 67 查看是否安装了mysql

```mysql
    mysql -v
```

### 68 启动mysql，进入mysql 

```mysql
     mysqld -u root -p
```

### 150 mysql用户test 只能由abc.com访问test表且test只能访问test密码testpasswd

## MySQL优化

124 innoDB和myisam有什么区别

```mysql
(1)、问5点不同；

1>.InnoDB支持事物，而MyISAM不支持事物

2>.InnoDB支持行级锁，而MyISAM支持表级锁

3>.InnoDB支持MVCC, 而MyISAM不支持

4>.InnoDB支持外键，而MyISAM不支持

5>.InnoDB不支持全文索引，而MyISAM支持。

(2)、innodb引擎的4大特性

插入缓冲（insert buffer),二次写(double write),自适应哈希索引(ahi),预读(read ahead)

(3)、2者selectcount(*)哪个更快，为什么

myisam更快，因为myisam内部维护了一个计数器，可以直接调取。
```

### 59 选择不同的存储引擎，对数据库的工作有何影响89 mysql 存储方式？

```mysql
Mysql中的数据都是用各种不同的技术存储在文件（或内存中的）。这些技术中的每一个技术会使用不同的存储机制、索引技巧等。我们应该根据应用的不同需求，来选择合适的存储引擎，从而改善应用的整体功能。

例如，在研究大量的临时数据，你也许需要使用内存存储引擎，内存存储引擎能够在内存中存储所有的表格数据。
或者，在并发应用中，你需要一个支持事务处理的数据库，以保证事务处理不成功时数据的回退能力.
这些不用的技术及配套的相关功能在Mysql中被称作存储引擎（也叫作表类型）
2）指定存储引擎的方法

可以使用很多方法指定一个要使用的存储引擎。最简单的方法是，如果需要一种能满足大多数数据库需求的存储引擎，可以在MySQL设置文件中设置一个默认的引擎类型（使用storage_engine选项），或者在启动数据库服务器时在命令行后面加上--default-storage-engine 或--default-table-type。

最直接的方式就是在创建表时指定存储引擎的类型，在create table 语句最后使用engine属性指定。

例如

CREATE TABLE mytable(id int,title char(20))ENGINE=INNODB
也可以使用alter table命令来更改现有表的存储引擎，例如：

ALTER TABLE mytable ENGINE=MYISAM
如果不指定的话，MySQL默认的存储引擎是MYISAM。
（3）常用存储引擎的优缺点

①ISAM

ISAM在设计之时就考虑到数据库被查询的次数要远大于更新的次数。因此，ISAM的执行读取操作的速度很快，而且不占用大量的内存和存储资源。ISAM的不足在于，它不支持事务处理，也不能容错，如果你的硬盘崩溃了，那么数据文件就无法恢复了。

②MyISAM

MyISAM是MySQL的ISAM扩展格式和默认的数据库引擎。相比于ISAM，MyISAM拥有更好的索引压缩，更好的键码统计分布。 除了提供了ISAM里所没有的索引和字段管理的大量功能，MyISAM还使用一种表格锁定的机制，来优化多个并发的读写操作。MyISAM强调了快速读写操作。

③MEMORY

MEMORY只允许驻留在内存里的临时表格中。驻留在内存里的MEMORY要比ISAM和MyISAM都快，但是它所管理的数据都是不稳定的，而且如果在关机之前没有保存，那么所有的数据都会丢失。

④InnoDB和BDB

InnoDB和BDB数据库都支持对事物处理和外来见的支持，但这两者的都比ISAM和MyISAM引擎慢很多。

（4）如何选择引擎

ISAM:执行读取操作的速度很快，而且不占用大量的内存和存储资源，但不支持事物处理，也不能容错

MyISAM：支持索引，能够实现并发的快速读取操作。

MEMORY: 只有内存存储，操作最快，但管理的数据是不稳定的.

InnoDB：支持事务和外来键，速度相对较慢。

（5）MyISAM和InnoDB对比
MyISAM类型不支持事物处理等高级处理，而InnoDB类型支持。MyISAM类型的表强调的性能，其执行速度比InnoDB类型更快，但是不提供事务支持，而InnoDB提高事务支持以及外部键等高级数据库功能。
另外，两者都可用来存储表和索引，InnoDB的索引和表存储在同一文件中，MyISAM的索引和表存储在不同文件中。InnoDB占用磁盘空间比MyISAM大，对空闲存储空间的使用不优。因此，MyISAM可大量节省磁盘空间，特别是对索引的存储上，优势巨大。
```



### 126 在mysql服务器运行缓慢的情况下输入什么命令能缓解服务器压力

```mysql
第一步 检查系统的状态**
　　　　通过操作系统的一些工具检查系统的状态，比如CPU、内存、交换、磁盘的利用率，根据经验或与系统正常时的状态相比对，有时系统表面上看起来看空闲，这也可能不是一个正常的状态，因为cpu可能正等待IO的完成。除此之外，还应观注那些占用系统资源(cpu、内存)的进程。
　　　　1.1　使用sar来检查操作系统是否存在IO问题
　　　　1.2　使用vmstat监控内存 cpu资源
　　　　1.3　磁盘IO问题，处理方式：做raid10提高性能
　　　　1.4　网络问题，telnet一下MySQL对外开放的端口，如果不通的话，看看防火墙是否正确设置了。另外，看看MySQL是不是开启了skip-networking的选项，如果开启请关闭。
　　**第二步 检查mysql参数**
　　　　2.1　max_connect_errors
　　　　2.2　connect_timeout
　　　　2.3　skip-name-resolve
　　　　2.4　slave-net-timeout=seconds
　　　　2.5　master-connect-retry
　　**第三步 检查mysql 相关状态值**
　　　　3.1　关注连接数
　　　　3.2　关注下系统锁情况
　　　　3.3　关注慢查询（slow query）日志
```

### 50 数据库死锁概念

```mysql
多数情况下，可以认为如果一个资源被锁定，它总会在以后某个时间被释放。而死锁发生在当多个进程访问同一数据库时，其中每个进程拥有的锁都是其他进程所需的，由此造成每个进程都无法继续下去。简单的说，进程A等待进程B释放他的资源，B又等待A释放他的资源，这样就互相等待就形成死锁。
　　虽然进程在运行过程中，可能发生死锁，但死锁的发生也必须具备一定的条件，死锁的发生必须具备以下四个必要条件。
　　1）互斥条件：指进程对所分配到的资源进行排它性使用，即在一段时间内某资源只由一个进程占用。如果此时还有其它进程请求资源，则请求者只能等待，直至占有资源的进程用毕释放。
　　2）请求和保持条件：指进程已经保持至少一个资源，但又提出了新的资源请求，而该资源已被其它进程占有，此时请求进程阻塞，但又对自己已获得的其它资源保持不放。
　　3）不剥夺条件：指进程已获得的资源，在未使用完之前，不能被剥夺，只能在使用完时由自己释放。
　　4）环路等待条件：指在发生死锁时，必然存在一个进程——资源的环形链，即进程集合{P0，P1，P2，???，Pn}中的P0正在等待一个P1占用的资源；P1正在等待P2占用的资源，……，Pn正在等待已被P0占用的资源。
　　下列方法有助于最大限度地降低死锁：
　　（1）按同一顺序访问对象。
　　（2）避免事务中的用户交互。
　　（3）保持事务简短并在一个批处理中。
　　（4）使用低隔离级别。
　　（5）使用绑定连接。
```

118 mysql 四种操作操作是什么

30 b树索引对更新数据有什么影响

```
　**B-TREE索引**(默认的索引类型)加速了数据访问，因为存储引擎不会扫描整个表得到需要的数据。相反，它从根节点开始。根节点保存了指向子节点的指针，并且存储引擎会根据指针寻找数据。它通过查找节点页中的值找到正确的指针，节点页包含子节点的指针，并且存储引擎会根据指针寻找数据。它通过查找节点页中的值找到正确的指针，节点页包含子节点中值的上界和下界。最后，存储引擎可能无法找到需要的数据，也可能成功地找到包含数据的叶子页面。
　　例：B-TREE索引 对于以下类型查询有用。匹配全名、匹配最左前缀、匹配列前缀、匹配范围值、精确匹配一部分并且匹配某个范围中的另一部分；
　　**B-TREE索引的局限**：如果查找没有从索引列的最左边开始，它就没什么用处。不能跳过索引中的列，存储引擎不能优先访问任何在第一个范围条件右边的列。例：如果查询是where last_name=’Smith’ AND first_name LIKE ‘J%’ AND dob=’1976-12-23’;访问就只能使用索引的头两列，因为LIKE是范围条件。
```

26 MySQL索引你会么？平时怎么用的？你是每个表都加上索引么？你怎么确定你加上索引后速度会快？

25 MySQL语句调优会不会？用的什么工具？

### 7 mysql的配置文件位置

```mysql
  1、Windows下MySQL的配置文件是my.ini，一般会在安装目录的根目录。
   2、Linux下MySQL的配置文件是my.cnf，一般会放在/etc/my.cnf，/etc/mysql/my.cnf。如果找不到，可以用find命令查找。
   3、Linux用rpm包安装的MySQL是不会安装/etc/my.cnf文件的
```



### 1 mysql你都修改了那些配置文件来进行优化(问配置文件中具体修改的内容)?

```
　　innodb_buffer_pool_size:这是你安装完InnoDB后第一个应该设置的选项。缓冲池是数据和索引缓存的地方：这个值越大越好，这能保证你在大多数的读取操作时使用的是内存而不是硬盘。典型的值是5-6GB(8GB内存)，20-25GB(32GB内存)，100-120GB(128GB内存)。
　　innodb_log_file_size：这是redo日志的大小。redo日志被用于确保写操作快速而可靠并且在崩溃时恢复。一直到MySQL 5.1，它都难于调整，因为一方面你想让它更大来提高性能，另一方面你想让它更小来使得崩溃后更快恢复。幸运的是从MySQL 5.5之后，崩溃恢复的性能的到了很大提升，这样你就可以同时拥有较高的写入性能和崩溃恢复性能了。一直到MySQL 5.5，redo日志的总尺寸被限定在4GB(默认可以有2个log文件)。这在MySQL 5.6里被提高。
　　一开始就把innodb_log_file_size设置成512M(这样有1GB的redo日志)会使你有充裕的写操作空间。如果你知道你的应用程序需要频繁的写入数据并且你使用的时MySQL 5.6，你可以一开始就把它这是成4G。max_connections:如果你经常看到‘Too many connections’错误，是因为max_connections的值太低了。这非常常见因为应用程序没有正确的关闭数据库连接，你需要比默认的151连接数更大的值。max_connection值被设高了(例如1000或更高)之后一个主要缺陷是当服务器运行1000个或更高的活动事务时会变的没有响应。在应用程序里使用连接池或者在MySQL里使用进程池有助于解决这一问题。
　　InnoDB配置
　　从MySQL 5.5版本开始，InnoDB就是默认的存储引擎并且它比任何其他存储引擎的使用都要多得多。那也是为什么它需要小心配置的原因。
　　innodb_file_per_table：这项设置告知InnoDB是否需要将所有表的数据和索引存放在共享表空间里 （innodb_file_per_table = OFF）或者为每张表的数据单独放在一个.ibd文件（innodb_file_per_table = ON）。每张表一个文件允许你在drop、truncate或者rebuild表时回收磁盘空间。这对于一些高级特性也是有必要的，比如数据压缩。但是它不会带来任何性能收益。你不想让每张表一个文件的主要场景是：有非常多的表（比如10k+）。
　　MySQL 5.6中，这个属性默认值是ON，因此大部分情况下你什么都不需要做。对于之前的版本你必需在加载数据之前将这个属性设置为ON，因为它只对新创建的表有影响。
　　innodb_flush_log_at_trx_commit：默认值为1，表示InnoDB完全支持ACID特性。当你的主要关注点是数据安全的时候这个值是最合适的，比如在一个主节点上。但是对于磁盘（读写）速度较慢的系统，它会带来很巨大的开销，因为每次将改变flush到redo日志都需要额外的fsyncs。将它的值设置为2会导致不太可靠（reliable）因为提交的事务仅仅每秒才flush一次到redo日志，但对于一些场景是可以接受的，比如对于主节点的备份节点这个值是可以接受的。如果值为0速度就更快了，但在系统崩溃时可能丢失一些数据：只适用于备份节点。
　　innodb_flush_method: 这项配置决定了数据和日志写入硬盘的方式。一般来说，如果你有硬件RAID控制器，并且其独立缓存采用write-back机制，并有着电池断电保护，那么应该设置配置为O_DIRECT；否则，大多数情况下应将其设为fdatasync（默认值）。sysbench是一个可以帮助你决定这个选项的好工具。
　　innodb_log_buffer_size: 这项配置决定了为尚未执行的事务分配的缓存。其默认值（1MB）一般来说已经够用了，但是如果你的事务中包含有二进制大对象或者大文本字段的话，这点缓存很快就会被填满并触发额外的I/O操作。看看Innodb_log_waits状态变量，如果它不是0，增加innodb_log_buffer_size。
　　其他设置
　　query_cache_size: query cache（查询缓存）是一个众所周知的瓶颈，甚至在并发并不多的时候也是如此。 最佳选项是将其从一开始就停用，设置query_cache_size = 0（现在MySQL 5.6的默认值）并利用其他方法加速查询：优化索引、增加拷贝分散负载或者启用额外的缓存（比如memcache或redis）。如果你已经为你的应用启用了query cache并且还没有发现任何问题，query cache可能对你有用。这是如果你想停用它，那就得小心了。
　　log_bin：如果你想让数据库服务器充当主节点的备份节点，那么开启二进制日志是必须的。如果这么做了之后，还别忘了设置server_id为一个唯一的值。就算只有一个服务器，如果你想做基于时间点的数据恢复，这（开启二进制日志）也是很有用的：从你最近的备份中恢复（全量备份），并应用二进制日志中的修改（增量备份）。二进制日志一旦创建就将永久保存。所以如果你不想让磁盘空间耗尽，你可以用 PURGE BINARY LOGS 来清除旧文件，或者设置 expire_logs_days 来指定过多少天日志将被自动清除。
　　记录二进制日志不是没有开销的，所以如果你在一个非主节点的复制节点上不需要它的话，那么建议关闭这个选项。
　　skip_name_resolve：当客户端连接数据库服务器时，服务器会进行主机名解析，并且当DNS很慢时，建立连接也会很慢。因此建议在启动服务器时关闭skip_name_resolve选项而不进行DNS查找。唯一的局限是之后GRANT语句中只能使用IP地址了，因此在添加这项设置到一个已有系统中必须格外小心。
```

5 mysql做过什么优化



## 主从复制

43 mysql用的是什么？主从？

45 mysql 主从数据库的搭建，配置的命令

21mysql会安装么？ mysql数据库同步怎样实现，怎么指定主的服务器

16 主从复制出错怎么解决

### 3 mysql怎么样，主从复制做过吗，怎样查看复制的状态

```mysql
 show slave status \G 
 #这个是查看从机复制状态，里面的参数很多，你要留意
 # Slave_IO_Running:
 # Slave_SQL_Running: 这两个参数的状态，正常是YES，如果是no，那么主从复制肯定是有问题的
 # 第一个参数是复制主库的binlog文件的线程，第二个是执行复制过来的binlog二进制文件，可以理解为编译成sql，并执行。
 #  主机的话你只要查看show master status;即可，只要有值，那么主库是支持主从复制的，就是说其他从机可以从主机上复制binlog文件
```

54 主从复制在停机和不停机情况下，分别怎么加从服务器

111 mysql同步有几个进程

122 主用的是什么引擎

146 mysql主从用什么方式传输日志

173 mysql主从复制数据丢失怎么知道的

123 主从都用的是innoDB吗

153 mysql主从数据不同步如何解决
82 mysql备份时备份主的还是备份从的？

154 mysql主从做过切换吗?当主的失效,从的自动切换成主?

## 备份

### 7 mysql是怎么备份的

```
一、备份的目的
　　　　做灾难恢复：对损坏的数据进行恢复和还原
　　　　需求改变：因需求改变而需要把数据还原到改变以前
　　　　测试：测试新功能是否可用
　　二、备份需要考虑的问题
　　　　可以容忍丢失多长时间的数据；
　　　　恢复数据要在多长时间内完；
　　　　恢复的时候是否需要持续提供服务；
　　　　恢复的对象，是整个库，多个表，还是单个库，单个表。
　　三、备份的类型
　　　　1、根据是否需要数据库离线
　　　　　　冷备（cold backup）：需要关mysql服务，读写请求均不允许状态下进行；
　　　　　　温备（warm backup）： 服务在线，但仅支持读请求，不允许写请求；
　　　　　　热备（hot backup）：备份的同时，业务不受影响。
　　　　注：
　　　　　　1、这种类型的备份，取决于业务的需求，而不是备份工具
　　　　　　2、MyISAM不支持热备，InnoDB支持热备，但是需要专门的工具
　　　　2、根据要备份的数据集合的范围
　　　　　　完全备份：full backup，备份全部字符集。
　　　　　　增量备份: incremental backup 上次完全备份或增量备份以来改变了的数据，不能单独使用，要借助完全备份，备份的频率取决于数据的更新频率。
　　　　　　差异备份：differential backup 上次完全备份以来改变了的数据。
　　　　　　建议的恢复策略：
　　　　　　　　完全+增量+二进制日志
　　　　　　　　完全+差异+二进制日志
　　　　3、根据备份数据或文件
　　　　　　物理备份：直接备份数据文件
　　　　　　优点：备份和恢复操作都比较简单，能够跨mysql的版本，恢复速度快，属于文件系统级别的
　　　　　　建议：不要假设备份一定可用，要测试mysql>check tables；检测表是否可用
　　　　　　逻辑备份: 备份表中的数据和代码
　　　　　　优点：恢复简单、备份的结果为ASCII文件，可以编辑与存储引擎无关可以通过网络备份和恢复
　　　　　　缺点：备份或恢复都需要mysql服务器进程参与备份结果占据更多的空间，浮点数可能会丢失精度 还原之后，缩影需要重建
　　四：备份的对象
　　　　1、 数据；
　　　　2、配置文件；
　　　　3、代码：存储过程、存储函数、触发器
　　　　4、os相关的配置文件
　　　　5、复制相关的配置
　　　　6、二进制日志
　　五、备份和恢复的实现
　　　　1、利用select into outfile实现数据的备份与还原。
　　　　2、利用mysqldump工具对数据进行备份和还原
　　　　3、利用lvm快照实现几乎热备的数据备份与恢复
　　　　4、基于Xtrabackup做备份恢复。
　　　　优势：
　　　　　　1、快速可靠的进行完全备份
　　　　　　2、在备份的过程中不会影响到事务
　　　　　　3、支持数据流、网络传输、压缩，所以它可以有效的节约磁盘资源和网络带宽。
　　　　　　4、可以自动备份校验数据的可用性。
```

### 10 你们备份数据是备份在同一设备？

```
本地+服务器
```

### 149 数据库的备份方式

```mysql
1、完全备份，这是大多数人常用的方式，它可以备份整个数据库，包含用户表、系统表、索引、视图和存储过程等所有数据库对象。但它需要花费更多的时间和空间，所以，一般推荐一周做一次完全备份。
　　2、事务日志备份，事务日志是一个单独的文件，它记录数据库的改变，备份的时候只需要复制自上次备份以来对数据库所做的改变，所以只需要很少的时间。为了使数据库具有鲁棒性，推荐每小时甚至更频繁的备份事务日志。
　　3、差异备份也叫增量备份。它是只备份数据库一部分的另一种方法，它不使用事务日志，相反，它使用整个数据库的一种新映象。它比最初的完全备份小，因为它只包含自上次完全备份以来所改变的数据库。它的优点是存储和恢复速度快。推荐每天做一次差异备份。
　　4、文件备份，数据库可以由硬盘上的许多文件构成。如果这个数据库非常大，并且一个晚上也不能将它备份完，那么可以使用文件备份每晚备份数据库的一部分。由于一般情况下数据库不会大到必须使用多个文件存储，所以这种备份不是很常用。
```

108 你怎么给mysql备份

44 mysql数据库的备份，用的是脚本？
110 mysql怎么备份

93 备份 存储过

99 什么时候恢复数据库？
100 怎么恢复？
101 为什么要备份数据库？
102 备份的周期？

105 mysql远程备份

112 怎么做的mysql数据库备份

117 问我mysqldump是什么意思，mysqld和mysqld_safe什么区别

120 mysql怎么做的备份

125 mysql的备份命令是什么

```mysql
　mysqldump -hhostname -uusername -ppassword databasename > backupfile.sql
　　　　备份MySQL数据库为带删除表的格式
　　　　备份MySQL数据库为带删除表的格式，能够让该备份覆盖已有数据库而不需要手动删除原有数据库。
　　mysqldump -–add-drop-table -uusername -ppassword databasename > backupfile.sql
　　　　直接将MySQL数据库压缩备份
　　mysqldump -hhostname -uusername -ppassword databasename | gzip > backupfile.sql.gz
　　　　备份MySQL数据库某个(些)表
　　mysqldump -hhostname -uusername -ppassword databasename specific_table1 specific_table2 > backupfile.sql
　　　　同时备份多个MySQL数据库
　　mysqldump -hhostname -uusername -ppassword –databases databasename1 databasename2 databasename3 > multibackupfile.sql
　　　　仅仅备份数据库结构
　　mysqldump –no-data –databases databasename1 databasename2 databasename3 > structurebackupfile.sql
　　　　备份服务器上所有数据库
　　mysqldump –all-databases > allbackupfile.sql
　　　　还原MySQL数据库的命令
　　mysql -hhostname -uusername -ppassword databasename < backupfile.sql
　　　　还原压缩的MySQL数据库
　　gunzip < backupfile.sql.gz | mysql -uusername -ppassword databasename
　　　　将数据库转移到新服务器
　　mysqldump -uusername -ppassword databasename | mysql –host=*.*.*.* -C databasename
```

152 介绍下mysql的管理，备份，主备

164 mysql的l备份流程，数据的导入

157 mysql备份，备份某个库中的某个表

103 系统调优都做哪些？

87 如果一个表被drop，在有完善的归档和备份情况下，如何恢复
88 .对于一个恢复时间比较短的系统（数据库50G，每天归档5G），你如何设计备份策略

34 mysql 数据库的备份与还原 例如一个数据库test

## 常见问题

39 Mysql怎么解决故障切换

### 20 mysql 简单的 怎么登入 怎么创建数据库bbb创建 用户 密码 授权

```
　一、创建用户:
　　CREATE USER用于创建新的MySQL账户。要使用CREATE USER，您必须拥有mysql数据库的全局CREATE USER权限，或拥有INSERT权限。对于每个账户，CREATE USER会在没有权限的mysql.user表中创建一个新记录。如果账户已经存在，则出现错误。
　　使用自选的IDENTIFIED BY子句，可以为账户给定一个密码。user值和 密码的给定方法和GRANT语句一 样。特别是，要在纯文本中指定密码，需忽略PASSWORD关键词。要把 密码指定为由PASSWORD()函数返回的混编值，需包含关键字PASSWORD
　　The create user command:mysql> CREATE USER yy IDENTIFIED BY ‘123’;
　　面建立的用户可以在任何地方登陆。
　　　　mysql> CREATE USER yy@localhost IDENTIFIED BY ‘123’;
　　二、授权:
　　命令:GRANT privileges ON databasename.tablename TO ‘username’@’host’
　　说明: privileges - 用户的操作权限,如SELECT , INSERT , UPDATE 等.如果要授予所的权限则使
　　用 ALL.;databasename - 数据库名,tablename-表名,如果要授予该用户对所有数据库和表的相应操
　　作权限则可用表示, 如.*.
　　　　GRANT SELECT, INSERT ON test.user TO ‘pig’@’%’;
　　　　GRANT ALL ON . TO ‘pig’@’%’;
　　注意:用以上命令授权的用户不能给其它用户授权,如果想让该用户可以授权,用以下命令:
　　GRANT privileges ON databasename.tablename TO ‘username’@’host’ WITH GRANT OPTION;
　　刷新系统权限表
　　flush privileges;
　　三、设置与更改用户密码
　　命令:SET PASSWORD FOR ‘username’@’host’ = PASSWORD(‘newpassword’);如果是当前登陆用户用SET PASSWORD = PASSWORD(“newpassword”);
　　例子:SET PASSWORD FOR ‘pig’@’%’ = PASSWORD(“123456”);
　　或：update mysql.user set password=password(‘新密码’) where User=”phplamp” and Host=”localhost”;
　　四、撤销用户权限
　　命令: REVOKE privilege ON databasename.tablename FROM ‘username’@’host’;
　　说明: privilege, databasename, tablename - 同授权部分.
　　例子: REVOKE SELECT ON . FROM ‘pig’@’%’;
　　注意: 假如你在给用户’pig’@’%’授权的时候是这样的(或类似的):GRANT SELECT ON test.user TO ‘pig’@’%’, 则在使用 REVOKE SELECT ON . FROM ‘pig’@’%’;命令并不能撤销该用户对test数据库中user表的SELECT 操作.相反,如果授权使用的是GRANT SELECT ON . TO ‘pig’@’%’;则REVOKE SELECT ON test.user FROM ‘pig’@’%’;命令也不能撤销该用户对test数据库中user表的Select 权限.具体信息可以用命令SHOW GRANTS FOR ‘pig’@’%’; 查看.
　　五、删除用户
　　命令: DROP USER ‘username’@’host’;
　　或：DELETE FROM user WHERE User=”phplamp” and Host=”localhost”;
　　//删除用户的数据库
　　mysql>drop database phplampDB;
```

### 23 查询mysql数据库中用户，密码，权限的命令

```mysql
#查看MYSQL数据库中所有用户
SELECT DISTINCT CONCAT(‘User: ”’,user,”’@”’,host,”’;’) AS query FROM mysql.user;
#查看数据库中具体某个用户的权限
show grants for ‘cactiuser’@’%’;
```

### 75 数据库有几种数据保护方式（AAA）

```
实现数据库安全性控制的常用方法和技术有：用户标识和鉴别；存取控制；视图机制；审计；数据加密
```

40 Mysql都有那几种日志



## 49 写出三种数据库对象

```txx
1.表（Table ）数据库中的表与我们日常生活中使用的表格类似，它也是由行（Row） 和列（Column）组成的。列由同类的信息组成，每列又称为一个字段，每列的标题称为字段名。行包括了若干列信息项。一行数据称为一个或一条记录，它表达有一定意义的信息组合。一个数据库表由一条或多条记录组成，没有记录的表称为空表。每个表中通常都有一个主关键字，用于惟一地确定一条记录。

2.索引（Index）根据指定的数据库表列建立起来的顺序。它提供了快速访问数据的途径，并且可监督表的数据，使其索引所指向的列中的数据不重复。

3.视图（View）视图看上去同表似乎一模一样，具有一组命名的字段和数据项，但它其实是一个虚拟的表，在数据库中并不实际存。在视图是由查询数据库表产生的，它限制了用户能看到和修改的数据。由此可见，视图可以用来控制用户对数据的访问，并能简化数据的显示，即通过视图只显示那些需要的数据信息。

4.图表（Diagram）图表其实就是数据库表之间的关系示意图。利用它可以编辑表与表之间的关系。

5.缺省值（Default）缺省值是当在表中创建列或插入数据时，对没有指定其具体值的列或列数据项赋予事先设定好的值。

6.规则（Rule）规则是对数据库表中数据信息的限制。它限定的是表的列。

7.触发器（Trigger）是用户定义的SQL事务命令的集合。当对一个表进行插入、更改、删除时，这组命令就会自动执行。

8.存储过程:是为完成特定的功能而汇集在一起的一组SQL 程序语句，经编译后存储在数据库中的SQL 程序。

9.用户:所谓用户就是有权限访问数据库的人。
```



##  

51 编译与解释的区别



55 Sybase数据库相关的东西很多



60 跨库时用存储引擎好不好？为何？
61 nosql的利用价值
62 如何合理分配表空间、创建？
63 数据空间的扩容？
64 对表空间如何监控？
65 把数据分开放在不同的表空间，利与弊？

113 如果数据库中有一个表的数据量很大，无法用rm删除，该怎么办

### 118 然后问mysql这个命令跟mysqld有什么区别么

```mysql
mysqld是用来启动mysql数据库的命令
mysql是打开并执行sql语句的命令
这两个都在mysql安装文件夹的bin目录下

启动mysql服务器就执行mysqld
```

### 127 怎么导出表结构？

```mysql
mysqldump -u用戶名 -p密码 -d 数据库名 表名 > 脚本名;
导出整个数据库结构和数据
mysqldump -h localhost -uroot -p123456 database > dump.sql
导出单个数据表结构和数据
mysqldump -h localhost -uroot -p123456  database table > dump.sql
导出整个数据库结构（不包含数据）
mysqldump -h localhost -uroot -p123456  -d database > dump.sql
导出单个数据表结构（不包含数据）
mysqldump -h localhost -uroot -p123456  -d database table > dump.sql
```

### 130 几种关闭数据库方法？参数？区别？

```mysql
一、normal

正常关闭数据库，等到所有的用户会话进程退出数据库连接时才真正关闭数据库

如果始终存在登陆用户的会话，那么使用normal方式关闭数据库时即shutdown normal时，数据库停止在现在的界面上没有反应，要等到用户会话主动退出以后才会关闭数据库连接。这样的话就必须要等待用户进程，在特殊情况下如果一定要使用normal方式关闭数据库同时又不想被动等待用户进程主动退出时可以选择杀死所有用户会话进程

select sid,serial#,username,type from v$session;//查看当前登录用户获得sid和serial#

alter system kill session ‘SID,serial#’；杀死指定sid和serial#的用户会话进程。

二、transactional

按照事务级关闭数据库，等到所有的事务交易都结束以后才关闭数据库，保证事务都被commit或rollback

三、immediate

立即关闭数据库，把所有没有完成的事务交易都rollback，不能保证事务的全部顺利结束。

四、abort

即刻关闭数据库，并且不校验强制型检查点和关闭文件。

因为abort模式不能保证所发检测点成功，只要所发检测点成功就表示数据库已经同步，在重启系统时也就不需要恢复啦。以上四种模式其中abort模式在重启数据库系统时需要进行数据库的恢复，immediate模式虽然不用进行数据库的恢复但是用户需要重新输入哪些没有提及的数据
```

### 139 正常登入MYSQL后使用什么命令查看其进程是否正常，和变量

```shell
　通过ps命令查看mysql进程即可，执行如下命令：
　　ps -aux|grep mysql
　　执行结果中看到了mysql进程，确定mysql正在运行。
　　mysql 1634 0.0 0.1 13980 1268 ? S Aug11 0:00 [mysqld]
root 6849 0.0 0.0 4816 640 pts/1 S 09:40 0:00 grep mysql
```

### 144 linux mysql 重设root密码

```mysql
#1首先停止mysql服务进程：
 service mysqld stop
#2然后编辑mysql的配置文件my.cnf
1 vim /etc/my.cnf
#3找到 [mysqld]这个模块：在最后面添加一段代码
1 skip-grant-tables   ##忽略mysql权限问题，直接登录
#4然后保存 :wq!退出启动mysql服务：
1 service mysqld start
#5直接进入mysql数据库：

1 Starting MySQL. SUCCESS! 
 2 [root@web1 ~]# mysql
 3 Welcome to the MySQL monitor.  Commands end with ; or \g.
 4 Your MySQL connection id is 1
 5 Server version: 5.6.34 Source distribution
 6 
 7 Copyright (c) 2000, 2016, Oracle and/or its affiliates. All rights reserved.
 8 
 9 Oracle is a registered trademark of Oracle Corporation and/or its
10 affiliates. Other names may be trademarks of their respective
11 owners.
12 
13 Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.
14 
15 mysql> 
使用mysql表，然后进行修改mysql的root密码：

 1 mysql> use mysql; ##使用mysql数据库
 2 Reading table information for completion of table and column names
 3 You can turn off this feature to get a quicker startup with -A
 4 
 5 Database changed
 6 mysql> update user set password=password("123456") where user="root";##更新密码
 7 Query OK, 4 rows affected (0.00 sec)
 8 Rows matched: 4  Changed: 4  Warnings: 0
 9 
10 mysql> flush privileges;##刷新权限
11 Query OK, 0 rows affected (0.00 sec)
 

 
 

 1 [root@web1 ~]# ps -ef |grep mysql  ##显示mysql现有的进程
 2 root      56407      1  0 17:50 pts/0    00:00:00 /bin/sh /usr/local/mysql/bin/mysqld_safe --datadir=/data/mysql --pid-file=/data/mysql/web1.pid
 3 mysql     56533  56407  0 17:50 pts/0    00:00:00 /usr/local/mysql/bin/mysqld --basedir=/usr/local/mysql --datadir=/data/mysql --plugin-dir=/usr/local/mysql/lib/plugin --user=mysql --log-error=/data/mysql/web1.err --pid-file=/data/mysql/web1.pid
 4 root      56560   1737  0 17:55 pts/0    00:00:00 grep mysql
 5 [root@web1 ~]# killall mysqld  ##删除mysql现有进程
 6 [root@web1 ~]# ps -ef |grep mysql
 7 root      56566   1737  0 17:56 pts/0    00:00:00 grep mysql
 8 [root@web1 ~]# service mysqld start ##重新启动mysql服务
 9 Starting MySQL. SUCCESS! 
10 [root@web1 ~]# mysql -uroot -p ##使用新密码登录
11 Enter password: 
12 Welcome to the MySQL monitor.  Commands end with ; or \g.
13 Your MySQL connection id is 1
14 Server version: 5.6.34 Source distribution
15 
16 Copyright (c) 2000, 2016, Oracle and/or its affiliates. All rights reserved.
17 
18 Oracle is a registered trademark of Oracle Corporation and/or its
19 affiliates. Other names may be trademarks of their respective
20 owners.
21 
22 Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.
23 
24 mysql> 
```

### 145 mysql远程连接命令

```shell
一、MySQL 连接本地数据库，用户名为“root”，密码“123”（注意：“-p”和“123” 之间不能有空格）
C:\>mysql -h localhost -u root -p123
二、MySQL 连接远程数据库（192.168.0.201），端口“3306”，用户名为“root”，密码“123”
C:\>mysql -h 192.168.0.201 -P 3306 -u root -p123
```

### 148 mysql、oracle、sqlserver的默认端口是

```txt
sqlserver默认端口号为：1433
mysql默认端口号为：3306**
oracle默认端口号为：1521
```

### 158 查看mysql数据库是否支持innodb

```mysql
show engines;

Engine	Support	Comment	Transactions	XA	Savepoints
InnoDB	DEFAULT	Supports transactions, row-level locking, and foreign keys	YES	YES	YES
MRG_MYISAM	YES	Collection of identical MyISAM tables	NO	NO	NO
MEMORY	YES	Hash based, stored in memory, useful for temporary tables	NO	NO	NO
BLACKHOLE	YES	/dev/null storage engine (anything you write to it disappears)	NO	NO	NO
MyISAM	YES	MyISAM storage engine	NO	NO	NO
CSV	YES	CSV storage engine	NO	NO	NO
ARCHIVE	YES	Archive storage engine	NO	NO	NO
PERFORMANCE_SCHEMA	YES	Performance Schema	NO	NO	NO
FEDERATED	NO	Federated MySQL storage engine			

```

### 171 在系统中有个sql文件，怎么在数据库中执行sql文件中的命令

```mysql
如何在linux中执行sql文件
第一种方法:
在命令行下(未连接数据库),输入 
mysql -h localhost -u root -p123456 < F:\hello world\niuzi.sql (注意路径不用加引号的!!) 回车即可.
第二种方法:
在命令行下(已连接数据库,此时的提示符为 mysql> ),输入 source F:\hello world\niuzi.sql (注意路径不用加引号的)
或者 . F:\hello world\niuzi.sql (注意路径不用加引号的) 回车即可.

```

