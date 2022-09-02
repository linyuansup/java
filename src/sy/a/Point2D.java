package sy.a;

public class Point2D {
    /**
     * 获取 x 坐标
     *
     * @return x 坐标
     */
    public int getX() {
        return x;
    }

    /**
     * 获取 y 坐标
     *
     * @return y 坐标
     */
    public int getY() {
        return y;
    }

    private int x;
    private int y;

    /**
     * 构造 2D 点
     *
     * @param x x 坐标
     * @param y y 坐标
     */
    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 平移点
     *
     * @param a x 平移坐标
     * @param b y 平移坐标
     */
    public void offset(int a, int b) {
        x += a;
        y += b;
    }
}
