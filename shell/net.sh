#!/bin/bash -e

function help() {
    echo 'usage: net.sh -p|-n port|name'
}

if [[ $# != 2 ]]; then
    help
    exit 1
fi

type=${1}
filter=${2}

if [ "${type}" == "-p" ]; then
    lsof -i:"${filter}"
elif [ "${type}" == "-n" ]; then
    netstat -ntlp |grep "${filter}"
else
  help
fi
