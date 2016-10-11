package com.smallchill.core.toolbox.kit;

import com.google.common.cache.*;
import com.smallchill.api.common.exception.LimitCountException;
import com.smallchill.api.common.exception.LockException;
import com.smallchill.api.common.model.Null;
import com.smallchill.api.common.model.ReqLimit;
import com.smallchill.core.constant.ConstCache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 简易缓存，基于google guava cache
 * Created by yesong on 2016/9/27.
 */
public class QuickCacheKit implements ConstCache{

    private static Map<String,LoadingCache> cacheMap = new ConcurrentHashMap<>();
    private static final String SMS_CACHE = "sms_cache";
    private static final String LOCLIP_CACHE = "loclip_cache";

    private static final int SMS_TIMEOUT = 1;    // 短信超时时间 分钟
    private static final int LOCKIP_TIMEOUT = 5; // IP锁定时间 秒

    public static LoadingCache init(String cacheName, long duration, RemovalListener removalListener) {
        LoadingCache cache = cacheMap.get(cacheName);
        if(cache == null) {
            synchronized (QuickCacheKit.class) {
                System.out.println("=====================创建缓存==================");
                cache = CacheBuilder.newBuilder()
                        .concurrencyLevel(4)
                        .expireAfterWrite(duration, TimeUnit.SECONDS)
                        .removalListener(removalListener)
                        .maximumSize(100)
                        .recordStats()
                        .build(new CacheLoader() {
                            @Override
                            public Object load(Object key) throws Exception {
                                return new Null();
                            }
                        });
                cacheMap.put(cacheName,cache);
            }
        }
        return cache;
    }

    public static Cache initSms() {
        return init(SMS_CACHE,SMS_TIMEOUT,null);
    }

    public static LoadingCache initLockIp() {
        return init(LOCLIP_CACHE, LOCKIP_TIMEOUT, new RemovalListener() {
            @Override
            public void onRemoval(RemovalNotification rn) {
                System.out.println("===============解除限制===============");
                String ip = (String) rn.getKey();
                System.out.println("key:" + ip);
                ReqLimit reqLimit = CacheKit.get(DEFAULT_CACHE,ip);
                if(reqLimit != null){
                    reqLimit.clearCount();
                }
            }
        });
    }

    public static void main(String[] args) {
        /**
        try {
            LoadingCache cache = QuickCacheKit.initLockIp();
            cache.put("admin","admin");

            LoadingCache cache2 = QuickCacheKit.initLockIp();
            System.out.println("这个会取到值:" + cache2.get("admin"));
            Thread.sleep(6000);
            LoadingCache cache3 = QuickCacheKit.initLockIp();
            System.out.println("这个不会取到值:" + cache3.get("admin"));
            cache.put("admin2","admin2");
            LoadingCache cache4 = QuickCacheKit.initLockIp();
            System.out.println("这会取到值:" + cache4.get("admin2"));
            Thread.sleep(6000);
            LoadingCache cache5 = QuickCacheKit.initLockIp();
            System.out.println("这个不会取到值:" + cache5.get("admin2"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         **/

        /**  **/
        String ip = "127.0.0.1";
        for(int j =0; j < 10; j++) {
            System.out.println("第" + (j+1) + "轮访问");
            for(int i=0; i < 50; i++) {
                ReqLimit reqLimit = CacheKit.get(DEFAULT_CACHE,ip);
                if(j == 1 && i == 10) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
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
            int m = j + 1;
            if(m == 2 || m == 4 || m == 7) {
                System.out.println("========================");
                System.out.println("暂停六秒钟,释放被限制IP的缓存");
                System.out.println("========================");
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
