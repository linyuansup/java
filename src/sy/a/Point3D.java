package sy.a;

public class Point3D extends Point2D {
    private int z;

    /**
     * 获取 z 坐标
     *
     * @return z 坐标
     */
    public int getZ() {
        return z;
    }

    /**
     * 构造3D点
     *
     * @param x x 坐标
     * @param y y 坐标
     * @param z z 坐标
     */
    public Point3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    /**
     * 构造3D点
     *
     * @param p 通过 2D 点表示的点坐标
     * @param z z 坐标
     */
    public Point3D(Point2D p, int z) {
        super(p.getX(), p.getY());
        this.z = z;
    }

    /**
     * 平移点
     *
     * @param a x 平移坐标
     * @param b y 平移坐标
     * @param c z 平移坐标
     */
    public void offset(int a, int b, int c) {
        super.offset(a, b);
        z += c;
    }

    public static void main(String[] args) {
        Point2D p2d1 = new Point2D(2, 0);
        Point2D p2d2 = new Point2D(0, 4);
        p2d1.offset(1, 0);
        System.out.println(Math.sqrt((p2d1.getX() - p2d2.getX()) * (p2d1.getX() - p2d2.getX())
                + (p2d1.getY() - p2d2.getY()) * (p2d1.getY() - p2d2.getY())));
        Point3D p3d1 = new Point3D(p2d1, 0);
        Point3D p3d2 = new Point3D(0, 0, 3);
        p3d2.offset(0, 0, 1);
        System.out.println(Math.sqrt((p3d1.getX() - p3d2.getX()) * (p3d1.getX() - p3d2.getX())
                + (p3d1.getY() - p3d2.getY()) * (p3d1.getY() - p3d2.getY())
                + (p3d1.getZ() - p3d2.getZ()) * (p3d1.getZ() - p3d2.getZ())));
    }
}
