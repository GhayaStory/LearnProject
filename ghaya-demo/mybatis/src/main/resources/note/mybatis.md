# Mybatis







## 三种执行器

### 相关结构

![image-20210602150854473](mybatis.assets/image-20210602150854473.png)

### 简单执行器



### 可重用执行器

只编译一次



### 批处理执行器







## 一级缓存

默认打开，k-v形式

### 命中条件



![image-20210602185223647](mybatis.assets/image-20210602185223647.png)

#### 1.运行时参数\

#### 2.操作配置相关

```tex
1.sql与参数相同
2.必须是相同的statementID(DAO方法是同一个)
3.sqlSession必须一样（会话级缓存）
4.RowBounds 分页行范围必须相同
```







代码：

第一次查询时

会将sql和传参生成一个缓存 CacheKey  并且设置在缓存中

```java
@Override
public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler) throws SQLException {
  BoundSql boundSql = ms.getBoundSql(parameter);
  //生成并设置缓存
  CacheKey key = createCacheKey(ms, parameter, rowBounds, boundSql);
  return query(ms, parameter, rowBounds, resultHandler, key, boundSql);
}
```

第二次查询时会再去查询缓存localCache

```java
@SuppressWarnings("unchecked")
@Override
public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, CacheKey key, BoundSql boundSql) throws SQLException {
  ErrorContext.instance().resource(ms.getResource()).activity("executing a query").object(ms.getId());
  if (closed) {
    throw new ExecutorException("Executor was closed.");
  }
  if (queryStack == 0 && ms.isFlushCacheRequired()) {
    clearLocalCache();
  }
  List<E> list;
  try {
    queryStack++;
    // 查缓存
    list = resultHandler == null ? (List<E>) localCache.getObject(key) : null;
    if (list != null) {
      handleLocallyCachedOutputParameters(ms, key, parameter, boundSql);
    } else {
      list = queryFromDatabase(ms, parameter, rowBounds, resultHandler, key, boundSql);
    }
  } finally {
    queryStack--;
  }
  if (queryStack == 0) {
    for (DeferredLoad deferredLoad : deferredLoads) {
      deferredLoad.load();
    }
    // issue #601
    deferredLoads.clear();
    if (configuration.getLocalCacheScope() == LocalCacheScope.STATEMENT) {
      // issue #482
      clearLocalCache();
    }
  }
  return list;
}
```



![image-20210528111809780](mybatis.assets/image-20210528111809780.png)

### 源码解析

![image-20210603100654653](mybatis.assets/image-20210603100654653.png)

BaseExecutor

![image-20210603101039521](mybatis.assets/image-20210603101039521.png)  

## 二级缓存

需要手动提交后才命中二级缓存

