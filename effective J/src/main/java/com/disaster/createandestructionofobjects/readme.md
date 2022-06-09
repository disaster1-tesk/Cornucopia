#### 第一章：对象的创建和销毁过程
#####1.用静态工厂方法代替构造器
* 优点
    * 静态工厂与构造器不同的第一大优势在于，他们有名称
    + 不必再每次调用他们的时候都创建一个新对象
    - 他们可以返回原返回类型的任何子类型的对象
    * 所返回的对象的类可以随着每次调用而发生变化，这取决与静态工厂方法的参数值
    + 方法返回的对象所属的类，在编写包含该静态工厂方法时可以不存在
  
* 缺点
    * 类如果不含有公有的或者受保护的构造器，就不能被子类化
    + 程序员很难发现他们
    
* 静态工厂惯用名称：
    * **from**—类型转换方法，它只有单个参数，返回该类型的一个实例，把他们合并起来，例如：
        * Date d = Data.from(instant)
    * **of**—聚合方法，带有多个参数，返回该类型的一个实例，把他们合并起来，例如：
        * Set<Rank> faceCards = EnumSet.of(JACK,QUEEN,KING)
    * **valueOf**—比from和of更繁琐的一种替代方法，例如：
        * BigInteger prime = BigInteger.valueOf(Integer.MAX_VALUE);
    * **instance或者getInstance**—返回的是通过方法的（如有）参数来描述的，但是不能说与参数具有相同的值，例如：
        * StackWalker luke = StackWalker.getInstance(options);
    * **create或者newInstance**—像instance或者getInstance一样，但create或者newInstance能够确保每次调用都返回一个新的实例，例如：
        * Object newArray = Array.newInstance(classObject, arrayLen);
    * **getType**—像getInstance一样，但是在工厂方法处于不同的类中的时候使用。Type表示工厂方法所返回的对象类型，例如：
        * FileStore fs = Files.getFileStore(path);
    * **newType**—像newInstance一样，但是在工厂方法处于不同的类中的时候使用。Type表示工厂所返回的对象类型，例如：
        * BufferedReader br = Files.newBufferedReader(path);
    * **type**—getType和newType的简版，例如：
        * List<Complaint> litany = Collections.list(legacyLitany);
        
        