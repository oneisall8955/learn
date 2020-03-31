#!/bin/bash -e
# 打印当前主机及部署环境信息
echo "主机：`hostname`"
echo "环境：$active"

# 不同环境不同参数配置
active=${active}
work_space=""
jar_file=""

case ${active} in
  "dev")
    work_space="/home/verticle"
    jar_file="verticle-dev.jar"
    ;;
  "integration")
    work_space="/home/uat"
    jar_file="verticle-uat.jar"
    ;;
  "prod")
    work_space="/home/verticle"
    jar_file="verticle-prod.jar"
    ;;
  *)
    echo "请传递正确的部署环境参数,目前支持为[dev,integration,prod]"
    exit 1
   ;;
esac
echo "路径：${work_space}"
echo "jar ：${jar_file}"

# 进入工作路径
cd ${work_space}
# 备份最多5份 verticle-xxx.jar 文件
if [[ ! -f "${jar_file}" ]]; then
    echo "在当前路径 `pwd` 找不到 ${jar_file}，不备份"
else
    echo "在当前路径 `pwd` 找到 ${jar_file}，进行备份"
    nowTime=`date +'%Y%m%d_%H%M%S'`
    echo "当前时间：`date +'%Y-%m-%d %H:%M:%S'`"
    backup_file="${jar_file}_${nowTime}"
    mv ${jar_file} "${backup_file}"
    echo "已成功备份：${backup_file}"
fi
ls -t | grep "${jar_file}_" | awk 'NR>5{print "rm -rf " $0}' | xargs rm -rf
echo "所有最新备份文件如下"
ls -l | grep "${jar_file}_"
echo ""

# 复制 /opt/deploy/jar_file 目录的 verticle-1.0-SNAPSHOT-fat.jar 到 /home/xxx/verticle-xxx.jar
# /opt/deploy/jar_file 由 配置 SSH SERVER 的 Remote Directory 与 Remote directory 决定
cp "/opt/deploy/jar_file/verticle-1.0-SNAPSHOT-fat.jar" ${work_space}/${jar_file}

# ps 命令检查是否存在 verticle-xxx.jar 的 java 进程，存在则 kill
echo "正在检测是否存在 ${jar_file} 相关的进程"
ps aux|grep ${jar_file} |grep -v "grep" |awk '{print $2}'
java_pid=`ps aux|grep ${jar_file} |grep -v "grep" |awk '{print $2}'`
echo -e "java java_pid :${java_pid}"
# 存在则杀掉进程
if [[ -z "${java_pid}" ]] ; then
    echo "目标进程不存在，直接启动"
else
    echo "目标进程PID=${java_pid}"
    kill -9 ${java_pid}
    echo "PID ${java_pid} 已被执行kill"
    echo "休眠3s ..."
    sleep 3s
fi

# java 命令启动 jar 包
log_file="engine.log"
echo "执行启动 nohup java -jar ${jar_file} > ${log_file} 2>&1 &"
#nohup  java -jar ${jar_file} > ${log_file} 2>&1 &
echo "启动成功 日志文件在 ${log_file}"