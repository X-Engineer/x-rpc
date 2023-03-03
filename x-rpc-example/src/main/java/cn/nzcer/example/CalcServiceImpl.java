package cn.nzcer.example;

/**
 * @project: x-rpc
 * @ClassName: CalcServiceImpl
 * @author: nzcer
 * @creat: 2023/3/3 12:05
 * @description:
 */
public class CalcServiceImpl implements CalcService{
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int minus(int a, int b) {
        return a - b;
    }
}
