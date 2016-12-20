import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

/**
 * Created by yong on 2016/12/20.
 */
public class TestCreateDir {
    private static final String PATH = "/crud";
    public static void main(String[] args) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("60.205.181.26:2181", retryPolicy);
        client.start();
        try {
            client.create().forPath(PATH, "I love messi".getBytes());

            System.out.println(new String(client.getData().forPath(PATH)));
//            byte[] bs = client.getData().forPath(PATH);
//            System.out.println("新建的节点，data为:" + new String(bs));
//
//            client.setData().forPath(PATH, "I love football".getBytes());
//
//            // 由于是在background模式下获取的data，此时的bs可能为null
//            byte[] bs2 = client.getData().watched().inBackground().forPath(PATH);
//            System.out.println("修改后的data为" + new String(bs2 != null ? bs2 : new byte[0]));
//
//            client.delete().forPath(PATH);
//            Stat stat = client.checkExists().forPath(PATH);
//
//            // Stat就是对zonde所有属性的一个映射， stat=null表示节点不存在！
//            System.out.println(stat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
