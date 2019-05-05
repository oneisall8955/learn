package com.oneisall.learn.java.utils;

import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

/**
 * 环境信息获取
 * @author oneisall
 */
@SuppressWarnings("all")
public class ComputerInfoUtil {

    private static Logger logger = LoggerFactory.getLogger(ComputerInfoUtil.class);

    static {
        try {
            init();
        } catch (IOException e) {
            logger.warn("获取不到环境信息:", e);
        }
    }

    private static void init() throws IOException {
        Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
        Set<String> networkInterfaceNames = Sets.newHashSet("eth0", "em1");
        while (e.hasMoreElements()) {
            NetworkInterface networkInterface = e.nextElement();
            int index = networkInterface.getIndex();
            String name = networkInterface.getName();
            String displayName = networkInterface.getDisplayName();
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();

            if (!networkInterfaceNames.contains(name)) {
                continue;
            }

            logger.info("index:{}", index);
            logger.info("name:{}", name);
            logger.info("displayName:{}", displayName);
            logger.info("inetAddresses:{}", inetAddresses);

            while (inetAddresses.hasMoreElements()) {
                InetAddress inetAddress = inetAddresses.nextElement();
                byte[] addressAddress = inetAddress.getAddress();
                List<Byte> list = new ArrayList<>();
                for (byte aByte : addressAddress) {
                    list.add(aByte);
                }
                String hostAddress = inetAddress.getHostAddress();
                logger.warn("inetAddresses-address:{},hostAddress:{}", list, hostAddress);
            }
        }
    }
}