#!/bin/bash -e
if [[ $# != 1 ]]; then
    echo '必须传递主机标识'
    exit 1
fi

# ssh conf
declare -A ssh_conf_map=()
ssh_conf_map["server1"]="192.168.199.1,root,pwd1"
ssh_conf_map["server2"]="192.168.199.2,root,pwd2"
ssh_conf_map["server3"]="192.168.199.3,root,pwd3"

host_nick_name=$1
ssh_conf=${ssh_conf_map[${host_nick_name}]}
if [[ "${ssh_conf}x" == "x" ]]; then
    echo "找不到主机标识的ssh配置:${host_nick_name}"
    exit 1
fi

echo "$host_nick_name ssh config-> $ssh_conf"

OLD_IFS=$IFS
IFS=","
# shellcheck disable=SC2206
array_ssh_conf=($ssh_conf)
IFS="$OLD_IFS"

size=${#array_ssh_conf[@]}

if [[ $size -ne 3 ]];then
    echo "ip,user,password"
    exit 1
fi

host=${array_ssh_conf[0]}
user=${array_ssh_conf[1]}
password=${array_ssh_conf[2]}

echo "$host $user $password"
# login.sh and expect_login.sh must be in same dir
# shellcheck disable=SC2046
work_path=$(dirname $(readlink -f "$0"))
"${work_path}"/expect_login.sh "$host" "$user" "$password"
