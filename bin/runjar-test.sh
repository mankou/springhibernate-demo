#!/bin/sh
# name runjar.sh
# desc 运行jar包的脚本
# create by m-ning at 2017-04-22 13:26:18

author=man003@163.com
version=V1-20170504

############配置区域##################

# jar包名 
jarName=pszxjob-0.0.1-SNAPSHOT.jar

# 延迟启动
# 之所以要延迟启动100s 是因为如果开机就启动java 会报连不上数据库的错误,所以先等100s再启动
delaySeconds=0
####################################



usage() {
 cat <<EOM
DESC: 运行JAR包的脚本
Usage: $SHELL_NAME parameters
  start     |start program
  stop      |stop program
  status    |show program status
EOM
}

###  ------------------------------- ###
###  Main script                     ###
###  ------------------------------- ###


# 脚本当前路径
SHELL_PATH=$(cd $(dirname "$0");pwd)
PARENT_PATH=`dirname $SHELL_PATH`;
SHELL_NAME=`basename $0`
JVM_CONFIG=$PARENT_PATH/config/jvm.config
jarAbsolutePath=$PARENT_PATH/lib/$jarName


cd $PARENT_PATH

# jvm相关路径
if [ -e $JVM_CONFIG ]
then
    JVM_OPT=`cat $JVM_CONFIG |xargs`
fi

case "$1" in
  start)
      # 判断程序是否正在运行 如果正在运行不启动
        process=`ps -ef|grep $jarAbsolutePath |grep -v "grep"|awk '{print $2}'`
        if [ -z $process ]
        then
            echo "wait for $delaySeconds s"
            sleep $delaySeconds
            echo "start ..."
            #echo "java $JVM_OPT -jar $jarAbsolutePath &"
            nohup java -Dservice.env=test $JVM_OPT -jar $jarAbsolutePath &
            process=`ps -ef|grep $jarAbsolutePath |grep -v "grep"|awk '{print $2}'`
            echo started pid $process
        else
            echo program already running $process please stop it first!!!
        fi
        exit $?
        ;;
  stop)
        process=`ps -ef|grep $jarAbsolutePath |grep -v "grep"|awk '{print $2}'`
        if [ ! -z $process ]
        then
            echo killed $process
            kill -9 $process
        else
            echo program not running!!!
            exit 0
        fi
        exit $?
        ;;
  status)
        process=`ps -ef|grep $jarAbsolutePath |grep -v "grep"|awk '{print $2}'`
        if [ -z $process ] 
        then
            echo program is not running!!!
        else
            ps -ef |grep $jarAbsolutePath |grep -v "grep"
        fi
        exit $?
        ;;
  *)
        usage
        exit 0
        ;; 
esac
