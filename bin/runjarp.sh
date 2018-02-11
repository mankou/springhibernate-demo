#!/bin/sh

author=man003@163.com
version=V1-20171222

jarName=springhibernate-demo-0.0.1-SNAPSHOT.jar

delaySeconds=0

env=pro

usage() {
 cat <<EOM
DESC: 运行JAR包的脚本
Usage: $SHELL_NAME parameters
  start [env]       |start program
  stop              |stop program
  status            |show program status
  restart           |restart program
EOM
}

SHELL_PATH=$(cd $(dirname "$0");pwd)
PARENT_PATH=`dirname $SHELL_PATH`;
SHELL_NAME=`basename $0`
SHELL_ABSOLUTE_PATH=$SHELL_PATH/$SHELL_NAME
jarAbsolutePath=$PARENT_PATH/lib/$jarName

configPath=config/setting-$env.properties

if [ ! -z $2 ]
then
    env=$2
    configPath=config/setting-$2.properties
fi

configPathAbsolute=$PARENT_PATH/$configPath

JVM_CONFIG=$PARENT_PATH/config/jvm.config
if [ -e $JVM_CONFIG ]
then
    JVM_OPT=`cat $JVM_CONFIG |xargs`
fi

cd $PARENT_PATH

function start {
    process=`ps -ef|grep $jarAbsolutePath |grep -v "grep"|awk '{print $2}'`
    if [ -z $process ]
    then
        echo "wait for $delaySeconds s"
        sleep $delaySeconds
        echo "start ..."
        nohup java -Dconfig.path=$configPathAbsolute -Dservice.env=$env $JVM_OPT -jar $jarAbsolutePath &
        process=`ps -ef|grep $jarAbsolutePath |grep -v "grep"|awk '{print $2}'`
        echo started pid $process
    else
        echo program already running $process please stop it first!!!
    fi
    exit $?
}

function stop {
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
}

function status {
    process=`ps -ef|grep $jarAbsolutePath |grep -v "grep"|awk '{print $2}'`
    if [ -z $process ] 
    then
        echo program is not running!!!
    else
        ps -ef |grep $jarAbsolutePath |grep -v "grep"
    fi
    exit $?
}

function restart {
    $SHELL_ABSOLUTE_PATH stop
    $SHELL_ABSOLUTE_PATH start $env
}

case "$1" in
  start)
        start
        ;;
  stop)
        stop
        ;;
  status)
        status
        ;;
  restart)
      restart
      ;;
  *)
        usage
        exit 0
        ;; 
esac
