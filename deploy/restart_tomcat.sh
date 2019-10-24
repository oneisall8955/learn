#!/bin/bash

# 远程调用请开启，以免Java环境变量不存在
# source /etc/profile

# 重启函数
function restart_tomcat(){

    restart_tomcat_home=$1
    tomcat_bin_dir="${restart_tomcat_home}/bin"

    if [ ! -d ${tomcat_bin_dir} ] ;then
        echo "${restart_tomcat_home}tomcat目录不存在"
        return
    fi

    echo "启动tomcat:${restart_tomcat_home}"
    ps -ef | grep "${tomcat_bin_dir}" |grep "java" |grep -v "grep"
    tomcat_pid=`ps -ef | grep "${tomcat_bin_dir}" |grep "java" |grep -v "grep" |awk '{print $2}'`
    echo -e "tomcat_pid=${tomcat_pid}"

    # 存在则杀掉进程
    if [ -z "${tomcat_pid}" ] ; then
        echo "目标tomcat进程不存在，直接启动"
    else
        echo "目标tomcat进程PID=${tomcat_pid}"
        kill -9 ${tomcat_pid}
        echo "PID ${tomcat_pid} 已被执行kill"
    fi

    echo "休眠3s ..."
    sleep 3s

    echo "执行startup.sh"
    cd ${tomcat_bin_dir}
    bash startup.sh

    echo "已成功执行启动tomcat命令"
    echo "请查看日志：tail -f ${restart_tomcat_home}/logs/catalina.out"
}

restart_tomcat /home/tomcats/demo