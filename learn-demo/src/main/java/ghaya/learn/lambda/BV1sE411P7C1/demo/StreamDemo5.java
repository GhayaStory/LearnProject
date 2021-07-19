package ghaya.learn.lambda.BV1sE411P7C1.demo;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream的性能分析
 *
 * http://www.zimug.com/java/%e6%81%95%e6%88%91%e7%9b%b4%e8%a8%80%e4%bd%a0%e5%8f%af%e8%83%bd%e7%9c%9f%e7%9a%84%e4%b8%8d%e4%bc%9ajava%e7%ac%ac6%e7%af%87%ef%bc%9astream%e6%80%a7%e8%83%bd%e5%b7%ae%ef%bc%9f%e4%b8%8d%e8%a6%81%e4%ba%ba/.html
 *
 */
public class StreamDemo5 {


    /**
     * 5亿个int随机数，求最小值
     * 使用普通for循环，执行效率是Stream串行流的2倍。也就是说普通for循环性能更好。
     * Stream并行流计算是普通for循环执行效率的4-5倍。
     * Stream并行流计算 > 普通for循环 > Stream串行流计算
     *
     */
    @Test
    public void demo1(){

    }

    /**
     * 长度为10的1000000随机字符串，求最小值
     * 普通for循环执行效率与Stream串行流不相上下
     * Stream并行流的执行效率远高于普通for循环
     * Stream并行流计算 > 普通for循环 = Stream串行流计算
     *
     */
    public void demo2(){

    }


    /**
     * 10个用户，每人200个订单。按用户统计订单的总价。
     * Stream并行流的执行效率远高于普通for循环
     * Stream串行流的执行效率大于等于普通for循环
     * Stream并行流计算 > Stream串行流计算 >= 普通for循环
     */
    public void demo3() {

    }

    /**
     * 总结
     * 对于简单的数字(list-Int)遍历，普通for循环效率的确比Stream串行流执行效率高（1.5-2.5倍）。
     * 但是Stream流可以利用并行执行的方式发挥CPU的多核优势,因此并行流计算执行效率高于for循环。
     * 对于list-Object类型的数据遍历，普通for循环和Stream串行流比也没有任何优势可言，更不用提Stream并行流计算。
     * 虽然在不同的场景、不同的数据结构、不同的硬件环境下。Stream流与for循环性能测试结果差异较大，甚至发生逆转。但是总体上而言：
     *
     * Stream并行流计算 >> 普通for循环 ~= Stream串行流计算
     *
     * 数据容量越大，Stream流的执行效率越高。
     * Stream并行流计算通常能够比较好的利用CPU的多核优势。CPU核心越多，Stream并行流计算效率越高。
     * stream比for循环慢5倍？也许吧，单核CPU、串行Stream的int类型数据遍历？我没试过这种场景，但是我知道这不是应用系统的核心场景。看了十几篇测试博文，和我的测试结果。我的结论是： 在大多数的核心业务场景下及常用数据结构下，Stream的执行效率比for循环更高。 毕竟我们的业务中通常是实实在在的实体对象，没事谁总对List<Int>类型进行遍历？谁的生产服务器是单核？。
     */
    public void demo4(){

    }
}
