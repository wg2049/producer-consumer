# 多线程练习：实现一个生产者-消费者模型

生产者/消费者模型是多线程领域的经典问题。请实现一个生产者/消费者模型，其中：

- 生产者生产10个随机的整数供消费者使用（随机数可以通过`new Random().nextInt()`获得）
- 消费者依次消费这10个随机的整数

标准输出应该得到这样的结果：

```
Producing 42
Consuming 42
Producing -1
Consuming -1
...
Producing 10086
Consuming 10086
Producing -12345678
Consuming -12345678
```

我们鼓励你采用不同的方法尝试，例如：

- `Object.wait/notify`
- `Lock/Condition`
- `BlockingQueue`
- `Semaphore`
- `Exchanger`
- 等等等等

你可以在[`ProducerConsumer1`](https://github.com/hcsp/producer-consumer/blob/master/src/main/java/com/github/hcsp/multithread/ProducerConsumer1.java)/[`ProducerConsumer2`](https://github.com/hcsp/producer-consumer/blob/master/src/main/java/com/github/hcsp/multithread/ProducerConsumer2.java)/../[`ProducerConsumer5`](https://github.com/hcsp/producer-consumer/blob/master/src/main/java/com/github/hcsp/multithread/ProducerConsumer5.java)中使用不同方式完成挑战，
你也可以使用类似的格式，新建`ProducerConsumerX`类使用更多方法完成挑战，每当你新建`ProducerConsumerX`类，它都会被自动测试。

祝你好运！

-----
注意！我们只允许你修改以下文件，对其他文件的修改会被拒绝：
- [src/main/java/com/github/hcsp/multithread/](https://github.com/hcsp/producer-consumer/blob/master/src/main/java/com/github/hcsp/multithread/)
-----


完成题目有困难？不妨来看看[写代码啦的相应课程](https://xiedaimala.com/tasks/9bf0fb20-929d-4e17-891a-4673291d74a0)吧！

回到[写代码啦的题目](https://xiedaimala.com/tasks/9bf0fb20-929d-4e17-891a-4673291d74a0/quizzes/1b0fc390-74ad-4f55-b355-90b8a9154cc5)，继续挑战！ 
