# 删除 /opt/soft/log/ 目录下 7 天前的日志
find /opt/soft/log/ -mtime +7 -name "*.log" -exec rm -rf {} \;

# 去重 tmp.txt 是文本
awk '!a[$0]++' tmp.txt

# ssh 本地端口转发到远程服务端口
# A主机指定开启一个端口，用来转发到B主机的某个端口
# A主机执行以下命令，访问A的18080端口即可访问 192.168.1.1 的8080端口服务
# N 代表登录到目标足迹不执行命令，f代表后台运行
# * 代表绑定A的任何网卡
ssh -fNL *:18080:localhost:8080 root@192.168.1.1

# ssh 远程端口转发到本地服务端口
# A主机已经存在一个端口8080运行着应用服务，希望通过B主机的指定某个端口18080转发访问到这个服务
# A主机执行以下命令，访问B主机的18080端口，即可转发访问到A主机的8080端口的服务
ssh -fNR *:18080:localhost:8080 root@192.168.1.1
# 备注，A主机执行这一命令，结果是只能在B本机内，调用curl 127.0.0.1:18080 访问A的服务
# 存在一个问题，通过访问 192.168.1.1:18080或B的公网IP 123.123.123.123:18080访问A服务，会直接拒绝
# 这时候需要在B主机内灵活使用本地端口转发到远程服务端口(-L)，将本地新开一个端口如18081，转发到本地的18080
# B中执行以下命令
ssh -fNL *:18081:localhost:18080 root@localhost
# 此时，使用192.168.1.1:18081或123.123.123.123:18081即可访问到A服务
