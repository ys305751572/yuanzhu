package com.smallchill.api.common.model;

import com.google.common.cache.Cache;
import com.google.common.cache.LoadingCache;
import com.smallchill.api.common.exception.LimitCountException;
import com.smallchill.api.common.exception.LockException;
import com.smallchill.core.constant.ConstCache;
import com.smallchill.core.plugins.dao.Blade;
import com.smallchill.core.toolbox.kit.CacheKit;
import com.smallchill.core.toolbox.kit.QuickCacheKit;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 *
 * Created by yesong on 2016/9/27.
 */
public class ReqLimit implements ConstCache,Serializable{

    private static final int LIMIT_COUNT = 30;

    private String ip;                                  // 访问IP
    private int count = 0;                              // 单位时间被记录的次数
    private int lockCount = 0;                          // 被锁次数，超过3次IP将被锁定
    private long lastTime = System.currentTimeMillis(); // 最后访问时间
    private boolean isLock = false;

    private ReqLimit(String ip) {
        this.ip = ip;
    }

    public static ReqLimit create(String ip) {
        return new ReqLimit(ip);
    }

    public boolean validate() throws LockException, LimitCountException {
        if(isReorcd()) throw new LimitCountException();
        if(isLock) {
            lockIp();
            throw new LockException();
        }
        if(isExpire()) {
            addOnce();
        }
        else {
            System.out.println("超过限制时间:清空");
            this.count = 0;
            this.lastTime = System.currentTimeMillis();
        }
        return true;
    }

    public void addOnce() {
        ++this.count;
        if(this.count == LIMIT_COUNT) {
            ++this.lockCount;
        }
        if(this.lockCount == 5) {
            this.isLock = true;
        }
    }

    public boolean isReorcd() {
        if(this.count > LIMIT_COUNT) {
            LoadingCache cache = QuickCacheKit.initLockIp();
            try {
                if(cache.get(this.ip) instanceof Null) {
                    cache.put(this.ip,this);
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    public boolean isExpire() {
        long now = System.currentTimeMillis();
        long expire = now - lastTime;
        return expire < 1000;
    }

    public void lockIp() {
        LockIp lockIp = new LockIp();
        lockIp.setIp(this.ip);
        Blade.create(LockIp.class).save(lockIp);
    }

    public void disLock() {
        this.count = 0;
        this.isLock = false;
    }

    public void clearCount() {
        this.count = 0;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isLock() {
        return isLock;
    }

    public void setLock(boolean lock) {
        isLock = lock;
    }

    public static void main(String[] args) {
        /**
        String ip = "127.0.0.1";
        for(int j =0; j < 10; j++) {
            System.out.println("第" + (j+1) + "轮访问");
            for(int i=0; i < 50; i++) {
                ReqLimit reqLimit = CacheKit.get(DEFAULT_CACHE,ip);

                if(reqLimit == null) {
                    reqLimit = ReqLimit.create(ip);
                    CacheKit.put(DEFAULT_CACHE,ip,reqLimit);
                    System.out.println("第"+ (i+1) +"次，访问通过");
                }
                else {
                    try {
                        boolean flag = reqLimit.validate();
                        if(flag) {
                            System.out.println("第"+ (i+1) +"次，访问通过");
                        }
                    } catch (LockException e) {
                        System.out.println("IP被锁定");
                    } catch (LimitCountException e) {
                        System.out.println("第"+ (i+1) +"次访问，超过访问限定次数");
                    }
                }
            }
            if(j == 2) {
                System.out.println("========================");
                System.out.println("暂停两秒钟");
                System.out.println("========================");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }**/
    }
}
