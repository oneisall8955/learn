#!/bin/bash -e

###########
##  定义 ##
###########
# 定义一个空 Map
# declare -A map=()
# 定义时初始化 Map
declare -A map=(["k1"]="v1" ["k2"]="v2")

###############
##  输出size ##
###############
echo ${#map[@]}

###################
##  输出所有 key  ##
###################
echo ${!map[@]}

#####################
##  输出所有 value  ##
#####################
echo ${map[@]}

######################
##  添加/修改 value  ##
######################
map["k2"]="newK2"
map["k3"]="k3"

################
##  遍历 map  ##
################
for key in ${!map[@]}
do
    value=${map[${key}]}
    echo "map : ${key} = ${value}"
done

###############
##  删除 key ##
###############
unset map["k1"]

######################
##  map 作为方法参数 ##
######################
function iterMap() {
    tmp=$(declare -p "$1")
    declare -A _map=${tmp#*=}
    for key in ${!_map[@]}
    do
        value=${map[${key}]}
        echo "map : ${key} = ${value}"
    done
}

echo ""
iterMap map

############
##  demo  ##
############

# 服务器IP别名映射
declare -A ip_server_name_map=(["192.168.0.1"]="app-01" ["192.168.0.2"]="app-02")
declare -A server_name_ip_map=()

echo ""
echo "--------debug--------"

# 遍历 map 中的 key
for key in ${!ip_server_name_map[@]}
do
	value=${ip_server_name_map[${key}]}
    echo "ip_server_name_map : ${key} = ${value}"
    # map 反转
	server_name_ip_map[${value}]=${key}
done
echo ""

for key in ${!server_name_ip_map[@]}
do
	value=${server_name_ip_map[${key}]}
    echo "server_name_ip_map : ${key} = ${value}"
done

echo "--------debug--------"

# 各个服务器地址别名取值
app_01_server_ip=${server_name_ip_map["app-01"]}
app_02_server_ip=${server_name_ip_map["app-02"]}