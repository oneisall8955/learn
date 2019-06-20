#!/bin/bash -e

#================================================================#
#   Env Supported:  linux Like unix                              #
#   Description: automatic deploy war to server with jenkins     #
#   Author: Liu Zhicong <oneisall8955@gmail.com>                 #
#   Copyright © 2019 oneisall. All rights reserved.              #
#================================================================#

#linux color
red="\033[31m"  ## red
green="\033[32m" ## green
yellow="\033[33m" ## yellow
end="\033[0m" #end
#jenkins color
red=""  ## red
green="" ## green
yellow="" ## yellow
end="" #end

echo -e "${green}${green}begin deploy...${end}"

#base path (jenkins's job workspace . such :/root/.jenkins/workspace/producer)
base_path=`pwd`
jenkins_project_war_dir_path="${base_path}/build/libs"
cd ${jenkins_project_war_dir_path}

#gradle build the project archive name
war_file_name="ROOT.war"
war_file_path="${jenkins_project_war_dir_path}/${war_file_name}"

#tomcat path (the target tomcat to deploy war for this time. such :/opt/tomcats/tomcat8080-producer-dev)
tomcat_location="${tomcat_location}"
#check tomcat run status url .this param should be passed by jenkins setting ,such : 192.168.3.1:8080
check_url=${check_url}

#notAllowExternal: check tomcat_location & check_url be allowed to used external pass parameter
notAllowExternal=1
if [ ${notAllowExternal} -eq 1 ]; then
    #active:spring-boot project environment
    active=${active}
    echo -e "${red}:param active =[${active}]"
    case ${active} in
        "dev")
            tomcat_location="/opt/tomcats/tomcat8080-producer-dev"
            check_url="192.168.3.1:8080"
            ;;
        "prod")
            tomcat_location="/opt/tomcats/tomcat9090-producer-dev"
            check_url="192.168.3.1:9090"
            ;;
        *)
        echo -e "${red}:param active ERROR ! param active's value is not dev or prod .check it !${active}"
        exit 1
        ;;
    esac
fi
tomcat_webapps="${tomcat_location}/webapps"
tomcat_log="${tomcat_location}/logs/catalina.out"

echo -e "${green}current_path:`pwd`${end}"

echo -e "${green}begin checking war file & tomcat location ...${end}"
#1.war check
if [ ! -f "${war_file_name}" ]; then
    echo -e "${red}war file can not found:${war_file_path}${end}"
    exit 1
fi
#2.tomcat check
if [ ! -d "${tomcat_location}" ]; then
    echo -e "${red}tomcat location can not found:${tomcat_location}${end}"
    exit 1
fi
echo -e "${green}check finish${end}"
echo ""

#3.rename to ROOT.war
echo -e "${green}rename ${war_file_name}  to ROOT.war ...${end}"
if [ ${war_file_name} != 'ROOT.war' ]; then
    mv ${war_file_name} "ROOT.war"
    war_file_name="ROOT.war"
    war_file_path="${current_path}/${war_file_name}"
    echo -e "${green}rename finish${end}"
else
    echo -e "${yellow}the war file's name :${war_file_name} equals 'ROOT.war',no need to rename${end}"
fi
echo ""

#4 cd to ${tomcat_location}/webapps
cd ${tomcat_webapps}
echo -e "${green}current_path:`pwd`${end}"
echo ""

#5.backup ROOT.war
if [ ! -f "ROOT.war" ]; then
    echo -e "${yellow}ROOT.war not found in `pwd`, not backup${end}"
else
    echo -e "${yellow}ROOT.war has found in `pwd`, begin backup ...${end}"
    nowTime=`date +'%Y%m%d_%H%M%S'`
    echo -e "${green}now:`date +'%Y-%m-%d %H:%M:%S'`${end}"
    mv ROOT.war "./ROOT.war_${nowTime}"
    echo -e "${green}backup finish${end}"
fi
#TODO remove old backup files,keep 5 backup files up to date
echo ""

#4.cp
#${tomcat_webapps} equals `pwd` !!!
echo -e "${green}cp ${war_file_path} to `pwd` ...${end}"
cp ${war_file_path} ./ROOT.war
echo -e "${green}cp finish${end}"
echo ""

echo -e "${green}deploy finish ,please check log ...${end}"
echo -e "${green}tail -f ${tomcat_log}${end}"
echo ""

#5.check tomcat
echo -e "${green}check tomcat ...${end}"
if [ "X" == "${check_url}X" ]; then
    echo -e "${yellow}check_url is blank ,not check .exit 0 ${end}"
    exit 0
else
    echo -e "${yellow}check_url:${check_url}${end}"
    echo -e "${yellow}sleep 15s for waiting tomcat reload the ROOT.war${end}"
    #wait tomcat reload the war
    sleep 15s
    echo -e "${yellow}start check ... ${end}"
    loseCount=0
    set +e
    while true
    do
        let loseCount++
        echo -e "${yellow}execute ${loseCount} time :wget --spider -q -o /dev/null  --tries=1 -T 5 ${check_url}${end}"
        wget --spider -q -o /dev/null  --tries=1 -T 5 ${check_url}
        if [ $? -eq 0 ]; then
            echo -e "${green}${loseCount} time check : connect ${check_url} success.${end}"
            break
        else
            echo -e "${yellow}lost the target tomcat ${loseCount} time .${end}"
        fi
        #check lose time
        if [ ${loseCount} -ge 18 ] ; then
            echo -e "${red}had lost the target tomcat in 18 times check (3min)${end}"
            exit 1;
        fi
        echo -e "${yellow}sleep 10s...${end}"
        sleep 10s
    done
    set +e
    echo -e "${green}check tomcat finish,use this url :${check_url}${end}"
fi
echo ""