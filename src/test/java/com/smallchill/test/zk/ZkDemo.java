//package com.smallchill.test.zk;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.zookeeper.WatchedEvent;
//import org.apache.zookeeper.Watcher;
//import org.apache.zookeeper.ZooKeeper;
//
//import java.io.IOException;
//import java.util.concurrent.CountDownLatch;
//
///**
// * Created by Administrator on 2016/9/20.
// */
//public class ZkDemo implements Watcher {
//
//    private static Log log = LogFactory.getLog(ZkDemo.class.getName());
//
//    //缓存时间
//    private static final int SESSION_TIME = 2000;
//    protected ZooKeeper zooKeeper;
//    protected CountDownLatch countDownLatch = new CountDownLatch(1);
//
//    public void connect(String hosts) throws IOException, InterruptedException {
//        zooKeeper = new ZooKeeper(hosts, SESSION_TIME, this);
//        countDownLatch.await();
//    }
//
//    /* (non-Javadoc)
//     * @see org.apache.zookeeper.Watcher#process(org.apache.zookeeper.WatchedEvent)
//     */
//    @Override
//    public void process(WatchedEvent event) {
//        // TODO Auto-generated method stub
//        if (event.getState() == Event.KeeperState.SyncConnected) {
//            countDownLatch.countDown();
//        }
//    }
//
//    public void close() throws InterruptedException {
//        zooKeeper.close();
//    }
//}
