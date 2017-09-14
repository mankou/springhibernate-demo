@echo off

echo 正在运行,请稍后....
echo path:%~dp0

::将当前路径切换到脚本所以路径 当使用相对路径时会用到 如在其它地方或者计划任务中运行脚本有可能找不到路径
::因一般脚本都放在bin 而jar包放在根目录下 所以这里切换到根目录下
%~d0
cd %~dp0%\..
java -jar lib/pszxjob-0.0.1-SNAPSHOT.jar
@pause
