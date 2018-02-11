@echo off




set env=dev

set configPath=config/setting-%env%.properties

set jarName=springhibernate-demo-0.0.1-SNAPSHOT.jar




echo ��������,���Ժ�....
echo batPath:%~dp0

cd %~dp0%\..

set parentDir=%cd%

set configPathAbsolute=%parentDir%/%configPath%

if exist %configPath% (
    echo %configPath% ����
    java  -Dconfig.path=%configPathAbsolute% -Dservice.env=%env% %JAVA_OPTS% -jar lib/%jarName%
) else ( 
    echo %configPath% ������
    java  -Dservice.env=%env% %JAVA_OPTS% -jar lib/%jarName%
)
@pause
