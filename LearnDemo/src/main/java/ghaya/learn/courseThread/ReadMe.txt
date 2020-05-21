本套练习来自于马士兵java多线程高并发变成公开课
总结：
1 synchronized的基本概念，
    对象锁的使用方法synchronized(o){}
    方法上的锁属于当前类这个对象
    优化方法：synchronized锁住的代码块越少越好，有时只需锁变量修改，不用锁整个方法

2 脏读问题：
    设置属性过程中其他属性获取该属性，造成不统一，
3 同类下多个上锁的方法可以互相调用，
    再次调用还可以获得当前类对象的锁，同理子类中同步方法也可以调用父类中同步方法
    死锁概念：线程1访问A锁再访问B锁   线程2访问B锁再访问A锁，相互等待锁  经典案例 哲学家吃饭
4 volatile关键字，定义在属性前，属性在多线程中可见
    程序在运行中时，每个线程占用一块cpu缓存，
    计算时，线程1这一块cpu缓存，会从内存中读取copy一份数据进线程1的cpu缓存运行计算循环等代码，
    当其他线程2去修改内存中的属性时，会读取copy数据进线程2的cpu缓存，修改后返回给内存
    此时内存中数据已被修改，但是线程1的程序还在运行的情况，
    线程1的cpu缓存则有可能有可能有可能不会再次访问修改后的数据
    volatile关键字定义属性后，在属性被修改后会通知其他线程再来读取一遍该属性值
    即便不通知，cpu在空闲时也会去内存中刷新读取属性
5 使用对象锁时，不使用String字符串。
    例如：String s1 = "ABC" 使用字符串锁定的是"ABC" 而不是s1
    也同时防止使用某工具类库中已有"ABC"字符串的锁，造成死锁现象
6 CountDownLatch 门闩
    new CountDownLatch(int i)线程中调用awiat()锁定  countDown()会i-1  i=0  则打开门闩，所有调用过门闩的对象都会列入受控范围
7 lock锁 ReentrantLock
    手动上锁
    lock.lock();//不可响应中断
    手动关闭
    lock.unlock();
    trylock尝试锁定（秒）  返回boolean
    一个线程用lockInterruptibly()去获取锁可响应中断   可以被其他地方用interrupt()打断等待防止死锁
    公平锁机制  new ReentrantLock(true)  公平锁，平衡分配线程
8 生产者消费者模式
    ReentrantLock中的Condition


使用高并发容器总结：
1.对于map/set的选择使用
不加锁，非多线程
hashmap
treemap
linkedhashmap
加锁
Hashtable 并发量小
Collections.synchronizedXXX  并发量小

concurrenthashmap  并发较高时
concurrentskiplistmap  跳表 并发较高时,且要排序

2.队列
非同步
ArrayList
LinkedList
同步
Collections.synchronizedXXX并发量小
CopyOnWriteList  写少读多
Queue
    ConcurrentLinkedQueue  非阻塞式并发队列(offer 添加 有返回值，poll 从头取出，peek从头读不删)
    BlockingQueue   阻塞式队列  (put添加 满了等待，take拿取 空了等待)
        LinkedBlockingQueue     无界队列
        ArrayBlockingQueue      有界队列
        TransferQueue   直接交给消费者线程，不经过队列
        SynchronousQueue    特殊的TransferQueue容量为0
    DelayQueue  执行定时任务，每个元素添加进去时要设置时间，根据时间排序最长的先执行
双端队列Deque



