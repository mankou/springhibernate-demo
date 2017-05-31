#!/bin/bash
# 工程初始化
# 在实际项目开发时都有自己的工程名、包名,该脚本帮助你快速将该demo工程修改成你想要的名称
# 运行方式
# chmod +s *.sh
# ./initProject.sh

author=man003@163.com
version=V1.0-20170530

# 脚本当前路径
SHELL_PATH=$(cd $(dirname "$0");pwd)
SHELL_NAME=`basename $0`
PARENT_PATH=`dirname $SHELL_PATH`;
cd $PARENT_PATH

###############定义原工程相关变量###################

# 原工程名称
project_name_old=springhibernate-demo

# 原工程中maven的 groupId
group_id_old=mang.demo

# 原工程中包名前缀
package_old=mang.demo.springhibernate

# 原工程中主类路径
main_class_old=mang.demo.springhibernate.App

# 原工程中打成jar包的名字
jar_name_old=springhibernate-demo-0.0.1-SNAPSHOT.jar

# 原工程中开发代码的路径前缀
java_path_prefix_develop=src/main/java
# 原工程中测试代码的路径前缀
java_path_prefix_test=src/test/java

# 获取包名根路径 如包名为 mang.demo.springmvc 则这里获取为mang
# 使用 sed #\..*##g  第一个\.表示转义. .*表示仍然字符 也即把 .开始的字符全部替换为空
# 目的 以后迁移java文件后需要把该目录删除 
packageRootPrefix=`echo $package_old|sed "s#\..*##g"`
javaRootPath_develop=${java_path_prefix_develop}/$packageRootPrefix
javaRootPath_test=${java_path_prefix_test}/$packageRootPrefix

# 通过包名获取java文件实际路径
# 如包名为 mang.demo.springmvc 则其java路径在 src/main/java/mang/demo/springmvc
packageFilePathOld=`echo "$package_old" | sed 's#\.#/#g'`
javaPathOld_develop=${java_path_prefix_develop}/$packageFilePathOld
javaPathOld_test=${java_path_prefix_test}/$packageFilePathOld

# 定义新工程相关变量默认值
# 注这些变量可通过 init.config 文件覆盖默认值

# 新工程的名称
project_name_new=mtest

# 新工程 maven中的groupID 用于替换pom.xml
group_id_new=com.mtest

# 新工程包名 
package_new=com.mtest

# 新工程主类路径
main_class_new=com.mtest.App


# 新工程中打成jar包的名字
jar_name_new=mtest-0.0.1-SNAPSHOT.jar


# 处理开发代码java路径
# 将java文件移动到新路径并删除原路径
processJavaPath_develop() {
    local project=$1
    local javaPathFrom=${PARENT_PATH}/${javaPathOld_develop}
    local javaPathTo=${PARENT_PATH}/${javaPathNew_develop}
    
    if [ -d $javaPathFrom ]
    then
        if [ ! -d ${javaPathTo}  ]
        then
            mkdir -p ${javaPathTo}
        fi
        mv $javaPathFrom/* $javaPathTo

        local javaPathFromRoot=${PARENT_PATH}/$javaRootPath_develop
        if [ -d ${javaPathFromRoot} ]
        then
            rm -rf $javaPathFromRoot
        fi
    fi
}

# 处理测试代码java路径
# 将java文件移动到新路径并删除原路径
processJavaPath_test() {
    local javaPathFrom=${PARENT_PATH}/${javaPathOld_test}
    local javaPathTo=${PARENT_PATH}/${javaPathNew_test}

    if [ -d $javaPathFrom ]
    then
        if [ ! -d ${javaPathTo}  ]
        then
            mkdir -p ${javaPathTo}
        fi
        mv $javaPathFrom/* $javaPathTo

        local javaPathFromRoot=${PARENT_PATH}/$javaRootPath_test
        if [ -d ${javaPathFromRoot} ]
        then
            rm -rf $javaPathFromRoot
        fi
    fi
}

###  ------------------------------- ###
###  Main script                     ###
###  ------------------------------- ###

# 获取配置文件覆盖变量默认值
initConfig=${SHELL_PATH}/initProject.config
if [ -f $initConfig ]
then
    echo 采用init.config覆盖默认配置
    . $initConfig

    # 通过新工程的包名取出java文件路径
    # 如下将 .变成/  如 com.mtest 将变成 com/mtest
    packageFilePathNew=`echo "$package_new" | sed 's#\.#/#g'`
    javaPathNew_develop=${java_path_prefix_develop}/${packageFilePathNew}
    javaPathNew_test=${java_path_prefix_test}/${packageFilePathNew}
fi

echo 新工程名  $project_name_new 
echo 新groupID $group_id_new
echo 新包名    $package_new
echo

echo 正在处理 .git目录
gitPath=$PARENT_PATH/.git
if [ -d ${gitPath} ]
then
    rm -rf $gitPath
fi

echo 正在处理 .svn目录
svnPath=$PARENT_PATH/.svn
if [ -d ${svnPath} ]
then
    rm -rf $svnPath
fi


echo 正在处理 java文件存储路径
processJavaPath_develop $proj
processJavaPath_test $proj


echo 正在处理 java文件包路径
find . -name "*.java" |xargs sed -ig "s/$package_old/$package_new/g"
find . -name "*.javag" |xargs -n5 rm -rf


echo 正在处理 .project
find . -name "*.project" |xargs sed -ig "s/$project_name_old/$project_name_new/g"
find . -name "*.projectg" |xargs -n5 rm -rf

echo 正在处理 pom.xml
# 注 对于包名中有. 而在正在表达式中 .正好又是特殊字符代表匹配所有字符,这里没有转义,有可能有问题 但既然.可匹配所有字符,当然可以匹配自己了,所以应该没有问题
# 另我先替换主类 再替换groupId 最后替换project名称(artifactId) 也有用意 我怕mainClass或者groupId中包含project名先替换了导致错误
find . -name "pom.xml" |xargs sed -ig "s/$main_class_old/$main_class_new/g"
find . -name "pom.xml" |xargs sed -ig "s/$group_id_old/$group_id_new/g"
find . -name "pom.xml" |xargs sed -ig "s/$project_name_old/$project_name_new/g"
find . -name "pom.xmlg" |xargs -n5 rm -rf

echo 正在处理 xml文件
find . -name "applicationContext.xml" |xargs sed -ig "s/$package_old/$package_new/g"
find . -name "*applicationContext.xmlg" |xargs  -n5 rm -rf

echo 正在处理 启动脚本
# XXX 因是windows脚本文件格式和文件编码不一样,如果要处理需要考虑文件格式所以还是手动处理下吧
#find . -name "runjar.bat" |xargs sed -ig "s/$jar_name_old/$jar_name_new/g"
#find . -name "runjar.batg" |xargs  -n5 rm -rf
find . -name "runjar.sh" |xargs sed -ig "s/$jar_name_old/$jar_name_new/g"
find . -name "runjar.shg" |xargs  -n5 rm -rf

echo 因文件编码不一样 导致脚本处理麻烦 所以runjar.bat 中的jar包名称需要手动修改!!!

echo
echo done.


