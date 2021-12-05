在JDK8中共提供43个现有的函数接口，可以满足基本的开发需要，如果需要特别的函数接口可以自定义，使用@FunctionalInterface注解。  
需要注意的是：接口中有且仅有一个抽象方法，Java中的Lambda才能顺利地进行推导。  
比如：Function接口：

  ```java

@FunctionalInterface
public interface Function<T, R> {

    // 只有这一个抽象函数
    R apply(T t);

    default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

    default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }

    static <T> Function<T, T> identity() {
        return t -> t;
    }
}
```

像LongConsumer接口：

```java

@FunctionalInterface
public interface LongConsumer {

    // 抽象函数
    void accept(long value);

    default LongConsumer andThen(LongConsumer after) {
        Objects.requireNonNull(after);
        return (long t) -> {
            accept(t);
            after.accept(t);
        };
    }
}
```

在java中像这种函数接口总共分为6大类：

1. Function：(函数)代表其参数与返回类型不一致的函数（17个接口）。
2. UnaryOperator：(一元操作)表示结果和参数类型一致的函数（4个）。
3. BinaryOperator：(二元操作)表示结果和参数类型一致的函数（4个）。
4. Predicate：（断言）该接口代表传入一个参数并返回boolean的函数（5个）。
5. Supplier：（供应者）表示没有输入参数，但返回一个值（5个）。
6. Consumer：（消费者）表示传入一个参数，但不返回任何值（8个）。

这些函数接口都在java.util.function包下：

| Function | UnaryOperator | BinaryOperator | Predicate | Supplier | Consumer |
| :---- | :---- | :---- | :---- | :---- | :---- |
| BiFunction | IntUnaryOperator | IntBinaryOperator | BiPredicate | IntSupplier | BiConsumer |
|IntFunction|LongUnaryOperator|LongBinaryOperator|IntPredicate|LongSupplier|IntConsumer|
|LongFunction|DoubleUnaryOperator|DoubleBinaryOperator|LongPredicate|DoubleSupplier|LongConsumer|
|DoubleFunction| | |DoublePredicate|BooleanSupplier|DoubleConsumer|
|IntToLongFunction| | | | | ObjIntConsumer|
|IntToDoubleFunction| | | | | ObjLongConsumer|
|LongToIntFunction | | | | | ObjDoubleConsumer|
|LongToDoubleFunction| | | | | |
|DoubleToIntFunction| | | | | |
|DoubleToLongFunction| | | | | |
|ToIntFunction| | | | | |
|ToIntBiFunction| | | | | |
|ToLongFunction| | | | | |
|ToLongBiFunction| | | | | |
|ToDoubleFunction| | | | | |
|ToDoubleBiFunction| | | | | |











