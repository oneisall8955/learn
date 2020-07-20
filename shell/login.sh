#!/bin/bash -e
if [[ $# != 1 ]]; then
    echo '必须传递主机标识'
    exit 1
fi
hostin=$1
password=""
prefix="192.168.199."

case $hostin in
    20)
        password="123456.1"
        ;;
    21)
        password="123456.2"
        ;;
    22)
        password="123456.3"
        ;;
    *)
        echo "unsupported host ${hostin}"
        exit 1
esac

host="${prefix}${hostin}"
user="root"
echo "$host $user $password"
# login.sh and expect_login.sh must be in same dir
work_path=$(dirname $(readlink -f $0))
${work_path}/expect_login.sh "$host" $user $password
